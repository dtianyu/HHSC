/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.InventoryTransaction;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.ProductionOrderDetail;
import com.hhsc.entity.ProductionPicking;
import com.hhsc.entity.ProductionPickingDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ProductionPickingBean extends SuperBean<ProductionPicking> {

    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;
    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private ProductionPickingDetailBean productionPickingDetailBean;

    private List<ProductionPickingDetail> detailList;

    private List<ItemInventory> inventoryList;

    public ProductionPickingBean() {
        super(ProductionPicking.class);
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(productionPickingDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    @Override
    public ProductionPicking unverify(ProductionPicking entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        ProductionOrderDetail s;
        try {

            ProductionPicking e = getEntityManager().merge(entity);
            setDetailList(productionPickingDetailBean.findByPId(e.getFormid()));

            //删除库存交易
            List<InventoryTransaction> transactionList = inventoryTransactionBean.findByFormid(e.getFormid());
            if (transactionList != null && !transactionList.isEmpty()) {
                inventoryTransactionBean.delete(transactionList);
            }

            for (ProductionPickingDetail d : detailList) {
                //更新库存数量
                ItemInventory i = new ItemInventory();
                i.setItemmaster(d.getItemmaster());
                i.setColorno(d.getColorno());
                i.setBrand(d.getBrand());
                i.setBatch(d.getBatch());
                i.setSn(d.getSn());
                i.setWarehouse(d.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(d.getQty().multiply(BigDecimal.valueOf(e.getTransactionType().getIocode())));//出库就 x(-1)
                inventoryList.add(i);

                if (d.getSrcformid() != null && d.getSeq() > 0) {
                    //更新制令领料数量
                    s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setIssqty(s.getIssqty().subtract(d.getQty()));
                        if (s.getIssqty().compareTo(BigDecimal.ZERO) == 1) {
                            s.setIssdate(null);
                        }
                        productionOrderDetailBean.update(s);
                    }
                }
            }
            itemInventoryBean.subtract(inventoryList);//出库变负值,减负值等于加
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ProductionPicking verify(ProductionPicking entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ProductionPicking e = getEntityManager().merge(entity);
            setDetailList(productionPickingDetailBean.findByPId(e.getFormid()));
            for (ProductionPickingDetail d : detailList) {
                //更新库存交易
                InventoryTransaction t = new InventoryTransaction();
                t.setTrtype(e.getTransactionType());
                t.setFormid(e.getFormid());
                t.setFormdate(e.getFormdate());
                t.setSeq(d.getSeq());
                t.setItemmaster(d.getItemmaster());
                t.setColorno(d.getColorno());
                t.setBrand(d.getBrand());
                t.setBatch(d.getBatch());
                t.setSn(d.getSn());
                t.setQty(d.getQty());
                t.setUnit(d.getUnit());
                t.setWarehouse(d.getWarehouse());
                t.setIocode(e.getTransactionType().getIocode());
                t.setProptype(d.getItemmaster().getProptype());
                t.setMaketype(d.getItemmaster().getMaketype());
                t.setSrcapi(d.getSrcapi());
                t.setSrcformid(d.getSrcformid());
                t.setSrcseq(d.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);

                //更新库存数量
                ItemInventory i = new ItemInventory();
                i.setItemmaster(d.getItemmaster());
                i.setColorno(d.getColorno());
                i.setBrand(d.getBrand());
                i.setBatch(d.getBatch());
                i.setSn(d.getSn());
                i.setWarehouse(d.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(d.getQty().multiply(BigDecimal.valueOf(e.getTransactionType().getIocode())));//出库就 x(-1)
                inventoryList.add(i);

                if (d.getSrcformid() != null && d.getSeq() > 0) {
                    //更新制令领料数量
                    ProductionOrderDetail s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setIssqty(s.getIssqty().add(d.getQty()));
                        s.setIssdate(e.getFormdate());
                        productionOrderDetailBean.update(s);
                    }
                }

            }
            itemInventoryBean.add(inventoryList);//出库变负值,加负值等于减
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<ProductionPickingDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ProductionPickingDetail> detailList) {
        this.detailList = detailList;
    }

}

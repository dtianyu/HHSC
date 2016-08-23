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
import com.hhsc.entity.ProductionFinish;
import com.hhsc.entity.ProductionFinishDetail;
import com.hhsc.entity.TransactionType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ProductionFinishBean extends SuperBean<ProductionFinish> {

    @EJB
    private TransactionTypeBean transactionTypeBean;
    @EJB
    private InventoryTransactionBean inventoryTransactionBean;
    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;

    @EJB
    private ProductionFinishDetailBean productionFinishDetailBean;

    private List<ProductionFinishDetail> detailList;

    private List<ItemInventory> inventoryList;

    public ProductionFinishBean() {
        super(ProductionFinish.class);
    }

    @Override
    public ProductionFinish unverify(ProductionFinish entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        ItemInventory i;
        ProductionOrderDetail s;
        try {

            ProductionFinish e = getEntityManager().merge(entity);
            detailList = productionFinishDetailBean.findByPId(e.getFormid());

            //删除库存交易
            List<InventoryTransaction> transactionList = inventoryTransactionBean.findByFormid(e.getFormid());
            if (transactionList != null && !transactionList.isEmpty()) {
                inventoryTransactionBean.delete(transactionList);
            }

            for (ProductionFinishDetail d : detailList) {
                s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                if (s == null) {
                    throw new RuntimeException("找不到来源单号");
                }
                if (s.getFinqty().compareTo(d.getQty()) < 0) {
                    throw new RuntimeException(s.getPid() + "可还原数量不足!");
                }

                //良品入库还原
                //更新库存数量
                i = new ItemInventory();
                i.setItemmaster(d.getDesign());
                i.setColorno(d.getColorno());
                i.setBrand(d.getBrand());
                i.setBatch(d.getItemno());
                i.setSn(d.getSn());
                i.setWarehouse(d.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(d.getQcqty().multiply(BigDecimal.valueOf(d.getTransactionType().getIocode())));//出库就 x(-1)
                inventoryList.add(i);

                //不良入库还原
                //更新库存数量
                if (d.getBadqty().compareTo(BigDecimal.ZERO) != 0) {
                    i = new ItemInventory();
                    i.setItemmaster(d.getDesign());
                    i.setColorno(d.getColorno());
                    i.setBrand(d.getBrand());
                    i.setBatch(d.getItemno());
                    i.setSn(d.getSn());
                    i.setWarehouse(d.getWarehouse2());
                    i.setPreqty(BigDecimal.ZERO);
                    i.setQty(d.getBadqty());//处理简化处理
                    inventoryList.add(i);
                }
                
                s.setFinqty(s.getFinqty().subtract(d.getQty()));
                getEntityManager().merge(s);
            }
            itemInventoryBean.subtract(inventoryList);//出库变负值,加负值等于减
            return e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public ProductionFinish verify(ProductionFinish entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }

        TransactionType bTT = transactionTypeBean.findByTrtype("MFB");
        InventoryTransaction t;
        ItemInventory i;
        ProductionOrderDetail s;
        try {

            ProductionFinish e = getEntityManager().merge(entity);
            detailList = productionFinishDetailBean.findByPId(e.getFormid());
            for (ProductionFinishDetail d : detailList) {

                s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                if (s == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                    throw new RuntimeException("找不到来源单号");
                }
                //良品入库处理
                //更新库存交易
                t = new InventoryTransaction();
                t.setTrtype(d.getTransactionType());
                t.setFormid(e.getFormid());
                t.setFormdate(e.getFormdate());
                t.setSeq(d.getSeq());
                t.setItemmaster(d.getDesign());
                t.setColorno(d.getColorno());
                t.setBrand(d.getBrand());
                t.setBatch(d.getItemno());
                t.setSn(d.getSn());
                t.setQty(d.getQcqty());
                t.setUnit(d.getUnit());
                t.setWarehouse(d.getWarehouse());
                t.setIocode(d.getTransactionType().getIocode());
                t.setProptype(d.getDesign().getProptype());
                t.setMaketype(d.getDesign().getMaketype());
                t.setSrcapi(d.getSrcapi());
                t.setSrcformid(d.getSrcformid());
                t.setSrcseq(d.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);

                //更新库存数量
                i = new ItemInventory();
                i.setItemmaster(d.getDesign());
                i.setColorno(d.getColorno());
                i.setBrand(d.getBrand());
                i.setBatch(d.getItemno());
                i.setSn(d.getSn());
                i.setWarehouse(d.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(d.getQcqty().multiply(BigDecimal.valueOf(d.getTransactionType().getIocode())));//出库就 x(-1)
                inventoryList.add(i);

                //不良入库处理
                if (d.getBadqty().compareTo(BigDecimal.ZERO) != 0) {
                    //更新库存交易
                    t = new InventoryTransaction();
                    t.setTrtype(bTT);
                    t.setFormid(e.getFormid());
                    t.setFormdate(e.getFormdate());
                    t.setSeq(9000 + d.getSeq());
                    t.setItemmaster(d.getDesign());
                    t.setColorno(d.getColorno());
                    t.setBrand(d.getBrand());
                    t.setBatch(d.getItemno());
                    t.setSn(d.getSn());
                    t.setQty(d.getBadqty());
                    t.setUnit(d.getUnit());
                    t.setWarehouse(d.getWarehouse2());
                    t.setIocode(bTT.getIocode());
                    t.setProptype(d.getDesign().getProptype());
                    t.setMaketype(d.getDesign().getMaketype());
                    t.setSrcapi(d.getSrcapi());
                    t.setSrcformid(d.getSrcformid());
                    t.setSrcseq(d.getSrcseq());
                    t.setStatus(e.getStatus());
                    t.setCfmuser(e.getCfmuser());
                    t.setCfmdate(e.getCfmdate());
                    inventoryTransactionBean.setDefaultValue(t);
                    inventoryTransactionBean.persist(t);

                    //更新库存数量
                    i = new ItemInventory();
                    i.setItemmaster(d.getDesign());
                    i.setColorno(d.getColorno());
                    i.setBrand(d.getBrand());
                    i.setBatch(d.getItemno());
                    i.setSn(d.getSn());
                    i.setWarehouse(d.getWarehouse2());
                    i.setPreqty(BigDecimal.ZERO);
                    i.setQty(d.getBadqty().multiply(BigDecimal.valueOf(bTT.getIocode())));//出库就 x(-1)
                    inventoryList.add(i);
                }
                
                s.setFinqty(s.getFinqty().add(d.getQty()));
                getEntityManager().merge(s);
            }
            itemInventoryBean.add(inventoryList);//出库变负值,加负值等于减
            return e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * @return the detailList
     */
    public List<ProductionFinishDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ProductionFinishDetail> detailList) {
        this.detailList = detailList;
    }

}

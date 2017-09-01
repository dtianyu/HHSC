/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemInventory;
import com.hhds.entity.ItemTransaction;
import com.hhds.entity.ItemTransactionDetail;
import com.hhds.entity.SalesOrderDetail;
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
public class ItemTransactionBean extends SuperBean<ItemTransaction> {

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;
    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private ItemTransactionDetailBean itemTransactionDetailBean;

    private List<ItemTransactionDetail> detailList;

    private List<ItemInventory> inventoryList;

    public ItemTransactionBean() {
        super(ItemTransaction.class);
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(itemTransactionDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    @Override
    public ItemTransaction unverify(ItemTransaction entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemTransaction e = getEntityManager().merge(entity);
            setDetailList(itemTransactionDetailBean.findByPId(e.getFormid()));

            //删除库存交易
            List<InventoryTransaction> transactionList = inventoryTransactionBean.findByFormid(e.getFormid());
            if (transactionList != null && !transactionList.isEmpty()) {
                inventoryTransactionBean.delete(transactionList);
            }

            for (ItemTransactionDetail d : detailList) {
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

                if ("IKA".equals(e.getTransactionType().getTrtype()) && d.getSrcformid() != null && d.getSeq() > 0) {
                    //更新客供面料入库数量
                    SalesOrderDetail s = salesOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setInqty(s.getInqty().subtract(d.getQty()));
                        salesOrderDetailBean.update(s);
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
    public ItemTransaction verify(ItemTransaction entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemTransaction e = getEntityManager().merge(entity);
            setDetailList(itemTransactionDetailBean.findByPId(e.getFormid()));
            for (ItemTransactionDetail d : detailList) {
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

                if ("IKA".equals(e.getTransactionType().getTrtype()) && d.getSrcformid() != null && d.getSeq() > 0) {
                    //更新客供面料入库数量
                    SalesOrderDetail s = salesOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setInqty(s.getInqty().add(d.getQty()));
                        salesOrderDetailBean.update(s);
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
    public List<ItemTransactionDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ItemTransactionDetail> detailList) {
        this.detailList = detailList;
    }

}

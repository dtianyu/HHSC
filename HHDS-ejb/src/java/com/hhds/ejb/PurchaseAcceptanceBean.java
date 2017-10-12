/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemInventory;
import com.hhds.entity.PurchaseAcceptance;
import com.hhds.entity.PurchaseAcceptanceDetail;
import com.hhds.entity.PurchaseOrderDetail;
import com.hhds.entity.TransactionType;
import com.lightshell.comm.SuperEJB;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseAcceptanceBean extends SuperBean<PurchaseAcceptance> {

    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private InventoryTransactionBean inventoryTransactionBean;
    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;

    @EJB
    private PurchaseAcceptanceDetailBean purchaseAcceptanceDetailBean;

    private List<PurchaseAcceptanceDetail> detailList;

    private List<ItemInventory> inventoryList;

    public PurchaseAcceptanceBean() {
        super(PurchaseAcceptance.class);
    }

    public boolean initByHHSC(PurchaseAcceptance ac, List<PurchaseAcceptanceDetail> details) {
        HashMap<SuperEJB, List<?>> detailAdded = new HashMap<>();
        detailAdded.put(purchaseAcceptanceDetailBean, details);
        try {
            this.persist(ac, detailAdded, null, null);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(purchaseAcceptanceDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    @Override
    public PurchaseAcceptance unverify(PurchaseAcceptance entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        PurchaseOrderDetail p;
        try {
            PurchaseAcceptance e = getEntityManager().merge(entity);
            setDetailList(purchaseAcceptanceDetailBean.findByPId(e.getFormid()));
            for (PurchaseAcceptanceDetail detail : getDetailList()) {
                //删除库存交易
                InventoryTransaction it = inventoryTransactionBean.findByFormidAndSeq(detail.getPid(), detail.getSeq());
                if (it != null) {
                    inventoryTransactionBean.delete(it);
                }
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno() == null ? detail.getVendorcolorno() : detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getVendoritemno() != null ? detail.getVendoritemno() : detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(detail.getQty());
                inventoryList.add(i);
                detail.setStatus("20");
                if (detail.getSrcformid() != null && !"".equals(detail.getSrcformid())) {
                    p = purchaseOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
                    p.setInqty(p.getInqty().subtract(detail.getQty()));
                    if (p.getInqty().compareTo(BigDecimal.ZERO) == 0) {
                        p.setStatus("10");
                        p.setRelapi("");
                        p.setRelformid("");
                        p.setRelseq(0);
                    }
                    purchaseOrderDetailBean.update(p);
                }
            }
            itemInventoryBean.subtract(inventoryList);
            purchaseAcceptanceDetailBean.update(getDetailList());
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PurchaseAcceptance verify(PurchaseAcceptance entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        TransactionType transactionType = transactionTypeBean.findByTrtype("PAA");
        if (transactionType == null) {
            throw new RuntimeException("PAA交易类别没有定义");
        }
        PurchaseOrderDetail p;
        try {
            PurchaseAcceptance e = getEntityManager().merge(entity);
            setDetailList(purchaseAcceptanceDetailBean.findByPId(e.getFormid()));
            for (PurchaseAcceptanceDetail detail : getDetailList()) {
                //更新库存交易
                InventoryTransaction t = new InventoryTransaction();
                t.setTrtype(transactionType);
                t.setFormid(e.getFormid());
                t.setFormdate(e.getFormdate());
                t.setSeq(detail.getSeq());
                t.setItemmaster(detail.getItemmaster());
                t.setColorno(detail.getColorno() == null ? detail.getVendorcolorno() : detail.getColorno());
                t.setBrand(detail.getBrand());
                t.setBatch(detail.getVendoritemno() != null ? detail.getVendoritemno() : detail.getBatch());
                t.setSn(detail.getSn());
                t.setQty(detail.getQcqty().add(detail.getAddqty()));
                t.setUnit(detail.getUnit());
                t.setWarehouse(e.getWarehouse());
                t.setIocode(transactionType.getIocode());
                t.setProptype(detail.getItemmaster().getProptype());
                t.setMaketype(detail.getItemmaster().getMaketype());
                t.setSrcapi(detail.getSrcapi());
                t.setSrcformid(detail.getSrcformid());
                t.setSrcseq(detail.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno() == null ? detail.getVendorcolorno() : detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getVendoritemno() != null ? detail.getVendoritemno() : detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(detail.getQty());
                inventoryList.add(i);
                detail.setStatus("50");
                if (detail.getSrcformid() != null && !"".equals(detail.getSrcformid())) {
                    p = purchaseOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
                    p.setInqty(p.getInqty().add(detail.getQty()));
                    p.setRelapi("purchaseacceptance");
                    p.setRelformid(detail.getPid());
                    p.setRelseq(detail.getSeq());
                    p.setStatus("50");
                    purchaseOrderDetailBean.update(p);
                }
            }
            itemInventoryBean.add(inventoryList);
            purchaseAcceptanceDetailBean.update(getDetailList());
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<PurchaseAcceptanceDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<PurchaseAcceptanceDetail> detailList) {
        this.detailList = detailList;
    }

}

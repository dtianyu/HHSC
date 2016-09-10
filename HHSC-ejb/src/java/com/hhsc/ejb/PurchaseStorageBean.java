/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.InventoryTransaction;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.PurchaseOrderDetail;
import com.hhsc.entity.PurchaseStorage;
import com.hhsc.entity.TransactionType;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseStorageBean extends SuperBean<PurchaseStorage> {

    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;

    public PurchaseStorageBean() {
        super(PurchaseStorage.class);
    }

    @Override
    public PurchaseStorage unverify(PurchaseStorage entity) {
        try {
            PurchaseStorage e = this.getEntityManager().merge(entity);

            //更新库存交易
            InventoryTransaction t = inventoryTransactionBean.findByFormidAndSeq(e.getPurchaseAcceptance().getFormid(), e.getSeq());
            if (t != null) {
                inventoryTransactionBean.delete(t);
            }
            //更新库存数量       
            ItemInventory i = new ItemInventory();
            i.setItemmaster(e.getItemmaster());
            i.setColorno(e.getColorno());
            i.setBrand(e.getBrand());
            i.setBatch(e.getBatch());
            i.setSn(e.getSn());
            i.setWarehouse(e.getWarehouse());
            i.setPreqty(e.getQty());
            i.setQty(BigDecimal.ZERO.subtract(e.getQcqty().add(e.getAddqty())));
            itemInventoryBean.add(i);

            //不良品处理
            if (e.getBadqty().compareTo(BigDecimal.ZERO) != 0 && e.getBadwarehouse() != null) {
                //更新库存交易
                t = inventoryTransactionBean.findByFormidAndSeq(e.getPurchaseAcceptance().getFormid(), 9000 + e.getSeq());
                if (t != null) {
                    inventoryTransactionBean.delete(t);
                }
                //更新库存数量
                i = new ItemInventory();
                i.setItemmaster(e.getItemmaster());
                i.setColorno(e.getColorno());
                i.setBrand(e.getBrand());
                i.setBatch(e.getBatch());
                i.setSn(e.getSn());
                i.setWarehouse(e.getBadwarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(BigDecimal.ZERO.subtract(e.getBadqty()));
                itemInventoryBean.add(i);
            }

            //更新采购状态 
            PurchaseOrderDetail p = purchaseOrderDetailBean.findByPIdAndSeq(e.getSrcformid(), e.getSrcseq());
            p.setInqty(p.getInqty().subtract(e.getQcqty().add(e.getAddqty())).add(e.getQty()));
            p.setStatus("30");
            purchaseOrderDetailBean.update(p);

            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PurchaseStorage verify(PurchaseStorage entity) {
        TransactionType transactionType = transactionTypeBean.findByTrtype("PAA");
        if (transactionType == null) {
            throw new RuntimeException("PAA交易类别没有定义");
        }
        try {
            PurchaseStorage e = this.getEntityManager().merge(entity);
            //更新库存交易
            InventoryTransaction t = new InventoryTransaction();
            t.setTrtype(transactionType);
            t.setFormid(e.getPurchaseAcceptance().getFormid());
            t.setFormdate(e.getAcceptdate());
            t.setSeq(e.getSeq());
            t.setItemmaster(e.getItemmaster());
            t.setColorno(e.getColorno());
            t.setBrand(e.getBrand());
            t.setBatch(e.getBatch());
            t.setSn(e.getSn());
            t.setQty(e.getQcqty().add(e.getAddqty()));
            t.setUnit(e.getUnit());
            t.setWarehouse(e.getWarehouse());
            t.setIocode(transactionType.getIocode());
            t.setProptype(e.getItemmaster().getProptype());
            t.setMaketype(e.getItemmaster().getMaketype());
            t.setSrcapi(e.getSrcapi());
            t.setSrcformid(e.getSrcformid());
            t.setSrcseq(e.getSrcseq());
            t.setStatus(e.getStatus());
            t.setCfmuser(e.getCfmuser());
            t.setCfmdate(e.getCfmdate());
            inventoryTransactionBean.setDefaultValue(t);
            inventoryTransactionBean.persist(t);

            //更新库存数量
            ItemInventory i = new ItemInventory();
            i.setItemmaster(e.getItemmaster());
            i.setColorno(e.getColorno());
            i.setBrand(e.getBrand());
            i.setBatch(e.getBatch());
            i.setSn(e.getSn());
            i.setWarehouse(e.getWarehouse());
            i.setPreqty(BigDecimal.ZERO.subtract(e.getQty()));
            i.setQty(e.getQcqty().add(e.getAddqty()));
            itemInventoryBean.add(i);

            //不良入库处理
            if (e.getBadqty().compareTo(BigDecimal.ZERO) != 0 && e.getBadwarehouse() != null) {
                //更新库存交易
                t = new InventoryTransaction();
                t.setTrtype(transactionType);
                t.setFormid(e.getPurchaseAcceptance().getFormid());
                t.setFormdate(e.getPurchaseAcceptance().getFormdate());
                t.setSeq(9000 + e.getSeq());
                t.setItemmaster(e.getItemmaster());
                t.setColorno(e.getColorno());
                t.setBrand(e.getBrand());
                t.setBatch(e.getItemno());
                t.setSn(e.getSn());
                t.setQty(e.getBadqty());
                t.setUnit(e.getUnit());
                t.setWarehouse(e.getBadwarehouse());
                t.setIocode(transactionType.getIocode());
                t.setProptype(e.getItemmaster().getProptype());
                t.setMaketype(e.getItemmaster().getMaketype());
                t.setSrcapi(e.getSrcapi());
                t.setSrcformid(e.getSrcformid());
                t.setSrcseq(e.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);

                //更新库存数量
                i = new ItemInventory();
                i.setItemmaster(e.getItemmaster());
                i.setColorno(e.getColorno());
                i.setBrand(e.getBrand());
                i.setBatch(e.getBatch());
                i.setSn(e.getSn());
                i.setWarehouse(e.getBadwarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(e.getBadqty());
                itemInventoryBean.add(i);
            }

            //更新采购状态    
            PurchaseOrderDetail p = purchaseOrderDetailBean.findByPIdAndSeq(e.getSrcformid(), e.getSrcseq());
            p.setInqty(p.getInqty().subtract(e.getQty()).add(e.getQcqty()));
            if (p.getQty().compareTo(p.getInqty()) == 0) {
                p.setStatus("AC");
            } else {
                p.setStatus("50");
            }
            purchaseOrderDetailBean.update(p);

            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

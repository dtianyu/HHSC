/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.PurchaseAcceptance;
import com.hhsc.entity.PurchaseAcceptanceDetail;
import com.hhsc.entity.PurchaseOrderDetail;
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
public class PurchaseAcceptanceBean extends SuperBean<PurchaseAcceptance> {

@EJB
private PurchaseOrderDetailBean purchaseOrderDetailBean;

@EJB
private ItemInventoryBean itemInventoryBean;

    @EJB
    private PurchaseAcceptanceDetailBean purchaseAcceptanceDetailBean;

    protected List<PurchaseAcceptanceDetail> detailList;

    private List<ItemInventory> inventoryList;

    public PurchaseAcceptanceBean() {
        super(PurchaseAcceptance.class);
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
            detailList = purchaseAcceptanceDetailBean.findByPId(e.getFormid());
            for (PurchaseAcceptanceDetail detail : detailList) {
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(detail.getQty());
                i.setQty(BigDecimal.ZERO);
                inventoryList.add(i);
                detail.setStatus("20");
                p = purchaseOrderDetailBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
                p.setInqty(p.getInqty().subtract(detail.getQty()));
                if (p.getInqty().compareTo(BigDecimal.ZERO) == 0) {
                    p.setStatus("10");
                    p.setRelapi("");
                    p.setRelformid("");
                    p.setRelseq(0);
                }
                purchaseOrderDetailBean.update(p);
            }
            itemInventoryBean.subtract(inventoryList);
            purchaseAcceptanceDetailBean.update(detailList);
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
        PurchaseOrderDetail p;
        try {
            PurchaseAcceptance e = getEntityManager().merge(entity);
            detailList = purchaseAcceptanceDetailBean.findByPId(e.getFormid());
            for (PurchaseAcceptanceDetail detail : detailList) {
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(detail.getQty());
                i.setQty(BigDecimal.ZERO);
                inventoryList.add(i);
                detail.setStatus("30");
                p = purchaseOrderDetailBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
                p.setInqty(p.getInqty().add(detail.getQty()));
                p.setRelapi("purchaseacceptance");
                p.setRelformid(detail.getPid());
                p.setRelseq(detail.getSeq());
                p.setStatus("30");
                purchaseOrderDetailBean.update(p);
            }
            itemInventoryBean.add(inventoryList);
            purchaseAcceptanceDetailBean.update(detailList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

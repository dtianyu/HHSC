/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.PurchaseOrderDetail;
import com.hhsc.entity.PurchaseStorage;
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
    private PurchaseOrderDetailBean purchaseOrderDetailBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    public PurchaseStorageBean() {
        super(PurchaseStorage.class);
    }

    @Override
    public PurchaseStorage unverify(PurchaseStorage entity) {
        try {
            PurchaseStorage e = this.getEntityManager().merge(entity);

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

            PurchaseOrderDetail p = purchaseOrderDetailBean.findByFormidAndSeq(e.getSrcformid(), e.getSrcseq());
            p.setInqty(p.getInqty().subtract(e.getQcqty()).subtract(e.getAddqty()).add(e.getQty()));
            p.setStatus("30");
            purchaseOrderDetailBean.update(p);

            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PurchaseStorage verify(PurchaseStorage entity) {
        try {
            PurchaseStorage e = this.getEntityManager().merge(entity);

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

            PurchaseOrderDetail p = purchaseOrderDetailBean.findByFormidAndSeq(e.getSrcformid(), e.getSrcseq());
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

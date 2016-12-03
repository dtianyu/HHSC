/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseStorage;
import com.hhsc.entity.PurchaseTransaction;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseTransactionBean extends SuperBean<PurchaseTransaction> {

    public PurchaseTransactionBean() {
        super(PurchaseTransaction.class);
    }

    public PurchaseTransaction createFromPurchaseStorage(PurchaseStorage d) {
        if (d == null) {
            return null;
        }
        PurchaseTransaction e = new PurchaseTransaction();
        e.setFormid(d.getPurchaseAcceptance().getFormid());
        e.setFormdate(d.getAcceptdate());
        e.setPurtype(d.getPurtype());
        e.setPurkind(d.getPurkind());
        e.setAbroad(Boolean.FALSE);
        e.setVendor(d.getPurchaseAcceptance().getVendor());
        e.setDept(d.getDept());
        e.setUserid(d.getAcceptuser().getId());
        e.setCurrency(d.getCurrency());
        e.setExchange(d.getExchange());
        e.setTaxtype(d.getTaxtype());
        e.setTaxkind(d.getTaxkind());
        e.setTaxrate(d.getTaxrate());
        e.setTradetype(d.getTradetype());
        e.setTradename(d.getTradename());
        e.setSeq(d.getSeq());
        e.setItemMaster(d.getItemmaster());
        e.setItemno(d.getItemno());
        e.setColorno(d.getColorno());
        e.setVendoritemno(d.getVendoritemno());
        e.setVendorcolorno(d.getVendorcolorno());
        e.setQty(d.getQcqty());
        e.setUnit(d.getUnit());
        e.setPrice(d.getPrice());
        e.setAmts(d.getAmts());
        e.setExtax(d.getExtax());
        e.setTaxes(d.getTaxes());
        e.setSrcapi(d.getSrcapi());
        e.setSrcformid(d.getSrcformid());
        e.setSrcseq(d.getSrcseq());
        e.setStatus(d.getStatus());
        e.setApplyqty(BigDecimal.ZERO);
        e.setApplyamts(BigDecimal.ZERO);
        e.setApplyamt(BigDecimal.ZERO);
        e.setPuramts(e.getExtax());
        e.setPuramt(e.getExtax().multiply(e.getExchange()));
        e.setPreamts(BigDecimal.ZERO);
        e.setPreamt(BigDecimal.ZERO);
        e.setAddamts(BigDecimal.ZERO);
        e.setAddamt(BigDecimal.ZERO);
        e.setOffamts(BigDecimal.ZERO);
        e.setOffamt(BigDecimal.ZERO);
        e.setTaxamts(e.getTaxes());
        e.setTaxamt(e.getTaxes().multiply(e.getExchange()));
        return e;
    }

    public PurchaseTransaction findByFormidAndSeq(Object formid, int seq) {
        Query query = this.getEntityManager().createNamedQuery("PurchaseTransaction.findByFormidAndSeq");
        query.setParameter("formid", formid);
        query.setParameter("seq", seq);
        try {
            Object o = query.getSingleResult();
            return (PurchaseTransaction) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseDraft;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseDraftBean extends SuperBean<PurchaseDraft> {

    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;


    public PurchaseDraftBean() {
        super(PurchaseDraft.class);
    }

    public List<PurchaseDraft> findByVendorIdAndItemno(Object id, String itemno) {
        Query query;
        if (itemno == null || "".equals(itemno)) {
            query = getEntityManager().createNamedQuery("PurchaseDraft.findByVendorId");
            query.setParameter("vendorid", id);
        } else {
            query = getEntityManager().createNamedQuery("PurchaseDraft.findByVendorIdAndItemno");
            query.setParameter("vendorid", id);
            query.setParameter("itemno", itemno);
        }
        return query.getResultList();
    }

    public boolean hasPurchaseOrder(String formid) {
        return !purchaseOrderDetailBean.findBySrcformid(formid).isEmpty();
    }
}

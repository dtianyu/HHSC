/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseOrderDetail;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseOrderDetailBean extends SuperBean<PurchaseOrderDetail> {

    public PurchaseOrderDetailBean() {
        super(PurchaseOrderDetail.class);
    }

    public PurchaseOrderDetail findByFormidAndSeq(String formid, int seq) {
        Query query = getEntityManager().createNamedQuery("PurchaseOrderDetail.findByFormidAndSeq");
        query.setParameter("formid", formid);
        query.setParameter("seq", seq);
        try {
            return (PurchaseOrderDetail) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}

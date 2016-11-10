/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseRequestDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseRequestDetailBean extends SuperBean<PurchaseRequestDetail> {

    public PurchaseRequestDetailBean() {
        super(PurchaseRequestDetail.class);
    }

    public List<PurchaseRequestDetail> findBySalesOrderFormid(String formid) {
        Query query = this.getEntityManager().createNamedQuery("PurchaseRequestDetail.findBySrcformid");
        query.setParameter("srcformid", formid);
        return query.getResultList();
    }

}

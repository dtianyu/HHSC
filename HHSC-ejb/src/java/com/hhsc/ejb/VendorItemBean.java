/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.VendorItem;
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
public class VendorItemBean extends SuperBean<VendorItem> {

    public VendorItemBean() {
        super(VendorItem.class);
    }

    public List<VendorItem> findByItemId(Object id) {
        Query query = getEntityManager().createNamedQuery("VendorItem.findByItemId");
        query.setParameter("itemid", id);
        return query.getResultList();
    }

    public List<VendorItem> findByItemno(String itemno) {
        Query query = getEntityManager().createNamedQuery("VendorItem.findByItemno");
        query.setParameter("itemno", itemno);
        return query.getResultList();
    }

    public VendorItem findByItemnoAndVendorno(String itemno, String vendorno) {
        Query query = getEntityManager().createNamedQuery("VendorItem.findByItemnoAndVendorno");
        query.setParameter("itemno", itemno);
        query.setParameter("vendorno", vendorno);
        try {
            return (VendorItem) query.getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }
    }
}

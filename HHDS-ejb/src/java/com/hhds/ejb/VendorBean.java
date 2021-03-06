/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.Vendor;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class VendorBean extends SuperBean<Vendor> {

    public VendorBean() {
        super(Vendor.class);
    }

    public Vendor findByVendorno(String vendorno) {
        Query query = getEntityManager().createNamedQuery("Vendor.findByVendorno");
        query.setParameter("vendorno", vendorno);
        try {
            Object o = query.getSingleResult();
            return (Vendor) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

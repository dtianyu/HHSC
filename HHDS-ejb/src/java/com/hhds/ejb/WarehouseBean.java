/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.Warehouse;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class WarehouseBean extends SuperBean<Warehouse> {

    public WarehouseBean() {
        super(Warehouse.class);
    }

    public Warehouse findByWarehouseno(String value) {
        Query query = this.getEntityManager().createNamedQuery("Warehouse.findByWarehouseno");
        query.setParameter("warehouseno", value);
        try {
            Object o = query.getSingleResult();
            return (Warehouse) o;
        } catch (Exception e) {
            return null;
        }
    }

}

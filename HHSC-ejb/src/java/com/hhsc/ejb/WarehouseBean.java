/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.Warehouse;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}

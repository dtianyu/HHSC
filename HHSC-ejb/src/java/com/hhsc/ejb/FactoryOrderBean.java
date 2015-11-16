/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrder;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryOrderBean extends SuperEJB<FactoryOrder> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public FactoryOrderBean() {
        super(FactoryOrder.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}

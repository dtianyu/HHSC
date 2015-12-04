/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrderDetail;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryOrderDetailBean extends SuperEJB<FactoryOrderDetail> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public FactoryOrderDetailBean() {
        super(FactoryOrderDetail.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<FactoryOrderDetail> findByPId(Object value) {
        return super.findByPId(Integer.parseInt(value.toString())); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.PrintDetail;
import com.lightshell.comm.SuperEJB;
import java.util.List;
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
public class PrintDetailBean extends SuperEJB<PrintDetail> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public PrintDetailBean() {
        super(PrintDetail.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<PrintDetail> findByPId(Object value) {
        return super.findByPId(Integer.parseInt(value.toString())); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

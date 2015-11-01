/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.ItemMaster;
import com.lightshell.comm.SuperEJB;
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
public class ItemMasterBean extends SuperEJB<ItemMaster> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public ItemMasterBean() {
        super(ItemMaster.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}

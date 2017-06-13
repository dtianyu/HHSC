/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemMaster;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemMasterBean extends SuperBean<ItemMaster> {

    public ItemMasterBean() {
        super(ItemMaster.class);
    }

    public ItemMaster findByItemno(String itemno) {
        Query query = getEntityManager().createNamedQuery("ItemMaster.findByItemno");
        query.setParameter("itemno", itemno);
        try {
            Object o = query.getSingleResult();
            return (ItemMaster) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

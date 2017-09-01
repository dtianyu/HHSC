/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.ItemMaster;
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

    public ItemMaster findByItemOID(String itemOID) {
        Query query = getEntityManager().createNamedQuery("ItemMaster.findByItemOID");
        query.setParameter("itemOID", itemOID);
        try {
            Object o = query.getSingleResult();
            return (ItemMaster) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

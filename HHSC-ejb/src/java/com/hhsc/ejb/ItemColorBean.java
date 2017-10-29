/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemColor;
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
public class ItemColorBean extends SuperBean<ItemColor> {

    public ItemColorBean() {
        super(ItemColor.class);
    }

    public boolean isExist(String itemno, String colorno, String customeritemno, String customercolorno) {
        if (customercolorno == null) {
            customercolorno = "";
        }
        ItemColor ic = findByItemnoAndColorno(itemno, colorno, customeritemno, customercolorno);
        return !(ic == null);
    }

    public ItemColor findByItemnoAndColorno(String itemno, String colorno, String customeritemno, String customercolorno) {
        Query query = this.getEntityManager().createNamedQuery("ItemColor.findByItemnoAndColorno");
        query.setParameter("itemno", itemno);
        query.setParameter("colorno", colorno);
        query.setParameter("customeritemno", customeritemno == null ? "" : customeritemno);
        query.setParameter("customercolorno", customercolorno == null ? "" : customercolorno);
        try {
            Object o = query.getSingleResult();
            return (ItemColor) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

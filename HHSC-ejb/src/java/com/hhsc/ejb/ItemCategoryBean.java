/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemCategory;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemCategoryBean extends SuperBean<ItemCategory> {

    public ItemCategoryBean() {
        super(ItemCategory.class);
    }

    public ItemCategory findByCategory(String value) {
        Query query = this.getEntityManager().createNamedQuery("ItemCategory.findByCategory");
        query.setParameter("category", value);
        try {
            return (ItemCategory) query.getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }
    }

}

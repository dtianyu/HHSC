/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemResource;
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
public class ItemResourceBean extends SuperBean<ItemResource> {

    public ItemResourceBean() {
        super(ItemResource.class);
    }

    public List<ItemResource> findByItemno(String itemno) {
        Query query = getEntityManager().createNamedQuery("ItemResource.findByItemno");
        query.setParameter("itemno", itemno);
        return query.getResultList();
    }

}

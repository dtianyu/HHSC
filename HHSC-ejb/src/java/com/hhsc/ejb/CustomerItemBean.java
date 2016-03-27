/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.CustomerItem;
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
public class CustomerItemBean extends SuperBean<CustomerItem> {

    public CustomerItemBean() {
        super(CustomerItem.class);
    }

    public List<CustomerItem> findByItemId(Object id) {
        Query query = getEntityManager().createNamedQuery("CustomerItem.findByItemId");
        query.setParameter("itemid", id);
        return query.getResultList();
    }

    public List<CustomerItem> findByItemno(String itemno) {
        Query query = getEntityManager().createNamedQuery("CustomerItem.findByItemno");
        query.setParameter("itemno", itemno);
        return query.getResultList();
    }

    public CustomerItem findByItemnoAndCustomerno(String itemno, String customerno) {
        Query query = getEntityManager().createNamedQuery("CustomerItem.findByItemnoAndCustomerno");
        query.setParameter("itemno", itemno);
        query.setParameter("customerno", customerno);
        try {
            return (CustomerItem) query.getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }
    }

}

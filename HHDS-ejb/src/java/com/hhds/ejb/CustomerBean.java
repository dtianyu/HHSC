/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.Customer;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class CustomerBean extends SuperBean<Customer> {

    public CustomerBean() {
        super(Customer.class);
    }

    public Customer findByCustomerno(String value) {
        Query query = getEntityManager().createNamedQuery("Customer.findByCustomerno");
        query.setParameter("customerno", value);
        try {
            Object o = query.getSingleResult();
            return (Customer) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isExist(String value) {
        return findByCustomerno(value) != null;
    }

}

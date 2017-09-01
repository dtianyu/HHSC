/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.Currency;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class CurrencyBean extends SuperBean<Currency> {

    public CurrencyBean() {
        super(Currency.class);
    }

    public Currency findByCurrency(String value) {
        Query query = getEntityManager().createNamedQuery("Currency.findByCurrency");
        query.setParameter("currency", value);
        try {
            Object o = query.getSingleResult();
            return (Currency) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

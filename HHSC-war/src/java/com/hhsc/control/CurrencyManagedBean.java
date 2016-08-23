/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CurrencyBean;
import com.hhsc.entity.Currency;
import com.hhsc.lazy.CurrencyModel;
import com.hhsc.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class CurrencyManagedBean extends SuperSingleBean<Currency> {

    @EJB
    private CurrencyBean currencyBean;
   
    public CurrencyManagedBean() {
        super(Currency.class);
    }

    @Override
    public void init() {
        this.superEJB = currencyBean;
        setModel(new CurrencyModel(currencyBean));
        super.init(); 
    }   
    
}

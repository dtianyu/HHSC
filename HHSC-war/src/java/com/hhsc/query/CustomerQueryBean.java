/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.CustomerBean;
import com.hhsc.entity.Customer;
import com.hhsc.lazy.CustomerModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "customerQueryBean")
@ViewScoped
public class CustomerQueryBean extends SuperQueryBean<Customer> {

    @EJB
    private CustomerBean customerBean;

    public CustomerQueryBean() {
        super(Customer.class);
    }

    @Override
    public void init() {
        this.superEJB = customerBean;
        setModel(new CustomerModel(customerBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("customerno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer", queryName);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerBean;
import com.hhsc.ejb.CustomerContacterBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerContacter;
import com.hhsc.lazy.CustomerModel;
import com.hhsc.web.SuperMultiBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean extends SuperMultiBean<Customer, CustomerContacter> {

    @EJB
    private CustomerBean customerBean;
    @EJB
    private CustomerContacterBean customerContacterBean;

    public CustomerManagedBean() {
        super(Customer.class, CustomerContacter.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setCurrency("RMB");
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setTradetype("C&F");
    }

    @Override
    public void createDetail() {
        super.createDetail();
        newDetail.setMajor(false);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            if (this.getCurrentSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen(), "customerno");
                this.newEntity.setCustomerno(formid);
            }
            return true;
        }
        return false;
    }

    @Override
    public void init() {
        this.superEJB = customerBean;
        this.detailEJB = customerContacterBean;
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
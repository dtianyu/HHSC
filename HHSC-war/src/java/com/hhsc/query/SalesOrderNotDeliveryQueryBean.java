/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderNotDeliveryQueryBean")
@SessionScoped
public class SalesOrderNotDeliveryQueryBean extends SalesDetailQueryBean {

    public SalesOrderNotDeliveryQueryBean() {

    }

    @Override
    public void init() {
        super.init();
        this.model.getFilterFields().put("salesOrder.status <>", "N");
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("salesOrder.formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("salesOrder.customer.customer", queryName);
            }
            if (queryDesignno != null && !"".equals(queryDesignno)) {
                this.model.getFilterFields().put("salesOrder.itemno", queryDesignno);
            }
            if (queryCustomeritemno != null && !"".equals(queryCustomeritemno)) {
                this.model.getFilterFields().put("salesOrder.customeritemno", queryCustomeritemno);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("salesOrder.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("salesOrder.formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("salesOrder.status <>", "N");
            }
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("salesOrder.salesman.id", userManagedBean.getCurrentUser().getId());
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("salesOrder.status <>", "N");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesTransactionBean;
import com.hhsc.entity.SalesTransaction;
import com.hhsc.lazy.SalesTransactionModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesTransactionQueryBean")
@ViewScoped
public class SalesTransactionQueryBean extends SuperQueryBean<SalesTransaction> {

    @EJB
    private SalesTransactionBean salesTransactionBean;

    private String queryCustomerno;
    private String queryItemno;

    public SalesTransactionQueryBean() {
        super(SalesTransaction.class);
    }

    @Override
    public void init() {
        this.superEJB = salesTransactionBean;
        setModel(new SalesTransactionModel(salesTransactionBean));
        this.model.getFilterFields().put("status", "50");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryItemno != null && !"".equals(this.queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (this.queryCustomerno != null && !"".equals(this.queryCustomerno)) {
                this.model.getFilterFields().put("customer.customerno", queryCustomerno);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (this.queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (this.queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (this.queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
            if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
                if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                    this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
                }
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "50");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
    }

    /**
     * @return the queryCustomerno
     */
    /**
     * @return the queryCustomerno
     */
    public String getQueryCustomerno() {
        return queryCustomerno;
    }

    /**
     * @param queryCustomerno the queryCustomerno to set
     */
    public void setQueryCustomerno(String queryCustomerno) {
        this.queryCustomerno = queryCustomerno;
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

}

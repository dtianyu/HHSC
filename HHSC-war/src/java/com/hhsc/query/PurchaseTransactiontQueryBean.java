/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.PurchaseTransactionBean;
import com.hhsc.ejb.PurchaseTransactionForPaymentBean;
import com.hhsc.entity.PurchaseTransaction;
import com.hhsc.lazy.PurchaseTransactionModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "purchaseTransactiontQueryBean")
@ViewScoped
public class PurchaseTransactiontQueryBean extends SuperQueryBean<PurchaseTransaction> {

    @EJB
    private PurchaseTransactionBean purchaseTransactionBean;

    private String queryVendorno;

    public PurchaseTransactiontQueryBean() {
        super(PurchaseTransaction.class);
    }

    @Override
    public void init() {
        setSuperEJB(purchaseTransactionBean);
        setModel(new PurchaseTransactionModel(purchaseTransactionBean));
        this.model.getFilterFields().put("status <>", "PF");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("formid", this.queryFormId);
            }
            if (this.queryVendorno != null) {
                this.model.getFilterFields().put("vendor.vendorno", queryVendorno);
            }
            if (this.queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.queryVendorno != null) {
            this.model.getFilterFields().put("vendor.vendorno", queryVendorno);
        }
    }

    /**
     * @return the queryVendorno
     */
    public String getQueryVendorno() {
        return queryVendorno;
    }

}

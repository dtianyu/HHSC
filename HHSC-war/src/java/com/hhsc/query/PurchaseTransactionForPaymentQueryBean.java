/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

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
@ManagedBean(name = "purchaseTransactionForPaymentQueryBean")
@ViewScoped
public class PurchaseTransactionForPaymentQueryBean extends SuperQueryBean<PurchaseTransaction> {

    @EJB
    private PurchaseTransactionForPaymentBean purchaseTransactionForPaymentBean;

    private String queryVendorno;

    public PurchaseTransactionForPaymentQueryBean() {
        super(PurchaseTransaction.class);
    }

    @Override
    public void init() {
        setSuperEJB(purchaseTransactionForPaymentBean);
        setModel(new PurchaseTransactionModel(purchaseTransactionForPaymentBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("vendorno")) {
                queryVendorno = params.get("vendorno")[0];
                this.model.getFilterFields().put("vendor.vendorno", queryVendorno);
            }
            if (params.containsKey("vendor")) {
                queryName = params.get("vendor")[0];
            }
        }
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

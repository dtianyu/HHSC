/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.AccountReceivableForReceiptBean;
import com.hhsc.entity.AccountReceivable;
import com.hhsc.lazy.AccountReceivableModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "accountReceivableForReceiptQueryBean")
@ViewScoped
public class AccountReceivableForReceiptQueryBean extends SuperQueryBean<AccountReceivable> {

    @EJB
    private AccountReceivableForReceiptBean accountReceivableForReceiptBean;

    private String customerno;

    public AccountReceivableForReceiptQueryBean() {
        super(AccountReceivable.class);
    }

    @Override
    public void init() {
        setSuperEJB(accountReceivableForReceiptBean);
        setModel(new AccountReceivableModel(accountReceivableForReceiptBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("customerno")) {
                customerno = params.get("customerno")[0];
                this.model.getFilterFields().put("customer.customerno", customerno);
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("salesOrder.formid", this.queryFormId);
            }
            if (this.customerno != null) {
                this.model.getFilterFields().put("customer.customerno", customerno);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.customerno != null) {
            this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
        }
    }

    /**
     * @return the customerno
     */
    public String getCustomerno() {
        return customerno;
    }

}

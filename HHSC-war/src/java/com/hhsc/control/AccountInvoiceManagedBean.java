/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "accountInvoiceManagedBean")
@SessionScoped
public class AccountInvoiceManagedBean extends SalesInvoiceManagedBean {

    /**
     * Creates a new instance of AccountInvoiceManagedBean
     */
    public AccountInvoiceManagedBean() {
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (!super.doBeforeUpdate()) {
            return false;
        }
        this.detailList.forEach((d) -> {
            d.setPid(currentEntity.getFormid());
            this.editedDetailList.add(d);
        });
        return true;
    }

}

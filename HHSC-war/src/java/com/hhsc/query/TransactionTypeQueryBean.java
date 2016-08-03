/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.TransactionTypeBean;
import com.hhsc.entity.TransactionType;
import com.hhsc.lazy.TransactionTypeModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean
@ViewScoped
public class TransactionTypeQueryBean extends SuperQueryBean<TransactionType> {

    @EJB
    private TransactionTypeBean transactionTypeBean;

    private String sysid;

    public TransactionTypeQueryBean() {
        super(TransactionType.class);
    }

    @Override
    public void init() {
        this.superEJB = transactionTypeBean;
        setModel(new TransactionTypeModel(transactionTypeBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("sysid")) {
                sysid = params.get("sysid")[0];
                this.model.getFilterFields().put("sysid", sysid);
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("trtype", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("trname", this.queryName);
            }
        }
    }

}

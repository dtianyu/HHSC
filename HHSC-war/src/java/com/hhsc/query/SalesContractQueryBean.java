/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesContractBean;
import com.hhsc.entity.SalesContract;
import com.hhsc.lazy.SalesContractModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@ViewScoped
public class SalesContractQueryBean extends SuperQueryBean<SalesContract> {

    @EJB
    private SalesContractBean salesContractBean;

    public SalesContractQueryBean() {
        super(SalesContract.class);
    }

    @Override
    public void init() {
        this.superEJB = salesContractBean;
        setModel(new SalesContractModel(salesContractBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("name", this.queryName);
            }
        }
    }

}

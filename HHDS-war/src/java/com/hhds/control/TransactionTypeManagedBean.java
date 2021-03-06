/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.TransactionTypeBean;
import com.hhds.entity.TransactionType;
import com.hhds.lazy.TransactionTypeModel;
import com.hhds.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class TransactionTypeManagedBean extends SuperSingleBean<TransactionType> {

    @EJB
    private TransactionTypeBean transactionTypeBean;

    public TransactionTypeManagedBean() {
        super(TransactionType.class);
    }

    @Override
    public void init() {
        this.superEJB = transactionTypeBean;
        setModel(new TransactionTypeModel(transactionTypeBean));
        this.model.getSortFields().put("sysid", "ASC");
        this.model.getSortFields().put("trtype", "ASC");
        super.init();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.TransactionTypeBean;
import com.hhsc.entity.TransactionType;
import com.hhsc.lazy.TransactionTypeModel;
import com.hhsc.web.SuperSingleBean;
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
        super.init();
    }

}

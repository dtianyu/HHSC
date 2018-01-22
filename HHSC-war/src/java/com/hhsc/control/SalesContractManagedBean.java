/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesContractBean;
import com.hhsc.entity.SalesContract;
import com.hhsc.lazy.SalesContractModel;
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
public class SalesContractManagedBean extends SuperSingleBean<SalesContract> {

    @EJB
    private SalesContractBean salesContractBean;


    public SalesContractManagedBean() {
        super(SalesContract.class);
    }

    @Override
    public void init() {
        this.superEJB = salesContractBean;
        setModel(new SalesContractModel(salesContractBean));
        super.init();
    }


}

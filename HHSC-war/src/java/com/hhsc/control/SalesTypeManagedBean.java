/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.entity.SalesType;
import com.hhsc.lazy.SalesTypeModel;
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
public class SalesTypeManagedBean extends SuperSingleBean<SalesType> {

    @EJB
    private SalesTypeBean currencyBean;

    public SalesTypeManagedBean() {
        super(SalesType.class);
    }

    @Override
    public void init() {
        this.superEJB = currencyBean;
        setModel(new SalesTypeModel(currencyBean));
        super.init();
    }

}

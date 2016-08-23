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
@ManagedBean
@SessionScoped
public class ItemCustomerManagedBean extends ItemMasterManagedBean {

    public ItemCustomerManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        newEntity.setItemcategory(itemCategoryBean.findByCategory("999"));
    }

    @Override
    public void init() {
        super.init();
        this.model.getFilterFields().put("itemcategory.category", "999");
    }

    @Override
    public void query() {
        super.query();
        this.model.getFilterFields().put("itemcategory.category", "999");
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("itemcategory.category", "999");
    }

}

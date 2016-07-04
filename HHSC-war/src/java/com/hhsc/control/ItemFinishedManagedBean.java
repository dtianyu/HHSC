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
public class ItemFinishedManagedBean extends ItemMasterManagedBean {

    public ItemFinishedManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        newEntity.setItemcategory(itemCategoryBean.findByCategory("100"));
    }

    @Override
    public void init() {
        super.init();
        this.model.getFilterFields().put("itemcategory.category", "100");
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("itemcategory.category", "100");
    }

    @Override
    public void query() {
        super.query();
        this.model.getFilterFields().put("itemcategory.category", "100");
    }

}

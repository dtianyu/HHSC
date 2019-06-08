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
        if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
            this.model.getFilterFields().put("itemno", this.currentPrgGrant.getSysprg().getNolead());
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("itemcategory.category", "100");
        if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
            this.model.getFilterFields().put("itemno", this.currentPrgGrant.getSysprg().getNolead());
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("itemcategory.category", "100");
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("itemno", queryFormId);
            } else {
                if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
                    this.model.getFilterFields().put("itemno", this.currentPrgGrant.getSysprg().getNolead());
                }
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemdesc", queryName);
            }
            if (queryItemmake != null && !"".equals(queryItemmake)) {
                this.model.getFilterFields().put("itemmake", queryItemmake);
            }
            if (queryItemspec != null && !"".equals(queryItemspec)) {
                this.model.getFilterFields().put("itemspec", queryItemspec);
            }
        }
    }

}

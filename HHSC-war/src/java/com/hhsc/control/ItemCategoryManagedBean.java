/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemCategoryBean;
import com.hhsc.entity.ItemCategory;
import com.hhsc.lazy.ItemCategoryModel;
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
public class ItemCategoryManagedBean extends SuperSingleBean<ItemCategory> {

    @EJB
    private ItemCategoryBean itemCategoryBean;

    /**
     * Creates a new instance of ItemCategoryManagedBean
     */
    public ItemCategoryManagedBean() {
        super(ItemCategory.class);
    }

    @Override
    public void init() {
        this.superEJB = itemCategoryBean;
        setModel(new ItemCategoryModel(itemCategoryBean));
        super.init();
    }

}

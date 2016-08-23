/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.define.ItemMakeType;
import com.hhsc.ejb.ItemMakeTypeBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemMakeTypeManagedBean")
@SessionScoped
public class ItemMakeTypeManagedBean implements Serializable {

    @EJB
    private ItemMakeTypeBean itemMakeTypeBean;

    protected List<ItemMakeType> itemMakeTypes;

    /**
     * Creates a new instance of ItemPropTypeManagedBean
     */
    public ItemMakeTypeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        itemMakeTypes = itemMakeTypeBean.getItemMakeTypes();
    }

    @PreDestroy
    public void predestroy() {
        if (itemMakeTypes != null) {
            itemMakeTypes.clear();
            itemMakeTypes = null;
        }
    }

    /**
     * @return the itemMakeTypes
     */
    public List<ItemMakeType> getItemMakeTypes() {
        return itemMakeTypes;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.define.ItemPropType;
import com.hhsc.ejb.ItemPropTypeBean;
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
@ManagedBean(name = "itemPropTypeManagedBean")
@SessionScoped
public class ItemPropTypeManagedBean implements Serializable {

    @EJB
    private ItemPropTypeBean itemPropTypeBean;

    protected List<ItemPropType> itemPropTypes;

    /**
     * Creates a new instance of ItemPropTypeManagedBean
     */
    public ItemPropTypeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        itemPropTypes = itemPropTypeBean.getItemPropTypes();
    }

    @PreDestroy
    public void predestroy() {
        if (this.itemPropTypes != null) {
            this.itemPropTypes.clear();
            this.itemPropTypes = null;
        }
    }

    /**
     * @return the itemPropTypes
     */
    public List<ItemPropType> getItemPropTypes() {
        return itemPropTypes;
    }

}

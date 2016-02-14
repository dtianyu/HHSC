/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.define.ItemPPType;
import com.hhsc.ejb.ItemPPTypeBean;
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
@ManagedBean(name = "itemPPTypeManagedBean")
@SessionScoped
public class ItemPPTypeManagedBean implements Serializable {

    @EJB
    private ItemPPTypeBean itemPPTypeBean;

    protected List<ItemPPType> itemPPTypes;

    public ItemPPTypeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        this.itemPPTypes = itemPPTypeBean.getItemPPTypes();
    }

    @PreDestroy
    public void predestroy() {
        if (getItemPPTypes() != null) {
            getItemPPTypes().clear();
            itemPPTypes = null;
        }
    }

    /**
     * @return the itemPPTypes
     */
    public List<ItemPPType> getItemPPTypes() {
        return itemPPTypes;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.ItemMaster;
import com.hhsc.lazy.ItemMasterModel;
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
public class ItemMasterManagedBean extends SuperSingleBean<ItemMaster> {

    @EJB
    private ItemMasterBean itemMasterBean;

    public ItemMasterManagedBean() {
        super(ItemMaster.class);
    }

    @Override
    public void init() {
        this.superEJB = itemMasterBean;
        setModel(new ItemMasterModel(itemMasterBean));
        super.init(); 
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.query;

import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.ItemMaster;
import com.hhds.lazy.ItemMasterModel;
import com.hhds.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemFinishedQueryBean")
@ViewScoped
public class ItemFinishedQueryBean extends SuperQueryBean<ItemMaster> {

    @EJB
    private ItemMasterBean itemMasterBean;

    public ItemFinishedQueryBean() {
        super(ItemMaster.class);
    }

    @Override
    public void init() {
        setSuperEJB(itemMasterBean);
        setModel(new ItemMasterModel(itemMasterBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("itemno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("itemdesc", this.queryName);
            }
        }
    }

}

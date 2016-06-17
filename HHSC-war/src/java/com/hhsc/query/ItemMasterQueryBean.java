/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.ItemMaster;
import com.hhsc.lazy.ItemMasterModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemMasterQueryBean")
@ViewScoped
public class ItemMasterQueryBean extends SuperQueryBean<ItemMaster> {

    @EJB
    private ItemMasterBean itemMasterBean;

    public ItemMasterQueryBean() {
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
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("itemno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("itemdesc", this.queryName);
            }
        }
    }

}

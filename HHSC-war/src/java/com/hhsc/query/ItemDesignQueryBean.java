/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.ItemMaster;
import com.hhsc.lazy.ItemDesignModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemDesignQueryBean")
@ViewScoped
public class ItemDesignQueryBean extends SuperQueryBean<ItemMaster> {

    @EJB
    private ItemMasterBean itemMasterBean;

    protected String designid;

    public ItemDesignQueryBean() {
        super(ItemMaster.class);
    }

    @Override
    public void init() {
        setSuperEJB(itemMasterBean);
        setModel(new ItemDesignModel(itemMasterBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.designid != null && !"".equals(this.designid)) {
                this.model.getFilterFields().put("itemno", this.designid);
            }
        }
    }

    /**
     * @return the designid
     */
    public String getDesignid() {
        return designid;
    }

    /**
     * @param designid the designid to set
     */
    public void setDesignid(String designid) {
        this.designid = designid;
    }

}

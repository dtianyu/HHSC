/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemDesignBean;
import com.hhsc.entity.ItemDesign;
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
public class ItemDesignQueryBean extends SuperQueryBean<ItemDesign> {

    @EJB
    private ItemDesignBean itemDesignBean;

    protected String designid;

    public ItemDesignQueryBean() {
        super(ItemDesign.class);
    }

    @Override
    public void init() {
        setSuperEJB(itemDesignBean);
        setModel(new ItemDesignModel(itemDesignBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.designid != null && !"".equals(this.designid)) {
                this.model.getFilterFields().put("designid", this.designid);
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

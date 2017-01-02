/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemColorBean;
import com.hhsc.entity.ItemColor;
import com.hhsc.lazy.ItemColorModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemColorQueryBean")
@ViewScoped
public class ItemColorQueryBean extends SuperQueryBean<ItemColor> {

    @EJB
    private ItemColorBean itemColorBean;

    public ItemColorQueryBean() {
        super(ItemColor.class);
    }

    @Override
    public void init() {
        setSuperEJB(itemColorBean);
        setModel(new ItemColorModel(itemColorBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null && params.containsKey("itemno")) {
            this.queryFormId = params.get("itemno")[0];
            this.model.getFilterFields().put("itemno", this.queryFormId);
        }
        if (params != null && params.containsKey("customeritemno")) {
            this.queryName = params.get("customeritemno")[0];
            this.model.getFilterFields().put("customeritemno", this.queryName);
        }
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
                this.model.getFilterFields().put("customeritemno", this.queryName);
            }
        }
    }

}

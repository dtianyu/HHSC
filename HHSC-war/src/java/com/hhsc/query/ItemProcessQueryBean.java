/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemProcessBean;
import com.hhsc.entity.ItemProcess;
import com.hhsc.lazy.ItemProcessModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemProcessQueryBean")
@ViewScoped
public class ItemProcessQueryBean extends SuperQueryBean<ItemProcess> {

    @EJB
    private ItemProcessBean itemProcessBean;

    public ItemProcessQueryBean() {
        super(ItemProcess.class);
    }

    @Override
    public void init() {
        setSuperEJB(itemProcessBean);
        setModel(new ItemProcessModel(itemProcessBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("itemno")) {
                this.queryFormId = params.get("itemno")[0];
                this.model.getFilterFields().put("itemno", queryFormId);
            }
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
                this.model.getFilterFields().put("remark", this.queryName);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.queryFormId != null && !"".equals(this.queryFormId)) {
            this.model.getFilterFields().put("itemno", this.queryFormId);
        }
    }

}

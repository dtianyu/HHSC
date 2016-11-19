/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.WarehouseBean;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.WarehouseModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "warehouseQueryBean")
@ViewScoped
public class WarehouseQueryBean extends SuperQueryBean<Warehouse> {

    @EJB
    private WarehouseBean warehouseBean;

    private boolean hascost;

    public WarehouseQueryBean() {
        super(Warehouse.class);
    }

    @Override
    public void init() {
        this.superEJB = warehouseBean;
        setModel(new WarehouseModel(warehouseBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null && params.containsKey("hascost")) {
            this.hascost = Boolean.parseBoolean(params.get("vendorno")[0]);
            this.model.getFilterFields().put("hascost", hascost);
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("warehouseno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("name", this.queryName);
            }
        }
    }

}

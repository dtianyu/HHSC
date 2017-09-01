/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.WarehouseBean;
import com.hhds.entity.Warehouse;
import com.hhds.lazy.WarehouseModel;
import com.hhds.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "warehouseManagedBean")
@SessionScoped
public class WarehouseManagedBean extends SuperSingleBean<Warehouse> {

    @EJB
    private WarehouseBean warehouseBean;

    public WarehouseManagedBean() {
        super(Warehouse.class);
    }

    @Override
    public void init() {
        setSuperEJB(warehouseBean);
        setModel(new WarehouseModel(warehouseBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if ((this.queryName != null) && (!"".equals(this.queryName))) {
                this.model.getFilterFields().put("name", queryName);
            }
            if (this.queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

}

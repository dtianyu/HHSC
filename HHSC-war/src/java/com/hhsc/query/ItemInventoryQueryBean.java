/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.ItemInventoryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemInventoryQueryBean")
@ViewScoped
public class ItemInventoryQueryBean extends SuperQueryBean<ItemInventory> {

    @EJB
    private ItemInventoryBean itemInventoryBean;

    private String queryColorno;
    private Warehouse queryWarehouse;

    public ItemInventoryQueryBean() {
        super(ItemInventory.class);
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            setQueryWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void init() {
        this.superEJB = itemInventoryBean;
        setModel(new ItemInventoryModel(itemInventoryBean));
        if (queryWarehouse == null) {
            queryWarehouse = new Warehouse();
            queryWarehouse.setId(-1);
            queryWarehouse.setName("请选择仓库");
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("itemmaster.itemno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("itemmaster.itemdesc", this.queryName);
            }
            if (this.queryColorno != null && !"".equals(this.queryColorno)) {
                this.model.getFilterFields().put("colorno", this.queryColorno);
            }
            if (this.getQueryWarehouse() != null && (this.getQueryWarehouse().getId() != -1)) {
                this.model.getFilterFields().put("warehouse.warehouseno", this.queryWarehouse.getWarehouseno());
            }
        }
    }

    /**
     * @return the queryColorno
     */
    public String getQueryColorno() {
        return queryColorno;
    }

    /**
     * @param queryColorno the queryColorno to set
     */
    public void setQueryColorno(String queryColorno) {
        this.queryColorno = queryColorno;
    }

    /**
     * @return the queryWarehouse
     */
    public Warehouse getQueryWarehouse() {
        return queryWarehouse;
    }

    /**
     * @param queryWarehouse the queryWarehouse to set
     */
    public void setQueryWarehouse(Warehouse queryWarehouse) {
        this.queryWarehouse = queryWarehouse;
    }

}

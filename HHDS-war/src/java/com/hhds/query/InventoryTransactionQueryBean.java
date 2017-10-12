/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.query;

import com.hhds.ejb.InventoryTransactionBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.Warehouse;
import com.hhds.lazy.InventoryTransactionModel;
import com.hhds.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "inventoryTransactionQueryBean")
@ViewScoped
public class InventoryTransactionQueryBean extends SuperQueryBean<InventoryTransaction> {

    @EJB
    private InventoryTransactionBean itemInventoryBean;
    private String queryColorno;
    private Warehouse queryWarehouse;

    public InventoryTransactionQueryBean() {
        super(InventoryTransaction.class);
    }

    public void handleDialogReturnItemMaster(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster item = (ItemMaster) event.getObject();
            setQueryFormId(item.getItemno());
            setQueryName(item.getItemdesc());
        }
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
        setModel(new InventoryTransactionModel(itemInventoryBean));
        this.model.getSortFields().put("formdate", "ASC");
        this.model.getSortFields().put("id", "ASC");
        this.model.getFilterFields().put("itemmaster.itemno", "-1");
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
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("itemmaster.itemno", this.queryFormId);
            }
            if (this.queryColorno != null && !"".equals(this.queryColorno)) {
                this.model.getFilterFields().put("colorno", this.queryColorno);
            }
            if (this.getQueryWarehouse() != null && (this.getQueryWarehouse().getId() != -1)) {
                this.model.getFilterFields().put("warehouse.warehouseno", this.queryWarehouse.getWarehouseno());
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("itemmaster.itemno", "-1");
        queryFormId = null;
        queryName = null;
        if (queryWarehouse != null && queryWarehouse.getId() != -1) {
            queryWarehouse = new Warehouse();
            queryWarehouse.setId(-1);
            queryWarehouse.setName("请选择仓库");
        }
        queryDateBegin = null;
        queryDateEnd = null;
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

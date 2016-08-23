/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.PurchaseOrderDetailForStorageBean;
import com.hhsc.entity.PurchaseOrderDetailForStorage;
import com.hhsc.lazy.PurchaseOrderDetailForStorageModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "purchaseOrderDetailForStorageQueryBean")
@ViewScoped
public class PurchaseOrderDetailForStorageQueryBean extends SuperQueryBean<PurchaseOrderDetailForStorage> {

    @EJB
    private PurchaseOrderDetailForStorageBean purchaseOrderDetailForStorageBean;

    protected String vendorno;

    public PurchaseOrderDetailForStorageQueryBean() {
        super(PurchaseOrderDetailForStorage.class);
    }

    @Override
    public void init() {
        setSuperEJB(purchaseOrderDetailForStorageBean);
        setModel(new PurchaseOrderDetailForStorageModel(purchaseOrderDetailForStorageBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null && params.containsKey("vendorno")) {
            this.vendorno = params.get("vendorno")[0];
            this.model.getFilterFields().put("purchaseOrder.vendor.vendorno", vendorno);
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("purchaseOrder.formid", this.queryFormId);
            }
            if (this.vendorno != null) {
                this.model.getFilterFields().put("purchaseOrder.vendor.vendorno", vendorno);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.vendorno != null) {
            this.model.getFilterFields().put("purchaseOrder.vendor.vendorno", vendorno);
        }
    }

    /**
     * @return the vendorno
     */
    public String getVendorno() {
        return vendorno;
    }

    /**
     * @param vendorno the vendorno to set
     */
    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.query;

import com.hhds.ejb.PurchaseOrderDetailForStorageBean;
import com.hhds.entity.PurchaseOrderDetailForStorage;
import com.hhds.lazy.PurchaseOrderDetailForStorageModel;
import com.hhds.web.SuperQueryBean;
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
    private String querySrcformid;
    private String vendorno;

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
            this.model.getFilterFields().clear();
            if (this.vendorno != null) {
                this.model.getFilterFields().put("purchaseOrder.vendor.vendorno", vendorno);
            }
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("purchaseOrder.formid", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("itemno", this.queryName);
            }
            if (this.querySrcformid != null && !"".equals(this.querySrcformid)) {
                this.model.getFilterFields().put("srcformid", this.querySrcformid);
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

    /**
     * @return the querySrcformid
     */
    public String getQuerySrcformid() {
        return querySrcformid;
    }

    /**
     * @param querySrcformid the querySrcformid to set
     */
    public void setQuerySrcformid(String querySrcformid) {
        this.querySrcformid = querySrcformid;
    }

}

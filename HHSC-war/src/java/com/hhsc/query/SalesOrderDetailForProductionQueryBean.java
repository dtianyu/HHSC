/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesOrderDetailForProductionBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.lazy.SalesOrderDetailForProductionModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderDetailForProductionQueryBean")
@ViewScoped
public class SalesOrderDetailForProductionQueryBean extends SuperQueryBean<SalesOrderDetailForQuery> {

    @EJB
    private SalesOrderDetailForProductionBean salesOrderDetailForProductionBean;

    private String formtype;
    private String customerno;
    private String designno;

    public SalesOrderDetailForProductionQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForProductionBean);
        setModel(new SalesOrderDetailForProductionModel(salesOrderDetailForProductionBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("formtype")) {
                formtype = params.get("formtype")[0];
                this.model.getFilterFields().put("salesOrder.ordertype.type", formtype);
            }
            if (params.containsKey("customerno")) {
                customerno = params.get("customerno")[0];
                this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
            }
            if (params.containsKey("designno")) {
                designno = params.get("designno")[0];
                this.model.getFilterFields().put("salesOrder.itemno", designno);
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("salesOrder.formid", this.queryFormId);
            }
            if (this.formtype != null) {
                this.model.getFilterFields().put("salesOrder.ordertype", formtype);
            }
            if (this.customerno != null) {
                this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
            }
            if (this.designno != null) {
                this.model.getFilterFields().put("salesOrder.itemno", designno);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.formtype != null) {
            this.model.getFilterFields().put("salesOrder.ordertype", formtype);
        }
        if (this.customerno != null) {
            this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
        }
        if (this.designno != null) {
            this.model.getFilterFields().put("salesOrder.itemno", designno);
        }
    }

    /**
     * @return the customerno
     */
    public String getCustomerno() {
        return customerno;
    }

    /**
     * @return the formtype
     */
    public String getShiptype() {
        return formtype;
    }

    /**
     * @return the designno
     */
    public String getDesignno() {
        return designno;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesOrderDetailForShipBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.lazy.SalesOrderDetailForShipModel;
import com.hhsc.web.SuperQueryBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderDetailForShipQueryBean")
@ViewScoped
public class SalesOrderDetailForShipQueryBean extends SuperQueryBean<SalesOrderDetailForQuery> {

    @EJB
    private SalesOrderDetailForShipBean salesOrderDetailForShipBean;

    private String shiptype;
    private String customerno;
    private String currency;
    private String taxtype;
    private BigDecimal taxrate;

    public SalesOrderDetailForShipQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForShipBean);
        setModel(new SalesOrderDetailForShipModel(salesOrderDetailForShipBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("shiptype")) {
                shiptype = params.get("shiptype")[0];
                this.model.getFilterFields().put("salesOrder.ordertype.type", shiptype);
            }
            if (params.containsKey("customerno")) {
                customerno = params.get("customerno")[0];
                this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
            }
            if (params.containsKey("currency")) {
                currency = params.get("currency")[0];
                this.model.getFilterFields().put("salesOrder.currency", currency);
            }
            if (params.containsKey("taxtype")) {
                taxtype = params.get("taxtype")[0];
                this.model.getFilterFields().put("salesOrder.taxtype", taxtype);
            }
            if (params.containsKey("taxrate")) {
                taxrate = BigDecimal.valueOf(Double.parseDouble(params.get("taxrate")[0]));
                this.model.getFilterFields().put("salesOrder.taxrate", taxrate);
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("salesOrder.formid", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("salesOrder.itemno", this.queryName);
            }
            if (this.shiptype != null && !"".equals(this.shiptype)) {
                this.model.getFilterFields().put("salesOrder.ordertype.type", shiptype);
            }
            if (this.customerno != null && !"".equals(this.customerno)) {
                this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
            }
            if (this.currency != null && !"".equals(this.currency)) {
                this.model.getFilterFields().put("salesOrder.currency", currency);
            }
            if (this.taxtype != null && !"".equals(this.taxtype)) {
                this.model.getFilterFields().put("salesOrder.taxtype", taxtype);
            }
            if (this.taxrate != null) {
                this.model.getFilterFields().put("salesOrder.taxrate", taxrate);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.shiptype != null && !"".equals(this.shiptype)) {
            this.model.getFilterFields().put("salesOrder.ordertype.type", shiptype);
        }
        if (this.customerno != null && !"".equals(this.customerno)) {
            this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
        }
        if (this.currency != null && !"".equals(this.currency)) {
            this.model.getFilterFields().put("salesOrder.currency", currency);
        }
        if (this.taxtype != null && !"".equals(this.taxtype)) {
            this.model.getFilterFields().put("salesOrder.taxtype", taxtype);
        }
        if (this.taxrate != null) {
            this.model.getFilterFields().put("salesOrder.taxrate", taxrate);
        }
    }

    /**
     * @return the customerno
     */
    public String getCustomerno() {
        return customerno;
    }

    /**
     * @return the shiptype
     */
    public String getShiptype() {
        return shiptype;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return the taxtype
     */
    public String getTaxtype() {
        return taxtype;
    }

    /**
     * @return the taxrate
     */
    public BigDecimal getTaxrate() {
        return taxrate;
    }

}

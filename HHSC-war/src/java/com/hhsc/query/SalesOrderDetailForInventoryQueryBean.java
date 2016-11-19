/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesOrderDetailForInventoryBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.lazy.SalesOrderDetailForInventoryModel;
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
@ManagedBean(name = "salesOrderDetailForInventoryQueryBean")
@ViewScoped
public class SalesOrderDetailForInventoryQueryBean extends SuperQueryBean<SalesOrderDetailForQuery> {

    @EJB
    private SalesOrderDetailForInventoryBean salesOrderDetailForInventoryBean;

    private String shiptype;
    private String customerno;
    private String currency;
    private String taxtype;
    private BigDecimal taxrate;

    public SalesOrderDetailForInventoryQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForInventoryBean);
        setModel(new SalesOrderDetailForInventoryModel(salesOrderDetailForInventoryBean));
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
                this.model.getFilterFields().put("itemno", this.queryName);
            }
            if (this.shiptype != null) {
                this.model.getFilterFields().put("salesOrder.ordertype", shiptype);
            }
            if (this.customerno != null) {
                this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
            }
            if (this.currency != null) {
                this.model.getFilterFields().put("salesOrder.currency", currency);
            }
            if (this.taxtype != null) {
                this.model.getFilterFields().put("salesOrder.taxkind", taxtype);
            }
            if (this.taxrate != null) {
                this.model.getFilterFields().put("salesOrder.taxrate", taxrate);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.customerno != null) {
            this.model.getFilterFields().put("salesOrder.customer.customerno", customerno);
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

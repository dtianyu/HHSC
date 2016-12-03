/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesShipmentDetailForQueryBean;
import com.hhsc.entity.SalesShipmentDetailForQuery;
import com.hhsc.lazy.SalesShipmentDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesShipmentDetailQueryBean")
@ViewScoped
public class SalesShipmentDetailQueryBean extends SuperQueryBean<SalesShipmentDetailForQuery> {

    @EJB
    protected SalesShipmentDetailForQueryBean salesShipmentDetailForQueryBean;

    protected String queryCustomerno;
    private String queryCustomer;
    private String queryItemno;

    public SalesShipmentDetailQueryBean() {
        super(SalesShipmentDetailForQuery.class);
    }

    @Override
    public void init() {
        this.superEJB = salesShipmentDetailForQueryBean;
        setModel(new SalesShipmentDetailForQueryModel(salesShipmentDetailForQueryBean));
        this.model.getFilterFields().put("salesShipment.status", "N");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("salesShipment.formid", queryFormId);
            }
            if (this.queryCustomerno != null && !"".equals(this.queryCustomerno)) {
                this.model.getFilterFields().put("salesShipment.customer.customerno", queryCustomerno);
            }
            if (this.queryCustomer != null && !"".equals(this.queryCustomer)) {
                this.model.getFilterFields().put("salesShipment.customer.customer", queryCustomer);
            }
            if (this.queryDateBegin != null) {
                this.model.getFilterFields().put("salesShipment.formdateBegin", queryDateBegin);
            }
            if (this.queryDateEnd != null) {
                this.model.getFilterFields().put("salesShipment.formdateEnd", queryDateEnd);
            }
            if (this.queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("salesShipment.status", queryState);
            }
            if (this.queryItemno != null && !"".equals(this.queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("itemmaster.itemdesc", queryName);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("salesShipment.status", "N");
    }

    /**
     * @return the queryCustomerno
     */
    public String getQueryCustomerno() {
        return queryCustomerno;
    }

    /**
     * @param queryCustomerno the queryCustomerno to set
     */
    public void setQueryCustomerno(String queryCustomerno) {
        this.queryCustomerno = queryCustomerno;
    }

    /**
     * @return the queryCustomer
     */
    public String getQueryCustomer() {
        return queryCustomer;
    }

    /**
     * @param queryCustomer the queryCustomer to set
     */
    public void setQueryCustomer(String queryCustomer) {
        this.queryCustomer = queryCustomer;
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

}

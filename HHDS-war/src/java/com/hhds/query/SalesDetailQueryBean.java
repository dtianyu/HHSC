/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.query;

import com.hhds.ejb.SalesOrderDetailForQueryBean;
import com.hhds.entity.SalesOrderDetailForQuery;
import com.hhds.lazy.SalesOrderDetailForQueryModel;
import com.hhds.web.SuperQueryBean;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesDetailQueryBean")
@SessionScoped
public class SalesDetailQueryBean extends SuperQueryBean<SalesOrderDetailForQuery> {

    @EJB
    protected SalesOrderDetailForQueryBean salesOrderDetailForQueryBean;

    private String queryItemno;
    private Date queryDeliveryDateBegin;
    private Date queryDeliveryDateEnd;

    public SalesDetailQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForQueryBean);
        setModel(new SalesOrderDetailForQueryModel(salesOrderDetailForQueryBean));
        model.getSortFields().put("salesOrder.status", "ASC");
        model.getSortFields().put("salesOrder.formid", "DESC");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("salesOrder.formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("salesOrder.customer.customer", queryName);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("salesOrder.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("salesOrder.formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("salesOrder.status", queryState);
            }
            if (queryDeliveryDateBegin != null) {
                this.model.getFilterFields().put("firstdeliveryBegin", queryDeliveryDateBegin);
            }
            if (queryDeliveryDateEnd != null) {
                this.model.getFilterFields().put("firstdeliveryEnd", queryDeliveryDateEnd);
            }
        }
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

    /**
     * @return the queryDeliveryDateBegin
     */
    public Date getQueryDeliveryDateBegin() {
        return queryDeliveryDateBegin;
    }

    /**
     * @param queryDeliveryDateBegin the queryDeliveryDateBegin to set
     */
    public void setQueryDeliveryDateBegin(Date queryDeliveryDateBegin) {
        this.queryDeliveryDateBegin = queryDeliveryDateBegin;
    }

    /**
     * @return the queryDeliveryDateEnd
     */
    public Date getQueryDeliveryDateEnd() {
        return queryDeliveryDateEnd;
    }

    /**
     * @param queryDeliveryDateEnd the queryDeliveryDateEnd to set
     */
    public void setQueryDeliveryDateEnd(Date queryDeliveryDateEnd) {
        this.queryDeliveryDateEnd = queryDeliveryDateEnd;
    }

}

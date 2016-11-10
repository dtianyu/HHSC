/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesOrderDetailForQueryBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.lazy.SalesOrderDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
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
    private SalesOrderDetailForQueryBean salesOrderDetailForQueryBean;

    private String queryItemno;

    public SalesDetailQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForQueryBean);
        setModel(new SalesOrderDetailForQueryModel(salesOrderDetailForQueryBean));
        if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
            this.model.getFilterFields().put("salesOrder.salesman.id", userManagedBean.getCurrentUser().getId());
        }
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
                this.model.getFilterFields().put("salesOrder.itemno", queryItemno);
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
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("salesOrder.salesman.id", userManagedBean.getCurrentUser().getId());
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
            this.model.getFilterFields().put("salesOrder.salesman.id", userManagedBean.getCurrentUser().getId());
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

}

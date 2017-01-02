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
@ManagedBean(name = "salesQueryQueryBean")
@SessionScoped
public class SalesQueryQueryBean extends SuperQueryBean<SalesOrderDetailForQuery> {

    @EJB
    private SalesOrderDetailForQueryBean salesOrderDetailForQueryBean;

    private String queryDesignno;
    private String queryItemno;
    private String queryCustomeritemno;

    public SalesQueryQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderDetailForQueryBean);
        setModel(new SalesOrderDetailForQueryModel(salesOrderDetailForQueryBean));
        super.init();
        if(getCurrentSysprg()!=null){
            this.doPriv = getCurrentSysprg().getDopriv();
            this.doPriv = userManagedBean.getCurrentUser().getSuperuser();
        }
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
            if (queryDesignno != null && !"".equals(queryDesignno)) {
                this.model.getFilterFields().put("salesOrder.itemno", queryDesignno);
            }
            if (queryCustomeritemno != null && !"".equals(queryCustomeritemno)) {
                this.model.getFilterFields().put("salesOrder.customeritemno", queryCustomeritemno);
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
     * @return the queryDesignno
     */
    public String getQueryDesignno() {
        return queryDesignno;
    }

    /**
     * @param queryDesignno the queryDesignno to set
     */
    public void setQueryDesignno(String queryDesignno) {
        this.queryDesignno = queryDesignno;
    }

    /**
     * @return the queryCustomeritemno
     */
    public String getQueryCustomeritemno() {
        return queryCustomeritemno;
    }

    /**
     * @param queryCustomeritemno the queryCustomeritemno to set
     */
    public void setQueryCustomeritemno(String queryCustomeritemno) {
        this.queryCustomeritemno = queryCustomeritemno;
    }

}

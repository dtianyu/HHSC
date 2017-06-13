/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.InvoiceDetailForQueryBean;
import com.hhsc.entity.InvoiceDetailForQuery;
import com.hhsc.lazy.InvoiceDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "invoiceQueryBean")
@SessionScoped
public class InvoiceQueryBean extends SuperQueryBean<InvoiceDetailForQuery> {

    @EJB
    private InvoiceDetailForQueryBean invoiceDetailForQueryBean;

    private String queryDesignno;
    private String queryItemno;
    private String queryCustomeritemno;

    private Date queryShipDateBegin;
    private Date queryShipDateEnd;

    public InvoiceQueryBean() {
        super(InvoiceDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(invoiceDetailForQueryBean);
        setModel(new InvoiceDetailForQueryModel(invoiceDetailForQueryBean));
        super.init();
        if (getCurrentPrgGrant() != null) {
            this.doPriv = getCurrentPrgGrant().getDopriv();
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("invoice.formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("invoice.customer", queryName);
            }
            if (queryCustomeritemno != null && !"".equals(queryCustomeritemno)) {
                this.model.getFilterFields().put("customeritemno", queryCustomeritemno);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("invoice.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("invoice.formdateEnd", queryDateEnd);
            }
            if (getQueryShipDateBegin() != null) {
                this.model.getFilterFields().put("invoice.shipdateBegin", getQueryShipDateBegin());
            }
            if (getQueryShipDateEnd() != null) {
                this.model.getFilterFields().put("invoice.shipdateEnd", getQueryShipDateEnd());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("invoice.status", queryState);
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

    /**
     * @return the queryShipDateBegin
     */
    public Date getQueryShipDateBegin() {
        return queryShipDateBegin;
    }

    /**
     * @param queryShipDateBegin the queryShipDateBegin to set
     */
    public void setQueryShipDateBegin(Date queryShipDateBegin) {
        this.queryShipDateBegin = queryShipDateBegin;
    }

    /**
     * @return the queryShipDateEnd
     */
    public Date getQueryShipDateEnd() {
        return queryShipDateEnd;
    }

    /**
     * @param queryShipDateEnd the queryShipDateEnd to set
     */
    public void setQueryShipDateEnd(Date queryShipDateEnd) {
        this.queryShipDateEnd = queryShipDateEnd;
    }

}

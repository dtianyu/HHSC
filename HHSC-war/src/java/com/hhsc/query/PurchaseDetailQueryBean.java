/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.PurchaseDetailBean;
import com.hhsc.entity.PurchaseDetail;
import com.hhsc.lazy.PurchaseDetailModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "purchaseDetailQueryBean")
@SessionScoped
public class PurchaseDetailQueryBean extends SuperQueryBean<PurchaseDetail> {

    @EJB
    private PurchaseDetailBean purchaseDetailBean;

    protected String queryItemno;
    private String queryVendor;

    public PurchaseDetailQueryBean() {
        super(PurchaseDetail.class);
    }

    @Override
    public void init() {
        this.superEJB = purchaseDetailBean;
        setModel(new PurchaseDetailModel(purchaseDetailBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("purchaseOrder.formid", queryFormId);
            }
            if (queryVendor != null && !"".equals(queryVendor)) {
                this.model.getFilterFields().put("purchaseOrder.vendor.vendor", queryVendor);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("purchaseOrder.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("purchaseOrder.formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemmaster.itemdesc", queryName);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
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
     * @return the queryVendor
     */
    public String getQueryVendor() {
        return queryVendor;
    }

    /**
     * @param queryVendor the queryVendor to set
     */
    public void setQueryVendor(String queryVendor) {
        this.queryVendor = queryVendor;
    }

}

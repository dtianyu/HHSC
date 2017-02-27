/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.PurchaseStorageBean;
import com.hhsc.entity.PurchaseStorage;
import com.hhsc.lazy.PurchaseStorageModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "purchaseAcceptanceQueryBean")
@SessionScoped
public class PurchaseAcceptanceQueryBean extends SuperQueryBean<PurchaseStorage> {

    @EJB
    private PurchaseStorageBean purchaseStorageBean;

    protected String queryItemno;

    public PurchaseAcceptanceQueryBean() {
        super(PurchaseStorage.class);
    }

    @Override
    public void init() {
        this.superEJB = purchaseStorageBean;
        setModel(new PurchaseStorageModel(purchaseStorageBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("purchaseAcceptance.formid", queryFormId);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("purchaseAcceptance.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("purchaseAcceptance.formdateEnd", queryDateEnd);
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
        this.model.getFilterFields().put("status", "N");
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

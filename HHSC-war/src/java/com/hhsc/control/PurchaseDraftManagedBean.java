/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.PurchaseDraftBean;
import com.hhsc.ejb.VendorItemBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.PurchaseDraft;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.VendorItem;
import com.hhsc.lazy.PurchaseDraftModel;
import com.hhsc.web.SuperSingleBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name="purchaseDraftManagedBean")
@SessionScoped
public class PurchaseDraftManagedBean extends SuperSingleBean<PurchaseDraft> {

    @EJB
    private VendorItemBean vendorItemBean;

    @EJB
    private PurchaseDraftBean purchaseDraftBean;

    protected String queryItemno;

    public PurchaseDraftManagedBean() {
        super(PurchaseDraft.class);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity.getVendor() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "请输入供应商!"));
            return false;
        }
        if (this.currentEntity.getPurqty().compareTo(BigDecimal.ZERO) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "请输入采购数量!"));
            return false;
        }
        if (this.currentEntity.getPrice().compareTo(BigDecimal.ZERO) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "请输入采购单价!"));
            return false;
        }
        this.currentEntity.setAmts(this.currentEntity.getQty().multiply(this.currentEntity.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentEntity.getAmts(), 2);
        this.currentEntity.setExtax(t.getExtax());
        this.currentEntity.setTaxes(t.getTaxes());
        return super.doBeforeUpdate();
    }

    public void findVendorItem() {
        if (currentEntity.getItemno() != null && currentEntity.getVendor() != null) {
            VendorItem entity = vendorItemBean.findByItemnoAndVendorno(currentEntity.getItemno(), currentEntity.getVendor().getVendorno());
            if (entity != null) {
                this.currentEntity.setVendoritemno(entity.getVendoritemno());
            }
        }
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }
    
    public void handleDialogReturnVendorWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentEntity.setVendor(entity);
        }
    }

    @Override
    public void init() {
        this.superEJB = purchaseDraftBean;
        setModel(new PurchaseDraftModel(purchaseDraftBean));
        this.model.getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("purchaserequest.formid", queryFormId);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("purchaserequest.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("purchaserequest.formdateEnd", queryDateEnd);
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

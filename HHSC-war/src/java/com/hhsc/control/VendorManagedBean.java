/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.PurchaseDraftBean;
import com.hhsc.ejb.VendorBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Vendor;
import com.hhsc.lazy.VendorModel;
import com.hhsc.web.SuperSingleBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "vendorManagedBean")
@SessionScoped
public class VendorManagedBean extends SuperSingleBean<Vendor> {

    @EJB
    private PurchaseDraftBean purchaseDraftBean;

    @EJB
    private VendorBean vendorBean;

    public VendorManagedBean() {
        super(Vendor.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setCurrency("CNY");
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT16");
        this.newEntity.setTaxrate(BigDecimal.valueOf(16));
        this.newEntity.setTradetype("C&F");
        this.newEntity.setAbroad(false);
    }

    @Override
    protected boolean doBeforeDelete(Vendor entity) throws Exception {
        if (entity != null) {
            Map<String, Object> filters = new HashMap<>();
            filters.put("vendor.vendorno", entity.getVendorno());
            if (purchaseDraftBean.getRowCount(filters) > 0) {
                showErrorMsg("Error", "已有交易记录不能删除");
                return false;
            }
        }
        return super.doBeforeDelete(entity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            String formid = "";
            if (this.getCurrentPrgGrant().getSysprg().getNoauto()) {
                formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "vendorno");
                this.newEntity.setVendorno(formid);
            }
            return true;
        }
        return false;
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
        }
    }

    @Override
    public void init() {
        this.superEJB = vendorBean;
        setModel(new VendorModel(vendorBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("vendorno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("vendor", queryName);
            }
        }
    }

}

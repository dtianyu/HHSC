/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.VendorBean;
import com.hhsc.entity.Vendor;
import com.hhsc.lazy.CustomerModel;
import com.hhsc.lazy.VendorModel;
import com.hhsc.web.SuperSingleBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "vendorManagedBean")
@SessionScoped
public class VendorManagedBean extends SuperSingleBean<Vendor> {

    @EJB
    private VendorBean vendorBean;

    public VendorManagedBean() {
        super(Vendor.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setCurrency("RMB");
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setTradetype("C&F");
        this.newEntity.setAbroad(false);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            String formid = "";
            if (this.getCurrentSysprg().getNoauto()) {
                formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen(), "vendorno");
                this.newEntity.setVendorno(formid);
            }
            return true;
        }
        return false;
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

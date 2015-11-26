/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.FactoryOrderDetailForStorageBean;
import com.hhsc.entity.FactoryOrderDetailForStorage;
import com.hhsc.lazy.FactoryOrderDetailForStorageModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "factoryOrderDetailForStorageQueryBean")
@ViewScoped
public class FactoryOrderDetailForStorageQueryBean extends SuperQueryBean<FactoryOrderDetailForStorage> {

    @EJB
    private FactoryOrderDetailForStorageBean factoryOrderDetailForStorageBean;

    protected String pformid;
    protected String designid;

    public FactoryOrderDetailForStorageQueryBean() {
        super(FactoryOrderDetailForStorage.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderDetailForStorageBean);
        setModel(new FactoryOrderDetailForStorageModel(factoryOrderDetailForStorageBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            if (this.pformid != null && !"".equals(this.pformid)) {
                this.model.getFilterFields().put("pformid", this.pformid);
            }
            if (this.designid != null && !"".equals(this.designid)) {
                this.model.getFilterFields().put("designid", this.designid);
            }
        }
    }

    /**
     * @return the pformid
     */
    public String getPformid() {
        return pformid;
    }

    /**
     * @param pformid the pformid to set
     */
    public void setPformid(String pformid) {
        this.pformid = pformid;
    }

    /**
     * @return the designid
     */
    public String getDesignid() {
        return designid;
    }

    /**
     * @param designid the designid to set
     */
    public void setDesignid(String designid) {
        this.designid = designid;
    }

}

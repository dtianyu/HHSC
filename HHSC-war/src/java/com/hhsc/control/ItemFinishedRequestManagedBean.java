/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.entity.PurchaseRequestDetail;
import com.hhsc.lazy.ItemFinishedRequestModel;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class ItemFinishedRequestManagedBean extends PurchaseRequestManagedBean {

    /**
     * Creates a new instance of ProductRequestManagedBean
     */
    public ItemFinishedRequestManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setPurtype("100");
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.detailList != null && !this.detailList.isEmpty()) {
            for (PurchaseRequestDetail detail : detailList) {
                if (detail.getItemdesign() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "请输入花号"));
                    return false;
                }
                if (detail.getItemmaster() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "请输入面料"));
                    return false;
                }
            }
        }
        return super.doBeforePersist();
    }

    @Override
    public void init() {
        super.init();
        setModel(new ItemFinishedRequestModel(purchaseRequestBean));
    }

}

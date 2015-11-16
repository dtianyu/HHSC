/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.ejb.FactoryOrderDetailBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import com.hhsc.lazy.HGModel;
import com.hhsc.web.SuperMultiBean;
import com.lightshell.comm.BaseLib;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "hgManagedBean")
@SessionScoped
public class HGManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected String designid;

    public HGManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new HGModel(factoryOrderBean));
        getModel().getFilterFields().put("hgstatus", "N");
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setHgrecdate(BaseLib.getDate());
                currentEntity.setHgrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getHgreaded()) || (!currentEntity.getHgreaded())) {
                    currentEntity.setHgreaded(Boolean.TRUE);
                    currentEntity.setHgreaddate(BaseLib.getDate());
                }
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("hgdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("hgdeldateEnd", queryDateEnd);
            }
            if (getDesignid() != null && !"".equals(designid)) {
                this.model.getFilterFields().put("itemid", getDesignid());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("hgstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("hgstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getHgstatus() != null && currentEntity.getZbstatus() != null) {
                if ("V".equals(currentEntity.getZbstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getHgstatus()) {
                        case "V":
                            this.doEdit = currentSysprg.getDoedit() && false;
                            this.doDel = currentSysprg.getDodel() && false;
                            this.doCfm = false;
                            this.doUnCfm = currentSysprg.getDouncfm() && true;
                            break;
                        default:
                            this.doEdit = currentSysprg.getDoedit() && true;
                            this.doDel = currentSysprg.getDodel() && true;
                            this.doCfm = currentSysprg.getDocfm() && true;
                            this.doUnCfm = false;
                    }
                }
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setHgstatus("N");
                currentEntity.setHgdelman(null);
                currentEntity.setZbrecdate(null);
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setStatus("HG");
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setHgstatus("V");
                currentEntity.setHgdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setZbrecdate(getDate());
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setStatus("ZB");
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
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

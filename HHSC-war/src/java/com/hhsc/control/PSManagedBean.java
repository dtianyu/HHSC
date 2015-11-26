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
import com.hhsc.lazy.PSModel;
import com.hhsc.web.SuperMultiBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "psManagedBean")
@SessionScoped
public class PSManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected String designid;

    public PSManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new PSModel(factoryOrderBean));
        getModel().getFilterFields().put("psstatus", "N");
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setPsrecdate(getDate());
                currentEntity.setPsrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getPsreaded()) || (!currentEntity.getPsreaded())) {
                    currentEntity.setPsreaded(Boolean.TRUE);
                    currentEntity.setPsreaddate(getDate());
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
                this.model.getFilterFields().put("psdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("psdeldateEnd", queryDateEnd);
            }
            if (getDesignid() != null && !"".equals(designid)) {
                this.model.getFilterFields().put("designid", getDesignid());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("psstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("psstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getPsstatus() != null && currentEntity.getYhstatus() != null) {
                if ("V".equals(currentEntity.getYhstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getPsstatus()) {
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
            if (doBeforeUnverify()) {
                try {
                    currentEntity.setPsstatus("N");
                    currentEntity.setPsdelman(null);
                    currentEntity.setYhrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("PS");
                    update();
                    doAfterUnverify();
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
            }
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            if (doBeforeVerify()) {
                try {
                    currentEntity.setPsstatus("V");
                    currentEntity.setPsdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setYhrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("YH");
                    update();
                    doAfterVerify();
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.lazy.CPModel;
import com.hhsc.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "cpManagedBean")
@SessionScoped
public class CPManagedBean extends SuperSingleBean<FactoryOrder> {

    @EJB
    private FactoryOrderBean factoryOrderBean;

    public CPManagedBean() {
        super(FactoryOrder.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setModel(new CPModel(factoryOrderBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setCprecdate(getDate());
                currentEntity.setCprecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getCpreaded()) || (!currentEntity.getCpreaded())) {
                    currentEntity.setCpreaded(Boolean.TRUE);
                    currentEntity.setCpreaddate(getDate());
                }
                currentEntity.setCpstatus("R");
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getCpstatus() != null) {
                switch (currentEntity.getCpstatus()) {
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

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setCpstatus("M");
                currentEntity.setCpdelman(null);
                currentEntity.setStatusToModify();
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setCfmuser(null);
                currentEntity.setCfmdate(null);
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
                currentEntity.setCpstatus("V");
                currentEntity.setCpdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setStatus("V");
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setCfmdateToNow();
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.lazy.ZBModel;
import com.hhsc.web.SuperSingleBean;
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
@ManagedBean(name = "zbManagedBean")
@SessionScoped
public class ZBManagedBean extends SuperSingleBean<FactoryOrder> {

    @EJB
    private FactoryOrderBean factoryOrderBean;

    public ZBManagedBean() {
        super(FactoryOrder.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setModel(new ZBModel(factoryOrderBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setZbrecdate(BaseLib.getDate());
                currentEntity.setZbrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getZbreaded()) || (!currentEntity.getZbreaded())) {
                    currentEntity.setZbreaded(Boolean.TRUE);
                    currentEntity.setZbreaddate(BaseLib.getDate());
                }
                currentEntity.setZbstatus("R");
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getZbstatus() != null && currentEntity.getPsstatus() != null) {
                if ("V".equals(currentEntity.getPsstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getZbstatus()) {
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
                currentEntity.setZbstatus("M");
                currentEntity.setZbdelman(null);
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
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
                currentEntity.setZbstatus("V");
                currentEntity.setZbdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setYhrecdate(getDate());
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

}

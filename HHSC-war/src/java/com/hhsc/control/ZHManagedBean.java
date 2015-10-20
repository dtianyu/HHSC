/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.entity.SalesOrder;
import com.hhsc.lazy.ZHModel;
import com.hhsc.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "zhManagedBean")
@SessionScoped
public class ZHManagedBean extends SuperOperateBean<SalesOrder> {

    @EJB
    private SalesOrderBean salesOrderBean;

    public ZHManagedBean() {
        super(SalesOrder.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderBean);
        setModel(new ZHModel(salesOrderBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setZhrecdate(getDate());
                currentEntity.setZhrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getZhreaded()) || (!currentEntity.getZhreaded())) {
                    currentEntity.setZhreaded(Boolean.TRUE);
                    currentEntity.setZhreaddate(getDate());
                }
                currentEntity.setZhstatus("R");
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getZhstatus() != null && currentEntity.getCkstatus() != null) {
                if ("V".equals(currentEntity.getCkstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getZhstatus()) {
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
                currentEntity.setZhstatus("M");
                currentEntity.setZhdelman(null);
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
                currentEntity.setZhstatus("V");
                currentEntity.setZhdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setCkrecdate(getDate());
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

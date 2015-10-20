/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.entity.SalesOrder;
import com.hhsc.lazy.CKModel;
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
@ManagedBean(name = "ckManagedBean")
@SessionScoped
public class CKManagedBean extends SuperOperateBean<SalesOrder> {

    @EJB
    private SalesOrderBean salesOrderBean;

    public CKManagedBean() {
        super(SalesOrder.class);
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderBean);
        setModel(new CKModel(salesOrderBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setCkrecdate(getDate());
                currentEntity.setCkrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getCkreaded()) || (!currentEntity.getCkreaded())) {
                    currentEntity.setCkreaded(Boolean.TRUE);
                    currentEntity.setCkreaddate(getDate());
                }
                currentEntity.setCkstatus("R");
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getCkstatus() != null && currentEntity.getCpstatus() != null) {
                if ("V".equals(currentEntity.getCpstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getCkstatus()) {
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
                currentEntity.setCkstatus("M");
                currentEntity.setCkdelman(null);
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
                currentEntity.setCkstatus("V");
                currentEntity.setCkdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setCprecdate(getDate());
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

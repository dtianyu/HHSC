/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.lazy.JHModel;
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
@ManagedBean(name = "jhManagedBean")
@SessionScoped
public class JHManagedBean extends SuperSingleBean<FactoryOrder> {

    @EJB
    private FactoryOrderBean factoryOrderBean;

    public JHManagedBean() {
        super(FactoryOrder.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setModel(new JHModel(factoryOrderBean));
        super.init();
    }

    public void passHG(Boolean flag) {
        if (null != getCurrentEntity()) {
            try {
                if (flag) {
                    currentEntity.setHgdeldate(getDate());
                    currentEntity.setHgdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setHgstatus("V");
                    currentEntity.setZbrecdate(getDate());
                } else {
                    currentEntity.setHgdeldate(null);
                    currentEntity.setHgdelman(null);
                    currentEntity.setHgstatus("M");
                    currentEntity.setZbrecdate(null);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void passZB(Boolean flag) {
        if (null != getCurrentEntity()) {
            try {
                if (flag) {
                    currentEntity.setZbdeldate(getDate());
                    currentEntity.setZbdelman(null);
                    currentEntity.setZbstatus("V");
                    currentEntity.setPsrecdate(getDate());
                } else {
                    currentEntity.setZbdeldate(null);
                    currentEntity.setZbdelman(null);
                    currentEntity.setZbstatus("M");
                    currentEntity.setPsrecdate(null);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void passPS(Boolean flag) {
        if (null != getCurrentEntity()) {
            try {
                if (flag) {
                    currentEntity.setPsdeldate(getDate());
                    currentEntity.setPsdelman(null);
                    currentEntity.setPsstatus("V");
                    currentEntity.setYhrecdate(getDate());
                } else {
                    currentEntity.setPsdeldate(null);
                    currentEntity.setPsdelman(null);
                    currentEntity.setPsstatus("M");
                    currentEntity.setYhrecdate(null);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void passYH(Boolean flag) {
        if (null != getCurrentEntity()) {
            try {
                if (flag) {
                    currentEntity.setYhdeldate(getDate());
                    currentEntity.setYhdelman(null);
                    currentEntity.setYhstatus("V");
                    currentEntity.setZhrecdate(getDate());
                } else {
                    currentEntity.setYhdeldate(null);
                    currentEntity.setYhdelman(null);
                    currentEntity.setYhstatus("M");
                    currentEntity.setZhrecdate(null);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setJhrecdate(getDate());
                currentEntity.setJhrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getJhreaded()) || (!currentEntity.getJhreaded())) {
                    currentEntity.setJhreaded(Boolean.TRUE);
                    currentEntity.setJhreaddate(getDate());
                }
                currentEntity.setJhstatus("R");
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getJhstatus() != null && currentEntity.getYhstatus() != null) {
                if ("V".equals(currentEntity.getYhstatus())) {
                    this.doEdit = currentSysprg.getDoedit() && false;
                    this.doDel = currentSysprg.getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = currentSysprg.getDouncfm() && false;
                } else {
                    switch (currentEntity.getJhstatus()) {
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
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setJhstatus("M");
                currentEntity.setJhdelman(null);
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
                currentEntity.setJhstatus("V");
                currentEntity.setJhdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setHgrecdate(getDate());
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

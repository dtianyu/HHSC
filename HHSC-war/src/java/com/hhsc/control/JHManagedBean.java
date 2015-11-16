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
import com.hhsc.lazy.JHModel;
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
@ManagedBean(name = "jhManagedBean")
@SessionScoped
public class JHManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected String designid;

    public JHManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new JHModel(factoryOrderBean));
        getModel().getFilterFields().put("jhstatus", "N");
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
                    currentEntity.setStatus("ZB");
                } else {
                    currentEntity.setHgdeldate(null);
                    currentEntity.setHgdelman(null);
                    currentEntity.setHgstatus("M");
                    currentEntity.setZbrecdate(null);
                    currentEntity.setStatus("HG");
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
                    currentEntity.setStatus("PS");
                } else {
                    currentEntity.setZbdeldate(null);
                    currentEntity.setZbdelman(null);
                    currentEntity.setZbstatus("M");
                    currentEntity.setPsrecdate(null);
                    currentEntity.setStatus("ZB");
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
                    currentEntity.setStatus("YH");
                } else {
                    currentEntity.setPsdeldate(null);
                    currentEntity.setPsdelman(null);
                    currentEntity.setPsstatus("M");
                    currentEntity.setYhrecdate(null);
                    currentEntity.setStatus("PS");
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
                    currentEntity.setStatus("HZ");
                } else {
                    currentEntity.setYhdeldate(null);
                    currentEntity.setYhdelman(null);
                    currentEntity.setYhstatus("M");
                    currentEntity.setZhrecdate(null);
                    currentEntity.setStatus("YH");
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
                this.model.getFilterFields().put("orderdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("orderdateEnd", queryDateEnd);
            }
            if (designid != null && !"".equals(designid)) {
                this.model.getFilterFields().put("itemid", designid);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("jhstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("jhstatus", "N");
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
                currentEntity.setJhstatus("N");
                currentEntity.setJhdelman(null);
                currentEntity.setHgrecdate(null);
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setStatus("JH");
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
                if ("JH".equals(currentEntity.getStatus().trim())) {
                    currentEntity.setStatus("HG");
                }
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

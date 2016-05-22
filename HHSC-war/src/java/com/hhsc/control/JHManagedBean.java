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
import com.hhsc.web.FormMultiBean;
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
public class JHManagedBean extends FormMultiBean<FactoryOrder, FactoryOrderDetail> {

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
                    currentEntity.setHgstatus("N");
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
                    currentEntity.setZbstatus("N");
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
                    currentEntity.setPsstatus("N");
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
                    currentEntity.setYhstatus("N");
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
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (designid != null && !"".equals(designid)) {
                this.model.getFilterFields().put("designid", designid);
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
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getJhstatus() != null && currentEntity.getPsstatus() != null) {
            if ("V".equals(currentEntity.getPsstatus())) {
                this.doEdit = getCurrentSysprg().getDoedit() && false;
                this.doDel = getCurrentSysprg().getDodel() && false;
                this.doCfm = false;
                this.doUnCfm = getCurrentSysprg().getDouncfm() && false;
            } else {
                switch (currentEntity.getJhstatus()) {
                    case "V":
                        this.doEdit = getCurrentSysprg().getDoedit() && false;
                        this.doDel = getCurrentSysprg().getDodel() && false;
                        this.doCfm = false;
                        this.doUnCfm = getCurrentSysprg().getDouncfm() && true;
                        break;
                    default:
                        this.doEdit = getCurrentSysprg().getDoedit() && true;
                        this.doDel = getCurrentSysprg().getDodel() && true;
                        this.doCfm = getCurrentSysprg().getDocfm() && true;
                        this.doUnCfm = false;
                        break;
                }
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setJhstatus("N");
                    currentEntity.setJhdelman(null);
                    currentEntity.setHgrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("JH");
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "取消前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setJhstatus("V");
                    currentEntity.setJhdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setHgrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    if ("JH".equals(currentEntity.getStatus().trim())) {
                        currentEntity.setStatus("HG");
                    }
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
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

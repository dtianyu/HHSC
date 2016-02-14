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
import com.hhsc.lazy.ZBModel;
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
@ManagedBean(name = "zbManagedBean")
@SessionScoped
public class ZBManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected String designid;

    public ZBManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new ZBModel(factoryOrderBean));
        getModel().getFilterFields().put("zbstatus", "N");
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
                this.model.getFilterFields().put("zbdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("zbdeldateEnd", queryDateEnd);
            }
            if (getDesignid() != null && !"".equals(designid)) {
                this.model.getFilterFields().put("designid", getDesignid());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("zbstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("zbstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getZbstatus() != null && currentEntity.getPsstatus() != null) {
            if ("V".equals(currentEntity.getPsstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getZbstatus()) {
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
                    currentEntity.setZbstatus("N");
                    currentEntity.setZbdelman(null);
                    currentEntity.setPsrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("ZB");
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
                    currentEntity.setZbstatus("V");
                    currentEntity.setZbdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setPsrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("PS");
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

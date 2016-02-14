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
import com.hhsc.lazy.ZHModel;
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
@ManagedBean(name = "zhManagedBean")
@SessionScoped
public class ZHManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected String designid;

    public ZHManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new ZHModel(factoryOrderBean));
        getModel().getFilterFields().put("zhstatus", "N");
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
                this.model.getFilterFields().put("zhdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("zhdeldateEnd", queryDateEnd);
            }
            if (getDesignid() != null && !"".equals(designid)) {
                this.model.getFilterFields().put("designid", getDesignid());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("zhstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("zhstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getZhstatus() != null && currentEntity.getCkstatus() != null) {
            if ("V".equals(currentEntity.getCkstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getZhstatus()) {
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
                    currentEntity.setZhstatus("N");
                    currentEntity.setZhdelman(null);
                    currentEntity.setCkrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("HZ");
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
                    currentEntity.setZhstatus("V");
                    currentEntity.setZhdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCkrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("CK");
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

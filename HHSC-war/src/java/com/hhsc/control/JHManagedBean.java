/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.entity.ProductionOrder;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "jhManagedBean")
@SessionScoped
public class JHManagedBean extends ProductionOrderManagedBean {

    public JHManagedBean() {

    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getPsstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "配色已确认");
            return false;
        }
        if (!"V".equals(e.getJhstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "状态已变更");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        if (detailList2 != null && !detailList2.isEmpty()) {
            detailList2.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        detailList2 = detailEJB2.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getJhstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "状态已变更!");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        if (detailList2 != null && !detailList2.isEmpty()) {
            detailList2.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        detailList2 = detailEJB2.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    public void init() {
        super.init();
        this.model.getFilterFields().clear();
        this.model.getSortFields().clear();
        this.model.getSortFields().put("jhstatus", "ASC");
        this.model.getSortFields().put("formid", "DESC");
        this.model.getFilterFields().put("salesstatus", "V");
        this.model.getFilterFields().put("jhstatus", "N");
    }

    public void passHG(Boolean flag) {
        if (null != getCurrentEntity()) {
            try {
                if (flag) {
                    currentEntity.setHgdeldate(getDate());
                    currentEntity.setHgdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setHgstatus("V");
                    currentEntity.setZbrecdate(getDate());
                    currentEntity.setStatus("制版");
                } else {
                    currentEntity.setHgdeldate(null);
                    currentEntity.setHgdelman(null);
                    currentEntity.setHgstatus("N");
                    currentEntity.setZbrecdate(null);
                    currentEntity.setStatus("画稿");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
                    currentEntity.setStatus("配色");
                } else {
                    currentEntity.setZbdeldate(null);
                    currentEntity.setZbdelman(null);
                    currentEntity.setZbstatus("N");
                    currentEntity.setPsrecdate(null);
                    currentEntity.setStatus("制版");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
                    currentEntity.setStatus("印花");
                } else {
                    currentEntity.setPsdeldate(null);
                    currentEntity.setPsdelman(null);
                    currentEntity.setPsstatus("N");
                    currentEntity.setYhrecdate(null);
                    currentEntity.setStatus("配色");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
                    currentEntity.setStatus("后整");
                } else {
                    currentEntity.setYhdeldate(null);
                    currentEntity.setYhdelman(null);
                    currentEntity.setYhstatus("N");
                    currentEntity.setZhrecdate(null);
                    currentEntity.setStatus("印花");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
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
            if (designno != null && !"".equals(designno)) {
                this.model.getFilterFields().put("designno", designno);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("jhstatus", queryState);
            }
            this.model.getSortFields().put("jhstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
            this.model.getFilterFields().put("salesstatus", "V");
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getSortFields().put("jhstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
            this.model.getFilterFields().put("salesstatus", "V");
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
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("计划");
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "还原前检查失败");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
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
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    if ("计划".equals(currentEntity.getStatus().trim())) {
                        currentEntity.setStatus("画稿");
                    }
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

}

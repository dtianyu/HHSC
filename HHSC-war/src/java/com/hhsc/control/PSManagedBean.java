/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.entity.ProductionOrder;
import com.hhsc.entity.ProductionResource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "psManagedBean")
@SessionScoped
public class PSManagedBean extends ProductionOrderManagedBean {

    public PSManagedBean() {

    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getYhstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "印花已确认");
            return false;
        }
        if (!"V".equals(e.getPsstatus())) {
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
        if ("V".equals(e.getPsstatus())) {
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
        this.model.getFilterFields().put("jhstatus", "V");
        this.model.getFilterFields().put("zbstatus", "V");
        this.model.getFilterFields().put("psstatus", "N");
        this.model.getSortFields().put("psstatus", "ASC");
        this.model.getSortFields().put("formid", "DESC");
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setPsrecdate(getDate());
                currentEntity.setPsrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getPsreaded()) || (!currentEntity.getPsreaded())) {
                    currentEntity.setPsreaded(Boolean.TRUE);
                    currentEntity.setPsreaddate(getDate());
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
                this.model.getFilterFields().put("psdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("psdeldateEnd", queryDateEnd);
            }
            if (queryDesignno != null && !"".equals(queryDesignno)) {
                this.model.getFilterFields().put("designno", queryDesignno);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("psstatus", queryState);
            }
            this.model.getFilterFields().put("jhstatus", "V");
            this.model.getFilterFields().put("zbstatus", "V");
            this.model.getSortFields().put("psstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("jhstatus", "V");
            this.model.getFilterFields().put("zbstatus", "V");
            this.model.getFilterFields().put("psstatus", "N");
            this.model.getSortFields().put("psstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getPsstatus() != null && currentEntity.getYhstatus() != null) {
            if ("V".equals(currentEntity.getYhstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getPsstatus()) {
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
    protected void splitResource() {
        this.equipments.clear();
        this.processes.clear();
        this.materials.clear();
        this.hurmans.clear();
        for (ProductionResource r : detailList2) {
            if (!"PS".equals(r.getProcess().getProcessno())) {
                continue;
            }
            switch (r.getKind()) {
                case "E":
                    this.equipments.add(r);
                    break;
                case "P":
                    this.processes.add(r);
                    break;
                case "M":
                    this.materials.add(r);
                    break;
                case "H":
                    this.hurmans.add(r);
                    break;
                default:
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setPsstatus("N");
                    currentEntity.setPsdelman(null);
                    currentEntity.setYhrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("配色");
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
                    currentEntity.setPsstatus("V");
                    currentEntity.setPsdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setYhrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("印花");
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

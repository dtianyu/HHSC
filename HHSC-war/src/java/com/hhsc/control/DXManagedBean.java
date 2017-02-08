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
@ManagedBean(name = "dxManagedBean")
@SessionScoped
public class DXManagedBean extends ProductionOrderManagedBean {

    public DXManagedBean() {

    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getCkstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "仓库已确认");
            return false;
        }
        if (!"V".equals(e.getDxstatus())) {
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
        if ("V".equals(e.getDxstatus())) {
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
        this.model.getFilterFields().put("psstatus", "V");
        this.model.getFilterFields().put("dxstatus", "N");
        this.model.getSortFields().put("dxstatus", "ASC");
        this.model.getSortFields().put("formid", "DESC");
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setSxrecdate(getDate());
                currentEntity.setSxrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getSxreaded()) || (!currentEntity.getSxreaded())) {
                    currentEntity.setSxreaded(Boolean.TRUE);
                    currentEntity.setSxreaddate(getDate());
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
                this.model.getFilterFields().put("dxdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("dxdeldateEnd", queryDateEnd);
            }
            if (queryDesignno != null && !"".equals(queryDesignno)) {
                this.model.getFilterFields().put("designno", queryDesignno);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("dxstatus", queryState);
            }
            this.model.getFilterFields().put("jhstatus", "V");
            this.model.getFilterFields().put("psstatus", "V");
            this.model.getSortFields().put("dxstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("jhstatus", "V");
            this.model.getFilterFields().put("psstatus", "V");
            this.model.getFilterFields().put("dxstatus", "N");
            this.model.getSortFields().put("dxstatus", "ASC");
            this.model.getSortFields().put("formid", "DESC");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && getCurrentPrgGrant() != null && currentEntity.getZhstatus() != null && currentEntity.getCkstatus() != null) {
            if ("V".equals(currentEntity.getCkstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getDxstatus()) {
                    case "V":
                        this.doEdit = getCurrentPrgGrant().getDoedit() && false;
                        this.doDel = getCurrentPrgGrant().getDodel() && false;
                        this.doCfm = false;
                        this.doUnCfm = getCurrentPrgGrant().getDouncfm() && true;
                        break;
                    default:
                        this.doEdit = getCurrentPrgGrant().getDoedit() && true;
                        this.doDel = getCurrentPrgGrant().getDodel() && true;
                        this.doCfm = getCurrentPrgGrant().getDocfm() && true;
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
            if (!"DX".equals(r.getProcess().getProcessno())) {
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
                    currentEntity.setDxstatus("N");
                    currentEntity.setDxdelman(null);
                    currentEntity.setCkrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("定型");
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
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!");
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setDxstatus("V");
                    currentEntity.setDxdelman(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCkrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("仓库");
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

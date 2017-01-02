/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesTransactionAdjustBean;
import com.hhsc.entity.SalesTransactionAdjust;
import com.hhsc.lazy.SalesTransactionAdjustModel;
import com.hhsc.web.SuperSingleBean;
import com.lightshell.comm.BaseLib;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesAdjustManagedBean")
@SessionScoped
public class SalesAdjustManagedBean extends SuperSingleBean<SalesTransactionAdjust> {

    @EJB
    private SalesTransactionAdjustBean salesTransactionAdjustBean;

    private String queryCustomerno;
    private String queryItemno;

    public SalesAdjustManagedBean() {
        super(SalesTransactionAdjust.class);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (!super.doBeforeUpdate()) {
            return false;
        }
        if ((currentEntity.getAddamts().compareTo(currentEntity.getAmts()) > 0) || (currentEntity.getOffamts().compareTo(currentEntity.getAmts()) > 0)) {
            showErrorMsg("Error", "调整金额不能大于出货总额");
            return false;
        }
        currentEntity.setAddamt(currentEntity.getAddamts().multiply(currentEntity.getExchange()));
        currentEntity.setOffamt(currentEntity.getOffamts().multiply(currentEntity.getExchange()));
        return true;
    }

    @Override
    public void init() {
        this.superEJB = salesTransactionAdjustBean;
        setModel(new SalesTransactionAdjustModel(salesTransactionAdjustBean));
        this.model.getFilterFields().put("status", "50");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
        super.init();
    }

    @Override
    public void print() throws Exception {
        if (getCurrentSysprg() != null && getCurrentSysprg().getDoprt()) {
            HashMap<String, Object> reportParams = new HashMap<>();
            reportParams.put("JNDIName", this.getCurrentSysprg().getRptjndi());
            if (!this.model.getFilterFields().isEmpty()) {
                reportParams.put("filterFields", BaseLib.convertMapToStringWithClass(this.model.getFilterFields()));
            } else {
                reportParams.put("filterFields", "");
            }
            if (!this.model.getSortFields().isEmpty()) {
                reportParams.put("sortFields", BaseLib.convertMapToString(this.model.getSortFields()));
            } else {
                reportParams.put("sortFields", "");
            }
            //设置报表名称
            String reportFormat;
            if (this.getCurrentSysprg().getRptformat() != null) {
                reportFormat = this.getCurrentSysprg().getRptformat();
            } else {
                reportFormat = reportOutputFormat;
            }
            this.fileName = this.getCurrentSysprg().getApi() + BaseLib.formatDate("yyyyMMddHHss", this.getDate()) + "." + reportFormat;
            String reportName = reportPath + this.getCurrentSysprg().getRptdesign();
            String outputName = reportOutputPath + this.fileName;
            this.reportViewPath = reportViewContext + this.fileName;
            try {
                if (this.getCurrentSysprg() != null && this.getCurrentSysprg().getRptclazz() != null) {
                    reportClassLoader = Class.forName(this.getCurrentSysprg().getRptclazz()).getClassLoader();
                }
                //初始配置
                this.reportInitAndConfig();
                //生成报表
                this.reportRunAndOutput(reportName, reportParams, outputName, reportFormat, null);
                //预览报表
                this.preview();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryItemno != null && !"".equals(this.queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (this.getQueryCustomerno() != null && !"".equals(this.queryCustomerno)) {
                this.model.getFilterFields().put("customer.customerno", getQueryCustomerno());
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (this.queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (this.queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (this.queryState != null && !"ALL".equals(this.queryState) && !"ADD".equals(this.queryState) && !"OFF".equals(this.queryState)) {
                this.model.getFilterFields().put("status", queryState);
            } else if ("ADD".equals(this.queryState)) {
                this.model.getFilterFields().put("addamts >=", 0.01);
            } else if ("OFF".equals(this.queryState)) {
                this.model.getFilterFields().put("offamts >=", 0.01);
            }
            if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
                if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                    this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
                }
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "50");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentnotinvoice") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "50":
                    this.doEdit = getCurrentSysprg().getDoedit() && true;
                    this.doDel = getCurrentSysprg().getDodel() && false;
                    this.doCfm = getCurrentSysprg().getDocfm() && false;
                    this.doUnCfm = getCurrentSysprg().getDouncfm() && false;
                    break;
                default:
                    this.doEdit = getCurrentSysprg().getDoedit() && false;
                    this.doDel = getCurrentSysprg().getDodel() && false;
                    this.doCfm = getCurrentSysprg().getDocfm() && false;
                    this.doUnCfm = getCurrentSysprg().getDouncfm() && false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void update() {
        if (currentEntity != null) {
            try {
                if (doBeforeUpdate()) {
                    getSuperEJB().update(currentEntity);
                    doAfterUpdate();
                    showInfoMsg("Info", "更新成功!");
                } else {
                    showWarnMsg("Warn", "更新前检查失败!");
                }
            } catch (Exception e) {
                showErrorMsg("Error", e.toString());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据!");
        }
    }

    /**
     * @return the queryCustomerno
     */
    public String getQueryCustomerno() {
        return queryCustomerno;
    }

    /**
     * @param queryCustomerno the queryCustomerno to set
     */
    public void setQueryCustomerno(String queryCustomerno) {
        this.queryCustomerno = queryCustomerno;
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

}

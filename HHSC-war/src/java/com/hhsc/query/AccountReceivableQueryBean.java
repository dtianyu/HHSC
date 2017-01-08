/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.AccountReceivableBean;
import com.hhsc.entity.AccountReceivable;
import com.hhsc.lazy.AccountReceivableModel;
import com.hhsc.web.SuperQueryBean;
import com.lightshell.comm.BaseLib;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "accountReceivableQueryBean")
@ViewScoped
public class AccountReceivableQueryBean extends SuperQueryBean<AccountReceivable> {

    @EJB
    private AccountReceivableBean accountReceivableBean;

    private String queryCustomerno;

    public AccountReceivableQueryBean() {
        super(AccountReceivable.class);
    }

    @Override
    public void init() {
        setSuperEJB(accountReceivableBean);
        setModel(new AccountReceivableModel(accountReceivableBean));
        this.model.getFilterFields().put("status", "V");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentreceivable") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("salerid", userManagedBean.getCurrentUser().getId());
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("formid", this.queryFormId);
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
            if (this.queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
            if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentreceivable") {
                if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                    this.model.getFilterFields().put("salerid", userManagedBean.getCurrentUser().getId());
                }
            }
        }
    }

    public void print(String reportDesignFile) throws Exception {
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
            String reportName = reportPath + reportDesignFile;
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
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "V");
        if (getCurrentSysprg() != null && getCurrentSysprg().getApi() == "shipmentreceivable") {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("salerid", userManagedBean.getCurrentUser().getId());
            }
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

}

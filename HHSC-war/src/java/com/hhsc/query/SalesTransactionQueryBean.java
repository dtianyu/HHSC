/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.SalesTransactionBean;
import com.hhsc.entity.SalesTransaction;
import com.hhsc.lazy.SalesTransactionModel;
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
@ManagedBean(name = "salesTransactionQueryBean")
@ViewScoped
public class SalesTransactionQueryBean extends SuperQueryBean<SalesTransaction> {

    @EJB
    private SalesTransactionBean salesTransactionBean;

    private String queryCustomerno;
    private String queryItemno;

    public SalesTransactionQueryBean() {
        super(SalesTransaction.class);
    }

    @Override
    public void init() {
        this.superEJB = salesTransactionBean;
        setModel(new SalesTransactionModel(salesTransactionBean));
        this.model.getFilterFields().put("status", "50");
        if (getCurrentPrgGrant() != null && getCurrentPrgGrant().getSysprg().getApi().equals("shipmentnotinvoice")) {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
        super.init();
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
            if (this.queryCustomerno != null && !"".equals(this.queryCustomerno)) {
                this.model.getFilterFields().put("customer.customerno", queryCustomerno);
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
            if (getCurrentPrgGrant() != null && getCurrentPrgGrant().getSysprg().getApi().equals("shipmentnotinvoice")) {
                if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                    this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
                }
            }
        }
    }

    public void print(String reportDesignFile) throws Exception {
        if (getCurrentPrgGrant() != null && getCurrentPrgGrant().getDoprt()) {
            HashMap<String, Object> reportParams = new HashMap<>();
            reportParams.put("JNDIName", this.getCurrentPrgGrant().getSysprg().getRptjndi());
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
            if (this.getCurrentPrgGrant().getSysprg().getRptformat() != null) {
                reportFormat = this.getCurrentPrgGrant().getSysprg().getRptformat();
            } else {
                reportFormat = reportOutputFormat;
            }
            this.fileName = this.getCurrentPrgGrant().getSysprg().getApi() + BaseLib.formatDate("yyyyMMddHHss", this.getDate()) + "." + reportFormat;
            String reportName = reportPath + reportDesignFile;
            String outputName = reportOutputPath + this.fileName;
            this.reportViewPath = reportViewContext + this.fileName;
            try {
                if (this.getCurrentPrgGrant() != null && this.getCurrentPrgGrant().getSysprg().getRptclazz() != null) {
                    reportClassLoader = Class.forName(this.getCurrentPrgGrant().getSysprg().getRptclazz()).getClassLoader();
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
        this.model.getFilterFields().put("status", "50");
        if (getCurrentPrgGrant() != null && getCurrentPrgGrant().getSysprg().getApi().equals("shipmentnotinvoice")) {
            if (userManagedBean != null && !userManagedBean.getCurrentUser().getSuperuser()) {
                this.model.getFilterFields().put("systemUser.id", userManagedBean.getCurrentUser().getId());
            }
        }
    }

    /**
     * @return the queryCustomerno
     */
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

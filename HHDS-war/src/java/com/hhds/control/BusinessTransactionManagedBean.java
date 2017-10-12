/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.BusinessTransactionBean;
import com.hhds.ejb.PayKindBean;
import com.hhds.entity.Currency;
import com.hhds.entity.BusinessTransaction;
import com.hhds.entity.PayKind;
import com.hhds.lazy.BusinessTransactionModel;
import com.hhds.web.SuperSingleBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "businessTransactionManagedBean")
@SessionScoped
public class BusinessTransactionManagedBean extends SuperSingleBean<BusinessTransaction> {

    @EJB
    protected PayKindBean payKindBean;
    @EJB
    protected BusinessTransactionBean businessTransactionBean;

    private List<PayKind> payKindList;

    public BusinessTransactionManagedBean() {
        super(BusinessTransaction.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(getDate());
        newEntity.setUsername(this.userManagedBean.getCurrentUser().getUsername());
        newEntity.setCurrency("CNY");
        newEntity.setExchange(BigDecimal.ONE);
        newEntity.setAmts(BigDecimal.ZERO);
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        this.verify();
        return super.doAfterPersist();
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (!super.doBeforePersist()) {
            return false;
        }
        if (this.getCurrentPrgGrant() != null && this.getCurrentPrgGrant().getSysprg().getNoauto()) {
            String formid = superEJB.getFormId(newEntity.getFormdate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "formid");
            this.newEntity.setFormid(formid);
        }
        this.newEntity.setAmt(this.newEntity.getAmts());
        return true;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (!super.doBeforeUpdate()) {
            return false;
        }
        this.currentEntity.setAmt(this.currentEntity.getAmts());
        return true;
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            PayKind entity = (PayKind) event.getObject();
            this.currentEntity.setPayment(entity.getName());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            PayKind entity = (PayKind) event.getObject();
            this.currentEntity.setPayment(entity.getName());
        }
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setExchange(entity.getExchange());
        }
    }

    @Override
    public void init() {
        this.superEJB = businessTransactionBean;
        setModel(new BusinessTransactionModel(businessTransactionBean));
        this.model.getFilterFields().put("status", "N");
        this.model.getSortFields().put("status", "ASC");
        this.model.getSortFields().put("formid", "DESC");
        this.payKindList = payKindBean.findAll();
        super.init();
    }

    @Override
    public void print() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可打印数据");
            return;
        }
        if (currentPrgGrant == null) {
            showErrorMsg("Error", "获取程序信息失败");
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentPrgGrant.getSysprg().getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentPrgGrant.getSysprg().getRptformat() != null) {
            reportFormat = this.currentPrgGrant.getSysprg().getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentPrgGrant.getSysprg().getRptdesign() + ".rptdesign";
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentPrgGrant.getSysprg().getRptclazz()).getClassLoader();
            }
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "N");
    }

    /**
     * @return the payKindList
     */
    public List<PayKind> getPayKindList() {
        return payKindList;
    }

}

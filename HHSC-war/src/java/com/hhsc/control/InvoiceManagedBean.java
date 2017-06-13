/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CurrencyBean;
import com.hhsc.ejb.InvoiceBean;
import com.hhsc.ejb.InvoiceDetailBean;
import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Invoice;
import com.hhsc.entity.InvoiceDetail;
import com.hhsc.entity.ItemColor;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.Unit;
import com.hhsc.lazy.InvoiceModel;
import com.hhsc.web.FormMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "invoiceManagedBean")
@SessionScoped
public class InvoiceManagedBean extends FormMultiBean<Invoice, InvoiceDetail> {

    @EJB
    private CurrencyBean currencyBean;

    @EJB
    private ItemMasterBean itemMasterBean;

    @EJB
    private InvoiceBean invoiceBean;

    @EJB
    private InvoiceDetailBean invoiceDetailBean;

    private ItemMaster itemMaster;
    private Currency currency;

    private Date queryShipDateBegin;
    private Date queryShipDateEnd;

    public InvoiceManagedBean() {
        super(Invoice.class, InvoiceDetail.class);
    }

    @Override
    public void create() {
        super.create();
        if (currency != null) {
            newEntity.setCurrency(currency.getCurrency());
            newEntity.setExchange(currency.getExchange());
        } else {
            newEntity.setCurrency("CNY");
            newEntity.setExchange(BigDecimal.ONE);
        }
        newEntity.setTaxtype("3");
        newEntity.setTaxrate(BigDecimal.ZERO);
        newEntity.setTaxkind("VAT");
        newEntity.setOsa1("(SEC.17)");
        newEntity.setOsa2("OSAKA,JAPAN");
        newEntity.setOsa3("ART.NO.");
        newEntity.setOsa4("CT/TO.1-UP");
        newEntity.setOsa5("MADE IN CHINA");
    }

    @Override
    public void doConfirmDetail() {
        if (currentDetail != null) {
            this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
            Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentDetail.getAmts(), 2);
            this.currentDetail.setExtax(t.getExtax());
            this.currentDetail.setTaxes(t.getTaxes());
        }
        super.doConfirmDetail();
    }

    public void handleDialogReturnCustomerColornoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentDetail.setCustomeritemno(ic.getCustomeritemno());
            this.currentDetail.setCustomercolorno(ic.getCustomercolorno());
            this.currentDetail.setColorno(ic.getColorno());
            itemMaster = itemMasterBean.findByItemno(ic.getItemno());
            if (itemMaster != null) {
                this.currentDetail.setItemno(itemMaster.getItemno());
                this.currentDetail.setItemdesc(itemMaster.getItemdesc());
                this.currentDetail.setItemspec(itemMaster.getItemspec());
                this.currentDetail.setItemmake(itemMaster.getItemmake());
                this.currentDetail.setItemwidth(itemMaster.getItemwidth());
                if (itemMaster.getUnit() != null) {
                    this.currentDetail.setUnit(itemMaster.getUnit());
                }
            }
        }
    }

    public void handleDialogReturnColornoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentDetail.setColorno(ic.getColorno());
            this.currentDetail.setCustomercolorno(ic.getCustomercolorno());
            itemMaster = itemMasterBean.findByItemno(ic.getItemno());
            if (itemMaster != null) {
                this.currentDetail.setItemno(itemMaster.getItemno());
                this.currentDetail.setItemdesc(itemMaster.getItemdesc());
                this.currentDetail.setItemspec(itemMaster.getItemspec());
                this.currentDetail.setItemmake(itemMaster.getItemmake());
                this.currentDetail.setItemwidth(itemMaster.getItemwidth());
                if (itemMaster.getUnit() != null) {
                    this.currentDetail.setUnit(itemMaster.getUnit());
                }
            }
        }
    }

    public void handleDialogReturnUnitWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            this.currentDetail.setUnit(((Unit) event.getObject()).getEnunit());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setItemno(entity.getItemno());
            this.currentDetail.setItemdesc(entity.getItemdesc());
            this.currentDetail.setItemspec(entity.getItemspec());
            this.currentDetail.setItemmake(entity.getItemmake());
            this.currentDetail.setItemwidth(entity.getItemwidth());
            if (entity.getUnit() != null) {
                this.currentDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void init() {
        superEJB = invoiceBean;
        detailEJB = invoiceDetailBean;
        model = new InvoiceModel(invoiceBean);
        model.getFilterFields().put("status", "N");
        super.init();
        currency = currencyBean.findByCurrency("USD");
    }

    public void print(String reportDesignFile) throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据");
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
        String reportName = reportPath + reportDesignFile;
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
                this.model.getFilterFields().put("customer", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (getQueryShipDateBegin() != null) {
                this.model.getFilterFields().put("shipdateBegin", getQueryShipDateBegin());
            }
            if (getQueryShipDateEnd() != null) {
                this.model.getFilterFields().put("shipdateEnd", getQueryShipDateEnd());
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

    /**
     * @return the queryShipDateBegin
     */
    public Date getQueryShipDateBegin() {
        return queryShipDateBegin;
    }

    /**
     * @param queryShipDateBegin the queryShipDateBegin to set
     */
    public void setQueryShipDateBegin(Date queryShipDateBegin) {
        this.queryShipDateBegin = queryShipDateBegin;
    }

    /**
     * @return the queryShipDateEnd
     */
    public Date getQueryShipDateEnd() {
        return queryShipDateEnd;
    }

    /**
     * @param queryShipDateEnd the queryShipDateEnd to set
     */
    public void setQueryShipDateEnd(Date queryShipDateEnd) {
        this.queryShipDateEnd = queryShipDateEnd;
    }

}

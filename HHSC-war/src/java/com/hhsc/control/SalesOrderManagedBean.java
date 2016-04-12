/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerItemBean;
import com.hhsc.ejb.DepartmentBean;
import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.ejb.SalesOrderDetailBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.Department;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.SalesOrderModel;
import com.hhsc.rpt.SalesOrderReport;
import com.hhsc.web.SuperMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderManagedBean")
@SessionScoped
public class SalesOrderManagedBean extends SuperMultiBean<SalesOrder, SalesOrderDetail> {

    @EJB
    private DepartmentBean departmentBean;
    @EJB
    private CustomerItemBean customerItemBean;

    @EJB
    private SalesOrderBean salesOrderBean;
    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;
    @EJB
    private SystemUserBean systemUserBean;

    private List<SystemUser> systemUserList;
    protected List<Department> deptList;

    protected String queryCustomerno;
    protected String queryItemno;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public SalesOrderManagedBean() {
        super(SalesOrder.class, SalesOrderDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setSalesman(this.userManagedBean.getCurrentUser());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        this.newEntity.setCurrency("RMB");
        this.newEntity.setExchange(BigDecimal.ONE);
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setDesignsets(0);
        this.newEntity.setDesignprice(BigDecimal.ZERO);
        this.newEntity.setTotaldesign(BigDecimal.ZERO);
        this.newEntity.setTotalextax(BigDecimal.ZERO);
        this.newEntity.setTotaltaxes(BigDecimal.ZERO);
        this.newEntity.setTotalamts(BigDecimal.ZERO);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setQuotedprice(BigDecimal.ZERO);
        this.newDetail.setDiscount(BigDecimal.valueOf(100));
        this.newDetail.setAmts(BigDecimal.ZERO);
        this.newDetail.setExtax(BigDecimal.ZERO);
        this.newDetail.setTaxes(BigDecimal.ZERO);
        this.newDetail.setDeliverydate(this.getDate());
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            String formid = "";
            if (this.getCurrentSysprg().getNoauto()) {
                formid = this.superEJB.getFormId(newEntity.getFormdate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen());
            }
            this.newEntity.setFormid(formid);
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (SalesOrderDetail detail : this.addedDetailList) {
                    detail.setPformid(formid);
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (SalesOrderDetail detail : this.editedDetailList) {
                    detail.setPformid(formid);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (SalesOrderDetail detail : this.addedDetailList) {
                    detail.setPformid(this.currentEntity.getFormid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (SalesOrderDetail detail : this.editedDetailList) {
                    detail.setPformid(this.currentEntity.getFormid());
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        //需要加入还原条件
        return super.doBeforeUnverify();
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "没有订单明细!"));
            return false;
        }
        return super.doBeforeVerify();
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentDetail.getAmts(), 2);
        this.currentDetail.setExtax(t.getExtax());
        this.currentDetail.setTaxes(t.getTaxes());
        super.doConfirmDetail();
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

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
            this.currentEntity.setItemmaster(null);
            this.currentEntity.setCustomeritemno("");
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            this.currentEntity.setPaymentid(entity.getPaymentid());
            this.currentEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setItemmaster(null);
            this.newEntity.setCustomeritemno("");
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            this.newEntity.setPaymentid(entity.getPaymentid());
            this.newEntity.setPayment(entity.getPayment());
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setItemmaster(entity);
            this.currentEntity.setItemno(entity.getItemno());
            this.currentEntity.setItemspec(entity.getItemspec());
            this.currentEntity.setItemimg(entity.getImg1());
            if (currentEntity.getCustomer() != null) {
                CustomerItem o = customerItemBean.findByItemnoAndCustomerno(entity.getItemno(), currentEntity.getCustomer().getCustomerno());
                if (o != null) {
                    this.currentEntity.setCustomeritemno(o.getCustomeritemno());
                } else {
                    this.currentEntity.setCustomeritemno("");
                }
            }
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setItemmaster(entity);
            this.newEntity.setItemno(entity.getItemno());
            this.newEntity.setItemspec(entity.getItemspec());
            this.newEntity.setItemimg(entity.getImg1());
            if (newEntity.getCustomer() != null) {
                CustomerItem o = customerItemBean.findByItemnoAndCustomerno(entity.getItemno(), newEntity.getCustomer().getCustomerno());
                if (o != null) {
                    this.newEntity.setCustomeritemno(o.getCustomeritemno());
                } else {
                    this.newEntity.setCustomeritemno("");
                }
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setItemmaster(entity);
            this.currentDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.currentDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newDetail.setItemmaster(entity);
            this.newDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.newDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setItemimg(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setItemimg(fileName);
        }
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderBean);
        setDetailEJB(salesOrderDetailBean);
        setModel(new SalesOrderModel(salesOrderBean, userManagedBean));
        getModel().getFilterFields().put("status", "N");
        setSystemUserList(systemUserBean.findAll());
        setDeptList(departmentBean.findAll());
        super.init();
    }

    @Override
    public void print() throws Exception {

        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据!"));
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("JNDIName", this.currentSysprg.getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentSysprg.getRptformat() != null) {
            reportFormat = this.currentSysprg.getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentSysprg.getRptdesign();
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
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
    protected void reportInitAndConfig() {
        super.reportInitAndConfig();
        reportEngineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, SalesOrderReport.class.getClassLoader());
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

    /**
     * @return the systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * @param systemUserList the systemUserList to set
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
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

    /**
     * @return the deptList
     */
    public List<Department> getDeptList() {
        return deptList;
    }

    /**
     * @param deptList the deptList to set
     */
    public void setDeptList(List<Department> deptList) {
        this.deptList = deptList;
    }

}

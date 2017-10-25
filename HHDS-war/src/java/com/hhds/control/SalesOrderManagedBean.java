/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.SalesOrderBean;
import com.hhds.ejb.SalesOrderDetailBean;
import com.hhds.ejb.SalesTypeBean;
import com.hhds.ejb.VendorItemBean;
import com.hhds.entity.Currency;
import com.hhds.entity.Customer;
import com.hhds.entity.SalesOrder;
import com.hhds.entity.SalesOrderDetail;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.SalesType;
import com.hhsc.entity.SystemUser;
import com.hhds.entity.Unit;
import com.hhds.entity.VendorItem;
import com.hhds.lazy.SalesOrderModel;
import com.hhds.web.FormMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderManagedBean")
@SessionScoped
public class SalesOrderManagedBean extends FormMultiBean<SalesOrder, SalesOrderDetail> {

    @EJB
    protected SalesTypeBean salesTypeBean;

    @EJB
    protected SalesOrderBean salesOrderBean;

    @EJB
    protected SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    private VendorItemBean vendorItemBean;

    protected List<SalesType> salesTypeList;

    protected Boolean doTransfer;

    protected String queryCustomerno;
    protected String queryItemno;
    protected Date queryDeliveryDateBegin;
    protected Date queryDeliveryDateEnd;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public SalesOrderManagedBean() {
        super(SalesOrder.class, SalesOrderDetail.class);
    }

    public String copyEntity(String path) {
        if (this.currentEntity != null && this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
            //获得原来的明细
            salesOrderBean.setDetail(currentEntity.getFormid());
            if (salesOrderBean.getDetailList().isEmpty()) {
                showWarnMsg("Warn", "没有明细无法复制");
                return "";
            }
            //清空明细新增列表
            if (!this.addedDetailList.isEmpty()) {
                this.addedDetailList.clear();
            }
            try {
                String formid = salesOrderBean.getFormId(getDate(), currentPrgGrant.getSysprg().getNolead(), currentPrgGrant.getSysprg().getNoformat(), currentPrgGrant.getSysprg().getNoseqlen());
                if (!formid.equals("")) {
                    //设定主表
                    SalesOrder entity = (SalesOrder) BeanUtils.cloneBean(currentEntity);
                    entity.setId(null);
                    entity.setFormid(formid);
                    entity.setFormdate(getDate());
                    entity.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    entity.setCredate(getDate());
                    entity.setStatus("N");
                    //设定明细
                    for (SalesOrderDetail detail : salesOrderBean.getDetailList()) {
                        SalesOrderDetail d = (SalesOrderDetail) BeanUtils.cloneBean(detail);
                        d.setId(null);
                        d.setPid(formid);
                        d.setProqty(BigDecimal.ZERO);
                        d.setInqty(BigDecimal.ZERO);
                        d.setShipqty(BigDecimal.ZERO);
                        d.setRelapi("");
                        d.setRelformid("");
                        d.setRelseq(-1);
                        d.setStatus("00");
                        this.addedDetailList.add(d);
                    }
                    //保存资料
                    salesOrderBean.persist(entity, detailAdded, null, null);
                    //清空明细新增列表
                    this.addedDetailList.clear();
                    return path;
                }
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
                showErrorMsg("Error", ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setSalesman(this.userManagedBean.getCurrentUser().getUsername());
        this.newEntity.setCurrency("CNY");
        this.newEntity.setExchange(BigDecimal.ONE);
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setTotalextax(BigDecimal.ZERO);
        this.newEntity.setTotaltaxes(BigDecimal.ZERO);
        this.newEntity.setTotalamts(BigDecimal.ZERO);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setDiscount(BigDecimal.valueOf(100));
        this.newDetail.setAmts(BigDecimal.ZERO);
        this.newDetail.setExtax(BigDecimal.ZERO);
        this.newDetail.setTaxes(BigDecimal.ZERO);
        this.newDetail.setDeliverydate(this.getDate());
        this.newDetail.setStatus("00");
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (super.doBeforeUpdate()) {
            for (SalesOrderDetail sod : detailList) {
                currentDetail = sod;
                currentDetail.setFirstdelivery(currentEntity.getFirstdelivery());
                currentDetail.setFirsttime(currentEntity.getFirsttime());
                currentDetail.setLastdelivery(currentEntity.getLastdelivery());
                currentDetail.setLasttime(currentEntity.getLasttime());
                doConfirmDetail();
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可更新数据");
            return false;
        }
        SalesOrder e = salesOrderBean.findById(currentEntity.getId());
        if (!"V".equals(e.getStatus())) {
            showErrorMsg("Warn", "状态已变更");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (super.doBeforeVerify()) {
            for (SalesOrderDetail d : detailList) {
                if (!salesOrderBean.hasInventory(d)) {
                    showErrorMsg("Error", d.getItemno() + "库存数量不足");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentDetail.getAmts(), 2);
        this.currentDetail.setExtax(t.getExtax());
        this.currentDetail.setTaxes(t.getTaxes());
        this.currentDetail.setFirstdelivery(currentEntity.getFirstdelivery());
        this.currentDetail.setFirsttime(currentEntity.getFirsttime());
        this.currentDetail.setLastdelivery(currentEntity.getLastdelivery());
        this.currentDetail.setLasttime(currentEntity.getLasttime());
        VendorItem vi = vendorItemBean.findFirstByItemno(this.currentDetail.getItemno());
        if (vi != null) {
            this.currentDetail.setBatch(vi.getVendordesignno());
            this.currentDetail.setColorno(vi.getVendoritemcolor());
        }
        super.doConfirmDetail();
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            this.currentEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            this.newEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnSalesmanWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            SystemUser u = (SystemUser) event.getObject();
            currentEntity.setSalesman(u.getUsername());
        }
    }

    public void handleDialogReturnSalesmanWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            SystemUser u = (SystemUser) event.getObject();
            newEntity.setSalesman(u.getUsername());
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
        }
    }

    public void handleDialogReturnUnitWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            this.currentDetail.setUnit(((Unit) event.getObject()).getUnit());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setItemMaster(entity);
            this.currentDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.currentDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        if (event.getObject() != null && this.newDetail != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newDetail.setItemMaster(entity);
            this.newDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.newDetail.setUnit(entity.getUnit());
            }
        }
    }

    public void handleTaxtypeChangedWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Object item = event.getObject();
            if (item.equals("2") || item.equals("3")) {
                this.currentEntity.setTaxrate(BigDecimal.ZERO);
            }
        }
    }

    public void handleTaxtypeChangedWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Object item = event.getObject();
            if (item.equals("2") || item.equals("3")) {
                this.newEntity.setTaxrate(BigDecimal.ZERO);
            }
        }
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderBean);
        setDetailEJB(salesOrderDetailBean);
        setModel(new SalesOrderModel(salesOrderBean));
        model.getFilterFields().put("status", "N");
        salesTypeList = salesTypeBean.findAll();
        super.init();
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
            if (queryCustomerno != null && !"".equals(queryCustomerno)) {
                this.model.getFilterFields().put("customer.customerno", queryCustomerno);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryDeliveryDateBegin != null) {
                this.model.getFilterFields().put("firstdeliveryBegin", queryDeliveryDateBegin);
            }
            if (queryDeliveryDateEnd != null) {
                this.model.getFilterFields().put("firstdeliveryEnd", queryDeliveryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        model.getFilterFields().put("status", "N");
    }

    @Override
    protected void setToolBar() {
        super.setToolBar();
        this.doUnCfm = false;
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
     * @return the doTransfer
     */
    public Boolean getDoTransfer() {
        return doTransfer;
    }

    /**
     * @param doTransfer the doTransfer to set
     */
    public void setDoTransfer(Boolean doTransfer) {
        this.doTransfer = doTransfer;
    }

    /**
     * @return the salesTypeList
     */
    public List<SalesType> getSalesTypeList() {
        return salesTypeList;
    }

    /**
     * @param salesTypeList the salesTypeList to set
     */
    public void setSalesTypeList(List<SalesType> salesTypeList) {
        this.salesTypeList = salesTypeList;
    }

    /**
     * @return the queryDeliveryDateBegin
     */
    public Date getQueryDeliveryDateBegin() {
        return queryDeliveryDateBegin;
    }

    /**
     * @param queryDeliveryDateBegin the queryDeliveryDateBegin to set
     */
    public void setQueryDeliveryDateBegin(Date queryDeliveryDateBegin) {
        this.queryDeliveryDateBegin = queryDeliveryDateBegin;
    }

    /**
     * @return the queryDeliveryDateEnd
     */
    public Date getQueryDeliveryDateEnd() {
        return queryDeliveryDateEnd;
    }

    /**
     * @param queryDeliveryDateEnd the queryDeliveryDateEnd to set
     */
    public void setQueryDeliveryDateEnd(Date queryDeliveryDateEnd) {
        this.queryDeliveryDateEnd = queryDeliveryDateEnd;
    }

}

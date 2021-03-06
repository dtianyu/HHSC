/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.DepartmentBean;
import com.hhsc.ejb.PurchaseOrderBean;
import com.hhsc.ejb.PurchaseOrderDetailBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.ejb.VendorItemBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.PurchaseOrder;
import com.hhsc.entity.PurchaseOrderDetail;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Unit;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.VendorItem;
import com.hhsc.lazy.PurchaseOrderModel;
import com.hhsc.web.FormMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
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
@ManagedBean(name = "purchaseOrderManagedBean")
@SessionScoped
public class PurchaseOrderManagedBean extends FormMultiBean<PurchaseOrder, PurchaseOrderDetail> {

    @EJB
    protected DepartmentBean departmentBean;
    @EJB
    protected SystemUserBean systemUserBean;
    @EJB
    protected VendorItemBean vendorItemBean;

    @EJB
    private PurchaseOrderBean purchaseOrderBean;
    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;

    private List<SystemUser> systemUserList;
    private List<Department> deptList;

    private String queryItemno;

    public PurchaseOrderManagedBean() {
        super(PurchaseOrder.class, PurchaseOrderDetail.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(getDate());
        newEntity.setBuyer(this.userManagedBean.getCurrentUser());
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        newEntity.setCurrency("CNY");
        newEntity.setExchange(BigDecimal.ONE);
        newEntity.setTaxtype("0");
        newEntity.setTaxkind("VAT16");
        newEntity.setTaxrate(BigDecimal.valueOf(16));
        newEntity.setDesignsets(0);
        newEntity.setDesignprice(BigDecimal.ZERO);
        newEntity.setTotaldesign(BigDecimal.ZERO);
        newEntity.setTotalextax(BigDecimal.ZERO);
        newEntity.setTotaltaxes(BigDecimal.ZERO);
        newEntity.setTotalamts(BigDecimal.ZERO);
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        newDetail.setQty(BigDecimal.ZERO);
        newDetail.setPrice(BigDecimal.ZERO);
        newDetail.setAmts(BigDecimal.ZERO);
        newDetail.setExtax(BigDecimal.ZERO);
        newDetail.setTaxes(BigDecimal.ZERO);
        newDetail.setDeliverydate(this.getDate());
        newDetail.setInqty(BigDecimal.ZERO);
        newDetail.setStatus("00");
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.getNewEntity() != null && this.getNewEntity().getVendor() == null) {
            showErrorMsg("Error", "请输入供应商");
            return false;
        }
        if (this.detailList != null && !this.detailList.isEmpty()) {
            for (PurchaseOrderDetail detail : detailList) {
                if (detail.getItemmaster() == null) {
                    showErrorMsg("Error", "请输入品号");
                    return false;
                }
                if ((detail.getQty().compareTo(BigDecimal.ZERO) < 1) || (detail.getPrice().compareTo(BigDecimal.ZERO) < 1)) {
                    showErrorMsg("Error", "请输入数量或单价");
                    return false;
                }
            }
        }
        return super.doBeforePersist();
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }//超类中有重新加载明细资料
        for (PurchaseOrderDetail detail : this.detailList) {
            if ((detail.getInqty().compareTo(BigDecimal.ZERO) == 1) || (!detail.getStatus().equals("10"))) {
                showWarnMsg("Warn", "已入库不可还原");
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }//超类中有重新加载明细资料
        if (this.detailList == null || this.detailList.isEmpty()) {
            showErrorMsg("Error", "没有采购明细!");
            return false;
        }
        for (PurchaseOrderDetail detail : this.detailList) {
            if ((detail.getQty().compareTo(BigDecimal.ZERO) < 1) || (detail.getPrice().compareTo(BigDecimal.ZERO) < 1)) {
                showErrorMsg("Error", "数量或单价不能为零!");
                return false;
            }
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentDetail.getAmts(), 2);
        this.currentDetail.setExtax(t.getExtax());
        this.currentDetail.setTaxes(t.getTaxes());
        super.doConfirmDetail();
    }

    public void findVendorItem() {
        if (currentEntity.getItemno() != null && currentEntity.getVendor() != null) {
            VendorItem entity = vendorItemBean.findByItemnoAndVendorno(currentEntity.getItemno(), currentEntity.getVendor().getVendorno());
            if (entity != null) {
                this.currentEntity.setVendoritemno(entity.getVendoritemno());
            }
        }
    }

    public void findVendorItemForDetail() {
        if (currentDetail.getItemno() != null && currentEntity.getVendor() != null) {
            VendorItem entity = vendorItemBean.findByItemnoAndVendorno(currentDetail.getItemno(), currentEntity.getVendor().getVendorno());
            if (entity != null) {
                this.currentDetail.setVendoritemno(entity.getVendoritemno());
            }
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentEntity.setVendor(entity);
            this.currentEntity.setItemmaster(null);
            this.currentEntity.setVendoritemno("");
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            this.currentEntity.setPaymentid(entity.getPaymentid());
            this.currentEntity.setPayment(entity.getPayment());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.newEntity.setVendor(entity);
            this.newEntity.setItemmaster(null);
            this.newEntity.setVendoritemno("");
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            this.newEntity.setPaymentid(entity.getPaymentid());
            this.newEntity.setPayment(entity.getPayment());
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

    public void handleDialogReturnItemMasterWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setItemmaster(entity);
            this.currentEntity.setItemno(entity.getItemno());
            this.currentEntity.setItemspec(entity.getItemspec());
            this.currentEntity.setItemimg(entity.getImg1());
        }
    }

    public void handleDialogReturnItemMasterWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setItemmaster(entity);
            this.newEntity.setItemno(entity.getItemno());
            this.newEntity.setItemspec(entity.getItemspec());
            this.newEntity.setItemimg(entity.getImg1());
        }
    }

    public void handleDialogReturnUnitWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            Unit entity = (Unit) event.getObject();
            this.currentDetail.setUnit(entity.getUnit());
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
    public void init() {
        this.superEJB = purchaseOrderBean;
        this.detailEJB = purchaseOrderDetailBean;
        setModel(new PurchaseOrderModel(purchaseOrderBean));
        this.model.getFilterFields().put("status", "N");
        setSystemUserList(systemUserBean.findAll());
        setDeptList(departmentBean.findAll());
        super.init();
    }

    @Override
    public void print() throws Exception {

        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可打印数据!");
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
        String reportName = reportPath + this.currentPrgGrant.getSysprg().getRptdesign() + currentEntity.getPurtype() + ".rptdesign";
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

    public void print(String reportDesignFile) throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可打印数据");
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
        String reportName = reportPath + reportDesignFile + currentEntity.getPurtype() + ".rptdesign";;
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
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("vendor.vendor", queryName);
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

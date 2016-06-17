/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerItemBean;
import com.hhsc.ejb.DepartmentBean;
import com.hhsc.ejb.PurchaseRequestBean;
import com.hhsc.ejb.PurchaseRequestDetailBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.ejb.VendorItemBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.PurchaseRequest;
import com.hhsc.entity.PurchaseRequestDetail;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.VendorItem;
import com.hhsc.lazy.ItemMasterRequestModel;
import com.hhsc.web.FormMultiBean;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class PurchaseRequestManagedBean extends FormMultiBean<PurchaseRequest, PurchaseRequestDetail> {

    @EJB
    protected CustomerItemBean customerItemBean;

    @EJB
    protected VendorItemBean vendorItemBean;

    @EJB
    protected DepartmentBean departmentBean;
    @EJB
    protected SystemUserBean systemUserBean;
    @EJB
    protected PurchaseRequestBean purchaseRequestBean;
    @EJB
    protected PurchaseRequestDetailBean purchaseRequestDetailBean;

    protected String queryItemno;

    public PurchaseRequestManagedBean() {
        super(PurchaseRequest.class, PurchaseRequestDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setSystemuser(this.userManagedBean.getCurrentUser());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        this.setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setAbroad(false);
        this.newDetail.setQty(BigDecimal.ZERO);
        this.newDetail.setPurqty(BigDecimal.ZERO);
        this.newDetail.setPrice(BigDecimal.ZERO);
        this.newDetail.setAmts(BigDecimal.ZERO);
        this.newDetail.setCurrency("CNY");
        this.newDetail.setExchange(BigDecimal.ONE);
        this.newDetail.setTaxtype("0");
        this.newDetail.setTaxkind("VAT17");
        this.newDetail.setTaxrate(BigDecimal.valueOf(17));
        this.newDetail.setExtax(BigDecimal.ZERO);
        this.newDetail.setTaxes(BigDecimal.ZERO);
        this.newDetail.setRequestdate(getDate());
        this.newDetail.setDeliverydate(getDate());
        this.newDetail.setStatus("N");
        this.newDetail.setCreator(this.userManagedBean.getCurrentUser().getUserid());
        this.newDetail.setCfmdate(getDate());
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        boolean flag = true;
        for (PurchaseRequestDetail detail : detailList) {
            if (detail.getStatus().equals("V")) {
                flag = false;
            }
        }
        if (!flag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "已转采购不能还原!"));
            return false;
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "没有请购明细!"));
            return false;
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        super.doConfirmDetail();
    }

    public void findCustomerItem() {
        if (currentDetail.getDesignno() != null && currentDetail.getCustomer() != null) {
            CustomerItem entity = customerItemBean.findByItemnoAndCustomerno(currentDetail.getDesignno(), currentDetail.getCustomer().getCustomerno());
            if (entity != null) {
                this.currentDetail.setCustomeritemno(entity.getCustomeritemno());
            }
        }
    }

    public void findVendorItem() {
        if (currentDetail.getItemno() != null && currentDetail.getVendor() != null) {
            VendorItem entity = vendorItemBean.findByItemnoAndVendorno(currentDetail.getItemno(), currentDetail.getVendor().getVendorno());
            if (entity != null) {
                this.currentDetail.setVendoritemno(entity.getVendoritemno());
            }
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        //返回部门
        if (event.getObject() != null) {
            this.newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        //返回部门
        if (event.getObject() != null) {
            this.currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnBuyerWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setSystemuser((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnBuyerWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setSystemuser((SystemUser) event.getObject());
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

    public void handleDialogReturnItemDesignWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setItemdesign(entity);
            this.currentDetail.setDesignno(entity.getItemno());
            this.currentDetail.setDesignspec(entity.getItemspec());
        }
    }

    public void handleDialogReturnCustomerWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentDetail.setCustomer(entity);
        }
    }

    public void handleDialogReturnVendorWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentDetail.setVendor(entity);
        }
    }

    @Override
    public void init() {
        this.superEJB = purchaseRequestBean;
        this.detailEJB = purchaseRequestDetailBean;
        setModel(new ItemMasterRequestModel(purchaseRequestBean));
        super.init();
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

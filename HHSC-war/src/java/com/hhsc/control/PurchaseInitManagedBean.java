/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.PurchaseOrderBean;
import com.hhsc.ejb.PurchaseDraftBean;
import com.hhsc.entity.PurchaseOrder;
import com.hhsc.entity.PurchaseOrderDetail;
import com.hhsc.entity.PurchaseDraft;
import com.hhsc.entity.Sysprg;
import com.hhsc.entity.Vendor;
import com.hhsc.lazy.PurchaseInitModel;
import com.hhsc.web.SuperSingleBean;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class PurchaseInitManagedBean extends SuperSingleBean<PurchaseDraft> {

    @EJB
    private PurchaseDraftBean purchaseDraftBean;
    @EJB
    private PurchaseOrderBean purchaseBean;

    private Sysprg purchaseorderSysprg;

    private String queryItemno;
    private Vendor queryVendor;

    public PurchaseInitManagedBean() {
        super(PurchaseDraft.class);
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {

        if (getEntityList() == null || entityList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可抛转数据"));
            return false;
        }
        purchaseorderSysprg = sysprgBean.findByAPI("purchaseorder");
        if (purchaseorderSysprg == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "采购作业设定错误"));
            return false;
        }
        if (!purchaseorderSysprg.getNoauto()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "采购单不是自动编号"));
            return false;
        }
        PurchaseDraft d = entityList.get(0);
        for (PurchaseDraft entity : entityList) {
            if (!entity.getVendor().equals(queryVendor)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "供应商信息不一致"));
                return false;
            }
            if ("100".equals(queryState)) {
                if (!d.getDesignno().equals(entity.getDesignno())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "相同成品才能产生一笔采购"));
                    return false;
                }
            }
        }
        if (this.userManagedBean.getCurrentUser().getDept() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "采购员没有设置部门"));
            return false;
        }
        return super.doBeforeVerify();

    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.queryVendor = entity;
        }
    }

    @Override
    public void init() {
        if (entityList == null) {
            entityList = new ArrayList<>();
        }
        if (queryVendor == null) {
            queryVendor = new Vendor();
            queryVendor.setId(-1);
            queryVendor.setVendor("请选择供应商");
        }
        this.superEJB = purchaseDraftBean;
        setModel(new PurchaseInitModel(purchaseDraftBean));
        this.model.getFilterFields().put("vendor.id", -1);
        this.model.getFilterFields().put("itemno", "");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryVendor != null) {
                this.model.getFilterFields().put("vendor.id", queryVendor.getId());
            }
            if (queryState != null && !"".equals(queryState)) {
                this.model.getFilterFields().put("purtype", queryState);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("purchaserequest.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("purchaserequest.formdateEnd", queryDateEnd);
            }
            if (getQueryItemno() != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", getQueryItemno());
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemmaster.itemdesc", queryName);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("vendor.id", -1);
        this.model.getFilterFields().put("itemno", "");
    }

    @Override
    public void verify() {
        try {
            if (doBeforeVerify()) {
                String formid = purchaseBean.getFormId(getDate(), purchaseorderSysprg.getNolead(), purchaseorderSysprg.getNoformat(), purchaseorderSysprg.getNoseqlen());
                if (formid == null || "".equals(formid)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "采购单无法自动编号"));
                    return;
                }
                PurchaseOrder p = new PurchaseOrder();
                p.setFormid(formid);
                p.setFormdate(getDate());
                p.setPurtype(queryState);
                p.setVendor(queryVendor);
                p.setDept(this.userManagedBean.getCurrentUser().getDept());
                p.setBuyer(this.userManagedBean.getCurrentUser());
                if ("100".equals(queryState)) {
                    p.setItemmaster(entityList.get(0).getItemdesign());
                    p.setItemno(entityList.get(0).getDesignno());
                    p.setItemspec(entityList.get(0).getDesignspec());
                    p.setItemimg(entityList.get(0).getItemdesign().getImg1());
                    p.setVendoritemno(entityList.get(0).getVendoritemno());
                }
                p.setCurrency(queryVendor.getCurrency());
                p.setExchange(BigDecimal.ONE);//需要替换
                p.setTaxtype(queryVendor.getTaxtype());
                p.setTaxkind(queryVendor.getTaxkind());
                p.setTaxrate(queryVendor.getTaxrate());
                p.setPaymentid(queryVendor.getPaymentid());
                p.setPayment(queryVendor.getPayment());
                p.setDesignsets(0);
                p.setDesignprice(BigDecimal.ZERO);
                p.setTotaldesign(BigDecimal.ZERO);
                p.setTotalextax(BigDecimal.ZERO);
                p.setTotaltaxes(BigDecimal.ZERO);
                p.setTotalamts(BigDecimal.ZERO);
                p.setStatus("N");
                p.setCreator(this.userManagedBean.getCurrentUser().getUserid());
                p.setCredateToNow();
                
                List<PurchaseOrderDetail> purchaseList = new ArrayList<>();
                int seq = 1;
                for (PurchaseDraft entity : entityList) {
                    PurchaseOrderDetail d = new PurchaseOrderDetail();
                    d.setSeq(seq);
                    d.setItemmaster(entity.getItemmaster());
                    d.setItemno(entity.getItemno());
                    d.setColorno(entity.getColorno());
                    if (entity.getCustomer() != null) {
                        d.setCustomerid(entity.getCustomer().getId());
                    }
                    d.setCustomeritemno(entity.getCustomeritemno());
                    d.setCustomercolorno(entity.getCustomercolorno());
                    d.setVendoritemno(entity.getVendoritemno());
                    d.setVendorcolorno(entity.getVendorcolorno());
                    d.setQty(entity.getPurqty());
                    d.setPrice(entity.getPrice());
                    d.setUnit(entity.getUnit());
                    d.setAmts(entity.getAmts());
                    d.setExtax(entity.getExtax());
                    d.setTaxes(entity.getTaxes());
                    d.setDeliverydate(entity.getDeliverydate());
                    d.setDeliverytime(entity.getDeliverytime());
                    d.setDeliveryadd(entity.getDeliveryadd());
                    d.setRemark(entity.getRemark());
                    d.setStatus("00");
                    d.setSrcapi(this.currentSysprg.getApi());
                    d.setSrcformid(entity.getPformid());
                    d.setSrcseq(entity.getSeq());
                    purchaseList.add(d);
                    entity.setStatus("V");
                    seq++;
                }
                
                purchaseBean.persist(p);
                purchaseBean.initPurchase(p, purchaseList);
                for (PurchaseDraft entity : entityList) {
                    superEJB.verify(entity);
                }
                doAfterVerify();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
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

    /**
     * @return the queryVendor
     */
    public Vendor getQueryVendor() {
        return queryVendor;
    }

    /**
     * @param queryVendor the queryVendor to set
     */
    public void setQueryVendor(Vendor queryVendor) {
        this.queryVendor = queryVendor;
    }

}

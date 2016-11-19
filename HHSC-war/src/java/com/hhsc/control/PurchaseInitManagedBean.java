/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CurrencyBean;
import com.hhsc.ejb.PurchaseOrderBean;
import com.hhsc.ejb.PurchaseDraftBean;
import com.hhsc.entity.Currency;
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
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class PurchaseInitManagedBean extends SuperSingleBean<PurchaseDraft> {

    @EJB
    private CurrencyBean currencyBean;

    @EJB
    private PurchaseDraftBean purchaseDraftBean;
    @EJB
    private PurchaseOrderBean purchaseOrderBean;

    private Sysprg purchaseorderSysprg;

    private String queryItemno;
    private Vendor queryVendor;
    private Boolean queryMerga;

    public PurchaseInitManagedBean() {
        super(PurchaseDraft.class);
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {

        if (getEntityList() == null || entityList.isEmpty()) {
            showWarnMsg("Warn", "没有可抛转数据");
            return false;
        }
        purchaseorderSysprg = sysprgBean.findByAPI("purchaseorder");
        if (purchaseorderSysprg == null) {
            showErrorMsg("Error", "采购作业设定错误");
            return false;
        }
        if (!purchaseorderSysprg.getNoauto()) {
            showErrorMsg("Error", "采购单不是自动编号");
            return false;
        }
        PurchaseDraft d = entityList.get(0);
        for (PurchaseDraft entity : entityList) {
            if (!entity.getVendor().equals(queryVendor)) {
                showErrorMsg("Error", "供应商信息不一致");
                return false;
            }
            if ("100".equals(queryState)) {
                if (!d.getDesignno().equals(entity.getDesignno())) {
                    showErrorMsg("Error", "相同成品才能产生一笔采购");
                    return false;
                }
            }
        }
        if (this.userManagedBean.getCurrentUser().getDept() == null) {
            showErrorMsg("Error", "采购员没有设置部门");
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
        Currency c;
        PurchaseDraft detail;
        List<PurchaseDraft> draftList = new ArrayList();
        try {
            if (doBeforeVerify()) {
                String formid = purchaseOrderBean.getFormId(getDate(), purchaseorderSysprg.getNolead(), purchaseorderSysprg.getNoformat(), purchaseorderSysprg.getNoseqlen());
                if (formid == null || "".equals(formid)) {
                    showErrorMsg("Error", "采购单无法自动编号");
                    return;
                }
                for (PurchaseDraft entity : entityList) {
                    PurchaseDraft pd = (PurchaseDraft) BeanUtils.cloneBean(entity);
                    draftList.add(pd);
                    entity.setStatus("V");
                    entity.setRelapi("purchaseorder");
                    entity.setRelformid(formid);
                    entity.setRelseq(0);
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
                    p.setShipmarks(entityList.get(0).getShipmarks());
                    p.setTestremark(entityList.get(0).getTestremark());
                    p.setProductremark(entityList.get(0).getProductremark());
                    p.setPackremark(entityList.get(0).getPackremark());
                } else {
                    if (draftList.size() > 1 && queryMerga) {
                        for (int i = 0; i < draftList.size() - 1; i++) {
                            for (int j = i + 1; j < draftList.size(); j++) {
                                PurchaseDraft e1 = draftList.get(i);
                                PurchaseDraft e2 = draftList.get(j);
                                if ((e1.getItemno().equals(e2.getItemno()))
                                        && (e1.getTaxtype().equals(e2.getTaxtype())) && (e1.getPrice().compareTo(e2.getPrice()) == 0)) {
                                    e1.setQty(e1.getQty().add(e2.getQty()));
                                    e1.setPurqty(e1.getPurqty().add(e2.getPurqty()));
                                    e1.setAmts(e1.getAmts().add(e2.getAmts()));
                                    e1.setExtax(e1.getExtax().add(e2.getExtax()));
                                    e1.setTaxes(e1.getTaxes().add(e2.getTaxes()));
                                    draftList.remove(j);
                                    j--;
                                }
                            }
                        }
                    }
                }
                p.setCurrency(queryVendor.getCurrency());
                c = currencyBean.findByCurrency(queryVendor.getCurrency());
                if (c != null) {
                    p.setExchange(c.getExchange());
                } else {
                    p.setExchange(BigDecimal.ONE);
                }
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
                int i = -1;
                for (PurchaseDraft pd : draftList) {
                    PurchaseOrderDetail d = new PurchaseOrderDetail();
                    d.setSeq(seq);
                    d.setItemmaster(pd.getItemmaster());
                    d.setItemno(pd.getItemno());
                    d.setColorno(pd.getColorno());
                    if (pd.getCustomer() != null) {
                        d.setCustomerid(pd.getCustomer().getId());
                    }
                    d.setCustomeritemno(pd.getCustomeritemno());
                    d.setCustomercolorno(pd.getCustomercolorno());
                    d.setVendoritemno(pd.getVendoritemno());
                    d.setVendorcolorno(pd.getVendorcolorno());
                    d.setQty(pd.getPurqty());
                    d.setPrice(pd.getPrice());
                    d.setUnit(pd.getUnit());
                    d.setAmts(pd.getAmts());
                    d.setExtax(pd.getExtax());
                    d.setTaxes(pd.getTaxes());
                    d.setRequestdate(pd.getRequestdate());
                    d.setRequesttime(pd.getRequesttime());
                    d.setDeliverydate(pd.getDeliverydate());
                    d.setDeliverytime(pd.getDeliverytime());
                    d.setDeliveryadd(pd.getDeliveryadd());
                    d.setRemark(pd.getRemark());
                    d.setInqty(BigDecimal.ZERO);
                    d.setStatus("00");
                    d.setSrcapi(this.currentSysprg.getApi());
                    d.setSrcformid(pd.getPurchaserequest().getFormid());
                    d.setSrcseq(pd.getSeq());
                    purchaseList.add(d);
                    //请购明细对应采购明细
                    i = entityList.indexOf(pd);
                    if (i > -1) {
                        detail = entityList.get(i);
                        detail.setRelseq(seq);
                    }
                    seq++;
                }

                purchaseOrderBean.initPurchase(p, purchaseList);
                entityList.forEach((entity) -> {
                    superEJB.verify(entity);
                });
                doAfterVerify();
                showInfoMsg("Info", "更新成功!");
            } else {
                showWarnMsg("Warn", "审核前检查失败!");
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

    /**
     * @return the queryMerga
     */
    public Boolean getQueryMerga() {
        return queryMerga;
    }

    /**
     * @param queryMerga the queryMerga to set
     */
    public void setQueryMerga(Boolean queryMerga) {
        this.queryMerga = queryMerga;
    }

}

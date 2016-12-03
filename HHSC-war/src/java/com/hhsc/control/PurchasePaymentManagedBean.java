/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.PurchasePaymentBean;
import com.hhsc.ejb.PurchasePaymentDetailBean;
import com.hhsc.ejb.PurchaseTransactionBean;
import com.hhsc.entity.PurchasePayment;
import com.hhsc.entity.PurchasePaymentDetail;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.Department;
import com.hhsc.entity.PurchaseTransaction;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.PurchasePaymentModel;
import com.hhsc.web.FormMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "purchasePaymentManagedBean")
@SessionScoped
public class PurchasePaymentManagedBean extends FormMultiBean<PurchasePayment, PurchasePaymentDetail> {

    @EJB
    private PurchaseTransactionBean purchaseTransactionBean;

    @EJB
    private PurchasePaymentBean purchasePaymentBean;

    @EJB
    private PurchasePaymentDetailBean purchasePaymentDetailBean;

    public PurchasePaymentManagedBean() {
        super(PurchasePayment.class, PurchasePaymentDetail.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(this.getDate());
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        newEntity.setSystemUser(this.userManagedBean.getCurrentUser());
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setCurrency("CNY");
        this.newDetail.setExchange(BigDecimal.ONE);
        this.newDetail.setPayamts(BigDecimal.ZERO);
        this.newDetail.setPayamt(BigDecimal.ZERO);
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        PurchaseTransaction pt;
        BigDecimal allowAmts;
        for (PurchasePaymentDetail detail : this.detailList) {
            pt = purchaseTransactionBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
            if (pt == null) {
                showErrorMsg("Error", "找不到验收单" + detail.getSrcformid());
                return false;
            }
            allowAmts = pt.getPuramts().add(pt.getTaxamts()).subtract(pt.getApplyamts());
            if (allowAmts.compareTo(detail.getPayamts()) == -1) {
                showErrorMsg("Error", detail.getSrcformid() + "可付款金额不足");
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        PurchaseTransaction pt;
        for (PurchasePaymentDetail detail : this.detailList) {
            pt = purchaseTransactionBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
            if (pt == null) {
                showErrorMsg("Error", "找不到验收单" + detail.getSrcformid());
                return false;
            }
            if (pt.getApplyamts().compareTo(detail.getPayamts()) == -1) {
                showErrorMsg("Error", detail.getSrcformid() + "可还原金额不足");
                return false;
            }
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        currentDetail.setPayamt(currentDetail.getPayamts().multiply(currentDetail.getExchange()));
        super.doConfirmDetail();
        BigDecimal amts = BigDecimal.ZERO;
        BigDecimal amt = BigDecimal.ZERO;
        for (PurchasePaymentDetail ar : detailList) {
            amts = amts.add(ar.getPayamts());
            amt = amt.add(ar.getPayamt());
        }
        currentEntity.setPayamts(amts);
        currentEntity.setPayamt(amt);
    }

    @Override
    public void deleteDetail() {
        super.deleteDetail();
        BigDecimal amts = BigDecimal.ZERO;
        BigDecimal amt = BigDecimal.ZERO;
        for (PurchasePaymentDetail ar : detailList) {
            amts = amts.add(ar.getPayamts());
            amt = amt.add(ar.getPayamt());
        }
        currentEntity.setPayamts(amts);
        currentEntity.setPayamt(amt);
    }

    public void handleDialogReturnVendorWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentEntity.setVendor(entity);
        }
    }

    public void handleDialogReturnVendorWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Vendor entity = (Vendor) event.getObject();
            this.newEntity.setVendor(entity);
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnUserWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            currentEntity.setSystemUser((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnUserWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            newEntity.setSystemUser((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnCurrencyWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            Currency entity = (Currency) event.getObject();
            this.currentDetail.setCurrency(entity.getCurrency());
            this.currentDetail.setExchange(entity.getExchange());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            PurchaseTransaction entity = (PurchaseTransaction) event.getObject();
            this.currentDetail.setCurrency(entity.getCurrency());
            this.currentDetail.setPayamts(entity.getPuramts().add(entity.getTaxamts()).subtract(entity.getApplyamts()));
            this.currentDetail.setSummary(entity.getItemMaster().getItemdesc());
            this.currentDetail.setSrcapi("purchasetransaction");
            this.currentDetail.setSrcformid(entity.getFormid());
            this.currentDetail.setSrcseq(entity.getSeq());
        }
    }

    @Override
    public void init() {
        this.superEJB = purchasePaymentBean;
        setModel(new PurchasePaymentModel(purchasePaymentBean));
        this.detailEJB = purchasePaymentDetailBean;
        super.init();
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "purchasetransactionSelect":
                if (currentEntity != null && currentEntity.getVendor() != null) {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> vendorno = new ArrayList<>();
                    vendorno.add(currentEntity.getVendor().getVendorno());
                    params.put("vendorno", vendorno);
                    List<String> vendor = new ArrayList<>();
                    vendor.add(currentEntity.getVendor().getVendor());
                    params.put("vendor", vendor);
                    openDialog(view, params);
                } else {
                    showWarnMsg("Warn", "请输入供应商");
                }
                break;
            default:
                super.openDialog(view);
        }
    }

}

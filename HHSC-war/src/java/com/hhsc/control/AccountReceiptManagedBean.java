/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.AccountReceiptBean;
import com.hhsc.ejb.AccountReceiptDetailBean;
import com.hhsc.entity.AccountReceipt;
import com.hhsc.entity.AccountReceiptDetail;
import com.hhsc.entity.AccountReceivable;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Customer;
import com.hhsc.entity.Department;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.AccountReceiptModel;
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
@ManagedBean(name = "accountReceiptManagedBean")
@SessionScoped
public class AccountReceiptManagedBean extends FormMultiBean<AccountReceipt, AccountReceiptDetail> {

    @EJB
    private AccountReceiptBean accountReceiptBean;

    @EJB
    private AccountReceiptDetailBean accountReceiptDetailBean;

    public AccountReceiptManagedBean() {
        super(AccountReceipt.class, AccountReceiptDetail.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(this.getDate());
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        newEntity.setSysuser(this.userManagedBean.getCurrentUser());
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setCurrency("CNY");
        this.newDetail.setExchange(BigDecimal.ONE);
        setCurrentDetail(newDetail);
    }

    @Override
    public void doConfirmDetail() {
        super.doConfirmDetail();
        BigDecimal amts = BigDecimal.ZERO;
        BigDecimal amt = BigDecimal.ZERO;
        for (AccountReceiptDetail ar : detailList) {
            amts = amts.add(ar.getRecamts());
            amt = amt.add(ar.getRecamt());
        }
        currentEntity.setRecamts(amts);
        currentEntity.setRecamt(amt);
    }

    @Override
    public void deleteDetail() {
        super.deleteDetail();
        BigDecimal amts = BigDecimal.ZERO;
        BigDecimal amt = BigDecimal.ZERO;
        for (AccountReceiptDetail ar : detailList) {
            amts = amts.add(ar.getRecamts());
            amt = amt.add(ar.getRecamt());
        }
        currentEntity.setRecamts(amts);
        currentEntity.setRecamt(amt);
    }

    public void handleDialogReturnCurrencyWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentDetail.setCurrency(entity.getCurrency());
            this.currentDetail.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnRecamtsWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            AccountReceivable entity = (AccountReceivable) event.getObject();
            this.currentDetail.setCurrency(entity.getCurrency());
            this.currentDetail.setRecamts(entity.getAmts().subtract(entity.getRecamts()));
            this.currentDetail.setSrcapi("accountreceivable");
            this.currentDetail.setSrcformid(entity.getFormid());
            this.currentDetail.setSrcseq(entity.getId());
        }
    }

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnSysuserWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setSysuser((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnSysuserWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setSysuser((SystemUser) event.getObject());
        }
    }

    @Override
    public void init() {
        this.superEJB = accountReceiptBean;
        setModel(new AccountReceiptModel(accountReceiptBean));
        this.detailEJB = accountReceiptDetailBean;
        super.init();
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "accountreceivableSelect":
                if (currentEntity != null && currentEntity.getCustomer() != null) {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> customerno = new ArrayList<>();
                    customerno.add(currentEntity.getCustomer().getCustomerno());
                    params.put("customerno", customerno);
                    openDialog(view, params);
                } else {
                    showWarnMsg("Warn", "请输入客户");
                }
                break;
            default:
                super.openDialog(view);
        }
    }

}

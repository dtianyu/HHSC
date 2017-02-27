/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.AccountReceivableBean;
import com.hhsc.ejb.SalesTransactionBean;
import com.hhsc.entity.AccountReceivable;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Customer;
import com.hhsc.entity.SalesTransaction;
import com.hhsc.lazy.AccountReceivableModel;
import com.hhsc.lazy.SalesTransactionModel;
import com.hhsc.web.FormMultiBean;
import com.lightshell.comm.BaseLazyModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesInvoiceManagedBean")
@SessionScoped
public class SalesInvoiceManagedBean extends FormMultiBean<AccountReceivable, SalesTransaction> {

    @EJB
    protected AccountReceivableBean accoutReceivableBean;

    @EJB
    protected SalesTransactionBean salesTransactionBean;

    protected BaseLazyModel shipmentModel;
    protected List<SalesTransaction> shipmentList;

    public SalesInvoiceManagedBean() {
        super(AccountReceivable.class, SalesTransaction.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(this.getDate());
        newEntity.setCurrency("CNY");
        newEntity.setExchange(BigDecimal.ONE);
        newEntity.setPaydate(newEntity.getFormdate());
        setCurrentEntity(newEntity);
    }

    @Override
    protected boolean doBeforeDelete(AccountReceivable entity) throws Exception {
        if (!super.doBeforeDelete(entity)) {
            return false;
        }
        for (SalesTransaction st : detailList) {
            if (st.getPid().equals("") || !st.getStatus().equals("BF")) {
                showErrorMsg("Error", "发票明细状态错误请检核");
                return false;
            }
        }
        detailList.stream().map((st) -> {
            st.setBillqty(BigDecimal.ZERO);
            return st;
        }).map((st) -> {
            st.setStatus("50");
            return st;
        }).map((st) -> {
            st.setPid("");
            return st;
        }).forEach((st) -> {
            this.editedDetailList.add(st);
        });
        return true;
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.shipmentList == null || this.shipmentList.isEmpty()) {
            showErrorMsg("Error", "没有发票明细");
            return false;
        }
        BigDecimal amts = BigDecimal.ZERO;
        SalesTransaction d;
        for (SalesTransaction st : shipmentList) {
            d = salesTransactionBean.findById(st.getId());
            if (d == null || !d.getStatus().equals("50") || !st.getStatus().equals("BF")) {
                showErrorMsg("Error", "发票明细状态错误,请检核");
                return false;
            }
            amts = amts.add(st.getShipamts()).add(st.getTaxamts()).add(st.getAddamts()).subtract(st.getOffamts());
        }
        if (amts.compareTo(this.newEntity.getAmts()) != 0) {
            showErrorMsg("Error", "发票金额不等于出货金额,请检核");
            return false;
        }
        shipmentList.stream().forEach((st) -> {
            this.editedDetailList.add(st);
        });
        if (newEntity != null) {
            newEntity.calcLocalAmounts();
            newEntity.setSalerid(shipmentList.get(0).getSalerid());
        }
        return super.doBeforePersist();
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可更新数据!");
            return false;
        }
        AccountReceivable e = accoutReceivableBean.findById(currentEntity.getId());
        if (!"N".equals(e.getStatus())) {
            showWarnMsg("Warn", "状态已变更!");
            return false;
        }
        return super.doBeforeVerify();
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        if (currentEntity.getRecamts().compareTo(BigDecimal.ZERO) != 0) {
            showErrorMsg("Error", "已收款不能还原");
            return false;
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        if (this.shipmentList == null || this.shipmentList.isEmpty()) {
            showErrorMsg("Error", "没有对应任何出货记录");
            return;
        }
        BigDecimal amts = BigDecimal.ZERO;
        for (SalesTransaction st : shipmentList) {
            amts = amts.add(st.getShipamts()).add(st.getTaxamts()).add(st.getAddamts()).subtract(st.getOffamts());
        }
        if (amts.compareTo(this.newEntity.getAmts()) != 0) {
            showErrorMsg("Error", "发票金额不等于出货金额,请核对");
            return;
        }
        shipmentList.stream().map((st) -> {
            st.setPid(newEntity.getFormid());
            return st;
        }).map((st) -> {
            st.setBillqty(st.getQty());
            return st;
        }).forEach((st) -> {
            st.setStatus("BF");
        });
        showInfoMsg("Info", "已对应出货明细,请保存");
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
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            //currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            //newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            //currentEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            //newEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    @Override
    public void init() {
        shipmentList = new ArrayList<>();
        this.superEJB = accoutReceivableBean;
        setModel(new AccountReceivableModel(accoutReceivableBean));
        this.model.getSortFields().put("status", "ASC");
        this.model.getSortFields().put("formdate", "DESC");
        this.model.getSortFields().put("formid", "DESC");
        this.detailEJB = salesTransactionBean;
        setShipmentModel(new SalesTransactionModel(salesTransactionBean));
        this.shipmentModel.getFilterFields().put("customer.id", -1);
        super.init();
    }

    @Override
    public void delete(AccountReceivable entity) {
        if (entity != null) {
            try {
                if (doBeforeDelete(entity)) {
                    superEJB.delete(entity);
                    detailEJB.update(editedDetailList);
                    doAfterDelete();
                    showInfoMsg("Info", "删除成功");
                } else {
                    showWarnMsg("Warn", "删除检核失败");
                }
            } catch (Exception e) {
                showErrorMsg("Error", e.getMessage());
            }
        } else {
            showWarnMsg("Warn", "没有可删除数据");
        }
    }

    public void loadTransaction() {
        if (newEntity == null) {
            showErrorMsg("Error", "系统发生错误请联系管理员");
            return;
        }
        if (newEntity.getCustomer() == null) {
            showErrorMsg("Error", "新输入客户");
            return;
        }
        this.shipmentModel.getFilterFields().clear();
        this.shipmentModel.getFilterFields().put("customer.id", newEntity.getCustomer().getId());
        this.shipmentModel.getFilterFields().put("currency", newEntity.getCurrency());
        this.shipmentModel.getFilterFields().put("taxrate", newEntity.getTaxrate());
        this.shipmentModel.getFilterFields().put("status", "50");
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
        this.shipmentModel.getFilterFields().clear();
        this.shipmentModel.getFilterFields().put("customer.id", -1);
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentPrgGrant() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "N":
                    this.doEdit = getCurrentPrgGrant().getDoedit() && true;
                    this.doDel = getCurrentPrgGrant().getDodel() && true;
                    this.doCfm = getCurrentPrgGrant().getDocfm() && true;
                    this.doUnCfm = getCurrentPrgGrant().getDouncfm() && false;
                    break;
                case "V":
                    this.doEdit = getCurrentPrgGrant().getDoedit() && false;
                    this.doDel = getCurrentPrgGrant().getDodel() && false;
                    this.doCfm = getCurrentPrgGrant().getDocfm() && false;
                    this.doUnCfm = getCurrentPrgGrant().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentPrgGrant().getDoedit() && false;
                    this.doDel = getCurrentPrgGrant().getDodel() && false;
                    this.doCfm = getCurrentPrgGrant().getDocfm() && false;
                    this.doUnCfm = getCurrentPrgGrant().getDouncfm() && false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    /**
     * @return the shipmentList
     */
    public List<SalesTransaction> getShipmentList() {
        return shipmentList;
    }

    /**
     * @param shipmentList the shipmentList to set
     */
    public void setShipmentList(List<SalesTransaction> shipmentList) {
        this.shipmentList = shipmentList;
    }

    /**
     * @return the shipmentModel
     */
    public BaseLazyModel getShipmentModel() {
        return shipmentModel;
    }

    /**
     * @param shipmentModel the shipmentModel to set
     */
    public void setShipmentModel(BaseLazyModel shipmentModel) {
        this.shipmentModel = shipmentModel;
    }

}

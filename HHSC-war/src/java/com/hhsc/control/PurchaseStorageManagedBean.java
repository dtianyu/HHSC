/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.PurchaseStorageBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.PurchaseStorage;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.PurchaseStorageModel;
import com.hhsc.web.SuperSingleBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
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
@ManagedBean(name = "purchaseStorageManagedBean")
@SessionScoped
public class PurchaseStorageManagedBean extends SuperSingleBean<PurchaseStorage> {

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private PurchaseStorageBean purchaseStorageBean;

    public PurchaseStorageManagedBean() {
        super(PurchaseStorage.class);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (!super.doBeforeUpdate()) {
            return false;
        }
        if (currentEntity.getQty().compareTo(currentEntity.getQcqty().add(currentEntity.getBadqty())) != 0) {
            showMsg(FacesMessage.SEVERITY_ERROR, "Error", "验收数量不等于点收数量");
            return false;
        }
        this.currentEntity.setStatus("40");//验收中状态
        //按合格数量重算金额
        this.currentEntity.setAmts(this.currentEntity.getQcqty().multiply(this.currentEntity.getPrice()));
        //按重算金额计算税额
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentEntity.getAmts(), 2);
        this.currentEntity.setExtax(t.getExtax());
        this.currentEntity.setTaxes(t.getTaxes());
        return true;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        //因为判断明细状态所以不能调用超类方法
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!");
            return false;
        }
        PurchaseStorage e = (PurchaseStorage) superEJB.findById(currentEntity.getId());
        if (!"50".equals(e.getStatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "状态已变更!");
            return false;
        }
        ItemInventory i;
        i = itemInventoryBean.findItemInventory(currentEntity.getItemno(), currentEntity.getColorno(), currentEntity.getBrand(), currentEntity.getBatch(), currentEntity.getSn(), currentEntity.getWarehouse().getWarehouseno());
        if ((i == null) || (i.getQty().compareTo(currentEntity.getQcqty().add(currentEntity.getAddqty())) == -1)) {
            showErrorMsg("Error", currentEntity.getItemno() + "库存可还原量不足");
            return false;
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        //因为判断明细状态所以不能调用超类方法
        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
            return false;
        }
        PurchaseStorage e = (PurchaseStorage) superEJB.findById(currentEntity.getId());
        if ("50".equals(e.getStatus())) {
            showWarnMsg("Warn", "状态已变更!");
            return false;
        }
        return true;
    }

    @Override
    public String edit(String path
    ) {
        if (currentEntity != null) {
            currentEntity.setAcceptdate(getDate());
            currentEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
            currentEntity.setAcceptuser(this.userManagedBean.getCurrentUser());
            currentEntity.setQcqty(currentEntity.getQty());
        }
        return super.edit(path);
    }

    @Override
    public void init() {
        superEJB = purchaseStorageBean;
        setModel(new PurchaseStorageModel(purchaseStorageBean));
        super.init();
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        //返回部门
        if (event.getObject() != null) {
            this.currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnUserWhenEdit(SelectEvent event) {
        //返回验收人
        if (event.getObject() != null) {
            this.currentEntity.setAcceptuser((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        //返回仓库
        if (event.getObject() != null) {
            this.currentEntity.setBadwarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("purchaseAcceptance.formid", queryFormId);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("acceptdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("acceptdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "50":
                    this.doEdit = getCurrentSysprg().getDoedit() && false;
                    this.doDel = getCurrentSysprg().getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = getCurrentSysprg().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentSysprg().getDoedit() && true;
                    this.doDel = getCurrentSysprg().getDodel() && true;
                    this.doCfm = getCurrentSysprg().getDocfm() && true;
                    this.doUnCfm = false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void toPrev() {
        super.toPrev();
        if (currentEntity.getAcceptdate() == null) {
            currentEntity.setAcceptdate(getDate());
            currentEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
            currentEntity.setAcceptuser(this.userManagedBean.getCurrentUser());
        }
    }

    @Override
    public void toNext() {
        super.toNext();
        if (currentEntity.getAcceptdate() == null) {
            currentEntity.setAcceptdate(getDate());
            currentEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
            currentEntity.setAcceptuser(this.userManagedBean.getCurrentUser());
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setStatus("30");
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(null);
                    currentEntity.setCfmdate(null);
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功!"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "还原前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setStatus("50");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setCfmdateToNow();
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功!"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

}

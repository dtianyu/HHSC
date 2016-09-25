/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.ItemTransactionBean;
import com.hhsc.ejb.ItemTransactionDetailBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.ItemTransaction;
import com.hhsc.entity.ItemTransactionDetail;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.entity.TransactionType;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.ItemTransactionModel;
import com.hhsc.web.FormMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemTransactionManagedBean")
@SessionScoped
public class ItemTransactionManagedBean extends FormMultiBean<ItemTransaction, ItemTransactionDetail> {

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private ItemTransactionBean itemTransactionBean;
    @EJB
    private ItemTransactionDetailBean itemTransactionDetailBean;

    public ItemTransactionManagedBean() {
        super(ItemTransaction.class, ItemTransactionDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setQty(BigDecimal.ZERO);
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有交易明细!");
            return false;
        }
        if (currentEntity.getTransactionType().getIocode() == 1) {
            ItemInventory i;
            for (ItemTransactionDetail detail : this.detailList) {
                i = itemInventoryBean.findItemInventory(detail.getItemno(), detail.getColorno(), detail.getBrand(), detail.getBatch(), detail.getSn(), detail.getWarehouse().getWarehouseno());
                if ((i == null) || (i.getQty().compareTo(detail.getQty()) == -1)) {
                    showMsg(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "库存数量不足");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有交易明细!");
            return false;
        }
        for (ItemTransactionDetail detail : this.detailList) {
            if (currentEntity.getTransactionType().getHascost() != detail.getWarehouse().getHascost()) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "交易成本属性与仓库成本属性不同!");
                return false;
            }
        }
        if (currentEntity.getTransactionType().getIocode() == -1) {
            ItemInventory i;
            for (ItemTransactionDetail detail : this.detailList) {
                i = itemInventoryBean.findItemInventory(detail.getItemno(), detail.getColorno(), detail.getBrand(), detail.getBatch(), detail.getSn(), detail.getWarehouse().getWarehouseno());
                if ((i == null) || (i.getQty().compareTo(detail.getQty()) == -1)) {
                    showMsg(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "库存数量不足");
                    return false;
                }
            }
        }
        return true;
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        handleDialogReturnDeptWhenEdit(event);
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnObjectWhenNew(SelectEvent event) {
        handleDialogReturnObjectWhenEdit(event);
    }

    public void handleDialogReturnObjectWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity.getTransactionType().getObjselect() != null) {
            switch (currentEntity.getTransactionType().getObjselect()) {
                case "customerSelect":
                    Customer c = (Customer) event.getObject();
                    currentEntity.setObjno(c.getCustomerno());
                    currentEntity.setRemark(c.getCustomer());
                    break;
                default:

            }
        }
    }

    public void handleDialogReturnReasonWhenNew(SelectEvent event) {
        handleDialogReturnReasonWhenEdit(event);
    }

    public void handleDialogReturnReasonWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {

        }
    }

    public void handleDialogReturnWarehouseWhenNew(SelectEvent event) {
        handleDialogReturnWarehouseWhenEdit(event);
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        handleDialogReturnWhenEdit(event);
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            TransactionType t = (TransactionType) event.getObject();
            currentEntity.setTransactionType(t);
            if (detailList != null && !detailList.isEmpty()) {
                for (ItemTransactionDetail d : detailList) {
                    d.setTrtype(t.getTrtype());
                }
            }
        }
    }

    public void handleDialogReturnBatchWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            currentDetail.setBatch(entity.getItemno());
        }
    }

    public void handleDialogReturnObjectWhenDetailEdit(SelectEvent event) {
        if (currentEntity.getTransactionType() == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入交易类别!");
            return;
        }
        if (currentEntity.getWarehouse() == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入默认仓库!");
            return;
        }
        if (event.getObject() != null && currentEntity.getTransactionType().getSrcselect() != null) {
            switch (currentEntity.getTransactionType().getSrcselect()) {
                case "salesordermaterialSelect":
                    SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) event.getObject();
                    this.currentDetail.setTrtype(currentEntity.getTransactionType().getTrtype());
                    this.currentDetail.setItemmaster(entity.getItemmaster());
                    this.currentDetail.setItemno(entity.getItemno());
                    this.currentDetail.setBatch(currentEntity.getObjno());
                    this.currentDetail.setQty(entity.getIssqty().subtract(entity.getInqty()));
                    this.currentDetail.setUnit(entity.getUnit());
                    this.currentDetail.setWarehouse(currentEntity.getWarehouse());
                    this.currentDetail.setSrcapi("salsesorder");
                    this.currentDetail.setSrcformid(entity.getSalesOrder().getFormid());
                    this.currentDetail.setSrcseq(entity.getSeq());
                    break;
                default:
            }

        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Warehouse entity = (Warehouse) event.getObject();
            currentDetail.setWarehouse(entity);
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (currentEntity.getTransactionType() == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入交易类别!");
            return;
        }
        if (currentEntity.getWarehouse() == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入默认仓库!");
            return;
        }
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setTrtype(currentEntity.getTransactionType().getTrtype());
            this.currentDetail.setItemmaster(entity);
            this.currentDetail.setItemno(entity.getItemno());
            this.currentDetail.setUnit(entity.getUnit());
            this.currentDetail.setWarehouse(currentEntity.getWarehouse());
        }
    }

    @Override
    public void init() {
        setSuperEJB(itemTransactionBean);
        setDetailEJB(itemTransactionDetailBean);
        setModel(new ItemTransactionModel(itemTransactionBean));
        this.model.getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if ("deptSelect".equals(view) || "warehouseSelect".equals(view)) {
            super.openDialog(view);
        } else if ("trtypeSelect".equals(view)) {
            Map<String, List<String>> params = new HashMap<>();
            List<String> sysid = new ArrayList<>();
            sysid.add("MM");
            params.put("sysid", sysid);           
            super.openDialog(view, params);
        } else {
            if (currentEntity.getTransactionType() == null) {
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入异动类别");
                return;
            }
            if (null != view) {
                switch (view) {
                    case "objSelect":
                        super.openDialog(currentEntity.getTransactionType().getObjselect());
                        break;
                    case "srcSelect":
                        switch (currentEntity.getTransactionType().getSrcselect()) {
                            case "salesordermaterialSelect":
                                if (currentEntity.getObjno() != null) {
                                    Map<String, List<String>> params = new HashMap<>();
                                    List<String> customerno = new ArrayList<>();
                                    customerno.add(currentEntity.getObjno());
                                    params.put("customerno", customerno);
                                    super.openDialog("salesordermaterialSelect", params);
                                } else {
                                    super.openDialog(currentEntity.getTransactionType().getSrcselect());
                                }
                                break;
                            default:
                                super.openDialog(currentEntity.getTransactionType().getSrcselect());
                        }
                        break;
                    default:
                        super.openDialog(view);
                        break;
                }
            }
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

}

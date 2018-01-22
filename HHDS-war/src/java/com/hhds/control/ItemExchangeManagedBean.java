/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.ItemExchangeBean;
import com.hhds.ejb.ItemInventoryBean;
import com.hhds.ejb.TransactionTypeBean;
import com.hhds.entity.Customer;
import com.hhds.entity.ItemExchange;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.SalesOrderDetailForQuery;
import com.hhds.entity.Unit;
import com.hhds.entity.Warehouse;
import com.hhds.lazy.ItemExchangeModel;
import com.hhds.web.SuperSingleBean;
import com.hhsc.entity.ItemColor;
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
@ManagedBean(name = "itemExchangeManagedBean")
@SessionScoped
public class ItemExchangeManagedBean extends SuperSingleBean<ItemExchange> {

    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private ItemExchangeBean itemExchangeBean;

    public ItemExchangeManagedBean() {
        super(ItemExchange.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setTransactionType(transactionTypeBean.findByTrtype("IAE"));
        this.newEntity.setObjtype(this.newEntity.getTransactionType().getObjtype());
        setCurrentEntity(newEntity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (newEntity.getItemMasterFrom() == null || newEntity.getItemMasterTo() == null) {
                showErrorMsg("Error", "请输入品号");
                return false;
            }
            if (newEntity.getWarehouseFrom() == null || newEntity.getWarehouseTo() == null) {
                showErrorMsg("Error", "请输入仓库");
                return false;
            }
            this.newEntity.setTransactionType(transactionTypeBean.findByTrtype("IAE"));
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen());
                this.newEntity.setFormid(formid);
            }
        }
        return super.doBeforePersist();
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }

        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }

        return true;
    }

    public void handleDialogReturnBatchFromWhenNew(SelectEvent event) {
        handleDialogReturnBatchFromWhenEdit(event);
    }

    public void handleDialogReturnBatchFromWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            com.hhsc.entity.ItemMaster entity = (com.hhsc.entity.ItemMaster) event.getObject();
            currentEntity.setBatchfrom(entity.getItemno());
            currentEntity.setBatchto(entity.getItemno());
        }
    }

    public void handleDialogReturnBatchToWhenNew(SelectEvent event) {
        handleDialogReturnBatchToWhenEdit(event);
    }

    public void handleDialogReturnBatchToWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            currentEntity.setBatchto(entity.getItemno());
        }
    }

    public void handleDialogReturnColornoFromWhenNew(SelectEvent event) {
        handleDialogReturnColornoFromWhenEdit(event);
    }

    public void handleDialogReturnColornoFromWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentEntity.setColornofrom(ic.getColorno());
            this.currentEntity.setColornoto(ic.getColorno());
        }
    }

    public void handleDialogReturnColornoToWhenNew(SelectEvent event) {
        handleDialogReturnColornoFromWhenEdit(event);
    }

    public void handleDialogReturnColornoToWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentEntity.setColornoto(ic.getColorno());
        }
    }

    public void handleDialogReturnItemFromWhenNew(SelectEvent event) {
        handleDialogReturnItemFromWhenEdit(event);
    }

    public void handleDialogReturnItemFromWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster e = (ItemMaster) event.getObject();
            currentEntity.setItemMasterFrom(e);
            currentEntity.setItemnofrom(e.getItemno());
            currentEntity.setUnitfrom(e.getUnit());
            //默认情况下转入转出品号相同
            currentEntity.setItemMasterTo(e);
            currentEntity.setItemnoto(e.getItemno());
        }
    }

    public void handleDialogReturnItemToWhenNew(SelectEvent event) {
        handleDialogReturnItemToWhenEdit(event);
    }

    public void handleDialogReturnItemToWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster e = (ItemMaster) event.getObject();
            currentEntity.setItemMasterTo(e);
            currentEntity.setItemnoto(e.getItemno());
            currentEntity.setUnitto(e.getUnit());
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
                    currentEntity.setObjno(c.getCustomer());
                    currentEntity.setRemark(c.getCustomerno());
                    break;
                case "salesorderdetailSelect":
                    SalesOrderDetailForQuery e = (SalesOrderDetailForQuery) event.getObject();
                    currentEntity.setObjno(e.getSalesOrder().getCustomer().getCustomer());
                    currentEntity.setRemark(e.getSalesOrder().getFormid());
                    currentEntity.setWarehouseFrom(e.getWarehouse());
                    currentEntity.setItemMasterFrom(e.getItemMaster());
                    currentEntity.setItemnofrom(e.getItemno());
                    currentEntity.setColornofrom(e.getColorno());
                    currentEntity.setBatchfrom(e.getBatch());
                    currentEntity.setQtyfrom(e.getQty());
                    currentEntity.setUnitfrom(e.getUnit());
                    currentEntity.setWarehouseTo(e.getWarehouse());
                    currentEntity.setItemMasterTo(e.getItemMaster());
                    currentEntity.setItemnoto(e.getItemno());
                    currentEntity.setColornoto(e.getColorno());
                    currentEntity.setBatchto(e.getBatch());
                    currentEntity.setQtyto(e.getQty());
                    currentEntity.setUnitto(e.getUnit());
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

    public void handleDialogReturnUnitFromWhenNew(SelectEvent event) {
        handleDialogReturnUnitFromWhenEdit(event);
    }

    public void handleDialogReturnUnitFromWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Unit u = (Unit) event.getObject();
            currentEntity.setUnitfrom(u.getUnit());
        }
    }

    public void handleDialogReturnUnitToWhenNew(SelectEvent event) {
        handleDialogReturnUnitToWhenEdit(event);
    }

    public void handleDialogReturnUnitToWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Unit u = (Unit) event.getObject();
            currentEntity.setUnitto(u.getUnit());
        }
    }

    public void handleDialogReturnWarehouseFromWhenNew(SelectEvent event) {
        handleDialogReturnWarehouseFromWhenEdit(event);
    }

    public void handleDialogReturnWarehouseFromWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setWarehouseFrom((Warehouse) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseToWhenNew(SelectEvent event) {
        handleDialogReturnWarehouseToWhenEdit(event);
    }

    public void handleDialogReturnWarehouseToWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setWarehouseTo((Warehouse) event.getObject());
        }
    }

    @Override
    public void init() {
        openOptions = new HashMap<>();
        openParams = new HashMap<>();
        setSuperEJB(itemExchangeBean);
        setModel(new ItemExchangeModel(itemExchangeBean));
        this.model.getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if (null != view && currentEntity != null) {
            switch (view) {
                case "objSelect":
                    switch (currentEntity.getTransactionType().getObjselect()) {
                        case "salesorderdetailSelect":
                            openOptions.clear();
                            openOptions.put("modal", true);
                            openOptions.put("contentWidth", "900");
                            super.openDialog(currentEntity.getTransactionType().getObjselect(), openOptions, null);
                            break;
                        default:
                            super.openDialog(currentEntity.getTransactionType().getObjselect());
                    }
                case "itemcolorSelectFrom":
                    if (currentEntity.getItemnofrom() != null) {
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemnofrom());
                        openParams.clear();
                        openParams.put("customeritemno", itemno);
                        super.openDialog("itemcolorSelect", openParams);
                    }
                    break;
                case "itemcolorSelectTo":
                    if (currentEntity.getItemnoto() != null) {
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemnoto());
                        openParams.clear();
                        openParams.put("customeritemno", itemno);
                        super.openDialog("itemcolorSelect", openParams);
                    }
                    break;
                default:
                    super.openDialog(view);
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
        super.reset();
        this.model.getFilterFields().put("status", "N");
    }

}

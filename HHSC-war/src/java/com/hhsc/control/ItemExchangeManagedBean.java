/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemExchangeBean;
import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.TransactionTypeBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemColor;
import com.hhsc.entity.ItemExchange;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Unit;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.ItemExchangeModel;
import com.hhsc.web.SuperSingleBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        this.newEntity.setSystemUser(this.userManagedBean.getCurrentUser());
        this.newEntity.setTransactionType(transactionTypeBean.findByTrtype("IAE"));
        this.newEntity.setObjtype(this.newEntity.getTransactionType().getObjtype());
        setCurrentEntity(newEntity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            if (newEntity.getItemMasterFrom() == null || newEntity.getItemMasterTo() == null) {
                showErrorMsg("Error", "请输入品号");
                return false;
            }
            if (newEntity.getWarehouseFrom() == null || newEntity.getWarehouseTo() == null) {
                showErrorMsg("Error", "请输入仓库");
                return false;
            }
            this.newEntity.setTransactionType(transactionTypeBean.findByTrtype("IAE"));
            if (this.getCurrentSysprg().getNoauto() && !this.getCurrentSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen());
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
            ItemMaster entity = (ItemMaster) event.getObject();
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

    public void handleDialogReturnUserWhenNew(SelectEvent event) {
        handleDialogReturnUserWhenEdit(event);
    }

    public void handleDialogReturnUserWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setSystemUser((SystemUser) event.getObject());
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
    public void handleDialogReturnWhenNew(SelectEvent event) {
        handleDialogReturnWhenEdit(event);
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    @Override
    public void init() {
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
                    super.openDialog(currentEntity.getTransactionType().getObjselect());
                    break;
                case "itemcolorSelectFrom":
                    if (currentEntity.getItemnofrom() != null) {
                        Map<String, List<String>> itemcolorParams = new HashMap<>();
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemnofrom());
                        itemcolorParams.put("itemno", itemno);
                        super.openDialog("itemcolorSelect", itemcolorParams);
                    }
                    break;
                case "itemcolorSelectTo":
                    if (currentEntity.getItemnoto() != null) {
                        Map<String, List<String>> itemcolorParams = new HashMap<>();
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemnoto());
                        itemcolorParams.put("itemno", itemno);
                        super.openDialog("itemcolorSelect", itemcolorParams);
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

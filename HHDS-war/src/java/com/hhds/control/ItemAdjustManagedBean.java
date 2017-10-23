/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.ItemAdjustBean;
import com.hhds.ejb.ItemAdjustDetailBean;
import com.hhds.ejb.ItemInventoryBean;
import com.hhds.ejb.TransactionTypeBean;
import com.hhds.entity.ItemAdjust;
import com.hhds.entity.ItemAdjustDetail;
import com.hhds.entity.ItemInventory;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.TransactionType;
import com.hhds.entity.VendorItem;
import com.hhds.entity.Warehouse;
import com.hhds.lazy.ItemAdjustModel;
import com.hhds.web.FormMultiBean;
import com.hhds.ejb.VendorItemBean;
import com.hhds.entity.Unit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "itemAdjustManagedBean")
@SessionScoped
public class ItemAdjustManagedBean extends FormMultiBean<ItemAdjust, ItemAdjustDetail> {

    @EJB
    private ItemAdjustBean itemAdjustBean;
    @EJB
    private ItemAdjustDetailBean itemAdjustDetailBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private TransactionTypeBean transactoinTypeBean;

    @EJB
    private VendorItemBean vendorItemBean;

    private TransactionType trtype;

    private List<String> paramItemno = null;

    public ItemAdjustManagedBean() {
        super(ItemAdjust.class, ItemAdjustDetail.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(getDate());
    }

    @Override
    public void createDetail() {
        super.createDetail();
        currentDetail.setTrtype(trtype);
        currentDetail.setWarehouse(currentEntity.getWarehouse());
        currentDetail.setWarehouse2(currentEntity.getWarehouse2());
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (super.doBeforePersist()) {
            for (ItemAdjustDetail add : detailList) {
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (super.doBeforeUpdate()) {
            for (ItemAdjustDetail add : detailList) {
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (super.doBeforeUnverify()) {
            ItemInventory ii;
            for (ItemAdjustDetail iad : detailList) {
                ii = itemInventoryBean.findItemInventory(iad.getItemno(), iad.getColorno(), iad.getBrand(), iad.getBatch(), iad.getSn(), iad.getWarehouse2().getWarehouseno());
                if ((ii == null) || ii.getQty().compareTo(iad.getQty()) == -1) {
                    showErrorMsg("Error", iad.getItemno() + "库存可还原量不足");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (super.doBeforeVerify()) {
            ItemInventory ii;
            for (ItemAdjustDetail iad : detailList) {
                ii = itemInventoryBean.findItemInventory(iad.getItemno(), iad.getColorno(), iad.getBrand(), iad.getBatch(), "", iad.getWarehouse().getWarehouseno());
                if ((ii == null) || ii.getQty().compareTo(iad.getQty()) == -1) {
                    showErrorMsg("Error", iad.getItemno() + "库存可利用量不足");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void doConfirmDetail() {
        if (currentDetail == null) {
            return;
        }
        if (currentDetail.getItemMaster() == null) {
            showErrorMsg("Error", "请输入件号");
            return;
        }
        if (currentDetail.getWarehouse() == null) {
            showErrorMsg("Error", "请输入来源仓");
            return;
        }
        if (currentDetail.getWarehouse2() == null) {
            showErrorMsg("Error", "请输入目的仓");
            return;
        }
        if (currentDetail.getQty().compareTo(BigDecimal.ZERO) != 1) {
            showErrorMsg("Error", "请输入数量");
            return;
        }
        super.doConfirmDetail();
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Warehouse e = (Warehouse) event.getObject();
            currentEntity.setWarehouse(e);
        }
    }

    public void handleDialogReturnWarehouseWhenNew(SelectEvent event) {
        handleDialogReturnWarehouseWhenEdit(event);
    }

    public void handleDialogReturnWarehouse2WhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Warehouse e = (Warehouse) event.getObject();
            currentEntity.setWarehouse2(e);
        }
    }

    public void handleDialogReturnWarehouse2WhenNew(SelectEvent event) {
        handleDialogReturnWarehouse2WhenEdit(event);
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            ItemMaster e = (ItemMaster) event.getObject();
            currentDetail.setItemMaster(e);
            currentDetail.setItemno(e.getItemno());
            currentDetail.setUnit(e.getUnit());
            currentDetail.setWarehouse(currentEntity.getWarehouse());
            currentDetail.setWarehouse2(currentEntity.getWarehouse2());
            VendorItem vi = vendorItemBean.findFirstByItemno(e.getItemno());
            if (vi != null) {
                currentDetail.setBatch(vi.getVendordesignno());
                currentDetail.setColorno(vi.getVendoritemcolor());
            }
        }
    }

    public void handleDialogReturnColornoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            com.hhsc.entity.ItemColor e = (com.hhsc.entity.ItemColor) event.getObject();
            currentDetail.setColorno(e.getColorno());
        }
    }

    public void handleDialogReturnDesignnoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            com.hhsc.entity.ItemMaster e = (com.hhsc.entity.ItemMaster) event.getObject();
            currentDetail.setBatch(e.getItemno());
        }
    }

    public void handleDialogReturnUnitWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            Unit u = (Unit) event.getObject();
            currentDetail.setUnit(u.getUnit());
        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            Warehouse e = (Warehouse) event.getObject();
            currentDetail.setWarehouse(e);
        }
    }

    public void handleDialogReturnWarehouse2WhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            Warehouse e = (Warehouse) event.getObject();
            currentDetail.setWarehouse2(e);
        }
    }

    @Override
    public void init() {
        openParams = new HashMap<>();
        superEJB = itemAdjustBean;
        detailEJB = itemAdjustDetailBean;
        model = new ItemAdjustModel(itemAdjustBean);
        model.getSortFields().put("status", "ASC");
        model.getSortFields().put("formid", "DESC");
        trtype = transactoinTypeBean.findByTrtype("IAC");
        if (trtype == null) {
            showErrorMsg("Error", "AIC异动类别未设置");
        }
        super.init();
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "warehouse2Select":
                super.openDialog("warehouseSelect");
                break;
            case "itemcolorSelect":
                if (currentDetail.getItemMaster() == null) {
                    showErrorMsg("Error", "请先输入商品编号");
                }
                openParams.clear();
                if (paramItemno == null) {
                    paramItemno = new ArrayList<>();
                } else {
                    paramItemno.clear();
                }
                paramItemno.add(currentDetail.getItemno());
                openParams.put("customeritemno", paramItemno);
                if (openOptions == null) {
                    openOptions = new HashMap();
                    openOptions.put("modal", true);
                    openOptions.put("contentWidth", "900");
                }
                super.openDialog("itemcolorSelect", openOptions, openParams);
                break;
            default:
                super.openDialog(view);
        }
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.ItemCategoryBean;
import com.hhds.ejb.ItemMasterBean;
import com.hhds.ejb.SalesOrderDetailBean;
import com.hhds.ejb.VendorItemBean;
import com.hhds.entity.ItemCategory;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.Unit;
import com.hhds.entity.Vendor;
import com.hhds.entity.VendorItem;
import com.hhds.lazy.ItemMasterModel;
import com.hhds.web.SuperMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemMasterManagedBean")
@SessionScoped
public class ItemMasterManagedBean extends SuperMultiBean<ItemMaster, VendorItem> {

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;
    @EJB
    protected ItemCategoryBean itemCategoryBean;
    @EJB
    protected ItemMasterBean itemMasterBean;
    @EJB
    protected VendorItemBean vendorItemBean;

    protected List<ItemCategory> itemCategoryList;
    protected String queryItemspec;

    public ItemMasterManagedBean() {
        super(ItemMaster.class, VendorItem.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setUnittype("1");
        newEntity.setQcpass(false);
        newEntity.setUnitexch(BigDecimal.ONE);
        newEntity.setInvtype(true);
        newEntity.setBbstype("000");
        newEntity.setPurmax(BigDecimal.ZERO);
        newEntity.setPurmin(BigDecimal.ZERO);
        newEntity.setInvmax(BigDecimal.ZERO);
        newEntity.setInvmin(BigDecimal.ZERO);
        if (this.superEJB != null && this.getCurrentPrgGrant().getSysprg().getNoauto()) {
            String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "itemno");
            newEntity.setItemno(formid);
        }
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setItemno("");
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeDelete(ItemMaster entity) throws Exception {
        if (entity != null) {
            Map<String, Object> filters = new HashMap<>();
            filters.put("itemno", entity.getItemno());
            if (salesOrderDetailBean.getRowCount(filters) > 0) {
                showErrorMsg("Error", "已有交易记录不能删除");
                return false;
            }
        }
        return super.doBeforeDelete(entity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "itemno");
                this.newEntity.setItemno(formid);
            } else {
                ItemMaster im = itemMasterBean.findByItemno(newEntity.getItemno());
                if (im != null) {
                    showErrorMsg("Error", "品号已存在无法保存");
                    return false;
                }
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                this.addedDetailList.forEach((detail) -> {
                    detail.setItemno(newEntity.getItemno());
                });
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                this.editedDetailList.forEach((detail) -> {
                    detail.setItemno(newEntity.getItemno());
                });
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (VendorItem detail : this.addedDetailList) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (VendorItem detail : this.editedDetailList) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可更新数据");
            return false;
        }
        ItemMaster e = itemMasterBean.findById(currentEntity.getId());
        if ("V".equals(e.getStatus())) {
            showWarnMsg("Warn", "状态已变更");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        detailList = vendorItemBean.findByItemId(currentEntity.getId());
        if (this.detailList.isEmpty()) {
            showErrorMsg("Error", "请设定供应商品号信息");
            return false;
        }
        return true;
    }

    public void handleDialogReturnVendorDesignWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            com.hhsc.entity.ItemMaster im = (com.hhsc.entity.ItemMaster) event.getObject();
            this.currentDetail.setVendordesignno(im.getItemno());
            this.currentDetail.setVendoritemdesc(im.getItemdesc());
            this.currentDetail.setVendoritemspec(im.getItemspec());
        }
    }

    public void handleDialogReturnVendorItemWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            com.hhsc.entity.ItemMaster im = (com.hhsc.entity.ItemMaster) event.getObject();
            this.currentDetail.setVendoritemno(im.getItemno());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentDetail.setPid(entity.getId());
            this.currentDetail.setVendor(entity);
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.currentEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.newEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setImg1(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setImg1(fileName);
        }
    }

    @Override
    public void init() {
        superEJB = itemMasterBean;
        model = new ItemMasterModel(itemMasterBean);
        detailEJB = vendorItemBean;
        itemCategoryList = itemCategoryBean.findAll();
        super.init();
    }

    @Override
    public void persist() {
        if (getNewEntity() != null) {
            try {
                if (doBeforePersist()) {
                    this.superEJB.persist(newEntity);
                    int itemid = newEntity.getId();
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        this.editedDetailList.stream().map((detail) -> {
                            detail.setItemid(itemid);
                            return detail;
                        }).forEach((detail) -> {
                            this.detailEJB.update(detail);
                        });
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        this.deletedDetailList.stream().forEach((detail) -> {
                            this.detailEJB.delete(detail);
                        });
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        this.addedDetailList.stream().map((detail) -> {
                            detail.setItemid(itemid);
                            return detail;
                        }).forEach((detail) -> {
                            this.detailEJB.persist(detail);
                        });
                    }
                    doAfterPersist();
                    showInfoMsg("Info", "更新成功！");
                } else {
                    showErrorMsg("Error", "更新前检核失败");
                }
            } catch (Exception e) {
                showErrorMsg("Error", e.getMessage());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("itemno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemdesc", queryName);
            }
            if (queryItemspec != null && !"".equals(queryItemspec)) {
                this.model.getFilterFields().put("itemspec", queryItemspec);
            }
        }
    }

    @Override
    public void update() {
        if (currentEntity != null) {
            try {
                if (doBeforeUpdate()) {
                    this.superEJB.update(currentEntity);
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        for (VendorItem detail : this.editedDetailList) {
                            detail.setItemid(currentEntity.getId());
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (VendorItem detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (VendorItem detail : this.addedDetailList) {
                            detail.setItemid(currentEntity.getId());
                            this.detailEJB.persist(detail);
                        }
                    }
                    doAfterUpdate();
                    showInfoMsg("Info", "更新成功");
                } else {
                    showErrorMsg("Error", "更新前检核失败");
                }
            } catch (Exception ex) {
                showErrorMsg("Error", ex.toString());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
        }
    }

    @Override
    public void setCurrentEntity(ItemMaster currentEntity) {
        this.currentEntity = currentEntity;
        setToolBar();
        if (currentEntity != null && currentEntity.getId() != null && this.model != null && !this.model.getDataList().isEmpty()) {
            int idx = this.model.getDataList().indexOf(currentEntity);
            if (idx == 0 && this.model.getDataList().size() == 1) {
                setHasPrev(false);
                setHasNext(false);
            } else if (idx == 0 && this.model.getDataList().size() > 1) {
                setHasPrev(false);
                setHasNext(true);
            } else if (idx == (this.model.getDataList().size() - 1)) {
                setHasPrev(true);
                setHasNext(false);
            } else {
                setHasPrev(true);
                setHasNext(true);
            }
        }
        setDetailList(vendorItemBean.findByItemId(currentEntity.getId()));
        if (this.detailList == null) {
            this.detailList = new ArrayList<>();
        }
    }

    /**
     * @return the itemCategoryList
     */
    public List<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

    /**
     * @return the queryItemspec
     */
    public String getQueryItemspec() {
        return queryItemspec;
    }

    /**
     * @param queryItemspec the queryItemspec to set
     */
    public void setQueryItemspec(String queryItemspec) {
        this.queryItemspec = queryItemspec;
    }

}

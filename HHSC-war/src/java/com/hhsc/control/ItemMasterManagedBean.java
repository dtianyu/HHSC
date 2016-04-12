/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemCategoryBean;
import com.hhsc.ejb.ItemMakeBean;
import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.ejb.ItemUnitTypeBean;
import com.hhsc.ejb.VendorItemBean;
import com.hhsc.entity.ItemCategory;
import com.hhsc.entity.ItemMake;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.Unit;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.VendorItem;
import com.hhsc.lazy.ItemMasterModel;
import com.hhsc.web.SuperMulti2Bean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "itemMasterManagedBean")
@SessionScoped
public class ItemMasterManagedBean extends SuperMulti2Bean<ItemMaster, ItemMake, VendorItem> {

    @EJB
    protected ItemCategoryBean itemCategoryBean;
    @EJB
    protected ItemUnitTypeBean itemUnitTypeBean;
    @EJB
    protected ItemMasterBean itemMasterBean;
    @EJB
    protected ItemMakeBean itemMakeBean;
    @EJB
    protected VendorItemBean vendorItemBean;

    protected List<ItemCategory> itemCategoryList;
    protected String queryItemspec;
    protected String queryItemmake;

    public ItemMasterManagedBean() {
        super(ItemMaster.class, ItemMake.class, VendorItem.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setItemcategory(itemCategoryBean.findByCategory("300"));
        newEntity.setProptype("3");
        newEntity.setUnittype("1");
        newEntity.setQcpass(false);
        newEntity.setUnitexch(BigDecimal.ONE);
        newEntity.setPurmax(BigDecimal.ZERO);
        newEntity.setPurmin(BigDecimal.ZERO);
        newEntity.setInvmax(BigDecimal.ZERO);
        newEntity.setInvmin(BigDecimal.ZERO);
        if (this.superEJB != null && this.getCurrentSysprg().getNoauto()) {
            String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen(), "itemno");
            newEntity.setItemno(formid);
        }
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setItemno("");
        setCurrentDetail(newDetail);
    }

    @Override
    public void createDetail2() {
        super.createDetail2();
        this.newDetail2.setItemno("");
        setCurrentDetail2(newDetail2);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            StringBuilder sb = new StringBuilder();
            if (this.getCurrentSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen(), "itemno");
                this.newEntity.setItemno(formid);
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (ItemMake detail : this.addedDetailList) {
                    detail.setItemno(newEntity.getItemno());
                    sb.append(detail.getMake()).append(";");
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (ItemMake detail : this.editedDetailList) {
                    detail.setItemno(newEntity.getItemno());
                    sb.append(detail.getMake()).append(";");
                }
            }
            if (this.addedDetailList2 != null && !this.addedDetailList2.isEmpty()) {
                for (VendorItem detail : this.addedDetailList2) {
                    detail.setItemno(newEntity.getItemno());
                }
            }
            if (this.editedDetailList2 != null && !this.editedDetailList2.isEmpty()) {
                for (VendorItem detail : this.editedDetailList2) {
                    detail.setItemno(newEntity.getItemno());
                }
            }
            this.newEntity.setItemmake(sb.toString());
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            StringBuilder sb = new StringBuilder();
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (ItemMake detail : this.addedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                    sb.append(detail.getMake()).append(";");
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (ItemMake detail : this.editedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                    sb.append(detail.getMake()).append(";");
                }
            }
            if (this.addedDetailList2 != null && !this.addedDetailList2.isEmpty()) {
                for (VendorItem detail : this.addedDetailList2) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            if (this.editedDetailList2 != null && !this.editedDetailList2.isEmpty()) {
                for (VendorItem detail : this.editedDetailList2) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            this.currentEntity.setItemmake(sb.toString());
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    public String edit(String path) {
        if (currentEntity != null) {
            setDetailList(itemMakeBean.findByPId(currentEntity.getId()));
            if (this.detailList == null) {
                this.detailList = new ArrayList<>();
            }
            setDetailList2(vendorItemBean.findByItemId(currentEntity.getId()));
            if (this.detailList2 == null) {
                this.detailList2 = new ArrayList<>();
            }
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择编辑数据！"));
            return "";
        }
    }

    @Override
    public void handleDialogReturnWhenDetail2Edit(SelectEvent event) {
        if (event.getObject() != null) {
            Vendor entity = (Vendor) event.getObject();
            this.currentDetail2.setPid(entity.getId());
            this.currentDetail2.setVendor(entity);
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Unit entity = (Unit) event.getObject();
            this.currentEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Unit entity = (Unit) event.getObject();
            this.newEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void init() {
        this.superEJB = itemMasterBean;
        setModel(new ItemMasterModel(itemMasterBean));
        this.detailEJB = itemMakeBean;
        this.detailEJB2 = vendorItemBean;
        itemCategoryList = itemCategoryBean.findAll();
        super.init();
    }

    @Override
    public void persist() {
        if (getNewEntity() != null) {
            try {
                if (doBeforePersist()) {
                    this.superEJB.persist(newEntity);
                    int pid = newEntity.getId();
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        for (ItemMake detail : this.editedDetailList) {
                            detail.setPid(pid);
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ItemMake detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ItemMake detail : this.addedDetailList) {
                            detail.setPid(pid);
                            this.detailEJB.persist(detail);
                        }
                    }
                    if (getEditedDetailList2() != null && !getEditedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.editedDetailList2) {
                            detail.setItemid(pid);
                            this.detailEJB2.update(detail);
                        }
                    }
                    if (getDeletedDetailList2() != null && !getDeletedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.deletedDetailList2) {
                            this.detailEJB2.delete(detail);
                        }
                    }
                    if (getAddedDetailList2() != null && !getAddedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.addedDetailList2) {
                            detail.setItemid(pid);
                            this.detailEJB2.persist(detail);
                        }
                    }
                    doAfterPersist();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "更新前检核失败"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据！"));
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
            if (queryItemmake != null && !"".equals(queryItemmake)) {
                this.model.getFilterFields().put("itemmake", queryItemmake);
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
                        for (ItemMake detail : this.editedDetailList) {
                            detail.setPid(currentEntity.getId());
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ItemMake detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ItemMake detail : this.addedDetailList) {
                            detail.setPid(currentEntity.getId());
                            this.detailEJB.persist(detail);
                        }
                    }
                    if (getEditedDetailList2() != null && !getEditedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.editedDetailList2) {
                            detail.setItemid(currentEntity.getId());
                            this.detailEJB2.update(detail);
                        }
                    }
                    if (getDeletedDetailList2() != null && !getDeletedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.deletedDetailList2) {
                            this.detailEJB2.delete(detail);
                        }
                    }
                    if (getAddedDetailList2() != null && !getAddedDetailList2().isEmpty()) {
                        for (VendorItem detail : this.addedDetailList2) {
                            detail.setItemid(currentEntity.getId());
                            this.detailEJB2.persist(detail);
                        }
                    }
                    doAfterUpdate();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "更新前检核失败"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.toString()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据！"));
        }
    }

    @Override
    public String view(String path) {
        if (currentEntity != null) {
            setDetailList(itemMakeBean.findByPId(currentEntity.getId()));
            if (this.detailList == null) {
                this.detailList = new ArrayList<>();
            }
            setDetailList2(vendorItemBean.findByItemId(currentEntity.getId()));
            if (this.detailList2 == null) {
                this.detailList2 = new ArrayList<>();
            }
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择编辑数据！"));
            return "";
        }
    }

    /**
     * @return the itemCategoryList
     */
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

    /**
     * @return the queryItemmake
     */
    public String getQueryItemmake() {
        return queryItemmake;
    }

    /**
     * @param queryItemmake the queryItemmake to set
     */
    public void setQueryItemmake(String queryItemmake) {
        this.queryItemmake = queryItemmake;
    }

}

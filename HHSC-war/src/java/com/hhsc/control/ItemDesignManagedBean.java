/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerItemBean;
import com.hhsc.ejb.ItemCategoryBean;
import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.ItemCategory;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.Unit;
import com.hhsc.lazy.ItemDesignModel;
import com.hhsc.web.SuperMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemDesignManagedBean")
@SessionScoped
public class ItemDesignManagedBean extends SuperMultiBean<ItemMaster, CustomerItem> {

    @EJB
    private ItemCategoryBean itemCategoryBean;
    @EJB
    private ItemMasterBean itemMasterBean;
    @EJB
    private CustomerItemBean customerItemBean;

    protected List<ItemCategory> itemCategoryList;
    protected String queryItemspec;
    protected String queryItemmake;

    public ItemDesignManagedBean() {
        super(ItemMaster.class, CustomerItem.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setItemcategory(itemCategoryBean.findByCategory("200"));
        newEntity.setProptype("2");
        newEntity.setUnittype("1");
        newEntity.setQcpass(false);
        newEntity.setUnitexch(BigDecimal.ONE);
        newEntity.setInvtype(true);
        newEntity.setBbstype("000");
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
        newDetail.setCustomeritemno("");
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        //新增后即审核2016/10/31
        this.verify();
        return super.doAfterPersist(); 
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            if (this.getCurrentSysprg().getNoauto() && !this.getCurrentSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen(), "itemno");
                this.newEntity.setItemno(formid);
                if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                    for (CustomerItem detail : this.addedDetailList) {
                        detail.setItemno(formid);
                    }
                }
                if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                    this.editedDetailList.stream().forEach((detail) -> {
                        detail.setItemno(formid);
                    });
                }
            } else {
                if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                    this.addedDetailList.stream().forEach((detail) -> {
                        detail.setItemno(newEntity.getItemno());
                    });
                }
                if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                    this.editedDetailList.stream().forEach((detail) -> {
                        detail.setItemno(newEntity.getItemno());
                    });
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (CustomerItem detail : this.addedDetailList) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (CustomerItem detail : this.editedDetailList) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    public String edit(String path) {
        if (currentEntity != null) {
            setDetailList(customerItemBean.findByItemId(currentEntity.getId()));
            if (this.detailList == null) {
                this.detailList = new ArrayList<>();
            }
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择编辑数据！"));
            return "";
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
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentDetail.setPid(entity.getId());
            this.currentDetail.setCustomer(entity);
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newDetail.setPid(entity.getId());
            this.newDetail.setCustomer(entity);
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
        this.superEJB = itemMasterBean;
        setModel(new ItemDesignModel(itemMasterBean));
        this.detailEJB = customerItemBean;
        this.model.getFilterFields().put("itemcategory.category", "200");
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
                        for (CustomerItem detail : this.editedDetailList) {
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (CustomerItem detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (CustomerItem detail : this.addedDetailList) {
                            detail.setItemid(pid);
                            this.detailEJB.persist(detail);
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
            this.model.getFilterFields().put("itemcategory.category", "200");
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("itemcategory.category", "200");
    }
    
    

    @Override
    public void update() {
        if (currentEntity != null) {
            try {
                if (doBeforeUpdate()) {
                    this.superEJB.update(currentEntity);
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        for (CustomerItem detail : this.editedDetailList) {
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (CustomerItem detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (CustomerItem detail : this.addedDetailList) {
                            detail.setItemid(currentEntity.getId());
                            this.detailEJB.persist(detail);
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
            setDetailList(customerItemBean.findByItemId(currentEntity.getId()));
            if (this.detailList == null) {
                this.detailList = new ArrayList<>();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerItemBean;
import com.hhsc.ejb.ItemCategoryBean;
import com.hhsc.ejb.ItemDesignBean;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.ItemCategory;
import com.hhsc.entity.ItemDesign;
import com.hhsc.lazy.ItemDesignModel;
import com.hhsc.web.SuperMultiBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemDesignManagedBean")
@SessionScoped
public class ItemDesignManagedBean extends SuperMultiBean<ItemDesign, CustomerItem> {

    @EJB
    private ItemCategoryBean itemCategoryBean;
    @EJB
    private ItemDesignBean itemDesignBean;
    @EJB
    private CustomerItemBean customerItemBean;

    protected List<ItemCategory> itemCategoryList;

    public ItemDesignManagedBean() {
        super(ItemDesign.class, CustomerItem.class);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            String formid = "";
            if (this.getCurrentSysprg().getNoauto()) {
                formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen());
            }
            this.newEntity.setDesignid(formid);
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (CustomerItem detail : this.addedDetailList) {
                    detail.setItemno(formid);
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (CustomerItem detail : this.editedDetailList) {
                    detail.setItemno(formid);
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
                    detail.setItemno(this.currentEntity.getDesignid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (CustomerItem detail : this.editedDetailList) {
                    detail.setItemid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getDesignid());
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
    public void init() {
        this.superEJB = itemDesignBean;
        setModel(new ItemDesignModel(itemDesignBean));
        this.detailEJB = customerItemBean;
        itemCategoryList = itemCategoryBean.findAll();
        super.init();
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setFilename(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setFilename(fileName);
        }
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

    /**
     * @return the itemCategoryList
     */
    public List<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

}

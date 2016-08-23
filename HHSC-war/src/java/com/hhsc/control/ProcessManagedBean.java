/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ProcessResourceBean;
import com.hhsc.ejb.ProcessBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.Process;
import com.hhsc.entity.ProcessResource;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.ProcessModel;
import com.hhsc.web.SuperMultiBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "processManagedBean")
@SessionScoped
public class ProcessManagedBean extends SuperMultiBean<Process, ProcessResource> {

    @EJB
    private ProcessResourceBean processResourceBean;

    @EJB
    private ProcessBean processBean;

    private List<ProcessResource> filteredResource;

    /**
     * Creates a new instance of ProcessManagedBean
     */
    public ProcessManagedBean() {
        super(Process.class, ProcessResource.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        newDetail.setStatus("N");
        setCurrentDetail(newDetail);
    }

    @Override
    public void doConfirmDetail() {
        switch (currentDetail.getValuetype()) {
            case "bool":
                currentDetail.setNumvalue(null);
                currentDetail.setStrvalue(null);
                break;
            case "Decimal":
                currentDetail.setBoolvalue(null);
                currentDetail.setStrvalue(null);
                break;
            case "String":
                currentDetail.setBoolvalue(null);
                currentDetail.setNumvalue(null);
                break;
            default:
        }
        super.doConfirmDetail();
    }

    public void handleDialogReturnLeaderWhenNew(SelectEvent event) {
        handleDialogReturnLeaderWhenEdit(event);
    }

    public void handleDialogReturnLeaderWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            SystemUser u = (SystemUser) event.getObject();
            currentEntity.setLeader(u.getUsername());
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
        this.superEJB = processBean;
        setModel(new ProcessModel(processBean));
        this.detailEJB = processResourceBean;
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
                        for (ProcessResource detail : this.editedDetailList) {
                            detail.setPid(pid);
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ProcessResource detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ProcessResource detail : this.addedDetailList) {
                            detail.setPid(pid);
                            this.detailEJB.persist(detail);
                        }
                    }
                    doAfterPersist();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_ERROR, "Error", "更新前检核失败");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    /**
     * @return the filteredResource
     */
    public List<ProcessResource> getFilteredResource() {
        return filteredResource;
    }

    /**
     * @param filteredResource the filteredResource to set
     */
    public void setFilteredResource(List<ProcessResource> filteredResource) {
        this.filteredResource = filteredResource;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ProcessDetailBean;
import com.hhsc.ejb.ProcessGroupBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.ProcessGroup;
import com.hhsc.entity.ProcessDetail;
import com.hhsc.entity.ProcessResource;
import com.hhsc.lazy.ProcessGroupModel;
import com.hhsc.web.SuperMultiBean;
import java.util.ArrayList;
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
@ManagedBean(name = "processGroupManagedBean")
@SessionScoped
public class ProcessGroupManagedBean extends SuperMultiBean<ProcessGroup, ProcessDetail> {

    @EJB
    private ProcessDetailBean processDetailBean;

    @EJB
    private ProcessGroupBean processGroupBean;

    private List<ProcessDetail> equipments;
    private List<ProcessDetail> processes;
    private List<ProcessDetail> materials;
    private List<ProcessDetail> hurmans;

    /**
     * Creates a new instance of ProcessGroupManagedBean
     */
    public ProcessGroupManagedBean() {
        super(ProcessGroup.class, ProcessDetail.class);
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
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        this.equipments.clear();
        this.processes.clear();
        this.materials.clear();
        this.hurmans.clear();
        return super.doAfterPersist();
    }

    @Override
    public void doConfirmDetail() {
        super.doConfirmDetail();
        splitResource();
    }

    @Override
    public void deleteDetail(ProcessDetail entity) {
        super.deleteDetail(entity);
        this.splitResource();
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
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ProcessResource e = (ProcessResource) event.getObject();
            this.currentDetail.setProcess(e.getProcess());
            this.currentDetail.setProcid(e.getProcess().getId());
            this.currentDetail.setProcseq(e.getSeq());
            this.currentDetail.setKind(e.getKind());
            this.currentDetail.setContent(e.getContent());
            this.currentDetail.setValuetype(e.getValuetype());
            this.currentDetail.setBoolvalue(e.getBoolvalue());
            this.currentDetail.setNumvalue(e.getNumvalue());
            this.currentDetail.setStrvalue(e.getStrvalue());
            this.currentDetail.setRemark(e.getRemark());
        }
    }

    @Override
    public void init() {
        this.superEJB = processGroupBean;
        setModel(new ProcessGroupModel(processGroupBean));
        this.detailEJB = processDetailBean;
        this.setEquipments(new ArrayList<>());
        this.setProcesses(new ArrayList<>());
        this.setMaterials(new ArrayList<>());
        this.setHurmans(new ArrayList<>());
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
                        for (ProcessDetail detail : this.editedDetailList) {
                            detail.setPid(pid);
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ProcessDetail detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ProcessDetail detail : this.addedDetailList) {
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

    @Override
    public void setDetailList(List<ProcessDetail> detailList) {
        super.setDetailList(detailList);
        if (!detailList.isEmpty()) {
            splitResource();
        }
    }

    private void splitResource() {
        this.getEquipments().clear();
        this.getProcesses().clear();
        this.getMaterials().clear();
        this.getHurmans().clear();
        for (ProcessDetail r : detailList) {
            switch (r.getKind()) {
                case "E":
                    this.getEquipments().add(r);
                    break;
                case "P":
                    this.getProcesses().add(r);
                    break;
                case "M":
                    this.getMaterials().add(r);
                    break;
                case "H":
                    this.getHurmans().add(r);
                    break;
                default:
            }
        }
    }

    /**
     * @return the equipments
     */
    public List<ProcessDetail> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(List<ProcessDetail> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the processes
     */
    public List<ProcessDetail> getProcesses() {
        return processes;
    }

    /**
     * @param processes the processes to set
     */
    public void setProcesses(List<ProcessDetail> processes) {
        this.processes = processes;
    }

    /**
     * @return the materials
     */
    public List<ProcessDetail> getMaterials() {
        return materials;
    }

    /**
     * @param materials the materials to set
     */
    public void setMaterials(List<ProcessDetail> materials) {
        this.materials = materials;
    }

    /**
     * @return the hurmans
     */
    public List<ProcessDetail> getHurmans() {
        return hurmans;
    }

    /**
     * @param hurmans the hurmans to set
     */
    public void setHurmans(List<ProcessDetail> hurmans) {
        this.hurmans = hurmans;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemResourceBean;
import com.hhsc.ejb.ItemProcessBean;
import com.hhsc.ejb.ProcessDetailBean;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.ItemResource;
import com.hhsc.entity.ProcessDetail;
import com.hhsc.entity.ProcessGroup;
import com.hhsc.entity.ProcessResource;
import com.hhsc.lazy.ItemProcessModel;
import com.hhsc.rpt.ItemProcessReport;
import com.hhsc.web.SuperMultiBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemProcessManagedBean")
@SessionScoped
public class ItemProcessManagedBean extends SuperMultiBean<ItemProcess, ItemResource> {

    @EJB
    private ProcessDetailBean processDetailBean;

    @EJB
    private ItemProcessBean itemProcessBean;
    @EJB
    private ItemResourceBean itemResourceBean;

    private List<ItemResource> equipments;
    private List<ItemResource> processes;
    private List<ItemResource> materials;
    private List<ItemResource> hurmans;

    public ItemProcessManagedBean() {
        super(ItemProcess.class, ItemResource.class);
    }

    @Override
    public void create() {
        super.create();
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (ItemResource detail : this.addedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (ItemResource detail : this.editedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    public void doConfirmDetail() {
        super.doConfirmDetail();
        this.splitResource();
    }

    @Override
    public void deleteDetail(ItemResource entity) {
        super.deleteDetail(entity);
        this.splitResource();
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setItemmaster(entity);
            this.currentEntity.setItemno(entity.getItemno());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setItemmaster(entity);
            this.newEntity.setItemno(entity.getItemno());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if ((event.getObject() != null) && (currentEntity.getItemmaster() != null)) {
            ProcessResource e = (ProcessResource) event.getObject();
            this.currentDetail.setItemno(currentEntity.getItemmaster().getItemno());
            this.currentDetail.setProcess(e.getProcess());
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

    public void handleDialogReturnGroupWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null && this.detailList != null) {
            ProcessGroup g = (ProcessGroup) event.getObject();
            List<ProcessDetail> processDetails = processDetailBean.findByPId(g.getId());
            if (processDetails != null && !processDetails.isEmpty()) {
                for (ProcessDetail r : processDetails) {
                    this.createDetail();                   
                    currentDetail.setItemno(currentEntity.getItemno());
                    currentDetail.setProcess(r.getProcess());
                    currentDetail.setProcseq(r.getSeq());
                    currentDetail.setKind(r.getKind());
                    currentDetail.setContent(r.getContent());
                    currentDetail.setValuetype(r.getValuetype());
                    currentDetail.setBoolvalue(r.getBoolvalue());
                    currentDetail.setNumvalue(r.getNumvalue());
                    currentDetail.setStrvalue(r.getStrvalue());
                    currentDetail.setRemark(r.getRemark());
                    this.doConfirmDetail();
                }
            }
        }
    }

    @Override
    public void init() {
        this.superEJB = itemProcessBean;
        setModel(new ItemProcessModel(itemProcessBean));
        this.detailEJB = itemResourceBean;
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
                        for (ItemResource detail : this.editedDetailList) {
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ItemResource detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ItemResource detail : this.addedDetailList) {
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
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("itemno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemmaster.itemdesc", queryName);
            }
        }
    }

    @Override
    protected void reportInitAndConfig() {
        super.reportInitAndConfig();
        reportEngineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, ItemProcessReport.class.getClassLoader());
    }

    @Override
    public void setDetailList(List<ItemResource> detailList) {
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
        for (ItemResource r : detailList) {
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
    public List<ItemResource> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(List<ItemResource> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the processes
     */
    public List<ItemResource> getProcesses() {
        return processes;
    }

    /**
     * @param processes the processes to set
     */
    public void setProcesses(List<ItemResource> processes) {
        this.processes = processes;
    }

    /**
     * @return the materials
     */
    public List<ItemResource> getMaterials() {
        return materials;
    }

    /**
     * @param materials the materials to set
     */
    public void setMaterials(List<ItemResource> materials) {
        this.materials = materials;
    }

    /**
     * @return the hurmans
     */
    public List<ItemResource> getHurmans() {
        return hurmans;
    }

    /**
     * @param hurmans the hurmans to set
     */
    public void setHurmans(List<ItemResource> hurmans) {
        this.hurmans = hurmans;
    }

}

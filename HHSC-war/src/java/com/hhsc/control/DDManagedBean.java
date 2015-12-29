/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.birt.FactoryOrderBIRT;
import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.ejb.FactoryOrderDetailBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import com.hhsc.entity.ItemDesign;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.DDModel;
import com.hhsc.web.SuperMultiBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "ddManagedBean")
@SessionScoped
public class DDManagedBean extends SuperMultiBean<FactoryOrder, FactoryOrderDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;
    @EJB
    private SystemUserBean systemUserBean;

    private List<SystemUser> systemUserList;

    private String designid;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public DDManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setSalesman(userManagedBean.getCurrentUser());
        this.newEntity.setSalesstatus("N");
        this.newEntity.setJhstatus("N");
        this.newEntity.setJhreaded(Boolean.FALSE);
        this.newEntity.setHgstatus("N");
        this.newEntity.setHgreaded(Boolean.FALSE);
        this.newEntity.setZbstatus("N");
        this.newEntity.setZbreaded(Boolean.FALSE);
        this.newEntity.setPsstatus("N");
        this.newEntity.setPsreaded(Boolean.FALSE);
        this.newEntity.setYhstatus("N");
        this.newEntity.setYhreaded(Boolean.FALSE);
        this.newEntity.setZhstatus("N");
        this.newEntity.setZhreaded(Boolean.FALSE);
        this.newEntity.setCkstatus("N");
        this.newEntity.setCkreaded(Boolean.FALSE);
        this.newEntity.setCpstatus("N");
        this.newEntity.setCpreaded(Boolean.FALSE);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setSeq(getMaxSeq(this.detailList));
        this.newDetail.setDesignid("");
        this.newDetail.setItemno("itemno");
        this.newDetail.setSuitqty(0);
        this.newDetail.setMeterqty(BigDecimal.ZERO);
        this.newDetail.setJhqty(BigDecimal.ZERO);
        this.newDetail.setInqty(BigDecimal.ZERO);
        this.newDetail.setDeliverdate(this.getDate());
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.currentSysprg != null) {
            String formid = "";
            if (this.currentSysprg.getNoauto()) {
                formid = this.superEJB.getFormId(newEntity.getFormdate(), this.currentSysprg.getNolead(), this.currentSysprg.getNoformat(), this.currentSysprg.getNoseqlen());
            }
            this.newEntity.setFormid(formid);
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (FactoryOrderDetail detail : this.addedDetailList) {
                    detail.setPformid(formid);
                    detail.setDesignid(this.newEntity.getDesignid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (FactoryOrderDetail detail : this.editedDetailList) {
                    detail.setPformid(formid);
                    detail.setDesignid(this.newEntity.getDesignid());
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
                for (FactoryOrderDetail detail : this.addedDetailList) {
                    detail.setPformid(this.currentEntity.getFormid());
                    detail.setDesignid(this.currentEntity.getDesignid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (FactoryOrderDetail detail : this.editedDetailList) {
                    detail.setPformid(this.currentEntity.getFormid());
                    detail.setDesignid(this.currentEntity.getDesignid());
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemDesign entity = (ItemDesign) event.getObject();
            this.currentEntity.setDesignid(entity.getDesignid());
            this.currentEntity.setOrderimg(entity.getFilename());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemDesign entity = (ItemDesign) event.getObject();
            this.newEntity.setDesignid(entity.getDesignid());
            this.newEntity.setOrderimg(entity.getFilename());
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setOrderimg(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setOrderimg(fileName);
        }
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setModel(new DDModel(factoryOrderBean, userManagedBean));
        getModel().getFilterFields().put("salesstatus", "N");
        setSystemUserList(systemUserBean.findAll());
        super.init();
    }

    @Override
    public void print() throws Exception{

        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据!"));
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("JNDIName", "java:global/HHSC/HHSC-ejb/FactoryOrderBean!com.hhsc.ejb.FactoryOrderBean");
        //设置报表名称
        String reportName = reportPath + "factoryorder.rptdesign";
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportOutputFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportOutputFormat;
        try {
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportOutputFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
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
            if (designid != null && !"".equals(designid)) {
                this.model.getFilterFields().put("designid", designid);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("salesstatus", queryState);
            }
        }
    }

    @Override
    protected void reportInitAndConfig() {
        super.reportInitAndConfig();
        reportEngineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, FactoryOrderBIRT.class.getClassLoader());
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("salesstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null && currentEntity.getSalesstatus() != null && currentEntity.getJhstatus() != null) {
            if ("V".equals(currentEntity.getJhstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getSalesstatus()) {
                    case "V":
                        this.doEdit = currentSysprg.getDoedit() && false;
                        this.doDel = currentSysprg.getDodel() && false;
                        this.doCfm = false;
                        this.doUnCfm = currentSysprg.getDouncfm() && true;
                        break;
                    default:
                        this.doEdit = currentSysprg.getDoedit() && true;
                        this.doDel = currentSysprg.getDodel() && true;
                        this.doCfm = currentSysprg.getDocfm() && true;
                        this.doUnCfm = false;
                }
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setSalesstatus("N");
                    currentEntity.setJhrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("DD");
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setSalesstatus("V");
                    currentEntity.setJhrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setStatus("JH");
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    /**
     * @return the systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * @param systemUserList the systemUserList to set
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * @return the designid
     */
    public String getDesignid() {
        return designid;
    }

    /**
     * @param designid the designid to set
     */
    public void setDesignid(String designid) {
        this.designid = designid;
    }

}

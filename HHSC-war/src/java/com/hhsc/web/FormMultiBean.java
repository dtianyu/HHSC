/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.web;

import com.hhsc.control.UserManagedBean;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Sysprg;
import com.lightshell.comm.FormDetailEntity;
import com.lightshell.comm.FormEntity;
import com.lightshell.comm.FormMultiManagedBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 * @param <V>
 */
public abstract class FormMultiBean<T extends FormEntity, V extends FormDetailEntity> extends FormMultiManagedBean<T, V> {

    @EJB
    protected SysprgBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected String persistenceUnitName;
    protected String appDataPath;
    protected String appImgPath;
    protected Sysprg currentSysprg;

    /**
     * @param entityClass
     * @param detailClass
     */
    public FormMultiBean(Class<T> entityClass, Class<V> detailClass) {
        this.entityClass = entityClass;
        this.detailClass = detailClass;
    }

    @Override
    protected void buildJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildJsonArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        appDataPath = fc.getExternalContext().getInitParameter("com.hhsc.web.appdatapath");
        appImgPath = fc.getExternalContext().getInitParameter("com.hhsc.web.appimgpath");
        reportPath = fc.getExternalContext().getInitParameter("com.hhsc.web.reportpath");
        reportOutputFormat = fc.getExternalContext().getInitParameter("com.hhsc.web.reportoutputformat");
        reportOutputPath = fc.getExternalContext().getInitParameter("com.hhsc.web.reportoutputpath");
        reportViewContext = fc.getExternalContext().getInitParameter("com.hhsc.web.reportviewcontext");
        persistenceUnitName = fc.getExternalContext().getInitParameter("com.hhsc.jpa.unitname");
        int beginIndex = fc.getViewRoot().getViewId().lastIndexOf("/") + 1;
        int endIndex = fc.getViewRoot().getViewId().lastIndexOf(".");
        currentSysprg = sysprgBean.findByAPI(fc.getViewRoot().getViewId().substring(beginIndex, endIndex));
        if (getCurrentSysprg() != null) {
            this.doAdd = getCurrentSysprg().getDoadd();
            this.doPrt = getCurrentSysprg().getDoprt();
        }
        super.construct();
    }

    @Override
    public void create() {
        if (this.detailList != null && !this.detailList.isEmpty()) {
            this.detailList.clear();
        } else {
            this.detailList = new ArrayList<>();
        }
        if (getNewEntity() == null) {
            try {
                T entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCreator(getUserManagedBean().getCurrentUser().getUsername());
                entity.setCredateToNow();
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(FormMultiBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setCurrentEntity(newEntity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            if (this.getCurrentSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getFormdate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen());
                this.newEntity.setFormid(formid);
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (V detail : this.addedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (V detail : this.editedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }

            }
            return true;
        }
        return false;
    }

    @Override
    public String getAppDataPath() {
        return this.appDataPath;
    }

    @Override
    public String getAppImgPath() {
        return this.appImgPath;
    }

    @Override
    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

    @Override
    public void print() throws Exception {
        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据!"));
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentSysprg.getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentSysprg.getRptformat() != null) {
            reportFormat = this.currentSysprg.getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentSysprg.getRptdesign();
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void preview() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.reportViewPath);
    }

    @Override
    public void pull() {

    }

    @Override
    public void push() {
        buildJsonArray();
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentSysprg() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = getCurrentSysprg().getDoedit() && false;
                    this.doDel = getCurrentSysprg().getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = getCurrentSysprg().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentSysprg().getDoedit() && true;
                    this.doDel = getCurrentSysprg().getDodel() && true;
                    this.doCfm = getCurrentSysprg().getDocfm() && true;
                    this.doUnCfm = false;
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
                    currentEntity.setStatus("N");//简化查询条件,此处不再提供修改状态(M)
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(null);
                    currentEntity.setCfmdate(null);
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功!"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "取消前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.toString()));
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
                    currentEntity.setStatus("V");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCfmdateToNow();
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败!"));
                }
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, ex.toString()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    /**
     * @return the currentSysprg
     */
    public Sysprg getCurrentSysprg() {
        return currentSysprg;
    }

}
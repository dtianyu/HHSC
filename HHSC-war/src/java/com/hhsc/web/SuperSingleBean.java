/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.web;

import com.lightshell.comm.SuperEntity;
import com.hhsc.control.UserManagedBean;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Sysprg;
import com.lightshell.comm.SuperSingleManagedBean;
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
 */
public abstract class SuperSingleBean<T extends SuperEntity> extends SuperSingleManagedBean<T> {

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
     */
    public SuperSingleBean(Class<T> entityClass) {
        this.entityClass = entityClass;
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
        if (getNewEntity() == null) {
            T entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
                entity.setCredateToNow();
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperSingleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public String update(String path) {
        try {
            update();
            return path;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setStatus("N");//简化查询条件,此处不再提供修改状态(M)
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
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
                    currentEntity.setStatus("V");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setCfmdateToNow();
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功!"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.web;

import com.lightshell.comm.BaseEntityWithOperate;
import com.hhsc.control.UserManagedBean;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Sysprg;
import com.lightshell.comm.BaseDetailEntity;
import com.lightshell.comm.SuperMultiManagedBean;
import java.util.ArrayList;
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
public abstract class SuperMultiBean<T extends BaseEntityWithOperate, V extends BaseDetailEntity> extends SuperMultiManagedBean<T, V> {

    @EJB
    protected SysprgBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    private String appDataPath;
    private String appImgPath;
    protected Sysprg currentSysprg;

    /**
     * @param entityClass
     * @param detailClass
     */
    public SuperMultiBean(Class<T> entityClass, Class<V> detailClass) {
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
        int beginIndex = fc.getViewRoot().getViewId().lastIndexOf("/") + 1;
        int endIndex = fc.getViewRoot().getViewId().lastIndexOf(".");
        currentSysprg = sysprgBean.findByAPI(fc.getViewRoot().getViewId().substring(beginIndex, endIndex));
        if (currentSysprg != null) {
            this.doAdd = currentSysprg.getDoadd();
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
                entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
                entity.setCredateToNow();
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperMultiBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String edit(String path) {
        if (currentEntity != null) {
            setDetailList(this.detailEJB.findByPId(currentEntity.getId()));
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
    public String getAppDataPath() {
        return this.appDataPath;
    }

    @Override
    public String getAppImgPath() {
        return this.appImgPath;
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
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getStatus() != null) {
                switch (currentEntity.getStatus()) {
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
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            if (doBeforeUnverify()) {
                try {
                    currentEntity.setStatus("N");//简化查询条件,此处不再提供修改状态(M)
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(null);
                    currentEntity.setCfmdate(null);
                    update();
                    doAfterUnverify();
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
            }
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            if (doBeforeVerify()) {
                try {
                    currentEntity.setStatus("V");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                    currentEntity.setCfmdateToNow();
                    update();
                    doAfterVerify();
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
            }
        }
    }

    @Override
    public String view(String path) {
        if (currentEntity != null) {
            setDetailList(this.detailEJB.findByPId(currentEntity.getId()));
            if (this.detailList == null) {
                if (this.detailList == null) {
                    this.detailList = new ArrayList<>();
                }
            }
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择查看数据！"));
            return "";
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

}

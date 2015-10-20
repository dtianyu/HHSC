/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.DDModel;
import com.hhsc.web.SuperOperateBean;
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
@ManagedBean(name = "ddManagedBean")
@SessionScoped
public class DDManagedBean extends SuperOperateBean<SalesOrder> {

    @EJB
    private SalesOrderBean salesOrderBean;
    @EJB
    private SystemUserBean systemUserBean;

    private List<SystemUser> systemUserList;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public DDManagedBean() {
        super(SalesOrder.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setOrderdate(getDate());
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
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setOrderimg(fileName);
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
    public void init() {
        setSuperEJB(salesOrderBean);
        setModel(new DDModel(salesOrderBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        setSystemUserList(systemUserBean.findAll());
        super.init();
    }

    @Override
    public void pull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getSalesstatus() != null && currentEntity.getJhstatus() != null) {
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
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setSalesstatus("M");
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setSalesstatus("V");
                currentEntity.setJhrecdate(getDate());
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
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

}

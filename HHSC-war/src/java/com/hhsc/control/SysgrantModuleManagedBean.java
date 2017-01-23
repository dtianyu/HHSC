/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysGrantModuleBean;
import com.hhsc.ejb.SysmoduleBean;
import com.hhsc.ejb.SystemUserBean;
import com.hhsc.entity.SysGrantModule;
import com.hhsc.entity.Sysmodule;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.SysGrantModuleModel;
import com.hhsc.web.SuperSingleBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "sysgrantModuleManagedBean")
@SessionScoped
public class SysgrantModuleManagedBean extends SuperSingleBean<SysGrantModule> {

    @EJB
    private SysGrantModuleBean sysgrantModuleBean;
    @EJB
    private SystemUserBean systemUserBean;
    @EJB
    private SysmoduleBean sysmoduleBean;

    private List<SystemUser> systemuserList;
    private List<Sysmodule> sysmoduleList;

    /**
     * Creates a new instance of SysgrantModuleManagedBean
     */
    public SysgrantModuleManagedBean() {
        super(SysGrantModule.class);
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
    public void init() {
        setSuperEJB(sysgrantModuleBean);
        setModel(new SysGrantModuleModel(sysgrantModuleBean));
        setSystemuserList(systemUserBean.findAll());
        setSysmoduleList(sysmoduleBean.findAll());
        if (currentEntity == null) {
            currentEntity = newEntity;
        }
        super.init();
    }

    @Override
    public void pull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setToolBar() {

    }

    /**
     * @return the systemuserList
     */
    public List<SystemUser> getSystemuserList() {
        return systemuserList;
    }

    /**
     * @param systemuserList the systemuserList to set
     */
    public void setSystemuserList(List<SystemUser> systemuserList) {
        this.systemuserList = systemuserList;
    }

    /**
     * @return the sysmoduleList
     */
    public List<Sysmodule> getSysmoduleList() {
        return sysmoduleList;
    }

    /**
     * @param sysmoduleList the sysmoduleList to set
     */
    public void setSysmoduleList(List<Sysmodule> sysmoduleList) {
        this.sysmoduleList = sysmoduleList;
    }

}

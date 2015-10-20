/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysmoduleBean;
import com.hhsc.entity.Sysmodule;
import com.hhsc.lazy.SysmoduleModel;
import com.hhsc.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class SysmoduleManagedBean extends SuperOperateBean<Sysmodule> {

    @EJB
    private SysmoduleBean sysmoduleBean;

    /**
     * Creates a new instance of SysmoduleManagedBean
     */
    public SysmoduleManagedBean() {
        super(Sysmodule.class);
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
    public void create() {
        super.create();
        newEntity.setSortid(1);
    }

    @Override
    public void init() {
        this.superEJB = sysmoduleBean;
        setModel(new SysmoduleModel(sysmoduleBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
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
}

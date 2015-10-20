/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysmoduleBean;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Sysmodule;
import com.hhsc.entity.Sysprg;
import com.hhsc.lazy.SysprgModel;
import com.hhsc.web.SuperOperateBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class SysprgManagedBean extends SuperOperateBean<Sysprg> {

    @EJB
    private SysmoduleBean sysmoduleBean;
    @EJB
    private SysprgBean sysprgBean;

    private List<Sysmodule> sysmoduleList;

    public SysprgManagedBean() {
        super(Sysprg.class);
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
        newEntity.setNoauto(Boolean.FALSE);
        newEntity.setNochange(Boolean.FALSE);
        newEntity.setDoadd(true);
        newEntity.setDoedit(true);
        newEntity.setDodel(true);
        newEntity.setDoprt(Boolean.FALSE);
        newEntity.setDocfm(true);
        newEntity.setDouncfm(true);
    }

    @Override
    public void init() {
        this.superEJB = sysprgBean;
        setModel(new SysprgModel(sysprgBean));
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

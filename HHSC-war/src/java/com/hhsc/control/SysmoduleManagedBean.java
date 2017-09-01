/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysmoduleBean;
import com.hhsc.ejb.SystemNameBean;
import com.hhsc.entity.Sysmodule;
import com.hhsc.entity.SystemName;
import com.hhsc.lazy.SysmoduleModel;
import com.hhsc.web.SuperSingleBean;
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
public class SysmoduleManagedBean extends SuperSingleBean<Sysmodule> {

    @EJB
    private SystemNameBean systemNameBean;
    @EJB
    private SysmoduleBean sysmoduleBean;

    private List<SystemName> systemNameList;

    public SysmoduleManagedBean() {
        super(Sysmodule.class);
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
        model.getSortFields().put("sortid", "ASC");
        systemNameList = systemNameBean.findAll();
        super.init();
        if (currentEntity == null) {
            currentEntity = newEntity;
        }
    }

    @Override
    public void query() {
        this.model.getFilterFields().clear();
        if (this.queryName != null && !"".equals(this.queryName)) {
            this.model.getFilterFields().put("name", this.queryName);
        }
        if (this.queryState != null && !"ALL".equals(this.queryState)) {
            this.model.getFilterFields().put("status", this.queryState);
        }
    }

    /**
     * @return the systemNameList
     */
    public List<SystemName> getSystemNameList() {
        return systemNameList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SystemRoleBean;
import com.hhsc.ejb.SystemRoleDetailBean;
import com.hhsc.entity.SystemRole;
import com.hhsc.entity.SystemRoleDetail;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.SystemRoleModel;
import com.hhsc.web.SuperMultiBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "systemRoleManagedBean")
@SessionScoped
public class SystemRoleManagedBean extends SuperMultiBean<SystemRole, SystemRoleDetail> {

    @EJB
    private SystemRoleBean systemRoleBean;

    @EJB
    private SystemRoleDetailBean systemRoleDetailBean;

    public SystemRoleManagedBean() {
        super(SystemRole.class, SystemRoleDetail.class);
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        handleDialogReturnWhenDetailEdit(event);
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            SystemUser u = (SystemUser) event.getObject();
            currentDetail.setSystemUser(u);
        }
    }

    @Override
    public void init() {
        this.superEJB = systemRoleBean;
        this.detailEJB = systemRoleDetailBean;
        setModel(new SystemRoleModel(systemRoleBean));
        super.init();
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = true;
                    break;
                default:
                    this.doEdit = true;
                    this.doDel = true;
                    this.doCfm = true;
                    this.doUnCfm = false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

}

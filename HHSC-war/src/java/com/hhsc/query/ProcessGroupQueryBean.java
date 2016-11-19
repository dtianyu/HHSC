/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProcessGroupBean;
import com.hhsc.entity.ProcessGroup;
import com.hhsc.lazy.ProcessGroupModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "processGroupQueryBean")
@ViewScoped
public class ProcessGroupQueryBean extends SuperQueryBean<ProcessGroup> {

    @EJB
    private ProcessGroupBean processDetailBean;

    public ProcessGroupQueryBean() {
        super(ProcessGroup.class);
    }

    @Override
    public void init() {
        setSuperEJB(processDetailBean);
        setModel(new ProcessGroupModel(processDetailBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("groupno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("name", this.queryName);
            }
        }
    }

}

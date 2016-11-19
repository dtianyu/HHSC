/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProcessResourceBean;
import com.hhsc.entity.ProcessResource;
import com.hhsc.lazy.ProcessResourceModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "processResourceQueryBean")
@ViewScoped
public class ProcessResourceQueryBean extends SuperQueryBean<ProcessResource> {

    @EJB
    private ProcessResourceBean processResourceBean;

    public ProcessResourceQueryBean() {
        super(ProcessResource.class);
    }

    @Override
    public void init() {
        setSuperEJB(processResourceBean);
        setModel(new ProcessResourceModel(processResourceBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("process.processno", this.queryFormId);
            }
            if (this.queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("content", this.queryName);
            }
        }
    }

}

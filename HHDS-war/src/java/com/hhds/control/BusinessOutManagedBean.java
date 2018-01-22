/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "businessOutManagedBean")
@SessionScoped
public class BusinessOutManagedBean extends BusinessTransactionManagedBean {

    public BusinessOutManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormtype("PQT");
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setBillno(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setBillno(fileName);
        }
    }

    @Override
    public void init() {
        super.init();
        this.model.getFilterFields().put("formtype", "P");
    }

    @Override
    public void query() {
        super.query();
        this.model.getFilterFields().put("formtype", "P");
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("formtype", "P");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemDesignBean;
import com.hhsc.entity.ItemDesign;
import com.hhsc.lazy.ItemDesignModel;
import com.hhsc.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class ItemDesignManagedBean extends SuperSingleBean<ItemDesign> {

    @EJB
    private ItemDesignBean itemDesignBean;

    public ItemDesignManagedBean() {
        super(ItemDesign.class);
    }

    @Override
    public void init() {
        this.superEJB = itemDesignBean;
        setModel(new ItemDesignModel(itemDesignBean));
        super.init();
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setFilename(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setFilename(fileName);
        }
    }

}

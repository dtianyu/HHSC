/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.AreaBean;
import com.hhds.entity.Area;
import com.hhds.lazy.AreaModel;
import com.hhds.web.SuperSingleBean;
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
public class AreaManagedBean extends SuperSingleBean<Area> {

    @EJB
    private AreaBean areaBean;
    
    protected List<Area> areaList;
   
    public AreaManagedBean() {
        super(Area.class);
    }

    @Override
    public void init() {
        this.superEJB = areaBean;
        setModel(new AreaModel(areaBean));
        this.areaList = areaBean.findAll();
        super.init(); 
    }   

    /**
     * @return the areaList
     */
    public List<Area> getAreaList() {
        return areaList;
    }

    /**
     * @param areaList the areaList to set
     */
    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ProductionOrderBean;
import com.hhsc.entity.ProductionOrder;
import com.hhsc.entity.ProductionOrderDetail;
import com.hhsc.entity.ProductionResource;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class ProductionOrderReport extends SuperMultiReportBean<ProductionOrderBean, ProductionOrder, ProductionOrderDetail> {
    
    public ProductionOrderReport() {
        
    }
    
    @Override
    public List<ProductionOrderDetail> getDetail(Object value) throws Exception {
        superEJB.setDetail(value);
        return superEJB.getDetailList();
    }
    
    public List<ProductionResource> getResource(Object value) throws Exception {
        superEJB.setResource(value);
        return superEJB.getResourceList();
    }
    
    @Override
    public ProductionOrder getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }
    
}

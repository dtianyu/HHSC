/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ProductionOrderDetailForQueryBean;
import com.hhsc.entity.ProductionOrderDetailForQuery;
import com.lightshell.comm.SuperSingleReportBean;

/**
 *
 * @author kevindong
 */
public class ProductionStatusReport extends SuperSingleReportBean<ProductionOrderDetailForQueryBean, ProductionOrderDetailForQuery> {

    public ProductionStatusReport() {

    }

    @Override
    public ProductionOrderDetailForQuery getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

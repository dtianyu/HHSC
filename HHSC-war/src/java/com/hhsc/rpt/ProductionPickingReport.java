/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ProductionPickingBean;
import com.hhsc.entity.ProductionPicking;
import com.hhsc.entity.ProductionPickingDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class ProductionPickingReport extends SuperMultiReportBean<ProductionPickingBean, ProductionPicking, ProductionPickingDetail> {

    public ProductionPickingReport() {

    }

    @Override
    public List<ProductionPickingDetail> getDetail(Object value) throws Exception {
        superEJB.setDetail(value);
        return superEJB.getDetailList();
    }

    @Override
    public ProductionPicking getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

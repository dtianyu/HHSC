/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ShipmentPackingBean;
import com.hhsc.entity.ShipmentPacking;
import com.hhsc.entity.ShipmentPackingDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class ShipmentPackingReport extends SuperMultiReportBean<ShipmentPackingBean, ShipmentPacking, ShipmentPackingDetail> {

    @Override
    public List<ShipmentPackingDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public ShipmentPacking getEntity(int i) throws Exception {
        ShipmentPacking entity = (ShipmentPacking) superEJB.findById(i);
        return entity;
    }

}

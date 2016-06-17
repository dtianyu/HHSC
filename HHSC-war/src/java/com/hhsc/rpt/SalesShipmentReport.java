/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.SalesShipmentBean;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class SalesShipmentReport extends SuperMultiReportBean<SalesShipmentBean, SalesShipment, SalesShipmentDetail> {

    @Override
    public List<SalesShipmentDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public SalesShipment getEntity(int i) throws Exception {
        SalesShipment entity = (SalesShipment) superEJB.findById(i);
        return entity;
    }

}

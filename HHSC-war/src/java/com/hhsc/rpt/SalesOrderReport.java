/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SalesOrderDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class SalesOrderReport extends SuperMultiReportBean<SalesOrderBean, SalesOrder, SalesOrderDetail> {

    @Override
    public List<SalesOrderDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public SalesOrder getEntity(int i) throws Exception {
        SalesOrder entity = (SalesOrder) superEJB.findById(i);
        return entity;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.rpt;

import com.hhds.ejb.SalesOrderBean;
import com.hhds.entity.SalesOrder;
import com.hhds.entity.SalesOrderDetail;
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

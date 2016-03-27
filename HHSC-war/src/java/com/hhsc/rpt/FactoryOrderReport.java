/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class FactoryOrderReport extends SuperMultiReportBean<FactoryOrderBean, FactoryOrder, FactoryOrderDetail> {

    public FactoryOrderReport() {

    }

    @Override
    public List<FactoryOrderDetail> getDetail(int value) throws Exception {
        superEJB.setDetail((Object) value);
        return superEJB.getDetailList();
    }

    @Override
    public FactoryOrder getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

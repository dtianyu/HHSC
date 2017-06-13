/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.InvoiceDetailForQueryBean;
import com.hhsc.entity.InvoiceDetailForQuery;
import com.lightshell.comm.SuperSingleReportBean;

/**
 *
 * @author kevindong
 */
public class InvoiceDetailForQueryReport extends SuperSingleReportBean<InvoiceDetailForQueryBean, InvoiceDetailForQuery> {

    @Override
    public InvoiceDetailForQuery getEntity(int i) throws Exception {
        InvoiceDetailForQuery entity = (InvoiceDetailForQuery) superEJB.findById(i);
        return entity;
    }

}

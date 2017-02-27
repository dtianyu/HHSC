/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.SalesTransactionBean;
import com.hhsc.entity.SalesTransaction;
import com.lightshell.comm.SuperSingleReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class SalesTransactionQueryReport extends SuperSingleReportBean<SalesTransactionBean, SalesTransaction> {

    @Override
    public SalesTransaction getEntity(int i) throws Exception {
        SalesTransaction entity = (SalesTransaction) superEJB.findById(i);
        return entity;
    }

    public List<SalesTransaction> findByNotInvoice(String filters, String sorts) {
        return superEJB.findByNotInvoice(filters, sorts);
    }

}

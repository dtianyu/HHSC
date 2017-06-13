/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.InvoiceBean;
import com.hhsc.entity.Invoice;
import com.hhsc.entity.InvoiceDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class InvoiceReport extends SuperMultiReportBean<InvoiceBean, Invoice, InvoiceDetail> {

    @Override
    public List<InvoiceDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public Invoice getEntity(int i) throws Exception {
        Invoice entity = (Invoice) superEJB.findById(i);
        return entity;
    }

}

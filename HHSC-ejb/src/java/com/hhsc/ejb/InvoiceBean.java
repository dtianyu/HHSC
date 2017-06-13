/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.Invoice;
import com.hhsc.entity.InvoiceDetail;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class InvoiceBean extends SuperBean<Invoice> {

    @EJB
    private InvoiceDetailBean invoiceDetailBean;

    private List<InvoiceDetail> detailList;

    public InvoiceBean() {
        super(Invoice.class);
    }

    @Override
    public void setDetail(Object value) {
        this.detailList = invoiceDetailBean.findByPId(value);
    }

    /**
     * @return the detailList
     */
    public List<InvoiceDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<InvoiceDetail> detailList) {
        this.detailList = detailList;
    }

}

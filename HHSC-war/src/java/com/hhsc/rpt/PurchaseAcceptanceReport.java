/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.PurchaseAcceptanceBean;
import com.hhsc.entity.PurchaseAcceptance;
import com.hhsc.entity.PurchaseAcceptanceDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class PurchaseAcceptanceReport extends SuperMultiReportBean<PurchaseAcceptanceBean, PurchaseAcceptance, PurchaseAcceptanceDetail> {

    @Override
    public List<PurchaseAcceptanceDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public PurchaseAcceptance getEntity(int i) throws Exception {
        PurchaseAcceptance entity = (PurchaseAcceptance) superEJB.findById(i);
        return entity;
    }

}

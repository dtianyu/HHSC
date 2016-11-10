/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ItemTransactionBean;
import com.hhsc.entity.ItemTransaction;
import com.hhsc.entity.ItemTransactionDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class ItemTransactionReport extends SuperMultiReportBean<ItemTransactionBean, ItemTransaction, ItemTransactionDetail> {

    public ItemTransactionReport() {

    }

    @Override
    public List<ItemTransactionDetail> getDetail(Object value) throws Exception {
        superEJB.setDetail(value);
        return superEJB.getDetailList();
    }

    @Override
    public ItemTransaction getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

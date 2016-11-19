/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ItemExchangeBean;
import com.hhsc.entity.ItemExchange;
import com.lightshell.comm.SuperSingleReportBean;

/**
 *
 * @author kevindong
 */
public class ItemExchangeReport extends SuperSingleReportBean<ItemExchangeBean, ItemExchange> {

    public ItemExchangeReport() {

    }

    @Override
    public ItemExchange getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

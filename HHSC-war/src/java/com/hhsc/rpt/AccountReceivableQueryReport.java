/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.AccountReceivableBean;
import com.hhsc.entity.AccountReceivable;
import com.lightshell.comm.SuperSingleReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class AccountReceivableQueryReport extends SuperSingleReportBean<AccountReceivableBean, AccountReceivable> {

    @Override
    public AccountReceivable getEntity(int i) throws Exception {
        AccountReceivable entity = (AccountReceivable) superEJB.findById(i);
        return entity;
    }

    public List<AccountReceivable> findByFilters(String filters, String sorts) {
        return this.findByFilters(filters, sorts, true);
    }

}

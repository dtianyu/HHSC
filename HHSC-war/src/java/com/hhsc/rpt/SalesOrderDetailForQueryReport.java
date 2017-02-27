/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.SalesOrderDetailForQueryBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.SuperSingleReportBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kevindong
 */
public class SalesOrderDetailForQueryReport extends SuperSingleReportBean<SalesOrderDetailForQueryBean, SalesOrderDetailForQuery> {

    @Override
    public SalesOrderDetailForQuery getEntity(int i) throws Exception {
        SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) superEJB.findById(i);
        return entity;
    }

    public List<SalesOrderDetailForQuery> findByNotDelivery(String filterString, String sortString) {
        Map<String, Object> filters = null;
        Map<String, String> sorts = null;
        if (filterString != null && !"".equals(filterString)) {
            filters = BaseLib.createHashMap(filterString, true);
        }
        if (sortString != null && !"".equals(sortString)) {
            sorts = BaseLib.createLinkedHashMap(sortString);
        }
        return superEJB.findByNotDelivery(filters, sorts);
    }

}

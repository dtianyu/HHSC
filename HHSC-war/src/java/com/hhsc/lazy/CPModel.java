/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.entity.SalesOrder;
import com.lightshell.comm.BaseLazyModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class CPModel extends BaseLazyModel<SalesOrder> {

    private SalesOrderBean salesOrderBean;
    private Map<String, Object> filter;
    private Map<String, String> order;

    public CPModel(SalesOrderBean superEJB) {
        this.superEJB = superEJB;
        salesOrderBean = superEJB;
        filter = new HashMap<>();
        filter.put("ckstatus", "V");
        order = new HashMap<>();
        order.put("cpstatus", "ASC");
        order.put("id", "DESC");
    }

    @Override
    public List<SalesOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setDataList(salesOrderBean.findByFilter(filter, first, pageSize, order));
        setRowCount(salesOrderBean.getRowCountByFilter(filter));
        return this.dataList;
    }

}

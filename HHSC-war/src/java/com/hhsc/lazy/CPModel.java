/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.lightshell.comm.BaseLazyModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class CPModel extends BaseLazyModel<FactoryOrder> {

    private FactoryOrderBean factoryOrderBean;
    private Map<String, Object> filter;
    private Map<String, String> order;

    public CPModel(FactoryOrderBean superEJB) {
        this.superEJB = superEJB;
        factoryOrderBean = superEJB;
        filter = new HashMap<>();
        filter.put("ckstatus", "V");
        order = new HashMap<>();
        order.put("cpstatus", "ASC");
        order.put("id", "DESC");
    }

    @Override
    public List<FactoryOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setDataList(factoryOrderBean.findByFilter(filter, first, pageSize, order));
        setRowCount(factoryOrderBean.getRowCountByFilter(filter));
        return this.dataList;
    }

}

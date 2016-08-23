/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.SalesOrderDetailForQuery;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class SalesOrderDetailForShipModel extends BaseLazyModel<SalesOrderDetailForQuery> {

    public SalesOrderDetailForShipModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<SalesOrderDetailForQuery> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("salesOrder.formid", "ASC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

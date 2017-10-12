/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhds.entity.SalesOrderDetailForQuery;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class SalesOrderDetailForQueryModel extends BaseLazyModel<SalesOrderDetailForQuery> {

    public SalesOrderDetailForQueryModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<SalesOrderDetailForQuery> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

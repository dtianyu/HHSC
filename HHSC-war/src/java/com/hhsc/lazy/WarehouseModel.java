/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.Warehouse;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class WarehouseModel extends BaseLazyModel<Warehouse> {

    public WarehouseModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<Warehouse> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("id", "DESC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

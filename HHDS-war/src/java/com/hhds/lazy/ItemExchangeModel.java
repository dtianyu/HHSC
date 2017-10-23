/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhds.entity.ItemExchange;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ItemExchangeModel extends BaseLazyModel<ItemExchange> {

    public ItemExchangeModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<ItemExchange> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("formid", "DESC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

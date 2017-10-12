/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.ItemTransaction;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ItemTransactionModel extends BaseLazyModel<ItemTransaction> {

    public ItemTransactionModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<ItemTransaction> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("formid", "DESC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

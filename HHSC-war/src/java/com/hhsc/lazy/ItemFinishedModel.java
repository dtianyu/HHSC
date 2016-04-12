/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.ItemMaster;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ItemFinishedModel extends BaseLazyModel<ItemMaster> {

    public ItemFinishedModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<ItemMaster> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("id", "DESC");
        this.filterFields.put("itemcategory.category", "100");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

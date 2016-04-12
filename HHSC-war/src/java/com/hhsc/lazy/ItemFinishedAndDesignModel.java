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
public class ItemFinishedAndDesignModel extends BaseLazyModel<ItemMaster> {

    private ArrayList category;

    public ItemFinishedAndDesignModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
        category = new ArrayList();
        category.add("100");
        category.add("200");
    }

    @Override
    public List<ItemMaster> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("id", "DESC");
        this.filterFields.put("itemcategory.category IN ", category);
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

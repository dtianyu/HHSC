/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.FactoryOrderDetailForStorage;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class FactoryOrderDetailForStorageModel extends BaseLazyModel<FactoryOrderDetailForStorage> {

    public FactoryOrderDetailForStorageModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<FactoryOrderDetailForStorage> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("pformid", "ASC");
        return super.load(first, pageSize, sortField, sortOrder, filters); 
    }
    
    

}

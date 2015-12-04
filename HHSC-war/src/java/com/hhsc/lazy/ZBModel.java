/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.lightshell.comm.BaseLazyModel;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ZBModel extends BaseLazyModel<FactoryOrder> {

    public ZBModel(FactoryOrderBean superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<FactoryOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.filterFields.put("jhstatus", "V"); 
        this.filterFields.put("hgstatus", "V");       
        this.sortFields.put("zbstatus", "ASC");
        this.sortFields.put("id", "DESC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

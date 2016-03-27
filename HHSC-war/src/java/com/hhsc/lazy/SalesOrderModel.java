/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.control.UserManagedBean;
import com.hhsc.entity.SalesOrder;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class SalesOrderModel extends BaseLazyModel<SalesOrder> {

    private UserManagedBean userManagedBean;

    public SalesOrderModel(SuperEJB superEJB, UserManagedBean userManagedBean) {
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
    }

    @Override
    public List<SalesOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("formid", "DESC");
        if (userManagedBean != null) {
            if (!userManagedBean.getCurrentUser().getSuperuser()) {
                this.filterFields.put("salesman.id", userManagedBean.getCurrentUser().getId());
            }
            setDataList(superEJB.findByFilters(this.filterFields, first, pageSize, this.sortFields));
            setRowCount(superEJB.getRowCount(this.filterFields));
        }
        return this.dataList;
    }

}

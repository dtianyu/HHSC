/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.control.UserManagedBean;
import com.hhsc.ejb.SalesOrderBean;
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
public class DDModel extends BaseLazyModel<SalesOrder> {

    private UserManagedBean userManagedBean;
    private SalesOrderBean salesOrderBean;

    public DDModel(SalesOrderBean superEJB, UserManagedBean userManagedBean) {
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
        this.salesOrderBean = superEJB;
    }

    @Override
    public List<SalesOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (userManagedBean != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                setDataList(superEJB.findAll(first, pageSize));
                setRowCount(superEJB.getRowCount());
            } else {
                setDataList(salesOrderBean.findBySalesman(userManagedBean.getCurrentUser().getId(), first, pageSize));
                setRowCount(salesOrderBean.getRowCountBySalesman(userManagedBean.getCurrentUser().getId()));
            }
        }
        return this.dataList;
    }

}

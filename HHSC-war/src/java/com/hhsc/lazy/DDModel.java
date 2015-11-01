/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.control.UserManagedBean;
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
public class DDModel extends BaseLazyModel<FactoryOrder> {

    private UserManagedBean userManagedBean;
    private FactoryOrderBean factoryOrderBean;

    public DDModel(FactoryOrderBean superEJB, UserManagedBean userManagedBean) {
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
        this.factoryOrderBean = superEJB;
    }

    @Override
    public List<FactoryOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (userManagedBean != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                setDataList(superEJB.findAll(first, pageSize));
                setRowCount(superEJB.getRowCount());
            } else {
                setDataList(factoryOrderBean.findBySalesman(userManagedBean.getCurrentUser().getId(), first, pageSize));
                setRowCount(factoryOrderBean.getRowCountBySalesman(userManagedBean.getCurrentUser().getId()));
            }
        }
        return this.dataList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.PurchaseDetail;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class PurchaseDetailModel extends BaseLazyModel<PurchaseDetail> {

    public PurchaseDetailModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<PurchaseDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("status", "ASC");
        this.sortFields.put("purchaseOrder.formid", "DESC");
        this.sortFields.put("seq", "ASC");
        this.filterFields.put("purchaseOrder.status", "V");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

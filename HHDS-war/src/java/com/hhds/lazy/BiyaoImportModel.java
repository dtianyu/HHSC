/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhds.entity.BiyaoImport;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class BiyaoImportModel extends BaseLazyModel<BiyaoImport> {

    private BigDecimal totalQty;
    private BigDecimal totalAmts;

    public BiyaoImportModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<BiyaoImport> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        super.load(first, pageSize, sortField, sortOrder, filters);
        totalQty = BigDecimal.ZERO;
        totalAmts = BigDecimal.ZERO;
        if (dataList != null && !dataList.isEmpty()) {
            dataList.stream().forEach((e) -> {
                totalQty = totalQty.add(e.getQty());
                totalAmts = totalAmts.add(e.getAmts());
            });
        }
        return dataList;
    }

    /**
     * @return the totalQty
     */
    public BigDecimal getTotalQty() {
        return totalQty;
    }

    /**
     * @return the totalAmts
     */
    public BigDecimal getTotalAmts() {
        return totalAmts;
    }

}

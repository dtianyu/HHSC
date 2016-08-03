/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.Process;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ProcessModel extends BaseLazyModel<Process> {

    public ProcessModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    @Override
    public List<Process> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.sortFields.put("sortid", "ASC");
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }

}

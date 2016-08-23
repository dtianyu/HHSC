/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ItemProcessBean;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.ItemResource;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class ItemProcessReport extends SuperMultiReportBean<ItemProcessBean, ItemProcess, ItemResource> {

    public ItemProcessReport() {

    }

    @Override
    public List<ItemResource> getDetail(Object value) throws Exception {
        superEJB.setDetail(value);
        return superEJB.getDetailList();
    }

    @Override
    public ItemProcess getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

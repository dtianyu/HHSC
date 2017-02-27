/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.ItemFrameBean;
import com.hhsc.entity.ItemFrame;
import com.lightshell.comm.SuperSingleReportBean;

/**
 *
 * @author kevindong
 */
public class ItemFrameReport extends SuperSingleReportBean<ItemFrameBean, ItemFrame> {

    public ItemFrameReport() {

    }

    @Override
    public ItemFrame getEntity(int i) throws Exception {
        return superEJB.findById(i);
    }

}

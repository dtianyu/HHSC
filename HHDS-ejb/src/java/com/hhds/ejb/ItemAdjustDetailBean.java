/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.ItemAdjustDetail;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemAdjustDetailBean extends SuperBean<ItemAdjustDetail> {

    public ItemAdjustDetailBean() {
        super(ItemAdjustDetail.class);
    }

}

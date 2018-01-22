/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.ItemColor;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class ItemColorModel extends BaseLazyModel<ItemColor> {

    public ItemColorModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.control.UserManagedBean;
import com.hhsc.entity.ShipmentPacking;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class ShipmentPackingModel extends BaseLazyModel<ShipmentPacking> {

    public ShipmentPackingModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.SalesContract;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class SalesContractModel extends BaseLazyModel<SalesContract> {

    public SalesContractModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhds.entity.BusinessTransaction;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class BusinessTransactionModel extends BaseLazyModel<BusinessTransaction> {

    public BusinessTransactionModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

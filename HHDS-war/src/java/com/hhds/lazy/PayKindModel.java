/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhds.entity.PayKind;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class PayKindModel extends BaseLazyModel<PayKind>{
    
    public PayKindModel(SuperEJB superEJB){
        this.superEJB = superEJB;
    }
    
}

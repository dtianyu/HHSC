/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.lazy;

import com.hhsc.entity.Unit;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class UnitModel extends BaseLazyModel<Unit>{
    
    public UnitModel(SuperEJB superEJB){
        this.superEJB = superEJB;
    }
    
}

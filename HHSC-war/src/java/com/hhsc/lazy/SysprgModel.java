/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.Sysprg;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class SysprgModel extends BaseLazyModel<Sysprg> {

    public SysprgModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
        this.sortFields.put("sysmodule.sortid", "ASC");
        this.sortFields.put("sortid", "ASC");
    }

}

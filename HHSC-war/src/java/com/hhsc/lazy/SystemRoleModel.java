/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import com.hhsc.entity.SystemRole;

/**
 *
 * @author kevindong
 */
public class SystemRoleModel extends BaseLazyModel<SystemRole> {

    public SystemRoleModel(SuperEJB sessionBean) {
        this.superEJB = sessionBean;
    }

}

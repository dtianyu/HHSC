/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SystemName;
import javax.ejb.Stateless;

/**
 *
 * @author C0160
 */
@Stateless
public class SystemNameBean extends SuperBean<SystemName> {

    public SystemNameBean() {
        super(SystemName.class);
    }

}

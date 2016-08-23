/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.FactoryOrderDetail;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryOrderDetailBean extends SuperBean<FactoryOrderDetail> {

    public FactoryOrderDetailBean() {
        super(FactoryOrderDetail.class);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.birt;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class FactoryOrderBIRT extends ConnectEJB<FactoryOrderBean, FactoryOrder> {

    public FactoryOrderBIRT() {

    }

    @Override
    public FactoryOrder getData(int value) throws Exception {
        FactoryOrder entity = (FactoryOrder) superEJB.findById(value);
        return entity;
    }

    public List<FactoryOrderDetail> getDetail(int value) throws Exception {
        superEJB.setDetail((Object) value);
        return superEJB.getDetailList();
    }

}

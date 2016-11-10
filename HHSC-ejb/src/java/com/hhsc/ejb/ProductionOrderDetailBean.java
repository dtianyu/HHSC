/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ProductionOrderDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ProductionOrderDetailBean extends SuperBean<ProductionOrderDetail> {

    public ProductionOrderDetailBean() {
        super(ProductionOrderDetail.class);
    }

    public List<ProductionOrderDetail> findBySalesOrderFormid(String formid) {
        Query query = this.getEntityManager().createNamedQuery("ProductionOrderDetail.findBySrcformid");
        query.setParameter("srcformid", formid);
        return query.getResultList();
    }

}

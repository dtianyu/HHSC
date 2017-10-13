/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.SalesOrderDetail;
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
public class SalesOrderDetailBean extends SuperBean<SalesOrderDetail> {

    public SalesOrderDetailBean() {
        super(SalesOrderDetail.class);
    }

    public List<SalesOrderDetail> findBySrcformid(String value) {
        Query query = getEntityManager().createNamedQuery("SalesOrderDetail.findBySrcformid");
        query.setParameter("srcformid", value);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

}

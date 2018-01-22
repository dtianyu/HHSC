/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SalesContract;
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
public class SalesContractBean extends SuperBean<SalesContract> {

    public SalesContractBean() {
        super(SalesContract.class);
    }

    public SalesContract findDefault() {
        Query query = getEntityManager().createNamedQuery("SalesContract.findAll");
        List<SalesContract> data = query.getResultList();
        if (data != null && !data.isEmpty()) {
            return data.get(0);
        } else {
            return null;
        }
    }

}

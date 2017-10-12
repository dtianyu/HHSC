/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SalesType;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesTypeBean extends SuperBean<SalesType> {

    public SalesTypeBean() {
        super(SalesType.class);
    }

    @Override
    public SalesType findByFormId(String value) {
        return findByType(value);
    }

    public SalesType findByType(String value) {
        Query query = getEntityManager().createNamedQuery("SalesType.findByType");
        query.setParameter("type", value);
        try {
            Object o = query.getSingleResult();
            return (SalesType) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

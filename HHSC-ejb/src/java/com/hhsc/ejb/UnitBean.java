/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.Unit;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class UnitBean extends SuperBean<Unit> {

    public UnitBean() {
        super(Unit.class);
    }

    @Override
    public Unit findByFormId(String value) {
        return findByUnit(value);
    }

    public Unit findByUnit(String value) {
        Query query = getEntityManager().createNamedQuery("Unit.findByUnit");
        query.setParameter("unit", value);
        try {
            Object o = query.getSingleResult();
            return (Unit) o;
        } catch (Exception ex) {
            return null;
        }
    }

}

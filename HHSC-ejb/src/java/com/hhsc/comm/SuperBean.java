/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.comm;

import com.lightshell.comm.SuperEJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 * @param <T>
 */
public abstract class SuperBean<T> extends SuperEJB<T> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public SuperBean(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public T findByPIdAndSeq(Object pid, int seq) {
        
        Query query = getEntityManager().createNamedQuery(this.className + ".findByPIdAndSeq");
        query.setParameter("pid", pid);
        query.setParameter("seq", seq);
        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}

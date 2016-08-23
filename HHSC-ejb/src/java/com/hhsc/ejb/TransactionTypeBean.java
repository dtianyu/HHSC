/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.TransactionType;
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
public class TransactionTypeBean extends SuperBean<TransactionType> {

    public TransactionTypeBean() {
        super(TransactionType.class);
    }

    public TransactionType findByTrtype(String trtype) {
        Query query = this.getEntityManager().createNamedQuery("TransactionType.findByTrtype");
        query.setParameter("trtype", trtype);
        try {
            Object o = query.getSingleResult();
            return (TransactionType) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<TransactionType> findBySystem(String system) {
        Query query = this.getEntityManager().createNamedQuery("TransactionType.findBySystem");
        query.setParameter("sysid", system);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

}

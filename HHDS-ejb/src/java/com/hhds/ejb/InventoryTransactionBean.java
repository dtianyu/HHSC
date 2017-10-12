/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
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
public class InventoryTransactionBean extends SuperBean<InventoryTransaction> {

    public InventoryTransactionBean() {
        super(InventoryTransaction.class);
    }

    @Override
    public List<InventoryTransaction> findByPId(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<InventoryTransaction> findByFormid(String formid) {
        Query query = this.getEntityManager().createNamedQuery("InventoryTransaction.findByFormid");
        query.setParameter("formid", formid);
        return query.getResultList();
    }

    public InventoryTransaction findByFormidAndSeq(String formid, int seq) {
        Query query = this.getEntityManager().createNamedQuery("InventoryTransaction.findByFormidAndSeq");
        query.setParameter("formid", formid);
        query.setParameter("seq", seq);
        try {
            return (InventoryTransaction) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public void setDefaultValue(InventoryTransaction entity) {
        if (entity.getColorno() == null) {
            entity.setColorno("");
        }
        if (entity.getBrand() == null || entity.getItemmaster().getBbstype().substring(0, 1).equals("0")) {
            entity.setBrand("");
        }
        if (entity.getBatch() == null || entity.getItemmaster().getBbstype().substring(1, 2).equals("0")) {
            entity.setBatch("");
        }
        if (entity.getSn() == null || entity.getItemmaster().getBbstype().substring(2).equals("0")) {
            entity.setSn("");
        }
    }

}

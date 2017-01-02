/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.SalesTransactionAdjust;
import com.lightshell.comm.BaseLib;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesTransactionAdjustBean extends SuperBean<SalesTransactionAdjust> {

    public SalesTransactionAdjustBean() {
        super(SalesTransactionAdjust.class);
    }

    public SalesTransactionAdjust findByFormidAndSeq(Object formid, int seq) {
        Query query = this.getEntityManager().createNamedQuery("SalesTransactionAdjust.findByFormidAndSeq");
        query.setParameter("formid", formid);
        query.setParameter("seq", seq);
        try {
            Object o = query.getSingleResult();
            return (SalesTransactionAdjust) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<SalesTransactionAdjust> findByNotInvoice(String filterString, String sortString) {
        Map<String, Object> filters = null;
        Map<String, String> sorts = null;
        if (filterString != null && !"".equals(filterString)) {
            filters = BaseLib.createHashMap(filterString, true);
        }
        if (sortString != null && !"".equals(sortString)) {
            sorts = BaseLib.createLinkedHashMap(sortString);
        }
        return findByFilters(filters, sorts);
    }

}

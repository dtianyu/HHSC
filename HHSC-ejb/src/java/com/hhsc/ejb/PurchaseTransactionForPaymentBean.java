/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseTransaction;
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
public class PurchaseTransactionForPaymentBean extends SuperBean<PurchaseTransaction> {

    public PurchaseTransactionForPaymentBean() {
        super(PurchaseTransaction.class);
    }

    @Override
    public int getRowCount(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(e) FROM PurchaseTransaction e WHERE ((e.puramts + e.taxamts + e.addamts - e.preamts - e.offamts - e.applyamts) > 0)  ");
        sb.append(" AND (e.status<>'PF') ");
        if (filters != null) {
            this.setQueryFilter(sb, filters);
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        if (filters != null) {
            this.setQueryParam(query, filters);
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public List<PurchaseTransaction> findByFilters(Map<String, Object> filters, int first, int pageSize, Map<String, String> orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM PurchaseTransaction e WHERE ((e.puramts + e.taxamts + e.addamts - e.preamts - e.offamts - e.applyamts) > 0) ");
        sb.append(" AND (e.status<>'PF') ");
        if (filters != null) {
            this.setQueryFilter(sb, filters);
        }
        if ((orderBy != null) && (orderBy.size() > 0)) {
            sb.append(" ORDER BY ");
            orderBy.entrySet().forEach((o) -> {
                sb.append(" e.").append(o.getKey()).append(" ").append(o.getValue()).append(",");
            });
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString()).setFirstResult(first).setMaxResults(pageSize);
        //参数赋值
        if (filters != null) {
            this.setQueryParam(query, filters);
        }
        return query.getResultList();
    }

}

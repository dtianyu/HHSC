/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SalesOrderDetailForQuery;
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
public class SalesOrderDetailForProductionBean extends SuperBean<SalesOrderDetailForQuery> {

    public SalesOrderDetailForProductionBean() {
        super(SalesOrderDetailForQuery.class);
    }

    @Override
    public int getRowCount(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(e) FROM SalesOrderDetailForQuery e WHERE ((e.issqty - e.proqty) > 0)  ");
        sb.append(" AND (e.salesOrder.status <>'N') AND (e.status<>'AC') AND (e.status<>'MC') ");
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
    public List<SalesOrderDetailForQuery> findByFilters(Map<String, Object> filters, int first, int pageSize, Map<String, String> orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM SalesOrderDetailForQuery e WHERE ((e.issqty - e.proqty) > 0)  ");
        sb.append(" AND (e.salesOrder.status<>'N') AND (e.status<>'AC') AND (e.status<>'MC') ");
        if (filters != null) {
            this.setQueryFilter(sb, filters);
        }
        if ((orderBy != null) && (orderBy.size() > 0)) {
            sb.append(" ORDER BY ");
            for (Map.Entry<String, String> o : orderBy.entrySet()) {
                sb.append(" e.").append(o.getKey()).append(" ").append(o.getValue()).append(",");
            }
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

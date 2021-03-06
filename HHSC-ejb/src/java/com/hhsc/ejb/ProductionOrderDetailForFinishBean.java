/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ProductionOrderDetailForQuery;
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
public class ProductionOrderDetailForFinishBean extends SuperBean<ProductionOrderDetailForQuery> {

    public ProductionOrderDetailForFinishBean() {
        super(ProductionOrderDetailForQuery.class);
    }

    @Override
    public int getRowCount(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(e) FROM ProductionOrderDetailForQuery e WHERE ((e.orderqty - e.finqty) > 0)  ");
        sb.append(" AND (e.issqty > 0) AND (e.productionOrder.salesstatus='V') ");
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
    public List<ProductionOrderDetailForQuery> findByFilters(Map<String, Object> filters, int first, int pageSize, Map<String, String> orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM ProductionOrderDetailForQuery e WHERE ((e.orderqty - e.finqty) > 0)  ");
        sb.append(" AND (e.issqty > 0) AND (e.productionOrder.salesstatus='V') ");
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

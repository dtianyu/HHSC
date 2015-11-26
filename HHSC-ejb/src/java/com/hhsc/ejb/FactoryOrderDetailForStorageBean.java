/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrderDetailForStorage;
import com.lightshell.comm.SuperEJB;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryOrderDetailForStorageBean extends SuperEJB<FactoryOrderDetailForStorage> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public FactoryOrderDetailForStorageBean() {
        super(FactoryOrderDetailForStorage.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<FactoryOrderDetailForStorage> findByPId(Object value) {
        return super.findByPId(Integer.parseInt(value.toString()));
    }

    @Override
    public int getRowCount(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(e) FROM FactoryOrderDetailForStorage e WHERE ((e.jhqty - e.inqty) > 0)  ");
        sb.append(" AND (e.factoryOrder.ckstatus='N') ");
        if (filters != null) {
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                if (e.getValue().getClass() == String.class) {
                    sb.append(" AND e.").append(e.getKey()).append(" LIKE :").append(e.getKey());
                } else if (e.getValue().getClass() == Date.class && e.getKey().endsWith("Begin")) {
                    sb.append(" AND e.").append(e.getKey().substring(0, e.getKey().indexOf("Begin"))).append(" >= :").append(e.getKey());
                } else if (e.getValue().getClass() == Date.class && e.getKey().endsWith("End")) {
                    sb.append(" AND e.").append(e.getKey().substring(0, e.getKey().indexOf("End"))).append(" <= :").append(e.getKey());
                } else if (e.getKey().contains(".")) {
                    sb.append(" AND e.").append(e.getKey()).append(" = :").append(e.getKey().substring(0, e.getKey().indexOf(".")));
                } else {
                    sb.append(" AND e.").append(e.getKey()).append(" = :").append(e.getKey());
                }
            }
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        if (filters != null) {
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                if ((e.getKey().contains(".")) && (e.getValue().getClass() == String.class)) {
                    query.setParameter(e.getKey().substring(0, e.getKey().indexOf(".")), "%" + e.getValue() + "%");
                } else if ((!e.getKey().contains(".")) && (e.getValue().getClass() == String.class)) {
                    query.setParameter(e.getKey(), "%" + e.getValue() + "%");
                } else if ((e.getKey().contains(".")) && (e.getValue().getClass() != String.class)) {
                    query.setParameter(e.getKey().substring(0, e.getKey().indexOf(".")), e.getValue());
                } else {
                    query.setParameter(e.getKey(), e.getValue());
                }
            }
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public List<FactoryOrderDetailForStorage> findByFilters(Map<String, Object> filters, int first, int pageSize, Map<String, String> orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM FactoryOrderDetailForStorage e WHERE ((e.jhqty - e.inqty) > 0)  ");
        sb.append(" AND e.factoryOrder.ckstatus='N' ");
        if (filters != null) {
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                if (e.getValue().getClass() == String.class) {
                    sb.append(" AND e.").append(e.getKey()).append(" LIKE :").append(e.getKey());
                } else if (e.getValue().getClass() == Date.class && e.getKey().endsWith("Begin")) {
                    sb.append(" AND e.").append(e.getKey().substring(0, e.getKey().indexOf("Begin"))).append(" >= :").append(e.getKey());
                } else if (e.getValue().getClass() == Date.class && e.getKey().endsWith("End")) {
                    sb.append(" AND e.").append(e.getKey().substring(0, e.getKey().indexOf("End"))).append(" <= :").append(e.getKey());
                } else if (e.getKey().contains(".")) {
                    sb.append(" AND e.").append(e.getKey()).append(" = :").append(e.getKey().substring(0, e.getKey().indexOf(".")));
                } else {
                    sb.append(" AND e.").append(e.getKey()).append(" = :").append(e.getKey());
                }
            }
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
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                if ((e.getKey().contains(".")) && (e.getValue().getClass() == String.class)) {
                    query.setParameter(e.getKey().substring(0, e.getKey().indexOf(".")), "%" + e.getValue() + "%");
                } else if ((!e.getKey().contains(".")) && (e.getValue().getClass() == String.class)) {
                    query.setParameter(e.getKey(), "%" + e.getValue() + "%");
                } else if ((e.getKey().contains(".")) && (e.getValue().getClass() != String.class)) {
                    query.setParameter(e.getKey().substring(0, e.getKey().indexOf(".")), e.getValue());
                } else {
                    query.setParameter(e.getKey(), e.getValue());
                }
            }
        }
        return query.getResultList();
    }

}

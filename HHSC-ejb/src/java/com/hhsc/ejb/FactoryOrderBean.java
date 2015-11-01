/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrder;
import com.lightshell.comm.SuperEJB;
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
public class FactoryOrderBean extends SuperEJB<FactoryOrder> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public FactoryOrderBean() {
        super(FactoryOrder.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<FactoryOrder> findBySalesman(int id) {
        Query query = em.createNamedQuery("FactoryOrder.findBySalesman");
        query.setParameter("salesman", id);
        return query.getResultList();
    }

    public List<FactoryOrder> findBySalesman(int id, int first, int size) {
        Query query = em.createNamedQuery("FactoryOrder.findBySalesman").setFirstResult(first).setMaxResults(size);
        query.setParameter("salesman", id);
        return query.getResultList();
    }

    public int getRowCountBySalesman(int id) {
        Query query = em.createNamedQuery("FactoryOrder.getRowCountBySalesman");
        query.setParameter("salesman", id);
        try {
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception e) {
            return 0;
        }

    }

    public List<FactoryOrder> findByFilter(Map<String, Object> filter, int first, int size, Map<String, String> orderBy) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT s FROM FactoryOrder s WHERE 1=1 ");
        boolean flag;
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.entrySet()) {
                flag = true;
                try {
                    FactoryOrder.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    sb.append(" AND s.").append(e.getKey()).append(" = :").append(e.getKey());
                }
            }
        }
        if (orderBy != null) {
            sb.append(" ORDER BY ");
            for (Map.Entry<String, String> e : orderBy.entrySet()) {
                flag = true;
                try {
                    FactoryOrder.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    sb.append(" s.").append(e.getKey()).append(" ").append(e.getValue()).append(",");
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.entrySet()) {
                flag = true;
                try {
                    FactoryOrder.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    query.setParameter(e.getKey(), e.getValue());
                }
            }
        }
        return query.getResultList();
    }

    public int getRowCountByFilter(Map<String, Object> filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(s) FROM FactoryOrder s WHERE 1=1 ");
        boolean flag;
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.entrySet()) {
                flag = true;
                try {
                    FactoryOrder.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    sb.append(" AND s.").append(e.getKey()).append(" = :").append(e.getKey());
                }
            }
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.entrySet()) {
                flag = true;
                try {
                    FactoryOrder.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    query.setParameter(e.getKey(), e.getValue());
                }
            }
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemFrame;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.SalesOrderDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemFrameBean extends SuperBean<ItemFrame> {

    @EJB
    private ItemProcessBean itemProcessBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    public ItemFrameBean() {
        super(ItemFrame.class);
    }

    @Override
    public ItemFrame unverify(ItemFrame entity) {
        try {
            ItemFrame e = getEntityManager().merge(entity);
            if (entity != null && entity.getSrcformid() != null && !"".equals(entity.getSrcformid())) {
                SalesOrderDetail sd = salesOrderDetailBean.findByPIdAndSeq(entity.getSrcformid(), entity.getSrcseq());
                if (sd != null) {
                    sd.setProqty(sd.getProqty().subtract(BigDecimal.valueOf(entity.getDesignsets())));
                    salesOrderDetailBean.update(sd);
                }
            }
            if (entity != null && entity.getItemProcess() != null && "XZ".equals(entity.getFormtype())) {
                ItemProcess ip = itemProcessBean.findById(entity.getItemProcess().getId());
                if (ip != null) {
                    ip.setDesignsets(ip.getDesignsets() - entity.getDesignsets());
                    itemProcessBean.update(ip);
                }
            }            
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemFrame verify(ItemFrame entity) {
        try {
            ItemFrame e = getEntityManager().merge(entity);
            if (entity != null && entity.getSrcformid() != null && !"".equals(entity.getSrcformid())) {
                SalesOrderDetail sd = salesOrderDetailBean.findByPIdAndSeq(entity.getSrcformid(), entity.getSrcseq());
                if (sd != null) {
                    sd.setProqty(sd.getProqty().add(BigDecimal.valueOf(entity.getDesignsets())));
                    salesOrderDetailBean.update(sd);
                }
            }
            if (entity != null && entity.getItemProcess() != null && "XZ".equals(entity.getFormtype())) {
                ItemProcess ip = itemProcessBean.findById(entity.getItemProcess().getId());
                if (ip != null) {
                    ip.setDesignsets(ip.getDesignsets() + entity.getDesignsets());
                    itemProcessBean.update(ip);
                }
            }            
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemFrame> findByItemno(String itemno) {
        Query query = getEntityManager().createNamedQuery("ItemFrame.findByItemno");
        query.setParameter("itemno", itemno);
        return query.getResultList();
    }

}

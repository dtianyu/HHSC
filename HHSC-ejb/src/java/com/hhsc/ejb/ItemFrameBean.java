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
import com.hhsc.entity.SalesOrderDetailForQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @EJB
    private SalesOrderDetailForQueryBean salesOrderDetailForQueryBean;

    @EJB
    private SalesShipmentBean salesShipmentBean;

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
                    if (sd.getProqty().compareTo(BigDecimal.ZERO) == 0) {
                        sd.setQty(sd.getIssqty());
                        sd.setIssqty(BigDecimal.ZERO);
                    } else {
                        sd.setQty(sd.getQty().subtract(BigDecimal.valueOf(entity.getDesignsets())));
                    }
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
                    //更新订单明细中的生产数量
                    if (sd.getProqty().compareTo(BigDecimal.ZERO) == 0) {
                        sd.setIssqty(sd.getQty());
                        sd.setQty(BigDecimal.valueOf(entity.getDesignsets()));
                    } else {
                        sd.setQty(sd.getQty().add(BigDecimal.valueOf(entity.getDesignsets())));
                    }
                    sd.setProqty(sd.getProqty().add(BigDecimal.valueOf(entity.getDesignsets())));
                    salesOrderDetailBean.update(sd);
                    //直接生成出货信息
                    List<SalesOrderDetailForQuery> details = new ArrayList<>();
                    SalesOrderDetailForQuery d = salesOrderDetailForQueryBean.findByPIdAndSeq(entity.getSrcformid(), entity.getSrcseq());
                    if (d != null) {
                        details.add(d);
                    }
                    salesShipmentBean.initShipment("shipmentadvice", details, true);
                }
            }
            if (entity != null && entity.getItemProcess() != null && "XZ".equals(entity.getFormtype())) {
                ItemProcess ip = itemProcessBean.findById(entity.getItemProcess().getId());
                if (ip != null) {
                    if (ip.getFrameid() == null || ip.getFrameid() == 0) {
                        ip.setDesignsets(entity.getDesignsets());
                    } else {
                        ip.setDesignsets(ip.getDesignsets() + entity.getDesignsets());
                    }
                    ip.setFrameid(entity.getId());
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

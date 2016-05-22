/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseOrder;
import com.hhsc.entity.PurchaseOrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseOrderBean extends SuperBean<PurchaseOrder> {

    @EJB
    private PurchaseOrderDetailBean purchaseDetailBean;

    protected List<PurchaseOrderDetail> detailList;

    public PurchaseOrderBean() {
        super(PurchaseOrder.class);
    }

    public void initPurchase(PurchaseOrder p, List<PurchaseOrderDetail> detailList) {
        try {
            getEntityManager().persist(p);
            for (PurchaseOrderDetail d : detailList) {
                d.setPid(p.getFormid());
                purchaseDetailBean.persist(d);
            }
        } catch (Exception e) {
            this.delete(p);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(purchaseDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    /**
     * @return the detailList
     */
    public List<PurchaseOrderDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<PurchaseOrderDetail> detailList) {
        this.detailList = detailList;
    }

}

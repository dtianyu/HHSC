/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseRequest;
import com.hhsc.entity.PurchaseRequestDetail;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SalesOrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesOrderBean extends SuperBean<SalesOrder> {

    @EJB
    private PurchaseRequestBean purchaseRequestBean;

    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    protected List<SalesOrderDetail> detailList;

    public SalesOrderBean() {
        super(SalesOrder.class);
    }

    public boolean hasPurchaseRequest(String formid) {
        return purchaseRequestBean.getRowCountBySalesOrderFormid(formid) != 0;
    }

    public boolean hasProductionOrder(String formid) {
        return !productionOrderDetailBean.findBySalesOrderFormid(formid).isEmpty();
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(salesOrderDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transferToPurchaseRequest(SalesOrder s, List<SalesOrderDetail> detailList, PurchaseRequest p, List<PurchaseRequestDetail> requestList) {
        try {
            purchaseRequestBean.initRequest(p, requestList);
            salesOrderDetailBean.update(detailList);
            update(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the detailList
     */
    public List<SalesOrderDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<SalesOrderDetail> detailList) {
        this.detailList = detailList;
    }

}

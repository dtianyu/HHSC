/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.SalesOrder;
import com.hhds.entity.SalesOrderDetail;
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
    private SalesOrderDetailBean salesOrderDetailBean;
    
    protected List<SalesOrderDetail> detailList;
    
    public SalesOrderBean() {
        super(SalesOrder.class);
    }
    
    public boolean hasPurchaseRequest(String formid) {
        //return purchaseRequestBean.getRowCountBySalesOrderFormid(formid) != 0;
        return false;
    }
    
    public boolean hasProductionOrder(String formid) {
        //return !productionOrderDetailBean.findBySalesOrderFormid(formid).isEmpty();
        return false;
    }
    
    @Override
    public void setDetail(Object value) {
        detailList = salesOrderDetailBean.findByPId(value);
        if (getDetailList() == null) {
            detailList = new ArrayList<>();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(SalesOrder s, List<SalesOrderDetail> detailList) {
        try {
            persist(s);
            for (SalesOrderDetail d : detailList) {
                salesOrderDetailBean.persist(d);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<SalesOrderDetail> getDetailList() {
        return detailList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.PurchaseRequest;
import com.hhsc.entity.PurchaseRequestDetail;
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
public class PurchaseRequestBean extends SuperBean<PurchaseRequest> {

    @EJB
    private PurchaseRequestDetailBean purchaseRequestDetailBean;

    protected List<PurchaseRequestDetail> detailList;

    public PurchaseRequestBean() {
        super(PurchaseRequest.class);
    }

    public int getRowCountBySalesOrderFormid(String formid) {
        return purchaseRequestDetailBean.findBySalesOrderFormid(formid).size();
    }

    @Override
    public PurchaseRequest verify(PurchaseRequest entity) {
        setDetail(entity.getFormid());
        getDetailList().stream().map((d) -> {
            PurchaseRequestDetail prd = null;
            if ("300".equals(d.getPurtype())) {
                prd = purchaseRequestDetailBean.findLastByItemno(d.getItemno());
            } else {
                prd = purchaseRequestDetailBean.findLastByDesignnoAndItemno(d.getDesignno(), d.getItemno());
            }
            if (prd != null) {
                d.setVendor(prd.getVendor());
                d.setVendoritemno(prd.getVendoritemno());
                d.setVendorcolorno(prd.getVendorcolorno());
                d.setCurrency(prd.getCurrency());
                d.setExchange(prd.getExchange());
                d.setTaxtype(prd.getTaxtype());
                d.setTaxkind(prd.getTaxkind());
                d.setTaxrate(prd.getTaxrate());
                d.setPrice(prd.getPrice());
                d.setAmts(d.getQty().multiply(d.getPrice()));
            }
            return d;
        }).forEach((d) -> {
            purchaseRequestDetailBean.update(d);
        });
        return super.verify(entity);
    }

    public void initRequest(PurchaseRequest p, List<PurchaseRequestDetail> detailList) {
        try {
            detailList.stream().map((d) -> {
                d.setPid(p.getFormid());
                return d;
            }).forEach((d) -> {
                purchaseRequestDetailBean.persist(d);
            });
        } catch (Exception e) {
            delete(p);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(purchaseRequestDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    /**
     * @return the detailList
     */
    public List<PurchaseRequestDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<PurchaseRequestDetail> detailList) {
        this.detailList = detailList;
    }
}

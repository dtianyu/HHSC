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

    public void initRequest(PurchaseRequest p, List<PurchaseRequestDetail> detailList) {
        try {
            detailList.stream().map((d) -> {
                d.setPid(p.getFormid());
                if (d.getPurtype() != "100") {
                    PurchaseRequestDetail hd = purchaseRequestDetailBean.findLastByItemno(d.getItemno());
                    if (hd != null) {
                        d.setVendor(hd.getVendor());
                        d.setVendoritemno(hd.getVendoritemno());
                        d.setVendorcolorno(hd.getVendorcolorno());
                        d.setCurrency(hd.getCurrency());
                        d.setExchange(hd.getExchange());
                        d.setTaxtype(hd.getTaxtype());
                        d.setTaxkind(hd.getTaxkind());
                        d.setTaxrate(hd.getTaxrate());
                        d.setPrice(hd.getPrice());
                        d.setAmts(d.getQty().multiply(d.getPrice()));
                    }
                }
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

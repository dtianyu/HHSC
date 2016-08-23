/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.PurchaseOrderBean;
import com.hhsc.entity.PurchaseOrder;
import com.hhsc.entity.PurchaseOrderDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class PurchaseOrderReport extends SuperMultiReportBean<PurchaseOrderBean, PurchaseOrder, PurchaseOrderDetail> {

    @Override
    public List<PurchaseOrderDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public PurchaseOrder getEntity(int i) throws Exception {
        PurchaseOrder entity = (PurchaseOrder) superEJB.findById(i);
        if (entity != null) {
            switch (entity.getPurtype()) {
                case "100":
                    entity.setPurtype("成品");
                    break;
                case "200":
                    entity.setPurtype("印花");
                    break;
                case "300":
                    entity.setPurtype("白坯");
                    break;
            }
            switch (entity.getTaxtype()) {
                case "0":
                    entity.setTaxtype("内含");
                    break;
                case "1":
                    entity.setTaxtype("外加");
                    break;
                case "2":
                    entity.setTaxtype("零税");
                    break;
                case "3":
                    entity.setTaxtype("免税");
                    break;
            }
        }
        return entity;
    }

}

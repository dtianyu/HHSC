/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.rpt;

import com.hhsc.ejb.PurchaseRequestBean;
import com.hhsc.entity.PurchaseRequest;
import com.hhsc.entity.PurchaseRequestDetail;
import com.lightshell.comm.SuperMultiReportBean;
import java.util.List;

/**
 *
 * @author kevindong
 */
public class PurchaseRequestReport extends SuperMultiReportBean<PurchaseRequestBean, PurchaseRequest, PurchaseRequestDetail> {

    @Override
    public List<PurchaseRequestDetail> getDetail(Object i) throws Exception {
        superEJB.setDetail(i);
        return superEJB.getDetailList();
    }

    @Override
    public PurchaseRequest getEntity(int i) throws Exception {
        PurchaseRequest entity = (PurchaseRequest) superEJB.findById(i);
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
        }
        return entity;
    }

}

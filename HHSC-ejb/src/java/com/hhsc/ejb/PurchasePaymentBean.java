/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.AccountReceiptDetail;
import com.hhsc.entity.PurchasePayment;
import com.hhsc.entity.PurchasePaymentDetail;
import com.hhsc.entity.PurchaseTransaction;
import java.math.BigDecimal;
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
public class PurchasePaymentBean extends SuperBean<PurchasePayment> {

    @EJB
    private PurchaseTransactionBean purchaseTransactionBean;

    @EJB
    private PurchasePaymentDetailBean purchasePaymentDetailBean;

    private List<PurchasePaymentDetail> detailList;

    public PurchasePaymentBean() {
        super(PurchasePayment.class);
    }

    @Override
    public PurchasePayment unverify(PurchasePayment entity) {
        PurchaseTransaction pt;
        try {
            PurchasePayment e = getEntityManager().merge(entity);
            detailList = purchasePaymentDetailBean.findByPId(entity.getFormid());
            for (PurchasePaymentDetail detail : detailList) {
                pt = purchaseTransactionBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
                pt.setApplyamts(pt.getApplyamts().subtract(detail.getPayamts()));
                pt.setApplyamt(pt.getApplyamt().subtract(detail.getPayamt()));
                if (pt.getApplyamts().compareTo(BigDecimal.ZERO) == 0) {
                    pt.setStatus("50");
                } else {
                    pt.setStatus("P0");
                }
                purchaseTransactionBean.update(pt);
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PurchasePayment verify(PurchasePayment entity) {
        PurchaseTransaction pt;
        BigDecimal totalAmts;
        try {
            PurchasePayment e = getEntityManager().merge(entity);
            detailList = purchasePaymentDetailBean.findByPId(entity.getFormid());
            for (PurchasePaymentDetail detail : detailList) {
                pt = purchaseTransactionBean.findByFormidAndSeq(detail.getSrcformid(), detail.getSrcseq());
                pt.setApplyamts(pt.getApplyamts().add(detail.getPayamts()));
                pt.setApplyamt(pt.getApplyamt().add(detail.getPayamt()));
                totalAmts = pt.getPuramts().add(pt.getTaxamts());
                if (pt.getApplyamts().compareTo(totalAmts) == 0) {
                    pt.setStatus("PF");
                } else {
                    pt.setStatus("P0");
                }
                purchaseTransactionBean.update(pt);
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

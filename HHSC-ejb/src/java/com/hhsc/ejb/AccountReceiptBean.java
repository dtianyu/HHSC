/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.AccountReceipt;
import com.hhsc.entity.AccountReceiptDetail;
import com.hhsc.entity.AccountReceivable;
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
public class AccountReceiptBean extends SuperBean<AccountReceipt> {

    @EJB
    private AccountReceiptDetailBean accountReceiptDetailBean;

    @EJB
    private AccountReceivableBean accountReceivableBean;

    private List<AccountReceiptDetail> detailList;

    public AccountReceiptBean() {
        super(AccountReceipt.class);
    }

    @Override
    public AccountReceipt unverify(AccountReceipt entity) {
        AccountReceivable ar;
        try {
            AccountReceipt e = getEntityManager().merge(entity);
            detailList = accountReceiptDetailBean.findByPId(entity.getFormid());
            for (AccountReceiptDetail detail : detailList) {
                ar = accountReceivableBean.findById(detail.getSrcseq());
                ar.setRecamts(ar.getRecamts().subtract(detail.getRecamts()));
                ar.setRecamt(ar.getRecamt().subtract(detail.getRecamt()));
                ar.setStatus("V");
                accountReceivableBean.update(ar);
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public AccountReceipt verify(AccountReceipt entity) {
        AccountReceivable ar;
        BigDecimal totalAmts;
        try {
            AccountReceipt e = getEntityManager().merge(entity);
            detailList = accountReceiptDetailBean.findByPId(entity.getFormid());
            for (AccountReceiptDetail detail : detailList) {
                ar = accountReceivableBean.findById(detail.getSrcseq());
                ar.setRecamts(ar.getRecamts().add(detail.getRecamts()));
                ar.setRecamt(ar.getRecamt().add(detail.getRecamt()));
                totalAmts = ar.getExtaxs().add(ar.getTaxess());
                if (totalAmts.compareTo(ar.getRecamts()) == 0) {
                    ar.setStatus("RF");
                }
                accountReceivableBean.update(ar);
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

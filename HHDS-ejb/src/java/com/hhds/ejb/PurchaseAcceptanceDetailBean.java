/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.PurchaseAcceptanceDetail;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseAcceptanceDetailBean extends SuperBean<PurchaseAcceptanceDetail> {

    public PurchaseAcceptanceDetailBean() {
        super(PurchaseAcceptanceDetail.class);
    }

}

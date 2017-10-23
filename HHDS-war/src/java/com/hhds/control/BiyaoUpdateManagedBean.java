/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.SalesOrderBean;
import com.hhds.entity.BiyaoImport;
import com.hhds.entity.SalesOrder;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "biyaoUpdateManagedBean")
@SessionScoped
public class BiyaoUpdateManagedBean extends BiyaoImportManagedBean {

    @EJB
    private SalesOrderBean salesOrderBean;

    /**
     * Creates a new instance of BiyaoUpdateManagedBean
     */
    public BiyaoUpdateManagedBean() {
    }

    @Override
    public void verify() {
        if (addedList == null || addedList.isEmpty()) {
            showErrorMsg("Error", "没有选择明细资料");
            return;
        }
        SalesOrder so;
        try {
            for (BiyaoImport bi : addedList) {
                so = salesOrderBean.findByFormId(bi.getFormid());
                if (so != null) {
                    so.setPaydate(bi.getPaydate());
                    so.setFreight(bi.getFreight());
                    so.setDeliverydate(bi.getDeliverydate());
                    so.setDeliverytype(bi.getDeliveryno());
                    salesOrderBean.update(so);
                }
            }
            addedList.clear();
            showInfoMsg("Info", "更新订单信息成功");
        } catch (Exception ex) {
            showErrorMsg("Error", ex.getMessage());
        }
    }

}

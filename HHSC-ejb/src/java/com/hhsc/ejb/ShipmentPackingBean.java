/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ShipmentPacking;
import com.hhsc.entity.ShipmentPackingDetail;
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
public class ShipmentPackingBean extends SuperBean<ShipmentPacking> {

    @EJB
    private ShipmentPackingDetailBean shipmentPackingDetailBean;

    private List<ShipmentPackingDetail> detailList;

    public ShipmentPackingBean() {
        super(ShipmentPacking.class);
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(shipmentPackingDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    /**
     * @return the detailList
     */
    public List<ShipmentPackingDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ShipmentPackingDetail> detailList) {
        this.detailList = detailList;
    }

}

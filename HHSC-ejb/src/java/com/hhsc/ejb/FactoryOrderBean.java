/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import com.lightshell.comm.SuperEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryOrderBean extends SuperEJB<FactoryOrder> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    protected List<FactoryOrderDetail> detailList;

    public FactoryOrderBean() {
        super(FactoryOrder.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(factoryOrderDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    /**
     * @return the detailList
     */
    public List<FactoryOrderDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<FactoryOrderDetail> detailList) {
        this.detailList = detailList;
    }

}

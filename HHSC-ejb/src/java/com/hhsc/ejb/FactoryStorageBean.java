/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.entity.FactoryOrderDetail;
import com.hhsc.entity.FactoryStorage;
import com.hhsc.entity.FactoryStorageDetail;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class FactoryStorageBean extends SuperEJB<FactoryStorage> {

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;
    @EJB
    private FactoryStorageDetailBean factoryStorageDetailBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;
    @Resource
    protected EJBContext ejbContext;

    public FactoryStorageBean() {
        super(FactoryStorage.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public FactoryStorage unverify(FactoryStorage entity) {
        try {
            FactoryOrderDetail factoryOrderDetail;
            FactoryStorage e = getEntityManager().merge(entity);
            List<FactoryStorageDetail> detailList = factoryStorageDetailBean.findByPId(entity.getId());
            for (FactoryStorageDetail detail : detailList) {
                factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                if (factoryOrderDetail == null) {
                    throw new RuntimeException("找不到来源单号");
                }
                if (factoryOrderDetail.getInqty().compareTo(detail.getQty()) < 0) {
                    throw new RuntimeException(factoryOrderDetail.getPformid() + "可还原数量不足!");
                }
                factoryOrderDetail.setInqty(factoryOrderDetail.getInqty().subtract(detail.getQty()));
                getEntityManager().merge(factoryOrderDetail);
            }
            return e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public FactoryStorage verify(FactoryStorage entity) {
        try {
            FactoryOrderDetail factoryOrderDetail;
            FactoryStorage e = getEntityManager().merge(entity);
            List<FactoryStorageDetail> detailList = factoryStorageDetailBean.findByPId(entity.getId());
            for (FactoryStorageDetail detail : detailList) {
                factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                if (factoryOrderDetail == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                    throw new RuntimeException("找不到来源单号");
                }
                //允许大于计划数
                //if (factoryOrderDetail.getJhqty().subtract(factoryOrderDetail.getInqty()).compareTo(detail.getQty()) < 0) {
                //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                //    throw new RuntimeException("可入库数量不足!");
                //}
                factoryOrderDetail.setInqty(factoryOrderDetail.getInqty().add(detail.getQty()));
                getEntityManager().merge(factoryOrderDetail);
            }
            return e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

}

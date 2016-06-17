/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderDetailBean;
import com.hhsc.ejb.FactoryStorageBean;
import com.hhsc.ejb.FactoryStorageDetailBean;
import com.hhsc.entity.FactoryOrderDetail;
import com.hhsc.entity.FactoryOrderDetailForStorage;
import com.hhsc.entity.FactoryStorage;
import com.hhsc.entity.FactoryStorageDetail;
import com.hhsc.lazy.FactoryStorageModel;
import com.hhsc.web.FormMultiBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "factoryStorageManagedBean")
@SessionScoped
public class FactoryStorageManagedBean extends FormMultiBean<FactoryStorage, FactoryStorageDetail> {

    @EJB
    private FactoryStorageBean factoryStorageBean;
    @EJB
    private FactoryStorageDetailBean factoryStorageDetailBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;

    public FactoryStorageManagedBean() {
        super(FactoryStorage.class, FactoryStorageDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setPlanqty(BigDecimal.ZERO);
        this.newDetail.setAllowqty(BigDecimal.ZERO);
        this.newDetail.setQty(BigDecimal.ZERO);
        this.newDetail.setGoodqty(BigDecimal.ZERO);
        this.newDetail.setDefectqty(BigDecimal.ZERO);
        this.newDetail.setBadqty(BigDecimal.ZERO);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            FactoryOrderDetail factoryOrderDetail;
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (FactoryStorageDetail detail : this.addedDetailList) {
                    detail.setPid(this.currentEntity.getFormid());
                    factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                    if (factoryOrderDetail == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                        return false;
                    } else {
                        //允许大于计划数
                        //if (factoryOrderDetail.getJhqty().subtract(factoryOrderDetail.getInqty()).compareTo(detail.getQty()) == -1) {
                        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                        //    return false;
                        //}
                    }
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (FactoryStorageDetail detail : this.editedDetailList) {
                    detail.setPid(this.currentEntity.getFormid());
                    factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                    if (factoryOrderDetail == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                        return false;
                    } else {
                        //if (factoryOrderDetail.getJhqty().subtract(factoryOrderDetail.getInqty()).compareTo(detail.getQty()) == -1) {
                        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                        //    return false;
                        //}
                    }
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        FactoryOrderDetail factoryOrderDetail;
        for (FactoryStorageDetail detail : this.detailList) {
            try {
                factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                if (factoryOrderDetail == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                    return false;
                }
                if (factoryOrderDetail.getInqty().compareTo(detail.getQty()) < 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可还原数量不足!"));
                    return false;
                }
            } catch (Exception ex) {
                throw ex;
            }
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有入库明细!"));
            return false;
        }
        FactoryOrderDetail factoryOrderDetail;
        for (FactoryStorageDetail detail : this.detailList) {
            try {
                factoryOrderDetail = factoryOrderDetailBean.findById(detail.getSid());
                if (factoryOrderDetail == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "找不到流转单!"));
                    return false;
                }
                //if (factoryOrderDetail.getJhqty().subtract(factoryOrderDetail.getInqty()).compareTo(detail.getQty()) < 0) {
                //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                //    return false;
                //}
            } catch (Exception ex) {
                throw ex;
            }
        }
        return true;
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            FactoryOrderDetailForStorage entity = (FactoryOrderDetailForStorage) event.getObject();
            this.currentDetail.setSid(entity.getId());
            this.currentDetail.setSformid(entity.getPformid());
            this.currentDetail.setSseq(entity.getSeq());
            this.currentDetail.setDesignid(entity.getDesignid());
            this.currentDetail.setItemno(entity.getItemno());
            this.currentDetail.setColorid(entity.getColorno());
            this.currentDetail.setPlanqty(entity.getJhqty());
            this.currentDetail.setAllowqty(entity.getJhqty().subtract(entity.getInqty()));
            this.currentDetail.setQty(entity.getJhqty().subtract(entity.getInqty()));
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        if (event.getObject() != null) {
            FactoryOrderDetailForStorage entity = (FactoryOrderDetailForStorage) event.getObject();
            this.newDetail.setSid(entity.getId());
            this.newDetail.setSformid(entity.getPformid());
            this.newDetail.setSseq(entity.getSeq());
            this.newDetail.setDesignid(entity.getDesignid());
            this.newDetail.setItemno(entity.getItemno());
            this.newDetail.setColorid(entity.getColorno());
            this.newDetail.setPlanqty(entity.getJhqty());
            this.newDetail.setAllowqty(entity.getJhqty().subtract(entity.getInqty()));
            this.newDetail.setQty(entity.getJhqty().subtract(entity.getInqty()));
        }
    }

    @Override
    public void init() {
        setSuperEJB(factoryStorageBean);
        setDetailEJB(factoryStorageDetailBean);
        setModel(new FactoryStorageModel(factoryStorageBean));
        this.model.getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

}

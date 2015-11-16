/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.FactoryOrderBean;
import com.hhsc.ejb.FactoryOrderDetailBean;
import com.hhsc.ejb.PrintDetailBean;
import com.hhsc.entity.FactoryOrder;
import com.hhsc.entity.FactoryOrderDetail;
import com.hhsc.entity.PrintDetail;
import com.hhsc.lazy.YHModel;
import com.hhsc.web.SuperMulti2Bean;
import com.lightshell.comm.BaseLib;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "yhManagedBean")
@SessionScoped
public class YHManagedBean extends SuperMulti2Bean<FactoryOrder, FactoryOrderDetail, PrintDetail> {

    @EJB
    private FactoryOrderBean factoryOrderBean;
    @EJB
    private FactoryOrderDetailBean factoryOrderDetailBean;
    @EJB
    private PrintDetailBean printDetailBean;

    protected String designid;

    public YHManagedBean() {
        super(FactoryOrder.class, FactoryOrderDetail.class, PrintDetail.class);
    }

    @Override
    public void createDetail2() {
        super.createDetail2();
        this.newDetail2.setSeq(getMaxSeq(this.detailList2));
        this.newDetail2.setQty(BigDecimal.ONE);
        this.newDetail2.setPrice(BigDecimal.ZERO);
        this.setCurrentDetail2(newDetail2);
    }

    @Override
    public void init() {
        setSuperEJB(factoryOrderBean);
        setDetailEJB(factoryOrderDetailBean);
        setDetailEJB2(printDetailBean);
        setModel(new YHModel(factoryOrderBean));
        getModel().getFilterFields().put("yhstatus", "N");
        super.init();
    }

    @Override
    public void pull() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setYhrecdate(BaseLib.getDate());
                currentEntity.setYhrecman(getUserManagedBean().getCurrentUser().getUsername());
                if ((null == currentEntity.getYhreaded()) || (!currentEntity.getYhreaded())) {
                    currentEntity.setYhreaded(Boolean.TRUE);
                    currentEntity.setYhreaddate(BaseLib.getDate());
                }
                update();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("yhdeldateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("yhdeldateEnd", queryDateEnd);
            }
            if (designid != null && !"".equals(designid)) {
                this.model.getFilterFields().put("itemid", designid);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("yhstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("yhstatus", "N");
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && currentSysprg != null) {
            if (currentEntity.getYhstatus() != null && currentEntity.getZhstatus() != null) {
                if ("V".equals(currentEntity.getZhstatus())) {
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = false;
                } else {
                    switch (currentEntity.getYhstatus()) {
                        case "V":
                            this.doEdit = currentSysprg.getDoedit() && false;
                            this.doDel = currentSysprg.getDodel() && false;
                            this.doCfm = false;
                            this.doUnCfm = currentSysprg.getDouncfm() && true;
                            break;
                        default:
                            this.doEdit = currentSysprg.getDoedit() && true;
                            this.doDel = currentSysprg.getDodel() && true;
                            this.doCfm = currentSysprg.getDocfm() && true;
                            this.doUnCfm = false;
                    }
                }
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setYhstatus("N");
                currentEntity.setYhdelman(null);
                currentEntity.setZhrecdate(null);
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setStatus("YH");
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setYhstatus("V");
                currentEntity.setYhdelman(getUserManagedBean().getCurrentUser().getUsername());
                currentEntity.setZhrecdate(getDate());
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdateToNow();
                currentEntity.setStatus("HZ");
                update();
                setToolBar();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    /**
     * @return the designid
     */
    public String getDesignid() {
        return designid;
    }

    /**
     * @param designid the designid to set
     */
    public void setDesignid(String designid) {
        this.designid = designid;
    }

}

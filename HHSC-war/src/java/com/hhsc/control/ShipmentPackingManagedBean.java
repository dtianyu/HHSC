/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ShipmentPackingBean;
import com.hhsc.ejb.ShipmentPackingDetailBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.ShipmentPacking;
import com.hhsc.entity.ShipmentPackingDetail;
import com.hhsc.entity.SalesShipmentDetailForQuery;
import com.hhsc.lazy.ShipmentPackingModel;
import com.hhsc.web.FormMultiBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "shipmentPackingManagedBean")
@SessionScoped
public class ShipmentPackingManagedBean extends FormMultiBean<ShipmentPacking, ShipmentPackingDetail> {

    @EJB
    protected ShipmentPackingBean shipmentPackingBean;
    @EJB
    protected ShipmentPackingDetailBean shipmentPackingDetailBean;

    private String queryCustomerno;

    /**
     * Creates a new instance of ShipmentPackingManagedBean
     */
    public ShipmentPackingManagedBean() {
        super(ShipmentPacking.class, ShipmentPackingDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomerno(entity.getCustomerno());
            this.currentEntity.setCustomer(entity.getCustomer());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomerno(entity.getCustomerno());
            this.newEntity.setCustomer(entity.getCustomer());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            List<SalesShipmentDetailForQuery> entities = (List<SalesShipmentDetailForQuery>) event.getObject();
            for (SalesShipmentDetailForQuery entity : entities) {
                if (!entity.getItemno().equals("A000000")) {
                    this.createDetail();
                    this.currentDetail.setItemDesign(entity.getItemmaster());
                    this.currentDetail.setDesignno(entity.getItemno());
                    this.currentDetail.setDesignDesc(entity.getItemmaster().getItemdesc());
                    this.currentDetail.setDesignSpec(entity.getItemmaster().getItemspec());
                    this.currentDetail.setDesignMake(entity.getItemmaster().getItemmake());
                    this.currentDetail.setItemimg(entity.getItemmaster().getImg1());
                    this.currentDetail.setColorno(entity.getColorno());
                    this.currentDetail.setCustomeritemno(entity.getCustomeritemno());
                    this.currentDetail.setCustomercolorno(entity.getCustomercolorno());
                    this.currentDetail.setCustomerrefno(entity.getCustomerrefno());
                    this.currentDetail.setQty(entity.getQty());
                    this.currentDetail.setUnit(entity.getUnit());
                    this.currentDetail.setSrcapi("salesshipment");
                    this.currentDetail.setSrcformid(entity.getSalesShipment().getFormid());
                    this.currentDetail.setSrcseq(entity.getSeq());
                    this.doConfirmDetail();
                }
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        handleDialogReturnWhenDetailEdit(event);
    }

    @Override
    public void init() {
        openParams = new HashMap<>();
        openOptions = new HashMap<>();
        setSuperEJB(shipmentPackingBean);
        setDetailEJB(shipmentPackingDetailBean);
        setModel(new ShipmentPackingModel(shipmentPackingBean));
        getModel().getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "packingdetailSelect":
                if (currentEntity != null && currentEntity.getCustomerno() != null) {
                    openParams.clear();
                    List<String> customerno = new ArrayList<>();
                    customerno.add(currentEntity.getCustomerno());
                    openParams.put("customerno", customerno);
                    List<String> itemno = new ArrayList<>();
                    itemno.add("A000000");
                    openParams.put("itemno <>", itemno);
                    openOptions.clear();
                    openOptions.put("modal", true);
                    openOptions.put("contentWidth", 800);
                    openDialog(view, openOptions, openParams);
                } else if (currentEntity.getCustomerno() == null) {
                    showWarnMsg("Warn", "请输入客户");
                }
                break;
            default:
                super.openDialog(view);
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
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

    /**
     * @return the queryCustomerno
     */
    public String getQueryCustomerno() {
        return queryCustomerno;
    }

    /**
     * @param queryCustomerno the queryCustomerno to set
     */
    public void setQueryCustomerno(String queryCustomerno) {
        this.queryCustomerno = queryCustomerno;
    }

}

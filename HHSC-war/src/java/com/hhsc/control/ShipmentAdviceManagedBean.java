/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.lazy.SalesShipmentModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "shipmentAdviceManagedBean")
@SessionScoped
public class ShipmentAdviceManagedBean extends SalesShipmentManagedBean {

    /**
     * Creates a new instance of SalesShipmentManagedBean
     */
    public ShipmentAdviceManagedBean() {

    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setShipkind("A000000");
    }

    @Override
    public void init() {
        setSuperEJB(salesShipmentBean);
        setDetailEJB(salesShipmentDetailBean);
        setModel(new SalesShipmentModel(salesShipmentBean, userManagedBean));
        super.init();
        getModel().getFilterFields().put("status", "N");
        getModel().getFilterFields().put("shipkind", "A000000");
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "samplingchargeSelect":
                if (currentEntity != null && currentEntity.getShiptype() != null && currentEntity.getCustomer() != null
                        && currentEntity.getCurrency() != null && currentEntity.getTaxtype() != null && currentEntity.getWarehouse() != null) {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> shiptype = new ArrayList<>();
                    shiptype.add(currentEntity.getShiptype().getType());
                    params.put("shiptype", shiptype);
                    List<String> customerno = new ArrayList<>();
                    customerno.add(currentEntity.getCustomer().getCustomerno());
                    params.put("customerno", customerno);
                    List<String> currency = new ArrayList<>();
                    currency.add(currentEntity.getCurrency());
                    params.put("currency", currency);
                    List<String> taxtype = new ArrayList<>();
                    taxtype.add(currentEntity.getTaxtype());
                    params.put("taxtype", taxtype);
                    List<String> taxrate = new ArrayList<>();
                    taxrate.add(currentEntity.getTaxrate().toString());
                    params.put("taxrate", taxrate);
                    List<String> itemno = new ArrayList<>();
                    itemno.add("A000000");
                    params.put("itemno", itemno);
                    Map<String, Object> options = new HashMap<>();
                    options.put("modal", true);
                    options.put("contentWidth", 800);
                    openDialog(view, options, params);
                } else if (currentEntity.getShiptype() == null) {
                    showWarnMsg("Warn", "请输入出货类别");
                } else if (currentEntity.getCustomer() == null) {
                    showWarnMsg("Warn", "请输入客户");
                } else if (currentEntity.getCurrency() == null) {
                    showWarnMsg("Warn", "请输入币别");
                } else if (currentEntity.getTaxkind() == null) {
                    showWarnMsg("Warn", "请输入税别");
                } else if (currentEntity.getWarehouse() == null) {
                    showWarnMsg("Warn", "请输入默认仓库");
                }
                break;
            default:
                super.openDialog(view);
        }
    }

    @Override
    public void query() {
        super.query();
        getModel().getFilterFields().put("shipkind", "A000000");
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
            this.model.getFilterFields().put("shipkind", "A000000");
        }
    }

}

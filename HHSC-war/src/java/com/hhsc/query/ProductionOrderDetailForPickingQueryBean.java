/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProductionOrderDetailForPickingBean;
import com.hhsc.entity.ProductionOrderDetailForQuery;
import com.hhsc.lazy.ProductionOrderDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionOrderDetailForPickingQueryBean")
@ViewScoped
public class ProductionOrderDetailForPickingQueryBean extends SuperQueryBean<ProductionOrderDetailForQuery> {

    @EJB
    private ProductionOrderDetailForPickingBean productionOrderDetailForPickingBean;

    private String formtype;
    private String designno;

    public ProductionOrderDetailForPickingQueryBean() {
        super(ProductionOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(productionOrderDetailForPickingBean);
        setModel(new ProductionOrderDetailForQueryModel(productionOrderDetailForPickingBean));
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        if (params != null) {
            if (params.containsKey("formtype")) {
                formtype = params.get("formtype")[0];
                this.model.getFilterFields().put("productionOrder.formtype.type", formtype);
            }
            if (params.containsKey("formid")) {
                queryFormId = params.get("formid")[0];
                this.model.getFilterFields().put("productionOrder.formid", queryFormId);
            }
            if (params.containsKey("designno")) {
                designno = params.get("designno")[0];
                this.model.getFilterFields().put("productionOrder.designno", designno);
            }
        }
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.formtype != null) {
                this.model.getFilterFields().put("productionOrder.formtype.type", formtype);
            }
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("productionOrder.formid", queryFormId);
            }

            if (this.designno != null && !"".equals(this.designno)) {
                this.model.getFilterFields().put("productionOrder.designno", designno);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (this.formtype != null) {
            this.model.getFilterFields().put("productionOrder.formtype.type", formtype);
        }
        if (this.queryFormId != null) {
            this.model.getFilterFields().put("productionOrder.formid", queryFormId);
        }
        if (this.designno != null) {
            this.model.getFilterFields().put("productionOrder.designno", designno);
        }
    }

    /**
     * @return the designno
     */
    public String getDesignno() {
        return designno;
    }

    /**
     * @param designno the designno to set
     */
    public void setDesignno(String designno) {
        this.designno = designno;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProductionOrderDetailForQueryBean;
import com.hhsc.entity.ProductionOrderDetailForQuery;
import com.hhsc.lazy.ProductionOrderDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionDetailQueryBean")
@ViewScoped
public class ProductionDetailQueryBean extends SuperQueryBean<ProductionOrderDetailForQuery> {

    @EJB
    private ProductionOrderDetailForQueryBean productionOrderDetailForQueryBean;

    public ProductionDetailQueryBean() {
        super(ProductionOrderDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(productionOrderDetailForQueryBean);
        setModel(new ProductionOrderDetailForQueryModel(productionOrderDetailForQueryBean));
        this.model.getSortFields().put("productionOrder.formid", "DESC");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("productionOrder.formid", queryFormId);
            }
            if (queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("productionOrder.designno", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("productionOrder.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("productionOrder.formdateEnd", queryDateEnd);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getSortFields().put("productionOrder.formid", "DESC");
    }

}

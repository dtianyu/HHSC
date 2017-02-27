/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProductionPickingDetailForQueryBean;
import com.hhsc.entity.ProductionPickingDetailForQuery;
import com.hhsc.lazy.ProductionPickingDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionPickingDetailQueryBean")
@ViewScoped
public class ProductionPickingDetailQueryBean extends SuperQueryBean<ProductionPickingDetailForQuery> {

    @EJB
    private ProductionPickingDetailForQueryBean productionPickingDetailForQueryBean;

    private String queryItemno;
    private String querySrcformid;

    public ProductionPickingDetailQueryBean() {
        super(ProductionPickingDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(productionPickingDetailForQueryBean);
        setModel(new ProductionPickingDetailForQueryModel(productionPickingDetailForQueryBean));
        this.model.getSortFields().put("productionPicking.formid", "DESC");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("productionPicking.formid", queryFormId);
            }
            if (queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("productionPicking.srcitemno", queryName);
            }
            if (queryItemno != null && !"".equals(this.queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (querySrcformid != null && !"".equals(this.querySrcformid)) {
                this.model.getFilterFields().put("srcformid", querySrcformid);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("productionPicking.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("productionPicking.formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("productionPicking.status", queryState);
            }
        }
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

    /**
     * @return the querySrcformid
     */
    public String getQuerySrcformid() {
        return querySrcformid;
    }

    /**
     * @param querySrcformid the querySrcformid to set
     */
    public void setQuerySrcformid(String querySrcformid) {
        this.querySrcformid = querySrcformid;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.query;

import com.hhsc.ejb.ProductionFinishDetailForQueryBean;
import com.hhsc.entity.ProductionFinishDetailForQuery;
import com.hhsc.lazy.ProductionFinishDetailForQueryModel;
import com.hhsc.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionFinishDetailQueryBean")
@ViewScoped
public class ProductionFinishDetailQueryBean extends SuperQueryBean<ProductionFinishDetailForQuery> {

    @EJB
    private ProductionFinishDetailForQueryBean productionFinishDetailForQueryBean;

    private String queryItemno;
    private String querySrcformid;

    public ProductionFinishDetailQueryBean() {
        super(ProductionFinishDetailForQuery.class);
    }

    @Override
    public void init() {
        setSuperEJB(productionFinishDetailForQueryBean);
        setModel(new ProductionFinishDetailForQueryModel(productionFinishDetailForQueryBean));
        this.model.getSortFields().put("productionFinish.formid", "DESC");
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("productionFinish.formid", queryFormId);
            }
            if (queryName != null && !"".equals(this.queryName)) {
                this.model.getFilterFields().put("productionFinish.srcitemno", queryName);
            }
            if (queryItemno != null && !"".equals(this.queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (querySrcformid != null && !"".equals(this.querySrcformid)) {
                this.model.getFilterFields().put("srcformid", querySrcformid);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("productionFinish.formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("productionFinish.formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(this.queryState)) {
                this.model.getFilterFields().put("productionFinish.status", queryState);
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

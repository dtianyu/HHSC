/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ProductionPickingDetailForQuery;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ProductionPickingDetailForQueryBean extends SuperBean<ProductionPickingDetailForQuery> {

    public ProductionPickingDetailForQueryBean() {
        super(ProductionPickingDetailForQuery.class);
    }

}

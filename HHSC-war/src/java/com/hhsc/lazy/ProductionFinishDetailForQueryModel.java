/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.ProductionFinishDetailForQuery;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class ProductionFinishDetailForQueryModel extends BaseLazyModel<ProductionFinishDetailForQuery> {

    public ProductionFinishDetailForQueryModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

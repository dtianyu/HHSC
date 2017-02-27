/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.lazy;

import com.hhsc.entity.ProductionPickingDetailForQuery;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ProductionPickingDetailForQueryModel extends BaseLazyModel<ProductionPickingDetailForQuery> {

    public ProductionPickingDetailForQueryModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}

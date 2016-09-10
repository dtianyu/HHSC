/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.validator;

import com.hhsc.ejb.ProductionOrderDetailBean;
import com.hhsc.entity.ProductionOrderDetail;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author kevindong
 */
@FacesValidator("com.hhsc.validator.ProductionFinishDetailQtyValidator")
public class ProductionFinishDetailQtyValidator implements Validator {

    @EJB
    private ProductionOrderDetailBean factoryOrderDetailBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            ProductionOrderDetail entity = factoryOrderDetailBean.findById(Integer.parseInt(value.toString()));
            return;
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "可入库数量不足", "可入库数量不足");
        throw new ValidatorException(message);
    }

}

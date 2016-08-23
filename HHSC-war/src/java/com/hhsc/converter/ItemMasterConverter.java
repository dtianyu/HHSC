/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.converter;

import com.hhsc.ejb.ItemMasterBean;
import com.hhsc.entity.ItemMaster;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kevindong
 */
@FacesConverter("itemMasterConverter")
public class ItemMasterConverter implements Converter {

    @EJB
    private ItemMasterBean itemMasterBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ItemMaster entity = itemMasterBean.findById(Integer.parseInt(value));
                if (entity != null) {
                    return entity;
                } else {
                    return null;
                }
            } catch (ConverterException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entity"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return ((ItemMaster) value).getId().toString();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.ItemUnitType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemUnitTypeBean {

    private List<ItemUnitType> itemUnitTypes;

    public ItemUnitTypeBean() {
        itemUnitTypes = new ArrayList<>();
        itemUnitTypes.add(new ItemUnitType("1", "单单位"));
        itemUnitTypes.add(new ItemUnitType("2", "双单位"));
    }

    /**
     * @return the itemUnitTypes
     */
    public List<ItemUnitType> getItemUnitTypes() {
        return itemUnitTypes;
    }

    /**
     * @param itemUnitTypes the itemUnitTypes to set
     */
    public void setItemUnitTypes(List<ItemUnitType> itemUnitTypes) {
        this.itemUnitTypes = itemUnitTypes;
    }
}

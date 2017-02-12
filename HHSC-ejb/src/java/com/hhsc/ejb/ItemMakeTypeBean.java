/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.ItemMakeType;
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
public class ItemMakeTypeBean {

    private List<ItemMakeType> itemMakeTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemMakeTypeBean() {
        itemMakeTypes = new ArrayList<>();
        itemMakeTypes.add(new ItemMakeType("F", "自制完成品"));
        itemMakeTypes.add(new ItemMakeType("M", "自制半成品"));
        itemMakeTypes.add(new ItemMakeType("P", "外购件"));
        itemMakeTypes.add(new ItemMakeType("S", "托外加工"));
        itemMakeTypes.add(new ItemMakeType("V", "虚拟件"));
    }

    public ItemMakeType findById(String id) {
        if (itemMakeTypes != null) {
            for (ItemMakeType entity : itemMakeTypes) {
                if (entity.getId().equals(id)) {
                    return entity;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * @return the itemMakeTypes
     */
    public List<ItemMakeType> getItemMakeTypes() {
        return itemMakeTypes;
    }

    /**
     * @param itemMakeTypes the itemMakeTypes to set
     */
    public void setItemMakeTypes(List<ItemMakeType> itemMakeTypes) {
        this.itemMakeTypes = itemMakeTypes;
    }
}

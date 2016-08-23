/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.ItemPPType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author KevinDong
 */
@Stateless
@LocalBean
public class ItemPPTypeBean {

    private List<ItemPPType> itemPPTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemPPTypeBean() {
        itemPPTypes = new ArrayList<>();
        itemPPTypes.add(new ItemPPType("F", "固定补足"));
        itemPPTypes.add(new ItemPPType("M", "MRP"));
        itemPPTypes.add(new ItemPPType("M&P", "MRP和采购"));
        itemPPTypes.add(new ItemPPType("P", "采购"));
    }

    public ItemPPType findById(String id) {
        if (itemPPTypes != null) {
            for (ItemPPType entity : itemPPTypes) {
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
     * @return the itemPPTypes
     */
    public List<ItemPPType> getItemPPTypes() {
        return itemPPTypes;
    }

    /**
     * @param itemPPTypes the itemPPTypes to set
     */
    public void setItemPPTypes(List<ItemPPType> itemPPTypes) {
        this.itemPPTypes = itemPPTypes;
    }
}

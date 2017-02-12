/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.ItemPropType;
import java.io.Serializable;
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
public class ItemPropTypeBean implements Serializable {

    private List<ItemPropType> itemPropTypes;

    public ItemPropTypeBean() {
        itemPropTypes = new ArrayList<>();
        itemPropTypes.add(new ItemPropType("1", "成品"));
        itemPropTypes.add(new ItemPropType("2", "半成品"));
        itemPropTypes.add(new ItemPropType("3", "原料"));
        itemPropTypes.add(new ItemPropType("4", "物料"));
        itemPropTypes.add(new ItemPropType("5", "人工"));
        itemPropTypes.add(new ItemPropType("7", "包装物"));
        itemPropTypes.add(new ItemPropType("8", "低值易耗品"));
        itemPropTypes.add(new ItemPropType("9", "费用"));
        itemPropTypes.add(new ItemPropType("A", "资产"));
        itemPropTypes.add(new ItemPropType("C", "代理商品"));
    }

    public ItemPropType findById(String id) {
        if (itemPropTypes != null) {
            for (ItemPropType entity : itemPropTypes) {
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
     * @return the itemPropTypes
     */
    public List<ItemPropType> getItemPropTypes() {
        return itemPropTypes;
    }

    /**
     * @param itemPropTypes the itemPropTypes to set
     */
    public void setItemPropTypes(List<ItemPropType> itemPropTypes) {
        this.itemPropTypes = itemPropTypes;
    }
}

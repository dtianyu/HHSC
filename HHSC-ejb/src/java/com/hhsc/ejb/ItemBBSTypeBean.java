/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.ItemBBSType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class ItemBBSTypeBean {

    private List<ItemBBSType> itemBBSTypes;

    public ItemBBSTypeBean() {
        itemBBSTypes = new ArrayList<>();
        itemBBSTypes.add(new ItemBBSType("000", "不管理"));
        itemBBSTypes.add(new ItemBBSType("001", "管理序号"));
        itemBBSTypes.add(new ItemBBSType("010", "管理批号"));
        itemBBSTypes.add(new ItemBBSType("011", "管理批号+序号"));
        itemBBSTypes.add(new ItemBBSType("100", "管理品牌"));
        itemBBSTypes.add(new ItemBBSType("101", "管理品牌+序号"));
        itemBBSTypes.add(new ItemBBSType("110", "管理品牌+批号"));
        itemBBSTypes.add(new ItemBBSType("111", "管理品牌+批号+序号"));
    }

    /**
     * @return the itemBBSTypes
     */
    public List<ItemBBSType> getItemBBSTypes() {
        return itemBBSTypes;
    }

    /**
     * @param itemBBSTypes the itemBBSTypes to set
     */
    public void setItemBBSTypes(List<ItemBBSType> itemBBSTypes) {
        this.itemBBSTypes = itemBBSTypes;
    }
}

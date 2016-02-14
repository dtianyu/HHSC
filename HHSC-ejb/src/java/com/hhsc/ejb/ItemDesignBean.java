/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemDesign;
import com.lightshell.comm.BaseLib;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemDesignBean extends SuperBean<ItemDesign> {

    public ItemDesignBean() {
        super(ItemDesign.class);
    }

    @Override
    public String getFormId(Date day, String code, String format, int len) {
        String maxid, newid;
        int id;
        if (day != null && code != null && format != null && len > 0) {
            String d = BaseLib.formatDate(format, day);
            int c = code.length();
            int f = d.length();
            Query query = getEntityManager().createNativeQuery("select max(designid) from  " + className
                    + " where substring(designid," + 1 + "," + (c + f) + ")='" + (code + d) + "'");
            if (query.getSingleResult() != null) {
                maxid = query.getSingleResult().toString();
                int m = maxid.length();
                id = Integer.parseInt(maxid.substring(m - len, m)) + 1;
                newid = code + d + String.format("%0" + len + "d", id);
            } else {
                newid = code + d + String.format("%0" + len + "d", 1);
            }
            return newid;
        } else {
            return "";
        }
    }

}

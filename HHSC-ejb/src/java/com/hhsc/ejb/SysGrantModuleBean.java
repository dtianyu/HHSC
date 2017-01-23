/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SysGrantModule;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SysGrantModuleBean extends SuperBean<SysGrantModule> {

    public SysGrantModuleBean() {
        super(SysGrantModule.class);
    }

    public int getRowCountByUserId(int id) {
        Query query = this.getEntityManager().createNamedQuery("SysGrantModule.getRowCountByUserId");
        query.setParameter("userid", id);
        try {
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public List<SysGrantModule> findByUserId(int id) {
        Query query = this.getEntityManager().createNamedQuery("SysGrantModule.findByUserId");
        query.setParameter("userid", id);
        return query.getResultList();
    }

    public List<SysGrantModule> findByRoleId(int id) {
        Query query = this.getEntityManager().createNamedQuery("SysGrantModule.findByRoleId");
        query.setParameter("roleid", id);
        return query.getResultList();
    }

}

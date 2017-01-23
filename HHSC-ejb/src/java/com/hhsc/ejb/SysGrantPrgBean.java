/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SysGrantPrg;
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
public class SysGrantPrgBean extends SuperBean<SysGrantPrg> {

    public SysGrantPrgBean() {
        super(SysGrantPrg.class);
    }

    public List<SysGrantPrg> findByUserId(int id) {
        Query query = this.getEntityManager().createNamedQuery("SysGrantPrg.findByUserId");
        query.setParameter("userid", id);
        return query.getResultList();
    }

    public List<SysGrantPrg> findByRoleId(int id) {
        Query query = this.getEntityManager().createNamedQuery("SysGrantPrg.findByRoleId");
        query.setParameter("roleid", id);
        return query.getResultList();
    }

}

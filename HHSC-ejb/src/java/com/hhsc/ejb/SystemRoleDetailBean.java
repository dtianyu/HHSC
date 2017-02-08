/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SystemRoleDetail;
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
public class SystemRoleDetailBean extends SuperBean<SystemRoleDetail> {

    public SystemRoleDetailBean() {
        super(SystemRoleDetail.class);
    }

    public List<SystemRoleDetail> findByUserId(int id) {
        Query query = getEntityManager().createNamedQuery("SystemRoleDetail.findByUserId");
        query.setParameter("userid", id);
        return query.getResultList();
    }

}

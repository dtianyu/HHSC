/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ProcessResource;
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
public class ProcessResourceBean extends SuperBean<ProcessResource> {

    public ProcessResourceBean() {
        super(ProcessResource.class);
    }

    public List<ProcessResource> findByKind(String kind) {
        Query query = getEntityManager().createNamedQuery("ProcessResource.findByKind");
        query.setParameter("kind", kind);
        return query.getResultList();
    }

}

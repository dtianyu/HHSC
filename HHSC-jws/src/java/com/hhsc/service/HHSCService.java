/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.service;

import com.hhsc.ejb.AreaBean;
import com.hhsc.entity.Area;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author kevindong
 */
@WebService
@Stateless
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HHSCService {

    @EJB
    private AreaBean areaBean;

    @WebMethod(operationName = "areaFindByIdF")
    public Area areaFindById(int id) {
        return areaBean.findById(id);
    }

}

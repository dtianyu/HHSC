/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.comm;

import com.lightshell.comm.SuperEJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 *
 * @author kevindong
 * @param <T>
 */
public abstract class SuperBean<T> extends SuperEJB<T> {

    protected final String url = "http://127.0.0.1:8480/WebService/HHWebService";
    protected final String nameSpace = "http://jws.hhsc.com/";

    @PersistenceContext(unitName = "HHSC-ejbPU")
    private EntityManager em;

    public SuperBean(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    protected Call createAXISCall(String urlString) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(urlString);
            return call;
        } catch (ServiceException ex) {
            Logger.getLogger(SuperBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public T findByFormId(String value) {
        Query query = getEntityManager().createNamedQuery(this.className + ".findByFormid");
        query.setParameter("formid", value);
        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public T findByPIdAndSeq(Object pid, int seq) {
        Query query = getEntityManager().createNamedQuery(this.className + ".findByPIdAndSeq");
        query.setParameter("pid", pid);
        query.setParameter("seq", seq);
        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public String formatString(String value, String format) {
        if (value.length() >= format.length()) {
            return value;
        }
        return format.substring(0, format.length() - value.length()) + value;
    }

}

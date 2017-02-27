/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.SalesTransaction;
import com.lightshell.comm.BaseLib;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesTransactionBean extends SuperBean<SalesTransaction> {

    public SalesTransactionBean() {
        super(SalesTransaction.class);
    }

    public SalesTransaction createFromSalesShipment(SalesShipment m, SalesShipmentDetail d) {
        if (m == null || d == null) {
            return null;
        }
        SalesTransaction e = new SalesTransaction();
        e.setFormid(m.getFormid());
        e.setFormdate(m.getFormdate());
        e.setShiptype(m.getShiptype().getType());
        e.setShipkind(m.getShipkind());
        if (m.getAbroad() == null) {
            e.setAbroad(Boolean.FALSE);
        } else {
            e.setAbroad(m.getAbroad());
        }
        e.setCustomer(m.getCustomer());
        e.setDept(m.getDept());
        e.setSalerid(m.getSalesman().getId());
        e.setCurrency(m.getCurrency());
        e.setExchange(m.getExchange());
        e.setTaxtype(m.getTaxtype());
        e.setTaxkind(m.getTaxkind());
        e.setTaxrate(m.getTaxrate());
        e.setTradetype(m.getTradetype());
        e.setTradename(m.getTradename());
        e.setSeq(d.getSeq());
        e.setItemMaster(d.getItemmaster());
        e.setItemno(d.getItemno());
        e.setItemimg(d.getItemimg());
        e.setColorno(d.getColorno());
        e.setCustomeritemno(d.getCustomeritemno());
        e.setCustomercolorno(d.getCustomercolorno());
        e.setCustomerrefno(d.getCustomerrefno());
        e.setBrand(d.getBrand());
        e.setBatch(d.getBatch());
        e.setSn(d.getSn());
        e.setQty(d.getQty());
        e.setUnit(d.getUnit());
        e.setPrice(d.getPrice());
        e.setAmts(d.getAmts());
        e.setExtax(d.getExtax());
        e.setTaxes(d.getTaxes());
        e.setSrcapi(d.getSrcapi());
        e.setSrcformid(d.getSrcformid());
        e.setSrcseq(d.getSrcseq());
        e.setStatus(d.getStatus());
        e.setBillqty(BigDecimal.ZERO);
        e.setShipamts(e.getExtax());
        e.setShipamt(e.getExtax().multiply(e.getExchange()));
        e.setPreamts(BigDecimal.ZERO);
        e.setPreamt(BigDecimal.ZERO);
        e.setAddamts(BigDecimal.ZERO);
        e.setAddamt(BigDecimal.ZERO);
        e.setOffamts(BigDecimal.ZERO);
        e.setOffamt(BigDecimal.ZERO);
        e.setTaxamts(e.getTaxes());
        e.setTaxamt(e.getTaxes().multiply(e.getExchange()));
        return e;
    }

    public SalesTransaction findByFormidAndSeq(Object formid, int seq) {
        Query query = this.getEntityManager().createNamedQuery("SalesTransaction.findByFormidAndSeq");
        query.setParameter("formid", formid);
        query.setParameter("seq", seq);
        try {
            Object o = query.getSingleResult();
            return (SalesTransaction) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<SalesTransaction> findByNotInvoice(String filterString, String sortString) {
        Map<String, Object> filters = null;
        Map<String, String> sorts = null;
        if (filterString != null && !"".equals(filterString)) {
            filters = BaseLib.createHashMap(filterString, true);
        }
        if (sortString != null && !"".equals(sortString)) {
            sorts = BaseLib.createLinkedHashMap(sortString);
        }
        return findByFilters(filters, sorts);
    }

}

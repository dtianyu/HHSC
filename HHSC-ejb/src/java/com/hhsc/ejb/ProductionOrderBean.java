/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemResource;
import com.hhsc.entity.ProductionOrder;
import com.hhsc.entity.ProductionOrderDetail;
import com.hhsc.entity.ProductionResource;
import com.hhsc.entity.SalesOrderDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ProductionOrderBean extends SuperBean<ProductionOrder> {

    @EJB
    private ItemResourceBean itemResourceBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;
    @EJB
    private ProductionResourceBean productionResourceBean;

    private List<ProductionOrderDetail> detailList;

    private List<ProductionResource> resourceList;

    public ProductionOrderBean() {
        super(ProductionOrder.class);
    }

    public void initResource(ProductionOrder entity) {

        List<ItemResource> itemResourceList = itemResourceBean.findByItemno(entity.getDesignno());
        if (itemResourceList != null && !itemResourceList.isEmpty()) {
            int seq = 0;
            for (ItemResource e : itemResourceList) {
                seq++;
                ProductionResource r = new ProductionResource();
                r.setPid(entity.getFormid());
                r.setSeq(seq);
                r.setProcess(e.getProcess());
                r.setProcseq(e.getSeq());
                r.setKind(e.getKind());
                r.setItemid(e.getPid());
                r.setItemno(e.getItemno());
                r.setContent(e.getContent());
                r.setValuetype(e.getValuetype());
                r.setBoolvalue(e.getBoolvalue());
                r.setNumvalue(e.getNumvalue());
                r.setStrvalue(e.getStrvalue());
                r.setRemark(e.getRemark());
                productionResourceBean.persist(r);
            }
            ItemResource e = itemResourceList.get(0);
            entity.setHgreq(e.getItemProcess().getHgreq());
            entity.setZbreq(e.getItemProcess().getZbreq());
            entity.setPsreq(e.getItemProcess().getPsreq());
            entity.setYhreq(e.getItemProcess().getYhreq());
            entity.setZhreq(e.getItemProcess().getZhreq());
            entity.setSxreq(e.getItemProcess().getSxreq());
            entity.setDxreq(e.getItemProcess().getDxreq());
            entity.setCkreq(e.getItemProcess().getCkreq());
        }

    }

    @Override
    public void persist(ProductionOrder entity) {
        super.persist(entity);
        initResource(entity);
        update(entity);
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(productionOrderDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    public void setResource(Object value) {
        setResourceList(productionResourceBean.findByPId(value));
        if (resourceList == null) {
            resourceList = new ArrayList<>();
        }
    }

    @Override
    public ProductionOrder unverify(ProductionOrder entity) {
        try {
            ProductionOrder e = this.getEntityManager().merge(entity);
            setDetailList(productionOrderDetailBean.findByPId(e.getFormid()));
            for (ProductionOrderDetail d : detailList) {
                if (d.getSrcformid() != null && !"".equals(d.getSrcformid())) {
                    SalesOrderDetail s = salesOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setProqty(s.getProqty().subtract(d.getQty()));
                        if (s.getProqty().compareTo(BigDecimal.ZERO) == 0) {
                            s.setStatus("10");
                        } else {
                            s.setStatus("20");
                        }
                        salesOrderDetailBean.update(s);
                    }
                }
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ProductionOrder verify(ProductionOrder entity) {
        try {
            ProductionOrder e = this.getEntityManager().merge(entity);
            setDetailList(productionOrderDetailBean.findByPId(e.getFormid()));
            for (ProductionOrderDetail d : detailList) {
                if (d.getSrcformid() != null && !"".equals(d.getSrcformid())) {
                    SalesOrderDetail s = salesOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s != null) {
                        s.setProqty(s.getProqty().add(d.getQty()));
                        s.setStatus("20");
                        salesOrderDetailBean.update(s);
                    }
                }
            }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<ProductionOrderDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ProductionOrderDetail> detailList) {
        this.detailList = detailList;
    }

    /**
     * @return the resourceList
     */
    public List<ProductionResource> getResourceList() {
        return resourceList;
    }

    /**
     * @param resourceList the resourceList to set
     */
    public void setResourceList(List<ProductionResource> resourceList) {
        this.resourceList = resourceList;
    }

}

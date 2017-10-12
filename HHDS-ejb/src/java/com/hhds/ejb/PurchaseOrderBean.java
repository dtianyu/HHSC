/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.PurchaseOrder;
import com.hhds.entity.PurchaseOrderDetail;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import static javax.xml.rpc.encoding.XMLType.XSD_STRING;
import org.apache.axis.client.Call;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class PurchaseOrderBean extends SuperBean<PurchaseOrder> {

    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;

    protected List<PurchaseOrderDetail> detailList;

    public PurchaseOrderBean() {
        super(PurchaseOrder.class);
    }

    public void initPurchase(PurchaseOrder p, List<PurchaseOrderDetail> detailList) {
        try {
            getEntityManager().persist(p);
            detailList.stream().map((d) -> {
                d.setPid(p.getFormid());
                return d;
            }).forEachOrdered((d) -> {
                purchaseOrderDetailBean.persist(d);
            });
        } catch (Exception e) {
            this.delete(p);
            throw new RuntimeException(e);
        }
    }

    public String initHHSCHH(String formid) throws RemoteException {
        Call call = createAXISCall(url);
        if (call != null) {
            try {
                call.setOperationName(new QName(nameSpace, "createHHSCHHByHHDSPO"));
                Object[] params = new Object[]{formid};
                call.addParameter("formid", XSD_STRING, ParameterMode.IN);
                call.setReturnType(XSD_STRING);
                String ret = call.invoke(params).toString();
                return ret;
            } catch (RemoteException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                return ex.getMessage();
            }
        }
        return "系统异常";
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(purchaseOrderDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    @Override
    public PurchaseOrder unverify(PurchaseOrder entity) {
        try {
            PurchaseOrder e = this.getEntityManager().merge(entity);
            detailList = purchaseOrderDetailBean.findByPId(e.getFormid());
            for (PurchaseOrderDetail detail : this.detailList) {
                detail.setStatus("00");
            }
            purchaseOrderDetailBean.update(detailList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    @Override
    public PurchaseOrder verify(PurchaseOrder entity) {
        try {
            PurchaseOrder e = this.getEntityManager().merge(entity);
            detailList = purchaseOrderDetailBean.findByPId(e.getFormid());
            for (PurchaseOrderDetail detail : this.detailList) {
                detail.setStatus("10");
            }
            purchaseOrderDetailBean.update(detailList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    /**
     * @return the detailList
     */
    public List<PurchaseOrderDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<PurchaseOrderDetail> detailList) {
        this.detailList = detailList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.InventoryTransaction;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.TransactionType;
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
public class SalesShipmentBean extends SuperBean<SalesShipment> {

    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    private SalesShipmentDetailBean salesShipmentDetailBean;

    private List<SalesShipmentDetail> detailList;

    private List<ItemInventory> inventoryList;

    public SalesShipmentBean() {
        super(SalesShipment.class);
    }

    @Override
    public SalesShipment unverify(SalesShipment entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        SalesOrderDetail s;
        try {
            SalesShipment e = this.getEntityManager().merge(entity);
            detailList = salesShipmentDetailBean.findByPId(e.getFormid());
            for (SalesShipmentDetail detail : detailList) {

                //更新库存交易
                List<InventoryTransaction> t = inventoryTransactionBean.findByFormid(e.getFormid());
                if (t != null && !t.isEmpty()) {
                    inventoryTransactionBean.delete(t);
                }

                //更新库存数量       
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(detail.getQty());
                itemInventoryBean.add(i);

                //更新订单状态 
                s = salesOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
                s.setShipqty(s.getShipqty().subtract(detail.getQty()));
                if (s.getShipqty().compareTo(BigDecimal.ZERO) == 0) {
                    s.setStatus("10");
                    s.setRelapi("");
                    s.setRelformid("");
                    s.setRelseq(0);
                } else {
                    s.setStatus("50");
                }
                salesOrderDetailBean.update(s);

                detail.setStatus("40");
            }
            itemInventoryBean.add(inventoryList);
            salesShipmentDetailBean.update(detailList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SalesShipment verify(SalesShipment entity) {
        TransactionType transactionType = transactionTypeBean.findByTrtype("SDA");
        if (transactionType == null) {
            throw new RuntimeException("SDA交易类别没有定义");
        }
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        SalesOrderDetail s;
        try {
            SalesShipment e = getEntityManager().merge(entity);
            detailList = salesShipmentDetailBean.findByPId(e.getFormid());
            for (SalesShipmentDetail detail : detailList) {

                //更新库存交易
                InventoryTransaction t = new InventoryTransaction();
                t.setTrtype(transactionType);
                t.setFormid(e.getFormid());
                t.setFormdate(e.getFormdate());
                t.setSeq(detail.getSeq());
                t.setItemmaster(detail.getItemmaster());
                t.setColorno(detail.getColorno());
                t.setBrand(detail.getBrand());
                t.setBatch(detail.getBatch());
                t.setSn(detail.getSn());
                t.setQty(detail.getQty());
                t.setUnit(detail.getUnit());
                t.setWarehouse(e.getWarehouse());
                t.setIocode(transactionType.getIocode());
                t.setProptype(detail.getItemmaster().getProptype());
                t.setMaketype(detail.getItemmaster().getMaketype());
                t.setSrcapi(detail.getSrcapi());
                t.setSrcformid(detail.getSrcformid());
                t.setSrcseq(detail.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);

                //更新库存数量
                ItemInventory i = new ItemInventory();
                i.setItemmaster(detail.getItemmaster());
                i.setColorno(detail.getColorno());
                i.setBrand(detail.getBrand());
                i.setBatch(detail.getBatch());
                i.setSn(detail.getSn());
                i.setWarehouse(detail.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(detail.getQty());
                inventoryList.add(i);

                //更新订单状态
                s = salesOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
                s.setShipqty(s.getShipqty().add(detail.getQty()));
                if (s.getShipqty().compareTo(s.getQty()) == 0) {
                    s.setStatus("AC");
                } else {
                    s.setStatus("50");
                }
                s.setRelapi("salesshipment");
                s.setRelformid(detail.getPid());
                s.setRelseq(detail.getSeq());
                salesOrderDetailBean.update(s);

                detail.setStatus("50");
            }
            itemInventoryBean.subtract(inventoryList);
            salesShipmentDetailBean.update(detailList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<SalesShipmentDetail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<SalesShipmentDetail> detailList) {
        this.detailList = detailList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemInventory;
import com.hhds.entity.SalesOrder;
import com.hhds.entity.SalesOrderDetail;
import com.hhds.entity.TransactionType;
import com.hhds.entity.VendorItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesOrderBean extends SuperBean<SalesOrder> {

    @EJB
    private TransactionTypeBean transactionTypeBean;
    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private InventoryTransactionBean inventoryTransactionBean;
    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;
    @EJB
    private VendorItemBean vendorItemBean;

    protected List<SalesOrderDetail> detailList;

    private List<ItemInventory> inventoryList;

    public SalesOrderBean() {
        super(SalesOrder.class);
    }

    public boolean hasInventory(SalesOrderDetail sod) {
        VendorItem vi = vendorItemBean.findFirstByItemno(sod.getItemno());
        ItemInventory i = new ItemInventory();
        i.setItemmaster(sod.getItemMaster());
        i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
        i.setBrand(sod.getBrand());
        i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
        i.setSn(sod.getSn());
        i.setWarehouse(sod.getWarehouse());
        i.setPreqty(BigDecimal.ZERO);
        i.setQty(sod.getQty());
        return !itemInventoryBean.isGreatThenInventory(i);
    }

    public boolean hasPurchaseRequest(String formid) {
        //return purchaseRequestBean.getRowCountBySalesOrderFormid(formid) != 0;
        return false;
    }

    public boolean hasProductionOrder(String formid) {
        //return !productionOrderDetailBean.findBySalesOrderFormid(formid).isEmpty();
        return false;
    }

    @Override
    public void setDetail(Object value) {
        detailList = salesOrderDetailBean.findByPId(value);
        if (getDetailList() == null) {
            detailList = new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(SalesOrder s, List<SalesOrderDetail> detailList) {
        try {
            persist(s);
            for (SalesOrderDetail d : detailList) {
                salesOrderDetailBean.persist(d);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SalesOrder verify(SalesOrder entity) {
        TransactionType transactionType = transactionTypeBean.findByTrtype("SDA");
        if (transactionType == null) {
            throw new RuntimeException("SDA交易类别没有定义");
        }
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        VendorItem vi;
        try {
            SalesOrder e = getEntityManager().merge(entity);
            detailList = salesOrderDetailBean.findByPId(e.getFormid());
            for (SalesOrderDetail sod : detailList) {
                vi = vendorItemBean.findFirstByItemno(sod.getItemno());
                //更新库存交易
                InventoryTransaction t = new InventoryTransaction();
                t.setTrtype(transactionType);
                t.setFormid(e.getFormid());
                t.setFormdate(e.getFormdate());
                t.setSeq(sod.getSeq());
                t.setItemmaster(sod.getItemMaster());
                t.setColorno(sod.getColorno());
                t.setBrand(sod.getBrand());
                t.setBatch(sod.getBatch());
                t.setSn(sod.getSn());
                t.setQty(sod.getQty());
                t.setUnit(sod.getUnit());
                t.setWarehouse(sod.getWarehouse());
                t.setIocode(transactionType.getIocode());
                t.setProptype("");
                t.setMaketype("");
                t.setSrcapi(sod.getSrcapi());
                t.setSrcformid(sod.getSrcformid());
                t.setSrcseq(sod.getSrcseq());
                t.setStatus(e.getStatus());
                t.setCfmuser(e.getCfmuser());
                t.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(t);
                inventoryTransactionBean.persist(t);
                //更新库存数量
                ItemInventory i = new ItemInventory();
                i.setItemmaster(sod.getItemMaster());
                i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
                i.setBrand(sod.getBrand());
                i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
                i.setSn(sod.getSn());
                i.setWarehouse(sod.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(sod.getQty());
                inventoryList.add(i);
                //更新订单状态
                sod.setShipqty(sod.getShipqty().add(sod.getQty()));
                if (sod.getShipqty().compareTo(sod.getQty()) >= 0) {
                    sod.setStatus("AC");
                } else {
                    sod.setStatus("50");
                }
                salesOrderDetailBean.update(sod);
            }
            itemInventoryBean.subtract(inventoryList);
            salesOrderDetailBean.update(detailList);

            return e;
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * @return the detailList
     */
    public List<SalesOrderDetail> getDetailList() {
        return detailList;
    }

}

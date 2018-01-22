/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemExchange;
import com.hhds.entity.ItemInventory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemExchangeBean extends SuperBean<ItemExchange> {

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    private List<ItemInventory> inventoryList;

    public ItemExchangeBean() {
        super(ItemExchange.class);
    }

    public List<ItemExchange> findByReason(String value) {
        Query query = this.getEntityManager().createNamedQuery("ItemExchange.findByReason");
        query.setParameter("reason", value);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ItemExchange unverify(ItemExchange entity) {
        ItemInventory i;
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemExchange e = getEntityManager().merge(entity);
            //删除库存交易
            List<InventoryTransaction> transactionList = inventoryTransactionBean.findByFormid(entity.getFormid());
            if (transactionList != null && !transactionList.isEmpty()) {
                inventoryTransactionBean.delete(transactionList);
            }
            //更新库存数量
            i = new ItemInventory();
            i.setItemmaster(entity.getItemMasterFrom());
            i.setColorno(entity.getColornofrom());
            i.setBrand(entity.getBrandfrom());
            i.setBatch(entity.getBatchfrom());
            i.setSn(entity.getSnfrom());
            i.setWarehouse(entity.getWarehouseFrom());
            i.setPreqty(BigDecimal.ZERO);
            i.setQty(entity.getQtyfrom());//转出入库
            inventoryList.add(i);

            i = new ItemInventory();
            i.setItemmaster(entity.getItemMasterTo());
            i.setColorno(entity.getColornoto());
            i.setBrand(entity.getBrandto());
            i.setBatch(entity.getBatchto());
            i.setSn(entity.getSnto());
            i.setWarehouse(entity.getWarehouseTo());
            i.setPreqty(BigDecimal.ZERO);
            i.setQty(BigDecimal.ZERO.subtract(entity.getQtyto()));//转入出库
            inventoryList.add(i);

            itemInventoryBean.add(inventoryList);

            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ItemExchange verify(ItemExchange entity) {
        InventoryTransaction t;
        ItemInventory i;
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemExchange e = getEntityManager().merge(entity);
            //更新库存交易
            t = new InventoryTransaction();
            t.setTrtype(entity.getTransactionType());
            t.setFormid(entity.getFormid());
            t.setFormdate(entity.getFormdate());
            t.setSeq(1);
            t.setItemmaster(entity.getItemMasterFrom());
            t.setColorno(entity.getColornofrom());
            t.setBrand(entity.getBrandfrom());
            t.setBatch(entity.getBatchfrom());
            t.setSn(entity.getSnfrom());
            t.setQty(entity.getQtyfrom());
            t.setUnit(entity.getUnitfrom());
            t.setAmts(entity.getAmtsfrom());
            t.setWarehouse(entity.getWarehouseFrom());
            t.setIocode(-1);
            t.setProptype(entity.getItemMasterFrom().getProptype());
            t.setMaketype(entity.getItemMasterFrom().getMaketype());
            t.setCostma(entity.getItemMasterFrom().getPurprice().multiply(entity.getQtyfrom()));
            t.setStatus(entity.getStatus());
            t.setCfmuser(e.getCfmuser());
            t.setCfmdate(e.getCfmdate());
            inventoryTransactionBean.setDefaultValue(t);
            inventoryTransactionBean.persist(t);

            t = new InventoryTransaction();
            t.setTrtype(entity.getTransactionType());
            t.setFormid(entity.getFormid());
            t.setFormdate(entity.getFormdate());
            t.setSeq(2);
            t.setItemmaster(entity.getItemMasterTo());
            t.setColorno(entity.getColornoto());
            t.setBrand(entity.getBrandto());
            t.setBatch(entity.getBatchto());
            t.setSn(entity.getSnto());
            t.setQty(entity.getQtyto());
            t.setUnit(entity.getUnitto());
            t.setAmts(entity.getAmtsto());
            t.setWarehouse(entity.getWarehouseTo());
            t.setIocode(1);
            t.setProptype(entity.getItemMasterTo().getProptype());
            t.setMaketype(entity.getItemMasterTo().getMaketype());
            t.setCostma(entity.getItemMasterTo().getPurprice().multiply(entity.getQtyto()));
            t.setStatus(entity.getStatus());
            t.setCfmuser(e.getCfmuser());
            t.setCfmdate(e.getCfmdate());
            inventoryTransactionBean.setDefaultValue(t);
            inventoryTransactionBean.persist(t);

            //更新库存数量
            i = new ItemInventory();
            i.setItemmaster(entity.getItemMasterFrom());
            i.setColorno(entity.getColornofrom());
            i.setBrand(entity.getBrandfrom());
            i.setBatch(entity.getBatchfrom());
            i.setSn(entity.getSnfrom());
            i.setWarehouse(entity.getWarehouseFrom());
            i.setPreqty(BigDecimal.ZERO);
            i.setQty(BigDecimal.ZERO.subtract(entity.getQtyfrom()));//转出出库
            inventoryList.add(i);

            i = new ItemInventory();
            i.setItemmaster(entity.getItemMasterTo());
            i.setColorno(entity.getColornoto());
            i.setBrand(entity.getBrandto());
            i.setBatch(entity.getBatchto());
            i.setSn(entity.getSnto());
            i.setWarehouse(entity.getWarehouseTo());
            i.setPreqty(BigDecimal.ZERO);
            i.setQty(entity.getQtyto());//转入入库
            inventoryList.add(i);

            itemInventoryBean.add(inventoryList);

            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

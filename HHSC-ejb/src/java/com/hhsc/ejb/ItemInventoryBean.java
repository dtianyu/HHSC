/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemInventory;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemInventoryBean extends SuperBean<ItemInventory> {

    @EJB
    private WarehouseBean warehouseBean;

    @EJB
    private ItemMasterBean itemMasterBean;

    public ItemInventoryBean() {
        super(ItemInventory.class);
    }

    public ItemInventory findItemInventory(ItemInventory entity) {
        return findItemInventory(entity.getItemmaster().getItemno(), entity.getColorno(), entity.getBrand(), entity.getBatch(), entity.getSn(), entity.getWarehouse().getWarehouseno());
    }

    public ItemInventory findItemInventory(String itemno, String colorno, String brand, String batch, String sn, String warehousno) {
        if (colorno == null) {
            colorno = "";
        }
        if (brand == null) {
            brand = "";
        }
        if (batch == null) {
            batch = "";
        }
        if (sn == null) {
            sn = "";
        }
        Query query = this.getEntityManager().createNamedQuery("ItemInventory.findItemInventory");
        query.setParameter("itemno", itemno);
        query.setParameter("colorno", colorno);
        query.setParameter("brand", brand);
        query.setParameter("batch", batch);
        query.setParameter("sn", sn);
        query.setParameter("warehouseno", warehousno);
        try {
            return (ItemInventory) query.getSingleResult();
        } catch (Exception e) {
            return null;

        }
    }

    public List<ItemInventory> findItemInventories(ItemInventory entity) {
        return findItemInventories(entity.getItemmaster().getItemno(), entity.getColorno(), entity.getBrand(), entity.getBatch(), entity.getSn());
    }

    public List<ItemInventory> findItemInventories(String itemno, String colorno, String brand, String batch, String sn) {
        if (colorno == null) {
            colorno = "";
        }
        if (brand == null) {
            brand = "";
        }
        if (batch == null) {
            batch = "";
        }
        if (sn == null) {
            sn = "";
        }
        Query query = this.getEntityManager().createNamedQuery("ItemInventory.findItemInventories");
        query.setParameter("itemno", itemno);
        query.setParameter("colorno", colorno);
        query.setParameter("brand", brand);
        query.setParameter("batch", batch);
        query.setParameter("sn", sn);
        return query.getResultList();
    }

    //增加数量                                                    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(ItemInventory entity) throws RuntimeException {
        try {
            setDefaultValue(entity);
            ItemInventory e = findItemInventory(entity);
            if (e == null) {
                persist(entity);
            } else {
                e.setPreqty(e.getPreqty().add(entity.getPreqty()));
                e.setQty(e.getQty().add(entity.getQty()));
                update(e);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    //增加数量
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(List<ItemInventory> entities) {
        for (ItemInventory e : entities) {
            add(e);
        }
    }

    //减少数量
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void subtract(ItemInventory entity) {
        try {
            setDefaultValue(entity);
            ItemInventory e = findItemInventory(entity);
            if (e == null) {
                throw new RuntimeException("找不到库存信息");
            } else {
                e.setPreqty(e.getPreqty().subtract(entity.getPreqty()));
                e.setQty(e.getQty().subtract(entity.getQty()));
                update(e);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    //减少数量
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void subtract(List<ItemInventory> entities) {
        for (ItemInventory e : entities) {
            subtract(e);
        }
    }

    public void setDefaultValue(ItemInventory entity) {
        if (entity.getColorno() == null) {
            entity.setColorno("");
        }
        if (entity.getBrand() == null) {
            entity.setBrand("");
        }
        if (entity.getBatch() == null) {
            entity.setBatch("");
        }
        if (entity.getSn() == null) {
            entity.setSn("");
        }
    }

}

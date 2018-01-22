/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemInventory;
import com.hhds.entity.Itembom;
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
    @EJB
    private ItembomBean itembomBean;

    protected List<SalesOrderDetail> detailList;

    private List<ItemInventory> inventoryList;

    public SalesOrderBean() {
        super(SalesOrder.class);
    }

    public boolean hasInventory(SalesOrderDetail sod) {
        try {
            if ("V".equals(sod.getItemMaster().getMaketype())) {
                //虚拟件按BOM展开计算库存
                List<Itembom> itembomList = itembomBean.findByPId(sod.getItemMaster().getId());
                if (itembomList == null || itembomList.isEmpty()) {
                    return false;
                }
                boolean flag = true;
                for (Itembom bom : itembomList) {
                    VendorItem vi = vendorItemBean.findFirstByItemno(bom.getItemno());
                    ItemInventory i = new ItemInventory();
                    i.setItemmaster(bom.getItemMaster());
                    if (vi != null) {
                        i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
                        i.setBrand(sod.getBrand());
                        i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
                    } else {
                        i.setColorno(sod.getColorno());
                        i.setBrand(sod.getBrand());
                        i.setBatch(sod.getBatch());
                    }
                    i.setSn(sod.getSn());
                    i.setWarehouse(sod.getWarehouse());
                    i.setPreqty(BigDecimal.ZERO);
                    i.setQty(sod.getQty().multiply(bom.getQty()));
                    flag = flag && !itemInventoryBean.isGreatThenInventory(i);
                }
                return flag;
            } else {
                VendorItem vi = vendorItemBean.findFirstByItemno(sod.getItemno());
                ItemInventory i = new ItemInventory();
                i.setItemmaster(sod.getItemMaster());
                if (vi != null) {
                    i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
                    i.setBrand(sod.getBrand());
                    i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
                } else {
                    i.setColorno(sod.getColorno());
                    i.setBrand(sod.getBrand());
                    i.setBatch(sod.getBatch());
                }
                i.setSn(sod.getSn());
                i.setWarehouse(sod.getWarehouse());
                i.setPreqty(BigDecimal.ZERO);
                i.setQty(sod.getQty());
                return !itemInventoryBean.isGreatThenInventory(i);
            }
        } catch (Exception ex) {
            return false;
        }
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
            detailList.forEach((d) -> {
                salesOrderDetailBean.persist(d);
            });
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
                if ("V".equals(sod.getItemMaster().getMaketype())) {
                    //虚拟件按BOM展开计算库存
                    List<Itembom> itembomList = itembomBean.findByPId(sod.getItemMaster().getId());
                    for (Itembom bom : itembomList) {
                        vi = vendorItemBean.findFirstByItemno(bom.getItemno());
                        //更新库存数量
                        ItemInventory i = new ItemInventory();
                        i.setItemmaster(bom.getItemMaster());
                        if (vi != null) {
                            i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
                            i.setBrand(sod.getBrand());
                            i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
                        } else {
                            i.setColorno(sod.getColorno());
                            i.setBrand(sod.getBrand());
                            i.setBatch(sod.getBatch());
                        }
                        i.setSn(sod.getSn());
                        i.setWarehouse(sod.getWarehouse());
                        i.setPreqty(BigDecimal.ZERO);
                        i.setQty(sod.getQty().multiply(bom.getQty()));
                        inventoryList.add(i);
                    }
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
                    t.setAmts(sod.getAmts());
                    t.setWarehouse(sod.getWarehouse());
                    t.setIocode(transactionType.getIocode());
                    t.setProptype("");
                    t.setMaketype("");
                    t.setSrcapi(sod.getSrcapi());
                    t.setSrcformid(sod.getSrcformid());
                    t.setSrcseq(sod.getSrcseq());
                    t.setCostma(BigDecimal.ZERO);
                    t.setStatus(e.getStatus());
                    t.setCfmuser(e.getCfmuser());
                    t.setCfmdate(e.getCfmdate());
                    itembomList.forEach((bom) -> {
                        t.setCostma(t.getCostma().add(bom.getItemMaster().getPurprice().multiply(bom.getQty())));
                    });
                    inventoryTransactionBean.setDefaultValue(t);
                    inventoryTransactionBean.persist(t);
                    //更新订单状态
                    sod.setShipqty(sod.getShipqty().add(sod.getQty()));
                    if (sod.getShipqty().compareTo(sod.getQty()) >= 0) {
                        sod.setStatus("AC");
                    } else {
                        sod.setStatus("50");
                    }
                } else {
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
                    t.setAmts(sod.getAmts());
                    t.setWarehouse(sod.getWarehouse());
                    t.setIocode(transactionType.getIocode());
                    t.setProptype("");
                    t.setMaketype("");
                    t.setSrcapi(sod.getSrcapi());
                    t.setSrcformid(sod.getSrcformid());
                    t.setSrcseq(sod.getSrcseq());
                    t.setCostma(sod.getItemMaster().getPurprice().multiply(sod.getQty()));//记录成本
                    t.setStatus(e.getStatus());
                    t.setCfmuser(e.getCfmuser());
                    t.setCfmdate(e.getCfmdate());
                    inventoryTransactionBean.setDefaultValue(t);
                    inventoryTransactionBean.persist(t);
                    //更新库存数量
                    ItemInventory i = new ItemInventory();
                    i.setItemmaster(sod.getItemMaster());
                    if (vi != null) {
                        i.setColorno(sod.getColorno() == null ? vi.getVendoritemcolor() : sod.getColorno());
                        i.setBrand(sod.getBrand());
                        i.setBatch(sod.getBatch() == null ? vi.getVendordesignno() : sod.getBatch());
                    } else {
                        i.setColorno(sod.getColorno());
                        i.setBrand(sod.getBrand());
                        i.setBatch(sod.getBatch());
                    }
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
                }
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

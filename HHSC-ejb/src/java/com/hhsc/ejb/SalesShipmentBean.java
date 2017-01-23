/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.InventoryTransaction;
import com.hhsc.entity.ItemExchange;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.SalesTransaction;
import com.hhsc.entity.Sysprg;
import com.hhsc.entity.TransactionType;
import com.hhsc.entity.Warehouse;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.SuperEJB;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private SysprgBean sysprgBean;

    @EJB
    private WarehouseBean warehouseBean;

    @EJB
    private ItemExchangeBean itemExchangeBean;

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

    @EJB
    private SalesTransactionBean salesTransactionBean;

    private List<SalesShipmentDetail> detailList;

    private List<ItemInventory> inventoryList;

    public SalesShipmentBean() {
        super(SalesShipment.class);
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(salesShipmentDetailBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
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
            //删除库存交易
            List<InventoryTransaction> itList = inventoryTransactionBean.findByFormid(e.getFormid());
            if (itList != null && !itList.isEmpty()) {
                inventoryTransactionBean.delete(itList);
            }
            //删除出货立账记录
            List<SalesTransaction> stList = salesTransactionBean.findByFormid(e.getFormid());
            if (stList != null && !stList.isEmpty()) {
                salesTransactionBean.delete(stList);
            }
            //删除不良数量记录
            List<ItemExchange> ieList = itemExchangeBean.findByReason(e.getFormid());
            if (ieList != null && !ieList.isEmpty()) {
                ieList.forEach((ie) -> {
                    itemExchangeBean.unverify(ie);
                    itemExchangeBean.delete(ie);
                });
            }
            detailList = salesShipmentDetailBean.findByPId(e.getFormid());
            for (SalesShipmentDetail detail : detailList) {
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
        SalesTransaction st;
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
                t.setWarehouse(detail.getWarehouse());
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
                if (s.getShipqty().compareTo(s.getQty()) >= 0) {
                    s.setStatus("AC");
                } else {
                    s.setStatus("50");
                }
                s.setRelapi("salesshipment");
                s.setRelformid(detail.getPid());
                s.setRelseq(detail.getSeq());
                salesOrderDetailBean.update(s);
                detail.setStatus("50");

                st = salesTransactionBean.createFromSalesShipment(entity, detail);
                salesTransactionBean.persist(st);
                //处理不良数量
                if (detail.getBadqty().compareTo(BigDecimal.ZERO) == 1) {
                    TransactionType exchangeType = transactionTypeBean.findByTrtype("IAE");
                    if (exchangeType == null) {
                        throw new RuntimeException("IAE交易类别没有定义");
                    }
                    Sysprg prg = sysprgBean.findByAPI("itemexchange");
                    String formid = itemExchangeBean.getFormId(entity.getFormdate(), prg.getNolead(), prg.getNoformat(), prg.getNoseqlen());
                    ItemExchange ie = new ItemExchange();
                    ie.setFormid(formid);
                    ie.setFormdate(entity.getFormdate());
                    ie.setTransactionType(exchangeType);
                    ie.setDept(entity.getDept());
                    ie.setSystemUser(entity.getSalesman());
                    ie.setItemMasterFrom(detail.getItemmaster());
                    ie.setItemnofrom(detail.getItemno());
                    ie.setColornofrom(detail.getColorno());
                    ie.setBrandfrom(detail.getBrand());
                    ie.setBatchfrom(detail.getBatch());
                    ie.setSnfrom(detail.getSn());
                    ie.setQtyfrom(detail.getBadqty());
                    ie.setUnitfrom(detail.getUnit());
                    ie.setWarehouseFrom(detail.getWarehouse());

                    ie.setItemMasterTo(detail.getItemmaster());
                    ie.setItemnoto(detail.getItemno());
                    ie.setColornoto(detail.getColorno());
                    ie.setBrandto(detail.getBrand());
                    ie.setBatchto(detail.getBatch());
                    ie.setSnto(detail.getSn());
                    ie.setQtyto(detail.getBadqty());
                    ie.setUnitto(detail.getUnit());
                    ie.setWarehouseTo(detail.getWarehouse2());
                    ie.setReason(entity.getFormid());
                    ie.setRemark("出货单不良数量直接转换");
                    ie.setStatus("V");
                    ie.setCreator(entity.getCreator());
                    ie.setCredate(entity.getCredate());
                    ie.setCfmuser(entity.getCfmuser());
                    ie.setCfmdate(entity.getCfmdate());

                    itemExchangeBean.persist(ie);
                    itemExchangeBean.verify(ie);
                }
            }
            itemInventoryBean.subtract(inventoryList);
            salesShipmentDetailBean.update(detailList);

            return e;
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void initShipment(String api, List<SalesOrderDetailForQuery> details, boolean flag) {

        Date baseDay = BaseLib.getDate();
        Sysprg prg = sysprgBean.findByAPI(api);
        if (prg == null) {
            throw new RuntimeException("获取系统程序设定失败");
        }
        String formid = this.getFormId(baseDay, prg.getNolead(), prg.getNoformat(), prg.getNoseqlen());
        TransactionType transactionType = transactionTypeBean.findByTrtype("SDA");
        if (transactionType == null) {
            throw new RuntimeException("SDA交易类别没有定义");
        }
        Warehouse wh = warehouseBean.findAll().get(0);
        SalesShipmentDetail d;
        HashMap<SuperEJB, List<?>> detailAdded = new HashMap<>();
        detailList = new ArrayList<>();
        detailAdded.put(salesShipmentDetailBean, detailList);
        //产生出货内容
        SalesOrderDetailForQuery sod = details.get(0);
        SalesShipment ss = new SalesShipment();
        ss.setFormid(formid);
        ss.setFormdate(baseDay);
        ss.setShiptype(sod.getSalesOrder().getOrdertype());
        ss.setShipkind(sod.getSalesOrder().getOrderkind());
        ss.setCustomer(sod.getSalesOrder().getCustomer());
        ss.setDept(sod.getSalesOrder().getDept());
        ss.setSalesman(sod.getSalesOrder().getSalesman());
        ss.setCurrency(sod.getSalesOrder().getCurrency());
        ss.setExchange(sod.getSalesOrder().getExchange());
        ss.setTaxtype(sod.getSalesOrder().getTaxtype());
        ss.setTaxkind(sod.getSalesOrder().getTaxkind());
        ss.setTaxrate(sod.getSalesOrder().getTaxrate());
        ss.setTotalextax(BigDecimal.ZERO);
        ss.setTotaltaxes(BigDecimal.ZERO);
        ss.setTotalamts(BigDecimal.ZERO);
        ss.setWarehouse(wh);
        ss.setStatus("N");
        ss.setCreatorToSystem();
        ss.setCredateToNow();
        int i = 0;
        for (SalesOrderDetailForQuery entity : details) {
            i++;
            d = new SalesShipmentDetail();
            d.setPid(formid);
            d.setSeq(i);
            if (!entity.getItemno().equals("A000000")) {
                d.setItemmaster(entity.getSalesOrder().getItemmaster());
                d.setItemno(entity.getSalesOrder().getItemno());
                d.setItemimg(entity.getSalesOrder().getItemimg());
                d.setColorno(entity.getColorno());
                d.setCustomeritemno(entity.getSalesOrder().getCustomeritemno());
                d.setCustomercolorno(entity.getCustomercolorno());
                d.setCustomerrefno(entity.getSalesOrder().getRefno());
                d.setBrand(entity.getBrand());
                d.setBatch(entity.getItemno());
                d.setSn(entity.getSn());
                d.setAllowqty(entity.getQty().subtract(entity.getShipqty()));
                d.setQty(d.getAllowqty());
                d.setUnit(entity.getUnit());
                d.setWarehouse(wh);
                d.setPrice(entity.getPrice());
                d.setSrcapi("salesorder");
                d.setSrcformid(entity.getSalesOrder().getFormid());
                d.setSrcseq(entity.getSeq());
            } else {
                ss.setShipkind(entity.getItemno());
                d.setItemmaster(entity.getItemmaster());
                d.setItemno(entity.getItemno());
                d.setItemimg(entity.getSalesOrder().getItemimg());
                d.setColorno(entity.getColorno());
                d.setCustomeritemno("");
                d.setCustomercolorno("");
                d.setCustomerrefno(entity.getSalesOrder().getRefno());
                d.setBrand(entity.getBrand());
                d.setBatch(entity.getSalesOrder().getItemno());
                d.setSn(entity.getSn());
                d.setAllowqty(entity.getQty().subtract(entity.getShipqty()));
                d.setQty(d.getAllowqty());
                d.setUnit(entity.getUnit());
                d.setWarehouse(wh);
                d.setPrice(entity.getPrice());
                d.setSrcapi("salesorder");
                d.setSrcformid(entity.getSalesOrder().getFormid());
                d.setSrcseq(entity.getSeq());
            }
            //计算税额
            d.setAmts(d.getQty().multiply(d.getPrice()));
            Tax t = BaseLib.getTaxes(ss.getTaxtype(), ss.getTaxkind(), ss.getTaxrate(), d.getAmts(), 2);
            d.setExtax(t.getExtax());
            d.setTaxes(t.getTaxes());
            d.setStatus("40");
            detailList.add(d);
        }
        try {
            this.persist(ss, detailAdded, null, null);
            //直接确认
            if (flag) {
                ss.setStatus("V");
                ss.setCfmuserToSystem();
                ss.setCfmdateToNow();
                this.verify(ss);
            }
        } catch (RuntimeException ex) {
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

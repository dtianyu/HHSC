/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.InventoryTransaction;
import com.hhds.entity.ItemAdjust;
import com.hhds.entity.ItemAdjustDetail;
import com.hhds.entity.ItemInventory;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Sysprg;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemAdjustBean extends SuperBean<ItemAdjust> {

    @EJB
    private SysprgBean systemProgramBean;

    @EJB
    private ItemAdjustDetailBean itemAdjustDetailBean;

    @EJB
    private InventoryTransactionBean inventoryTransactionBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    private List<ItemAdjustDetail> detailList;

    private List<ItemInventory> inventoryList;

    public ItemAdjustBean() {
        super(ItemAdjust.class);
    }

    public String getFormId(Date day) {
        Sysprg sp = systemProgramBean.findBySystemAndAPI("HHDS", "itemadjust");
        if (sp == null) {
            return "";
        }
        return super.getFormId(day, sp.getNolead(), sp.getNoformat(), sp.getNoseqlen());
    }

    @Override
    public void setDetail(Object value) {
        detailList = itemAdjustDetailBean.findByPId(value);
    }

    @Override
    public ItemAdjust unverify(ItemAdjust entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemAdjust e = getEntityManager().merge(entity);
            detailList = itemAdjustDetailBean.findByPId(e.getFormid());
            //删除库存交易
            List<InventoryTransaction> transactionList = inventoryTransactionBean.findByFormid(e.getFormid());
            if (transactionList != null && !transactionList.isEmpty()) {
                inventoryTransactionBean.delete(transactionList);
            }
            for (ItemAdjustDetail d : detailList) {
                //更新库存转出数量
                ItemInventory si = new ItemInventory();
                si.setItemmaster(d.getItemMaster());
                si.setColorno(d.getColorno());
                si.setBrand(d.getBrand());
                si.setBatch(d.getBatch());
                si.setSn(d.getSn());
                si.setWarehouse(d.getWarehouse());
                si.setPreqty(BigDecimal.ZERO);
                si.setQty(d.getQty().multiply(BigDecimal.valueOf(-1)));//出库就 x(-1)
                si.setStatusToNew();
                inventoryList.add(si);
                //更新库存转入数量
                ItemInventory ti = new ItemInventory();
                ti.setItemmaster(d.getItemMaster());
                ti.setColorno(d.getColorno());
                ti.setBrand(d.getBrand());
                ti.setBatch(d.getBatch());
                ti.setSn(d.getSn());
                ti.setWarehouse(d.getWarehouse2());
                ti.setPreqty(BigDecimal.ZERO);
                ti.setQty(d.getQty());//出库就 x(-1)
                ti.setStatusToNew();
                inventoryList.add(ti);
            }
            itemInventoryBean.subtract(inventoryList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ItemAdjust verify(ItemAdjust entity) {
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        } else {
            inventoryList.clear();
        }
        try {
            ItemAdjust e = getEntityManager().merge(entity);
            detailList = itemAdjustDetailBean.findByPId(e.getFormid());
            for (ItemAdjustDetail d : detailList) {
                //更新库存交易转出
                InventoryTransaction st = new InventoryTransaction();
                st.setTrtype(d.getTrtype());
                st.setFormid(e.getFormid());
                st.setFormdate(e.getFormdate());
                st.setSeq(d.getSeq());
                st.setItemmaster(d.getItemMaster());
                st.setColorno(d.getColorno());
                st.setBrand(d.getBrand());
                st.setBatch(d.getBatch());
                st.setSn(d.getSn());
                st.setQty(d.getQty());
                st.setUnit(d.getUnit());
                st.setWarehouse(d.getWarehouse());
                st.setIocode(-1);
                st.setSrcapi(d.getSrcapi());
                st.setSrcformid(d.getSrcformid());
                st.setSrcseq(d.getSrcseq());
                st.setStatus(e.getStatus());
                st.setCfmuser(e.getCfmuser());
                st.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(st);
                inventoryTransactionBean.persist(st);

                //更新库存交易转入
                InventoryTransaction tt = new InventoryTransaction();
                tt.setTrtype(d.getTrtype());
                tt.setFormid(e.getFormid());
                tt.setFormdate(e.getFormdate());
                tt.setSeq(d.getSeq());
                tt.setItemmaster(d.getItemMaster());
                tt.setColorno(d.getColorno());
                tt.setBrand(d.getBrand());
                tt.setBatch(d.getBatch());
                tt.setSn(d.getSn());
                tt.setQty(d.getQty());
                tt.setUnit(d.getUnit());
                tt.setWarehouse(d.getWarehouse2());
                tt.setIocode(1);
                tt.setSrcapi(d.getSrcapi());
                tt.setSrcformid(d.getSrcformid());
                tt.setSrcseq(d.getSrcseq());
                tt.setStatus(e.getStatus());
                tt.setCfmuser(e.getCfmuser());
                tt.setCfmdate(e.getCfmdate());
                inventoryTransactionBean.setDefaultValue(tt);
                inventoryTransactionBean.persist(tt);

                //更新库存转出数量
                ItemInventory si = new ItemInventory();
                si.setItemmaster(d.getItemMaster());
                si.setColorno(d.getColorno());
                si.setBrand(d.getBrand());
                si.setBatch(d.getBatch());
                si.setSn(d.getSn());
                si.setWarehouse(d.getWarehouse());
                si.setPreqty(BigDecimal.ZERO);
                si.setQty(d.getQty().multiply(BigDecimal.valueOf(-1)));//出库就 x(-1)
                si.setStatusToNew();
                inventoryList.add(si);

                //更新库存转入数量
                ItemInventory ti = new ItemInventory();
                ti.setItemmaster(d.getItemMaster());
                ti.setColorno(d.getColorno());
                ti.setBrand(d.getBrand());
                ti.setBatch(d.getBatch());
                ti.setSn(d.getSn());
                ti.setWarehouse(d.getWarehouse2());
                ti.setPreqty(BigDecimal.ZERO);
                ti.setQty(d.getQty());//出库就 x(-1)
                ti.setStatusToNew();
                inventoryList.add(ti);

            }
            //更新库存
            itemInventoryBean.add(inventoryList);
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the detailList
     */
    public List<ItemAdjustDetail> getDetailList() {
        return detailList;
    }

}

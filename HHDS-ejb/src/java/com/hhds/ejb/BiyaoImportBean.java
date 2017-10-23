/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.BiyaoImport;
import com.hhds.entity.Customer;
import com.hhds.entity.ItemMaster;
import com.hhds.entity.SalesOrder;
import com.hhds.entity.SalesOrderDetail;
import com.hhds.entity.VendorItem;
import com.hhds.entity.Warehouse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class BiyaoImportBean extends SuperBean<BiyaoImport> {

    @EJB
    private VendorItemBean vendorItemBean;

    @EJB
    private WarehouseBean warehouseBean;

    @EJB
    private SalesOrderBean salesOrderBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    private ItemMasterBean itemMasterBean;

    @EJB
    private CustomerBean customerBean;

    public BiyaoImportBean() {
        super(BiyaoImport.class);
    }

    public boolean isImported(String value) {
        List<SalesOrderDetail> data = salesOrderDetailBean.findBySrcformid(value);
        if (data == null || data.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verify(List<BiyaoImport> entityList, String warehouseno) {
        int seq = 0;
        BigDecimal totalAmts = BigDecimal.ZERO;
        String formid = "";
        SalesOrder so = null;
        SalesOrderDetail sod;
        Customer c;
        ItemMaster im;
        List<SalesOrderDetail> saelsOrderDetailList = new ArrayList<>();
        Warehouse warehouse = warehouseBean.findByWarehouseno(warehouseno);
        try {
            for (BiyaoImport e : entityList) {

                VendorItem vi = vendorItemBean.findFirstByItemno(e.getItemno());

                if (!formid.equals(e.getFormid())) {
                    if (so != null && !saelsOrderDetailList.isEmpty()) {
                        //保存并且清空前面资料
                        so.setTotalamts(totalAmts);
                        salesOrderBean.persist(so, saelsOrderDetailList);
                        saelsOrderDetailList.clear();
                    }
                    totalAmts = BigDecimal.ZERO;
                    seq = 1;
                    so = new SalesOrder();
                    so.setFormid(e.getFormid());
                    so.setFormdate(e.getFormdate());
                    so.setFormtype(e.getFormtype());
                    so.setFormkind("Biyao");
                    c = customerBean.findByCustomerno(e.getPhone());
                    so.setCustomer(c);
                    so.setCurrency(c.getCurrency());
                    so.setExchange(BigDecimal.ONE);
                    so.setTaxtype(c.getTaxtype());
                    so.setTaxkind(c.getTaxkind());
                    so.setTaxrate(c.getTaxrate());
                    so.setContacter(e.getContacter());
                    so.setMobile(e.getMobile());
                    so.setPhone(e.getPhone());
                    so.setMailadd(e.getMailadd());
                    so.setProvince(e.getProvince());
                    so.setCity(e.getCity());
                    so.setArea(e.getArea());
                    so.setAddress(e.getAddress());
                    so.setZipcode(e.getZipcode());
                    so.setShipmarks(e.getShipmarks());
                    so.setFreight(e.getFreight());
                    so.setInsurance(BigDecimal.ZERO);
                    so.setOthercharges(BigDecimal.ZERO);
                    so.setRemark(e.getRemark());
                    so.setSalesremark(e.getSalesremark());
                    so.setPackremark(e.getPackremark());
                    so.setBill(e.getBill());
                    so.setBilltype(e.getBilltype());
                    so.setBilltitle(e.getBilltitle());
                    so.setUscc(e.getUscc());
                    so.setPaydate(e.getPaydate());
                    so.setPrintdate(e.getPrintdate());
                    so.setFirstdelivery(e.getFirstdelivery());
                    so.setLastdelivery(e.getLastdelivery());
                    so.setDeliverytype(e.getDeliveryno());
                    so.setStatusToNew();
                    so.setCreator(e.getCreator());
                    so.setCredateToNow();

                    sod = new SalesOrderDetail();
                    sod.setPid(e.getFormid());
                    sod.setSeq(seq);
                    im = itemMasterBean.findByItemOID(e.getItemOID());
                    if (im == null) {
                        throw new RuntimeException("找不到商品" + e.getItemOID());
                    }
                    sod.setItemMaster(im);
                    sod.setItemno(e.getItemno());
                    if (vi != null) {
                        sod.setBatch(vi.getVendordesignno());
                        sod.setColorno(vi.getVendoritemcolor());
                    }
                    sod.setQty(e.getQty());
                    sod.setUnit(im.getUnit());
                    sod.setPrice(e.getPrice());
                    sod.setDiscount(e.getDiscount());
                    sod.setAmts(e.getAmts());
                    sod.setExtax(e.getAmts());
                    sod.setTaxes(BigDecimal.ZERO);
                    sod.setProqty(BigDecimal.ZERO);
                    sod.setInqty(BigDecimal.ZERO);
                    sod.setShipqty(BigDecimal.ZERO);
                    sod.setBackqty(BigDecimal.ZERO);
                    sod.setWarehouse(warehouse);
                    sod.setSrcformid(e.getFormid());
                    sod.setSrcseq(Integer.parseInt(e.getRefno()));
                    sod.setStatus("10");
                    //加入明细
                    saelsOrderDetailList.add(sod);
                    //累计金额
                    totalAmts = totalAmts.add(e.getAmts());
                } else {
                    seq++;
                    sod = new SalesOrderDetail();
                    sod.setPid(e.getFormid());
                    sod.setSeq(seq);
                    im = itemMasterBean.findByItemOID(e.getItemOID());
                    if (im == null) {
                        throw new RuntimeException("找不到商品" + e.getItemOID());
                    }
                    sod.setItemMaster(im);
                    sod.setItemno(e.getItemno());
                    if (vi != null) {
                        sod.setBatch(vi.getVendordesignno());
                        sod.setColorno(vi.getVendoritemcolor());
                    }
                    sod.setQty(e.getQty());
                    sod.setUnit(im.getUnit());
                    sod.setPrice(e.getPrice());
                    sod.setDiscount(e.getDiscount());
                    sod.setAmts(e.getAmts());
                    sod.setExtax(e.getAmts());
                    sod.setTaxes(BigDecimal.ZERO);
                    sod.setProqty(BigDecimal.ZERO);
                    sod.setInqty(BigDecimal.ZERO);
                    sod.setShipqty(BigDecimal.ZERO);
                    sod.setBackqty(BigDecimal.ZERO);
                    sod.setWarehouse(warehouse);
                    sod.setSrcformid(e.getFormid());
                    sod.setSrcseq(Integer.parseInt(e.getRefno()));
                    sod.setStatus("10");
                    //加入明细
                    saelsOrderDetailList.add(sod);
                    //累计金额
                    totalAmts = totalAmts.add(e.getAmts());
                }
                formid = e.getFormid();
                e.setStatus("V");
                update(e);
            }
            return true;
        } catch (RuntimeException ex) {
            Logger.getLogger(this.getClass().toString()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}

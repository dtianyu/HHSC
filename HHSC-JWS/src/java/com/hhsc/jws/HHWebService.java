/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.jws;

import com.hhds.entity.PurchaseAcceptance;
import com.hhds.entity.PurchaseAcceptanceDetail;
import com.hhds.entity.PurchaseOrderDetail;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.ItemColor;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.Sysprg;
import com.lightshell.comm.BaseLib;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author kevindong
 */
@WebService(serviceName = "WebService")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HHWebService {

    //EJBForHHSC
    @EJB
    private com.hhsc.ejb.CustomerBean scCustomerBean;
    @EJB
    private com.hhsc.ejb.CustomerItemBean scCustomerItemBean;
    @EJB
    private com.hhsc.ejb.ItemColorBean scItemColorBean;
    @EJB
    private com.hhsc.ejb.ItemMasterBean scItemMasterBean;
    @EJB
    private com.hhsc.ejb.SalesOrderBean scSalesOrderBean;
    @EJB
    private com.hhsc.ejb.SalesShipmentBean scSalesShipmentBean;
    @EJB
    private com.hhsc.ejb.SalesTypeBean scSalesTypeBean;
    @EJB
    private SysprgBean sysprgBean;

    //EJBForHHDS
    @EJB
    private com.hhds.ejb.ItemMasterBean dsItemMasterBean;
    @EJB
    private com.hhds.ejb.PurchaseAcceptanceBean dsPurchaseAcceptanceBean;
    @EJB
    private com.hhds.ejb.PurchaseOrderBean dsPurchaseOrderBean;
    @EJB
    private com.hhds.ejb.PurchaseOrderDetailBean dsPurchaseOrderDetailBean;
    @EJB
    private com.hhds.ejb.VendorBean dsVendorBean;
    @EJB
    private com.hhds.ejb.VendorItemBean dsVendorItemBean;
    @EJB
    private com.hhds.ejb.WarehouseBean dsWarehouseBean;

    /**
     * Web 服务操作
     *
     * @param formid
     * @return
     */
    @WebMethod(operationName = "createHHDSPAByHHSCSS")
    public String createHHDSPAByHHSCSS(@WebParam(name = "formid") String formid) {
        int seq = 0;
        Sysprg prg = sysprgBean.findBySystemAndAPI("HHDS", "purchaseacceptance");
        CustomerItem scci;
        ItemColor scic;
        SalesShipment scss;
        List<SalesShipmentDetail> scssdList;

        com.hhds.entity.Vendor dsvendor;
        com.hhds.entity.VendorItem dsvi;
        com.hhds.entity.ItemMaster dsim;
        com.hhds.entity.Warehouse dswh;
        com.hhds.entity.PurchaseAcceptanceDetail pad;
        List<PurchaseAcceptanceDetail> padList = new ArrayList<>();

        scss = scSalesShipmentBean.findByFormId(formid);
        if (scss == null) {
            throw new NullPointerException("HHSC出货单" + formid + "不存在");
        }
        dsvendor = dsVendorBean.findByVendorno(scss.getCustomer().getRelationno());
        if (dsvendor == null) {
            throw new NullPointerException("HHDS供应商不存在");
        }
        dswh = dsWarehouseBean.findByWarehouseno(scss.getCustomer().getRelationwh());
        if (dswh == null) {
            throw new NullPointerException("HHDS仓库不存在");
        }
        try {
            scSalesShipmentBean.setDetail(formid);
            scssdList = scSalesShipmentBean.getDetailList();
            for (SalesShipmentDetail d : scssdList) {
                scci = scCustomerItemBean.findCustomerItemno(d.getItemno(), scss.getCustomer().getCustomerno(), d.getCustomeritemno());
                if (scci == null) {
                    throw new NullPointerException("没有" + d.getItemno() + "对应的客户品号");
                }
                scic = scItemColorBean.findByItemnoAndColorno(d.getItemno(), d.getColorno(), d.getCustomeritemno(), d.getCustomercolorno());
                if (scic == null) {
                    throw new NullPointerException("没有" + d.getItemno() + "对应的转换品号");
                }
                dsim = dsItemMasterBean.findByItemno(scic.getCustomeritemno());
                if (dsim == null) {
                    throw new NullPointerException("电商品号" + scic.getCustomeritemno() + "不存在");
                }
                dsvi = dsVendorItemBean.findByItemnoAndVendorno(dsim.getItemno(), dsvendor.getVendorno());
                if (dsvi == null) {
                    dsvi = new com.hhds.entity.VendorItem();
                    dsvi.setPid(dsvendor.getId());
                    dsvi.setVendor(dsvendor);
                    dsvi.setSeq(1);
                    dsvi.setItemid(dsim.getId());
                    dsvi.setItemno(dsim.getItemno());
                    dsvi.setVendoritemno(d.getItemno());
                    dsvi.setVendoritemcolor(d.getColorno());
                    dsvi.setVendoritemdesc(d.getItemmaster().getItemdesc());
                    dsvi.setVendoritemspec(d.getItemmaster().getItemspec());
                    dsVendorItemBean.persist(dsvi);
                    dsvi = null;
                }
                seq++;
                pad = new PurchaseAcceptanceDetail();
                pad.setSeq(seq);
                pad.setAcceptdate(scss.getCfmdate());
                pad.setItemmaster(dsim);
                pad.setItemno(scic.getCustomeritemno());
                pad.setVendoritemno(d.getItemno());
                pad.setVendorcolorno(d.getColorno());
                pad.setBrand(d.getBrand());
                pad.setBatch(d.getBatch());
                pad.setSn(d.getSn());
                pad.setAllowqty(d.getQty());
                pad.setQty(d.getQty());
                pad.setUnit(d.getUnit());
                pad.setQcpass(false);
                pad.setQcqty(d.getQty());
                pad.setBadqty(BigDecimal.ZERO);
                pad.setAddqty(BigDecimal.ZERO);
                pad.setWarehouse(dswh);
                pad.setCurrency(scss.getCurrency());
                pad.setExchange(scss.getExchange());
                pad.setTaxtype(scss.getTaxtype());
                pad.setTaxrate(scss.getTaxrate());
                pad.setTaxkind(scss.getTaxkind());
                pad.setPrice(d.getPrice());
                pad.setAmts(d.getAmts());
                pad.setExtax(d.getExtax());
                pad.setTaxes(d.getTaxes());
                pad.setRelapi("HHSC");
                pad.setRelformid(d.getPid());
                pad.setRelseq(d.getSeq());
                pad.setStatus("10");
                if(d.getRelapi().equals("HHDS")){
                    pad.setSrcapi("purchaserorder");
                    pad.setSrcformid(d.getRelformid());
                    pad.setSrcseq(d.getRelseq());
                }
                //加入明细新增列表
                padList.add(pad);
            }
            Date initDay = BaseLib.getDate();
            String id = dsPurchaseAcceptanceBean.getFormId(initDay, prg.getNolead(), prg.getNoformat(), prg.getNoseqlen());
            PurchaseAcceptance pa = new PurchaseAcceptance();
            pa.setFormid(id);
            pa.setFormdate(initDay);
            pa.setVendor(dsvendor);
            pa.setWarehouse(dswh);
            pa.setStatus("N");
            pa.setCreator(scss.getCfmuser());
            pa.setCredateToNow();
            padList.stream().forEach((t) -> {
                t.setPid(id);
            });
            dsPurchaseAcceptanceBean.initByHHSC(pa, padList);
            return "200$" + id;
        } catch (NullPointerException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return "404$" + ex.getMessage();
        }

    }

    @WebMethod(operationName = "createHHSCHHByHHDSPO")
    public String createHHSCHHByHHDSPO(@WebParam(name = "formid") String formid) {
        int seq = 0;
        Date initDay = BaseLib.getDate();
        Sysprg prg = sysprgBean.findBySystemAndAPI("HHSC", "salesorder");
        Customer sccu;
        CustomerItem scci;
        List<CustomerItem> scciList;
        ItemMaster scid, scim;
        SalesOrder scso;
        SalesOrderDetail scsod;
        List<SalesOrderDetail> scsodList = new ArrayList<>();

        com.hhds.entity.PurchaseOrder dspo;
        List<com.hhds.entity.PurchaseOrderDetail> dspodList;
        com.hhds.entity.VendorItem dsvi;

        dspo = dsPurchaseOrderBean.findByFormId(formid);
        if (dspo == null) {
            throw new NullPointerException("HHDS采购单" + formid + "不存在");
        }
        sccu = scCustomerBean.findByFormId(dspo.getVendor().getRelationno());
        if (sccu == null) {
            throw new NullPointerException("HHSC客户不存在");
        }
        try {
            dsPurchaseOrderBean.setDetail(formid);
            dspodList = dsPurchaseOrderBean.getDetailList();
            for (PurchaseOrderDetail d : dspodList) {
                dsvi = dsVendorItemBean.findByItemnoAndVendorno(d.getItemno(), dspo.getVendor().getVendorno());
                if (dsvi == null) {
                    throw new NullPointerException("没有" + d.getItemno() + "对应的供应商品号");
                }
                scid = scItemMasterBean.findByItemno(d.getVendoritemno());
                if (scid == null) {
                    throw new NullPointerException("华卉品号" + d.getVendoritemno() + "不存在");
                }
                scim = scItemMasterBean.findByItemno(d.getBatch());
                if (scim == null) {
                    throw new NullPointerException("华卉品号" + d.getBatch() + "不存在");
                }
                scciList = scCustomerItemBean.findCustomerItemno(d.getVendoritemno(), sccu.getCustomerno());
                scci = scCustomerItemBean.findCustomerItemno(d.getVendoritemno(), sccu.getCustomerno(), d.getItemno());
                if (scci == null) {
                    if (scciList == null || scciList.isEmpty()) {
                        seq = 1;
                    } else {
                        seq = scciList.size() + 1;
                    }
                    scci = new CustomerItem();
                    scci.setPid(sccu.getId());
                    scci.setCustomer(sccu);
                    scci.setSeq(seq);
                    scci.setItemid(scim.getId());
                    scci.setItemno(scim.getItemno());
                    scci.setCustomeritemno(d.getItemno());
                    scci.setCustomeritemdesc(d.getItemmaster().getItemdesc());
                    scci.setCustomeritemspec(d.getItemmaster().getItemspec());
                    scCustomerItemBean.persist(scci);
                }
                String id = scSalesOrderBean.getFormId(initDay, prg.getNolead(), prg.getNoformat(), prg.getNoseqlen());
                //每个采购明细生成一张订单
                scsod = new SalesOrderDetail();
                scsod.setPid(id);
                scsod.setSeq(1);
                scsod.setColorno(d.getVendorcolorno());
                scsod.setItemmaster(scim);
                scsod.setItemno(scim.getItemno());
                scsod.setQty(d.getQty());
                scsod.setUnit(d.getUnit());
                scsod.setPrice(d.getPrice());
                scsod.setDeliverydate(d.getRequestdate());
                scsod.setIssqty(BigDecimal.ZERO);
                scsod.setStatus("00");
                scsod.setSrcapi("HHDS");
                scsod.setSrcformid(d.getPid());
                scsod.setSrcseq(d.getSeq());
                scsod.calcTotalAmts(sccu.getTaxtype(), sccu.getTaxkind(), sccu.getTaxrate());
                //加入明细新增列表
                scsodList.add(scsod);

                scso = new SalesOrder();
                scso.setFormid(id);
                scso.setFormdate(initDay);
                scso.setOrdertype(scSalesTypeBean.findByFormId("JX"));
                scso.setCustomer(sccu);
                scso.setDept(sccu.getDept());
                scso.setSalesman(sccu.getSaler());
                scso.setItemmaster(scid);
                scso.setItemno(scid.getItemno());
                scso.setItemspec(scid.getItemspec());
                scso.setItemimg(scid.getImg1());
                scso.setCustomeritemno(d.getItemno());
                scso.setCurrency(sccu.getCurrency());
                scso.setExchange(BigDecimal.ONE);
                scso.setTaxtype(sccu.getTaxtype());
                scso.setTaxkind(sccu.getTaxkind());
                scso.setTaxrate(sccu.getTaxrate());
                scso.setPayment(sccu.getPayment());
                scso.setRefno(d.getPid());
                scso.setStatus("N");
                scso.setCreator(dspo.getCfmuser());
                scso.setCredateToNow();

                scSalesOrderBean.initByHHDS(scso, scsodList);
                d.setSrcapi("HHSC");
                d.setSrcformid(id);
                d.setSrcseq(scsod.getSeq());
                dsPurchaseOrderDetailBean.update(d);
            }
            return "200$" + "批量抛转成功";
        } catch (NullPointerException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return "404$" + ex.getMessage();
        }

    }

}

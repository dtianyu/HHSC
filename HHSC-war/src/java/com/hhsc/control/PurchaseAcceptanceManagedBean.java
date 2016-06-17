/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.PurchaseAcceptanceBean;
import com.hhsc.ejb.PurchaseAcceptanceDetailBean;
import com.hhsc.ejb.PurchaseOrderDetailBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.PurchaseAcceptance;
import com.hhsc.entity.PurchaseAcceptanceDetail;
import com.hhsc.entity.PurchaseOrderDetail;
import com.hhsc.entity.PurchaseOrderDetailForStorage;
import com.hhsc.entity.Vendor;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.PurchaseAcceptanceModel;
import com.hhsc.web.FormMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class PurchaseAcceptanceManagedBean extends FormMultiBean<PurchaseAcceptance, PurchaseAcceptanceDetail> {

    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private PurchaseOrderDetailBean purchaseOrderDetailBean;
    @EJB
    private PurchaseAcceptanceBean purchaseAcceptanceBean;
    @EJB
    private PurchaseAcceptanceDetailBean purchaseAcceptanceDetailBean;

    protected String queryItemno;
    protected Vendor queryVendor;

    public PurchaseAcceptanceManagedBean() {
        super(PurchaseAcceptance.class, PurchaseAcceptanceDetail.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(getDate());
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        currentEntity = newEntity;
    }

    @Override
    public void createDetail() {
        super.createDetail();
        newDetail.setQcpass(false);
        newDetail.setAllowqty(BigDecimal.ZERO);
        newDetail.setQcqty(BigDecimal.ZERO);
        newDetail.setAddqty(BigDecimal.ZERO);
        newDetail.setBadqty(BigDecimal.ZERO);
        newDetail.setPricetype("0");
        newDetail.setStatus("20");
        newDetail.setCreator(this.userManagedBean.getCurrentUser().getUserid());
        newDetail.setCredate(getDate());
        currentDetail = newDetail;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }//超类中有重新加载明细资料
        for (PurchaseAcceptanceDetail detail : detailList) {
            if (detail.getStatus().equals("50")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", detail.getItemno() + "已入库不可取消"));
                return false;
            }
            ItemInventory i = itemInventoryBean.findItemInventory(detail.getItemno(), detail.getColorno(), detail.getBrand(), detail.getBatch(), detail.getSn(), detail.getWarehouse().getWarehouseno());
            if ((i == null) || (i.getPreqty().compareTo(detail.getQty()) == -1)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "库存可还原量不足"));
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }//超类中有重新加载明细资料
        if (detailList == null || detailList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "没有点收明细"));
            return false;
        }
        PurchaseOrderDetail p;
        for (PurchaseAcceptanceDetail detail : detailList) {
            p = purchaseOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
            if ((p == null) || p.getStatus().equals("AC") || p.getStatus().endsWith("MC")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "采购明细状态错误"));
                return false;
            } else if ((p.getQty().subtract(p.getInqty()).compareTo(detail.getQty()) == -1)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "可点收量不足"));
                return false;
            }
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentDetail.getTaxtype(), this.currentDetail.getTaxkind(), this.currentDetail.getTaxrate(), this.currentDetail.getAmts(), 2);
        this.currentDetail.setExtax(t.getExtax());
        this.currentDetail.setTaxes(t.getTaxes());
        super.doConfirmDetail();
    }

    @Override
    public void init() {
        superEJB = purchaseAcceptanceBean;
        detailEJB = purchaseAcceptanceDetailBean;
        setModel(new PurchaseAcceptanceModel(purchaseAcceptanceBean));
        super.init();
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        //返回供应商
        if (event.getObject() != null) {
            this.newEntity.setVendor((Vendor) event.getObject());
            if (detailList != null) {
                detailList.clear();
            }
            this.addedDetailList.clear();
            this.editedDetailList.clear();
            this.deletedDetailList.clear();
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        //返回供应商
        if (event.getObject() != null) {
            this.currentEntity.setVendor((Vendor) event.getObject());
            if (detailList != null) {
                detailList.clear();
            }
            this.addedDetailList.clear();
            this.editedDetailList.clear();
            this.deletedDetailList.clear();
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        //返回部门
        if (event.getObject() != null) {
            this.newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        //返回部门
        if (event.getObject() != null) {
            this.currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseWhenNew(SelectEvent event) {
        //返回仓库
        if (event.getObject() != null) {
            this.newEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        //返回仓库
        if (event.getObject() != null) {
            this.currentEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        //返回采购明细进行填充
        if (event.getObject() != null) {
            PurchaseOrderDetailForStorage detail = (PurchaseOrderDetailForStorage) event.getObject();
            this.currentDetail.setSrcapi("purchaseorder");
            this.currentDetail.setSrcformid(detail.getPurchaseOrder().getFormid());
            this.currentDetail.setSrcseq(detail.getSeq());
            this.currentDetail.setWarehouse(this.currentEntity.getWarehouse());
            if (detail.getPurchaseOrder().getItemmaster() != null) {
                this.currentDetail.setItemmaster(detail.getPurchaseOrder().getItemmaster());
                this.currentDetail.setItemno(detail.getPurchaseOrder().getItemno());
                this.currentDetail.setColorno(detail.getColorno());
                this.currentDetail.setBatch(detail.getItemno());
            } else {
                this.currentDetail.setItemmaster(detail.getItemmaster());
                this.currentDetail.setItemno(detail.getItemno());
                this.currentDetail.setColorno(detail.getColorno());
            }
            this.currentDetail.setPrice(detail.getPrice());
            this.currentDetail.setAllowqty(detail.getQty().subtract(detail.getInqty()));
            this.currentDetail.setUnit(detail.getUnit());
            this.currentDetail.setPurtype(detail.getPurchaseOrder().getPurtype());
            this.currentDetail.setPurkind(detail.getPurchaseOrder().getPurkind());
            this.currentDetail.setCurrency(detail.getPurchaseOrder().getCurrency());
            this.currentDetail.setExchange(detail.getPurchaseOrder().getExchange());
            this.currentDetail.setTaxtype(detail.getPurchaseOrder().getTaxtype());
            this.currentDetail.setTaxkind(detail.getPurchaseOrder().getTaxkind());
            this.currentDetail.setTaxrate(detail.getPurchaseOrder().getTaxrate());
        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        //返回仓库
        if (event.getObject() != null && currentDetail != null) {
            this.currentDetail.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "purchaseorderdetailSelect":
                if (currentEntity != null && currentEntity.getVendor() != null && currentEntity.getWarehouse() != null) {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> vendorno = new ArrayList<>();
                    vendorno.add(currentEntity.getVendor().getVendorno());
                    params.put("vendorno", vendorno);
                    openDialog(view, params);
                } else if (currentEntity.getVendor() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入供应商"));
                } else if (currentEntity.getWarehouse() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入默认仓库"));
                }
                break;
            default:
                super.openDialog(view);
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

    /**
     * @return the queryVendor
     */
    public Vendor getQueryVendor() {
        return queryVendor;
    }

    /**
     * @param queryVendor the queryVendor to set
     */
    public void setQueryVendor(Vendor queryVendor) {
        this.queryVendor = queryVendor;
    }

}

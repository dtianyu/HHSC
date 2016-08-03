/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.ProductionFinishBean;
import com.hhsc.ejb.ProductionFinishDetailBean;
import com.hhsc.ejb.ProductionOrderDetailBean;
import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.ejb.TransactionTypeBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.ProductionOrderDetail;
import com.hhsc.entity.ProductionFinish;
import com.hhsc.entity.ProductionFinishDetail;
import com.hhsc.entity.ProductionOrderDetailForQuery;
import com.hhsc.entity.SalesType;
import com.hhsc.entity.TransactionType;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.ProductionFinishModel;
import com.hhsc.web.FormMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionFinishManagedBean")
@SessionScoped
public class ProductionFinishManagedBean extends FormMultiBean<ProductionFinish, ProductionFinishDetail> {

    @EJB
    private SalesTypeBean salesTypeBean;
    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private ProductionFinishBean productionFinishBean;
    @EJB
    private ProductionFinishDetailBean productionFinishDetailBean;
    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;

    private List<SalesType> salesTypeList;
    private List<TransactionType> transactionTypeList;

    private Map<String, Object> trtypeFilter;

    public ProductionFinishManagedBean() {
        super(ProductionFinish.class, ProductionFinishDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        this.newEntity.setTransactionType(transactionTypeBean.findByTrtype("MFA"));
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setAllowqty(BigDecimal.ZERO);
        this.newDetail.setQty(BigDecimal.ZERO);
        this.newDetail.setQcqty(BigDecimal.ZERO);
        this.newDetail.setDefqty(BigDecimal.ZERO);
        this.newDetail.setBadqty(BigDecimal.ZERO);
        this.newDetail.setAddqty(BigDecimal.ZERO);
        this.newDetail.setStatus("00");
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            ProductionOrderDetail s;
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (ProductionFinishDetail d : this.addedDetailList) {
                    d.setPid(this.currentEntity.getFormid());

                    if (d.getBadqty().compareTo(BigDecimal.ZERO) == 1 && d.getWarehouse2() == null) {
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "请输入不良仓库");
                        return false;
                    }
                    if (d.getQty().compareTo(d.getQcqty().add(d.getBadqty())) != 0) {
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "良品数量+不良数量<>入库数量");
                        return false;
                    }
                    if (d.getAllowqty().compareTo(d.getQty()) == -1) {
                        d.setQty(d.getAllowqty());
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "入库数量不能大于可入库数量");
                        return false;
                    }
                    s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s == null) {
                        showMsg(FacesMessage.SEVERITY_WARN, "Warn", "找不到流转单");
                        return false;
                    } else {
                        //允许大于计划数
                        //if (s.getOrderqty().subtract(s.getFinqty()).compareTo(d.getQty()) == -1) {
                        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                        //    return false;
                        //}
                    }
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (ProductionFinishDetail d : this.editedDetailList) {
                    d.setPid(this.currentEntity.getFormid());

                    if (d.getBadqty().compareTo(BigDecimal.ZERO) == 1 && d.getWarehouse2() == null) {
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "请输入不良仓库");
                        return false;
                    }
                    if (d.getQty().compareTo(d.getQcqty().add(d.getBadqty())) != 0) {
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "良品数量+不良数量<>入库数量");
                        return false;
                    }
                    if (d.getAllowqty().compareTo(d.getQty()) == -1) {
                        d.setQty(d.getAllowqty());
                        showMsg(FacesMessage.SEVERITY_ERROR, "Error", "入库数量不能大于可入库数量");
                        return false;
                    }
                    s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                    if (s == null) {
                        showMsg(FacesMessage.SEVERITY_WARN, "Warn", "找不到流转单");
                        return false;
                    } else {
                        //if (s.getOrderqty().subtract(s.getFinqty()).compareTo(d.getQty()) == -1) {
                        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                        //    return false;
                        //}
                    }
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        ItemInventory i;
        ProductionOrderDetail s;
        try {
            for (ProductionFinishDetail d : this.detailList) {
                s = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                if (s == null) {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "找不到流转单");
                    return false;
                }
                if (s.getFinqty().compareTo(d.getQty()) < 0) {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "可还原数量不足");
                    return false;
                }
                i = itemInventoryBean.findItemInventory(d.getDesignno(), d.getColorno(), d.getBrand(), d.getItemno(), d.getSn(), d.getWarehouse().getWarehouseno());
                if ((i == null) || (i.getQty().compareTo(d.getQcqty()) == -1)) {
                    showMsg(FacesMessage.SEVERITY_ERROR, "Error", d.getDesignno() + "库存数量不足");
                    return false;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有入库明细");
            return false;
        }
        ProductionOrderDetail productionOrderDetail;
        for (ProductionFinishDetail d : this.detailList) {
            if (d.getBadqty().compareTo(BigDecimal.ZERO) == 1 && d.getWarehouse2() == null) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "请输入不良仓库");
                return false;
            }
            if (d.getQty().compareTo(d.getQcqty().add(d.getBadqty())) != 0) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "良品数量+不良数量<>入库数量");
                return false;
            }
            if (d.getAllowqty().compareTo(d.getQty()) == -1) {
                d.setQty(d.getAllowqty());
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "入库数量不能大于可入库数量");
                return false;
            }
            try {
                productionOrderDetail = productionOrderDetailBean.findByPIdAndSeq(d.getSrcformid(), d.getSrcseq());
                if (productionOrderDetail == null) {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "找不到流转单");
                    return false;
                }
                //if (s.getOrderqty().subtract(s.getFinqty()).compareTo(d.getQty()) < 0) {
                //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "可入库数量不足!"));
                //    return false;
                //}
            } catch (Exception ex) {
                throw ex;
            }
        }
        return true;
    }

    @Override
    public void doConfirmDetail() {
        if (currentDetail != null) {
            currentDetail.setTransactionType(currentEntity.getTransactionType());
            if (currentDetail.getBadqty().compareTo(BigDecimal.ZERO) == 1 && currentDetail.getWarehouse2() == null) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "请输入不良仓库");
                return;
            }
            if (currentDetail.getQty().compareTo(currentDetail.getQcqty().add(currentDetail.getBadqty())) != 0) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", "良品数量+不良数量<>入库数量");
                return;
            }
            if (currentDetail.getAllowqty().compareTo(currentDetail.getQty()) == -1) {
                currentDetail.setQty(currentDetail.getAllowqty());
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "入库数量不能大于可入库数量");
            } else {
                super.doConfirmDetail();
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有入库明细");
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        handleDialogReturnDeptWhenEdit(event);
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnItemDesignWhenNew(SelectEvent event) {
        handleDialogReturnItemDesignWhenEdit(event);
    }

    public void handleDialogReturnItemDesignWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster e = (ItemMaster) event.getObject();
            currentEntity.setSrcitemno(e.getItemno());
        }
    }

    public void handleDialogReturnReasonWhenNew(SelectEvent event) {
        handleDialogReturnReasonWhenEdit(event);
    }

    public void handleDialogReturnReasonWhenEdit(SelectEvent event) {

    }

    public void handleDialogReturnWarehouseWhenNew(SelectEvent event) {
        handleDialogReturnWarehouseWhenEdit(event);
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        handleDialogReturnWhenEdit(event);
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            currentEntity.setSrcitemno(entity.getItemno());
        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            currentDetail.setWarehouse((Warehouse) event.getObject());
        }
    }

    public void handleDialogReturnWarehouse2WhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            currentDetail.setWarehouse2((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ProductionOrderDetailForQuery entity = (ProductionOrderDetailForQuery) event.getObject();
            this.currentDetail.setSrcapi("productionorder");
            this.currentDetail.setSrcformid(entity.getProductionOrder().getFormid());
            this.currentDetail.setSrcseq(entity.getSeq());
            this.currentDetail.setDesign(entity.getProductionOrder().getDesign());
            this.currentDetail.setDesignno(entity.getProductionOrder().getDesignno());
            this.currentDetail.setDesignimg(entity.getProductionOrder().getDesignimg());
            this.currentDetail.setDesignspec(entity.getProductionOrder().getDesignspec());
            this.currentDetail.setColorno(entity.getColorno());
            this.currentDetail.setCustomercolorno(entity.getCustomercolorno());
            this.currentDetail.setCustomeritemno(entity.getCustomeritemno());
            this.currentDetail.setItemmaster(entity.getItemmaster());
            this.currentDetail.setItemno(entity.getItemno());
            this.currentDetail.setBrand(entity.getBrand());
            this.currentDetail.setBatch(entity.getBatch());
            this.currentDetail.setSn(entity.getSn());
            this.currentDetail.setAllowqty(entity.getOrderqty().subtract(entity.getFinqty()));
            this.currentDetail.setQty(entity.getOrderqty().subtract(entity.getFinqty()));
            this.currentDetail.setUnit(entity.getOrderunit());
            this.currentDetail.setWarehouse(currentEntity.getWarehouse());
        }
    }

    @Override
    public void init() {
        setSuperEJB(productionFinishBean);
        setDetailEJB(productionFinishDetailBean);
        setModel(new ProductionFinishModel(productionFinishBean));
        this.model.getFilterFields().put("status", "N");
        salesTypeList = salesTypeBean.findAll();
        trtypeFilter = new HashMap<>();
        trtypeFilter.put("sysid", "PF");
        trtypeFilter.put("iocode", -1);
        this.setTransactionTypeList(transactionTypeBean.findByFilters(trtypeFilter));
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if ("productionorderdetailSelect".equals(view)) {
            if (currentEntity.getSrcformtype() == null) {
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入工单类型");
                return;
            }
            if (currentEntity.getWarehouse() == null) {
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入默认仓库");
                return;
            }
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("contentWidth", 800);

            Map<String, List<String>> params = new HashMap<>();
            List<String> formtype = new ArrayList<>();
            formtype.add(currentEntity.getSrcformtype());
            params.put("formtype", formtype);

            if (currentEntity.getSrcitemno() != null && !"".equals(currentEntity.getSrcitemno())) {
                List<String> itemno = new ArrayList<>();
                itemno.add(currentEntity.getSrcitemno());
                params.put("designno", itemno);
            }
            if (currentEntity.getSrcformid() != null && !"".equals(currentEntity.getSrcformid())) {
                List<String> formid = new ArrayList<>();
                formid.add(currentEntity.getSrcformid());
                params.put("formid", formid);
            }
            super.openDialog(view, options, params);
        } else {
            super.openDialog(view);
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

    /**
     * @return the salesTypeList
     */
    public List<SalesType> getSalesTypeList() {
        return salesTypeList;
    }

    /**
     * @param salesTypeList the salesTypeList to set
     */
    public void setSalesTypeList(List<SalesType> salesTypeList) {
        this.salesTypeList = salesTypeList;
    }

    /**
     * @return the transactionTypeList
     */
    public List<TransactionType> getTransactionTypeList() {
        return transactionTypeList;
    }

    /**
     * @param transactionTypeList the transactionTypeList to set
     */
    public void setTransactionTypeList(List<TransactionType> transactionTypeList) {
        this.transactionTypeList = transactionTypeList;
    }

}

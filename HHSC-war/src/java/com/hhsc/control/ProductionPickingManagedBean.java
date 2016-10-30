/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.ProductionPickingBean;
import com.hhsc.ejb.ProductionPickingDetailBean;
import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.ejb.TransactionTypeBean;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.ProductionOrderDetailForQuery;
import com.hhsc.entity.ProductionPicking;
import com.hhsc.entity.ProductionPickingDetail;
import com.hhsc.entity.SalesType;
import com.hhsc.entity.TransactionType;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.ProductionPickingModel;
import com.hhsc.rpt.ProductionPickingReport;
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
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionPickingManagedBean")
@SessionScoped
public class ProductionPickingManagedBean extends FormMultiBean<ProductionPicking, ProductionPickingDetail> {

    @EJB
    private SalesTypeBean salesTypeBean;
    @EJB
    private TransactionTypeBean transactionTypeBean;

    @EJB
    private ItemInventoryBean itemInventoryBean;

    @EJB
    private ProductionPickingBean productionPickingBean;
    @EJB
    private ProductionPickingDetailBean productionPickingDetailBean;

    private List<SalesType> salesTypeList;
    private List<TransactionType> transactionTypeList;

    private Map<String, Object> trtypeFilter;

    public ProductionPickingManagedBean() {
        super(ProductionPicking.class, ProductionPickingDetail.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setQty(BigDecimal.ZERO);
        setCurrentDetail(newDetail);
    }

    @Override
    public void doConfirmDetail() {
        if (currentEntity != null && currentEntity.getTransactionType() != null) {
            this.currentDetail.setTrtype(currentEntity.getTransactionType().getTrtype());
        }
        super.doConfirmDetail();
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有交易明细!");
            return false;
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (this.detailList == null || this.detailList.isEmpty()) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有交易明细!");
            return false;
        }
        if (currentEntity.getTransactionType().getIocode() == -1) {
            ItemInventory i;
            for (ProductionPickingDetail d : this.detailList) {
                i = itemInventoryBean.findItemInventory(d.getItemno(), d.getColorno(), d.getBrand(), d.getBatch(), d.getSn(), d.getWarehouse().getWarehouseno());
                if ((i == null) || (i.getQty().compareTo(d.getQty()) == -1)) {
                    showMsg(FacesMessage.SEVERITY_ERROR, "Error", d.getItemno() + "库存数量不足");
                    return false;
                }
            }
        }
        return true;
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

    public void handleDialogReturnBatchWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster e = (ItemMaster) event.getObject();
            currentDetail.setBatch(e.getItemno());
        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            currentDetail.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ProductionOrderDetailForQuery entity = (ProductionOrderDetailForQuery) event.getObject();
            currentDetail.setRefitemid(entity.getItemmaster().getId());
            currentDetail.setRefitemno(entity.getItemno());
            currentDetail.setItemmaster(entity.getItemmaster());
            currentDetail.setItemno(entity.getItemno());
            if (!entity.getItemmaster().getItemcategory().getCategory().equals("300")) {
                currentDetail.setColorno(entity.getColorno());
                currentDetail.setBrand(entity.getBrand());
                currentDetail.setBatch(entity.getBatch());
                currentDetail.setSn(entity.getSn());
            }
            currentDetail.setQty(entity.getQty().subtract(entity.getIssqty()));
            currentDetail.setUnit(entity.getItemmaster().getUnit());
            if (currentEntity != null && currentEntity.getWarehouse() != null) {
                currentDetail.setWarehouse(currentEntity.getWarehouse());
            }
            currentDetail.setSrcapi("productionorder");
            currentDetail.setSrcformid(entity.getProductionOrder().getFormid());
            currentDetail.setSrcseq(entity.getSeq());
        }
    }

    @Override
    public void init() {
        setSuperEJB(productionPickingBean);
        setDetailEJB(productionPickingDetailBean);
        setModel(new ProductionPickingModel(productionPickingBean));
        getModel().getFilterFields().put("status", "N");
        this.salesTypeList = salesTypeBean.findAll();
        trtypeFilter = new HashMap<>();
        trtypeFilter.put("sysid", "PP");
        trtypeFilter.put("iocode", -1);
        this.setTransactionTypeList(transactionTypeBean.findByFilters(trtypeFilter));
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if ("productionorderpickingSelect".equals(view)) {
            if (currentEntity.getTransactionType() == null) {
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入领料类别");
                return;
            }
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
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("srcitemno", queryName);
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

    @Override
    protected void reportInitAndConfig() {
        super.reportInitAndConfig();
        reportEngineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, ProductionPickingReport.class.getClassLoader());
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

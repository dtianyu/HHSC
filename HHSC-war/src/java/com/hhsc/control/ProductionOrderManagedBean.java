/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ProcessDetailBean;
import com.hhsc.ejb.ProductionOrderBean;
import com.hhsc.ejb.ProductionOrderDetailBean;
import com.hhsc.ejb.ProductionPrintBean;
import com.hhsc.ejb.ProductionResourceBean;
import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.ProductionOrder;
import com.hhsc.entity.ProductionOrderDetail;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.ProcessDetail;
import com.hhsc.entity.ProcessGroup;
import com.hhsc.entity.ProcessResource;
import com.hhsc.entity.ProductionPrint;
import com.hhsc.entity.ProductionResource;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.entity.SalesType;
import com.hhsc.entity.SystemUser;
import com.hhsc.lazy.ProductionOrderModel;
import com.hhsc.web.FormMulti3Bean;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "productionOrderManagedBean")
@SessionScoped
public class ProductionOrderManagedBean extends FormMulti3Bean<ProductionOrder, ProductionOrderDetail, ProductionResource, ProductionPrint> {

    @EJB
    private SalesTypeBean salesTypeBean;

    @EJB
    private ProductionOrderBean productionOrderBean;
    @EJB
    private ProductionOrderDetailBean productionOrderDetailBean;
    @EJB
    private ProductionPrintBean productionPrintBean;
    @EJB
    private ProductionResourceBean productionResourceBean;
    @EJB
    private ProcessDetailBean processDetailBean;

    protected List<ProductionResource> equipments;
    protected List<ProductionResource> processes;
    protected List<ProductionResource> materials;
    protected List<ProductionResource> hurmans;

    protected List<SalesType> salesTypeList;

    protected String queryDesignno;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public ProductionOrderManagedBean() {
        super(ProductionOrder.class, ProductionOrderDetail.class, ProductionResource.class, ProductionPrint.class);
    }

    public String copyEntity(String path) {
        if (this.currentEntity != null && this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
            //获得原来的明细
            productionOrderBean.setDetail(currentEntity.getFormid());
            if (productionOrderBean.getDetailList().isEmpty()) {
                showWarnMsg("Warn", "没有明细无法复制");
                return "";
            }
            //清空明细新增列表
            if (!this.addedDetailList.isEmpty()) {
                this.addedDetailList.clear();
            }
            try {
                String formid = productionOrderBean.getFormId(getDate(), currentPrgGrant.getSysprg().getNolead(), currentPrgGrant.getSysprg().getNoformat(), currentPrgGrant.getSysprg().getNoseqlen());
                if (!formid.equals("")) {
                    //设定主表
                    ProductionOrder entity = (ProductionOrder) BeanUtils.cloneBean(currentEntity);
                    entity.setId(null);
                    entity.setFormid(formid);
                    entity.setFormdate(getDate());
                    entity.setFormkind("补单");
                    entity.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    entity.setCredate(getDate());
                    entity.setStatus("N");
                    entity.setSalesstatus("N");
                    entity.setJhstatus("N");
                    entity.setJhreaded(Boolean.FALSE);
                    entity.setHgstatus("N");
                    entity.setHgreaded(Boolean.FALSE);
                    entity.setZbstatus("N");
                    entity.setZbreaded(Boolean.FALSE);
                    entity.setPsstatus("N");
                    entity.setPsreaded(Boolean.FALSE);
                    entity.setYhstatus("N");
                    entity.setYhreaded(Boolean.FALSE);
                    entity.setZhstatus("N");
                    entity.setZhreaded(Boolean.FALSE);
                    entity.setSxstatus("N");
                    entity.setSxreaded(Boolean.FALSE);
                    entity.setDxstatus("N");
                    entity.setDxreaded(Boolean.FALSE);
                    entity.setCkstatus("N");
                    entity.setCkreaded(Boolean.FALSE);
                    //设定明细
                    for (ProductionOrderDetail detail : productionOrderBean.getDetailList()) {
                        ProductionOrderDetail d = (ProductionOrderDetail) BeanUtils.cloneBean(detail);
                        d.setId(null);
                        d.setPid(formid);
                        d.setIssqty(BigDecimal.ZERO);
                        d.setFinqty(BigDecimal.ZERO);
                        d.setStatus("00");
                        this.addedDetailList.add(d);
                    }
                    //保存资料
                    productionOrderBean.persist(entity, detailAdded, null, null);
                    //清空明细新增列表
                    this.addedDetailList.clear();
                    return path;
                }
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
                showErrorMsg("Error", ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormdate(getDate());
        this.newEntity.setSalesman(userManagedBean.getCurrentUser().getUsername());
        this.newEntity.setSalesstatus("N");
        this.newEntity.setJhstatus("N");
        this.newEntity.setJhreaded(Boolean.FALSE);
        this.newEntity.setHgstatus("N");
        this.newEntity.setHgreaded(Boolean.FALSE);
        this.newEntity.setZbstatus("N");
        this.newEntity.setZbreaded(Boolean.FALSE);
        this.newEntity.setPsstatus("N");
        this.newEntity.setPsreaded(Boolean.FALSE);
        this.newEntity.setYhstatus("N");
        this.newEntity.setYhreaded(Boolean.FALSE);
        this.newEntity.setZhstatus("N");
        this.newEntity.setZhreaded(Boolean.FALSE);
        this.newEntity.setSxstatus("N");
        this.newEntity.setSxreaded(Boolean.FALSE);
        this.newEntity.setDxstatus("N");
        this.newEntity.setDxreaded(Boolean.FALSE);
        this.newEntity.setCkstatus("N");
        this.newEntity.setCkreaded(Boolean.FALSE);
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setOrderqty(BigDecimal.ZERO);
        this.newDetail.setQty(BigDecimal.ZERO);
        this.newDetail.setIssqty(BigDecimal.ZERO);
        this.newDetail.setFinqty(BigDecimal.ZERO);
        this.newDetail.setStatus("00");
        this.setCurrentDetail(newDetail);
    }

    @Override
    public void createDetail3() {
        super.createDetail3();
        this.newDetail3.setSeq(getMaxSeq(this.detailList3));
        this.newDetail3.setQty(BigDecimal.ONE);
        this.newDetail3.setPrice(BigDecimal.ZERO);
        this.setCurrentDetail3(newDetail3);
    }

    @Override
    protected boolean doBeforeDelete(ProductionOrder entity) throws Exception {
        try {
            ProductionOrder e = (ProductionOrder) superEJB.findById(entity.getId());
            if (e == null || !"N".equals(e.getSalesstatus())) {
                showMsg(FacesMessage.SEVERITY_WARN, "Warn", "数据已被修改");
                return false;
            }
            if (this.detailList != null && !this.detailList.isEmpty()) {
                this.detailList.clear();
            }
            if (this.detailList2 != null && !this.detailList2.isEmpty()) {
                this.detailList2.clear();
            }
            if (this.detailList3 != null && !this.detailList3.isEmpty()) {
                this.detailList3.clear();
            }
            this.detailList = detailEJB.findByPId(entity.getFormid());
            this.detailList2 = detailEJB2.findByPId(entity.getFormid());
            this.detailList3 = detailEJB3.findByPId(entity.getFormid());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getJhstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "计划已确认");
            return false;
        }
        if (!"V".equals(e.getSalesstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "状态已变更");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        if (detailList2 != null && !detailList2.isEmpty()) {
            detailList2.clear();
        }
        if (detailList3 != null && !detailList3.isEmpty()) {
            detailList3.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        detailList2 = detailEJB2.findByPId(currentEntity.getFormid());
        detailList3 = detailEJB3.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!");
            return false;
        }
        ProductionOrder e = (ProductionOrder) superEJB.findById(currentEntity.getId());
        if ("V".equals(e.getSalesstatus())) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "状态已变更!");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        if (detailList2 != null && !detailList2.isEmpty()) {
            detailList2.clear();
        }
        if (detailList3 != null && !detailList3.isEmpty()) {
            detailList3.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        detailList2 = detailEJB2.findByPId(currentEntity.getFormid());
        detailList3 = detailEJB3.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    public void doConfirmDetail() {
        super.doConfirmDetail();
        BigDecimal qty = BigDecimal.ZERO;
        for (ProductionOrderDetail d : this.detailList) {
            qty = qty.add(d.getQty());
        }
        currentEntity.setQty(qty.toString());
    }

    @Override
    public void doConfirmDetail2() {
        super.doConfirmDetail2();
        this.splitResource();
    }

    @Override
    public void deleteDetail2(ProductionResource entity) {
        super.deleteDetail2(entity);
        this.splitResource();
    }

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomerno(entity.getCustomerno());
            this.currentEntity.setCustomer(entity.getCustomer());
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        handleDialogReturnCustomerWhenEdit(event);
    }

    public void handleDialogReturnProcessWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemProcess entity = (ItemProcess) event.getObject();
            this.currentEntity.setItemprocessid(entity.getId());
            this.currentEntity.setJhremark(entity.getFyreq());
            this.currentEntity.setHgreq(entity.getHgreq());
            this.currentEntity.setZbreq(entity.getZbreq());
            this.currentEntity.setPsreq(entity.getPsreq());
            this.currentEntity.setYhreq(entity.getYhreq());
            this.currentEntity.setZhreq(entity.getZhreq());
            this.currentEntity.setSxreq(entity.getSxreq());
            this.currentEntity.setDxreq(entity.getDxreq());
            this.currentEntity.setCkreq(entity.getCkreq());
        }
    }

    public void handleDialogReturnSalesmanWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            SystemUser entity = (SystemUser) event.getObject();
            this.currentEntity.setSalesman(entity.getUsername());
        }
    }

    public void handleDialogReturnSalesmanWhenNew(SelectEvent event) {
        handleDialogReturnSalesmanWhenEdit(event);
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setDesign(entity);
            this.currentEntity.setDesignno(entity.getItemno());
            this.currentEntity.setDesignimg(entity.getImg1());
            this.currentEntity.setDesignspec(entity.getItemspec());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setDesign(entity);
            this.newEntity.setDesignno(entity.getItemno());
            this.newEntity.setDesignimg(entity.getImg1());
            this.newEntity.setDesignspec(entity.getItemspec());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) event.getObject();
            currentDetail.setColorno(entity.getColorno());
            currentDetail.setCustomercolorno(entity.getCustomercolorno());
            currentDetail.setCustomeritemno(entity.getSalesOrder().getCustomeritemno());
            currentDetail.setItemmaster(entity.getItemmaster());
            currentDetail.setItemno(entity.getItemno());
            currentDetail.setBrand(entity.getBrand());
            currentDetail.setBatch(entity.getBatch());
            currentDetail.setSn(entity.getSn());
            currentDetail.setOrderqty(entity.getQty());
            currentDetail.setOrderunit(entity.getUnit());
            currentDetail.setQty(entity.getIssqty());
            currentDetail.setUnit(entity.getItemmaster().getUnit());
            currentDetail.setSrcapi("salesorder");
            currentDetail.setSrcformid(entity.getSalesOrder().getFormid());
            currentDetail.setSrcseq(entity.getSeq());
            currentDetail.setRemark(entity.getRemark());
            currentEntity.setSalesman(entity.getSalesOrder().getSalesman().getUsername());
            if (detailList.isEmpty()) {
                if (currentEntity.getSalesremark() == null) {
                    currentEntity.setSalesremark("");
                }
                if (entity.getSalesOrder().getSalesremark() != null) {
                    currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getSalesremark());
                }
                if (entity.getSalesOrder().getTestremark() != null) {
                    currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getTestremark());
                }
                if (entity.getSalesOrder().getProductremark() != null) {
                    currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getProductremark());
                }
                if (entity.getSalesOrder().getPackremark() != null) {
                    currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getPackremark());
                }
            } else {
                detailList.stream().forEach((e) -> {
                    if (e.getSrcformid() != null && !e.getSrcformid().equals(entity.getSalesOrder().getFormid())) {
                        if (currentEntity.getSalesremark() == null) {
                            currentEntity.setSalesremark("");
                        }
                        if (entity.getSalesOrder().getSalesremark() != null) {
                            currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getSalesremark());
                        }
                        if (entity.getSalesOrder().getTestremark() != null) {
                            currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getTestremark());
                        }
                        if (entity.getSalesOrder().getProductremark() != null) {
                            currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getProductremark());
                        }
                        if (entity.getSalesOrder().getPackremark() != null) {
                            currentEntity.setSalesremark(currentEntity.getSalesremark() + entity.getSalesOrder().getPackremark());
                        }
                    }
                });
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetail2Edit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null && currentDetail2 != null) {
            ProcessResource r = (ProcessResource) event.getObject();
            currentDetail2.setProcess(r.getProcess());
            currentDetail2.setProcseq(r.getSeq());
            currentDetail2.setKind(r.getKind());
            currentDetail2.setItemid(currentEntity.getDesign().getId());
            currentDetail2.setItemno(currentEntity.getDesignno());
            currentDetail2.setContent(r.getContent());
            currentDetail2.setValuetype(r.getValuetype());
            currentDetail2.setBoolvalue(r.getBoolvalue());
            currentDetail2.setNumvalue(r.getNumvalue());
            currentDetail2.setStrvalue(r.getStrvalue());
            currentDetail2.setRemark(r.getRemark());
        }
    }

    public void handleDialogReturnGroupWhenDetail2Edit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null && this.detailList2 != null) {
            ProcessGroup g = (ProcessGroup) event.getObject();
            List<ProcessDetail> processDetails = processDetailBean.findByPId(g.getId());
            if (processDetails != null && !processDetails.isEmpty()) {
                for (ProcessDetail r : processDetails) {
                    this.createDetail2();
                    currentDetail2.setProcess(r.getProcess());
                    currentDetail2.setProcseq(r.getSeq());
                    currentDetail2.setKind(r.getKind());
                    currentDetail2.setItemid(currentEntity.getDesign().getId());
                    currentDetail2.setItemno(currentEntity.getDesignno());
                    currentDetail2.setContent(r.getContent());
                    currentDetail2.setValuetype(r.getValuetype());
                    currentDetail2.setBoolvalue(r.getBoolvalue());
                    currentDetail2.setNumvalue(r.getNumvalue());
                    currentDetail2.setStrvalue(r.getStrvalue());
                    currentDetail2.setRemark(r.getRemark());
                    this.doConfirmDetail2();
                }
            }
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setDesignimg(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setDesignimg(fileName);
        }
    }

    @Override
    public void init() {
        setSuperEJB(productionOrderBean);
        setDetailEJB(productionOrderDetailBean);
        setDetailEJB2(productionResourceBean);
        setDetailEJB3(productionPrintBean);
        setModel(new ProductionOrderModel(productionOrderBean));
        this.model.getFilterFields().put("salesstatus", "N");
        this.model.getSortFields().put("salesstatus", "ASC");
        this.model.getSortFields().put("formid", "DESC");
        this.salesTypeList = salesTypeBean.findAll();
        this.equipments = new ArrayList<>();
        this.processes = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.hurmans = new ArrayList<>();
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if (null != view) {
            switch (view) {
                case "salesorderproductionSelect": {
                    if (currentEntity.getFormtype() == null) {
                        showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入工单类别");
                        return;
                    }
                    if (currentEntity.getDesignno() == null) {
                        showMsg(FacesMessage.SEVERITY_WARN, "Warn", "请输入华卉花号");
                        return;
                    }
                    Map<String, Object> options = new HashMap<>();
                    options.put("modal", true);
                    options.put("contentWidth", 800);
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> formtype = new ArrayList<>();
                    formtype.add(currentEntity.getFormtype().getType());
                    params.put("formtype", formtype);
                    List<String> itemno = new ArrayList<>();
                    itemno.add(currentEntity.getDesignno());
                    params.put("designno", itemno);
                    if (currentEntity.getCustomerno() != null) {
                        List<String> customerno = new ArrayList<>();
                        customerno.add(currentEntity.getCustomerno());
                        params.put("customerno", customerno);
                    }
                    super.openDialog(view, options, params);
                    break;
                }
                case "itemprocessSelect": {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> itemno = new ArrayList<>();
                    itemno.add(currentEntity.getDesignno());
                    params.put("itemno", itemno);
                    super.openDialog(view, params);
                    break;
                }
                default:
                    super.openDialog(view);
                    break;
            }
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
            if (queryDesignno != null && !"".equals(queryDesignno)) {
                this.model.getFilterFields().put("designno", queryDesignno);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("salesstatus", queryState);
            }
        }
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("salesstatus", "N");
        }
    }

    @Override
    public void setDetailList2(List<ProductionResource> detailList2) {
        super.setDetailList2(detailList2);
        if (!detailList2.isEmpty()) {
            splitResource();
        }
    }

    @Override
    public void setToolBar() {
        if (currentEntity != null && getCurrentPrgGrant() != null && currentEntity.getSalesstatus() != null && currentEntity.getJhstatus() != null) {
            if ("V".equals(currentEntity.getJhstatus())) {
                this.doEdit = false;
                this.doDel = false;
                this.doCfm = false;
                this.doUnCfm = false;
            } else {
                switch (currentEntity.getSalesstatus()) {
                    case "V":
                        this.doEdit = false;
                        this.doDel = false;
                        this.doCfm = false;
                        this.doUnCfm = getCurrentPrgGrant().getDouncfm() && true;
                        break;
                    default:
                        this.doEdit = getCurrentPrgGrant().getDoedit() && true;
                        this.doDel = getCurrentPrgGrant().getDodel() && true;
                        this.doCfm = getCurrentPrgGrant().getDocfm() && true;
                        this.doUnCfm = false;
                }
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    protected void splitResource() {
        this.equipments.clear();
        this.processes.clear();
        this.materials.clear();
        this.hurmans.clear();
        for (ProductionResource r : detailList2) {
            switch (r.getKind()) {
                case "E":
                    this.equipments.add(r);
                    break;
                case "P":
                    this.processes.add(r);
                    break;
                case "M":
                    this.materials.add(r);
                    break;
                case "H":
                    this.hurmans.add(r);
                    break;
                default:
            }
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setSalesstatus("N");
                    currentEntity.setJhrecdate(null);
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser("");
                    currentEntity.setCfmdate(null);
                    currentEntity.setStatus("工艺");
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败!"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据!"));
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setSalesstatus("V");
                    currentEntity.setJhrecdate(getDate());
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCfmdateToNow();
                    currentEntity.setStatus("计划");
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "更新前检查失败");
                }
            } catch (Exception e) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    /**
     * @return the queryDesignno
     */
    public String getQueryDesignno() {
        return queryDesignno;
    }

    /**
     * @param queryDesignno the queryDesignno to set
     */
    public void setQueryDesignno(String queryDesignno) {
        this.queryDesignno = queryDesignno;
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
     * @return the equipments
     */
    public List<ProductionResource> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(List<ProductionResource> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the processes
     */
    public List<ProductionResource> getProcesses() {
        return processes;
    }

    /**
     * @param processes the processes to set
     */
    public void setProcesses(List<ProductionResource> processes) {
        this.processes = processes;
    }

    /**
     * @return the materials
     */
    public List<ProductionResource> getMaterials() {
        return materials;
    }

    /**
     * @param materials the materials to set
     */
    public void setMaterials(List<ProductionResource> materials) {
        this.materials = materials;
    }

    /**
     * @return the hurmans
     */
    public List<ProductionResource> getHurmans() {
        return hurmans;
    }

    /**
     * @param hurmans the hurmans to set
     */
    public void setHurmans(List<ProductionResource> hurmans) {
        this.hurmans = hurmans;
    }

}

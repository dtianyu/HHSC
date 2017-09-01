/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.CustomerItemBean;
import com.hhsc.ejb.ItemColorBean;
import com.hhsc.ejb.PurchaseRequestBean;
import com.hhsc.ejb.SalesOrderBean;
import com.hhsc.ejb.SalesOrderDetailBean;
import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Customer;
import com.hhsc.entity.CustomerItem;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemColor;
import com.hhsc.entity.SalesOrder;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.ItemMaster;
import com.hhsc.entity.PurchaseRequest;
import com.hhsc.entity.PurchaseRequestDetail;
import com.hhsc.entity.SalesType;
import com.hhsc.entity.Sysprg;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Unit;
import com.hhsc.lazy.SalesOrderModel;
import com.hhsc.web.FormMultiBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
@ManagedBean(name = "salesOrderManagedBean")
@SessionScoped
public class SalesOrderManagedBean extends FormMultiBean<SalesOrder, SalesOrderDetail> {

    @EJB
    private ItemColorBean itemColorBean;

    @EJB
    private SalesTypeBean salesTypeBean;

    @EJB
    private PurchaseRequestBean purchaseRequestBean;

    @EJB
    private CustomerItemBean customerItemBean;

    @EJB
    private SalesOrderBean salesOrderBean;

    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    private List<SalesType> salesTypeList;

    private Boolean doTransfer;

    private String queryCustomerno;
    private String queryItemno;

    /**
     * Creates a new instance of SalesOrderManagedBean
     */
    public SalesOrderManagedBean() {
        super(SalesOrder.class, SalesOrderDetail.class);
    }

    public String copyEntity(String path) {
        if (this.currentEntity != null && this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getNoauto()) {
            //获得原来的明细
            salesOrderBean.setDetail(currentEntity.getFormid());
            if (salesOrderBean.getDetailList().isEmpty()) {
                showWarnMsg("Warn", "没有明细无法复制");
                return "";
            }
            //清空明细新增列表
            if (!this.addedDetailList.isEmpty()) {
                this.addedDetailList.clear();
            }
            try {
                String formid = salesOrderBean.getFormId(getDate(), currentPrgGrant.getSysprg().getNolead(), currentPrgGrant.getSysprg().getNoformat(), currentPrgGrant.getSysprg().getNoseqlen());
                if (!formid.equals("")) {
                    //设定主表
                    SalesOrder entity = (SalesOrder) BeanUtils.cloneBean(currentEntity);
                    entity.setId(null);
                    entity.setFormid(formid);
                    entity.setFormdate(getDate());
                    entity.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    entity.setCredate(getDate());
                    entity.setStatus("N");
                    //设定明细
                    for (SalesOrderDetail detail : salesOrderBean.getDetailList()) {
                        SalesOrderDetail d = (SalesOrderDetail) BeanUtils.cloneBean(detail);
                        d.setId(null);
                        d.setPid(formid);
                        d.setProqty(BigDecimal.ZERO);
                        d.setInqty(BigDecimal.ZERO);
                        d.setShipqty(BigDecimal.ZERO);
                        d.setRelapi("");
                        d.setRelformid("");
                        d.setRelseq(-1);
                        d.setStatus("00");
                        this.addedDetailList.add(d);
                    }
                    //保存资料
                    salesOrderBean.persist(entity, detailAdded, null, null);
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
        this.newEntity.setSalesman(this.userManagedBean.getCurrentUser());
        this.newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        this.newEntity.setCurrency("CNY");
        this.newEntity.setExchange(BigDecimal.ONE);
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setDesignsets(0);
        this.newEntity.setDesignprice(BigDecimal.ZERO);
        this.newEntity.setTotaldesign(BigDecimal.ZERO);
        this.newEntity.setTotalextax(BigDecimal.ZERO);
        this.newEntity.setTotaltaxes(BigDecimal.ZERO);
        this.newEntity.setTotalamts(BigDecimal.ZERO);
        this.setCurrentEntity(this.newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setQuotedprice(BigDecimal.ZERO);
        this.newDetail.setDiscount(BigDecimal.valueOf(100));
        this.newDetail.setAmts(BigDecimal.ZERO);
        this.newDetail.setExtax(BigDecimal.ZERO);
        this.newDetail.setTaxes(BigDecimal.ZERO);
        this.newDetail.setDeliverydate(this.getDate());
        this.newDetail.setStatus("00");
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可更新数据");
            return false;
        }
        SalesOrder e = salesOrderBean.findById(currentEntity.getId());
        if ("N".equals(e.getStatus())) {
            showErrorMsg("Warn", "状态已变更");
            return false;
        }
        if (salesOrderBean.hasPurchaseRequest(currentEntity.getFormid())) {
            showErrorMsg("Error", "已有请购资料无法还原");
            return false;
        }
        if (salesOrderBean.hasProductionOrder(currentEntity.getFormid())) {
            showErrorMsg("Error", "已有流转单无法还原");
            return false;
        }
        if (detailList != null && !detailList.isEmpty()) {
            detailList.clear();
        }
        detailList = detailEJB.findByPId(currentEntity.getFormid());
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (currentEntity != null && !detailList.isEmpty()) {
            detailList.forEach((d) -> {
                if (!itemColorBean.isExist(currentEntity.getItemno(), d.getColorno(), currentEntity.getCustomeritemno(), d.getCustomercolorno())) {
                    ItemColor ic = new ItemColor();
                    ic.setPid(currentEntity.getItemmaster().getId());
                    ic.setItemno(currentEntity.getItemno());
                    ic.setColorno(d.getColorno());
                    ic.setCustomeritemno(currentEntity.getCustomeritemno() == null ? "" : currentEntity.getCustomeritemno());
                    ic.setCustomercolorno(d.getCustomercolorno() == null ? "" : d.getCustomercolorno());
                    ic.setStatus("N");
                    ic.setCreatorToSystem();
                    ic.setCredateToNow();
                    itemColorBean.persist(ic);
                }
            });
        }
        return super.doBeforeVerify();
    }

    @Override
    public void doConfirmDetail() {
        this.currentDetail.setAmts(this.currentDetail.getQty().multiply(this.currentDetail.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentDetail.getAmts(), 2);
        this.currentDetail.setExtax(t.getExtax());
        this.currentDetail.setTaxes(t.getTaxes());
        super.doConfirmDetail();
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
            this.currentEntity.setItemmaster(null);
            this.currentEntity.setCustomeritemno("");
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            this.currentEntity.setPaymentid(entity.getPaymentid());
            this.currentEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setItemmaster(null);
            this.newEntity.setCustomeritemno("");
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            this.newEntity.setPaymentid(entity.getPaymentid());
            this.newEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            currentEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            newEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setItemmaster(entity);
            this.currentEntity.setItemno(entity.getItemno());
            this.currentEntity.setItemspec(entity.getItemspec());
            this.currentEntity.setItemimg(entity.getImg1());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setItemmaster(entity);
            this.newEntity.setItemno(entity.getItemno());
            this.newEntity.setItemspec(entity.getItemspec());
            this.newEntity.setItemimg(entity.getImg1());
        }
    }

    public void findCustomerItem() {
        if (currentEntity != null && currentEntity.getItemno() != null && currentEntity.getCustomer() != null) {
            CustomerItem o = customerItemBean.findByItemnoAndCustomerno(currentEntity.getItemno(), currentEntity.getCustomer().getCustomerno());
            if (o != null) {
                this.currentEntity.setCustomeritemno(o.getCustomeritemno());
            } else {
                this.currentEntity.setCustomeritemno("");
            }
        }
    }

    public void handleDialogReturnColornoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentDetail.setColorno(ic.getColorno());
            if (Objects.equals(this.getCurrentDetail().getCustomercolorno(), "")) {
                this.currentDetail.setCustomercolorno(ic.getCustomercolorno());
            }
        }
    }

    public void handleDialogReturnCustomerColornoWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemColor ic = (ItemColor) event.getObject();
            this.currentDetail.setCustomercolorno(ic.getCustomercolorno());
            if (Objects.equals(this.getCurrentDetail().getColorno(), "")) {
                this.currentDetail.setColorno(ic.getColorno());
            }
        }
    }

    public void handleDialogReturnUnitWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            this.currentDetail.setUnit(((Unit) event.getObject()).getUnit());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && this.currentDetail != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentDetail.setItemmaster(entity);
            this.currentDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.currentDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        if (event.getObject() != null && this.newDetail != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newDetail.setItemmaster(entity);
            this.newDetail.setItemno(entity.getItemno());
            if (entity.getUnit() != null) {
                this.newDetail.setUnit(entity.getUnit());
            }
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setItemimg(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setItemimg(fileName);
        }
    }

    public void handleTaxtypeChangedWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Object item = event.getObject();
            if (item.equals("2") || item.equals("3")) {
                this.currentEntity.setTaxrate(BigDecimal.ZERO);
            }
        }
    }

    public void handleTaxtypeChangedWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Object item = event.getObject();
            if (item.equals("2") || item.equals("3")) {
                this.newEntity.setTaxrate(BigDecimal.ZERO);
            }
        }
    }

    @Override
    public void init() {
        setSuperEJB(salesOrderBean);
        setDetailEJB(salesOrderDetailBean);
        setModel(new SalesOrderModel(salesOrderBean, userManagedBean));
        getModel().getFilterFields().put("status", "N");
        salesTypeList = salesTypeBean.findAll();
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if (null != view) {
            switch (view) {
                case "customercolorSelect":
                    if (currentEntity != null && !Objects.equals(currentEntity.getItemno(), "") && !Objects.equals(currentEntity.getCustomeritemno(), "")) {
                        Map<String, List<String>> itemcolorParams = new HashMap<>();
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemno());
                        itemcolorParams.put("itemno", itemno);
                        List<String> customeritemno = new ArrayList<>();
                        customeritemno.add(currentEntity.getCustomeritemno());
                        itemcolorParams.put("customeritemno", customeritemno);
                        super.openDialog(view, itemcolorParams);
                    }
                    break;
                case "designspecSelect":
                    if (currentEntity.getItemno() != null) {
                        Map<String, List<String>> designspecParams = new HashMap<>();
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemno());
                        designspecParams.put("itemno", itemno);
                        super.openDialog(view, designspecParams);
                    }
                    break;
                case "itemcolorSelect":
                    if (currentEntity.getItemno() != null) {
                        Map<String, List<String>> itemcolorParams = new HashMap<>();
                        List<String> itemno = new ArrayList<>();
                        itemno.add(currentEntity.getItemno());
                        itemcolorParams.put("itemno", itemno);
                        if (!Objects.equals(currentEntity.getCustomeritemno(), "")) {
                            List<String> customeritemno = new ArrayList<>();
                            customeritemno.add(currentEntity.getCustomeritemno());
                            itemcolorParams.put("customeritemno", customeritemno);
                        }
                        super.openDialog(view, itemcolorParams);
                    }
                    break;
                case "itemmasterSelect":
                    /*
                    不再限制类别TS成品也可以作为明细
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> itemcategory = new ArrayList<>();
                    itemcategory.add("300");
                    itemcategory.add("999");
                    params.put("itemcategory", itemcategory);
                    super.openDialog(view, params);
                     */
                    super.openDialog(view);
                    break;
                case "itemdesignSelect":
                    Map<String, List<String>> itemdesignParams = new HashMap<>();
                    List<String> itemcategory = new ArrayList<>();
                    itemcategory.add("100");
                    itemcategory.add("200");
                    itemdesignParams.put("itemcategory", itemcategory);
                    super.openDialog(view, itemdesignParams);
                    break;
                default:
                    super.openDialog(view);
            }
        }
    }

    public void print(String reportDesignFile) throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据");
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentPrgGrant.getSysprg().getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentPrgGrant.getSysprg().getRptformat() != null) {
            reportFormat = this.currentPrgGrant.getSysprg().getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + reportDesignFile;
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentPrgGrant.getSysprg().getRptclazz()).getClassLoader();
            }
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
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
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
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
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
        }
    }

    @Override
    protected void setToolBar() {
        super.setToolBar();
        if (currentEntity != null && "V".equals(currentEntity.getStatus())) {
            this.doTransfer = true;
        } else {
            this.doTransfer = false;
        }
        if (currentEntity != null && "T".equals(currentEntity.getStatus())) {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = !salesOrderBean.hasPurchaseRequest(currentEntity.getFormid());
        }
    }

    public void transferToPurchaseRequest(String purtype) {

        if (currentEntity == null || this.detailList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可抛转数据"));
            return;
        }
        Sysprg purchaserequestSysprg = null;
        switch (purtype) {
            case "100":
            case "200":
                purchaserequestSysprg = sysprgBean.findBySystemAndAPI("HHSC", "itemfinishedrequest");
                break;
            case "300":
                purchaserequestSysprg = sysprgBean.findBySystemAndAPI("HHSC", "itemmasterrequest");
                break;
        }
        if (purchaserequestSysprg == null) {
            showErrorMsg("Error", "请购作业设定错误");
            return;
        }
        if (!purchaserequestSysprg.getNoauto()) {
            showErrorMsg("Error", "请购单不是自动编号");
            return;
        }
        if (this.userManagedBean.getCurrentUser().getDept() == null) {
            showErrorMsg("Error", "请购人部门未设定");
            return;
        }
        String formid = purchaseRequestBean.getFormId(getDate(), purchaserequestSysprg.getNolead(), purchaserequestSysprg.getNoformat(), purchaserequestSysprg.getNoseqlen());
        if (formid == null || "".equals(formid)) {
            showErrorMsg("Error", "请购单无法自动编号");
            return;
        }
        try {
            PurchaseRequest pr = new PurchaseRequest();
            pr.setFormid(formid);
            pr.setFormdate(getDate());
            pr.setDept(this.userManagedBean.getCurrentUser().getDept());
            pr.setSystemuser(this.userManagedBean.getCurrentUser());
            pr.setPurtype(purtype);
            pr.setRemark(currentEntity.getItemno() + "订单抛转");
            pr.setStatus("N");
            pr.setCreator(this.userManagedBean.getCurrentUser().getUsername());
            pr.setCredateToNow();

            List<PurchaseRequestDetail> requestList = new ArrayList<>();
            int seq = 1;
            for (SalesOrderDetail entity : this.detailList) {
                PurchaseRequestDetail d = new PurchaseRequestDetail();
                d.setSeq(seq);
                d.setPurtype(purtype);
                d.setAbroad(false);
                if ("100".equals(purtype)) {
                    d.setItemdesign(currentEntity.getItemmaster());
                    d.setDesignno(currentEntity.getItemno());
                    d.setDesignspec(currentEntity.getItemspec());
                    d.setCustomeritemno(currentEntity.getCustomeritemno());
                    d.setColorno(entity.getColorno());
                    d.setShipmarks(currentEntity.getShipmarks());
                    d.setTestremark(currentEntity.getTestremark());
                    d.setProductremark(currentEntity.getProductremark());
                    d.setPackremark(currentEntity.getPackremark());
                }
                d.setItemmaster(entity.getItemmaster());
                d.setItemno(entity.getItemno());
                d.setCustomer(currentEntity.getCustomer());
                d.setCustomercolorno(entity.getCustomercolorno());
                if ("300".equals(purtype)) {
                    d.setQty(entity.getIssqty());
                } else {
                    d.setQty(entity.getQty());
                }
                d.setUnit(entity.getUnit());
                d.setPurqty(BigDecimal.ZERO);
                d.setPrice(BigDecimal.ZERO);
                d.setAmts(BigDecimal.ZERO);
                d.setCurrency("CNY");
                d.setExchange(BigDecimal.ONE);
                d.setTaxtype("0");
                d.setTaxkind("VAT17");
                d.setTaxrate(BigDecimal.valueOf(17));
                d.setExtax(BigDecimal.ZERO);
                d.setTaxes(BigDecimal.ZERO);
                d.setRequestdate(getDate());
                d.setDeliverydate(entity.getDeliverydate());
                d.setRemark(entity.getRemark());
                d.setStatus("N");
                d.setSrcapi(this.currentPrgGrant.getSysprg().getApi());
                d.setSrcformid(currentEntity.getFormid());
                d.setSrcseq(entity.getSeq());
                requestList.add(d);
                entity.setStatus("20");
                seq++;
            }
            currentEntity.setStatus("T");
            purchaseRequestBean.persist(pr);//先保存,因为需要得到Id
            salesOrderBean.transferToPurchaseRequest(currentEntity, detailList, pr, requestList);
            setToolBar();//重设工具栏
            showInfoMsg("Info", "抛转请购单成功");
        } catch (Exception e) {
            showErrorMsg("Error", "抛转请购单失败");
        }

    }

    /**
     * @return the queryCustomerno
     */
    public String getQueryCustomerno() {
        return queryCustomerno;
    }

    /**
     * @param queryCustomerno the queryCustomerno to set
     */
    public void setQueryCustomerno(String queryCustomerno) {
        this.queryCustomerno = queryCustomerno;
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
     * @return the doTransfer
     */
    public Boolean getDoTransfer() {
        return doTransfer;
    }

    /**
     * @param doTransfer the doTransfer to set
     */
    public void setDoTransfer(Boolean doTransfer) {
        this.doTransfer = doTransfer;
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemInventoryBean;
import com.hhsc.ejb.SalesOrderDetailBean;
import com.hhsc.ejb.SalesShipmentBean;
import com.hhsc.ejb.SalesShipmentDetailBean;
import com.hhsc.ejb.SalesTypeBean;
import com.hhsc.entity.Currency;
import com.hhsc.entity.Customer;
import com.hhsc.entity.Department;
import com.hhsc.entity.ItemInventory;
import com.hhsc.entity.SalesOrderDetail;
import com.hhsc.entity.SalesShipment;
import com.hhsc.entity.SalesShipmentDetail;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.entity.SalesType;
import com.hhsc.entity.SystemUser;
import com.hhsc.entity.Warehouse;
import com.hhsc.lazy.SalesShipmentModel;
import com.hhsc.rpt.SalesShipmentReport;
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
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesShipmentManagedBean")
@SessionScoped
public class SalesShipmentManagedBean extends FormMultiBean<SalesShipment, SalesShipmentDetail> {

    @EJB
    private ItemInventoryBean itemInventoryBean;
    @EJB
    private SalesTypeBean salesTypeBean;
    @EJB
    private SalesOrderDetailBean salesOrderDetailBean;

    @EJB
    protected SalesShipmentBean salesShipBean;
    @EJB
    protected SalesShipmentDetailBean salesShipmentDetailBean;
    
    private List<SalesType> salesTypeList;

    private String queryCustomerno;

    /**
     * Creates a new instance of SalesShipmentManagedBean
     */
    public SalesShipmentManagedBean() {
        super(SalesShipment.class, SalesShipmentDetail.class);
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
        this.newEntity.setTotalextax(BigDecimal.ZERO);
        this.newEntity.setTotaltaxes(BigDecimal.ZERO);
        this.newEntity.setTotalamts(BigDecimal.ZERO);
        this.setCurrentEntity(this.newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setQty(BigDecimal.ZERO);
        this.newDetail.setAmts(BigDecimal.ZERO);
        this.newDetail.setExtax(BigDecimal.ZERO);
        this.newDetail.setTaxes(BigDecimal.ZERO);
        this.newDetail.setStatus("40");
        this.setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doBeforeUnverify() throws Exception {
        if (!super.doBeforeUnverify()) {
            return false;
        }//超类中有重新加载明细资料
        SalesOrderDetail s;
        for (SalesShipmentDetail detail : detailList) {
            s = salesOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
            if ((s == null) || s.getStatus().equals("10")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "订单明细状态错误"));
                return false;
            } else if (s.getShipqty().compareTo(detail.getQty()) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "订单可还原量不足"));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "没有出货明细"));
            return false;
        }
        SalesOrderDetail s;
        for (SalesShipmentDetail detail : detailList) {
            s = salesOrderDetailBean.findByPIdAndSeq(detail.getSrcformid(), detail.getSrcseq());
            if ((s == null) || s.getStatus().equals("AC") || s.getStatus().endsWith("MC")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "订单明细状态错误"));
                return false;
            } else if ((s.getQty().subtract(s.getShipqty()).compareTo(detail.getQty()) == -1)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "订单可出货量不足"));
                return false;
            }
            ItemInventory i = itemInventoryBean.findItemInventory(detail.getItemno(), detail.getColorno(), detail.getBrand(), detail.getBatch(), detail.getSn(), detail.getWarehouse().getWarehouseno());
            if ((i == null) || (i.getQty().compareTo(detail.getQty()) == -1)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detail.getItemno() + "库存可使用量不足"));
                return false;
            }
        }
        return true;
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
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnDeptWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnDeptWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setDept((Department) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnSalesmanWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setSalesman((SystemUser) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    public void handleDialogReturnWarehouseWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            newEntity.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            if (detailList != null) {
                detailList.clear();
            }
            this.addedDetailList.clear();
            this.editedDetailList.clear();
            this.deletedDetailList.clear();
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            if (detailList != null) {
                detailList.clear();
            }
            this.addedDetailList.clear();
            this.editedDetailList.clear();
            this.deletedDetailList.clear();
        }
    }

    public void handleDialogReturnWarehouseWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            currentDetail.setWarehouse((Warehouse) event.getObject());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null) {
            SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) event.getObject();
            this.currentDetail.setItemmaster(entity.getSalesOrder().getItemmaster());
            this.currentDetail.setItemno(entity.getSalesOrder().getItemno());
            this.currentDetail.setItemimg(entity.getSalesOrder().getItemimg());
            this.currentDetail.setColorno(entity.getColorno());
            this.currentDetail.setCustomeritemno(entity.getSalesOrder().getCustomeritemno());
            this.currentDetail.setCustomercolorno(entity.getCustomercolorno());
            this.currentDetail.setCustomerrefno(entity.getSalesOrder().getRefno());
            this.currentDetail.setBrand(entity.getBrand());
            this.currentDetail.setBatch(entity.getItemno());
            this.currentDetail.setSn(entity.getSn());
            this.currentDetail.setAllowqty(entity.getQty().subtract(entity.getShipqty()));
            this.currentDetail.setQty(this.currentDetail.getAllowqty());
            this.currentDetail.setUnit(entity.getUnit());
            this.currentDetail.setWarehouse(currentEntity.getWarehouse());
            this.currentDetail.setPrice(entity.getPrice());
            this.currentDetail.setSrcapi("salesorder");
            this.currentDetail.setSrcformid(entity.getSalesOrder().getFormid());
            this.currentDetail.setSrcseq(entity.getSeq());
        }
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        handleDialogReturnWhenDetailEdit(event);
    }

    @Override
    public void init() {
        setSuperEJB(salesShipBean);
        setDetailEJB(salesShipmentDetailBean);
        setModel(new SalesShipmentModel(salesShipBean, null));
        getModel().getFilterFields().put("status", "N");
        salesTypeList = salesTypeBean.findAll();
        super.init();
    }

    @Override
    public void openDialog(String view) {
        switch (view) {
            case "salesorderdetailSelect":
                if (currentEntity != null && currentEntity.getShiptype() != null && currentEntity.getCustomer() != null
                        && currentEntity.getCurrency() != null && currentEntity.getTaxtype() != null && currentEntity.getWarehouse() != null) {
                    Map<String, List<String>> params = new HashMap<>();
                    List<String> shiptype = new ArrayList<>();
                    shiptype.add(currentEntity.getShiptype().getType());
                    params.put("shiptype", shiptype);
                    List<String> customerno = new ArrayList<>();
                    customerno.add(currentEntity.getCustomer().getCustomerno());
                    params.put("customerno", customerno);
                    List<String> currency = new ArrayList<>();
                    currency.add(currentEntity.getCurrency());
                    params.put("currency", currency);
                    List<String> taxtype = new ArrayList<>();
                    taxtype.add(currentEntity.getTaxtype());
                    params.put("taxtype", taxtype);
                    List<String> taxrate = new ArrayList<>();
                    taxrate.add(currentEntity.getTaxrate().toString());
                    params.put("taxrate", taxrate);
                    openDialog(view, params);
                } else if (currentEntity.getShiptype() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入出货类别"));
                } else if (currentEntity.getCustomer() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入客户"));
                } else if (currentEntity.getCurrency() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入币别"));
                } else if (currentEntity.getTaxkind() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入税别"));
                } else if (currentEntity.getWarehouse() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "请输入默认仓库"));
                }
                break;
            default:
                super.openDialog(view);
        }
    }

    @Override
    public void print() throws Exception {

        if (currentEntity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据!"));
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentSysprg.getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentSysprg.getRptformat() != null) {
            reportFormat = this.currentSysprg.getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentSysprg.getRptdesign();
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
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
        reportEngineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, SalesShipmentReport.class.getClassLoader());
    }

    @Override
    public void reset() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            this.model.getFilterFields().put("status", "N");
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

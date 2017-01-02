/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.ItemFrameBean;
import com.hhsc.entity.Customer;
import com.hhsc.entity.ItemFrame;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.SalesOrderDetailForQuery;
import com.hhsc.lazy.ItemFrameModel;
import com.hhsc.web.SuperSingleBean;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemFrameManagedBean")
@SessionScoped
public class ItemFrameManagedBean extends SuperSingleBean<ItemFrame> {

    @EJB
    private ItemFrameBean itemFrameBean;

    private String queryFrame;

    public ItemFrameManagedBean() {
        super(ItemFrame.class);
    }

    public String copyEntity(String formtype, String path) {
        if (this.currentEntity != null && this.currentSysprg != null && this.currentSysprg.getNoauto()) {
            try {
                String formid = itemFrameBean.getFormId(getDate(), currentSysprg.getNolead(), currentSysprg.getNoformat(), currentSysprg.getNoseqlen());
                if (!formid.equals("")) {
                    //设定主表
                    ItemFrame entity = (ItemFrame) BeanUtils.cloneBean(currentEntity);
                    entity.setId(null);
                    entity.setFormid(formid);
                    entity.setFormdate(getDate());
                    entity.setFormtype(formtype);
                    entity.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    entity.setCredate(getDate());
                    entity.setStatus("N");
                    entity.setDesignsets(0);
                    entity.setLosssets(0);
                    entity.setLastdate(null);
                    entity.setRemovedate(null);
                    entity.setSrcapi("");
                    entity.setSrcformid("");
                    entity.setSrcseq(0);
                    //保存资料
                    itemFrameBean.persist(entity);
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
        this.newEntity.setFormtype("XZ");
        this.currentEntity = this.newEntity;
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSysprg() != null) {
            if (this.getCurrentSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getFormdate(), this.getCurrentSysprg().getNolead(), this.getCurrentSysprg().getNoformat(), this.getCurrentSysprg().getNoseqlen());
                this.newEntity.setFormid(formid);
            }
            if ("XZ".equals(this.newEntity.getFormtype()) && (this.newEntity.getSrcformid() == null)) {
                this.showErrorMsg("Error", "请输入订单编号");
                return false;
            }
            if (("XZ".equals(this.newEntity.getFormtype()) || "CG".equals(this.newEntity.getFormtype())) && (this.newEntity.getDesignsets() == 0)) {
                this.showErrorMsg("Error", "请输入制版套数");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (!super.doBeforeVerify()) {
            return false;
        }
        if (("XZ".equals(this.currentEntity.getFormtype())) && (this.currentEntity.getSrcformid() == null)) {
            this.showErrorMsg("Error", "请输入订单编号");
            return false;
        }
        if (("XZ".equals(this.currentEntity.getFormtype()) || "CG".equals(this.currentEntity.getFormtype())) && (this.currentEntity.getDesignsets() == 0)) {
            this.showErrorMsg("Error", "请输入制版套数");
            return false;
        }
        return true;
    }

    public void handleDialogReturnCustomerWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
        }
    }

    public void handleDialogReturnCustomerWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
        }
    }

    public void handleDialogReturnSalesOrderWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) event.getObject();
            this.currentEntity.setSrcapi("salesorder");
            this.currentEntity.setSrcformid(entity.getSalesOrder().getFormid());
            this.currentEntity.setSrcseq(entity.getSeq());
            if (this.currentEntity.getCustomer() == null) {
                this.currentEntity.setCustomer(entity.getSalesOrder().getCustomer());
            }
        }
    }

    public void handleDialogReturnSalesOrderWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            SalesOrderDetailForQuery entity = (SalesOrderDetailForQuery) event.getObject();
            this.newEntity.setSrcapi("salesorder");
            this.newEntity.setSrcformid(entity.getSalesOrder().getFormid());
            this.newEntity.setSrcseq(entity.getSeq());
            if (this.newEntity.getCustomer() == null) {
                this.newEntity.setCustomer(entity.getSalesOrder().getCustomer());
            }
        }
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            ItemProcess entity = (ItemProcess) event.getObject();
            this.currentEntity.setItemProcess(entity);
            this.currentEntity.setItemno(entity.getItemno());
            this.currentEntity.setDesignspec(entity.getItemspec());
            this.currentEntity.setDesignsets(entity.getDesignsets());
            this.currentEntity.setRemark(entity.getRemark());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            ItemProcess entity = (ItemProcess) event.getObject();
            this.newEntity.setItemProcess(entity);
            this.newEntity.setItemno(entity.getItemno());
            this.newEntity.setDesignspec(entity.getItemspec());
            this.newEntity.setDesignsets(entity.getDesignsets());
            this.newEntity.setRemark(entity.getRemark());
        }
    }

    @Override
    public void init() {
        this.superEJB = itemFrameBean;
        this.model = new ItemFrameModel(itemFrameBean);
        this.model.getFilterFields().put("status", "N");
        this.model.getSortFields().put("status", "ASC");
        this.model.getSortFields().put("formid", "DESC");
        super.init();
    }

    @Override
    public void openDialog(String view) {
        if (null != view && currentEntity != null) {
            switch (view) {
                case "salesorderdetailSelect":
                    if (!Objects.equals(currentEntity.getItemno(), "")) {
                        Map<String, List<String>> itemParams = new HashMap<>();
                        List<String> designno = new ArrayList<>();
                        designno.add(currentEntity.getItemno());
                        itemParams.put("designno", designno);
                        List<String> itemno = new ArrayList<>();
                        itemno.add("A000000");
                        itemParams.put("itemno", itemno);
                        if (currentEntity.getCustomer() != null) {
                            List<String> customerno = new ArrayList<>();
                            customerno.add(currentEntity.getCustomer().getCustomerno());
                            itemParams.put("customerno", customerno);
                        }
                        super.openDialog(view, itemParams);
                    }
                    break;
                default:
                    super.openDialog(view);
            }
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("itemno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryFrame != null && !"".equals(queryFrame)) {
                this.model.getFilterFields().put("framespec", queryFrame);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "N");
    }

    /**
     * @return the queryFrame
     */
    public String getQueryFrame() {
        return queryFrame;
    }

    /**
     * @param queryFrame the queryFrame to set
     */
    public void setQueryFrame(String queryFrame) {
        this.queryFrame = queryFrame;
    }

}

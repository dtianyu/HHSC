/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.BiyaoImportBean;
import com.hhds.ejb.CustomerBean;
import com.hhds.ejb.ItemCategoryBean;
import com.hhds.ejb.ItemMasterBean;
import com.hhds.entity.BiyaoImport;
import com.hhds.entity.Customer;
import com.hhds.entity.ItemCategory;
import com.hhds.entity.ItemMaster;
import com.hhds.lazy.BiyaoImportModel;
import com.hhds.web.SuperSingleBean;
import com.lightshell.comm.BaseLib;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "biyaoImportManagedBean")
@SessionScoped
public class BiyaoImportManagedBean extends SuperSingleBean<BiyaoImport> {

    @EJB
    private ItemCategoryBean itemCategoryBean;

    @EJB
    private ItemMasterBean itemMasterBean;

    @EJB
    private CustomerBean customerBean;

    @EJB
    private BiyaoImportBean biyaoImportBean;

    private List<BiyaoImport> addedList;

    private BigDecimal importQty;
    private BigDecimal importAmts;

    public BiyaoImportManagedBean() {
        super(BiyaoImport.class);
    }

    @Override
    public void delete() {
        if (entityList != null && !entityList.isEmpty()) {
            superEJB.delete(entityList);
            entityList.clear();
            showInfoMsg("Info", "删除成功");
        }
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        fileName = null;
        doAdd = false;
        return super.doAfterPersist();
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        //排序
        if (getAddedList() == null || getAddedList().isEmpty()) {
            showErrorMsg("Error", "没有可新增资料");
            return false;
        }
        if (getAddedList().size() > 1) {
            getAddedList().sort((BiyaoImport o1, BiyaoImport o2) -> {
                if (o1.getFormid().compareTo(o2.getFormid()) < 1) {
                    return -1;
                } else {
                    return 1;
                }
            });
        }
        return true;
    }

    @Override
    protected boolean doBeforeVerify() throws Exception {
        if (entityList == null || entityList.isEmpty()) {
            showErrorMsg("Error", "没有选择明细资料");
            return false;
        }
        return prepare();
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null) {
            BiyaoImport e;
            if (addedList != null) {
                addedList.clear();
            }
            try {
                InputStream is = new FileInputStream(getAppResPath() + "/" + fileName);
                Workbook wb = WorkbookFactory.create(is);
                Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
                importQty = BigDecimal.ZERO;
                importAmts = BigDecimal.ZERO;
                Cell c;
                for (Row r : sheet) {
                    if (r.getRowNum() == 0) {
                        continue;
                    }
                    e = new BiyaoImport();
                    c = r.getCell(0);
                    e.setFormid(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(1);
                    e.setRefno(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(2);
                    e.setFormtype(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(3);
                    e.setFormdate(BaseLib.convertExcelCell(Date.class, c));
                    c = r.getCell(4);
                    e.setPaydate(BaseLib.convertExcelCell(Date.class, c));
                    c = r.getCell(5);
                    e.setContacter(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(6);
                    e.setPhone(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(7);
                    e.setProvince(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(8);
                    e.setCity(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(9);
                    e.setArea(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(10);
                    e.setAddress(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(11);
                    e.setZipcode(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(12);
                    e.setShipmarks(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(13);
                    e.setRemark(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(14);
                    e.setSalesremark(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(15);
                    e.setBill(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(16);
                    e.setBilltype(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(17);
                    e.setBilltitle(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(18);
                    e.setUscc(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(19);
                    e.setMailadd(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(20);
                    e.setMobile(BaseLib.convertExcelCell(String.class, c).trim());
                    if (e.getMobile() == null || e.getMobile().equals("")) {
                        e.setMobile(e.getPhone());
                    }
                    c = r.getCell(21);
                    e.setItemOID(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(22);
                    e.setItemno(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(23);
                    e.setItemdesc(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(24);
                    e.setQty(BaseLib.convertExcelCell(BigDecimal.class, c));
                    c = r.getCell(25);
                    e.setPrice(BaseLib.convertExcelCell(BigDecimal.class, c));
                    c = r.getCell(26);
                    e.setDiscount(BaseLib.convertExcelCell(BigDecimal.class, c));
                    c = r.getCell(27);
                    e.setAmts(BaseLib.convertExcelCell(BigDecimal.class, c));
                    if (e.getAmts() == null || e.getAmts().compareTo(BigDecimal.ZERO) == 0) {
                        e.setAmts(e.getQty().multiply(e.getPrice()));
                    }
                    c = r.getCell(28);
                    e.setFreight(BaseLib.convertExcelCell(BigDecimal.class, c));
                    c = r.getCell(29);
                    e.setPeriod(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(35);
                    e.setItemspec1(BaseLib.convertExcelCell(String.class, c).trim());
                    c = r.getCell(36);
                    e.setItemspec2(BaseLib.convertExcelCell(String.class, c).trim());
                    e.setStatusToNew();
                    e.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    e.setCredateToNow();
                    importQty = getImportQty().add(e.getQty());
                    importAmts = getImportAmts().add(e.getAmts());
                    addedList.add(e);
                }
                doAdd = true;
                showInfoMsg("Info", "导入成功");
            } catch (FileNotFoundException ex) {
                showErrorMsg("Error", "导入失败,找不到导入文件");
            } catch (IOException | InvalidFormatException ex) {
                showErrorMsg("Error", "导入失败,文件格式错误");
                Logger.getLogger(this.getClass().toString()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void init() {
        doAdd = false;//导入后才显示保存按钮
        setAddedList(new ArrayList<>());
        superEJB = biyaoImportBean;
        model = new BiyaoImportModel(biyaoImportBean);
        model.getFilterFields().put("status", "N");
        super.init();
    }

    @Override
    public void persist() {
        try {
            if (doBeforePersist()) {
                getAddedList().stream().forEach((e) -> {
                    superEJB.persist(e);
                });
                doAfterPersist();
                showInfoMsg("Info", "新增保存成功");
            } else {
                showErrorMsg("Error", "新增前检核失败");
            }
        } catch (Exception ex) {
            showErrorMsg("Error", ex.toString());
        }
    }

    public boolean prepare() {
        if (entityList == null || entityList.isEmpty()) {
            showErrorMsg("Error", "没有选择明细资料");
            return false;
        }
        String itemOID = "";
        String customerno = "";
        ItemCategory itemCategory = itemCategoryBean.findByCategory("Biyao");
        Customer c;
        ItemMaster im;
        if (entityList.size() > 1) {
            entityList.sort((BiyaoImport o1, BiyaoImport o2) -> {
                if (o1.getFormid().compareTo(o2.getFormid()) < 1) {
                    return -1;
                } else {
                    return 1;
                }
            });
        }
        try {
            for (BiyaoImport e : entityList) {
                if (!customerno.equals(e.getPhone())) {
                    customerno = e.getPhone();
                    if (!customerBean.isExist(customerno)) {
                        c = new Customer();
                        c.setSrc("Biyao");
                        c.setCustomerno(e.getPhone());
                        c.setCustomer(e.getContacter());
                        c.setFullname(e.getContacter());
                        c.setTel(e.getPhone());
                        c.setTel2(e.getMobile());
                        c.setCountry(e.getProvince());
                        c.setCity(e.getCity());
                        c.setArea(e.getArea());
                        c.setZipcode(e.getZipcode());
                        c.setContactadd(e.getAddress());
                        c.setShipadd(e.getAddress());
                        c.setStatus("N");
                        c.setCreator(userManagedBean.getCurrentUser().getUsername());
                        c.setCredateToNow();
                        customerBean.persist(c);
                    }
                }
                if (!itemOID.equals(e.getItemOID())) {
                    itemOID = e.getItemOID();
                    im = itemMasterBean.findByItemOID(itemOID);
                    if (im == null) {
                        im = new ItemMaster();
                        im.setItemCategory(itemCategory);
                        im.setItemOID(itemOID);
                        im.setItemno(e.getItemno());
                        im.setItemdesc(e.getItemdesc());
                        im.setItemspec(e.getItemspec1());
                        im.setItemwidth(e.getItemspec2());
                        im.setUnit("PC");
                        im.setStatusToNew();
                        im.setCreator(userManagedBean.getCurrentUser().getUsername());
                        im.setCredateToNow();
                        itemMasterBean.persist(im);
                    }
                }
            }
            showInfoMsg("Info", "更新客户信息品号资料成功");
            return true;
        } catch (Exception ex) {
            showErrorMsg("Error", ex.toString());
            return false;
        }
    }

    @Override
    public void reset() {
        if (addedList != null) {
            addedList.clear();
        }
        fileName = null;
        doAdd = false;
    }

    @Override
    protected void setToolBar() {
        if (getCurrentPrgGrant() != null) {
            this.doEdit = getCurrentPrgGrant().getDoedit();
            this.doDel = getCurrentPrgGrant().getDodel();
            this.doCfm = getCurrentPrgGrant().getDocfm();
            this.doUnCfm = getCurrentPrgGrant().getDouncfm();
        }
    }

    @Override
    public void verify() {
        try {
            if (!doBeforeVerify()) {
                showErrorMsg("Error", "抛转前检核失败");
                return;
            }
            if (biyaoImportBean.verify(entityList)) {
                showInfoMsg("Info", "抛转成功");
            } else {
                showErrorMsg("Error", "抛转失败");
            }
        } catch (Exception ex) {
            Logger.getLogger(BiyaoImportManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMsg("Error", ex.toString());
        }
    }

    /**
     * @return the addedList
     */
    public List<BiyaoImport> getAddedList() {
        return addedList;
    }

    /**
     * @param addedList the addedList to set
     */
    public void setAddedList(List<BiyaoImport> addedList) {
        this.addedList = addedList;
    }

    /**
     * @return the importQty
     */
    public BigDecimal getImportQty() {
        return importQty;
    }

    /**
     * @return the importAmts
     */
    public BigDecimal getImportAmts() {
        return importAmts;
    }

}

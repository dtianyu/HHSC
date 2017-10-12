/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "purchasetransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseTransaction.findAll", query = "SELECT p FROM PurchaseTransaction p")
    ,
    @NamedQuery(name = "PurchaseTransaction.findById", query = "SELECT p FROM PurchaseTransaction p WHERE p.id = :id")
    ,
    @NamedQuery(name = "PurchaseTransaction.findByFormid", query = "SELECT p FROM PurchaseTransaction p WHERE p.formid = :formid")
    ,
    @NamedQuery(name = "PurchaseTransaction.findByFormidAndSeq", query = "SELECT p FROM PurchaseTransaction p WHERE p.formid = :formid AND p.seq = :seq")
    ,
    @NamedQuery(name = "PurchaseTransaction.findByPId", query = "SELECT p FROM PurchaseTransaction p WHERE p.pid = :pid")
    ,
    @NamedQuery(name = "PurchaseTransaction.findByAbroad", query = "SELECT p FROM PurchaseTransaction p WHERE p.abroad = :abroad")
    ,
    @NamedQuery(name = "PurchaseTransaction.findByVendorId", query = "SELECT p FROM PurchaseTransaction p WHERE p.vendor.id = :vendorid")})
public class PurchaseTransaction extends FormDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "formid")
    private String formid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "formdate")
    @Temporal(TemporalType.DATE)
    private Date formdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    private boolean abroad;

    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vendor vendor;

    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Department dept;

    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "exchange")
    private BigDecimal exchange;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "taxtype")
    private String taxtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxrate")
    private BigDecimal taxrate;
    @Size(max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 45)
    @Column(name = "tradename")
    private String tradename;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMaster;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 45)
    @Column(name = "vendoritemno")
    private String vendoritemno;
    @Size(max = 20)
    @Column(name = "vendorcolorno")
    private String vendorcolorno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "extax")
    private BigDecimal extax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 20)
    @Column(name = "pid")
    private String pid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applyqty")
    private BigDecimal applyqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applyamts")
    private BigDecimal applyamts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applyamt")
    private BigDecimal applyamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puramts")
    private BigDecimal puramts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puramt")
    private BigDecimal puramt;
    @Column(name = "preamts")
    private BigDecimal preamts;
    @Column(name = "preamt")
    private BigDecimal preamt;
    @Column(name = "addamts")
    private BigDecimal addamts;
    @Column(name = "addamt")
    private BigDecimal addamt;
    @Column(name = "offamts")
    private BigDecimal offamts;
    @Column(name = "offamt")
    private BigDecimal offamt;
    @Column(name = "shortamts")
    private BigDecimal shortamts;
    @Column(name = "shortamt")
    private BigDecimal shortamt;
    @Column(name = "overamts")
    private BigDecimal overamts;
    @Column(name = "overamt")
    private BigDecimal overamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxamts")
    private BigDecimal taxamts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxamt")
    private BigDecimal taxamt;
    @Column(name = "backqty")
    private BigDecimal backqty;
    @Column(name = "backamts")
    private BigDecimal backamts;
    @Column(name = "backamt")
    private BigDecimal backamt;

    public PurchaseTransaction() {

        this.exchange = BigDecimal.ONE;
        this.pid = "";
        this.applyqty = BigDecimal.ZERO;
        this.applyamts = BigDecimal.ZERO;
        this.applyamt = BigDecimal.ZERO;
        this.preamts = BigDecimal.ZERO;
        this.preamt = BigDecimal.ZERO;
        this.addamts = BigDecimal.ZERO;
        this.addamt = BigDecimal.ZERO;
        this.offamts = BigDecimal.ZERO;
        this.offamt = BigDecimal.ZERO;
        this.shortamts = BigDecimal.ZERO;
        this.shortamt = BigDecimal.ZERO;
        this.overamts = BigDecimal.ZERO;
        this.overamt = BigDecimal.ZERO;
        this.taxamts = BigDecimal.ZERO;
        this.taxamt = BigDecimal.ZERO;
        this.backqty = BigDecimal.ZERO;
        this.backamts = BigDecimal.ZERO;
        this.backamt = BigDecimal.ZERO;

    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public Date getFormdate() {
        return formdate;
    }

    public void setFormdate(Date formdate) {
        this.formdate = formdate;
    }

    public String getPurtype() {
        return purtype;
    }

    public void setPurtype(String purtype) {
        this.purtype = purtype;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(boolean abroad) {
        this.abroad = abroad;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    public String getTaxtype() {
        return taxtype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public String getTaxkind() {
        return taxkind;
    }

    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getVendoritemno() {
        return vendoritemno;
    }

    public void setVendoritemno(String vendoritemno) {
        this.vendoritemno = vendoritemno;
    }

    public String getVendorcolorno() {
        return vendorcolorno;
    }

    public void setVendorcolorno(String vendorcolorno) {
        this.vendorcolorno = vendorcolorno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getExtax() {
        return extax;
    }

    public void setExtax(BigDecimal extax) {
        this.extax = extax;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public String getSrcapi() {
        return srcapi;
    }

    public void setSrcapi(String srcapi) {
        this.srcapi = srcapi;
    }

    public String getSrcformid() {
        return srcformid;
    }

    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    public Integer getSrcseq() {
        return srcseq;
    }

    public void setSrcseq(Integer srcseq) {
        this.srcseq = srcseq;
    }

    public BigDecimal getApplyqty() {
        return applyqty;
    }

    public void setApplyqty(BigDecimal applyqty) {
        this.applyqty = applyqty;
    }

    public BigDecimal getApplyamts() {
        return applyamts;
    }

    public void setApplyamts(BigDecimal applyamts) {
        this.applyamts = applyamts;
    }

    public BigDecimal getApplyamt() {
        return applyamt;
    }

    public void setApplyamt(BigDecimal applyamt) {
        this.applyamt = applyamt;
    }

    public BigDecimal getPuramts() {
        return puramts;
    }

    public void setPuramts(BigDecimal puramts) {
        this.puramts = puramts;
    }

    public BigDecimal getPuramt() {
        return puramt;
    }

    public void setPuramt(BigDecimal puramt) {
        this.puramt = puramt;
    }

    public BigDecimal getPreamts() {
        return preamts;
    }

    public void setPreamts(BigDecimal preamts) {
        this.preamts = preamts;
    }

    public BigDecimal getPreamt() {
        return preamt;
    }

    public void setPreamt(BigDecimal preamt) {
        this.preamt = preamt;
    }

    public BigDecimal getAddamts() {
        return addamts;
    }

    public void setAddamts(BigDecimal addamts) {
        this.addamts = addamts;
    }

    public BigDecimal getAddamt() {
        return addamt;
    }

    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    public BigDecimal getOffamts() {
        return offamts;
    }

    public void setOffamts(BigDecimal offamts) {
        this.offamts = offamts;
    }

    public BigDecimal getOffamt() {
        return offamt;
    }

    public void setOffamt(BigDecimal offamt) {
        this.offamt = offamt;
    }

    public BigDecimal getShortamts() {
        return shortamts;
    }

    public void setShortamts(BigDecimal shortamts) {
        this.shortamts = shortamts;
    }

    public BigDecimal getShortamt() {
        return shortamt;
    }

    public void setShortamt(BigDecimal shortamt) {
        this.shortamt = shortamt;
    }

    public BigDecimal getOveramts() {
        return overamts;
    }

    public void setOveramts(BigDecimal overamts) {
        this.overamts = overamts;
    }

    public BigDecimal getOveramt() {
        return overamt;
    }

    public void setOveramt(BigDecimal overamt) {
        this.overamt = overamt;
    }

    public BigDecimal getTaxamts() {
        return taxamts;
    }

    public void setTaxamts(BigDecimal taxamts) {
        this.taxamts = taxamts;
    }

    public BigDecimal getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(BigDecimal taxamt) {
        this.taxamt = taxamt;
    }

    public BigDecimal getBackqty() {
        return backqty;
    }

    public void setBackqty(BigDecimal backqty) {
        this.backqty = backqty;
    }

    public BigDecimal getBackamts() {
        return backamts;
    }

    public void setBackamts(BigDecimal backamts) {
        this.backamts = backamts;
    }

    public BigDecimal getBackamt() {
        return backamt;
    }

    public void setBackamt(BigDecimal backamt) {
        this.backamt = backamt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseTransaction)) {
            return false;
        }
        PurchaseTransaction other = (PurchaseTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseTransaction[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "purchaserequestdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseDraft.getRowCount", query = "SELECT COUNT(p) FROM PurchaseDraft p"),
    @NamedQuery(name = "PurchaseDraft.findAll", query = "SELECT p FROM PurchaseDraft p"),
    @NamedQuery(name = "PurchaseDraft.findById", query = "SELECT p FROM PurchaseDraft p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseDraft.findByPId", query = "SELECT p FROM PurchaseDraft p WHERE p.purchaserequest.formid = :pid"),
    @NamedQuery(name = "PurchaseDraft.findByPurtype", query = "SELECT p FROM PurchaseDraft p WHERE p.purtype = :purtype"),
    @NamedQuery(name = "PurchaseDraft.findByPurkind", query = "SELECT p FROM PurchaseDraft p WHERE p.purkind = :purkind"),
    @NamedQuery(name = "PurchaseDraft.findByAbroad", query = "SELECT p FROM PurchaseDraft p WHERE p.abroad = :abroad"),
    @NamedQuery(name = "PurchaseDraft.findByItemId", query = "SELECT p FROM PurchaseDraft p WHERE p.itemmaster.id = :itemmasterid"),
    @NamedQuery(name = "PurchaseDraft.findByItemno", query = "SELECT p FROM PurchaseDraft p WHERE p.itemno = :itemno"),
    @NamedQuery(name = "PurchaseDraft.findByCustomerId", query = "SELECT p FROM PurchaseDraft p WHERE p.customer.id = :customerid"),
    @NamedQuery(name = "PurchaseDraft.findByVendorId", query = "SELECT p FROM PurchaseDraft p WHERE p.vendor.id = :vendorid AND p.status='N' "),
    @NamedQuery(name = "PurchaseDraft.findByVendorIdAndItemno", query = "SELECT p FROM PurchaseDraft p WHERE p.vendor.id = :vendorid AND p.itemno=:itemno AND p.status='N' ")})
public class PurchaseDraft extends SuperEntity {

    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne(optional = false)
    private PurchaseRequest purchaserequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Column(name = "abroad")
    private Boolean abroad;
    @JoinColumn(name = "designid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    protected ItemMaster itemdesign;
    @Size(max = 45)
    @Column(name = "designno")
    protected String designno;
    @Size(max = 100)
    @Column(name = "designspec")
    private String designspec;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne
    private Customer customer;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 20)
    @Column(name = "customercolorno")
    private String customercolorno;
    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne
    private Vendor vendor;
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
    @Column(name = "paymentid")
    private Integer paymentid;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "purqty")
    private BigDecimal purqty;
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
    @Column(name = "extax")
    private BigDecimal extax;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requestdate")
    @Temporal(TemporalType.DATE)
    private Date requestdate;
    @Column(name = "requesttime")
    @Temporal(TemporalType.TIME)
    private Date requesttime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deliverydate")
    @Temporal(TemporalType.DATE)
    private Date deliverydate;
    @Column(name = "deliverytime")
    @Temporal(TemporalType.TIME)
    private Date deliverytime;
    @Size(max = 200)
    @Column(name = "deliveryadd")
    private String deliveryadd;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Size(max = 100)
    @Column(name = "relapi")
    private String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    private String relformid;

    public PurchaseDraft() {
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

    public Boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(Boolean abroad) {
        this.abroad = abroad;
    }

    public ItemMaster getItemmaster() {
        return itemmaster;
    }

    public void setItemmaster(ItemMaster itemmaster) {
        this.itemmaster = itemmaster;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
    }

    public String getCustomercolorno() {
        return customercolorno;
    }

    public void setCustomercolorno(String customercolorno) {
        this.customercolorno = customercolorno;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(Date deliverytime) {
        this.deliverytime = deliverytime;
    }

    public String getDeliveryadd() {
        return deliveryadd;
    }

    public void setDeliveryadd(String deliveryadd) {
        this.deliveryadd = deliveryadd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRelapi() {
        return relapi;
    }

    public void setRelapi(String relapi) {
        this.relapi = relapi;
    }

    public String getRelformid() {
        return relformid;
    }

    public void setRelformid(String relformid) {
        this.relformid = relformid;
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
        if (!(object instanceof PurchaseDraft)) {
            return false;
        }
        PurchaseDraft other = (PurchaseDraft) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return (Objects.equals(this.getPurchaserequest().getId(), other.getPurchaserequest().getId()) && this.getSeq() == other.getSeq());
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseDraft[ id=" + id + " ]";
    }

    /**
     * @return the qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * @return the purqty
     */
    public BigDecimal getPurqty() {
        return purqty;
    }

    /**
     * @param purqty the purqty to set
     */
    public void setPurqty(BigDecimal purqty) {
        this.purqty = purqty;
    }

    /**
     * @return the designspec
     */
    public String getDesignspec() {
        return designspec;
    }

    /**
     * @param designspec the designspec to set
     */
    public void setDesignspec(String designspec) {
        this.designspec = designspec;
    }

    /**
     * @return the srcapi
     */
    public String getSrcapi() {
        return srcapi;
    }

    /**
     * @param srcapi the srcapi to set
     */
    public void setSrcapi(String srcapi) {
        this.srcapi = srcapi;
    }

    /**
     * @return the srcformid
     */
    public String getSrcformid() {
        return srcformid;
    }

    /**
     * @param srcformid the srcformid to set
     */
    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

    /**
     * @return the purchaserequest
     */
    public PurchaseRequest getPurchaserequest() {
        return purchaserequest;
    }

    /**
     * @param purchaserequest the purchaserequest to set
     */
    public void setPurchaserequest(PurchaseRequest purchaserequest) {
        this.purchaserequest = purchaserequest;
    }

    /**
     * @return the itemdesign
     */
    public ItemMaster getItemdesign() {
        return itemdesign;
    }

    /**
     * @param itemdesign the itemdesign to set
     */
    public void setItemdesign(ItemMaster itemdesign) {
        this.itemdesign = itemdesign;
    }

    /**
     * @return the designno
     */
    public String getDesignno() {
        return designno;
    }

    /**
     * @param designno the designno to set
     */
    public void setDesignno(String designno) {
        this.designno = designno;
    }

}

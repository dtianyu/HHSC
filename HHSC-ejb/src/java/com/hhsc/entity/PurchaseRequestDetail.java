/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
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
    @NamedQuery(name = "PurchaseRequestDetail.findAll", query = "SELECT p FROM PurchaseRequestDetail p"),
    @NamedQuery(name = "PurchaseRequestDetail.findById", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseRequestDetail.findByPId", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.pid = :pid"),
    @NamedQuery(name = "PurchaseRequestDetail.findByPurtype", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.purtype = :purtype"),
    @NamedQuery(name = "PurchaseRequestDetail.findByPurkind", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.purkind = :purkind"),
    @NamedQuery(name = "PurchaseRequestDetail.findByAbroad", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.abroad = :abroad"),
    @NamedQuery(name = "PurchaseRequestDetail.findByItemId", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.itemmaster.id = :itemmasterid"),
    @NamedQuery(name = "PurchaseRequestDetail.findByItemno", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.itemno = :itemno"),
    @NamedQuery(name = "PurchaseRequestDetail.findByCustomerId", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.customer.id = :customerid"),
    @NamedQuery(name = "PurchaseRequestDetail.findByVendorId", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.vendor.id = :vendorid"),
    @NamedQuery(name = "PurchaseRequestDetail.findBySrcformid", query = "SELECT p FROM PurchaseRequestDetail p WHERE p.srcformid = :srcformid")})
public class PurchaseRequestDetail extends FormDetailEntity {

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
    @ManyToOne(optional = true)
    private Customer customer;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 100)
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
    @Column(name = "shipmarks")
    private String shipmarks;
    @Size(max = 200)
    @Column(name = "testremark")
    private String testremark;
    @Size(max = 200)
    @Column(name = "productremark")
    private String productremark;
    @Size(max = 200)
    @Column(name = "packremark")
    private String packremark;
        
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    protected Integer srcseq;
    @Size(max = 100)
    @Column(name = "relapi")
    private String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    private String relformid;
    @Column(name = "relseq")
    protected Integer relseq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    public PurchaseRequestDetail() {
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
        if (!(object instanceof PurchaseRequestDetail)) {
            return false;
        }
        PurchaseRequestDetail other = (PurchaseRequestDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return (Objects.equals(this.pid, other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseRequestDetail[ id=" + id + " ]";
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the credate
     */
    public Date getCredate() {
        return credate;
    }

    /**
     * @param credate the credate to set
     */
    public void setCredate(Date credate) {
        this.credate = credate;
    }

    /**
     * @return the optuser
     */
    public String getOptuser() {
        return optuser;
    }

    /**
     * @param optuser the optuser to set
     */
    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    /**
     * @return the optdate
     */
    public Date getOptdate() {
        return optdate;
    }

    /**
     * @param optdate the optdate to set
     */
    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    /**
     * @return the cfmuser
     */
    public String getCfmuser() {
        return cfmuser;
    }

    /**
     * @param cfmuser the cfmuser to set
     */
    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    /**
     * @return the cfmdate
     */
    public Date getCfmdate() {
        return cfmdate;
    }

    /**
     * @param cfmdate the cfmdate to set
     */
    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    /**
     * @return the srcseq
     */
    public Integer getSrcseq() {
        return srcseq;
    }

    /**
     * @param srcseq the srcseq to set
     */
    public void setSrcseq(Integer srcseq) {
        this.srcseq = srcseq;
    }

    /**
     * @return the relseq
     */
    public Integer getRelseq() {
        return relseq;
    }

    /**
     * @param relseq the relseq to set
     */
    public void setRelseq(Integer relseq) {
        this.relseq = relseq;
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

    /**
     * @return the shipmarks
     */
    public String getShipmarks() {
        return shipmarks;
    }

    /**
     * @param shipmarks the shipmarks to set
     */
    public void setShipmarks(String shipmarks) {
        this.shipmarks = shipmarks;
    }

    /**
     * @return the testremark
     */
    public String getTestremark() {
        return testremark;
    }

    /**
     * @param testremark the testremark to set
     */
    public void setTestremark(String testremark) {
        this.testremark = testremark;
    }

    /**
     * @return the productremark
     */
    public String getProductremark() {
        return productremark;
    }

    /**
     * @param productremark the productremark to set
     */
    public void setProductremark(String productremark) {
        this.productremark = productremark;
    }

    /**
     * @return the packremark
     */
    public String getPackremark() {
        return packremark;
    }

    /**
     * @param packremark the packremark to set
     */
    public void setPackremark(String packremark) {
        this.packremark = packremark;
    }

}

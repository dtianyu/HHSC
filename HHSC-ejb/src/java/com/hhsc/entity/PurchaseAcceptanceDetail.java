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
@Table(name = "purchaseacceptancedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseAcceptanceDetail.findAll", query = "SELECT p FROM PurchaseAcceptanceDetail p")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findById", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.id = :id")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByPId", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.pid = :pid")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByAcceptdate", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.acceptdate = :acceptdate")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByPurtype", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.purtype = :purtype")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByPurkind", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.purkind = :purkind")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByItemno", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.itemno = :itemno")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByColorno", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.colorno = :colorno")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByVendoritemno", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.vendoritemno = :vendoritemno")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByVendorcolorno", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.vendorcolorno = :vendorcolorno")
    ,
    @NamedQuery(name = "PurchaseAcceptanceDetail.findByStatus", query = "SELECT p FROM PurchaseAcceptanceDetail p WHERE p.status = :status")})
public class PurchaseAcceptanceDetail extends FormDetailEntity {

    @Column(name = "acceptdate")
    @Temporal(TemporalType.DATE)
    private Date acceptdate;
    @Column(name = "acceptdept")
    private Integer acceptdept;
    @JoinColumn(name = "acceptuser", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemUser acceptuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;

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
    @Column(name = "allowqty")
    private BigDecimal allowqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qcpass")
    private boolean qcpass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qcqty")
    private BigDecimal qcqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "badqty")
    private BigDecimal badqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "addqty")
    private BigDecimal addqty;
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    @JoinColumn(name = "badwarehouse", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse badwarehouse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
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
    @Column(name = "taxrate")
    private BigDecimal taxrate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    @Size(max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 45)
    @Column(name = "tradename")
    private String tradename;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "pricetype")
    private String pricetype;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 100)
    @Column(name = "relapi")
    private String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    private String relformid;
    @Column(name = "relseq")
    private Integer relseq;
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

    public PurchaseAcceptanceDetail() {
    }

    public Date getAcceptdate() {
        return acceptdate;
    }

    public void setAcceptdate(Date acceptdate) {
        this.acceptdate = acceptdate;
    }

    public Integer getAcceptdept() {
        return acceptdept;
    }

    public void setAcceptdept(Integer acceptdept) {
        this.acceptdept = acceptdept;
    }

    public SystemUser getAcceptuser() {
        return acceptuser;
    }

    public void setAcceptuser(SystemUser acceptuser) {
        this.acceptuser = acceptuser;
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

    public boolean getQcpass() {
        return qcpass;
    }

    public void setQcpass(boolean qcpass) {
        this.qcpass = qcpass;
    }

    public BigDecimal getQcqty() {
        return qcqty;
    }

    public void setQcqty(BigDecimal qcqty) {
        this.qcqty = qcqty;
    }

    public BigDecimal getBadqty() {
        return badqty;
    }

    public void setBadqty(BigDecimal badqty) {
        this.badqty = badqty;
    }

    public BigDecimal getAddqty() {
        return addqty;
    }

    public void setAddqty(BigDecimal addqty) {
        this.addqty = addqty;
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

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public String getTaxkind() {
        return taxkind;
    }

    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
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

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
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

    public Integer getRelseq() {
        return relseq;
    }

    public void setRelseq(Integer relseq) {
        this.relseq = relseq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
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
        if (!(object instanceof PurchaseAcceptanceDetail)) {
            return false;
        }
        PurchaseAcceptanceDetail other = (PurchaseAcceptanceDetail) object;
        if (!Objects.equals(this.srcformid, other.srcformid)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && !Objects.equals(this.srcseq, other.srcseq)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && Objects.equals(this.srcseq, other.srcseq)) {
            return true;
        }
        if (this.itemmaster != other.itemmaster) {
            return false;
        }
        return (Objects.equals(this.pid, other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseAcceptanceDetail[ id=" + id + " ]";
    }

    /**
     * @return the allowqty
     */
    public BigDecimal getAllowqty() {
        return allowqty;
    }

    /**
     * @param allowqty the allowqty to set
     */
    public void setAllowqty(BigDecimal allowqty) {
        this.allowqty = allowqty;
    }

    /**
     * @return the warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * @return the badwarehouse
     */
    public Warehouse getBadwarehouse() {
        return badwarehouse;
    }

    /**
     * @param badwarehouse the badwarehouse to set
     */
    public void setBadwarehouse(Warehouse badwarehouse) {
        this.badwarehouse = badwarehouse;
    }

}

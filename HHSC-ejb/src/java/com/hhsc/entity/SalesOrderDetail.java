/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseLib;
import com.lightshell.comm.FormDetailEntity;
import com.lightshell.comm.Tax;
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
@Table(name = "salesorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrderDetail.findAll", query = "SELECT s FROM SalesOrderDetail s")
    ,
    @NamedQuery(name = "SalesOrderDetail.findById", query = "SELECT s FROM SalesOrderDetail s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByPId", query = "SELECT s FROM SalesOrderDetail s WHERE s.pid = :pid ORDER BY s.seq")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByPIdAndSeq", query = "SELECT s FROM SalesOrderDetail s WHERE s.pid = :pid AND s.seq = :seq ")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByColorno", query = "SELECT s FROM SalesOrderDetail s WHERE s.colorno = :colorno")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByCustomercolorno", query = "SELECT s FROM SalesOrderDetail s WHERE s.customercolorno = :customercolorno")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByItemId", query = "SELECT s FROM SalesOrderDetail s WHERE s.itemmaster.id = :itemmasterid")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByItemno", query = "SELECT s FROM SalesOrderDetail s WHERE s.itemno = :itemno")
    ,
    @NamedQuery(name = "SalesOrderDetail.findByDeliverydate", query = "SELECT s FROM SalesOrderDetail s WHERE s.deliverydate = :deliverydate")})
public class SalesOrderDetail extends FormDetailEntity {

    /**
     * @return the printdate
     */
    public Date getPrintdate() {
        return printdate;
    }

    /**
     * @param printdate the printdate to set
     */
    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 100)
    @Column(name = "customercolorno")
    private String customercolorno;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quotedprice")
    private BigDecimal quotedprice;
    @Column(name = "discount")
    private BigDecimal discount;
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
    @Column(name = "extax")
    private BigDecimal extax;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "printdate")
    @Temporal(TemporalType.DATE)
    private Date printdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deliverydate")
    @Temporal(TemporalType.DATE)
    private Date deliverydate;
    @Column(name = "deliverytime")
    @Temporal(TemporalType.TIME)
    private Date deliverytime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issqty")
    private BigDecimal issqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proqty")
    private BigDecimal proqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inqty")
    private BigDecimal inqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipqty")
    private BigDecimal shipqty;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 2)
    @Column(name = "status")
    protected String status;
    @Size(max = 100)
    @Column(name = "srcapi")
    protected String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    protected String srcformid;
    @Column(name = "srcseq")
    protected Integer srcseq;
    @Size(max = 100)
    @Column(name = "relapi")
    protected String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    protected String relformid;
    @Column(name = "relseq")
    protected Integer relseq;

    public SalesOrderDetail() {
        this.quotedprice = BigDecimal.ZERO;
        this.discount = BigDecimal.valueOf(100);
        this.proqty = BigDecimal.ZERO;
        this.inqty = BigDecimal.ZERO;
        this.shipqty = BigDecimal.ZERO;
        this.amts = BigDecimal.ZERO;
        this.extax = BigDecimal.ZERO;
        this.taxes = BigDecimal.ZERO;
        this.status = "00";
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getCustomercolorno() {
        return customercolorno;
    }

    public void setCustomercolorno(String customercolorno) {
        this.customercolorno = customercolorno;
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

    public BigDecimal getQuotedprice() {
        return quotedprice;
    }

    public void setQuotedprice(BigDecimal quotedprice) {
        this.quotedprice = quotedprice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public BigDecimal getIssqty() {
        return issqty;
    }

    public void setIssqty(BigDecimal issqty) {
        this.issqty = issqty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof SalesOrderDetail)) {
            return false;
        }
        SalesOrderDetail other = (SalesOrderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return (Objects.equals(this.pid, other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrderDetail[ id=" + id + " ]";
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
     * @return the relapi
     */
    public String getRelapi() {
        return relapi;
    }

    /**
     * @param relapi the relapi to set
     */
    public void setRelapi(String relapi) {
        this.relapi = relapi;
    }

    /**
     * @return the relformid
     */
    public String getRelformid() {
        return relformid;
    }

    /**
     * @param relformid the relformid to set
     */
    public void setRelformid(String relformid) {
        this.relformid = relformid;
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
     * @return the shipqty
     */
    public BigDecimal getShipqty() {
        return shipqty;
    }

    /**
     * @param shipqty the shipqty to set
     */
    public void setShipqty(BigDecimal shipqty) {
        this.shipqty = shipqty;
    }

    /**
     * @return the inqty
     */
    public BigDecimal getInqty() {
        return inqty;
    }

    /**
     * @param inqty the inqty to set
     */
    public void setInqty(BigDecimal inqty) {
        this.inqty = inqty;
    }

    /**
     * @return the proqty
     */
    public BigDecimal getProqty() {
        return proqty;
    }

    /**
     * @param proqty the proqty to set
     */
    public void setProqty(BigDecimal proqty) {
        this.proqty = proqty;
    }

    public void calcTotalAmts(String taxtype, String taxkind, BigDecimal taxrate) {
        this.setAmts(this.getQty().multiply(this.getPrice()));
        Tax t = BaseLib.getTaxes(taxtype, taxkind, taxrate, this.getAmts(), 2);
        this.setExtax(t.getExtax());
        this.setTaxes(t.getTaxes());
    }

}

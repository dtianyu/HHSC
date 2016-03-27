/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseDetailEntity;
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
@Table(name = "salesorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrderDetail.findAll", query = "SELECT s FROM SalesOrderDetail s"),
    @NamedQuery(name = "SalesOrderDetail.findById", query = "SELECT s FROM SalesOrderDetail s WHERE s.id = :id"),
    @NamedQuery(name = "SalesOrderDetail.findByPId", query = "SELECT s FROM SalesOrderDetail s WHERE s.pid = :pid"),
    @NamedQuery(name = "SalesOrderDetail.findByPformid", query = "SELECT s FROM SalesOrderDetail s WHERE s.pformid = :pformid"),
    @NamedQuery(name = "SalesOrderDetail.findBySeq", query = "SELECT s FROM SalesOrderDetail s WHERE s.seq = :seq"),
    @NamedQuery(name = "SalesOrderDetail.findByColorno", query = "SELECT s FROM SalesOrderDetail s WHERE s.colorno = :colorno"),
    @NamedQuery(name = "SalesOrderDetail.findByCustomercolorno", query = "SELECT s FROM SalesOrderDetail s WHERE s.customercolorno = :customercolorno"),
    @NamedQuery(name = "SalesOrderDetail.findByItemId", query = "SELECT s FROM SalesOrderDetail s WHERE s.itemmaster.id = :itemid"),
    @NamedQuery(name = "SalesOrderDetail.findByItemno", query = "SELECT s FROM SalesOrderDetail s WHERE s.itemno = :itemno"),
    @NamedQuery(name = "SalesOrderDetail.findByDeliverydate", query = "SELECT s FROM SalesOrderDetail s WHERE s.deliverydate = :deliverydate")})
public class SalesOrderDetail extends BaseDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pformid")
    private String pformid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 20)
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
    @Column(name = "notaxs")
    private BigDecimal notaxs;
    @Column(name = "taxes")
    private BigDecimal taxes;
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
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public SalesOrderDetail() {
    }

    public String getPformid() {
        return pformid;
    }

    public void setPformid(String pformid) {
        this.pformid = pformid;
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

    public BigDecimal getNotaxs() {
        return notaxs;
    }

    public void setNotaxs(BigDecimal notaxs) {
        this.notaxs = notaxs;
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
        return (this.pid == other.pid && this.seq == other.seq);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrderDetail[ id=" + id + " ]";
    }

}

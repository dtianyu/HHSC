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
    @NamedQuery(name = "SalesOrderDetail.findBySeq", query = "SELECT s FROM SalesOrderDetail s WHERE s.seq = :seq"),
    @NamedQuery(name = "SalesOrderDetail.findByDesignid", query = "SELECT s FROM SalesOrderDetail s WHERE s.designid = :designid"),
    @NamedQuery(name = "SalesOrderDetail.findByCustomeritem", query = "SELECT s FROM SalesOrderDetail s WHERE s.customeritem = :customeritem"),
    @NamedQuery(name = "SalesOrderDetail.findByItemno", query = "SELECT s FROM SalesOrderDetail s WHERE s.itemno = :itemno")})
public class SalesOrderDetail extends BaseDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "designid")
    private int designid;
    @Size(max = 45)
    @Column(name = "customeritem")
    private String customeritem;
    @Size(max = 45)
    @Column(name = "colorid")
    private String colorid;
    @Size(max = 45)
    @Column(name = "factoryspec")
    private String factoryspec;
    @Size(max = 45)
    @Column(name = "productspec")
    private String productspec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "charge")
    private BigDecimal charge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suitqty")
    private int suitqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meterqty")
    private BigDecimal meterqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    protected String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "excludingtax")
    private BigDecimal excludingtax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deliverdate")
    @Temporal(TemporalType.DATE)
    private Date deliverdate;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public SalesOrderDetail() {
    }

    public int getDesignid() {
        return designid;
    }

    public void setDesignid(int designid) {
        this.designid = designid;
    }

    public String getCustomeritem() {
        return customeritem;
    }

    public void setCustomeritem(String customeritem) {
        this.customeritem = customeritem;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getFactoryspec() {
        return factoryspec;
    }

    public void setFactoryspec(String factoryspec) {
        this.factoryspec = factoryspec;
    }

    public String getProductspec() {
        return productspec;
    }

    public void setProductspec(String productspec) {
        this.productspec = productspec;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public int getSuitqty() {
        return suitqty;
    }

    public void setSuitqty(int suitqty) {
        this.suitqty = suitqty;
    }

    public BigDecimal getMeterqty() {
        return meterqty;
    }

    public void setMeterqty(BigDecimal meterqty) {
        this.meterqty = meterqty;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getExcludingtax() {
        return excludingtax;
    }

    public void setExcludingtax(BigDecimal excludingtax) {
        this.excludingtax = excludingtax;
    }

    public Date getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(Date deliverdate) {
        this.deliverdate = deliverdate;
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
        if ((this.colorid == null && other.colorid != null) || (this.colorid != null && !this.colorid.equals(other.colorid))) {
            return false;
        }
        if (this.seq != other.seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrderDetail[ id=" + id + " ]";
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}

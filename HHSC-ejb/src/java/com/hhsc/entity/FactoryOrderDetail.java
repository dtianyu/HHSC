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
@Table(name = "factoryorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryOrderDetail.findAll", query = "SELECT f FROM FactoryOrderDetail f"),
    @NamedQuery(name = "FactoryOrderDetail.findById", query = "SELECT f FROM FactoryOrderDetail f WHERE f.id = :id"),
    @NamedQuery(name = "FactoryOrderDetail.findByPId", query = "SELECT f FROM FactoryOrderDetail f WHERE f.pid = :pid"),
    @NamedQuery(name = "FactoryOrderDetail.findByDesignId", query = "SELECT f FROM FactoryOrderDetail f WHERE f.designid = :designid"),
    @NamedQuery(name = "FactoryOrderDetail.findByColorId", query = "SELECT f FROM FactoryOrderDetail f WHERE f.colorid = :colorid")})
public class FactoryOrderDetail extends BaseDetailEntity {

    @Size(max = 20)
    @Column(name = "pformid")
    private String pformid;
    @Size(max = 20)
    @Column(name = "designid")
    private String designid;
    @Size(max = 45)
    @Column(name = "customeritem")
    private String customeritem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "suitqty")
    private int suitqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "meterqty")
    private BigDecimal meterqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Column(name = "jhqty")
    private BigDecimal jhqty;
    @Column(name = "inqty")
    protected BigDecimal inqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deliverdate")
    @Temporal(TemporalType.DATE)
    private Date deliverdate;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public FactoryOrderDetail() {
    }

    public String getDesignid() {
        return designid;
    }

    public void setDesignid(String designid) {
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        if (!(object instanceof FactoryOrderDetail)) {
            return false;
        }
        FactoryOrderDetail other = (FactoryOrderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (!this.colorid.equals(other.colorid)) {
            return false;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.FactoryOrderDetail[ id=" + id + " ]";
    }

    /**
     * @return the jhqty
     */
    public BigDecimal getJhqty() {
        return jhqty;
    }

    /**
     * @param jhqty the jhqty to set
     */
    public void setJhqty(BigDecimal jhqty) {
        this.jhqty = jhqty;
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
     * @return the pformid
     */
    public String getPformid() {
        return pformid;
    }

    /**
     * @param pformid the pformid to set
     */
    public void setPformid(String pformid) {
        this.pformid = pformid;
    }

}

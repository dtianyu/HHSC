/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntity;
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
@Table(name = "factoryorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryOrderDetailForStorage.findAll", query = "SELECT f FROM FactoryOrderDetailForStorage f"),
    @NamedQuery(name = "FactoryOrderDetailForStorage.findById", query = "SELECT f FROM FactoryOrderDetailForStorage f WHERE f.id = :id"),
    @NamedQuery(name = "FactoryOrderDetailForStorage.findByPId", query = "SELECT f FROM FactoryOrderDetailForStorage f WHERE f.factoryOrder.id = :pid"),
    @NamedQuery(name = "FactoryOrderDetailForStorage.findByDesignId", query = "SELECT f FROM FactoryOrderDetailForStorage f WHERE f.designid = :designid"),
    @NamedQuery(name = "FactoryOrderDetailForStorage.findByColorno", query = "SELECT f FROM FactoryOrderDetailForStorage f WHERE f.colorno = :color")})
public class FactoryOrderDetailForStorage extends BaseEntity {

    @JoinColumn(name = "pid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FactoryOrder factoryOrder;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    protected int seq;
    @Size(max = 20)
    @Column(name = "pformid")
    protected String pformid;
    @Size(max = 20)
    @Column(name = "designid")
    protected String designid;
    @Size(max = 45)
    @Column(name = "customeritem")
    protected String customeritem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorid")
    protected String colorno;
    @Size(max = 45)
    @Column(name = "factoryspec")
    protected String factoryspec;
    @Size(max = 45)
    @Column(name = "productspec")
    protected String productspec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    protected String itemno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suitqty")
    protected int suitqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "meterqty")
    protected BigDecimal meterqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    protected BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    protected String unit;
    @Column(name = "jhqty")
    protected BigDecimal jhqty;
    @Column(name = "inqty")
    protected BigDecimal inqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deliverdate")
    @Temporal(TemporalType.DATE)
    protected Date deliverdate;
    @Size(max = 200)
    @Column(name = "remark")
    protected String remark;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryOrderDetailForStorage)) {
            return false;
        }
        FactoryOrderDetailForStorage other = (FactoryOrderDetailForStorage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.FactoryOrderDetailForStorage[ id=" + id + " ]";
    }

    /**
     * @return the factoryOrder
     */
    public FactoryOrder getFactoryOrder() {
        return factoryOrder;
    }

    /**
     * @param factoryOrder the factoryOrder to set
     */
    public void setFactoryOrder(FactoryOrder factoryOrder) {
        this.factoryOrder = factoryOrder;
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
     * @return the pformid
     */
    public String getPformid() {
        return pformid;
    }

    /**
     * @return the designid
     */
    public String getDesignid() {
        return designid;
    }

    /**
     * @return the customeritem
     */
    public String getCustomeritem() {
        return customeritem;
    }

    /**
     * @return the colorno
     */
    public String getColorno() {
        return colorno;
    }

    /**
     * @return the factoryspec
     */
    public String getFactoryspec() {
        return factoryspec;
    }

    /**
     * @return the productspec
     */
    public String getProductspec() {
        return productspec;
    }

    /**
     * @return the itemno
     */
    public String getItemno() {
        return itemno;
    }

    /**
     * @return the qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @return the jhqty
     */
    public BigDecimal getJhqty() {
        return jhqty;
    }

    /**
     * @return the inqty
     */
    public BigDecimal getInqty() {
        return inqty;
    }

    /**
     * @return the deliverdate
     */
    public Date getDeliverdate() {
        return deliverdate;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @return the suitqty
     */
    public int getSuitqty() {
        return suitqty;
    }

    /**
     * @param suitqty the suitqty to set
     */
    public void setSuitqty(int suitqty) {
        this.suitqty = suitqty;
    }

    /**
     * @return the meterqty
     */
    public BigDecimal getMeterqty() {
        return meterqty;
    }

}

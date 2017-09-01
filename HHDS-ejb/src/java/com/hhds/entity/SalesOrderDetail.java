/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

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
@Table(name = "salesorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrderDetail.findAll", query = "SELECT s FROM SalesOrderDetail s")
    , @NamedQuery(name = "SalesOrderDetail.findById", query = "SELECT s FROM SalesOrderDetail s WHERE s.id = :id")
    , @NamedQuery(name = "SalesOrderDetail.findByPId", query = "SELECT s FROM SalesOrderDetail s WHERE s.pid = :pid")})
public class SalesOrderDetail extends FormDetailEntity {

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMaster;

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
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Column(name = "extax")
    private BigDecimal extax;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "printdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printdate;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;
    @Column(name = "proqty")
    private BigDecimal proqty;
    @Column(name = "inqty")
    private BigDecimal inqty;
    @Column(name = "shipqty")
    private BigDecimal shipqty;
    @Column(name = "backqty")
    private BigDecimal backqty;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 10)
    @Column(name = "period")
    private String period;
    @Column(name = "firstdelivery")
    @Temporal(TemporalType.DATE)
    private Date firstdelivery;
    @Column(name = "firsttime")
    @Temporal(TemporalType.TIME)
    private Date firsttime;
    @Column(name = "lastdelivery")
    @Temporal(TemporalType.DATE)
    private Date lastdelivery;
    @Column(name = "lasttime")
    @Temporal(TemporalType.TIME)
    private Date lasttime;
    @Size(max = 2)
    @Column(name = "status")
    private String status;
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

    public SalesOrderDetail() {
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

    public Date getPrintdate() {
        return printdate;
    }

    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public BigDecimal getProqty() {
        return proqty;
    }

    public void setProqty(BigDecimal proqty) {
        this.proqty = proqty;
    }

    public BigDecimal getInqty() {
        return inqty;
    }

    public void setInqty(BigDecimal inqty) {
        this.inqty = inqty;
    }

    public BigDecimal getShipqty() {
        return shipqty;
    }

    public void setShipqty(BigDecimal shipqty) {
        this.shipqty = shipqty;
    }

    public BigDecimal getBackqty() {
        return backqty;
    }

    public void setBackqty(BigDecimal backqty) {
        this.backqty = backqty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getFirstdelivery() {
        return firstdelivery;
    }

    public void setFirstdelivery(Date firstdelivery) {
        this.firstdelivery = firstdelivery;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Date getLastdelivery() {
        return lastdelivery;
    }

    public void setLastdelivery(Date lastdelivery) {
        this.lastdelivery = lastdelivery;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (this.id != null && other.id != null) {
            return this.id.equals(other.id);
        }
        return Objects.equals(this.pid, other.pid) && this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhds.entity.SalesOrderDetail[ id=" + id + " ]";
    }

    /**
     * @return the itemMaster
     */
    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    /**
     * @param itemMaster the itemMaster to set
     */
    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

}
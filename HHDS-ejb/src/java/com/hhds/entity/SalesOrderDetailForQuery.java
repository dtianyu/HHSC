/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

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
@Table(name = "salesorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrderDetailForQuery.getRowCount", query = "SELECT COUNT(s) FROM SalesOrderDetailForQuery s")
    ,
    @NamedQuery(name = "SalesOrderDetailForQuery.findAll", query = "SELECT s FROM SalesOrderDetailForQuery s")
    ,
    @NamedQuery(name = "SalesOrderDetailForQuery.findById", query = "SELECT s FROM SalesOrderDetailForQuery s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SalesOrderDetailForQuery.findByPId", query = "SELECT s FROM SalesOrderDetailForQuery s WHERE s.salesOrder.formid = :pid ORDER BY s.seq")
    ,
    @NamedQuery(name = "SalesOrderDetailForQuery.findByPIdAndSeq", query = "SELECT s FROM SalesOrderDetailForQuery s WHERE s.salesOrder.formid = :pid AND s.seq=:seq")
    ,
    @NamedQuery(name = "SalesOrderDetailForQuery.findByItemno", query = "SELECT s FROM SalesOrderDetailForQuery s WHERE s.itemno = :itemno")})
public class SalesOrderDetailForQuery extends BaseEntity {

    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne(optional = false)
    protected SalesOrder salesOrder;

    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;
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

    public SalesOrderDetailForQuery() {
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

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    /**
     * @return the colorno
     */
    public String getColorno() {
        return colorno;
    }

    /**
     * @param colorno the colorno to set
     */
    public void setColorno(String colorno) {
        this.colorno = colorno;
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
        if (!(object instanceof SalesOrderDetailForQuery)) {
            return false;
        }
        SalesOrderDetailForQuery other = (SalesOrderDetailForQuery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return (this.getSalesOrder().getFormid().equals(other.getSalesOrder().getFormid()) && (this.getSeq() == other.getSeq()));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrderDetailForQuery[ id=" + id + " ]";
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
     * @return the salesOrder
     */
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    /**
     * @param salesOrder the salesOrder to set
     */
    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
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
     * @return the backqty
     */
    public BigDecimal getBackqty() {
        return backqty;
    }

    /**
     * @param backqty the backqty to set
     */
    public void setBackqty(BigDecimal backqty) {
        this.backqty = backqty;
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

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the firstdelivery
     */
    public Date getFirstdelivery() {
        return firstdelivery;
    }

    /**
     * @param firstdelivery the firstdelivery to set
     */
    public void setFirstdelivery(Date firstdelivery) {
        this.firstdelivery = firstdelivery;
    }

    /**
     * @return the firsttime
     */
    public Date getFirsttime() {
        return firsttime;
    }

    /**
     * @param firsttime the firsttime to set
     */
    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    /**
     * @return the lastdelivery
     */
    public Date getLastdelivery() {
        return lastdelivery;
    }

    /**
     * @param lastdelivery the lastdelivery to set
     */
    public void setLastdelivery(Date lastdelivery) {
        this.lastdelivery = lastdelivery;
    }

    /**
     * @return the lasttime
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * @param lasttime the lasttime to set
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

}

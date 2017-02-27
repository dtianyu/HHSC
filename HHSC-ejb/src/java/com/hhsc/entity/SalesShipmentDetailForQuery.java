/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntity;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "salesshipmentdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesShipmentDetailForQuery.getRowCount", query = "SELECT COUNT(s) FROM SalesShipmentDetailForQuery s")
    ,
    @NamedQuery(name = "SalesShipmentDetailForQuery.findAll", query = "SELECT s FROM SalesShipmentDetailForQuery s")
    ,
    @NamedQuery(name = "SalesShipmentDetailForQuery.findById", query = "SELECT s FROM SalesShipmentDetailForQuery s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SalesShipmentDetailForQuery.findByPId", query = "SELECT s FROM SalesShipmentDetailForQuery s WHERE s.salesShipment.formid = :pid")
    ,
    @NamedQuery(name = "SalesShipmentDetailForQuery.findByItemno", query = "SELECT s FROM SalesShipmentDetailForQuery s WHERE s.itemno = :itemno")})
public class SalesShipmentDetailForQuery extends BaseEntity {
    
    @JoinColumn(name = "batch", referencedColumnName = "itemno", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private ItemMaster itemBatch;
    
    @Column(name = "badqty")
    private BigDecimal badqty;
    @JoinColumn(name = "badwarehouse", referencedColumnName = "warehouseno")
    @ManyToOne(optional = true)
    private Warehouse warehouse2;
    
    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne(optional = false)
    private SalesShipment salesShipment;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 100)
    @Column(name = "itemimg")
    private String itemimg;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 100)
    @Column(name = "customercolorno")
    private String customercolorno;
    @Size(max = 45)
    @Column(name = "customerrefno")
    protected String customerrefno;
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
    protected BigDecimal price;
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

    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;
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
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;

    public SalesShipmentDetailForQuery() {
        this.status = "00";
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

    public String getItemimg() {
        return itemimg;
    }

    public void setItemimg(String itemimg) {
        this.itemimg = itemimg;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof SalesShipmentDetailForQuery)) {
            return false;
        }
        SalesShipmentDetailForQuery other = (SalesShipmentDetailForQuery) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesShipmentDetail[ id=" + id + " ]";
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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
     * @return the customerrefno
     */
    public String getCustomerrefno() {
        return customerrefno;
    }

    /**
     * @param customerrefno the customerrefno to set
     */
    public void setCustomerrefno(String customerrefno) {
        this.customerrefno = customerrefno;
    }

    /**
     * @return the salesShipment
     */
    public SalesShipment getSalesShipment() {
        return salesShipment;
    }

    /**
     * @param salesShipment the salesShipment to set
     */
    public void setSalesShipment(SalesShipment salesShipment) {
        this.salesShipment = salesShipment;
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
     * @return the badqty
     */
    public BigDecimal getBadqty() {
        return badqty;
    }

    /**
     * @param badqty the badqty to set
     */
    public void setBadqty(BigDecimal badqty) {
        this.badqty = badqty;
    }

    /**
     * @return the warehouse2
     */
    public Warehouse getWarehouse2() {
        return warehouse2;
    }

    /**
     * @param warehouse2 the warehouse2 to set
     */
    public void setWarehouse2(Warehouse warehouse2) {
        this.warehouse2 = warehouse2;
    }

    /**
     * @return the itemBatch
     */
    public ItemMaster getItemBatch() {
        return itemBatch;
    }

    /**
     * @param itemBatch the itemBatch to set
     */
    public void setItemBatch(ItemMaster itemBatch) {
        this.itemBatch = itemBatch;
    }

}

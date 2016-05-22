/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
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
@Table(name = "iteminventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemInventory.getRowCount", query = "SELECT COUNT(i) FROM ItemInventory i"),
    @NamedQuery(name = "ItemInventory.findAll", query = "SELECT i FROM ItemInventory i"),
    @NamedQuery(name = "ItemInventory.findItemInventory", query = "SELECT i FROM ItemInventory i where i.itemmaster.itemno = :itemno AND i.colorno = :colorno AND i.brand = :brand AND i.batch = :batch AND i.sn = :sn AND i.warehouse.warehouseno = :warehouseno"),
    @NamedQuery(name = "ItemInventory.findItemInventories", query = "SELECT i FROM ItemInventory i where i.itemmaster.itemno = :itemno AND i.colorno = :colorno AND i.brand = :brand AND i.batch = :batch AND i.sn = :sn"),
    @NamedQuery(name = "ItemInventory.findById", query = "SELECT i FROM ItemInventory i WHERE i.id = :id"),
    @NamedQuery(name = "ItemInventory.findByItemno", query = "SELECT i FROM ItemInventory i WHERE i.itemmaster.itemno = :itemno"),
    @NamedQuery(name = "ItemInventory.findByColorno", query = "SELECT i FROM ItemInventory i WHERE i.colorno = :colorno"),
    @NamedQuery(name = "ItemInventory.findByBrand", query = "SELECT i FROM ItemInventory i WHERE i.brand = :brand"),
    @NamedQuery(name = "ItemInventory.findByBatch", query = "SELECT i FROM ItemInventory i WHERE i.batch = :batch"),
    @NamedQuery(name = "ItemInventory.findBySn", query = "SELECT i FROM ItemInventory i WHERE i.sn = :sn"),
    @NamedQuery(name = "ItemInventory.findByWarehouseno", query = "SELECT i FROM ItemInventory i WHERE i.warehouse.warehouseno = :warehouseno"),
    @NamedQuery(name = "ItemInventory.findByLocation", query = "SELECT i FROM ItemInventory i WHERE i.location = :location"),
    @NamedQuery(name = "ItemInventory.findByObjectkind", query = "SELECT i FROM ItemInventory i WHERE i.objectkind = :objectkind"),
    @NamedQuery(name = "ItemInventory.findByObjectId", query = "SELECT i FROM ItemInventory i WHERE i.objectid = :objectid"),
    @NamedQuery(name = "ItemInventory.findByStatus", query = "SELECT i FROM ItemInventory i WHERE i.status = :status")})
public class ItemInventory extends SuperEntity {

    @JoinColumn(name = "itemno", referencedColumnName = "itemno")
    @ManyToOne(optional = false)
    protected ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorno")
    protected String colorno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "batch")
    private String batch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sn")
    private String sn;

    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preqty")
    private BigDecimal preqty;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @Size(max = 45)
    @Column(name = "objectkind")
    private String objectkind;
    @Size(max = 45)
    @Column(name = "objectid")
    private String objectid;
    @Column(name = "indate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indate;
    @Column(name = "outdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outdate;

    public ItemInventory() {
        this.status = "N";
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public BigDecimal getPreqty() {
        return preqty;
    }

    public void setPreqty(BigDecimal preqty) {
        this.preqty = preqty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getObjectkind() {
        return objectkind;
    }

    public void setObjectkind(String objectkind) {
        this.objectkind = objectkind;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
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
        if (!(object instanceof ItemInventory)) {
            return false;
        }
        ItemInventory other = (ItemInventory) object;
        return this.itemmaster.getItemno().equals(other.itemmaster.getItemno())
                && this.colorno.equals(other.colorno) && this.brand.equals(other.brand)
                && this.batch.equals(other.batch) && this.sn.equals(other.sn)
                && this.warehouse.getWarehouseno().equals(other.warehouse.getWarehouseno());
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemInventory[ id=" + id + " ]";
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

    /**
     * @return the itemmaster
     */
    public ItemMaster getItemmaster() {
        return itemmaster;
    }

    /**
     * @param itemmaster the itemmaster to set
     */
    public void setItemmaster(ItemMaster itemmaster) {
        this.itemmaster = itemmaster;
    }

}

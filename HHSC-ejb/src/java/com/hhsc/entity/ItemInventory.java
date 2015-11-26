/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "ItemInventory.findAll", query = "SELECT i FROM ItemInventory i"),
    @NamedQuery(name = "ItemInventory.findById", query = "SELECT i FROM ItemInventory i WHERE i.id = :id"),
    @NamedQuery(name = "ItemInventory.findByItemno", query = "SELECT i FROM ItemInventory i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemInventory.findByWarehouse", query = "SELECT i FROM ItemInventory i WHERE i.warehouse = :warehouse"),
    @NamedQuery(name = "ItemInventory.findByBrand", query = "SELECT i FROM ItemInventory i WHERE i.brand = :brand"),
    @NamedQuery(name = "ItemInventory.findByBatch", query = "SELECT i FROM ItemInventory i WHERE i.batch = :batch"),
    @NamedQuery(name = "ItemInventory.findBySn", query = "SELECT i FROM ItemInventory i WHERE i.sn = :sn"),
    @NamedQuery(name = "ItemInventory.findByQty", query = "SELECT i FROM ItemInventory i WHERE i.qty = :qty"),
    @NamedQuery(name = "ItemInventory.findByPreqty", query = "SELECT i FROM ItemInventory i WHERE i.preqty = :preqty"),
    @NamedQuery(name = "ItemInventory.findByLocation", query = "SELECT i FROM ItemInventory i WHERE i.location = :location"),
    @NamedQuery(name = "ItemInventory.findByObjectkind", query = "SELECT i FROM ItemInventory i WHERE i.objectkind = :objectkind"),
    @NamedQuery(name = "ItemInventory.findByObjectid", query = "SELECT i FROM ItemInventory i WHERE i.objectid = :objectid"),
    @NamedQuery(name = "ItemInventory.findByIndate", query = "SELECT i FROM ItemInventory i WHERE i.indate = :indate"),
    @NamedQuery(name = "ItemInventory.findByOutdate", query = "SELECT i FROM ItemInventory i WHERE i.outdate = :outdate"),
    @NamedQuery(name = "ItemInventory.findByStatus", query = "SELECT i FROM ItemInventory i WHERE i.status = :status")})
public class ItemInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "warehouse")
    private String warehouse;
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

    public ItemInventory() {
    }

    public ItemInventory(Integer id) {
        this.id = id;
    }

    public ItemInventory(Integer id, String itemno, String warehouse, String brand, String batch, String sn, BigDecimal qty, BigDecimal preqty, String status) {
        this.id = id;
        this.itemno = itemno;
        this.warehouse = warehouse;
        this.brand = brand;
        this.batch = batch;
        this.sn = sn;
        this.qty = qty;
        this.preqty = preqty;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
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
        if (!(object instanceof ItemInventory)) {
            return false;
        }
        ItemInventory other = (ItemInventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemInventory[ id=" + id + " ]";
    }
    
}

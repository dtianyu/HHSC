/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
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
@Table(name = "itemtransactiondetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemTransactionDetail.findAll", query = "SELECT i FROM ItemTransactionDetail i"),
    @NamedQuery(name = "ItemTransactionDetail.findById", query = "SELECT i FROM ItemTransactionDetail i WHERE i.id = :id"),
    @NamedQuery(name = "ItemTransactionDetail.findByPId", query = "SELECT i FROM ItemTransactionDetail i WHERE i.pid = :pid"),
    @NamedQuery(name = "ItemTransactionDetail.findByTrtype", query = "SELECT i FROM ItemTransactionDetail i WHERE i.trtype = :trtype"),
    @NamedQuery(name = "ItemTransactionDetail.findByItemno", query = "SELECT i FROM ItemTransactionDetail i WHERE i.itemno = :itemno")})
public class ItemTransactionDetail extends FormDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "trtype")
    private String trtype;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
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
    @Size(max = 45)
    @Column(name = "remark")
    private String remark;

    public ItemTransactionDetail() {
    }

    public String getTrtype() {
        return trtype;
    }

    public void setTrtype(String trtype) {
        this.trtype = trtype;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemTransactionDetail)) {
            return false;
        }
        ItemTransactionDetail other = (ItemTransactionDetail) object;
        if (this.itemmaster != other.itemmaster) {
            return false;
        }
        if (!Objects.equals(this.srcformid, other.srcformid)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && !Objects.equals(this.srcseq, other.srcseq)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && Objects.equals(this.srcseq, other.srcseq)) {
            return true;
        }
        return (Objects.equals(this.pid, other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemTransactionDetail[ id=" + id + " ]";
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

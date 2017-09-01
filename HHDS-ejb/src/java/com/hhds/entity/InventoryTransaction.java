/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
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
@Table(name = "inventorytransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryTransaction.getRowCount", query = "SELECT COUNT(i) FROM InventoryTransaction i"),
    @NamedQuery(name = "InventoryTransaction.findAll", query = "SELECT i FROM InventoryTransaction i"),
    @NamedQuery(name = "InventoryTransaction.findById", query = "SELECT i FROM InventoryTransaction i WHERE i.id = :id"),
    @NamedQuery(name = "InventoryTransaction.findByTrtype", query = "SELECT i FROM InventoryTransaction i WHERE i.trtype.trtype = :trtype"),
    @NamedQuery(name = "InventoryTransaction.findByFormid", query = "SELECT i FROM InventoryTransaction i WHERE i.formid = :formid"),
    @NamedQuery(name = "InventoryTransaction.findByFormidAndSeq", query = "SELECT i FROM InventoryTransaction i WHERE i.formid = :formid AND i.seq=:seq "),
    @NamedQuery(name = "InventoryTransaction.findByItemno", query = "SELECT i FROM InventoryTransaction i WHERE i.itemmaster.itemno = :itemno")})
public class InventoryTransaction extends FormEntity {

    @JoinColumn(name = "trtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType trtype;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @JoinColumn(name = "itemno", referencedColumnName = "itemno")
    @ManyToOne(optional = false)
    protected ItemMaster itemmaster;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorno")
    private String colorno;
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
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;

    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    protected Warehouse warehouse;

    @Column(name = "iocode")
    private Integer iocode;
    @Size(max = 45)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 20)
    @Column(name = "objno")
    private String objno;
    @Size(max = 10)
    @Column(name = "reasontype")
    private String reasontype;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 2)
    @Column(name = "proptype")
    private String proptype;
    @Size(max = 2)
    @Column(name = "maketype")
    private String maketype;
    @Size(max = 10)
    @Column(name = "sysno")
    private String sysno;
    @Size(max = 45)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Column(name = "costma")
    private BigDecimal costma;
    @Column(name = "costla")
    private BigDecimal costla;
    @Column(name = "costex")
    private BigDecimal costex;
    @Column(name = "costsub")
    private BigDecimal costsub;
    @Column(name = "cost")
    private BigDecimal cost;

    public InventoryTransaction() {
        this.costma = BigDecimal.ZERO;
        this.costla = BigDecimal.ZERO;
        this.costex = BigDecimal.ZERO;
        this.costsub = BigDecimal.ZERO;
        this.cost = BigDecimal.ZERO;
        this.status = "N";
    }

    public TransactionType getTrtype() {
        return trtype;
    }

    public void setTrtype(TransactionType trtype) {
        this.trtype = trtype;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public ItemMaster getItemmaster() {
        return itemmaster;
    }

    public void setItemmaster(ItemMaster itemmaster) {
        this.itemmaster = itemmaster;
    }

    public String getColorno() {
        return colorno;
    }

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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getIocode() {
        return iocode;
    }

    public void setIocode(Integer iocode) {
        this.iocode = iocode;
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    public String getObjno() {
        return objno;
    }

    public void setObjno(String objno) {
        this.objno = objno;
    }

    public String getReasontype() {
        return reasontype;
    }

    public void setReasontype(String reasontype) {
        this.reasontype = reasontype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public String getMaketype() {
        return maketype;
    }

    public void setMaketype(String maketype) {
        this.maketype = maketype;
    }

    public String getSysno() {
        return sysno;
    }

    public void setSysno(String sysno) {
        this.sysno = sysno;
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

    public BigDecimal getCostma() {
        return costma;
    }

    public void setCostma(BigDecimal costma) {
        this.costma = costma;
    }

    public BigDecimal getCostla() {
        return costla;
    }

    public void setCostla(BigDecimal costla) {
        this.costla = costla;
    }

    public BigDecimal getCostex() {
        return costex;
    }

    public void setCostex(BigDecimal costex) {
        this.costex = costex;
    }

    public BigDecimal getCostsub() {
        return costsub;
    }

    public void setCostsub(BigDecimal costsub) {
        this.costsub = costsub;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
        if (!(object instanceof InventoryTransaction)) {
            return false;
        }
        InventoryTransaction other = (InventoryTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.InventoryTransaction[ id=" + id + " ]";
    }

}

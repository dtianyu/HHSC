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
@Table(name = "itemexchange")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemExchange.findAll", query = "SELECT i FROM ItemExchange i")
    ,
    @NamedQuery(name = "ItemExchange.findById", query = "SELECT i FROM ItemExchange i WHERE i.id = :id")
    ,
    @NamedQuery(name = "ItemExchange.findByFormid", query = "SELECT i FROM ItemExchange i WHERE i.formid = :formid")
    ,
    @NamedQuery(name = "ItemExchange.findByFormdate", query = "SELECT i FROM ItemExchange i WHERE i.formdate = :formdate")
    ,
    @NamedQuery(name = "ItemExchange.findByReason", query = "SELECT i FROM ItemExchange i WHERE i.reason = :reason")
    ,
    @NamedQuery(name = "ItemExchange.findByStatus", query = "SELECT i FROM ItemExchange i WHERE i.status = :status")})
public class ItemExchange extends FormEntity {

    @JoinColumn(name = "trtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType transactionType;

    @Size(max = 45)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 20)
    @Column(name = "objno")
    private String objno;

    @JoinColumn(name = "itemidfrom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMasterFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemnofrom")
    private String itemnofrom;
    @Size(max = 20)
    @Column(name = "colornofrom")
    private String colornofrom;
    @Size(max = 20)
    @Column(name = "brandfrom")
    private String brandfrom;
    @Size(max = 20)
    @Column(name = "batchfrom")
    private String batchfrom;
    @Size(max = 45)
    @Column(name = "snfrom")
    private String snfrom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtyfrom")
    private BigDecimal qtyfrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unitfrom")
    private String unitfrom;
    @JoinColumn(name = "warehousefrom", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouseFrom;

    @JoinColumn(name = "itemidto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMasterTo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemnoto")
    private String itemnoto;
    @Size(max = 20)
    @Column(name = "colornoto")
    private String colornoto;
    @Size(max = 20)
    @Column(name = "brandto")
    private String brandto;
    @Size(max = 20)
    @Column(name = "batchto")
    private String batchto;
    @Size(max = 45)
    @Column(name = "snto")
    private String snto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtyto")
    private BigDecimal qtyto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unitto")
    private String unitto;
    @JoinColumn(name = "warehouseto", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouseTo;

    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ItemExchange() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public ItemMaster getItemMasterFrom() {
        return itemMasterFrom;
    }

    public void setItemMasterFrom(ItemMaster itemMasterFrom) {
        this.itemMasterFrom = itemMasterFrom;
    }

    public String getItemnofrom() {
        return itemnofrom;
    }

    public void setItemnofrom(String itemnofrom) {
        this.itemnofrom = itemnofrom;
    }

    public String getColornofrom() {
        return colornofrom;
    }

    public void setColornofrom(String colornofrom) {
        this.colornofrom = colornofrom;
    }

    public String getBrandfrom() {
        return brandfrom;
    }

    public void setBrandfrom(String brandfrom) {
        this.brandfrom = brandfrom;
    }

    public String getBatchfrom() {
        return batchfrom;
    }

    public void setBatchfrom(String batchfrom) {
        this.batchfrom = batchfrom;
    }

    public String getSnfrom() {
        return snfrom;
    }

    public void setSnfrom(String snfrom) {
        this.snfrom = snfrom;
    }

    public BigDecimal getQtyfrom() {
        return qtyfrom;
    }

    public void setQtyfrom(BigDecimal qtyfrom) {
        this.qtyfrom = qtyfrom;
    }

    public String getUnitfrom() {
        return unitfrom;
    }

    public void setUnitfrom(String unitfrom) {
        this.unitfrom = unitfrom;
    }

    public Warehouse getWarehouseFrom() {
        return warehouseFrom;
    }

    public void setWarehouseFrom(Warehouse warehouseFrom) {
        this.warehouseFrom = warehouseFrom;
    }

    public ItemMaster getItemMasterTo() {
        return itemMasterTo;
    }

    public void setItemMasterTo(ItemMaster itemMasterTo) {
        this.itemMasterTo = itemMasterTo;
    }

    public String getItemnoto() {
        return itemnoto;
    }

    public void setItemnoto(String itemnoto) {
        this.itemnoto = itemnoto;
    }

    public String getColornoto() {
        return colornoto;
    }

    public void setColornoto(String colornoto) {
        this.colornoto = colornoto;
    }

    public String getBrandto() {
        return brandto;
    }

    public void setBrandto(String brandto) {
        this.brandto = brandto;
    }

    public String getBatchto() {
        return batchto;
    }

    public void setBatchto(String batchto) {
        this.batchto = batchto;
    }

    public String getSnto() {
        return snto;
    }

    public void setSnto(String snto) {
        this.snto = snto;
    }

    public BigDecimal getQtyto() {
        return qtyto;
    }

    public void setQtyto(BigDecimal qtyto) {
        this.qtyto = qtyto;
    }

    public String getUnitto() {
        return unitto;
    }

    public void setUnitto(String unitto) {
        this.unitto = unitto;
    }

    public Warehouse getWarehouseTo() {
        return warehouseTo;
    }

    public void setWarehouseTo(Warehouse warehouseTo) {
        this.warehouseTo = warehouseTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        if (!(object instanceof ItemExchange)) {
            return false;
        }
        ItemExchange other = (ItemExchange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemExchange[ id=" + id + " ]";
    }

    /**
     * @return the objtype
     */
    public String getObjtype() {
        return objtype;
    }

    /**
     * @param objtype the objtype to set
     */
    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    /**
     * @return the objno
     */
    public String getObjno() {
        return objno;
    }

    /**
     * @param objno the objno to set
     */
    public void setObjno(String objno) {
        this.objno = objno;
    }

}

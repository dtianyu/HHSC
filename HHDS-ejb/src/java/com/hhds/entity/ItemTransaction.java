/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.FormEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "itemtransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemTransaction.getRowCount", query = "SELECT COUNT(i) FROM ItemTransaction i")
    ,
    @NamedQuery(name = "ItemTransaction.findAll", query = "SELECT i FROM ItemTransaction i")
    ,
    @NamedQuery(name = "ItemTransaction.findById", query = "SELECT i FROM ItemTransaction i WHERE i.id = :id")
    ,
    @NamedQuery(name = "ItemTransaction.findByFormid", query = "SELECT i FROM ItemTransaction i WHERE i.formid = :formid")
    ,
    @NamedQuery(name = "ItemTransaction.findByStatus", query = "SELECT i FROM ItemTransaction i WHERE i.status = :status")})
public class ItemTransaction extends FormEntity {

    @JoinColumn(name = "trtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType transactionType;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 20)
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne
    private Warehouse warehouse;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    @Size(max = 45)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 20)
    @Column(name = "objno")
    private String objno;

    public ItemTransaction() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

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
        if (!(object instanceof ItemTransaction)) {
            return false;
        }
        ItemTransaction other = (ItemTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemTransaction[ id=" + id + " ]";
    }

    /**
     * @return the transactionType
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

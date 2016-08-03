/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

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
@Table(name = "productionfinish")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionFinish.getRowCount", query = "SELECT COUNT(f) FROM ProductionFinish f"),
    @NamedQuery(name = "ProductionFinish.findAll", query = "SELECT f FROM ProductionFinish f"),
    @NamedQuery(name = "ProductionFinish.findById", query = "SELECT f FROM ProductionFinish f WHERE f.id = :id"),
    @NamedQuery(name = "ProductionFinish.findByFormid", query = "SELECT f FROM ProductionFinish f WHERE f.formid = :formid"),
    @NamedQuery(name = "ProductionFinish.findByStatus", query = "SELECT f FROM ProductionFinish f WHERE f.status = :status")})
public class ProductionFinish extends FormEntity {

    @JoinColumn(name = "formtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType transactionType;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Column(name = "processid")
    private Integer processid;

    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;

    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 10)
    @Column(name = "srcformtype")
    private String srcformtype;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Size(max = 45)
    @Column(name = "srcitemno")
    private String srcitemno;

    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ProductionFinish() {
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
        if (!(object instanceof ProductionFinish)) {
            return false;
        }
        ProductionFinish other = (ProductionFinish) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.ejb.ProductionFinish[ id=" + id + " ]";
    }

    public String getFormkind() {
        return formkind;
    }

    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSrcformtype() {
        return srcformtype;
    }

    public void setSrcformtype(String srcformtype) {
        this.srcformtype = srcformtype;
    }

    public String getSrcformid() {
        return srcformid;
    }

    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    public String getSrcitemno() {
        return srcitemno;
    }

    public void setSrcitemno(String srcitemno) {
        this.srcitemno = srcitemno;
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

}

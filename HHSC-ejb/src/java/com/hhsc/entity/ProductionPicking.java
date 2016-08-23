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
@Table(name = "productionpicking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionPicking.findAll", query = "SELECT p FROM ProductionPicking p"),
    @NamedQuery(name = "ProductionPicking.findById", query = "SELECT p FROM ProductionPicking p WHERE p.id = :id"),
    @NamedQuery(name = "ProductionPicking.findByFormid", query = "SELECT p FROM ProductionPicking p WHERE p.formid = :formid"),
    @NamedQuery(name = "ProductionPicking.findByStatus", query = "SELECT p FROM ProductionPicking p WHERE p.status = :status")})
public class ProductionPicking extends FormEntity {

    @JoinColumn(name = "trtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType transactionType;
    @Size(max = 10)
    @Column(name = "trkind")
    private String trkind;
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

    public ProductionPicking() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTrkind() {
        return trkind;
    }

    public void setTrkind(String trkind) {
        this.trkind = trkind;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductionPicking)) {
            return false;
        }
        ProductionPicking other = (ProductionPicking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProductionPicking[ id=" + id + " ]";
    }

}

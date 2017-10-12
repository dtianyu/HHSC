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
@Table(name = "purchaserequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseRequest.getRowCount", query = "SELECT COUNT(p) FROM PurchaseRequest p")
    ,
    @NamedQuery(name = "PurchaseRequest.findAll", query = "SELECT p FROM PurchaseRequest p")
    ,
    @NamedQuery(name = "PurchaseRequest.findById", query = "SELECT p FROM PurchaseRequest p WHERE p.id = :id")
    ,
    @NamedQuery(name = "PurchaseRequest.findByFormid", query = "SELECT p FROM PurchaseRequest p WHERE p.formid = :formid")
    ,
    @NamedQuery(name = "PurchaseRequest.findByFormdate", query = "SELECT p FROM PurchaseRequest p WHERE p.formdate = :formdate")
    ,
    @NamedQuery(name = "PurchaseRequest.findByDeptId", query = "SELECT p FROM PurchaseRequest p WHERE p.dept.id = :deptid")
    ,
    @NamedQuery(name = "PurchaseRequest.findByUserId", query = "SELECT p FROM PurchaseRequest p WHERE p.systemuser.id = :systemuserid")
    ,
    @NamedQuery(name = "PurchaseRequest.findByPurtype", query = "SELECT p FROM PurchaseRequest p WHERE p.purtype = :purtype")
    ,
    @NamedQuery(name = "PurchaseRequest.findByPurkind", query = "SELECT p FROM PurchaseRequest p WHERE p.purkind = :purkind")
    ,
    @NamedQuery(name = "PurchaseRequest.findByStatus", query = "SELECT p FROM PurchaseRequest p WHERE p.status = :status")})
public class PurchaseRequest extends FormEntity {

    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser systemuser;
    @Size(max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public PurchaseRequest() {
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getSystemuser() {
        return systemuser;
    }

    public void setSystemuser(SystemUser systemuser) {
        this.systemuser = systemuser;
    }

    public String getPurtype() {
        return purtype;
    }

    public void setPurtype(String purtype) {
        this.purtype = purtype;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
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
        if (!(object instanceof PurchaseRequest)) {
            return false;
        }
        PurchaseRequest other = (PurchaseRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseRequest[ id=" + id + " ]";
    }

}

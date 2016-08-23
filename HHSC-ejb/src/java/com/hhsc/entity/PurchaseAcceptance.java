/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "purchaseacceptance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseAcceptance.getRowCount", query = "SELECT COUNT(p) FROM PurchaseAcceptance p"),
    @NamedQuery(name = "PurchaseAcceptance.findAll", query = "SELECT p FROM PurchaseAcceptance p"),
    @NamedQuery(name = "PurchaseAcceptance.findById", query = "SELECT p FROM PurchaseAcceptance p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseAcceptance.findByFormid", query = "SELECT p FROM PurchaseAcceptance p WHERE p.formid = :formid"),
    @NamedQuery(name = "PurchaseAcceptance.findByFormdate", query = "SELECT p FROM PurchaseAcceptance p WHERE p.formdate = :formdate"),
    @NamedQuery(name = "PurchaseAcceptance.findByVendorId", query = "SELECT p FROM PurchaseAcceptance p WHERE p.vendor.id = :vendorid"),
    @NamedQuery(name = "PurchaseAcceptance.findByDeptId", query = "SELECT p FROM PurchaseAcceptance p WHERE p.dept.id = :deptid"),
    @NamedQuery(name = "PurchaseAcceptance.findByStatus", query = "SELECT p FROM PurchaseAcceptance p WHERE p.status = :status")})
public class PurchaseAcceptance extends FormEntity {

    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    protected Vendor vendor;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    protected Department dept;
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = true)
    protected Warehouse warehouse;

    public PurchaseAcceptance() {
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
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
        if (!(object instanceof PurchaseAcceptance)) {
            return false;
        }
        PurchaseAcceptance other = (PurchaseAcceptance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseAcceptance[ id=" + id + " ]";
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

}

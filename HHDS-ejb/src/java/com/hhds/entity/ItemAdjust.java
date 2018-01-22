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
@Table(name = "itemadjust")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemAdjust.findAll", query = "SELECT i FROM ItemAdjust i")
    , @NamedQuery(name = "ItemAdjust.findById", query = "SELECT i FROM ItemAdjust i WHERE i.id = :id")
    , @NamedQuery(name = "ItemAdjust.findByFormid", query = "SELECT i FROM ItemAdjust i WHERE i.formid = :formid")
    , @NamedQuery(name = "ItemAdjust.findByFormdate", query = "SELECT i FROM ItemAdjust i WHERE i.formdate = :formdate")
    , @NamedQuery(name = "ItemAdjust.findByObjtype", query = "SELECT i FROM ItemAdjust i WHERE i.objtype = :objtype")
    , @NamedQuery(name = "ItemAdjust.findByObjno", query = "SELECT i FROM ItemAdjust i WHERE i.objno = :objno")
    , @NamedQuery(name = "ItemAdjust.findByReason", query = "SELECT i FROM ItemAdjust i WHERE i.reason = :reason")
    , @NamedQuery(name = "ItemAdjust.findByRemark", query = "SELECT i FROM ItemAdjust i WHERE i.remark = :remark")
    , @NamedQuery(name = "ItemAdjust.findByStatus", query = "SELECT i FROM ItemAdjust i WHERE i.status = :status")})
public class ItemAdjust extends FormEntity {

    @Size(max = 20)
    @Column(name = "deptno")
    private String deptno;
    @Size(max = 45)
    @Column(name = "deptname")
    private String deptname;
    @Size(max = 45)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 20)
    @Column(name = "objno")
    private String objno;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = true)
    private Warehouse warehouse;
    @JoinColumn(name = "warehouseno2", referencedColumnName = "warehouseno")
    @ManyToOne(optional = true)
    private Warehouse warehouse2;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ItemAdjust() {
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
     * @return the warehouse2
     */
    public Warehouse getWarehouse2() {
        return warehouse2;
    }

    /**
     * @param warehouse2 the warehouse2 to set
     */
    public void setWarehouse2(Warehouse warehouse2) {
        this.warehouse2 = warehouse2;
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
        if (!(object instanceof ItemAdjust)) {
            return false;
        }
        ItemAdjust other = (ItemAdjust) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhds.entity.ItemAdjust[ id=" + id + " ]";
    }

}

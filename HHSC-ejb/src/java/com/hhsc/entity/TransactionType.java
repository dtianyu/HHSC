/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "transactiontype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionType.getRowCount", query = "SELECT COUNT(t) FROM TransactionType t"),
    @NamedQuery(name = "TransactionType.findAll", query = "SELECT t FROM TransactionType t"),
    @NamedQuery(name = "TransactionType.findById", query = "SELECT t FROM TransactionType t WHERE t.id = :id"),
    @NamedQuery(name = "TransactionType.findByTrtype", query = "SELECT t FROM TransactionType t WHERE t.trtype = :trtype"),
    @NamedQuery(name = "TransactionType.findBySysid", query = "SELECT t FROM TransactionType t WHERE t.sysid = :sysid"),
    @NamedQuery(name = "TransactionType.findByIocode", query = "SELECT t FROM TransactionType t WHERE t.iocode = :iocode"),
    @NamedQuery(name = "TransactionType.findByHascost", query = "SELECT t FROM TransactionType t WHERE t.hascost = :hascost"),
    @NamedQuery(name = "TransactionType.findByStatus", query = "SELECT t FROM TransactionType t WHERE t.status = :status")})
public class TransactionType extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "trtype")
    private String trtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "trname")
    private String trname;
    @Column(name = "sysid")
    private Integer sysid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iocode")
    private int iocode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hascost")
    private boolean hascost;
    @Size(max = 45)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 10)
    @Column(name = "reasontype")
    private String reasontype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updateindate")
    private boolean updateindate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updateoutdate")
    private boolean updateoutdate;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public TransactionType() {
    }

    public TransactionType(Integer id, String trtype, String trname, int iocode, boolean hascost, boolean updateindate, boolean updateoutdate, String status) {
        this.trtype = trtype;
        this.trname = trname;
        this.iocode = iocode;
        this.hascost = hascost;
        this.updateindate = updateindate;
        this.updateoutdate = updateoutdate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrtype() {
        return trtype;
    }

    public void setTrtype(String trtype) {
        this.trtype = trtype;
    }

    public String getTrname() {
        return trname;
    }

    public void setTrname(String trname) {
        this.trname = trname;
    }

    public Integer getSysid() {
        return sysid;
    }

    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }

    public int getIocode() {
        return iocode;
    }

    public void setIocode(int iocode) {
        this.iocode = iocode;
    }

    public boolean getHascost() {
        return hascost;
    }

    public void setHascost(boolean hascost) {
        this.hascost = hascost;
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    public String getReasontype() {
        return reasontype;
    }

    public void setReasontype(String reasontype) {
        this.reasontype = reasontype;
    }

    public boolean getUpdateindate() {
        return updateindate;
    }

    public void setUpdateindate(boolean updateindate) {
        this.updateindate = updateindate;
    }

    public boolean getUpdateoutdate() {
        return updateoutdate;
    }

    public void setUpdateoutdate(boolean updateoutdate) {
        this.updateoutdate = updateoutdate;
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
        if (!(object instanceof TransactionType)) {
            return false;
        }
        TransactionType other = (TransactionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.TransactionType[ id=" + id + " ]";
    }

}

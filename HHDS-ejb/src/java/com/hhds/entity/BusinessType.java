/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.SuperEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "businesstype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessType.findAll", query = "SELECT b FROM BusinessType b")
    , @NamedQuery(name = "BusinessType.findById", query = "SELECT b FROM BusinessType b WHERE b.id = :id")
    , @NamedQuery(name = "BusinessType.findByBiztype", query = "SELECT b FROM BusinessType b WHERE b.biztype = :biztype")
    , @NamedQuery(name = "BusinessType.findByName", query = "SELECT b FROM BusinessType b WHERE b.name = :name")
    , @NamedQuery(name = "BusinessType.findByStatus", query = "SELECT b FROM BusinessType b WHERE b.status = :status")})
public class BusinessType extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "biztype")
    private String biztype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 10)
    @Column(name = "sysid")
    private String sysid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iocode")
    private int iocode;
    @Size(max = 100)
    @Column(name = "objtype")
    private String objtype;
    @Size(max = 45)
    @Column(name = "objselect")
    private String objselect;
    @Size(max = 100)
    @Column(name = "srctype")
    private String srctype;
    @Size(max = 45)
    @Column(name = "srcselect")
    private String srcselect;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public BusinessType() {
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public int getIocode() {
        return iocode;
    }

    public void setIocode(int iocode) {
        this.iocode = iocode;
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    public String getObjselect() {
        return objselect;
    }

    public void setObjselect(String objselect) {
        this.objselect = objselect;
    }

    public String getSrctype() {
        return srctype;
    }

    public void setSrctype(String srctype) {
        this.srctype = srctype;
    }

    public String getSrcselect() {
        return srcselect;
    }

    public void setSrcselect(String srcselect) {
        this.srcselect = srcselect;
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
        if (!(object instanceof BusinessType)) {
            return false;
        }
        BusinessType other = (BusinessType) object;
        if (Objects.equals(this.biztype, other.biztype)) {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.BusinessType[ id=" + id + " ]";
    }

}

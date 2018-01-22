/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "purchasekind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseKind.getRowCount", query = "SELECT COUNT(p) FROM PurchaseKind p")
    ,
    @NamedQuery(name = "PurchaseKind.findAll", query = "SELECT p FROM PurchaseKind p")
    ,
    @NamedQuery(name = "PurchaseKind.findById", query = "SELECT p FROM PurchaseKind p WHERE p.id = :id")
    ,
    @NamedQuery(name = "PurchaseKind.findByPurkind", query = "SELECT p FROM PurchaseKind p WHERE p.purkind = :purkind")
    ,
    @NamedQuery(name = "PurchaseKind.findByName", query = "SELECT p FROM PurchaseKind p WHERE p.name = :name")
    ,
    @NamedQuery(name = "PurchaseKind.findByProptype", query = "SELECT p FROM PurchaseKind p WHERE p.proptype = :proptype")
    ,
    @NamedQuery(name = "PurchaseKind.findByCharge", query = "SELECT p FROM PurchaseKind p WHERE p.charge = :charge")
    ,
    @NamedQuery(name = "PurchaseKind.findByIocode", query = "SELECT p FROM PurchaseKind p WHERE p.iocode = :iocode")
    ,
    @NamedQuery(name = "PurchaseKind.findByTradeId", query = "SELECT p FROM PurchaseKind p WHERE p.tradeid = :tradeid")
    ,
    @NamedQuery(name = "PurchaseKind.findByStatus", query = "SELECT p FROM PurchaseKind p WHERE p.status = :status")})
public class PurchaseKind implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 2)
    @Column(name = "proptype")
    private String proptype;
    @Column(name = "charge")
    private Boolean charge;
    @Column(name = "iocode")
    private Boolean iocode;
    @Column(name = "tradeid")
    private Integer tradeid;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    public PurchaseKind() {
    }

    public PurchaseKind(Integer id) {
        this.id = id;
    }

    public PurchaseKind(Integer id, String purkind, String name, String status) {
        this.id = id;
        this.purkind = purkind;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    public Boolean getIocode() {
        return iocode;
    }

    public void setIocode(Boolean iocode) {
        this.iocode = iocode;
    }

    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
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
        if (!(object instanceof PurchaseKind)) {
            return false;
        }
        PurchaseKind other = (PurchaseKind) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseKind[ id=" + id + " ]";
    }

}

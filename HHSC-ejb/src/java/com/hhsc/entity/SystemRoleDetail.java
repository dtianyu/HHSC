/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperDetailEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "systemroledetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemRoleDetail.findAll", query = "SELECT s FROM SystemRoleDetail s")
    , @NamedQuery(name = "SystemRoleDetail.findById", query = "SELECT s FROM SystemRoleDetail s WHERE s.id = :id")
    , @NamedQuery(name = "SystemRoleDetail.findByPId", query = "SELECT s FROM SystemRoleDetail s WHERE s.pid = :pid")
    , @NamedQuery(name = "SystemRoleDetail.findByUserId", query = "SELECT s FROM SystemRoleDetail s WHERE s.systemUser.id = :userid")})
public class SystemRoleDetail extends SuperDetailEntity {

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser systemUser;

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

    public SystemRoleDetail() {
        this.status = "N";
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
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
        if (!(object instanceof SystemRoleDetail)) {
            return false;
        }
        SystemRoleDetail other = (SystemRoleDetail) object;
        if ((this.pid == 0 && other.pid == 0) && (this.systemUser != other.systemUser)) {
            return false;
        }
        if ((this.pid == 0 && other.pid != 0) || (this.pid != 0 && other.pid == 0)) {
            return false;
        }
        return ((this.pid == other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SystemRoleDetail[ id=" + id + " ]";
    }

}

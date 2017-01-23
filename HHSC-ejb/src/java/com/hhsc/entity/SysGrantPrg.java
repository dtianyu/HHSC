/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperDetailEntity;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "sysgrantprg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysGrantPrg.findAll", query = "SELECT s FROM SysGrantPrg s")
    , @NamedQuery(name = "SysGrantPrg.findById", query = "SELECT s FROM SysGrantPrg s WHERE s.id = :id")
    , @NamedQuery(name = "SysGrantPrg.findByPId", query = "SELECT s FROM SysGrantPrg s WHERE s.pid = :pid")
    , @NamedQuery(name = "SysGrantPrg.findByKind", query = "SELECT s FROM SysGrantPrg s WHERE s.kind = :kind")
    , @NamedQuery(name = "SysGrantPrg.findByUserId", query = "SELECT s FROM SysGrantPrg s WHERE s.systemUser.id = :userid")
    , @NamedQuery(name = "SysGrantPrg.findByRoleId", query = "SELECT s FROM SysGrantPrg s WHERE s.systemRole.id = :roleid")})
public class SysGrantPrg extends SuperDetailEntity {

    @JoinColumn(name = "prgid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sysprg sysprg;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemUser systemUser;
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemRole systemRole;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "kind")
    private String kind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doadd")
    private boolean doadd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doedit")
    private boolean doedit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dodel")
    private boolean dodel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doprt")
    private boolean doprt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dopriv")
    private boolean dopriv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docfm")
    private boolean docfm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "douncfm")
    private boolean douncfm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doaudit")
    private boolean doaudit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dounaudit")
    private boolean dounaudit;
    @Size(max = 200)
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

    public SysGrantPrg() {
        this.status = "N";
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean getDoadd() {
        return doadd;
    }

    public void setDoadd(boolean doadd) {
        this.doadd = doadd;
    }

    public boolean getDoedit() {
        return doedit;
    }

    public void setDoedit(boolean doedit) {
        this.doedit = doedit;
    }

    public boolean getDodel() {
        return dodel;
    }

    public void setDodel(boolean dodel) {
        this.dodel = dodel;
    }

    public boolean getDoprt() {
        return doprt;
    }

    public void setDoprt(boolean doprt) {
        this.doprt = doprt;
    }

    public boolean getDopriv() {
        return dopriv;
    }

    public void setDopriv(boolean dopriv) {
        this.dopriv = dopriv;
    }

    public boolean getDocfm() {
        return docfm;
    }

    public void setDocfm(boolean docfm) {
        this.docfm = docfm;
    }

    public boolean getDouncfm() {
        return douncfm;
    }

    public void setDouncfm(boolean douncfm) {
        this.douncfm = douncfm;
    }

    public boolean getDoaudit() {
        return doaudit;
    }

    public void setDoaudit(boolean doaudit) {
        this.doaudit = doaudit;
    }

    public boolean getDounaudit() {
        return dounaudit;
    }

    public void setDounaudit(boolean dounaudit) {
        this.dounaudit = dounaudit;
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
        if (!(object instanceof SysGrantPrg)) {
            return false;
        }
        SysGrantPrg other = (SysGrantPrg) object;
        if ((this.kind != other.kind) || !Objects.equals(this.systemRole, other.systemRole) || !Objects.equals(this.systemUser, other.systemUser)) {
            return false;
        }
        if ((this.kind == other.kind) && ((Objects.equals(this.systemRole, other.systemRole)) || (Objects.equals(this.systemUser, other.systemUser))) && (Objects.equals(this.sysprg, other.sysprg))) {
            return true;
        }
        if ((this.kind == other.kind) && ((Objects.equals(this.systemRole, other.systemRole)) || (Objects.equals(this.systemUser, other.systemUser))) && (!Objects.equals(this.sysprg, other.sysprg))) {
            return false;
        }
        return (this.pid == other.pid && this.seq == other.seq);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SysGrantPrg[ id=" + id + " ]";
    }

    /**
     * @return the systemUser
     */
    public SystemUser getSystemUser() {
        return systemUser;
    }

    /**
     * @param systemUser the systemUser to set
     */
    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * @return the systemRole
     */
    public SystemRole getSystemRole() {
        return systemRole;
    }

    /**
     * @param systemRole the systemRole to set
     */
    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    /**
     * @return the sysprg
     */
    public Sysprg getSysprg() {
        return sysprg;
    }

    /**
     * @param sysprg the sysprg to set
     */
    public void setSysprg(Sysprg sysprg) {
        this.sysprg = sysprg;
    }

}

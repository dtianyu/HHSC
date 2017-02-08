/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sysgrantmodule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysGrantModule.getRowCount", query = "SELECT COUNT(s) FROM SysGrantModule s")
    ,
    @NamedQuery(name = "SysGrantModule.getRowCountByUserId", query = "SELECT COUNT(s) FROM SysGrantModule s WHERE s.systemUser.id = :userid ")
    ,
    @NamedQuery(name = "SysGrantModule.findAll", query = "SELECT s FROM SysGrantModule s ORDER BY s.systemUser.id,s.sysmodule.id")
    ,
    @NamedQuery(name = "SysGrantModule.findById", query = "SELECT s FROM SysGrantModule s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SysGrantModule.findByUserId", query = "SELECT s FROM SysGrantModule s WHERE s.kind='U' AND s.systemUser.id = :userid ORDER BY s.sysmodule.sortid ")
    ,
    @NamedQuery(name = "SysGrantModule.findByRoleId", query = "SELECT s FROM SysGrantModule s WHERE s.kind='R' AND s.systemRole.id = :roleid ORDER BY s.sysmodule.sortid ")
    ,
    @NamedQuery(name = "SysGrantModule.findByModuleId", query = "SELECT s FROM SysGrantModule s WHERE s.sysmodule.id = :moduleid ORDER BY s.sysmodule.sortid ")
    ,
    @NamedQuery(name = "SysGrantModule.findByStatus", query = "SELECT s FROM SysGrantModule s WHERE s.status = :status")})
public class SysGrantModule extends SuperEntity {

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemUser systemUser;
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemRole systemRole;
    @JoinColumn(name = "moduleid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sysmodule sysmodule;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    private String kind;

    public SysGrantModule() {
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
        if (!(object instanceof SysGrantModule)) {
            return false;
        }
        SysGrantModule other = (SysGrantModule) object;
        if ((this.kind != other.kind) || !Objects.equals(this.systemRole, other.systemRole) || !Objects.equals(this.systemUser, other.systemUser)) {
            return false;
        }
        if ((this.kind == other.kind) && ((Objects.equals(this.systemRole, other.systemRole)) || (Objects.equals(this.systemUser, other.systemUser))) && (Objects.equals(this.sysmodule, other.sysmodule))) {
            return true;
        }
        if ((this.kind == other.kind) && ((Objects.equals(this.systemRole, other.systemRole)) || (Objects.equals(this.systemUser, other.systemUser))) && (!Objects.equals(this.sysmodule, other.sysmodule))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SysGrantModule[ id=" + id + " ]";
    }

    /**
     * @return the sysmodule
     */
    public Sysmodule getSysmodule() {
        return sysmodule;
    }

    /**
     * @param sysmodule the sysmodule to set
     */
    public void setSysmodule(Sysmodule sysmodule) {
        this.sysmodule = sysmodule;
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
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

}

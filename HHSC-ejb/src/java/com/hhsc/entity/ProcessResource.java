/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperDetailEntity;
import java.math.BigDecimal;
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
@Table(name = "processresource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessResource.findAll", query = "SELECT i FROM ProcessResource i ORDER BY i.process.sortid,i.seq")
    ,
    @NamedQuery(name = "ProcessResource.findById", query = "SELECT i FROM ProcessResource i WHERE i.id = :id")
    ,
    @NamedQuery(name = "ProcessResource.findByPId", query = "SELECT i FROM ProcessResource i WHERE i.pid = :pid ORDER BY i.pid,i.seq")
    ,
    @NamedQuery(name = "ProcessResource.findByKind", query = "SELECT i FROM ProcessResource i WHERE i.kind = :kind")
    ,
    @NamedQuery(name = "ProcessResource.findByStatus", query = "SELECT i FROM ProcessResource i WHERE i.status = :status")})
public class ProcessResource extends SuperDetailEntity {

    @JoinColumn(name = "pid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Process process;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kind")
    private String kind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "valuetype")
    private String valuetype;
    @Column(name = "boolvalue")
    private Boolean boolvalue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numvalue")
    private BigDecimal numvalue;
    @Size(max = 45)
    @Column(name = "strvalue")
    private String strvalue;
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
    @Temporal(TemporalType.DATE)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.DATE)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.DATE)
    private Date cfmdate;

    public ProcessResource() {
        this.status = "N";
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValuetype() {
        return valuetype;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype;
    }

    public Boolean getBoolvalue() {
        return boolvalue;
    }

    public void setBoolvalue(Boolean boolvalue) {
        this.boolvalue = boolvalue;
    }

    public BigDecimal getNumvalue() {
        return numvalue;
    }

    public void setNumvalue(BigDecimal numvalue) {
        this.numvalue = numvalue;
    }

    public String getStrvalue() {
        return strvalue;
    }

    public void setStrvalue(String strvalue) {
        this.strvalue = strvalue;
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
        if (!(object instanceof ProcessResource)) {
            return false;
        }
        ProcessResource other = (ProcessResource) object;
        if ((this.pid != 0 && other.pid != 0) && !Objects.equals(this.pid, other.pid)) {
            return false;
        }
        if (Objects.equals(this.pid, other.pid) && !Objects.equals(this.kind, other.kind)) {
            return false;
        }
        if (Objects.equals(this.kind, other.kind) && !Objects.equals(this.content, other.content)) {
            return false;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProcessResource[ id=" + id + " ]";
    }

    /**
     * @return the process
     */
    public Process getProcess() {
        return process;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperDetailEntity;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "processdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessDetail.getRowCount", query = "SELECT COUNT(p) FROM ProcessDetail p")
    ,
    @NamedQuery(name = "ProcessDetail.findAll", query = "SELECT p FROM ProcessDetail p")
    ,
    @NamedQuery(name = "ProcessDetail.findById", query = "SELECT p FROM ProcessDetail p WHERE p.id = :id")
    ,
    @NamedQuery(name = "ProcessDetail.findByPId", query = "SELECT p FROM ProcessDetail p WHERE p.pid = :pid")
    ,
    @NamedQuery(name = "ProcessDetail.findByKind", query = "SELECT p FROM ProcessDetail p WHERE p.kind = :kind")
    ,
    @NamedQuery(name = "ProcessDetail.findByContent", query = "SELECT p FROM ProcessDetail p WHERE p.content = :content")})
public class ProcessDetail extends SuperDetailEntity {

    @JoinColumn(name = "procid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Process process;

    @Basic(optional = false)
    @NotNull
    @Column(name = "procid")
    private int procid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procseq")
    private int procseq;
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

    public ProcessDetail() {
    }

    public int getProcid() {
        return procid;
    }

    public void setProcid(int procid) {
        this.procid = procid;
    }

    public int getProcseq() {
        return procseq;
    }

    public void setProcseq(int procseq) {
        this.procseq = procseq;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessDetail)) {
            return false;
        }
        ProcessDetail other = (ProcessDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
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
        return "com.hhsc.entity.ProcessDetail[ id=" + id + " ]";
    }

    /**
     * @return the process
     */
    public Process getProcess() {
        return process;
    }

    /**
     * @param process the process to set
     */
    public void setProcess(Process process) {
        this.process = process;
    }

}

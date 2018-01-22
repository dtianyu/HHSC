/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
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
@Table(name = "productionresource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionResource.findAll", query = "SELECT p FROM ProductionResource p")
    ,
    @NamedQuery(name = "ProductionResource.findById", query = "SELECT p FROM ProductionResource p WHERE p.id = :id")
    ,
    @NamedQuery(name = "ProductionResource.findByPId", query = "SELECT p FROM ProductionResource p WHERE p.pid = :pid ORDER BY p.process.sortid,p.procseq")
    ,
    @NamedQuery(name = "ProductionResource.findByKind", query = "SELECT p FROM ProductionResource p WHERE p.kind = :kind")})
public class ProductionResource extends FormDetailEntity {

    @JoinColumn(name = "procid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Process process;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procseq")
    private int procseq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kind")
    private String kind;
    @Column(name = "itemid")
    private Integer itemid;
    @Size(max = 45)
    @Column(name = "itemno")
    private String itemno;
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

    public ProductionResource() {
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
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

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
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
        if (!(object instanceof ProductionResource)) {
            return false;
        }
        ProductionResource other = (ProductionResource) object;
        if (Objects.equals(this.process.getId(), other.process.getId()) && Objects.equals(this.kind, other.kind) && this.procseq == other.procseq) {
            return true;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProductionResource[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "purchasepayment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasePayment.findAll", query = "SELECT p FROM PurchasePayment p")
    , @NamedQuery(name = "PurchasePayment.findById", query = "SELECT p FROM PurchasePayment p WHERE p.id = :id")
    , @NamedQuery(name = "PurchasePayment.findByFormid", query = "SELECT p FROM PurchasePayment p WHERE p.formid = :formid")
    , @NamedQuery(name = "PurchasePayment.findByFormdate", query = "SELECT p FROM PurchasePayment p WHERE p.formdate = :formdate")
    , @NamedQuery(name = "PurchasePayment.findByFormtype", query = "SELECT p FROM PurchasePayment p WHERE p.formtype = :formtype")
    , @NamedQuery(name = "PurchasePayment.findByFormkind", query = "SELECT p FROM PurchasePayment p WHERE p.formkind = :formkind")
    , @NamedQuery(name = "PurchasePayment.findByStatus", query = "SELECT p FROM PurchasePayment p WHERE p.status = :status")})
public class PurchasePayment extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;

    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vendor vendor;

    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser systemUser;

    @Size(max = 100)
    @Column(name = "summary")
    private String summary;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "payamts")
    private BigDecimal payamts;
    @Column(name = "payamt")
    private BigDecimal payamt;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public PurchasePayment() {
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getFormkind() {
        return formkind;
    }

    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getPayamts() {
        return payamts;
    }

    public void setPayamts(BigDecimal payamts) {
        this.payamts = payamts;
    }

    public BigDecimal getPayamt() {
        return payamt;
    }

    public void setPayamt(BigDecimal payamt) {
        this.payamt = payamt;
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
        if (!(object instanceof PurchasePayment)) {
            return false;
        }
        PurchasePayment other = (PurchasePayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchasePayment[ id=" + id + " ]";
    }

}

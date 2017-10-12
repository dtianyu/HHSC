/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
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
@Table(name = "businesstransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessTransaction.findAll", query = "SELECT c FROM BusinessTransaction c")
    , @NamedQuery(name = "BusinessTransaction.findById", query = "SELECT c FROM BusinessTransaction c WHERE c.id = :id")
    , @NamedQuery(name = "BusinessTransaction.findByFormid", query = "SELECT c FROM BusinessTransaction c WHERE c.formid = :formid")
    , @NamedQuery(name = "BusinessTransaction.findByFormdate", query = "SELECT c FROM BusinessTransaction c WHERE c.formdate = :formdate")
    , @NamedQuery(name = "BusinessTransaction.findByFormtype", query = "SELECT c FROM BusinessTransaction c WHERE c.formtype = :formtype")
    , @NamedQuery(name = "BusinessTransaction.findByFormkind", query = "SELECT c FROM BusinessTransaction c WHERE c.formkind = :formkind")
    , @NamedQuery(name = "BusinessTransaction.findByUsername", query = "SELECT c FROM BusinessTransaction c WHERE c.username = :username")
    , @NamedQuery(name = "BusinessTransaction.findByCurrency", query = "SELECT c FROM BusinessTransaction c WHERE c.currency = :currency")
    , @NamedQuery(name = "BusinessTransaction.findByExchange", query = "SELECT c FROM BusinessTransaction c WHERE c.exchange = :exchange")
    , @NamedQuery(name = "BusinessTransaction.findByPayment", query = "SELECT c FROM BusinessTransaction c WHERE c.payment = :payment")
    , @NamedQuery(name = "BusinessTransaction.findBySummary", query = "SELECT c FROM BusinessTransaction c WHERE c.summary = :summary")
    , @NamedQuery(name = "BusinessTransaction.findByStatus", query = "SELECT c FROM BusinessTransaction c WHERE c.status = :status")})
public class BusinessTransaction extends FormEntity {

    @JoinColumn(name = "paykind", referencedColumnName = "paykind")
    @ManyToOne(optional = true)
    private PayKind payKind;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Size(max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "exchange")
    private BigDecimal exchange;
    @Size(min = 1, max = 45)
    @Column(name = "payment")
    private String payment;
    @Size(max = 45)
    @Column(name = "summary")
    private String summary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amt")
    private BigDecimal amt;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 45)
    @Column(name = "billno")
    private String billno;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public BusinessTransaction() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * @return the payKind
     */
    public PayKind getPayKind() {
        return payKind;
    }

    /**
     * @param payKind the payKind to set
     */
    public void setPayKind(PayKind payKind) {
        this.payKind = payKind;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getSrcapi() {
        return srcapi;
    }

    public void setSrcapi(String srcapi) {
        this.srcapi = srcapi;
    }

    public String getSrcformid() {
        return srcformid;
    }

    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    public Integer getSrcseq() {
        return srcseq;
    }

    public void setSrcseq(Integer srcseq) {
        this.srcseq = srcseq;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
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
        if (!(object instanceof BusinessTransaction)) {
            return false;
        }
        BusinessTransaction other = (BusinessTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.BusinessTransaction[ id=" + id + " ]";
    }

}

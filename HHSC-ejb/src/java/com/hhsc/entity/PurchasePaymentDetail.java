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
@Table(name = "purchasepaymentdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasePaymentDetail.findAll", query = "SELECT p FROM PurchasePaymentDetail p")
    , @NamedQuery(name = "PurchasePaymentDetail.findById", query = "SELECT p FROM PurchasePaymentDetail p WHERE p.id = :id")
    , @NamedQuery(name = "PurchasePaymentDetail.findByPId", query = "SELECT p FROM PurchasePaymentDetail p WHERE p.pid = :pid")})
public class PurchasePaymentDetail extends FormDetailEntity {

    @Size(max = 45)
    @Column(name = "summary")
    private String summary;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "payamts")
    private BigDecimal payamts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payamt")
    private BigDecimal payamt;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;

    public PurchasePaymentDetail() {
    }

    public PurchasePaymentDetail(Integer id) {
        this.id = id;
    }

    public PurchasePaymentDetail(Integer id, String pid, int seq, String currency, BigDecimal exchange, BigDecimal payamts, BigDecimal payamt) {
        this.id = id;
        this.pid = pid;
        this.seq = seq;
        this.currency = currency;
        this.exchange = exchange;
        this.payamts = payamts;
        this.payamt = payamt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasePaymentDetail)) {
            return false;
        }
        PurchasePaymentDetail other = (PurchasePaymentDetail) object;
        if (!Objects.equals(this.srcformid, other.srcformid)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && !Objects.equals(this.srcseq, other.srcseq)) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && Objects.equals(this.srcseq, other.srcseq)) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchasePaymentDetail[ id=" + id + " ]";
    }

}

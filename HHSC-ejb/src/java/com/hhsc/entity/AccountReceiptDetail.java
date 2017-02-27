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
@Table(name = "accountreceiptdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountReceiptDetail.findAll", query = "SELECT a FROM AccountReceiptDetail a"),
    @NamedQuery(name = "AccountReceiptDetail.findById", query = "SELECT a FROM AccountReceiptDetail a WHERE a.id = :id"),
    @NamedQuery(name = "AccountReceiptDetail.findByPId", query = "SELECT a FROM AccountReceiptDetail a WHERE a.pid = :pid")})
public class AccountReceiptDetail extends FormDetailEntity {

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
    @Column(name = "recamts")
    private BigDecimal recamts;
    @Column(name = "recamt")
    private BigDecimal recamt;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;

    public AccountReceiptDetail() {
        this.recamts = BigDecimal.ZERO;
        this.recamt = BigDecimal.ZERO;
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
        calcRecamt();
    }

    public BigDecimal getRecamts() {
        return recamts;
    }

    public void setRecamts(BigDecimal recamts) {
        this.recamts = recamts;
        calcRecamt();
    }

    public BigDecimal getRecamt() {
        return recamt;
    }

    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
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
        if (!(object instanceof AccountReceiptDetail)) {
            return false;
        }
        AccountReceiptDetail other = (AccountReceiptDetail) object;
        if (!Objects.equals(this.srcformid, other.srcformid)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && !Objects.equals(this.srcseq, other.srcseq)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && Objects.equals(this.srcseq, other.srcseq)) {
            return true;
        }
        return (Objects.equals(this.pid, other.pid) && (this.seq == other.seq));
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.AccountReceiptDetail[ id=" + id + " ]";
    }

    public void calcRecamt() {
        this.recamt = this.recamts.multiply(this.exchange);
    }

}

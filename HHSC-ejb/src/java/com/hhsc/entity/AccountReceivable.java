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
@Table(name = "accountreceivable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountReceivable.findAll", query = "SELECT a FROM AccountReceivable a"),
    @NamedQuery(name = "AccountReceivable.findById", query = "SELECT a FROM AccountReceivable a WHERE a.id = :id"),
    @NamedQuery(name = "AccountReceivable.findByFormid", query = "SELECT a FROM AccountReceivable a WHERE a.formid = :formid"),
    @NamedQuery(name = "AccountReceivable.findByFormdate", query = "SELECT a FROM AccountReceivable a WHERE a.formdate = :formdate"),
    @NamedQuery(name = "AccountReceivable.findByCustomerid", query = "SELECT a FROM AccountReceivable a WHERE a.customer.id = :customerid"),
    @NamedQuery(name = "AccountReceivable.findByDeptid", query = "SELECT a FROM AccountReceivable a WHERE a.deptid = :deptid"),
    @NamedQuery(name = "AccountReceivable.findBySalerid", query = "SELECT a FROM AccountReceivable a WHERE a.salerid = :salerid"),
    @NamedQuery(name = "AccountReceivable.findByStatus", query = "SELECT a FROM AccountReceivable a WHERE a.status = :status")})
public class AccountReceivable extends FormEntity {

    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @Column(name = "deptid")
    private Integer deptid;
    @Basic(optional = true)
    @NotNull
    @Column(name = "salerid")
    private int salerid;
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
    @Column(name = "paydate")
    @Temporal(TemporalType.DATE)
    private Date paydate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amt")
    private BigDecimal amt;
    @Column(name = "preamts")
    private BigDecimal preamts;
    @Column(name = "preamt")
    private BigDecimal preamt;
    @Column(name = "addamts")
    private BigDecimal addamts;
    @Column(name = "addamt")
    private BigDecimal addamt;
    @Column(name = "offamts")
    private BigDecimal offamts;
    @Column(name = "offamt")
    private BigDecimal offamt;
    @Column(name = "extaxs")
    private BigDecimal extaxs;
    @Column(name = "extax")
    private BigDecimal extax;
    @Column(name = "taxess")
    private BigDecimal taxess;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "recamts")
    private BigDecimal recamts;
    @Column(name = "recamt")
    private BigDecimal recamt;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "taxtype")
    private String taxtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxrate")
    private BigDecimal taxrate;

    public AccountReceivable() {
        this.exchange = BigDecimal.ONE;
        this.amts = BigDecimal.ZERO;
        this.amt = BigDecimal.ZERO;
        this.extaxs = BigDecimal.ZERO;
        this.extax = BigDecimal.ZERO;
        this.taxess = BigDecimal.ZERO;
        this.taxes = BigDecimal.ZERO;
        this.preamts = BigDecimal.ZERO;
        this.preamt = BigDecimal.ZERO;
        this.addamts = BigDecimal.ZERO;
        this.addamt = BigDecimal.ZERO;
        this.offamts = BigDecimal.ZERO;
        this.offamt = BigDecimal.ZERO;
        this.recamts = BigDecimal.ZERO;
        this.recamt = BigDecimal.ZERO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public int getSalerid() {
        return salerid;
    }

    public void setSalerid(int salerid) {
        this.salerid = salerid;
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

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
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

    public BigDecimal getPreamts() {
        return preamts;
    }

    public void setPreamts(BigDecimal preamts) {
        this.preamts = preamts;
    }

    public BigDecimal getPreamt() {
        return preamt;
    }

    public void setPreamt(BigDecimal preamt) {
        this.preamt = preamt;
    }

    public BigDecimal getAddamts() {
        return addamts;
    }

    public void setAddamts(BigDecimal addamts) {
        this.addamts = addamts;
    }

    public BigDecimal getAddamt() {
        return addamt;
    }

    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    public BigDecimal getOffamts() {
        return offamts;
    }

    public void setOffamts(BigDecimal offamts) {
        this.offamts = offamts;
    }

    public BigDecimal getOffamt() {
        return offamt;
    }

    public void setOffamt(BigDecimal offamt) {
        this.offamt = offamt;
    }

    public BigDecimal getExtaxs() {
        return extaxs;
    }

    public void setExtaxs(BigDecimal extaxs) {
        this.extaxs = extaxs;
        this.amts = this.taxess.add(this.extaxs);
    }

    public BigDecimal getExtax() {
        return extax;
    }

    public void setExtax(BigDecimal extax) {
        this.extax = extax;
        this.amt = this.taxes.add(this.extax);
    }

    public BigDecimal getTaxess() {
        return taxess;
    }

    public void setTaxess(BigDecimal taxess) {
        this.taxess = taxess;
        this.amts = this.taxess.add(this.extaxs);
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
        this.amt = this.taxes.add(this.extax);
    }

    public BigDecimal getRecamts() {
        return recamts;
    }

    public void setRecamts(BigDecimal recamts) {
        this.recamts = recamts;
    }

    public BigDecimal getRecamt() {
        return recamt;
    }

    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
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
        if (!(object instanceof AccountReceivable)) {
            return false;
        }
        AccountReceivable other = (AccountReceivable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.AccountReceivable[ id=" + id + " ]";
    }

    /**
     * @return the taxtype
     */
    public String getTaxtype() {
        return taxtype;
    }

    /**
     * @param taxtype the taxtype to set
     */
    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    /**
     * @return the taxkind
     */
    public String getTaxkind() {
        return taxkind;
    }

    /**
     * @param taxkind the taxkind to set
     */
    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
    }

    /**
     * @return the taxrate
     */
    public BigDecimal getTaxrate() {
        return taxrate;
    }

    /**
     * @param taxrate the taxrate to set
     */
    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public void calcLocalAmounts() {
        this.amt = this.amts.multiply(this.exchange);
        this.extax = this.extaxs.multiply(this.exchange);
        this.taxes = this.taxess.multiply(this.exchange);
    }

}

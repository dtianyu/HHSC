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
@Table(name = "purchaseorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrder.getRowCount", query = "SELECT COUNT(p) FROM PurchaseOrder p")
    ,
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT p FROM PurchaseOrder p")
    ,
    @NamedQuery(name = "PurchaseOrder.findById", query = "SELECT p FROM PurchaseOrder p WHERE p.id = :id")
    ,
    @NamedQuery(name = "PurchaseOrder.findByFormid", query = "SELECT p FROM PurchaseOrder p WHERE p.formid = :formid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByVendorId", query = "SELECT p FROM PurchaseOrder p WHERE p.vendor.id = :vendorid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByStatus", query = "SELECT p FROM PurchaseOrder p WHERE p.status = :status")})
public class PurchaseOrder extends FormEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    protected boolean abroad;
    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vendor vendor;
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
    @Size(max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 45)
    @Column(name = "tradename")
    private String tradename;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
    @Size(max = 45)
    @Column(name = "refno")
    private String refno;
    @Column(name = "totalextax")
    private BigDecimal totalextax;
    @Column(name = "totaltaxes")
    private BigDecimal totaltaxes;
    @Column(name = "totalamts")
    private BigDecimal totalamts;
    @Size(max = 10)
    @Column(name = "shiptype")
    private String shiptype;
    @Size(max = 200)
    @Column(name = "shipadd")
    private String shipadd;
    @Size(max = 200)
    @Column(name = "shipmarks")
    private String shipmarks;
    @Column(name = "freight")
    private BigDecimal freight;
    @Column(name = "insurance")
    private BigDecimal insurance;
    @Column(name = "othercharges")
    private BigDecimal othercharges;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public PurchaseOrder() {
        this.abroad = false;
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

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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

    public String getTaxtype() {
        return taxtype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public String getTaxkind() {
        return taxkind;
    }

    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public BigDecimal getTotalextax() {
        return totalextax;
    }

    public void setTotalextax(BigDecimal totalextax) {
        this.totalextax = totalextax;
    }

    public BigDecimal getTotaltaxes() {
        return totaltaxes;
    }

    public void setTotaltaxes(BigDecimal totaltaxes) {
        this.totaltaxes = totaltaxes;
    }

    public BigDecimal getTotalamts() {
        return totalamts;
    }

    public void setTotalamts(BigDecimal totalamts) {
        this.totalamts = totalamts;
    }

    public String getShiptype() {
        return shiptype;
    }

    public void setShiptype(String shiptype) {
        this.shiptype = shiptype;
    }

    public String getShipadd() {
        return shipadd;
    }

    public void setShipadd(String shipadd) {
        this.shipadd = shipadd;
    }

    public String getShipmarks() {
        return shipmarks;
    }

    public void setShipmarks(String shipmarks) {
        this.shipmarks = shipmarks;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getOthercharges() {
        return othercharges;
    }

    public void setOthercharges(BigDecimal othercharges) {
        this.othercharges = othercharges;
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
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.PurchaseOrder[ id=" + id + " ]";
    }

    /**
     * @return the abroad
     */
    public boolean isAbroad() {
        return abroad;
    }

    /**
     * @param abroad the abroad to set
     */
    public void setAbroad(boolean abroad) {
        this.abroad = abroad;
    }

}

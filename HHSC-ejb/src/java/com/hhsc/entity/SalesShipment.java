/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

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
@Table(name = "salesshipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesShipment.getRowCount", query = "SELECT COUNT(s) FROM SalesShipment s")
    ,
    @NamedQuery(name = "SalesShipment.findAll", query = "SELECT s FROM SalesShipment s")
    ,
    @NamedQuery(name = "SalesShipment.findById", query = "SELECT s FROM SalesShipment s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SalesShipment.findByFormid", query = "SELECT s FROM SalesShipment s WHERE s.formid = :formid")
    ,
    @NamedQuery(name = "SalesShipment.findByFormdate", query = "SELECT s FROM SalesShipment s WHERE s.formdate = :formdate")
    ,
    @NamedQuery(name = "SalesShipment.findByShipkind", query = "SELECT s FROM SalesShipment s WHERE s.shipkind = :shipkind")
    ,
    @NamedQuery(name = "SalesShipment.findByStatus", query = "SELECT s FROM SalesShipment s WHERE s.status = :status")})
public class SalesShipment extends FormEntity {

    @JoinColumn(name = "shiptype", referencedColumnName = "type")
    @ManyToOne(optional = false)
    private SalesType shiptype;
    @Size(max = 10)
    @Column(name = "shipkind")
    private String shipkind;
    @Column(name = "abroad")
    private Boolean abroad;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    @JoinColumn(name = "salerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser salesman;
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
    @Column(name = "totalextax")
    private BigDecimal totalextax;
    @Column(name = "totaltaxes")
    private BigDecimal totaltaxes;
    @Column(name = "totalamts")
    private BigDecimal totalamts;
    @Size(max = 45)
    @Column(name = "deliverytype")
    private String deliverytype;
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
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = true)
    protected Warehouse warehouse;

    public SalesShipment() {
    }

    public SalesType getShiptype() {
        return shiptype;
    }

    public void setShiptype(SalesType shiptype) {
        this.shiptype = shiptype;
    }

    public String getShipkind() {
        return shipkind;
    }

    public void setShipkind(String shipkind) {
        this.shipkind = shipkind;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getSalesman() {
        return salesman;
    }

    public void setSalesman(SystemUser salesman) {
        this.salesman = salesman;
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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
        if (!(object instanceof SalesShipment)) {
            return false;
        }
        SalesShipment other = (SalesShipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return this.formid.equals(other.formid);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesShipment[ id=" + id + " ]";
    }

    public Boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(Boolean abroad) {
        this.abroad = abroad;
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

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
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

}

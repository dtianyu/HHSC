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
@Table(name = "salesorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrder.getRowCount", query = "SELECT COUNT(s) FROM SalesOrder s")
    ,
    @NamedQuery(name = "SalesOrder.findAll", query = "SELECT s FROM SalesOrder s")
    ,
    @NamedQuery(name = "SalesOrder.findById", query = "SELECT s FROM SalesOrder s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SalesOrder.findByFormid", query = "SELECT s FROM SalesOrder s WHERE s.formid = :formid")
    ,
    @NamedQuery(name = "SalesOrder.findByFormdate", query = "SELECT s FROM SalesOrder s WHERE s.formdate = :formdate")
    ,
    @NamedQuery(name = "SalesOrder.findByCustomerId", query = "SELECT s FROM SalesOrder s WHERE s.customer.id = :customerid")
    ,
    @NamedQuery(name = "SalesOrder.findByDeptId", query = "SELECT s FROM SalesOrder s WHERE s.dept.id = :deptid")
    ,
    @NamedQuery(name = "SalesOrder.findBySalesmanId", query = "SELECT s FROM SalesOrder s WHERE s.salesman.id = :salesmanid")
    ,
    @NamedQuery(name = "SalesOrder.findByItemId", query = "SELECT s FROM SalesOrder s WHERE s.itemmaster.id = :itemmasterid")
    ,
    @NamedQuery(name = "SalesOrder.findByItemno", query = "SELECT s FROM SalesOrder s WHERE s.itemno = :itemno")
    ,
    @NamedQuery(name = "SalesOrder.findByCustomeritemno", query = "SELECT s FROM SalesOrder s WHERE s.customeritemno = :customeritemno")
    ,
    @NamedQuery(name = "SalesOrder.findByTradetype", query = "SELECT s FROM SalesOrder s WHERE s.tradetype = :tradetype")
    ,
    @NamedQuery(name = "SalesOrder.findByTradename", query = "SELECT s FROM SalesOrder s WHERE s.tradename = :tradename")
    ,
    @NamedQuery(name = "SalesOrder.findByPaymentid", query = "SELECT s FROM SalesOrder s WHERE s.paymentid = :paymentid")
    ,
    @NamedQuery(name = "SalesOrder.findByPayment", query = "SELECT s FROM SalesOrder s WHERE s.payment = :payment")
    ,
    @NamedQuery(name = "SalesOrder.findByRefno", query = "SELECT s FROM SalesOrder s WHERE s.refno = :refno")
    ,
    @NamedQuery(name = "SalesOrder.findByStatus", query = "SELECT s FROM SalesOrder s WHERE s.status = :status")})
public class SalesOrder extends FormEntity {

    @JoinColumn(name = "ordertype", referencedColumnName = "type")
    @ManyToOne(optional = false)
    private SalesType ordertype;
    @Size(max = 10)
    @Column(name = "orderkind")
    private String orderkind;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    @JoinColumn(name = "salerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser salesman;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 100)
    @Column(name = "itemspec")
    protected String itemspec;
    @Size(max = 100)
    @Column(name = "itemimg")
    private String itemimg;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
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
    @Column(name = "paymentid")
    private Integer paymentid;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
    @Size(max = 45)
    @Column(name = "refno")
    private String refno;
    @Column(name = "designsets")
    private Integer designsets;
    @Column(name = "designprice")
    private BigDecimal designprice;
    @Column(name = "totaldesign")
    private BigDecimal totaldesign;
    @Column(name = "totalextax")
    private BigDecimal totalextax;
    @Column(name = "totaltaxes")
    private BigDecimal totaltaxes;
    @Column(name = "totalamts")
    private BigDecimal totalamts;
    @Size(max = 10)
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
    @Size(max = 200)
    @Column(name = "salesremark")
    private String salesremark;
    @Size(max = 200)
    @Column(name = "testremark")
    private String testremark;
    @Size(max = 200)
    @Column(name = "productremark")
    private String productremark;
    @Size(max = 200)
    @Column(name = "packremark")
    private String packremark;

    public SalesOrder() {
        this.designsets = 0;
        this.designprice = BigDecimal.ZERO;
        this.exchange = BigDecimal.ZERO;
        this.totaldesign = BigDecimal.ZERO;
        this.totalextax = BigDecimal.ZERO;
        this.totaltaxes = BigDecimal.ZERO;
        this.totalamts = BigDecimal.ZERO;
    }

    public SalesType getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(SalesType ordertype) {
        this.ordertype = ordertype;
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

    public ItemMaster getItemmaster() {
        return itemmaster;
    }

    public void setItemmaster(ItemMaster itemmaster) {
        this.itemmaster = itemmaster;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getItemimg() {
        return itemimg;
    }

    public void setItemimg(String itemimg) {
        this.itemimg = itemimg;
    }

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
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

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
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

    public Integer getDesignsets() {
        return designsets;
    }

    public void setDesignsets(Integer designsets) {
        this.designsets = designsets;
    }

    public BigDecimal getDesignprice() {
        return designprice;
    }

    public void setDesignprice(BigDecimal designprice) {
        this.designprice = designprice;
    }

    public BigDecimal getTotaldesign() {
        return totaldesign;
    }

    public void setTotaldesign(BigDecimal totaldesign) {
        this.totaldesign = totaldesign;
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

    public String getSalesremark() {
        return salesremark;
    }

    public void setSalesremark(String salesremark) {
        this.salesremark = salesremark;
    }

    public String getTestremark() {
        return testremark;
    }

    public void setTestremark(String testremark) {
        this.testremark = testremark;
    }

    public String getProductremark() {
        return productremark;
    }

    public void setProductremark(String productremark) {
        this.productremark = productremark;
    }

    public String getPackremark() {
        return packremark;
    }

    public void setPackremark(String packremark) {
        this.packremark = packremark;
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
        if (!(object instanceof SalesOrder)) {
            return false;
        }
        SalesOrder other = (SalesOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrder[ id=" + id + " ]";
    }

    /**
     * @return the itemspec
     */
    public String getItemspec() {
        return itemspec;
    }

    /**
     * @param itemspec the itemspec to set
     */
    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    /**
     * @return the orderkind
     */
    public String getOrderkind() {
        return orderkind;
    }

    /**
     * @param orderkind the orderkind to set
     */
    public void setOrderkind(String orderkind) {
        this.orderkind = orderkind;
    }

}

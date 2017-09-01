/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

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
@Table(name = "salesorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrder.findAll", query = "SELECT s FROM SalesOrder s")
    , @NamedQuery(name = "SalesOrder.findById", query = "SELECT s FROM SalesOrder s WHERE s.id = :id")
    , @NamedQuery(name = "SalesOrder.findByFormid", query = "SELECT s FROM SalesOrder s WHERE s.formid = :formid")
    , @NamedQuery(name = "SalesOrder.findByFormdate", query = "SELECT s FROM SalesOrder s WHERE s.formdate = :formdate")
    , @NamedQuery(name = "SalesOrder.findByFormtype", query = "SELECT s FROM SalesOrder s WHERE s.formtype = :formtype")
    , @NamedQuery(name = "SalesOrder.findByFormkind", query = "SELECT s FROM SalesOrder s WHERE s.formkind = :formkind")
    , @NamedQuery(name = "SalesOrder.findByCustomerId", query = "SELECT s FROM SalesOrder s WHERE s.customer.id = :customerid")
    , @NamedQuery(name = "SalesOrder.findByStatus", query = "SELECT s FROM SalesOrder s WHERE s.status = :status")})
public class SalesOrder extends FormEntity {

    @Size(max = 45)
    @Column(name = "salesman")
    private String salesman;

    @Column(name = "printdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printdate;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Customer customer;
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
    @Column(name = "refno")
    private String refno;
    @Column(name = "totalextax")
    private BigDecimal totalextax;
    @Column(name = "totaltaxes")
    private BigDecimal totaltaxes;
    @Column(name = "totalamts")
    private BigDecimal totalamts;
    @Size(max = 45)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 45)
    @Column(name = "mailadd")
    private String mailadd;
    @Size(max = 20)
    @Column(name = "province")
    private String province;
    @Size(max = 20)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "area")
    private String area;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 200)
    @Column(name = "shipmarks")
    private String shipmarks;
    @Size(max = 10)
    @Column(name = "deliverytype")
    private String deliverytype;
    @Column(name = "freight")
    private BigDecimal freight;
    @Column(name = "insurance")
    private BigDecimal insurance;
    @Column(name = "othercharges")
    private BigDecimal othercharges;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 200)
    @Column(name = "salesremark")
    private String salesremark;
    @Size(max = 200)
    @Column(name = "packremark")
    private String packremark;
    @Size(max = 10)
    @Column(name = "bill")
    private String bill;
    @Size(max = 20)
    @Column(name = "billtype")
    private String billtype;
    @Size(max = 100)
    @Column(name = "billtitle")
    private String billtitle;
    @Size(max = 45)
    @Column(name = "uscc")
    private String uscc;
    @Size(max = 45)
    @Column(name = "paykind")
    private String paykind;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
    @Column(name = "paydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paydate;
    @Size(max = 10)
    @Column(name = "period")
    private String period;
    @Column(name = "firstdelivery")
    @Temporal(TemporalType.DATE)
    private Date firstdelivery;
    @Column(name = "firsttime")
    @Temporal(TemporalType.TIME)
    private Date firsttime;
    @Column(name = "lastdelivery")
    @Temporal(TemporalType.DATE)
    private Date lastdelivery;
    @Column(name = "lasttime")
    @Temporal(TemporalType.TIME)
    private Date lasttime;

    public SalesOrder() {

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailadd() {
        return mailadd;
    }

    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getShipmarks() {
        return shipmarks;
    }

    public void setShipmarks(String shipmarks) {
        this.shipmarks = shipmarks;
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
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

    public String getSalesremark() {
        return salesremark;
    }

    public void setSalesremark(String salesremark) {
        this.salesremark = salesremark;
    }

    public String getPackremark() {
        return packremark;
    }

    public void setPackremark(String packremark) {
        this.packremark = packremark;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBilltitle() {
        return billtitle;
    }

    public void setBilltitle(String billtitle) {
        this.billtitle = billtitle;
    }

    public String getUscc() {
        return uscc;
    }

    public void setUscc(String uscc) {
        this.uscc = uscc;
    }

    public String getPaykind() {
        return paykind;
    }

    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getFirstdelivery() {
        return firstdelivery;
    }

    public void setFirstdelivery(Date firstdelivery) {
        this.firstdelivery = firstdelivery;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Date getLastdelivery() {
        return lastdelivery;
    }

    public void setLastdelivery(Date lastdelivery) {
        this.lastdelivery = lastdelivery;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
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
        return "com.hhds.entity.SalesOrder[ id=" + id + " ]";
    }

    /**
     * @return the salesman
     */
    public String getSalesman() {
        return salesman;
    }

    /**
     * @param salesman the salesman to set
     */
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    /**
     * @return the printdate
     */
    public Date getPrintdate() {
        return printdate;
    }

    /**
     * @param printdate the printdate to set
     */
    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    /**
     * @return the deliverydate
     */
    public Date getDeliverydate() {
        return deliverydate;
    }

}

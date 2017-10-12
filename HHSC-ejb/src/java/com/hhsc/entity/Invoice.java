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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
    , @NamedQuery(name = "Invoice.findByFormid", query = "SELECT i FROM Invoice i WHERE i.formid = :formid")
    , @NamedQuery(name = "Invoice.findByStatus", query = "SELECT i FROM Invoice i WHERE i.status = :status")})
public class Invoice extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customer")
    private String customer;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Size(max = 20)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 45)
    @Column(name = "mailadd")
    private String mailadd;
    @Size(max = 200)
    @Column(name = "contactadd")
    private String contactadd;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 10)
    @Column(name = "country")
    private String country;
    @Size(max = 10)
    @Column(name = "area")
    private String area;
    @Size(max = 10)
    @Column(name = "city")
    private String city;
    @Column(name = "deptid")
    private Integer deptid;
    @Column(name = "salerid")
    private Integer salerid;
    @Column(name = "itemid")
    private Integer itemid;
    @Size(max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 100)
    @Column(name = "itemspec")
    private String itemspec;
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
    @Column(name = "totalextax")
    private BigDecimal totalextax;
    @Column(name = "totaltaxes")
    private BigDecimal totaltaxes;
    @Column(name = "totalamts")
    private BigDecimal totalamts;
    @Size(max = 10)
    @Column(name = "shipmode")
    private String shipmode;
    @Column(name = "shipdate")
    @Temporal(TemporalType.DATE)
    private Date shipdate;
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
    @Column(name = "components")
    private BigDecimal components;
    @Column(name = "bankcharge")
    private BigDecimal bankcharge;
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
    @Size(max = 45)
    @Column(name = "GW")
    private String gw;
    @Size(max = 45)
    @Column(name = "NW")
    private String nw;
    @Size(max = 20)
    @Column(name = "osa1")
    private String osa1;
    @Size(max = 20)
    @Column(name = "osa2")
    private String osa2;
    @Size(max = 20)
    @Column(name = "osa3")
    private String osa3;
    @Size(max = 20)
    @Column(name = "osa4")
    private String osa4;
    @Size(max = 20)
    @Column(name = "osa5")
    private String osa5;

    public Invoice() {
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getMailadd() {
        return mailadd;
    }

    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

    public String getContactadd() {
        return contactadd;
    }

    public void setContactadd(String contactadd) {
        this.contactadd = contactadd;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getSalerid() {
        return salerid;
    }

    public void setSalerid(Integer salerid) {
        this.salerid = salerid;
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

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
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

    public String getShipmode() {
        return shipmode;
    }

    public void setShipmode(String shipmode) {
        this.shipmode = shipmode;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
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

    public BigDecimal getComponents() {
        return components;
    }

    public void setComponents(BigDecimal components) {
        this.components = components;
    }

    public BigDecimal getBankcharge() {
        return bankcharge;
    }

    public void setBankcharge(BigDecimal bankcharge) {
        this.bankcharge = bankcharge;
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

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getNw() {
        return nw;
    }

    public void setNw(String nw) {
        this.nw = nw;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.Invoice[ id=" + id + " ]";
    }

    /**
     * @return the osa1
     */
    public String getOsa1() {
        return osa1;
    }

    /**
     * @param osa1 the osa1 to set
     */
    public void setOsa1(String osa1) {
        this.osa1 = osa1;
    }

    /**
     * @return the osa2
     */
    public String getOsa2() {
        return osa2;
    }

    /**
     * @param osa2 the osa2 to set
     */
    public void setOsa2(String osa2) {
        this.osa2 = osa2;
    }

    /**
     * @return the osa3
     */
    public String getOsa3() {
        return osa3;
    }

    /**
     * @param osa3 the osa3 to set
     */
    public void setOsa3(String osa3) {
        this.osa3 = osa3;
    }

    /**
     * @return the osa4
     */
    public String getOsa4() {
        return osa4;
    }

    /**
     * @param osa4 the osa4 to set
     */
    public void setOsa4(String osa4) {
        this.osa4 = osa4;
    }

    /**
     * @return the osa5
     */
    public String getOsa5() {
        return osa5;
    }

    /**
     * @param osa5 the osa5 to set
     */
    public void setOsa5(String osa5) {
        this.osa5 = osa5;
    }

}

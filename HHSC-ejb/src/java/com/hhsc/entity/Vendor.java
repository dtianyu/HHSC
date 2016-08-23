/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
import java.math.BigDecimal;
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
@Table(name = "vendor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor.findAll", query = "SELECT v FROM Vendor v"),
    @NamedQuery(name = "Vendor.findById", query = "SELECT v FROM Vendor v WHERE v.id = :id"),
    @NamedQuery(name = "Vendor.findByVendorno", query = "SELECT v FROM Vendor v WHERE v.vendorno = :vendorno"),
    @NamedQuery(name = "Vendor.findByVendor", query = "SELECT v FROM Vendor v WHERE v.vendor = :vendor"),
    @NamedQuery(name = "Vendor.findByFullname", query = "SELECT v FROM Vendor v WHERE v.fullname = :fullname"),
    @NamedQuery(name = "Vendor.findBySimplecode", query = "SELECT v FROM Vendor v WHERE v.simplecode = :simplecode"),
    @NamedQuery(name = "Vendor.findByContacter", query = "SELECT v FROM Vendor v WHERE v.contacter = :contacter"),
    @NamedQuery(name = "Vendor.findByContactadd", query = "SELECT v FROM Vendor v WHERE v.contactadd = :contactadd"),
    @NamedQuery(name = "Vendor.findByZipcode", query = "SELECT v FROM Vendor v WHERE v.zipcode = :zipcode"),
    @NamedQuery(name = "Vendor.findByCountry", query = "SELECT v FROM Vendor v WHERE v.country = :country"),
    @NamedQuery(name = "Vendor.findByArea", query = "SELECT v FROM Vendor v WHERE v.area = :area"),
    @NamedQuery(name = "Vendor.findByCity", query = "SELECT v FROM Vendor v WHERE v.city = :city"),
    @NamedQuery(name = "Vendor.findByBuyerid", query = "SELECT v FROM Vendor v WHERE v.buyerid = :buyerid"),
    @NamedQuery(name = "Vendor.findByDeptid", query = "SELECT v FROM Vendor v WHERE v.deptid = :deptid"),
    @NamedQuery(name = "Vendor.findByAbroad", query = "SELECT v FROM Vendor v WHERE v.abroad = :abroad"),
    @NamedQuery(name = "Vendor.findByCurrency", query = "SELECT v FROM Vendor v WHERE v.currency = :currency"),
    @NamedQuery(name = "Vendor.findByTaxtype", query = "SELECT v FROM Vendor v WHERE v.taxtype = :taxtype"),
    @NamedQuery(name = "Vendor.findByTaxkind", query = "SELECT v FROM Vendor v WHERE v.taxkind = :taxkind"),
    @NamedQuery(name = "Vendor.findByTaxrate", query = "SELECT v FROM Vendor v WHERE v.taxrate = :taxrate"),
    @NamedQuery(name = "Vendor.findByTradetype", query = "SELECT v FROM Vendor v WHERE v.tradetype = :tradetype"),
    @NamedQuery(name = "Vendor.findByPaymentid", query = "SELECT v FROM Vendor v WHERE v.paymentid = :paymentid"),
    @NamedQuery(name = "Vendor.findByPurkind", query = "SELECT v FROM Vendor v WHERE v.purkind = :purkind")})
public class Vendor extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "vendorno")
    private String vendorno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "vendor")
    private String vendor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 10)
    @Column(name = "simplecode")
    private String simplecode;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Size(max = 20)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 20)
    @Column(name = "fax2")
    private String fax2;
    @Size(max = 20)
    @Column(name = "boss")
    private String boss;
    @Size(max = 45)
    @Column(name = "weburl")
    private String weburl;
    @Size(max = 20)
    @Column(name = "src")
    private String src;
    @Size(max = 20)
    @Column(name = "grade")
    private String grade;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 200)
    @Column(name = "industry")
    private String industry;
    @Size(max = 20)
    @Column(name = "contacter")
    private String contacter;
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
    @Column(name = "buyerid")
    private Integer buyerid;
    @Column(name = "deptid")
    private Integer deptid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    private boolean abroad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "taxtype")
    private String taxtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxrate")
    private BigDecimal taxrate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 45)
    @Column(name = "tradename")
    protected String tradename;
    @Column(name = "paymentid")
    private Integer paymentid;
    @Size(max = 45)
    @Column(name = "payment")
    protected String payment;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Size(max = 200)
    @Column(name = "regaddress")
    private String regaddress;
    @Column(name = "regcapital")
    private BigDecimal regcapital;
    @Size(max = 30)
    @Column(name = "taxid")
    private String taxid;
    @Size(max = 60)
    @Column(name = "bankid")
    private String bankid;
    @Size(max = 60)
    @Column(name = "bankaccount")
    private String bankaccount;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public Vendor() {
    }

    public String getVendorno() {
        return vendorno;
    }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSimplecode() {
        return simplecode;
    }

    public void setSimplecode(String simplecode) {
        this.simplecode = simplecode;
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

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
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

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(boolean abroad) {
        this.abroad = abroad;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public BigDecimal getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(BigDecimal regcapital) {
        this.regcapital = regcapital;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
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
        if (!(object instanceof Vendor)) {
            return false;
        }
        Vendor other = (Vendor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.Vendor[ id=" + id + " ]";
    }

    /**
     * @return the tradename
     */
    public String getTradename() {
        return tradename;
    }

    /**
     * @param tradename the tradename to set
     */
    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    /**
     * @return the payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

}

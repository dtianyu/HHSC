/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
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
@Table(name = "salestransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesTransaction.getRowCount", query = "SELECT COUNT(s) FROM SalesTransaction s"),
    @NamedQuery(name = "SalesTransaction.findAll", query = "SELECT s FROM SalesTransaction s"),
    @NamedQuery(name = "SalesTransaction.findById", query = "SELECT s FROM SalesTransaction s WHERE s.id = :id"),
    @NamedQuery(name = "SalesTransaction.findByFormid", query = "SELECT s FROM SalesTransaction s WHERE s.formid = :formid"),
    @NamedQuery(name = "SalesTransaction.findByFormidAndSeq", query = "SELECT s FROM SalesTransaction s WHERE s.formid = :formid AND s.seq=:seq"),
    @NamedQuery(name = "SalesTransaction.findByPId", query = "SELECT s FROM SalesTransaction s WHERE s.pid = :pid"),
    @NamedQuery(name = "SalesTransaction.findByAbroad", query = "SELECT s FROM SalesTransaction s WHERE s.abroad = :abroad"),
    @NamedQuery(name = "SalesTransaction.findByCustomerId", query = "SELECT s FROM SalesTransaction s WHERE s.customer.id = :customerid"),
    @NamedQuery(name = "SalesTransaction.findBySalerid", query = "SELECT s FROM SalesTransaction s WHERE s.salerid = :salerid"),
    @NamedQuery(name = "SalesTransaction.findByStatus", query = "SELECT s FROM SalesTransaction s WHERE s.status = :status")})
public class SalesTransaction extends FormDetailEntity {

    @JoinColumn(name = "salerid", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false)
    private SystemUser systemUser;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "formid")
    private String formid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "formdate")
    @Temporal(TemporalType.DATE)
    private Date formdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "shiptype")
    private String shiptype;
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

    @Basic(optional = false)
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMaster;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 100)
    @Column(name = "itemimg")
    private String itemimg;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 100)
    @Column(name = "customercolorno")
    private String customercolorno;
    @Size(max = 45)
    @Column(name = "customerrefno")
    private String customerrefno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "extax")
    private BigDecimal extax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "billqty")
    private BigDecimal billqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipamts")
    private BigDecimal shipamts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipamt")
    private BigDecimal shipamt;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxamts")
    private BigDecimal taxamts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxamt")
    private BigDecimal taxamt;

    public SalesTransaction() {
        this.pid = "";
        this.preamts = BigDecimal.ZERO;
        this.preamt = BigDecimal.ZERO;
        this.addamts = BigDecimal.ZERO;
        this.addamt = BigDecimal.ZERO;
        this.offamts = BigDecimal.ZERO;
        this.offamt = BigDecimal.ZERO;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public Date getFormdate() {
        return formdate;
    }

    public void setFormdate(Date formdate) {
        this.formdate = formdate;
    }

    public String getShiptype() {
        return shiptype;
    }

    public void setShiptype(String shiptype) {
        this.shiptype = shiptype;
    }

    public String getShipkind() {
        return shipkind;
    }

    public void setShipkind(String shipkind) {
        this.shipkind = shipkind;
    }

    public Boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(Boolean abroad) {
        this.abroad = abroad;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
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

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
    }

    public String getCustomercolorno() {
        return customercolorno;
    }

    public void setCustomercolorno(String customercolorno) {
        this.customercolorno = customercolorno;
    }

    public String getCustomerrefno() {
        return customerrefno;
    }

    public void setCustomerrefno(String customerrefno) {
        this.customerrefno = customerrefno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getExtax() {
        return extax;
    }

    public void setExtax(BigDecimal extax) {
        this.extax = extax;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
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

    public BigDecimal getBillqty() {
        return billqty;
    }

    public void setBillqty(BigDecimal billqty) {
        this.billqty = billqty;
    }

    public BigDecimal getShipamts() {
        return shipamts;
    }

    public void setShipamts(BigDecimal shipamts) {
        this.shipamts = shipamts;
    }

    public BigDecimal getShipamt() {
        return shipamt;
    }

    public void setShipamt(BigDecimal shipamt) {
        this.shipamt = shipamt;
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

    public BigDecimal getTaxamts() {
        return taxamts;
    }

    public void setTaxamts(BigDecimal taxamts) {
        this.taxamts = taxamts;
    }

    public BigDecimal getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(BigDecimal taxamt) {
        this.taxamt = taxamt;
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
        if (!(object instanceof SalesTransaction)) {
            return false;
        }
        SalesTransaction other = (SalesTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesTransaction[ id=" + id + " ]";
    }

    /**
     * @return the systemUser
     */
    public SystemUser getSystemUser() {
        return systemUser;
    }

    /**
     * @param systemUser the systemUser to set
     */
    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

}

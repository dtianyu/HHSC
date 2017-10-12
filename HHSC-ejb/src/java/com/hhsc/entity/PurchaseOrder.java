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
    @NamedQuery(name = "PurchaseOrder.findByFormdate", query = "SELECT p FROM PurchaseOrder p WHERE p.formdate = :formdate")
    ,
    @NamedQuery(name = "PurchaseOrder.findByPurtype", query = "SELECT p FROM PurchaseOrder p WHERE p.purtype = :purtype")
    ,
    @NamedQuery(name = "PurchaseOrder.findByPurkind", query = "SELECT p FROM PurchaseOrder p WHERE p.purkind = :purkind")
    ,
    @NamedQuery(name = "PurchaseOrder.findByVendorId", query = "SELECT p FROM PurchaseOrder p WHERE p.vendor.id = :vendorid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByDeptId", query = "SELECT p FROM PurchaseOrder p WHERE p.dept.id = :deptid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByBuyerId", query = "SELECT p FROM PurchaseOrder p WHERE p.buyer.id = :buyerid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByItemId", query = "SELECT p FROM PurchaseOrder p WHERE p.itemmaster.id = :itemmasterid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByItemno", query = "SELECT p FROM PurchaseOrder p WHERE p.itemno = :itemno")
    ,
    @NamedQuery(name = "PurchaseOrder.findByVendoritemno", query = "SELECT p FROM PurchaseOrder p WHERE p.vendoritemno = :vendoritemno")
    ,
    @NamedQuery(name = "PurchaseOrder.findByTradetype", query = "SELECT p FROM PurchaseOrder p WHERE p.tradetype = :tradetype")
    ,
    @NamedQuery(name = "PurchaseOrder.findByTradename", query = "SELECT p FROM PurchaseOrder p WHERE p.tradename = :tradename")
    ,
    @NamedQuery(name = "PurchaseOrder.findByPaymentid", query = "SELECT p FROM PurchaseOrder p WHERE p.paymentid = :paymentid")
    ,
    @NamedQuery(name = "PurchaseOrder.findByPayment", query = "SELECT p FROM PurchaseOrder p WHERE p.payment = :payment")
    ,
    @NamedQuery(name = "PurchaseOrder.findByRefno", query = "SELECT p FROM PurchaseOrder p WHERE p.refno = :refno")
    ,
    @NamedQuery(name = "PurchaseOrder.findByStatus", query = "SELECT p FROM PurchaseOrder p WHERE p.status = :status")})
public class PurchaseOrder extends FormEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    protected boolean abroad;
    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vendor vendor;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    @JoinColumn(name = "buyerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser buyer;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne
    private ItemMaster itemmaster;
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
    @Column(name = "vendoritemno")
    private String vendoritemno;
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
    @Column(name = "shiptype")
    private String shiptype;
    @Size(max = 200)
    @Column(name = "shpadd")
    private String shpadd;
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
    @Size(max = 200)
    @Column(name = "testremark")
    private String testremark;
    @Size(max = 200)
    @Column(name = "productremark")
    private String productremark;
    @Size(max = 200)
    @Column(name = "packremark")
    private String packremark;

    public PurchaseOrder() {
        this.abroad = false;
    }

    public String getPurtype() {
        return purtype;
    }

    public void setPurtype(String purtype) {
        this.purtype = purtype;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getBuyer() {
        return buyer;
    }

    public void setBuyer(SystemUser buyer) {
        this.buyer = buyer;
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

    public String getVendoritemno() {
        return vendoritemno;
    }

    public void setVendoritemno(String vendoritemno) {
        this.vendoritemno = vendoritemno;
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

    public String getShiptype() {
        return shiptype;
    }

    public void setShiptype(String shiptype) {
        this.shiptype = shiptype;
    }

    public String getShpadd() {
        return shpadd;
    }

    public void setShpadd(String shpadd) {
        this.shpadd = shpadd;
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

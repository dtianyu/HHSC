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
@Table(name = "invoicedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceDetail.findAll", query = "SELECT i FROM InvoiceDetail i")
    , @NamedQuery(name = "InvoiceDetail.findById", query = "SELECT i FROM InvoiceDetail i WHERE i.id = :id")
    , @NamedQuery(name = "InvoiceDetail.findByPId", query = "SELECT i FROM InvoiceDetail i WHERE i.pid = :pid")})
public class InvoiceDetail extends FormDetailEntity {

    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 100)
    @Column(name = "customercolorno")
    private String customercolorno;
    @Column(name = "itemid")
    private Integer itemid;
    @Size(max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    @Size(max = 45)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Size(max = 100)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 100)
    @Column(name = "itemmake")
    private String itemmake;
    @Size(max = 45)
    @Column(name = "itemweight")
    private String itemweight;
    @Size(max = 45)
    @Column(name = "itemyarncount")
    private String itemyarncount;
    @Size(max = 45)
    @Column(name = "itemdensity")
    private String itemdensity;
    @Size(max = 45)
    @Column(name = "itemwidth")
    private String itemwidth;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quotedprice")
    private BigDecimal quotedprice;
    @Column(name = "discount")
    private BigDecimal discount;
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
    @Column(name = "extax")
    private BigDecimal extax;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Column(name = "printdate")
    @Temporal(TemporalType.DATE)
    private Date printdate;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.DATE)
    private Date deliverydate;
    @Column(name = "deliverytime")
    @Temporal(TemporalType.TIME)
    private Date deliverytime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issqty")
    private BigDecimal issqty;
    @Column(name = "proqty")
    private BigDecimal proqty;
    @Column(name = "inqty")
    private BigDecimal inqty;
    @Column(name = "shipqty")
    private BigDecimal shipqty;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recamts")
    private BigDecimal recamts;
    @Size(max = 45)
    @Column(name = "hscode")
    private String hscode;
    @Size(max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 100)
    @Column(name = "relapi")
    private String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    private String relformid;
    @Column(name = "relseq")
    private Integer relseq;

    public InvoiceDetail() {
        this.issqty = BigDecimal.ZERO;
        this.proqty = BigDecimal.ZERO;
        this.inqty = BigDecimal.ZERO;
        this.shipqty = BigDecimal.ZERO;
        this.recamts = BigDecimal.ZERO;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getCustomercolorno() {
        return customercolorno;
    }

    public void setCustomercolorno(String customercolorno) {
        this.customercolorno = customercolorno;
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

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
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

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    public String getItemmake() {
        return itemmake;
    }

    public void setItemmake(String itemmake) {
        this.itemmake = itemmake;
    }

    public String getItemweight() {
        return itemweight;
    }

    public void setItemweight(String itemweight) {
        this.itemweight = itemweight;
    }

    public String getItemyarncount() {
        return itemyarncount;
    }

    public void setItemyarncount(String itemyarncount) {
        this.itemyarncount = itemyarncount;
    }

    public String getItemdensity() {
        return itemdensity;
    }

    public void setItemdensity(String itemdensity) {
        this.itemdensity = itemdensity;
    }

    public String getItemwidth() {
        return itemwidth;
    }

    public void setItemwidth(String itemwidth) {
        this.itemwidth = itemwidth;
    }

    public BigDecimal getQuotedprice() {
        return quotedprice;
    }

    public void setQuotedprice(BigDecimal quotedprice) {
        this.quotedprice = quotedprice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public Date getPrintdate() {
        return printdate;
    }

    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(Date deliverytime) {
        this.deliverytime = deliverytime;
    }

    public BigDecimal getIssqty() {
        return issqty;
    }

    public void setIssqty(BigDecimal issqty) {
        this.issqty = issqty;
    }

    public BigDecimal getProqty() {
        return proqty;
    }

    public void setProqty(BigDecimal proqty) {
        this.proqty = proqty;
    }

    public BigDecimal getInqty() {
        return inqty;
    }

    public void setInqty(BigDecimal inqty) {
        this.inqty = inqty;
    }

    public BigDecimal getShipqty() {
        return shipqty;
    }

    public void setShipqty(BigDecimal shipqty) {
        this.shipqty = shipqty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRelapi() {
        return relapi;
    }

    public void setRelapi(String relapi) {
        this.relapi = relapi;
    }

    public String getRelformid() {
        return relformid;
    }

    public void setRelformid(String relformid) {
        this.relformid = relformid;
    }

    public Integer getRelseq() {
        return relseq;
    }

    public void setRelseq(Integer relseq) {
        this.relseq = relseq;
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
        if (!(object instanceof InvoiceDetail)) {
            return false;
        }
        InvoiceDetail other = (InvoiceDetail) object;
        if ((this.id != null && other.id != null)) {
            return this.id.equals(other.id);
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.InvoiceDetail[ id=" + id + " ]";
    }

    /**
     * @return the recamts
     */
    public BigDecimal getRecamts() {
        return recamts;
    }

    /**
     * @param recamts the recamts to set
     */
    public void setRecamts(BigDecimal recamts) {
        this.recamts = recamts;
    }

    /**
     * @return the hscode
     */
    public String getHscode() {
        return hscode;
    }

    /**
     * @param hscode the hscode to set
     */
    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

}

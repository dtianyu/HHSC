/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "productionorderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionOrderDetailForQuery.findAll", query = "SELECT p FROM ProductionOrderDetailForQuery p")
    ,
    @NamedQuery(name = "ProductionOrderDetailForQuery.findById", query = "SELECT p FROM ProductionOrderDetailForQuery p WHERE p.id = :id")
    ,
    @NamedQuery(name = "ProductionOrderDetailForQuery.findByPId", query = "SELECT p FROM ProductionOrderDetailForQuery p WHERE p.productionOrder.id = :pid")})
public class ProductionOrderDetailForQuery extends BaseEntity {

    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne(optional = false)
    private ProductionOrder productionOrder;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 20)
    @Column(name = "customercolorno")
    private String customercolorno;
    @Size(max = 45)
    @Column(name = "customeritemno")
    private String customeritemno;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderqty")
    private BigDecimal orderqty;
    @Size(max = 10)
    @Column(name = "orderunit")
    private String orderunit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 45)
    @Column(name = "unit")
    private String unit;
    @Column(name = "issqty")
    private BigDecimal issqty;
    @Column(name = "issdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issdate;
    @Column(name = "finqty")
    private BigDecimal finqty;
    @Column(name = "findate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date findate;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
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

    public ProductionOrderDetailForQuery() {
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

    public BigDecimal getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(BigDecimal orderqty) {
        this.orderqty = orderqty;
    }

    public String getOrderunit() {
        return orderunit;
    }

    public void setOrderunit(String orderunit) {
        this.orderunit = orderunit;
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

    public BigDecimal getIssqty() {
        return issqty;
    }

    public void setIssqty(BigDecimal issqty) {
        this.issqty = issqty;
    }

    public Date getIssdate() {
        return issdate;
    }

    public void setIssdate(Date issdate) {
        this.issdate = issdate;
    }

    public BigDecimal getFinqty() {
        return finqty;
    }

    public void setFinqty(BigDecimal finqty) {
        this.finqty = finqty;
    }

    public Date getFindate() {
        return findate;
    }

    public void setFindate(Date findate) {
        this.findate = findate;
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
        if (!(object instanceof ProductionOrderDetailForQuery)) {
            return false;
        }
        ProductionOrderDetailForQuery other = (ProductionOrderDetailForQuery) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProductionOrderDetailForQuery[ id=" + id + " ]";
    }

    /**
     * @return the customeritemno
     */
    public String getCustomeritemno() {
        return customeritemno;
    }

    /**
     * @param customeritemno the customeritemno to set
     */
    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
    }

    /**
     * @return the productionOrder
     */
    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    /**
     * @param productionOrder the productionOrder to set
     */
    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

}

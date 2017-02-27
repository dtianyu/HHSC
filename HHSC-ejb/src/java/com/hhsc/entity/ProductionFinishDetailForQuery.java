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
@Table(name = "productionfinishdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionFinishDetailForQuery.findAll", query = "SELECT f FROM ProductionFinishDetailForQuery f")
    ,
    @NamedQuery(name = "ProductionFinishDetailForQuery.findById", query = "SELECT f FROM ProductionFinishDetailForQuery f WHERE f.id = :id")
    ,
    @NamedQuery(name = "ProductionFinishDetailForQuery.findByPId", query = "SELECT f FROM ProductionFinishDetailForQuery f WHERE f.productionFinish.formid = :pid")})
public class ProductionFinishDetailForQuery extends BaseEntity {

    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne(optional = false)
    private ProductionFinish productionFinish;

    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @JoinColumn(name = "formtype", referencedColumnName = "trtype")
    @ManyToOne(optional = false)
    private TransactionType transactionType;
    @JoinColumn(name = "warehouseno", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    @JoinColumn(name = "badwarehouse", referencedColumnName = "warehouseno")
    @ManyToOne(optional = false)
    private Warehouse warehouse2;
    @Size(max = 100)
    @Column(name = "relapi")
    private String relapi;
    @Size(max = 20)
    @Column(name = "relformid")
    private String relformid;
    @Column(name = "relseq")
    private Integer relseq;
    @Size(max = 45)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    @JoinColumn(name = "designid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster design;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "designno")
    private String designno;
    @Size(max = 100)
    @Column(name = "designimg")
    private String designimg;
    @Size(max = 100)
    @Column(name = "designspec")
    private String designspec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 100)
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
    @Size(max = 45)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allowqty")
    private BigDecimal allowqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 45)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qcqty")
    private BigDecimal qcqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "defqty")
    private BigDecimal defqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "badqty")
    private BigDecimal badqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "addqty")
    private BigDecimal addqty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "srcseq")
    private int srcseq;

    public ProductionFinishDetailForQuery() {
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
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

    public BigDecimal getAllowqty() {
        return allowqty;
    }

    public void setAllowqty(BigDecimal allowqty) {
        this.allowqty = allowqty;
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
        if (!(object instanceof ProductionFinishDetailForQuery)) {
            return false;
        }
        ProductionFinishDetailForQuery other = (ProductionFinishDetailForQuery) object;
        if (!Objects.equals(this.srcformid, other.srcformid)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && !Objects.equals(this.srcseq, other.srcseq)) {
            return false;
        }
        if (Objects.equals(this.srcformid, other.srcformid) && Objects.equals(this.srcseq, other.srcseq)) {
            return true;
        }
        return this.getSeq() == other.getSeq();
    }

    @Override
    public String toString() {
        return "com.hhsc.ejb.ProductionFinishDetail[ id=" + id + " ]";
    }

    /**
     * @return the badqty
     */
    public BigDecimal getBadqty() {
        return badqty;
    }

    /**
     * @param badqty the badqty to set
     */
    public void setBadqty(BigDecimal badqty) {
        this.badqty = badqty;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    public ItemMaster getDesign() {
        return design;
    }

    public void setDesign(ItemMaster design) {
        this.design = design;
    }

    public String getDesignno() {
        return designno;
    }

    public void setDesignno(String designno) {
        this.designno = designno;
    }

    public String getDesignimg() {
        return designimg;
    }

    public void setDesignimg(String designimg) {
        this.designimg = designimg;
    }

    public String getDesignspec() {
        return designspec;
    }

    public void setDesignspec(String designspec) {
        this.designspec = designspec;
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

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
    }

    public ItemMaster getItemmaster() {
        return itemmaster;
    }

    public void setItemmaster(ItemMaster itemmaster) {
        this.itemmaster = itemmaster;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getQcqty() {
        return qcqty;
    }

    public void setQcqty(BigDecimal qcqty) {
        this.qcqty = qcqty;
    }

    public BigDecimal getDefqty() {
        return defqty;
    }

    public void setDefqty(BigDecimal defqty) {
        this.defqty = defqty;
    }

    public BigDecimal getAddqty() {
        return addqty;
    }

    public void setAddqty(BigDecimal addqty) {
        this.addqty = addqty;
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

    public int getSrcseq() {
        return srcseq;
    }

    public void setSrcseq(int srcseq) {
        this.srcseq = srcseq;
    }

    /**
     * @return the warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * @return the transactionType
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the warehouse2
     */
    public Warehouse getWarehouse2() {
        return warehouse2;
    }

    /**
     * @param warehouse2 the warehouse2 to set
     */
    public void setWarehouse2(Warehouse warehouse2) {
        this.warehouse2 = warehouse2;
    }

    /**
     * @return the productionFinish
     */
    public ProductionFinish getProductionFinish() {
        return productionFinish;
    }

    /**
     * @param productionFinish the productionFinish to set
     */
    public void setProductionFinish(ProductionFinish productionFinish) {
        this.productionFinish = productionFinish;
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

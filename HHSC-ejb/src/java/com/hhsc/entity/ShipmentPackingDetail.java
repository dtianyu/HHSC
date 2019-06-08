/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
import java.math.BigDecimal;
import java.util.Objects;
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
@Table(name = "shipmentpackingdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentPackingDetail.findAll", query = "SELECT s FROM ShipmentPackingDetail s")
    , @NamedQuery(name = "ShipmentPackingDetail.findById", query = "SELECT s FROM ShipmentPackingDetail s WHERE s.id = :id")
    , @NamedQuery(name = "ShipmentPackingDetail.findByPId", query = "SELECT s FROM ShipmentPackingDetail s WHERE s.pid = :pid")})
public class ShipmentPackingDetail extends FormDetailEntity {

    @JoinColumn(name = "designid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemDesign;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private ItemMaster itemMaster;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "designno")
    private String designno;
    @Size(max = 45)
    @Column(name = "designDesc")
    private String designDesc;
    @Size(max = 100)
    @Column(name = "designSpec")
    private String designSpec;
    @Size(max = 100)
    @Column(name = "designMake")
    private String designMake;

    @Size(max = 45)
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Size(max = 45)
    @Column(name = "netWeight")
    private String netWeight;
    @Size(max = 45)
    @Column(name = "netTotal")
    private String netTotal;
    @Size(max = 45)
    @Column(name = "roughWeight")
    private String roughWeight;
    @Size(max = 45)
    @Column(name = "roughTotal")
    private String roughTotal;
    @Size(max = 45)
    @Column(name = "boxNo")
    private String boxNo;
    @Size(max = 45)
    @Column(name = "boxSize")
    private String boxSize;
    @Size(max = 45)
    @Column(name = "boxNum")
    private String boxNum;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public ShipmentPackingDetail() {
        this.qty = BigDecimal.ZERO;
    }

    /**
     * @return the itemDesign
     */
    public ItemMaster getItemDesign() {
        return itemDesign;
    }

    /**
     * @param itemDesign the itemDesign to set
     */
    public void setItemDesign(ItemMaster itemDesign) {
        this.itemDesign = itemDesign;
    }

    /**
     * @return the itemMaster
     */
    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    /**
     * @param itemMaster the itemMaster to set
     */
    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public String getDesignno() {
        return designno;
    }

    public void setDesignno(String designno) {
        this.designno = designno;
    }

    public String getDesignDesc() {
        return designDesc;
    }

    public void setDesignDesc(String designDesc) {
        this.designDesc = designDesc;
    }

    public String getDesignSpec() {
        return designSpec;
    }

    public void setDesignSpec(String designSpec) {
        this.designSpec = designSpec;
    }

    public String getDesignMake() {
        return designMake;
    }

    public void setDesignMake(String designMake) {
        this.designMake = designMake;
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

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(String netTotal) {
        this.netTotal = netTotal;
    }

    public String getRoughWeight() {
        return roughWeight;
    }

    public void setRoughWeight(String roughWeight) {
        this.roughWeight = roughWeight;
    }

    public String getRoughTotal() {
        return roughTotal;
    }

    public void setRoughTotal(String roughTotal) {
        this.roughTotal = roughTotal;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(String boxSize) {
        this.boxSize = boxSize;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShipmentPackingDetail)) {
            return false;
        }
        ShipmentPackingDetail other = (ShipmentPackingDetail) object;
        if (this.id != null && other.id != null) {
            return Objects.equals(this.id, other.id);
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ShipmentPackingDetail[ id=" + id + " ]";
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}

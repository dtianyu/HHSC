/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "itemmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMaster.getRowCount", query = "SELECT COUNT(i) FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findAll", query = "SELECT i FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findByItemno", query = "SELECT i FROM ItemMaster i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemMaster.findByItemdesc", query = "SELECT i FROM ItemMaster i WHERE i.itemdesc = :itemdesc"),
    @NamedQuery(name = "ItemMaster.findByItemspec", query = "SELECT i FROM ItemMaster i WHERE i.itemspec = :itemspec"),
    @NamedQuery(name = "ItemMaster.findByItemproperty", query = "SELECT i FROM ItemMaster i WHERE i.itemproperty = :itemproperty"),
    @NamedQuery(name = "ItemMaster.findByCategoryid", query = "SELECT i FROM ItemMaster i WHERE i.categoryid = :categoryid")})
public class ItemMaster extends BaseEntityWithOperate{

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Size(max = 100)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 45)
    @Column(name = "itemproperty")
    private String itemproperty;
    @Size(max = 45)
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
    @Column(name = "categoryid")
    private Integer categoryid;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 45)
    @Column(name = "batch")
    private String batch;
    @Size(max = 45)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "stdcost")
    private BigDecimal stdcost;
    @Column(name = "purprice")
    private BigDecimal purprice;
    @Column(name = "idx")
    private Integer idx;
    @Size(max = 45)
    @Column(name = "logo1")
    private String logo1;
    @Size(max = 45)
    @Column(name = "logo2")
    private String logo2;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;
    

    public ItemMaster() {
    }

    public ItemMaster(String itemno, String itemdesc, String unit, String status) {
        this.itemno = itemno;
        this.itemdesc = itemdesc;
        this.unit = unit;
        this.status = status;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
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

    public String getItemproperty() {
        return itemproperty;
    }

    public void setItemproperty(String itemproperty) {
        this.itemproperty = itemproperty;
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

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getStdcost() {
        return stdcost;
    }

    public void setStdcost(BigDecimal stdcost) {
        this.stdcost = stdcost;
    }

    public BigDecimal getPurprice() {
        return purprice;
    }

    public void setPurprice(BigDecimal purprice) {
        this.purprice = purprice;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLogo1() {
        return logo1;
    }

    public void setLogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
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
        hash += (itemno != null ? itemno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.itemno == null && other.itemno != null) || (this.itemno != null && !this.itemno.equals(other.itemno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemMaster[ itemno=" + itemno + " ]";
    }
    
}

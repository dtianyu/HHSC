/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
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
@Table(name = "itemmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMaster.getRowCount", query = "SELECT COUNT(i) FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findAll", query = "SELECT i FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findById", query = "SELECT i FROM ItemMaster i WHERE i.id = :id"),
    @NamedQuery(name = "ItemMaster.findByItemno", query = "SELECT i FROM ItemMaster i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemMaster.findByCategoryId", query = "SELECT i FROM ItemMaster i WHERE i.categoryid.id = :categoryid")})
public class ItemMaster extends BaseEntityWithOperate {

    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemCategory categoryid;
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
    @Size(max = 10)
    @Column(name = "simplecode")
    private String simplecode;
    @Size(max = 2)
    @Column(name = "proptype")
    private String proptype;
    @Size(max = 2)
    @Column(name = "maketype")
    private String maketype;
    @Size(max = 8)
    @Column(name = "pptype")
    private String pptype;
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
    @Column(name = "opendate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opendate;
    @Size(max = 45)
    @Column(name = "opensize")
    private String opensize;
    @Column(name = "sampleperiod")
    private Integer sampleperiod;
    @Column(name = "deliveryperiod")
    private Integer deliveryperiod;
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
    @Size(min = 1, max = 2)
    @Column(name = "unittype")
    private String unittype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    private String unit;
    @Size(max = 10)
    @Column(name = "unit2")
    private String unit2;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitexch")
    private BigDecimal unitexch;
    @Size(max = 10)
    @Column(name = "unitsales")
    private String unitsales;
    @Size(max = 10)
    @Column(name = "unitpurchase")
    private String unitpurchase;
    @Column(name = "stdcost")
    private BigDecimal stdcost;
    @Column(name = "purprice")
    private BigDecimal purprice;
    @Column(name = "purmin")
    private BigDecimal purmin;
    @Column(name = "purmax")
    private BigDecimal purmax;
    @Column(name = "invmin")
    private BigDecimal invmin;
    @Column(name = "invmax")
    private BigDecimal invmax;
    @Size(max = 45)
    @Column(name = "barcode")
    private String barcode;
    @Size(max = 100)
    @Column(name = "img1")
    private String img1;
    @Size(max = 100)
    @Column(name = "img2")
    private String img2;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public ItemMaster() {
        this.invmax = BigDecimal.ZERO;
        this.invmin = BigDecimal.ZERO;
        this.purmax = BigDecimal.ZERO;
        this.purmin = BigDecimal.ZERO;
    }

    public ItemCategory getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(ItemCategory categoryid) {
        this.categoryid = categoryid;
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

    public String getSimplecode() {
        return simplecode;
    }

    public void setSimplecode(String simplecode) {
        this.simplecode = simplecode;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public String getMaketype() {
        return maketype;
    }

    public void setMaketype(String maketype) {
        this.maketype = maketype;
    }

    public String getPptype() {
        return pptype;
    }

    public void setPptype(String pptype) {
        this.pptype = pptype;
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

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public String getOpensize() {
        return opensize;
    }

    public void setOpensize(String opensize) {
        this.opensize = opensize;
    }

    public Integer getSampleperiod() {
        return sampleperiod;
    }

    public void setSampleperiod(Integer sampleperiod) {
        this.sampleperiod = sampleperiod;
    }

    public Integer getDeliveryperiod() {
        return deliveryperiod;
    }

    public void setDeliveryperiod(Integer deliveryperiod) {
        this.deliveryperiod = deliveryperiod;
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

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public BigDecimal getUnitexch() {
        return unitexch;
    }

    public void setUnitexch(BigDecimal unitexch) {
        this.unitexch = unitexch;
    }

    public String getUnitsales() {
        return unitsales;
    }

    public void setUnitsales(String unitsales) {
        this.unitsales = unitsales;
    }

    public String getUnitpurchase() {
        return unitpurchase;
    }

    public void setUnitpurchase(String unitpurchase) {
        this.unitpurchase = unitpurchase;
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

    public BigDecimal getPurmin() {
        return purmin;
    }

    public void setPurmin(BigDecimal purmin) {
        this.purmin = purmin;
    }

    public BigDecimal getPurmax() {
        return purmax;
    }

    public void setPurmax(BigDecimal purmax) {
        this.purmax = purmax;
    }

    public BigDecimal getInvmin() {
        return invmin;
    }

    public void setInvmin(BigDecimal invmin) {
        this.invmin = invmin;
    }

    public BigDecimal getInvmax() {
        return invmax;
    }

    public void setInvmax(BigDecimal invmax) {
        this.invmax = invmax;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
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
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (!this.itemno.equals(other.itemno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemMaster[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseDetailEntity;
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
@Table(name = "factorystoragedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryStorageDetail.findAll", query = "SELECT f FROM FactoryStorageDetail f"),
    @NamedQuery(name = "FactoryStorageDetail.findById", query = "SELECT f FROM FactoryStorageDetail f WHERE f.id = :id"),
    @NamedQuery(name = "FactoryStorageDetail.findByPId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.pid = :pid"),
    @NamedQuery(name = "FactoryStorageDetail.findByPformId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.pformid = :pformid"),
    @NamedQuery(name = "FactoryStorageDetail.findBySId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.sid = :sid"),
    @NamedQuery(name = "FactoryStorageDetail.findBySformId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.sformid = :sformid"),
    @NamedQuery(name = "FactoryStorageDetail.findByDesignId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.designid = :designid"),
    @NamedQuery(name = "FactoryStorageDetail.findByItemno", query = "SELECT f FROM FactoryStorageDetail f WHERE f.itemno = :itemno"),
    @NamedQuery(name = "FactoryStorageDetail.findByColorId", query = "SELECT f FROM FactoryStorageDetail f WHERE f.colorid = :colorid"),
    @NamedQuery(name = "FactoryStorageDetail.findBySn", query = "SELECT f FROM FactoryStorageDetail f WHERE f.sn = :sn")})
public class FactoryStorageDetail extends BaseDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pformid")
    private String pformid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid")
    private int sid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sformid")
    private String sformid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sseq")
    private int sseq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "designid")
    private String designid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorid")
    private String colorid;
    @Size(max = 45)
    @Column(name = "sn")
    private String sn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "planqty")
    private BigDecimal planqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allowqty")
    private BigDecimal allowqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "goodqty")
    protected BigDecimal goodqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "defectqty")
    private BigDecimal defectqty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "badqty")
    private BigDecimal badqty;

    public FactoryStorageDetail() {
    }

    public String getPformid() {
        return pformid;
    }

    public void setPformid(String pformid) {
        this.pformid = pformid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSformid() {
        return sformid;
    }

    public void setSformid(String sformid) {
        this.sformid = sformid;
    }

    public int getSseq() {
        return sseq;
    }

    public void setSseq(int sseq) {
        this.sseq = sseq;
    }

    public String getDesignid() {
        return designid;
    }

    public void setDesignid(String designid) {
        this.designid = designid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
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
        if (!(object instanceof FactoryStorageDetail)) {
            return false;
        }
        FactoryStorageDetail other = (FactoryStorageDetail) object;
        if (this.sid != other.sid) {
            return false;
        }
        if (this.sid == other.sid && this.sseq != other.sseq) {
            return false;
        }
        if (this.sid == other.sid && this.sseq == other.sseq) {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.ejb.FactoryStorageDetail[ id=" + id + " ]";
    }

    /**
     * @return the planqty
     */
    public BigDecimal getPlanqty() {
        return planqty;
    }

    /**
     * @param planqty the planqty to set
     */
    public void setPlanqty(BigDecimal planqty) {
        this.planqty = planqty;
    }

    /**
     * @return the goodqty
     */
    public BigDecimal getGoodqty() {
        return goodqty;
    }

    /**
     * @param goodqty the goodqty to set
     */
    public void setGoodqty(BigDecimal goodqty) {
        this.goodqty = goodqty;
    }

    /**
     * @return the defectqty
     */
    public BigDecimal getDefectqty() {
        return defectqty;
    }

    /**
     * @param defectqty the defectqty to set
     */
    public void setDefectqty(BigDecimal defectqty) {
        this.defectqty = defectqty;
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
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
@Table(name = "itemprocess")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemProcess.getRowCount", query = "SELECT COUNT(i) FROM ItemProcess i")
    ,
    @NamedQuery(name = "ItemProcess.findAll", query = "SELECT i FROM ItemProcess i")
    ,
    @NamedQuery(name = "ItemProcess.findById", query = "SELECT i FROM ItemProcess i WHERE i.id = :id")
    ,
    @NamedQuery(name = "ItemProcess.findByItemno", query = "SELECT i FROM ItemProcess i WHERE i.itemno = :itemno ORDER BY i.id DESC")
    ,
    @NamedQuery(name = "ItemProcess.findByStatus", query = "SELECT i FROM ItemProcess i WHERE i.status = :status")})
public class ItemProcess extends SuperEntity {

    @Column(name = "frameid")
    private Integer frameid;

    @Size(max = 100)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Column(name = "designsets")
    private Integer designsets;
    @Size(max = 400)
    @Column(name = "fyreq")
    private String fyreq;
    @Size(max = 400)
    @Column(name = "hgreq")
    private String hgreq;
    @Size(max = 400)
    @Column(name = "zbreq")
    private String zbreq;
    @Size(max = 400)
    @Column(name = "psreq")
    private String psreq;
    @Size(max = 400)
    @Column(name = "yhreq")
    private String yhreq;
    @Size(max = 400)
    @Column(name = "zhreq")
    private String zhreq;
    @Size(max = 400)
    @Column(name = "sxreq")
    private String sxreq;
    @Size(max = 400)
    @Column(name = "dxreq")
    private String dxreq;
    @Size(max = 400)
    @Column(name = "ckreq")
    private String ckreq;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemmaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Column(name = "procid")
    private Integer procid;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ItemProcess() {
        this.procid = 0;
        this.frameid = 0;
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

    public Integer getProcid() {
        return procid;
    }

    public void setProcid(Integer procid) {
        this.procid = procid;
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
        if (!(object instanceof ItemProcess)) {
            return false;
        }
        ItemProcess other = (ItemProcess) object;
        if (Objects.equals(this.itemno, other.itemno)) {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemProcess[ id=" + id + " ]";
    }

    public String getFyreq() {
        return fyreq;
    }

    public void setFyreq(String fyreq) {
        this.fyreq = fyreq;
    }

    public String getHgreq() {
        return hgreq;
    }

    public void setHgreq(String hgreq) {
        this.hgreq = hgreq;
    }

    public String getZbreq() {
        return zbreq;
    }

    public void setZbreq(String zbreq) {
        this.zbreq = zbreq;
    }

    public String getPsreq() {
        return psreq;
    }

    public void setPsreq(String psreq) {
        this.psreq = psreq;
    }

    public String getYhreq() {
        return yhreq;
    }

    public void setYhreq(String yhreq) {
        this.yhreq = yhreq;
    }

    public String getZhreq() {
        return zhreq;
    }

    public void setZhreq(String zhreq) {
        this.zhreq = zhreq;
    }

    public String getSxreq() {
        return sxreq;
    }

    public void setSxreq(String sxreq) {
        this.sxreq = sxreq;
    }

    public String getDxreq() {
        return dxreq;
    }

    public void setDxreq(String dxreq) {
        this.dxreq = dxreq;
    }

    public String getCkreq() {
        return ckreq;
    }

    public void setCkreq(String ckreq) {
        this.ckreq = ckreq;
    }

    /**
     * @return the itemspec
     */
    public String getItemspec() {
        return itemspec;
    }

    /**
     * @param itemspec the itemspec to set
     */
    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    /**
     * @return the colorno
     */
    public String getColorno() {
        return colorno;
    }

    /**
     * @param colorno the colorno to set
     */
    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    /**
     * @return the designsets
     */
    public Integer getDesignsets() {
        return designsets;
    }

    /**
     * @param designsets the designsets to set
     */
    public void setDesignsets(Integer designsets) {
        this.designsets = designsets;
    }

    /**
     * @return the frameid
     */
    public Integer getFrameid() {
        return frameid;
    }

    /**
     * @param frameid the frameid to set
     */
    public void setFrameid(Integer frameid) {
        this.frameid = frameid;
    }

}

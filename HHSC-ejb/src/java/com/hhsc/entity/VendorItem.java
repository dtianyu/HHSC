/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseDetailEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "vendoritem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendorItem.findAll", query = "SELECT v FROM VendorItem v"),
    @NamedQuery(name = "VendorItem.findById", query = "SELECT v FROM VendorItem v WHERE v.id = :id"),
    @NamedQuery(name = "VendorItem.findByPId", query = "SELECT v FROM VendorItem v WHERE v.pid = :pid"),
    @NamedQuery(name = "VendorItem.findByVendorno", query = "SELECT v FROM VendorItem v WHERE v.vendorno = :vendorno"),
    @NamedQuery(name = "VendorItem.findBySeq", query = "SELECT v FROM VendorItem v WHERE v.seq = :seq"),
    @NamedQuery(name = "VendorItem.findByItemId", query = "SELECT v FROM VendorItem v WHERE v.itemid = :itemid"),
    @NamedQuery(name = "VendorItem.findByItemno", query = "SELECT v FROM VendorItem v WHERE v.itemno = :itemno"),
    @NamedQuery(name = "VendorItem.findByVendoritemno", query = "SELECT v FROM VendorItem v WHERE v.vendoritemno = :vendoritemno")})
public class VendorItem extends BaseDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "vendorno")
    private String vendorno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private int itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vendoritemno")
    private String vendoritemno;
    @Size(max = 45)
    @Column(name = "vendoritemdesc")
    private String vendoritemdesc;
    @Size(max = 45)
    @Column(name = "vendoritemspec")
    private String vendoritemspec;

    public VendorItem() {
    }

    public String getVendorno() {
        return vendorno;
    }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getVendoritemno() {
        return vendoritemno;
    }

    public void setVendoritemno(String vendoritemno) {
        this.vendoritemno = vendoritemno;
    }

    public String getVendoritemdesc() {
        return vendoritemdesc;
    }

    public void setVendoritemdesc(String vendoritemdesc) {
        this.vendoritemdesc = vendoritemdesc;
    }

    public String getVendoritemspec() {
        return vendoritemspec;
    }

    public void setVendoritemspec(String vendoritemspec) {
        this.vendoritemspec = vendoritemspec;
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
        if (!(object instanceof VendorItem)) {
            return false;
        }
        VendorItem other = (VendorItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.VendorItem[ id=" + id + " ]";
    }

}

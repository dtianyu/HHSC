/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.SuperDetailEntity;
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
@Table(name = "itembom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itembom.findAll", query = "SELECT i FROM Itembom i")
    , @NamedQuery(name = "Itembom.findById", query = "SELECT i FROM Itembom i WHERE i.id = :id")
    , @NamedQuery(name = "Itembom.findByPId", query = "SELECT i FROM Itembom i WHERE i.pid = :pid")
    , @NamedQuery(name = "Itembom.findByItemno", query = "SELECT i FROM Itembom i WHERE i.itemno = :itemno")})
public class Itembom extends SuperDetailEntity {

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster itemMaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;

    public Itembom() {
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
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
        if (!(object instanceof Itembom)) {
            return false;
        }
        Itembom other = (Itembom) object;
        if (this.id != null && other.id != null) {
            return Objects.equals(this.id, other.id);
        }
        if (Objects.equals(this.pid, other.pid)) {
            return Objects.equals(this.itemno, other.itemno);
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhds.entity.Itembom[ id=" + id + " ]";
    }

}

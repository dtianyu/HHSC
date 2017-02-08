/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperDetailEntity;
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
@Table(name = "itemmake")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMake.findAll", query = "SELECT i FROM ItemMake i")
    ,
    @NamedQuery(name = "ItemMake.findById", query = "SELECT i FROM ItemMake i WHERE i.id = :id")
    ,
    @NamedQuery(name = "ItemMake.findByPId", query = "SELECT i FROM ItemMake i WHERE i.pid = :pid")
    ,
    @NamedQuery(name = "ItemMake.findByItemno", query = "SELECT i FROM ItemMake i WHERE i.itemno = :itemno")})
public class ItemMake extends SuperDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "make")
    private String make;

    public ItemMake() {
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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
        if (!(object instanceof ItemMake)) {
            return false;
        }
        ItemMake other = (ItemMake) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (!this.itemno.equals(other.itemno)) {
            return false;
        }
        return (this.seq == other.seq);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemMake[ id=" + id + " ]";
    }

}

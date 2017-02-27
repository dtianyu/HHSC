/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
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
@Table(name = "itemcolor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemColor.getRowCount", query = "SELECT COUNT(i) FROM ItemColor i")
    ,@NamedQuery(name = "ItemColor.findAll", query = "SELECT i FROM ItemColor i")
    , @NamedQuery(name = "ItemColor.findById", query = "SELECT i FROM ItemColor i WHERE i.id = :id")
    , @NamedQuery(name = "ItemColor.findByPid", query = "SELECT i FROM ItemColor i WHERE i.pid = :pid")
    , @NamedQuery(name = "ItemColor.findByItemno", query = "SELECT i FROM ItemColor i WHERE i.itemno = :itemno")
    , @NamedQuery(name = "ItemColor.findByColorno", query = "SELECT i FROM ItemColor i WHERE i.colorno = :colorno")
    , @NamedQuery(name = "ItemColor.findByItemnoAndColorno", query = "SELECT i FROM ItemColor i WHERE i.itemno = :itemno AND i.colorno = :colorno AND  i.customeritemno = :customeritemno AND i.customercolorno = :customercolorno ")
    , @NamedQuery(name = "ItemColor.findByStatus", query = "SELECT i FROM ItemColor i WHERE i.status = :status")})
public class ItemColor extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "pid")
    private int pid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 45)
    @Column(name = "customeritemno")
    protected String customeritemno;
    @Size(max = 100)
    @Column(name = "customercolorno")
    protected String customercolorno;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public ItemColor() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
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
        if (!(object instanceof ItemColor)) {
            return false;
        }
        ItemColor other = (ItemColor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemColor[ id=" + id + " ]";
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
     * @return the customercolorno
     */
    public String getCustomercolorno() {
        return customercolorno;
    }

    /**
     * @param customercolorno the customercolorno to set
     */
    public void setCustomercolorno(String customercolorno) {
        this.customercolorno = customercolorno;
    }

}

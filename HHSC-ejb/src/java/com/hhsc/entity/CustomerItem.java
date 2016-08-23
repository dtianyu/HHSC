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
@Table(name = "customeritem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerItem.getRowCount", query = "SELECT COUNT(c) FROM CustomerItem c"),
    @NamedQuery(name = "CustomerItem.findAll", query = "SELECT c FROM CustomerItem c"),
    @NamedQuery(name = "CustomerItem.findById", query = "SELECT c FROM CustomerItem c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerItem.findByPId", query = "SELECT c FROM CustomerItem c WHERE c.pid = :pid"),
    @NamedQuery(name = "CustomerItem.findByCustomerno", query = "SELECT c FROM CustomerItem c WHERE c.customer.customerno = :customerno"),
    @NamedQuery(name = "CustomerItem.findByItemId", query = "SELECT c FROM CustomerItem c WHERE c.itemid = :itemid"),
    @NamedQuery(name = "CustomerItem.findByItemno", query = "SELECT c FROM CustomerItem c WHERE c.itemno = :itemno"),
    @NamedQuery(name = "CustomerItem.findByItemnoAndCustomerno", query = "SELECT c FROM CustomerItem c WHERE c.itemno = :itemno AND c.customer.customerno=:customerno"),
    @NamedQuery(name = "CustomerItem.findByCustomeritemno", query = "SELECT c FROM CustomerItem c WHERE c.customeritemno = :customeritemno")})
public class CustomerItem extends SuperDetailEntity {

    @JoinColumn(name = "customerno", referencedColumnName = "customerno")
    @ManyToOne(optional = false)
    private Customer customer;
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
    @Column(name = "customeritemno")
    private String customeritemno;
    @Size(max = 45)
    @Column(name = "customeritemdesc")
    private String customeritemdesc;
    @Size(max = 45)
    @Column(name = "customeritemspec")
    private String customeritemspec;

    public CustomerItem() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
    }

    public String getCustomeritemdesc() {
        return customeritemdesc;
    }

    public void setCustomeritemdesc(String customeritemdesc) {
        this.customeritemdesc = customeritemdesc;
    }

    public String getCustomeritemspec() {
        return customeritemspec;
    }

    public void setCustomeritemspec(String customeritemspec) {
        this.customeritemspec = customeritemspec;
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
        if (!(object instanceof CustomerItem)) {
            return false;
        }
        CustomerItem other = (CustomerItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (!this.customer.equals(other.customer)) {
            return false;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.CustomerItem[ id=" + id + " ]";
    }

}

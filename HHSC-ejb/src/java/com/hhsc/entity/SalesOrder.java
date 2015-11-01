/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "salesorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesOrder.getRowCount", query = "SELECT COUNT(s) FROM SalesOrder s"),
    @NamedQuery(name = "SalesOrder.findAll", query = "SELECT s FROM SalesOrder s ORDER BY s.status,s.orderid DESC"),
    @NamedQuery(name = "SalesOrder.findById", query = "SELECT s FROM SalesOrder s WHERE s.id = :id"),
    @NamedQuery(name = "SalesOrder.findByOrderId", query = "SELECT s FROM SalesOrder s WHERE s.orderid = :orderid"),
    @NamedQuery(name = "SalesOrder.findByOrderdate", query = "SELECT s FROM SalesOrder s WHERE s.orderdate = :orderdate"),
    @NamedQuery(name = "SalesOrder.findByCustomerId", query = "SELECT s FROM SalesOrder s WHERE s.customerid = :customerid"),
    @NamedQuery(name = "SalesOrder.findByOrdertype", query = "SELECT s FROM SalesOrder s WHERE s.ordertype = :ordertype"),
    @NamedQuery(name = "SalesOrder.findByStatus", query = "SELECT s FROM SalesOrder s WHERE s.status = :status")})
public class SalesOrder extends BaseEntityWithOperate {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "orderid")
    private String orderid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderdate")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customerid")
    private int customerid;
    @Size(max = 45)
    @Column(name = "ordertype")
    private String ordertype;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public SalesOrder() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
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
        if (!(object instanceof SalesOrder)) {
            return false;
        }
        SalesOrder other = (SalesOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrder[ id=" + id + " ]";
    }

}

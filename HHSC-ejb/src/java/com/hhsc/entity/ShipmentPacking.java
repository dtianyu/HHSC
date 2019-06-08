/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "shipmentpacking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentPacking.findAll", query = "SELECT s FROM ShipmentPacking s")
    , @NamedQuery(name = "ShipmentPacking.findById", query = "SELECT s FROM ShipmentPacking s WHERE s.id = :id")
    , @NamedQuery(name = "ShipmentPacking.findByFormid", query = "SELECT s FROM ShipmentPacking s WHERE s.formid = :formid")
    , @NamedQuery(name = "ShipmentPacking.findByFormdate", query = "SELECT s FROM ShipmentPacking s WHERE s.formdate = :formdate")
    , @NamedQuery(name = "ShipmentPacking.findByFormtype", query = "SELECT s FROM ShipmentPacking s WHERE s.formtype = :formtype")
    , @NamedQuery(name = "ShipmentPacking.findByFormkind", query = "SELECT s FROM ShipmentPacking s WHERE s.formkind = :formkind")
    , @NamedQuery(name = "ShipmentPacking.findByCustomerno", query = "SELECT s FROM ShipmentPacking s WHERE s.customerno = :customerno")
    , @NamedQuery(name = "ShipmentPacking.findByCustomer", query = "SELECT s FROM ShipmentPacking s WHERE s.customer = :customer")
    , @NamedQuery(name = "ShipmentPacking.findByRemark", query = "SELECT s FROM ShipmentPacking s WHERE s.remark = :remark")
    , @NamedQuery(name = "ShipmentPacking.findByStatus", query = "SELECT s FROM ShipmentPacking s WHERE s.status = :status")})
public class ShipmentPacking extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Size(max = 20)
    @Column(name = "customerno")
    private String customerno;
    @Size(max = 45)
    @Column(name = "customer")
    private String customer;
    @Size(max = 400)
    @Column(name = "remark")
    private String remark;

    public ShipmentPacking() {
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getFormkind() {
        return formkind;
    }

    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    public String getCustomerno() {
        return customerno;
    }

    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
        if (!(object instanceof ShipmentPacking)) {
            return false;
        }
        ShipmentPacking other = (ShipmentPacking) object;
        if (this.id != null && other.id != null) {
            return Objects.equals(this.id, other.id);
        }
        return this.formid == other.formid;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ShipmentPacking[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

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
@Table(name = "paykind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PayKind.findAll", query = "SELECT p FROM PayKind p")
    , @NamedQuery(name = "PayKind.findById", query = "SELECT p FROM PayKind p WHERE p.id = :id")
    , @NamedQuery(name = "PayKind.findByPaykind", query = "SELECT p FROM PayKind p WHERE p.paykind = :paykind")
    , @NamedQuery(name = "PayKind.findByName", query = "SELECT p FROM PayKind p WHERE p.name = :name")
    , @NamedQuery(name = "PayKind.findByStatus", query = "SELECT p FROM PayKind p WHERE p.status = :status")})
public class PayKind extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "paykind")
    private String paykind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public PayKind() {
    }

    public String getPaykind() {
        return paykind;
    }

    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof PayKind)) {
            return false;
        }
        PayKind other = (PayKind) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.PayKind[ id=" + id + " ]";
    }

}

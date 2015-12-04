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
@Table(name = "factorystorage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryStorage.findAll", query = "SELECT f FROM FactoryStorage f"),
    @NamedQuery(name = "FactoryStorage.findById", query = "SELECT f FROM FactoryStorage f WHERE f.id = :id"),
    @NamedQuery(name = "FactoryStorage.findByFormid", query = "SELECT f FROM FactoryStorage f WHERE f.formid = :formid"),
    @NamedQuery(name = "FactoryStorage.findByFormdate", query = "SELECT f FROM FactoryStorage f WHERE f.formdate = :formdate"),
    @NamedQuery(name = "FactoryStorage.findByDeptid", query = "SELECT f FROM FactoryStorage f WHERE f.deptid = :deptid"),
    @NamedQuery(name = "FactoryStorage.findByStatus", query = "SELECT f FROM FactoryStorage f WHERE f.status = :status")})
public class FactoryStorage extends BaseEntityWithOperate {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "formid")
    private String formid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "formdate")
    @Temporal(TemporalType.DATE)
    private Date formdate;
    @Column(name = "deptid")
    private Integer deptid;

    public FactoryStorage() {
    }

    public FactoryStorage(Integer id) {
        this.id = id;
    }

    public FactoryStorage(Integer id, String formid, Date formdate, String status) {
        this.id = id;
        this.formid = formid;
        this.formdate = formdate;
        this.status = status;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public Date getFormdate() {
        return formdate;
    }

    public void setFormdate(Date formdate) {
        this.formdate = formdate;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
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
        if (!(object instanceof FactoryStorage)) {
            return false;
        }
        FactoryStorage other = (FactoryStorage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.ejb.FactoryStorage[ id=" + id + " ]";
    }

}

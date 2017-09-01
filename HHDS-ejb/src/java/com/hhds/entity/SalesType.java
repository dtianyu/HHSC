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
@Table(name = "salestype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesType.getRowCount", query = "SELECT COUNT(s) FROM SalesType s"),
    @NamedQuery(name = "SalesType.findAll", query = "SELECT s FROM SalesType s"),
    @NamedQuery(name = "SalesType.findById", query = "SELECT s FROM SalesType s WHERE s.id = :id"),
    @NamedQuery(name = "SalesType.findByType", query = "SELECT s FROM SalesType s WHERE s.type = :type"),
    @NamedQuery(name = "SalesType.findByName", query = "SELECT s FROM SalesType s WHERE s.name = :name"),
    @NamedQuery(name = "SalesType.findByProcess", query = "SELECT s FROM SalesType s WHERE s.process = :process"),
    @NamedQuery(name = "SalesType.findByStatus", query = "SELECT s FROM SalesType s WHERE s.status = :status")})
public class SalesType extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "process")
    private boolean process;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public SalesType() {
    }

    public SalesType(Integer id) {
        this.id = id;
    }

    public SalesType(Integer id, String type, String name, boolean process, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.process = process;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getProcess() {
        return process;
    }

    public void setProcess(boolean process) {
        this.process = process;
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
        if (!(object instanceof SalesType)) {
            return false;
        }
        SalesType other = (SalesType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesType[ id=" + id + " ]";
    }

}

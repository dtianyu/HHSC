/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
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
@Table(name = "warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.getRowCount", query = "SELECT COUNT(w) FROM Warehouse w"),
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w"),
    @NamedQuery(name = "Warehouse.findById", query = "SELECT w FROM Warehouse w WHERE w.id = :id"),
    @NamedQuery(name = "Warehouse.findByWarehouseId", query = "SELECT w FROM Warehouse w WHERE w.warehouseid = :warehouseid"),
    @NamedQuery(name = "Warehouse.findByName", query = "SELECT w FROM Warehouse w WHERE w.name = :name"),
    @NamedQuery(name = "Warehouse.findByHascost", query = "SELECT w FROM Warehouse w WHERE w.hascost = :hascost"),
    @NamedQuery(name = "Warehouse.findBySysId", query = "SELECT w FROM Warehouse w WHERE w.sysid = :sysid"),
    @NamedQuery(name = "Warehouse.findByAreaId", query = "SELECT w FROM Warehouse w WHERE w.areaid = :areaid"),
    @NamedQuery(name = "Warehouse.findByManagerId", query = "SELECT w FROM Warehouse w WHERE w.managerid = :managerid"),
    @NamedQuery(name = "Warehouse.findByStatus", query = "SELECT w FROM Warehouse w WHERE w.status = :status")})
public class Warehouse extends BaseEntityWithOperate {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "warehouseid")
    private String warehouseid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hascost")
    private boolean hascost;
    @Column(name = "sysid")
    private Integer sysid;
    @Column(name = "areaid")
    private Integer areaid;
    @Column(name = "managerid")
    private Integer managerid;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public Warehouse() {
        this.hascost = false;
    }

    public String getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHascost() {
        return hascost;
    }

    public void setHascost(boolean hascost) {
        this.hascost = hascost;
    }

    public Integer getSysid() {
        return sysid;
    }

    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.Warehouse[ id=" + id + " ]";
    }

}

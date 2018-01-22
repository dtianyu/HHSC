/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.SuperEntity;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.getRowCount", query = "SELECT COUNT(a) FROM Area a")
    ,
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    ,
    @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id")
    ,
    @NamedQuery(name = "Area.findByLvl", query = "SELECT a FROM Area a WHERE a.lvl = :lvl")
    ,
    @NamedQuery(name = "Area.findByName", query = "SELECT a FROM Area a WHERE a.name = :name")
    ,
    @NamedQuery(name = "Area.findByStatus", query = "SELECT a FROM Area a WHERE a.status = :status")})
public class Area extends SuperEntity {

    @Column(name = "lvl")
    private Integer lvl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy = "pid")
    private List<Area> areaList;
    @JoinColumn(name = "pid", referencedColumnName = "id")
    @ManyToOne
    private Area pid;

    public Area() {
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
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

    @XmlTransient
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public Area getPid() {
        return pid;
    }

    public void setPid(Area pid) {
        this.pid = pid;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.Area[ id=" + id + " ]";
    }

}

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
@Table(name = "itemcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemCategory.getRowCount", query = "SELECT COUNT(i) FROM ItemCategory i"),
    @NamedQuery(name = "ItemCategory.findAll", query = "SELECT i FROM ItemCategory i ORDER BY i.status,i.id DESC "),
    @NamedQuery(name = "ItemCategory.findById", query = "SELECT i FROM ItemCategory i WHERE i.id = :id"),
    @NamedQuery(name = "ItemCategory.findByCategory", query = "SELECT i FROM ItemCategory i WHERE i.category = :category"),
    @NamedQuery(name = "ItemCategory.findByName", query = "SELECT i FROM ItemCategory i WHERE i.name = :name"),
    @NamedQuery(name = "ItemCategory.findByStatus", query = "SELECT i FROM ItemCategory i WHERE i.status = :status")})
public class ItemCategory extends BaseEntityWithOperate {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    protected String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "proptype")
    private String proptype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "maketype")
    private String maketype;
    @Size(max = 8)
    @Column(name = "pptype")
    private String pptype;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public ItemCategory() {
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
        if (!(object instanceof ItemCategory)) {
            return false;
        }
        ItemCategory other = (ItemCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (!this.category.equals(other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.ejb.ItemCategory[ id=" + id + " ]";
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public String getMaketype() {
        return maketype;
    }

    public void setMaketype(String maketype) {
        this.maketype = maketype;
    }

    public String getPptype() {
        return pptype;
    }

    public void setPptype(String pptype) {
        this.pptype = pptype;
    }

}

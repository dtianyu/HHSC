/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "itemdesign")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemDesign.getRowCount", query = "SELECT COUNT(i) FROM ItemDesign i"),
    @NamedQuery(name = "ItemDesign.findAll", query = "SELECT i FROM ItemDesign i ORDER BY i.status,i.id DESC "),
    @NamedQuery(name = "ItemDesign.findById", query = "SELECT i FROM ItemDesign i WHERE i.id = :id"),
    @NamedQuery(name = "ItemDesign.findByDesignId", query = "SELECT i FROM ItemDesign i WHERE i.designid = :designid"),
    @NamedQuery(name = "ItemDesign.findByStatus", query = "SELECT i FROM ItemDesign i WHERE i.status = :status")})
public class ItemDesign extends BaseEntityWithOperate {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "designid")
    private String designid;
    @Size(max = 100)
    @Column(name = "filename")
    private String filename;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public ItemDesign() {
    }

    public String getDesignid() {
        return designid;
    }

    public void setDesignid(String designid) {
        this.designid = designid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        if (!(object instanceof ItemDesign)) {
            return false;
        }
        ItemDesign other = (ItemDesign) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ItemDesign[ id=" + id + " ]";
    }

}

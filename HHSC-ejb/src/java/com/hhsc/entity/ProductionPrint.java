/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormDetailEntity;
import java.math.BigDecimal;
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
@Table(name = "productionprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionPrint.findAll", query = "SELECT p FROM ProductionPrint p")
    ,
    @NamedQuery(name = "ProductionPrint.findById", query = "SELECT p FROM ProductionPrint p WHERE p.id = :id")
    ,
    @NamedQuery(name = "ProductionPrint.findByPId", query = "SELECT p FROM ProductionPrint p WHERE p.pid = :pid")})
public class ProductionPrint extends FormDetailEntity {

    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;

    public ProductionPrint() {
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
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
        if (!(object instanceof ProductionPrint)) {
            return false;
        }
        ProductionPrint other = (ProductionPrint) object;
        if ((this.dept == null && other.dept != null) || (this.dept != null && !this.dept.equals(other.dept))) {
            return false;
        }
        return this.seq == other.seq;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProductionPrint[ id=" + id + " ]";
    }

}

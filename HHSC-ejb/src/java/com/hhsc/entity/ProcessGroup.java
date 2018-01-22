/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.SuperEntity;
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
@Table(name = "processgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessGroup.getRowCount", query = "SELECT COUNT(p) FROM ProcessGroup p")
    ,
    @NamedQuery(name = "ProcessGroup.findAll", query = "SELECT p FROM ProcessGroup p")
    ,
    @NamedQuery(name = "ProcessGroup.findById", query = "SELECT p FROM ProcessGroup p WHERE p.id = :id")
    ,
    @NamedQuery(name = "ProcessGroup.findByGroupno", query = "SELECT p FROM ProcessGroup p WHERE p.groupno = :groupno")
    ,
    @NamedQuery(name = "ProcessGroup.findByStatus", query = "SELECT p FROM ProcessGroup p WHERE p.status = :status")})
public class ProcessGroup extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "groupno")
    private String groupno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Department dept;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ProcessGroup() {
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno;
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
        if (!(object instanceof ProcessGroup)) {
            return false;
        }
        ProcessGroup other = (ProcessGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProcessGroup[ id=" + id + " ]";
    }

    /**
     * @return the dept
     */
    public Department getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(Department dept) {
        this.dept = dept;
    }

}

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
@Table(name = "process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Process.getRowCount", query = "SELECT COUNT(p) FROM Process p"),
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p"),
    @NamedQuery(name = "Process.findById", query = "SELECT p FROM Process p WHERE p.id = :id"),
    @NamedQuery(name = "Process.findByProcessno", query = "SELECT p FROM Process p WHERE p.processno = :processno"),
    @NamedQuery(name = "Process.findByProcess", query = "SELECT p FROM Process p WHERE p.process = :process"),
    @NamedQuery(name = "Process.findByStatus", query = "SELECT p FROM Process p WHERE p.status = :status")})
public class Process extends SuperEntity {

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "processno")
    private String processno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "process")
    private String process;
    @Column(name = "sortid")
    private Integer sortid;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Department dept;
    @Size(max = 45)
    @Column(name = "leader")
    private String leader;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public Process() {
    }

    public Process(Integer id) {
        this.id = id;
    }

    public Process(Integer id, String processno, String process, String status) {
        this.id = id;
        this.processno = processno;
        this.process = process;
        this.status = status;
    }

    public String getProcessno() {
        return processno;
    }

    public void setProcessno(String processno) {
        this.processno = processno;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
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
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.Process[ id=" + id + " ]";
    }

    /**
     * @return the sortid
     */
    public Integer getSortid() {
        return sortid;
    }

    /**
     * @param sortid the sortid to set
     */
    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "accountreceipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountReceipt.getRowCount", query = "SELECT COUNT(a) FROM AccountReceipt a"),
    @NamedQuery(name = "AccountReceipt.findAll", query = "SELECT a FROM AccountReceipt a"),
    @NamedQuery(name = "AccountReceipt.findById", query = "SELECT a FROM AccountReceipt a WHERE a.id = :id"),
    @NamedQuery(name = "AccountReceipt.findByFormid", query = "SELECT a FROM AccountReceipt a WHERE a.formid = :formid"),
    @NamedQuery(name = "AccountReceipt.findByFormdate", query = "SELECT a FROM AccountReceipt a WHERE a.formdate = :formdate"),
    @NamedQuery(name = "AccountReceipt.findByFormtype", query = "SELECT a FROM AccountReceipt a WHERE a.formtype = :formtype"),
    @NamedQuery(name = "AccountReceipt.findByFormkind", query = "SELECT a FROM AccountReceipt a WHERE a.formkind = :formkind"),
    @NamedQuery(name = "AccountReceipt.findByDeptid", query = "SELECT a FROM AccountReceipt a WHERE a.dept.id = :deptid"),
    @NamedQuery(name = "AccountReceipt.findByStatus", query = "SELECT a FROM AccountReceipt a WHERE a.status = :status")})
public class AccountReceipt extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department dept;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser sysuser;
    @Size(max = 100)
    @Column(name = "summary")
    private String summary;
    @Column(name = "recamts")
    private BigDecimal recamts;
    @Column(name = "recamt")
    private BigDecimal recamt;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public AccountReceipt() {
        this.recamts = BigDecimal.ZERO;
        this.recamt = BigDecimal.ZERO;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getFormkind() {
        return formkind;
    }

    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getSysuser() {
        return sysuser;
    }

    public void setSysuser(SystemUser sysuser) {
        this.sysuser = sysuser;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        if (!(object instanceof AccountReceipt)) {
            return false;
        }
        AccountReceipt other = (AccountReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.AccountReceipt[ id=" + id + " ]";
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the recamts
     */
    public BigDecimal getRecamts() {
        return recamts;
    }

    /**
     * @param recamts the recamts to set
     */
    public void setRecamts(BigDecimal recamts) {
        this.recamts = recamts;
    }

    /**
     * @return the recamt
     */
    public BigDecimal getRecamt() {
        return recamt;
    }

    /**
     * @param recamt the recamt to set
     */
    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
    }

}

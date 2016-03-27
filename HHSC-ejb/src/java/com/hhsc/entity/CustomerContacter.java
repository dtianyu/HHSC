/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseDetailEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "customercontacter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerContacter.getRowCount", query = "SELECT COUNT(c) FROM CustomerContacter c"),
    @NamedQuery(name = "CustomerContacter.findAll", query = "SELECT c FROM CustomerContacter c"),
    @NamedQuery(name = "CustomerContacter.findById", query = "SELECT c FROM CustomerContacter c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerContacter.findByPId", query = "SELECT c FROM CustomerContacter c WHERE c.pid = :pid")})
public class CustomerContacter extends BaseDetailEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 45)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "fax")
    private String fax;
    @Size(max = 45)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 45)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "major")
    private boolean major;

    public CustomerContacter() {
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        if (!(object instanceof CustomerContacter)) {
            return false;
        }
        CustomerContacter other = (CustomerContacter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return (this.pid == other.pid && this.seq == other.seq);
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.CustomerContacter[ id=" + id + " ]";
    }

    public boolean getMajor() {
        return major;
    }

    public void setMajor(boolean major) {
        this.major = major;
    }

}

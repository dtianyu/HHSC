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
import javax.persistence.Lob;
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
@Table(name = "salescontract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesContract.findAll", query = "SELECT s FROM SalesContract s")
    , @NamedQuery(name = "SalesContract.findById", query = "SELECT s FROM SalesContract s WHERE s.id = :id")
    , @NamedQuery(name = "SalesContract.findByName", query = "SELECT s FROM SalesContract s WHERE s.name = :name")
    , @NamedQuery(name = "SalesContract.findByStatus", query = "SELECT s FROM SalesContract s WHERE s.status = :status")})
public class SalesContract extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "params")
    private int params;
    @Size(max = 45)
    @Column(name = "param1")
    private String param1;
    @Size(max = 45)
    @Column(name = "param2")
    private String param2;
    @Size(max = 45)
    @Column(name = "param3")
    private String param3;
    @Size(max = 45)
    @Column(name = "param4")
    private String param4;
    @Size(max = 45)
    @Column(name = "param5")
    private String param5;
    @Size(max = 45)
    @Column(name = "param6")
    private String param6;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public SalesContract() {
        this.params = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParams() {
        return params;
    }

    public void setParams(int params) {
        this.params = params;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesContract)) {
            return false;
        }
        SalesContract other = (SalesContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesContract[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.hhsc.entity.Customer;
import com.hhsc.entity.ItemProcess;
import com.lightshell.comm.FormEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "itemframe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemFrame.getRowCount", query = "SELECT COUNT(i) FROM ItemFrame i")
    ,@NamedQuery(name = "ItemFrame.findAll", query = "SELECT i FROM ItemFrame i")
    , @NamedQuery(name = "ItemFrame.findById", query = "SELECT i FROM ItemFrame i WHERE i.id = :id")
    , @NamedQuery(name = "ItemFrame.findByFormid", query = "SELECT i FROM ItemFrame i WHERE i.formid = :formid")
    , @NamedQuery(name = "ItemFrame.findByItemno", query = "SELECT i FROM ItemFrame i WHERE i.itemno = :itemno")
    , @NamedQuery(name = "ItemFrame.findByStatus", query = "SELECT i FROM ItemFrame i WHERE i.status = :status")})
public class ItemFrame extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;

    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Customer customer;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemProcess itemProcess;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 20)
    @Column(name = "colorno")
    private String colorno;
    @Size(max = 100)
    @Column(name = "designspec")
    private String designspec;
    @Column(name = "designsets")
    private Integer designsets;
    @Column(name = "losssets")
    private Integer losssets;

    @Size(max = 100)
    @Column(name = "framespec")
    private String framespec;
    @Size(max = 45)
    @Column(name = "framesize")
    private String framesize;

    @Column(name = "lastdate")
    @Temporal(TemporalType.DATE)
    private Date lastdate;
    @Column(name = "removedate")
    @Temporal(TemporalType.DATE)
    private Date removedate;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ItemFrame() {
        this.designsets = 0;
        this.losssets = 0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ItemProcess getItemProcess() {
        return itemProcess;
    }

    public void setItemProcess(ItemProcess itemProcess) {
        this.itemProcess = itemProcess;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getColorno() {
        return colorno;
    }

    public void setColorno(String colorno) {
        this.colorno = colorno;
    }

    public String getDesignspec() {
        return designspec;
    }

    public void setDesignspec(String designspec) {
        this.designspec = designspec;
    }

    public Integer getDesignsets() {
        return designsets;
    }

    public void setDesignsets(Integer designsets) {
        this.designsets = designsets;
    }

    public String getFramespec() {
        return framespec;
    }

    public void setFramespec(String framespec) {
        this.framespec = framespec;
    }

    public Date getRemovedate() {
        return removedate;
    }

    public void setRemovedate(Date removedate) {
        this.removedate = removedate;
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
        if (!(object instanceof ItemFrame)) {
            return false;
        }
        ItemFrame other = (ItemFrame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.comm.ItemFrame[ id=" + id + " ]";
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public Integer getLosssets() {
        return losssets;
    }

    public void setLosssets(Integer losssets) {
        this.losssets = losssets;
    }

    public String getFramesize() {
        return framesize;
    }

    public void setFramesize(String framesize) {
        this.framesize = framesize;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSrcapi() {
        return srcapi;
    }

    public void setSrcapi(String srcapi) {
        this.srcapi = srcapi;
    }

    public String getSrcformid() {
        return srcformid;
    }

    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    public Integer getSrcseq() {
        return srcseq;
    }

    public void setSrcseq(Integer srcseq) {
        this.srcseq = srcseq;
    }

}

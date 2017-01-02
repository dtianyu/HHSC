/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

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
@Table(name = "productionorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductionOrder.getRowCount", query = "SELECT COUNT(p) FROM ProductionOrder p"),
    @NamedQuery(name = "ProductionOrder.findAll", query = "SELECT p FROM ProductionOrder p"),
    @NamedQuery(name = "ProductionOrder.findById", query = "SELECT p FROM ProductionOrder p WHERE p.id = :id"),
    @NamedQuery(name = "ProductionOrder.findByFormid", query = "SELECT p FROM ProductionOrder p WHERE p.formid = :formid"),
    @NamedQuery(name = "ProductionOrder.findByStatus", query = "SELECT p FROM ProductionOrder p WHERE p.status = :status")})
public class ProductionOrder extends FormEntity {

    @Column(name = "itemprocessid")
    private Integer itemprocessid;

    @JoinColumn(name = "formtype", referencedColumnName = "type")
    @ManyToOne(optional = false)
    private SalesType formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Size(max = 20)
    @Column(name = "customerno")
    private String customerno;
    @Size(max = 20)
    @Column(name = "customer")
    private String customer;
    @Size(max = 20)
    @Column(name = "salesman")
    private String salesman;

    @JoinColumn(name = "designid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemMaster design;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "designno")
    private String designno;
    @Size(max = 100)
    @Column(name = "designimg")
    private String designimg;
    @Size(max = 100)
    @Column(name = "designspec")
    private String designspec;

    @Size(max = 20)
    @Column(name = "qty")
    private String qty;
    @Column(name = "printdate")
    @Temporal(TemporalType.DATE)
    private Date printdate;
    @Column(name = "shipdate")
    @Temporal(TemporalType.DATE)
    private Date shipdate;
    @Column(name = "issdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issdate;
    @Column(name = "findate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date findate;
    @Size(max = 200)
    @Column(name = "salesremark")
    private String salesremark;
    @Size(max = 2)
    @Column(name = "salesstatus")
    private String salesstatus;
    @Column(name = "jhreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jhreaddate;
    @Column(name = "jhreaded")
    private Boolean jhreaded;
    @Column(name = "jhrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jhrecdate;
    @Size(max = 20)
    @Column(name = "jhrecman")
    private String jhrecman;
    @Size(max = 400)
    @Column(name = "jhremark")
    private String jhremark;
    @Column(name = "jhdeldate")
    @Temporal(TemporalType.DATE)
    private Date jhdeldate;
    @Size(max = 20)
    @Column(name = "jhdelman")
    private String jhdelman;
    @Size(max = 2)
    @Column(name = "jhstatus")
    private String jhstatus;
    @Column(name = "hgreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hgreaddate;
    @Column(name = "hgreaded")
    private Boolean hgreaded;
    @Column(name = "hgrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hgrecdate;
    @Size(max = 20)
    @Column(name = "hgrecman")
    private String hgrecman;
    @Size(max = 400)
    @Column(name = "hgreq")
    private String hgreq;
    @Size(max = 400)
    @Column(name = "hgremark")
    private String hgremark;
    @Column(name = "hgsets")
    private Integer hgsets;
    @Size(max = 200)
    @Column(name = "hgspec")
    private String hgspec;
    @Column(name = "hgcolors")
    private Integer hgcolors;
    @Column(name = "hgdeldate")
    @Temporal(TemporalType.DATE)
    private Date hgdeldate;
    @Size(max = 20)
    @Column(name = "hgdelman")
    private String hgdelman;
    @Size(max = 2)
    @Column(name = "hgstatus")
    private String hgstatus;
    @Column(name = "zbreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zbreaddate;
    @Column(name = "zbreaded")
    private Boolean zbreaded;
    @Column(name = "zbrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zbrecdate;
    @Size(max = 20)
    @Column(name = "zbrecman")
    private String zbrecman;
    @Size(max = 400)
    @Column(name = "zbreq")
    private String zbreq;
    @Size(max = 400)
    @Column(name = "zbremark")
    private String zbremark;
    @Column(name = "zbcount")
    private Integer zbcount;
    @Column(name = "zbdeldate")
    @Temporal(TemporalType.DATE)
    private Date zbdeldate;
    @Size(max = 20)
    @Column(name = "zbdelman")
    private String zbdelman;
    @Size(max = 2)
    @Column(name = "zbstatus")
    private String zbstatus;
    @Column(name = "psreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date psreaddate;
    @Column(name = "psreaded")
    private Boolean psreaded;
    @Column(name = "psrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date psrecdate;
    @Size(max = 20)
    @Column(name = "psrecman")
    private String psrecman;
    @Size(max = 400)
    @Column(name = "psreq")
    private String psreq;
    @Size(max = 400)
    @Column(name = "psremark")
    private String psremark;
    @Column(name = "psdeldate")
    @Temporal(TemporalType.DATE)
    private Date psdeldate;
    @Size(max = 20)
    @Column(name = "psdelman")
    private String psdelman;
    @Size(max = 2)
    @Column(name = "psstatus")
    private String psstatus;
    @Column(name = "yhreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yhreaddate;
    @Column(name = "yhreaded")
    private Boolean yhreaded;
    @Column(name = "yhrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yhrecdate;
    @Size(max = 20)
    @Column(name = "yhrecman")
    private String yhrecman;
    @Size(max = 400)
    @Column(name = "yhreq")
    private String yhreq;
    @Size(max = 400)
    @Column(name = "yhremark")
    private String yhremark;
    @Size(max = 45)
    @Column(name = "yhdept")
    private String yhdept;
    @Size(max = 45)
    @Column(name = "yhgyds")
    private String yhgyds;
    @Column(name = "yhdeldate")
    @Temporal(TemporalType.DATE)
    private Date yhdeldate;
    @Size(max = 20)
    @Column(name = "yhdelman")
    private String yhdelman;
    @Size(max = 2)
    @Column(name = "yhstatus")
    private String yhstatus;
    @Column(name = "zhreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zhreaddate;
    @Column(name = "zhreaded")
    private Boolean zhreaded;
    @Column(name = "zhrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zhrecdate;
    @Size(max = 20)
    @Column(name = "zhrecman")
    private String zhrecman;
    @Size(max = 400)
    @Column(name = "zhreq")
    private String zhreq;
    @Size(max = 400)
    @Column(name = "zhremark")
    private String zhremark;
    @Column(name = "zhdeldate")
    @Temporal(TemporalType.DATE)
    private Date zhdeldate;
    @Size(max = 20)
    @Column(name = "zhdelman")
    private String zhdelman;
    @Size(max = 2)
    @Column(name = "zhstatus")
    private String zhstatus;
    @Column(name = "sxreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sxreaddate;
    @Column(name = "sxreaded")
    private Boolean sxreaded;
    @Column(name = "sxrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sxrecdate;
    @Size(max = 20)
    @Column(name = "sxrecman")
    private String sxrecman;
    @Size(max = 400)
    @Column(name = "sxreq")
    private String sxreq;
    @Size(max = 400)
    @Column(name = "sxremark")
    private String sxremark;
    @Column(name = "sxdeldate")
    @Temporal(TemporalType.DATE)
    private Date sxdeldate;
    @Size(max = 20)
    @Column(name = "sxdelman")
    private String sxdelman;
    @Size(max = 2)
    @Column(name = "sxstatus")
    private String sxstatus;
    @Column(name = "dxreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dxreaddate;
    @Column(name = "dxreaded")
    private Boolean dxreaded;
    @Column(name = "dxrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dxrecdate;
    @Size(max = 20)
    @Column(name = "dxrecman")
    private String dxrecman;
    @Size(max = 400)
    @Column(name = "dxreq")
    private String dxreq;
    @Size(max = 400)
    @Column(name = "dxremark")
    private String dxremark;
    @Column(name = "dxdeldate")
    @Temporal(TemporalType.DATE)
    private Date dxdeldate;
    @Size(max = 20)
    @Column(name = "dxdelman")
    private String dxdelman;
    @Size(max = 2)
    @Column(name = "dxstatus")
    private String dxstatus;
    @Column(name = "ckreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ckreaddate;
    @Column(name = "ckreaded")
    private Boolean ckreaded;
    @Column(name = "ckrecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ckrecdate;
    @Size(max = 20)
    @Column(name = "ckrecman")
    private String ckrecman;
    @Size(max = 400)
    @Column(name = "ckreq")
    private String ckreq;
    @Size(max = 400)
    @Column(name = "ckremark")
    private String ckremark;
    @Column(name = "ckdeldate")
    @Temporal(TemporalType.DATE)
    private Date ckdeldate;
    @Size(max = 20)
    @Column(name = "ckdelman")
    private String ckdelman;
    @Size(max = 2)
    @Column(name = "ckstatus")
    private String ckstatus;

    public ProductionOrder() {
    }

    public String getCustomerno() {
        return customerno;
    }

    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public ItemMaster getDesign() {
        return design;
    }

    public void setDesign(ItemMaster design) {
        this.design = design;
    }

    public String getDesignno() {
        return designno;
    }

    public void setDesignno(String designno) {
        this.designno = designno;
    }

    public String getDesignimg() {
        return designimg;
    }

    public void setDesignimg(String designimg) {
        this.designimg = designimg;
    }

    public String getDesignspec() {
        return designspec;
    }

    public void setDesignspec(String designspec) {
        this.designspec = designspec;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Date getPrintdate() {
        return printdate;
    }

    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }

    public Date getIssdate() {
        return issdate;
    }

    public void setIssdate(Date issdate) {
        this.issdate = issdate;
    }

    public Date getFindate() {
        return findate;
    }

    public void setFindate(Date findate) {
        this.findate = findate;
    }

    public String getSalesremark() {
        return salesremark;
    }

    public void setSalesremark(String salesremark) {
        this.salesremark = salesremark;
    }

    public String getSalesstatus() {
        return salesstatus;
    }

    public void setSalesstatus(String salesstatus) {
        this.salesstatus = salesstatus;
    }

    public Date getJhreaddate() {
        return jhreaddate;
    }

    public void setJhreaddate(Date jhreaddate) {
        this.jhreaddate = jhreaddate;
    }

    public Boolean getJhreaded() {
        return jhreaded;
    }

    public void setJhreaded(Boolean jhreaded) {
        this.jhreaded = jhreaded;
    }

    public Date getJhrecdate() {
        return jhrecdate;
    }

    public void setJhrecdate(Date jhrecdate) {
        this.jhrecdate = jhrecdate;
    }

    public String getJhrecman() {
        return jhrecman;
    }

    public void setJhrecman(String jhrecman) {
        this.jhrecman = jhrecman;
    }

    public String getJhremark() {
        return jhremark;
    }

    public void setJhremark(String jhremark) {
        this.jhremark = jhremark;
    }

    public Date getJhdeldate() {
        return jhdeldate;
    }

    public void setJhdeldate(Date jhdeldate) {
        this.jhdeldate = jhdeldate;
    }

    public String getJhdelman() {
        return jhdelman;
    }

    public void setJhdelman(String jhdelman) {
        this.jhdelman = jhdelman;
    }

    public String getJhstatus() {
        return jhstatus;
    }

    public void setJhstatus(String jhstatus) {
        this.jhstatus = jhstatus;
    }

    public Date getHgreaddate() {
        return hgreaddate;
    }

    public void setHgreaddate(Date hgreaddate) {
        this.hgreaddate = hgreaddate;
    }

    public Boolean getHgreaded() {
        return hgreaded;
    }

    public void setHgreaded(Boolean hgreaded) {
        this.hgreaded = hgreaded;
    }

    public Date getHgrecdate() {
        return hgrecdate;
    }

    public void setHgrecdate(Date hgrecdate) {
        this.hgrecdate = hgrecdate;
    }

    public String getHgrecman() {
        return hgrecman;
    }

    public void setHgrecman(String hgrecman) {
        this.hgrecman = hgrecman;
    }

    public String getHgreq() {
        return hgreq;
    }

    public void setHgreq(String hgreq) {
        this.hgreq = hgreq;
    }

    public String getHgremark() {
        return hgremark;
    }

    public void setHgremark(String hgremark) {
        this.hgremark = hgremark;
    }

    public Integer getHgsets() {
        return hgsets;
    }

    public void setHgsets(Integer hgsets) {
        this.hgsets = hgsets;
    }

    public String getHgspec() {
        return hgspec;
    }

    public void setHgspec(String hgspec) {
        this.hgspec = hgspec;
    }

    public Integer getHgcolors() {
        return hgcolors;
    }

    public void setHgcolors(Integer hgcolors) {
        this.hgcolors = hgcolors;
    }

    public Date getHgdeldate() {
        return hgdeldate;
    }

    public void setHgdeldate(Date hgdeldate) {
        this.hgdeldate = hgdeldate;
    }

    public String getHgdelman() {
        return hgdelman;
    }

    public void setHgdelman(String hgdelman) {
        this.hgdelman = hgdelman;
    }

    public String getHgstatus() {
        return hgstatus;
    }

    public void setHgstatus(String hgstatus) {
        this.hgstatus = hgstatus;
    }

    public Date getZbreaddate() {
        return zbreaddate;
    }

    public void setZbreaddate(Date zbreaddate) {
        this.zbreaddate = zbreaddate;
    }

    public Boolean getZbreaded() {
        return zbreaded;
    }

    public void setZbreaded(Boolean zbreaded) {
        this.zbreaded = zbreaded;
    }

    public Date getZbrecdate() {
        return zbrecdate;
    }

    public void setZbrecdate(Date zbrecdate) {
        this.zbrecdate = zbrecdate;
    }

    public String getZbrecman() {
        return zbrecman;
    }

    public void setZbrecman(String zbrecman) {
        this.zbrecman = zbrecman;
    }

    public String getZbreq() {
        return zbreq;
    }

    public void setZbreq(String zbreq) {
        this.zbreq = zbreq;
    }

    public String getZbremark() {
        return zbremark;
    }

    public void setZbremark(String zbremark) {
        this.zbremark = zbremark;
    }

    public Integer getZbcount() {
        return zbcount;
    }

    public void setZbcount(Integer zbcount) {
        this.zbcount = zbcount;
    }

    public Date getZbdeldate() {
        return zbdeldate;
    }

    public void setZbdeldate(Date zbdeldate) {
        this.zbdeldate = zbdeldate;
    }

    public String getZbdelman() {
        return zbdelman;
    }

    public void setZbdelman(String zbdelman) {
        this.zbdelman = zbdelman;
    }

    public String getZbstatus() {
        return zbstatus;
    }

    public void setZbstatus(String zbstatus) {
        this.zbstatus = zbstatus;
    }

    public Date getPsreaddate() {
        return psreaddate;
    }

    public void setPsreaddate(Date psreaddate) {
        this.psreaddate = psreaddate;
    }

    public Boolean getPsreaded() {
        return psreaded;
    }

    public void setPsreaded(Boolean psreaded) {
        this.psreaded = psreaded;
    }

    public Date getPsrecdate() {
        return psrecdate;
    }

    public void setPsrecdate(Date psrecdate) {
        this.psrecdate = psrecdate;
    }

    public String getPsrecman() {
        return psrecman;
    }

    public void setPsrecman(String psrecman) {
        this.psrecman = psrecman;
    }

    public String getPsreq() {
        return psreq;
    }

    public void setPsreq(String psreq) {
        this.psreq = psreq;
    }

    public String getPsremark() {
        return psremark;
    }

    public void setPsremark(String psremark) {
        this.psremark = psremark;
    }

    public Date getPsdeldate() {
        return psdeldate;
    }

    public void setPsdeldate(Date psdeldate) {
        this.psdeldate = psdeldate;
    }

    public String getPsdelman() {
        return psdelman;
    }

    public void setPsdelman(String psdelman) {
        this.psdelman = psdelman;
    }

    public String getPsstatus() {
        return psstatus;
    }

    public void setPsstatus(String psstatus) {
        this.psstatus = psstatus;
    }

    public Date getYhreaddate() {
        return yhreaddate;
    }

    public void setYhreaddate(Date yhreaddate) {
        this.yhreaddate = yhreaddate;
    }

    public Boolean getYhreaded() {
        return yhreaded;
    }

    public void setYhreaded(Boolean yhreaded) {
        this.yhreaded = yhreaded;
    }

    public Date getYhrecdate() {
        return yhrecdate;
    }

    public void setYhrecdate(Date yhrecdate) {
        this.yhrecdate = yhrecdate;
    }

    public String getYhrecman() {
        return yhrecman;
    }

    public void setYhrecman(String yhrecman) {
        this.yhrecman = yhrecman;
    }

    public String getYhreq() {
        return yhreq;
    }

    public void setYhreq(String yhreq) {
        this.yhreq = yhreq;
    }

    public String getYhremark() {
        return yhremark;
    }

    public void setYhremark(String yhremark) {
        this.yhremark = yhremark;
    }

    public String getYhdept() {
        return yhdept;
    }

    public void setYhdept(String yhdept) {
        this.yhdept = yhdept;
    }

    public String getYhgyds() {
        return yhgyds;
    }

    public void setYhgyds(String yhgyds) {
        this.yhgyds = yhgyds;
    }

    public Date getYhdeldate() {
        return yhdeldate;
    }

    public void setYhdeldate(Date yhdeldate) {
        this.yhdeldate = yhdeldate;
    }

    public String getYhdelman() {
        return yhdelman;
    }

    public void setYhdelman(String yhdelman) {
        this.yhdelman = yhdelman;
    }

    public String getYhstatus() {
        return yhstatus;
    }

    public void setYhstatus(String yhstatus) {
        this.yhstatus = yhstatus;
    }

    public Date getZhreaddate() {
        return zhreaddate;
    }

    public void setZhreaddate(Date zhreaddate) {
        this.zhreaddate = zhreaddate;
    }

    public Boolean getZhreaded() {
        return zhreaded;
    }

    public void setZhreaded(Boolean zhreaded) {
        this.zhreaded = zhreaded;
    }

    public Date getZhrecdate() {
        return zhrecdate;
    }

    public void setZhrecdate(Date zhrecdate) {
        this.zhrecdate = zhrecdate;
    }

    public String getZhrecman() {
        return zhrecman;
    }

    public void setZhrecman(String zhrecman) {
        this.zhrecman = zhrecman;
    }

    public String getZhreq() {
        return zhreq;
    }

    public void setZhreq(String zhreq) {
        this.zhreq = zhreq;
    }

    public String getZhremark() {
        return zhremark;
    }

    public void setZhremark(String zhremark) {
        this.zhremark = zhremark;
    }

    public Date getZhdeldate() {
        return zhdeldate;
    }

    public void setZhdeldate(Date zhdeldate) {
        this.zhdeldate = zhdeldate;
    }

    public String getZhdelman() {
        return zhdelman;
    }

    public void setZhdelman(String zhdelman) {
        this.zhdelman = zhdelman;
    }

    public String getZhstatus() {
        return zhstatus;
    }

    public void setZhstatus(String zhstatus) {
        this.zhstatus = zhstatus;
    }

    public Date getSxreaddate() {
        return sxreaddate;
    }

    public void setSxreaddate(Date sxreaddate) {
        this.sxreaddate = sxreaddate;
    }

    public Boolean getSxreaded() {
        return sxreaded;
    }

    public void setSxreaded(Boolean sxreaded) {
        this.sxreaded = sxreaded;
    }

    public Date getSxrecdate() {
        return sxrecdate;
    }

    public void setSxrecdate(Date sxrecdate) {
        this.sxrecdate = sxrecdate;
    }

    public String getSxrecman() {
        return sxrecman;
    }

    public void setSxrecman(String sxrecman) {
        this.sxrecman = sxrecman;
    }

    public String getSxreq() {
        return sxreq;
    }

    public void setSxreq(String sxreq) {
        this.sxreq = sxreq;
    }

    public String getSxremark() {
        return sxremark;
    }

    public void setSxremark(String sxremark) {
        this.sxremark = sxremark;
    }

    public Date getSxdeldate() {
        return sxdeldate;
    }

    public void setSxdeldate(Date sxdeldate) {
        this.sxdeldate = sxdeldate;
    }

    public String getSxdelman() {
        return sxdelman;
    }

    public void setSxdelman(String sxdelman) {
        this.sxdelman = sxdelman;
    }

    public String getSxstatus() {
        return sxstatus;
    }

    public void setSxstatus(String sxstatus) {
        this.sxstatus = sxstatus;
    }

    public Date getDxreaddate() {
        return dxreaddate;
    }

    public void setDxreaddate(Date dxreaddate) {
        this.dxreaddate = dxreaddate;
    }

    public Boolean getDxreaded() {
        return dxreaded;
    }

    public void setDxreaded(Boolean dxreaded) {
        this.dxreaded = dxreaded;
    }

    public Date getDxrecdate() {
        return dxrecdate;
    }

    public void setDxrecdate(Date dxrecdate) {
        this.dxrecdate = dxrecdate;
    }

    public String getDxrecman() {
        return dxrecman;
    }

    public void setDxrecman(String dxrecman) {
        this.dxrecman = dxrecman;
    }

    public String getDxreq() {
        return dxreq;
    }

    public void setDxreq(String dxreq) {
        this.dxreq = dxreq;
    }

    public String getDxremark() {
        return dxremark;
    }

    public void setDxremark(String dxremark) {
        this.dxremark = dxremark;
    }

    public Date getDxdeldate() {
        return dxdeldate;
    }

    public void setDxdeldate(Date dxdeldate) {
        this.dxdeldate = dxdeldate;
    }

    public String getDxdelman() {
        return dxdelman;
    }

    public void setDxdelman(String dxdelman) {
        this.dxdelman = dxdelman;
    }

    public String getDxstatus() {
        return dxstatus;
    }

    public void setDxstatus(String dxstatus) {
        this.dxstatus = dxstatus;
    }

    public Date getCkreaddate() {
        return ckreaddate;
    }

    public void setCkreaddate(Date ckreaddate) {
        this.ckreaddate = ckreaddate;
    }

    public Boolean getCkreaded() {
        return ckreaded;
    }

    public void setCkreaded(Boolean ckreaded) {
        this.ckreaded = ckreaded;
    }

    public Date getCkrecdate() {
        return ckrecdate;
    }

    public void setCkrecdate(Date ckrecdate) {
        this.ckrecdate = ckrecdate;
    }

    public String getCkrecman() {
        return ckrecman;
    }

    public void setCkrecman(String ckrecman) {
        this.ckrecman = ckrecman;
    }

    public String getCkreq() {
        return ckreq;
    }

    public void setCkreq(String ckreq) {
        this.ckreq = ckreq;
    }

    public String getCkremark() {
        return ckremark;
    }

    public void setCkremark(String ckremark) {
        this.ckremark = ckremark;
    }

    public Date getCkdeldate() {
        return ckdeldate;
    }

    public void setCkdeldate(Date ckdeldate) {
        this.ckdeldate = ckdeldate;
    }

    public String getCkdelman() {
        return ckdelman;
    }

    public void setCkdelman(String ckdelman) {
        this.ckdelman = ckdelman;
    }

    public String getCkstatus() {
        return ckstatus;
    }

    public void setCkstatus(String ckstatus) {
        this.ckstatus = ckstatus;
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
        if (!(object instanceof ProductionOrder)) {
            return false;
        }
        ProductionOrder other = (ProductionOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.ProductionOrder[ id=" + id + " ]";
    }

    /**
     * @return the formtype
     */
    public SalesType getFormtype() {
        return formtype;
    }

    /**
     * @param formtype the formtype to set
     */
    public void setFormtype(SalesType formtype) {
        this.formtype = formtype;
    }

    /**
     * @return the formkind
     */
    public String getFormkind() {
        return formkind;
    }

    /**
     * @param formkind the formkind to set
     */
    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    /**
     * @return the itemprocessid
     */
    public Integer getItemprocessid() {
        return itemprocessid;
    }

    /**
     * @param itemprocessid the itemprocessid to set
     */
    public void setItemprocessid(Integer itemprocessid) {
        this.itemprocessid = itemprocessid;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.entity;

import com.lightshell.comm.BaseEntityWithOperate;
import java.math.BigDecimal;
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
@Table(name = "factoryorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryOrder.getRowCount", query = "SELECT COUNT(s) FROM FactoryOrder s "),
    @NamedQuery(name = "FactoryOrder.getRowCountBySalesman", query = "SELECT COUNT(s) FROM FactoryOrder s WHERE s.salesman.id = :salesman "),
    @NamedQuery(name = "FactoryOrder.findAll", query = "SELECT s FROM FactoryOrder s ORDER BY s.salesstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findById", query = "SELECT s FROM FactoryOrder s WHERE s.id = :id"),
    @NamedQuery(name = "FactoryOrder.findByOrderId", query = "SELECT s FROM FactoryOrder s WHERE s.orderid = :orderid"),
    @NamedQuery(name = "FactoryOrder.findByOrderdate", query = "SELECT s FROM FactoryOrder s WHERE s.orderdate = :orderdate"),
    @NamedQuery(name = "FactoryOrder.findByItemId", query = "SELECT s FROM FactoryOrder s WHERE s.itemid = :itemid"),
    @NamedQuery(name = "FactoryOrder.findByColorId", query = "SELECT s FROM FactoryOrder s WHERE s.colorid = :colorid"),
    @NamedQuery(name = "FactoryOrder.findBySalesman", query = "SELECT s FROM FactoryOrder s WHERE s.salesman.id = :salesman ORDER BY s.salesstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findBySalesstatus", query = "SELECT s FROM FactoryOrder s WHERE s.salesstatus = :salesstatus ORDER BY s.salesstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findByHgstatus", query = "SELECT s FROM FactoryOrder s WHERE s.hgstatus = :hgstatus ORDER BY s.hgstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findByZbstatus", query = "SELECT s FROM FactoryOrder s WHERE s.zbstatus = :zbstatus ORDER BY s.zbstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findByPsstatus", query = "SELECT s FROM FactoryOrder s WHERE s.psstatus = :psstatus ORDER BY s.psstatus,s.id DESC"),
    @NamedQuery(name = "FactoryOrder.findByStatus", query = "SELECT s FROM FactoryOrder s WHERE s.status = :status ORDER BY s.salesstatus,s.id DESC")})
public class FactoryOrder extends BaseEntityWithOperate {

    @JoinColumn(name = "salesman", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemUser salesman;

    @Size(max = 45)
    @Column(name = "orderid")
    private String orderid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderdate")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Size(max = 200)
    @Column(name = "orderimg")
    private String orderimg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemid")
    private String itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "colorid")
    private String colorid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "itemspec")
    private String itemspec;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private String qty;
    @Size(max = 20)
    @Column(name = "equipmentid")
    private String equipmentid;
    @Column(name = "yhdate")
    @Temporal(TemporalType.DATE)
    private Date yhdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
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
    @Column(name = "hgremark")
    private String hgremark;
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
    @Column(name = "zbremark")
    private String zbremark;
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
    @Column(name = "psremark")
    private String psremark;
    @Size(max = 45)
    @Column(name = "psns")
    private String psns;
    @Size(max = 45)
    @Column(name = "pshl")
    private String pshl;
    @Size(max = 45)
    @Column(name = "psyj")
    private String psyj;
    @Size(max = 45)
    @Column(name = "pszhsj")
    private String pszhsj;
    @Size(max = 45)
    @Column(name = "pszhcs")
    private String pszhcs;
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
    @Column(name = "yhremark")
    private String yhremark;
    @Size(max = 45)
    @Column(name = "scbm")
    private String scbm;
    @Column(name = "gyds")
    private BigDecimal gyds;
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
    @Column(name = "zhremark")
    private String zhremark;
    @Size(max = 45)
    @Column(name = "zhsb")
    private String zhsb;
    @Size(max = 45)
    @Column(name = "zhsj")
    private String zhsj;
    @Size(max = 45)
    @Column(name = "zhcs")
    private String zhcs;
    @Size(max = 45)
    @Column(name = "jbyl")
    private String jbyl;
    @Size(max = 45)
    @Column(name = "zhyl")
    private String zhyl;
    @Size(max = 45)
    @Column(name = "zhwd")
    private String zhwd;
    @Size(max = 45)
    @Column(name = "zhsd")
    private String zhsd;
    @Size(max = 45)
    @Column(name = "cb")
    private String cb;
    @Column(name = "zhdeldate")
    @Temporal(TemporalType.DATE)
    private Date zhdeldate;
    @Size(max = 20)
    @Column(name = "zhdelman")
    private String zhdelman;
    @Size(max = 2)
    @Column(name = "zhstatus")
    private String zhstatus;
    @Size(max = 200)
    @Column(name = "bjremark")
    private String bjremark;
    @Column(name = "bjdeldate")
    @Temporal(TemporalType.DATE)
    private Date bjdeldate;
    @Size(max = 200)
    @Column(name = "sxremark")
    private String sxremark;
    @Column(name = "sxdeldate")
    @Temporal(TemporalType.DATE)
    private Date sxdeldate;
    @Size(max = 200)
    @Column(name = "lfremark")
    private String lfremark;
    @Column(name = "lfdeldate")
    @Temporal(TemporalType.DATE)
    private Date lfdeldate;
    @Size(max = 200)
    @Column(name = "hxremark")
    private String hxremark;
    @Column(name = "hxdeldate")
    @Temporal(TemporalType.DATE)
    private Date hxdeldate;
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
    @Column(name = "cpreaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cpreaddate;
    @Column(name = "cpreaded")
    private Boolean cpreaded;
    @Column(name = "cprecdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cprecdate;
    @Size(max = 20)
    @Column(name = "cprecman")
    private String cprecman;
    @Size(max = 400)
    @Column(name = "cpremark")
    private String cpremark;
    @Column(name = "cpdeldate")
    @Temporal(TemporalType.DATE)
    private Date cpdeldate;
    @Size(max = 20)
    @Column(name = "cpdelman")
    private String cpdelman;
    @Size(max = 2)
    @Column(name = "cpstatus")
    private String cpstatus;

    public FactoryOrder() {
    }

    public FactoryOrder(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(String equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Date getYhdate() {
        return yhdate;
    }

    public void setYhdate(Date yhdate) {
        this.yhdate = yhdate;
    }

    public String getSalesremark() {
        return salesremark;
    }

    public void setSalesremark(String salesremark) {
        this.salesremark = salesremark;
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

    public String getHgremark() {
        return hgremark;
    }

    public void setHgremark(String hgremark) {
        this.hgremark = hgremark;
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

    public String getZbremark() {
        return zbremark;
    }

    public void setZbremark(String zbremark) {
        this.zbremark = zbremark;
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

    public String getPsremark() {
        return psremark;
    }

    public void setPsremark(String psremark) {
        this.psremark = psremark;
    }

    public String getPsns() {
        return psns;
    }

    public void setPsns(String psns) {
        this.psns = psns;
    }

    public String getPshl() {
        return pshl;
    }

    public void setPshl(String pshl) {
        this.pshl = pshl;
    }

    public String getPsyj() {
        return psyj;
    }

    public void setPsyj(String psyj) {
        this.psyj = psyj;
    }

    public String getPszhsj() {
        return pszhsj;
    }

    public void setPszhsj(String pszhsj) {
        this.pszhsj = pszhsj;
    }

    public String getPszhcs() {
        return pszhcs;
    }

    public void setPszhcs(String pszhcs) {
        this.pszhcs = pszhcs;
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

    /**
     * @return the salesstatus
     */
    public String getSalesstatus() {
        return salesstatus;
    }

    /**
     * @param salesstatus the salesstatus to set
     */
    public void setSalesstatus(String salesstatus) {
        this.salesstatus = salesstatus;
    }

    /**
     * @return the salesman
     */
    public SystemUser getSalesman() {
        return salesman;
    }

    /**
     * @param salesman the salesman to set
     */
    public void setSalesman(SystemUser salesman) {
        this.salesman = salesman;
    }

    /**
     * @return the jhreaddate
     */
    public Date getJhreaddate() {
        return jhreaddate;
    }

    /**
     * @param jhreaddate the jhreaddate to set
     */
    public void setJhreaddate(Date jhreaddate) {
        this.jhreaddate = jhreaddate;
    }

    /**
     * @return the jhreaded
     */
    public Boolean getJhreaded() {
        return jhreaded;
    }

    /**
     * @param jhreaded the jhreaded to set
     */
    public void setJhreaded(Boolean jhreaded) {
        this.jhreaded = jhreaded;
    }

    /**
     * @return the jhrecdate
     */
    public Date getJhrecdate() {
        return jhrecdate;
    }

    /**
     * @param jhrecdate the jhrecdate to set
     */
    public void setJhrecdate(Date jhrecdate) {
        this.jhrecdate = jhrecdate;
    }

    /**
     * @return the jhrecman
     */
    public String getJhrecman() {
        return jhrecman;
    }

    /**
     * @param jhrecman the jhrecman to set
     */
    public void setJhrecman(String jhrecman) {
        this.jhrecman = jhrecman;
    }

    /**
     * @return the jhremark
     */
    public String getJhremark() {
        return jhremark;
    }

    /**
     * @param jhremark the jhremark to set
     */
    public void setJhremark(String jhremark) {
        this.jhremark = jhremark;
    }

    /**
     * @return the jhdeldate
     */
    public Date getJhdeldate() {
        return jhdeldate;
    }

    /**
     * @param jhdeldate the jhdeldate to set
     */
    public void setJhdeldate(Date jhdeldate) {
        this.jhdeldate = jhdeldate;
    }

    /**
     * @return the jhdelman
     */
    public String getJhdelman() {
        return jhdelman;
    }

    /**
     * @param jhdelman the jhdelman to set
     */
    public void setJhdelman(String jhdelman) {
        this.jhdelman = jhdelman;
    }

    /**
     * @return the jhstatus
     */
    public String getJhstatus() {
        return jhstatus;
    }

    /**
     * @param jhstatus the jhstatus to set
     */
    public void setJhstatus(String jhstatus) {
        this.jhstatus = jhstatus;
    }

    public String getOrderimg() {
        return orderimg;
    }

    public void setOrderimg(String orderimg) {
        this.orderimg = orderimg;
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

    public String getYhremark() {
        return yhremark;
    }

    public void setYhremark(String yhremark) {
        this.yhremark = yhremark;
    }

    public String getScbm() {
        return scbm;
    }

    public void setScbm(String scbm) {
        this.scbm = scbm;
    }

    public BigDecimal getGyds() {
        return gyds;
    }

    public void setGyds(BigDecimal gyds) {
        this.gyds = gyds;
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

    public String getZhremark() {
        return zhremark;
    }

    public void setZhremark(String zhremark) {
        this.zhremark = zhremark;
    }

    public String getZhsb() {
        return zhsb;
    }

    public void setZhsb(String zhsb) {
        this.zhsb = zhsb;
    }

    public String getZhsj() {
        return zhsj;
    }

    public void setZhsj(String zhsj) {
        this.zhsj = zhsj;
    }

    public String getZhcs() {
        return zhcs;
    }

    public void setZhcs(String zhcs) {
        this.zhcs = zhcs;
    }

    public String getJbyl() {
        return jbyl;
    }

    public void setJbyl(String jbyl) {
        this.jbyl = jbyl;
    }

    public String getZhyl() {
        return zhyl;
    }

    public void setZhyl(String zhyl) {
        this.zhyl = zhyl;
    }

    public String getZhwd() {
        return zhwd;
    }

    public void setZhwd(String zhwd) {
        this.zhwd = zhwd;
    }

    public String getZhsd() {
        return zhsd;
    }

    public void setZhsd(String zhsd) {
        this.zhsd = zhsd;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getZhstatus() {
        return zhstatus;
    }

    public void setZhstatus(String zhstatus) {
        this.zhstatus = zhstatus;
    }

    public String getBjremark() {
        return bjremark;
    }

    public void setBjremark(String bjremark) {
        this.bjremark = bjremark;
    }

    public Date getBjdeldate() {
        return bjdeldate;
    }

    public void setBjdeldate(Date bjdeldate) {
        this.bjdeldate = bjdeldate;
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

    public String getLfremark() {
        return lfremark;
    }

    public void setLfremark(String lfremark) {
        this.lfremark = lfremark;
    }

    public Date getLfdeldate() {
        return lfdeldate;
    }

    public void setLfdeldate(Date lfdeldate) {
        this.lfdeldate = lfdeldate;
    }

    public String getHxremark() {
        return hxremark;
    }

    public void setHxremark(String hxremark) {
        this.hxremark = hxremark;
    }

    public Date getHxdeldate() {
        return hxdeldate;
    }

    public void setHxdeldate(Date hxdeldate) {
        this.hxdeldate = hxdeldate;
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

    public Date getCpreaddate() {
        return cpreaddate;
    }

    public void setCpreaddate(Date cpreaddate) {
        this.cpreaddate = cpreaddate;
    }

    public Boolean getCpreaded() {
        return cpreaded;
    }

    public void setCpreaded(Boolean cpreaded) {
        this.cpreaded = cpreaded;
    }

    public Date getCprecdate() {
        return cprecdate;
    }

    public void setCprecdate(Date cprecdate) {
        this.cprecdate = cprecdate;
    }

    public String getCprecman() {
        return cprecman;
    }

    public void setCprecman(String cprecman) {
        this.cprecman = cprecman;
    }

    public String getCpremark() {
        return cpremark;
    }

    public void setCpremark(String cpremark) {
        this.cpremark = cpremark;
    }

    public Date getCpdeldate() {
        return cpdeldate;
    }

    public void setCpdeldate(Date cpdeldate) {
        this.cpdeldate = cpdeldate;
    }

    public String getCpdelman() {
        return cpdelman;
    }

    public void setCpdelman(String cpdelman) {
        this.cpdelman = cpdelman;
    }

    public String getCpstatus() {
        return cpstatus;
    }

    public void setCpstatus(String cpstatus) {
        this.cpstatus = cpstatus;
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
        if (!(object instanceof FactoryOrder)) {
            return false;
        }
        FactoryOrder other = (FactoryOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhsc.entity.SalesOrder[ id=" + id + " ]";
    }

    /**
     * @return the zhdeldate
     */
    public Date getZhdeldate() {
        return zhdeldate;
    }

    /**
     * @param zhdeldate the zhdeldate to set
     */
    public void setZhdeldate(Date zhdeldate) {
        this.zhdeldate = zhdeldate;
    }

    /**
     * @return the zhdelman
     */
    public String getZhdelman() {
        return zhdelman;
    }

    /**
     * @param zhdelman the zhdelman to set
     */
    public void setZhdelman(String zhdelman) {
        this.zhdelman = zhdelman;
    }

}

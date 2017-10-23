/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "biyaoImport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiyaoImport.findAll", query = "SELECT b FROM BiyaoImport b")
    , @NamedQuery(name = "BiyaoImport.findById", query = "SELECT b FROM BiyaoImport b WHERE b.id = :id")
    , @NamedQuery(name = "BiyaoImport.findByFormid", query = "SELECT b FROM BiyaoImport b WHERE b.formid = :formid")
    , @NamedQuery(name = "BiyaoImport.findByStatus", query = "SELECT b FROM BiyaoImport b WHERE b.status = :status")})
public class BiyaoImport extends FormEntity {

    @Size(max = 45)
    @Column(name = "refno")
    private String refno;
    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Column(name = "paydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paydate;
    @Size(max = 45)
    @Column(name = "contacter")
    private String contacter;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 20)
    @Column(name = "province")
    private String province;
    @Size(max = 20)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "area")
    private String area;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 200)
    @Column(name = "shipmarks")
    private String shipmarks;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Size(max = 200)
    @Column(name = "salesremark")
    private String salesremark;
    @Size(max = 200)
    @Column(name = "packremark")
    private String packremark;
    @Size(max = 10)
    @Column(name = "bill")
    private String bill;
    @Size(max = 20)
    @Column(name = "billtype")
    private String billtype;
    @Size(max = 100)
    @Column(name = "billtitle")
    private String billtitle;
    @Size(max = 45)
    @Column(name = "uscc")
    private String uscc;
    @Size(max = 45)
    @Column(name = "mailadd")
    private String mailadd;
    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 32)
    @Column(name = "itemOID")
    private String itemOID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 100)
    @Column(name = "itemdesc")
    private String itemdesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Column(name = "freight")
    private BigDecimal freight;
    @Size(max = 10)
    @Column(name = "period")
    private String period;
    @Size(max = 100)
    @Column(name = "outside1")
    private String outside1;
    @Size(max = 100)
    @Column(name = "outside2")
    private String outside2;
    @Size(max = 100)
    @Column(name = "outside3")
    private String outside3;
    @Size(max = 100)
    @Column(name = "outside4")
    private String outside4;
    @Size(max = 100)
    @Column(name = "outside5")
    private String outside5;
    @Size(max = 100)
    @Column(name = "itemspec1")
    private String itemspec1;
    @Size(max = 100)
    @Column(name = "itemspec2")
    private String itemspec2;

    @Column(name = "firstdelivery")
    @Temporal(TemporalType.DATE)
    private Date firstdelivery;
    @Column(name = "firsttime")
    @Temporal(TemporalType.TIME)
    private Date firsttime;
    @Column(name = "lastdelivery")
    @Temporal(TemporalType.DATE)
    private Date lastdelivery;
    @Column(name = "lasttime")
    @Temporal(TemporalType.TIME)
    private Date lasttime;
    @Column(name = "printdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printdate;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;
    @Size(max = 45)
    @Column(name = "deliveryno")
    private String deliveryno;

    public BiyaoImport() {
        this.refno = "1";
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getShipmarks() {
        return shipmarks;
    }

    public void setShipmarks(String shipmarks) {
        this.shipmarks = shipmarks;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalesremark() {
        return salesremark;
    }

    public void setSalesremark(String salesremark) {
        this.salesremark = salesremark;
    }

    /**
     * @return the packremark
     */
    public String getPackremark() {
        return packremark;
    }

    /**
     * @param packremark the packremark to set
     */
    public void setPackremark(String packremark) {
        this.packremark = packremark;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBilltitle() {
        return billtitle;
    }

    public void setBilltitle(String billtitle) {
        this.billtitle = billtitle;
    }

    public String getUscc() {
        return uscc;
    }

    public void setUscc(String uscc) {
        this.uscc = uscc;
    }

    public String getMailadd() {
        return mailadd;
    }

    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getItemOID() {
        return itemOID;
    }

    public void setItemOID(String itemOID) {
        this.itemOID = itemOID;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getOutside1() {
        return outside1;
    }

    public void setOutside1(String outside1) {
        this.outside1 = outside1;
    }

    public String getOutside2() {
        return outside2;
    }

    public void setOutside2(String outside2) {
        this.outside2 = outside2;
    }

    public String getOutside3() {
        return outside3;
    }

    public void setOutside3(String outside3) {
        this.outside3 = outside3;
    }

    public String getOutside4() {
        return outside4;
    }

    public void setOutside4(String outside4) {
        this.outside4 = outside4;
    }

    public String getOutside5() {
        return outside5;
    }

    public void setOutside5(String outside5) {
        this.outside5 = outside5;
    }

    public String getItemspec1() {
        return itemspec1;
    }

    public void setItemspec1(String itemspec1) {
        this.itemspec1 = itemspec1;
    }

    public String getItemspec2() {
        return itemspec2;
    }

    public void setItemspec2(String itemspec2) {
        this.itemspec2 = itemspec2;
    }

    /**
     * @return the firstdelivery
     */
    public Date getFirstdelivery() {
        return firstdelivery;
    }

    /**
     * @param firstdelivery the firstdelivery to set
     */
    public void setFirstdelivery(Date firstdelivery) {
        this.firstdelivery = firstdelivery;
    }

    /**
     * @return the firsttime
     */
    public Date getFirsttime() {
        return firsttime;
    }

    /**
     * @param firsttime the firsttime to set
     */
    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    /**
     * @return the lastdelivery
     */
    public Date getLastdelivery() {
        return lastdelivery;
    }

    /**
     * @param lastdelivery the lastdelivery to set
     */
    public void setLastdelivery(Date lastdelivery) {
        this.lastdelivery = lastdelivery;
    }

    /**
     * @return the lasttime
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * @param lasttime the lasttime to set
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /**
     * @return the printdate
     */
    public Date getPrintdate() {
        return printdate;
    }

    /**
     * @param printdate the printdate to set
     */
    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    /**
     * @return the deliverydate
     */
    public Date getDeliverydate() {
        return deliverydate;
    }

    /**
     * @param deliverydate the deliverydate to set
     */
    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    /**
     * @return the deliveryno
     */
    public String getDeliveryno() {
        return deliveryno;
    }

    /**
     * @param deliveryno the deliveryno to set
     */
    public void setDeliveryno(String deliveryno) {
        this.deliveryno = deliveryno;
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
        if (!(object instanceof BiyaoImport)) {
            return false;
        }
        BiyaoImport other = (BiyaoImport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hhds.entity.BiyaoImport[ id=" + id + " ]";
    }

}

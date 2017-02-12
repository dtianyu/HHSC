/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.define;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author kevindong
 */
@Entity
public class ItemUnitType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;

    public ItemUnitType() {
    }

    public ItemUnitType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof ItemUnitType)) {
            return false;
        }
        ItemUnitType other = (ItemUnitType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myworld.define.ItemUnitType[ id=" + id + " ]";
    }
}

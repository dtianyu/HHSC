/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.define.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class GenderManagedBean {

    private List<Gender> genders;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public GenderManagedBean() {
        genders = new ArrayList<Gender>();
        genders.add(new Gender("M", "男"));
        genders.add(new Gender("F", "女"));
    }


    /**
     * @return the genders
     */
    public List<Gender> getGenders() {
        return genders;
    }

    /**
     * @param genders the genders to set
     */
    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }
}

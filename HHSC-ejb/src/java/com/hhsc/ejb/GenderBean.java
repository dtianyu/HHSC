/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.define.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author KevinDong
 */
@Stateless
@LocalBean
public class GenderBean {

    private List<Gender> genders;

    public GenderBean() {
        genders = new ArrayList<>();
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

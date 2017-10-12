/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.ejb.BusinessTypeBean;
import com.hhds.entity.BusinessType;
import com.hhds.lazy.BusinessTypeModel;
import com.hhds.web.SuperSingleBean;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class BusinessTypeManagedBean extends SuperSingleBean<BusinessType> {

    @EJB
    private BusinessTypeBean payKindBean;

    /**
     * Creates a new instance of BusinessTypeManagedBean
     */
    public BusinessTypeManagedBean() {
        super(BusinessType.class);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (!super.doBeforePersist()) {
            return false;
        }
        if (this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "biztype");
                this.newEntity.setBiztype(formid);
            } else {
                Map<String, Object> filters = new HashMap<>();
                filters.put("biztype", this.newEntity.getBiztype());
                if (payKindBean.getRowCount(filters) > 0) {
                    showErrorMsg("Error", "编号已存在无法保存");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void init() {
        this.superEJB = payKindBean;
        setModel(new BusinessTypeModel(payKindBean));
        super.init();
    }

}

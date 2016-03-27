/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.DepartmentBean;
import com.hhsc.entity.Department;
import com.hhsc.lazy.DepartmentModel;
import com.hhsc.web.SuperSingleBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class DepartmentManagedBean extends SuperSingleBean<Department> {

    @EJB
    private DepartmentBean departmentBean;

    protected List<Department> deptList;

    public DepartmentManagedBean() {
        super(Department.class);
    }

    @Override
    public void init() {
        this.superEJB = departmentBean;
        setModel(new DepartmentModel(departmentBean));
        setDeptList(departmentBean.findAll());
        super.init();
    }

    /**
     * @return the deptList
     */
    public List<Department> getDeptList() {
        return deptList;
    }

    /**
     * @param deptList the deptList to set
     */
    public void setDeptList(List<Department> deptList) {
        this.deptList = deptList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysGrantModuleBean;
import com.hhsc.ejb.SysprgBean;
import com.hhsc.entity.SysGrantModule;
import com.hhsc.entity.Sysprg;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "menuManagedBean")
@SessionScoped
public class MenuManagedBean implements Serializable {

    @EJB
    private SysprgBean sysprgBean;
    @EJB
    private SysGrantModuleBean sysgrantModuleBean;

    @ManagedProperty(value = "#{userManagedBean}")
    private UserManagedBean userManagedBean;

    private List<SysGrantModule> sysgantModuleList;
    private List<Sysprg> sysprgList;

    private MenuModel model;

    public MenuManagedBean() {
    }

    @PostConstruct
    public void init() {

        model = new DefaultMenuModel();

        if (getUserManagedBean() != null) {

            DefaultSubMenu appmenu;
            DefaultSubMenu submenu;
            DefaultMenuItem menuitem;

            menuitem = new DefaultMenuItem("Home");
            menuitem.setId("menu_home");
            menuitem.setOutcome("home");
            menuitem.setIcon("icon-home-outline");
            menuitem.setContainerStyleClass("layout-menubar-active");

            model.addElement(menuitem);

            appmenu = new DefaultSubMenu("应用");
            appmenu.setIcon("icon-th-thumb");

            sysgantModuleList = sysgrantModuleBean.findByUserId(userManagedBean.getCurrentUser().getId());
            if (sysgantModuleList != null && !sysgantModuleList.isEmpty()) {
                for (SysGrantModule grantModule : sysgantModuleList) {

                    submenu = new DefaultSubMenu(grantModule.getSysmodule().getName());
                    submenu.setIcon("icon-th-thumb");
                    sysprgList = null;
                    sysprgList = sysprgBean.findByModuleId(grantModule.getSysmodule().getId());
                    if (sysprgList != null && !sysprgList.isEmpty()) {
                        for (Sysprg prg : sysprgList) {
                            menuitem = new DefaultMenuItem(prg.getName());
                            menuitem.setIcon("icon-doc-text-1");
                            menuitem.setOutcome(prg.getApi());
                            submenu.addElement(menuitem);
                        }
                    }
                    appmenu.addElement(submenu);

                }
            }
            model.addElement(appmenu);

            submenu = new DefaultSubMenu("用户");
            submenu.setIcon("icon-vcard");
            menuitem = new DefaultMenuItem("更改密码");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("resetPwd");
            submenu.addElement(menuitem);

            model.addElement(submenu);
            //系统管理菜单
            submenu = new DefaultSubMenu("系统");
            submenu.setIcon("icon-th-thumb");
            submenu.setRendered(userManagedBean.getCurrentUser().getUserid().equals("Admin"));

            menuitem = new DefaultMenuItem("用户维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("systemuser");
            submenu.addElement(menuitem);
            
            menuitem = new DefaultMenuItem("用户授权");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("usergrant");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("角色群组");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("systemrole");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("角色授权");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("rolegrant");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("模块维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysmodule");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("功能维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysprg");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("模块授权");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysgrantmodule");
            submenu.addElement(menuitem);

            model.addElement(submenu);
        }
    }

    @PreDestroy
    public void destory() {

    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    /**
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }

}

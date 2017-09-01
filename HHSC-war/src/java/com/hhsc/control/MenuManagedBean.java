/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.control;

import com.hhsc.ejb.SysGrantModuleBean;
import com.hhsc.ejb.SysGrantPrgBean;
import com.hhsc.ejb.SystemRoleDetailBean;
import com.hhsc.entity.SysGrantModule;
import com.hhsc.entity.SysGrantPrg;
import com.hhsc.entity.SystemRoleDetail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private SystemRoleDetailBean systemRoleDetailBean;

    @EJB
    private SysGrantModuleBean sysGrantModuleBean;

    @EJB
    private SysGrantPrgBean sysGrantPrgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    private UserManagedBean userManagedBean;

    private List<SysGrantModule> userModuleGrantList;
    private List<SysGrantModule> roleModuleGrantList;
    private List<SysGrantModule> moduleGrantList;
    private List<SysGrantPrg> userPrgGrantList;
    private List<SysGrantPrg> rolePrgGrantList;
    private List<SysGrantPrg> prgGrantList;
    private List<SystemRoleDetail> roleList;

    private MenuModel model;
    private String systemName;

    public MenuManagedBean() {
    }

    @PostConstruct
    public void init() {

        boolean flag;
        moduleGrantList = new ArrayList<>();
        prgGrantList = new ArrayList<>();

        systemName = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.hhsc.system");

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

            moduleGrantList.clear();
            userModuleGrantList = sysGrantModuleBean.findBySystemNameAndUserId(systemName, userManagedBean.getCurrentUser().getId());
            userModuleGrantList.forEach((m) -> {
                moduleGrantList.add(m);
            });
            prgGrantList.clear();
            userPrgGrantList = sysGrantPrgBean.findBySystemNameAndUserId(systemName, userManagedBean.getCurrentUser().getId());
            userPrgGrantList.forEach((p) -> {
                prgGrantList.add(p);
            });
            roleList = systemRoleDetailBean.findByUserId(userManagedBean.getCurrentUser().getId());
            for (SystemRoleDetail r : roleList) {
                roleModuleGrantList = sysGrantModuleBean.findBySystemNameAndRoleId(systemName, r.getPid());
                if (moduleGrantList.isEmpty()) {
                    moduleGrantList.addAll(roleModuleGrantList);
                } else {
                    for (SysGrantModule m : roleModuleGrantList) {
                        flag = true;
                        for (SysGrantModule e : moduleGrantList) {
                            if (e.getSysmodule().getId().compareTo(m.getSysmodule().getId()) == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            moduleGrantList.add(m);
                        }
                    }
                }
                rolePrgGrantList = sysGrantPrgBean.findBySystemNameAndRoleId(systemName, r.getPid());
                if (prgGrantList.isEmpty()) {
                    prgGrantList.addAll(rolePrgGrantList);
                } else {
                    for (SysGrantPrg p : rolePrgGrantList) {
                        flag = true;
                        for (SysGrantPrg e : prgGrantList) {
                            if (e.getSysprg().getId().compareTo(p.getSysprg().getId()) == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            prgGrantList.add(p);
                        }
                    }
                }
            }
            moduleGrantList.sort((SysGrantModule o1, SysGrantModule o2) -> {
                if (o1.getSysmodule().getSortid() < o2.getSysmodule().getSortid()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            prgGrantList.sort((SysGrantPrg o1, SysGrantPrg o2) -> {
                if (o1.getSysprg().getSortid() < o2.getSysprg().getSortid()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            userManagedBean.setSystemGrantPrgList(prgGrantList);
            for (SysGrantModule m : moduleGrantList) {
                submenu = new DefaultSubMenu(m.getSysmodule().getName());
                submenu.setIcon("icon-th-thumb");
                for (SysGrantPrg p : prgGrantList) {
                    if (p.getPid() == m.getSysmodule().getId()) {
                        menuitem = new DefaultMenuItem(p.getSysprg().getName());
                        menuitem.setIcon("icon-doc-text-1");
                        menuitem.setOutcome(p.getSysprg().getApi());
                        menuitem.setParam("menu", p.getId().toString());
                        submenu.addElement(menuitem);
                    }
                }
                appmenu.addElement(submenu);
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

            menuitem = new DefaultMenuItem("系统维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysname");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("模块维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysmodule");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("功能维护");
            menuitem.setIcon("icon-doc-text");
            menuitem.setOutcome("sysprg");
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

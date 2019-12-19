/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.dao.UsersFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author Alex
 */
@Named(value = "departEditBean")
@ViewScoped
public class DepartEditBean implements Serializable{
    @EJB
    private DepartFacadeLocal depDao;
    @EJB
    private UsersFacadeLocal userDao;
    
    private Integer id;
    private String name;
    
    // текущий пользователь
    private Users currentUser;
//
    private Users user;
    private Integer userId;

    //private boolean createNewDep;
    
    // список пользователей
    private List<Users> userList;
    
    private Depart depSelect;
    
    @Inject
    private DepartsBean depBean;
    @Inject
    private UserBean userInfo; 
    

    /**
     * Creates a new instance of DepartEditBean
     */
    public DepartEditBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    ////////////////////////////////////////////////////
    // methods
    
    @Override
    public String toString() {
        return "DepartEditBean{" + "id=" + id + ", name=" + name + " '}'";
    }
   
    public String onActionOK() {
        onAction();
        return "departlist";
    }
    
    public String onActionSave() {
        onAction();
        return null;
    }
    
    public void onAction() {
       if (depSelect == null) {
            //создаем новый
            Depart depNew = new Depart();
            depNew.setName(name);
            depNew.setUser(userInfo.getUsers());
            
            depDao.create(depNew);
            depSelect = depNew;
        } else {
            depSelect.setName(name);
            depSelect.setUser(userInfo.getUsers());
            
            depDao.edit(depSelect);
        }
    }
    
    @PostConstruct
    private void init() {
        
        //////////////////////////////////////////////
        // по умолчанию пустое подр
        
        depSelect = null;
        
        if (depBean != null && depBean.getDepSelect() != null) {
            depSelect = depBean.getDepSelect();
        }
        
        if (depSelect == null) {
            this.name = "Новое подразделение";
            this.userId = userInfo.getUsers().getId();
            this.user = userInfo.getUsers();

        } else {
            this.id = depSelect.getId();
            this.name = depSelect.getName();
            this.userId = depSelect.getUser().getId();
            this.user = depSelect.getUser();
        }
        
        currentUser = userInfo.getUsers();
//        
//        System.out.println(" ================ is admin: " + currentUser.getAdm());
//        
        userList = userDao.findAll();
//        
//        if (depBean != null) {
//            this.createNewDep = depBean.isCreateNewDep();
//        } else {
//            this.createNewDep = false;
//        }
//        
//        if (depBean != null && !this.createNewDep) {
//            this.id = depBean.getDepSelect().getId();
//            this.name = depBean.getDepSelect().getName();
//            
//            this.createNewDep = depBean.isCreateNewDep();
//            
//            this.userId = depBean.getDepSelect().getUser().getId();
//            this.user = depBean.getDepSelect().getUser();
//
//        }
//        
//        if (this.createNewDep) {
//            this.name = "Новое подразделение";
//            
//            this.userId = userInfo.getUsers().getId();
//            this.user = userInfo.getUsers();
//        }
    }
}

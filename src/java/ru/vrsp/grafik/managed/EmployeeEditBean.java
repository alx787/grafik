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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.dao.EmployeesFacadeLocal;
import ru.vrsp.grafik.dao.PostsFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;
import ru.vrsp.grafik.entity.Posts;

/**
 *
 * @author user
 */
@Named(value = "employeeEditBean")
@ViewScoped
public class EmployeeEditBean implements Serializable {

    @EJB
    private DepartFacadeLocal depDao;
    @EJB
    private EmployeesFacadeLocal empDao;
    @EJB
    private PostsFacadeLocal postDao;
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

    private Employees emplSelect;

    @Inject
    private EmployeesBean emplsBean;
    @Inject
    private UserBean userInfo;

    private Integer id;
    private String name;
    private boolean enabled;
    private Integer boss;
    private Integer post;
    private Integer depart;
    private boolean iamboss;


//    private boolean createNewEmpl;
    private List<Depart> departs;
    private List<Employees> bosses;
    private List<Posts> posts;

    public EmployeeEditBean() {
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

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public Integer getDepart() {
        return depart;
    }

    public void setDepart(Integer depart) {
        this.depart = depart;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public boolean isIamboss() {
        return iamboss;
    }

    public void setIamboss(boolean iamboss) {
        this.iamboss = iamboss;
    }

    public List<Employees> getBosses() {
        return bosses;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "EmployeeEditBean{" + "emplsBean=" + emplsBean + ", id=" + id + ", name=" + name + ", post=" + post + ", enabled=" + enabled + ", boss=" + boss + ", depart=" + depart + '}';
    }
    
    ///////////////////////////////////////////////////////////
    // methods

    public void onAction() {
        if (emplSelect == null) {
            //создаем новый
            Employees empNew = new Employees();

            empNew.setName(this.name);
            empNew.setPostid(postDao.find(this.post));
            empNew.setEnabled(this.enabled);
            empNew.setBoss(this.boss);
            empNew.setDepartid(depDao.find(this.depart));
            empNew.setIamboss(this.iamboss);

            empDao.create(empNew);
            
            emplSelect = empNew;
//            return "employeelist?faces-redirect=true";

        } else {
            //редактируем существующий
            //Employees emplEdit = empDao.find(this.getId());

            emplSelect.setName(this.name);
            emplSelect.setPostid(postDao.find(this.post));
            emplSelect.setEnabled(this.enabled);
            emplSelect.setBoss(this.boss);
            emplSelect.setDepartid(depDao.find(this.depart));
            emplSelect.setIamboss(this.iamboss);

            empDao.edit(emplSelect);
//            return "employeelist?faces-redirect=true";
        }
        //return null;
    }

    public String onActionOK() {
        onAction();
        return "employeelist?faces-redirect=true";
    }
    
    public String onActionSave() {
        onAction();
        return null;
    }
    
    
    @PostConstruct
    private void init() {

        //////////////////////////////////////////////////////
        // по умолчанию - новый график
        emplSelect = null;

        if (emplsBean != null && emplsBean.getEmpSelect() != null) {
            emplSelect = emplsBean.getEmpSelect();
        }

        if (emplSelect == null) {
            // новый сотрудник
            this.name = "Новый сотрудник";
            
            if (emplsBean.getSelectDepartId() != null) {
                this.depart = emplsBean.getSelectDepartId();
            }
        } else {
            this.id = emplSelect.getId();
            this.name = emplSelect.getName();
            this.post = emplSelect.getPostid().getId();
            this.enabled = emplSelect.getEnabled();
            this.depart = emplSelect.getDepartid().getId();
            this.boss = emplSelect.getBoss();
            this.iamboss = emplSelect.getIamboss();
        }

        //get all departs
        departs = depDao.findByUser(userInfo.getUsers());
        //get all bosses
        //bosses = em.createNativeQuery("select id, name, departid, postid, enabled, boss from employees", Employees.class).getResultList();
        bosses = empDao.findAllBossesByDepart(userInfo.getDeparts());
        if (bosses.isEmpty()) {
            bosses = em.createNativeQuery("select id, name, departid, postid, enabled, boss from employees", Employees.class).getResultList();
        }
        //get all posts
        posts = postDao.findAll();
    }
}

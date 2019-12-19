/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ru.vrsp.grafik.dao.EmployeesFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;
import ru.vrsp.grafik.util.SortEmplsByBoss;

/**
 *
 * @author user
 */
@Named(value = "rasstanovka")
@RequestScoped
public class Rasstanovka {
    @EJB
    private EmployeesFacadeLocal empDao;
    
    private List<Employees> empList;
    private List<Depart> depList;
    private Integer selectDepartId;
    
    private Collection<Object> empSelect;
    
    @Inject
    private UserBean userInfo;

    /**
     * Creates a new instance of Rasstanovka
     */
    public Rasstanovka() {
    }

    public List<Employees> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employees> empList) {
        this.empList = empList;
    }

    public Collection<Object> getEmpSelect() {
        return empSelect;
    }

    public void setEmpSelect(Collection<Object> empSelect) {
        this.empSelect = empSelect;
    }

    public List<Depart> getDepList() {
        return depList;
    }

    public void setDepList(List<Depart> depList) {
        this.depList = depList;
    }

    public Integer getSelectDepartId() {
        return selectDepartId;
    }

    public void setSelectDepartId(Integer selectDepartId) {
        this.selectDepartId = selectDepartId;
    }
    
    ///////////////////////////////////////////////////////////
    // methods
    public void refreshTable() {
        SortEmplsByBoss sortEmpl = null;
        empList.clear();
        
        if (selectDepartId == null) {
            sortEmpl = new SortEmplsByBoss(empDao.findAllByEnabledByDeparts(userInfo.getDeparts()));
        } else {
            for (Depart depSingle : depList ) {
                if (depSingle.getId() == selectDepartId) {
                    sortEmpl = new SortEmplsByBoss(empDao.findAllByEnabledByDeparts(Arrays.asList(depSingle)));
                }
            }
        }
        
        if (sortEmpl != null) {
            sortEmpl.sortEmpls();
            empList = sortEmpl.getEmplList();
            //расставим имена боссов
            for (Employees emp : empList) {
                emp.setBossName(empDao.find(emp.getBoss()).getName());
            }
       }
        
    }
    
    @PostConstruct
    private void init() {
        SortEmplsByBoss sortEmpl = new SortEmplsByBoss(empDao.findAllByEnabledByDeparts(userInfo.getDeparts()));
        sortEmpl.sortEmpls();
        //sortEmpl.sortRasstanovka();
        empList = sortEmpl.getEmplList();
        depList = userInfo.getDeparts();
            //расставим имена боссов
            for (Employees emp : empList) {
                emp.setBossName(empDao.find(emp.getBoss()).getName());
            }
    }
}

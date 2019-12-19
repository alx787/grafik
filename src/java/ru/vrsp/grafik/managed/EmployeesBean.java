/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.dao.EmployeesFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;


/**
 *
 * @author user
 */
@Named(value = "employeesBean")
@RequestScoped
public class EmployeesBean implements Serializable {
    @EJB
    private EmployeesFacadeLocal empDao;
    @EJB
    private DepartFacadeLocal depDao;
    
    private List<Employees> empList = null;
    private Collection<Object> empCollectSelect = null;
    private Employees empSelect = null;
    // флаг помечать всех (уволенных)
    private boolean showAll;
    
    // подразделения
    private List<Depart> depList;
    // выбранное подразделение
    private Depart selectDepart;
    // выбранное идентификатор подразделения
    private Integer selectDepartId;
    
    @Inject
    private UserBean userInfo;
    /**
     * Creates a new instance of employeeBean
     */
    public EmployeesBean() {
    }

    public List<Employees> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employees> empList) {
        this.empList = empList;
    }

    public Collection<Object> getEmpCollectSelect() {
        return empCollectSelect;
    }

    public void setEmpCollectSelect(Collection<Object> empCollectSelect) {
        this.empCollectSelect = empCollectSelect;
    }

    @Override
    public String toString() {
        return "EmployeesBean{" + "empDao=" + empDao + ", empList=" + empList + ", empCollectSelect=" + empCollectSelect + ", empSelect=" + empSelect + '}';
    }

    public Employees getEmpSelect() {
        return empSelect;
    }

    public boolean getShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public List<Depart> getDepList() {
        return depList;
    }

    public void setDepList(List<Depart> depList) {
        this.depList = depList;
    }

    public Depart getSelectDepart() {
        return selectDepart;
    }

    public void setSelectDepart(Depart selectDepart) {
        this.selectDepart = selectDepart;
    }

    public Integer getSelectDepartId() {
        return selectDepartId;
    }

    public void setSelectDepartId(Integer selectDepartId) {
        this.selectDepartId = selectDepartId;
    }

    ////////////////////////////////////////////////////////////////////
    // methods
    public void refreshEmployees() {
        
        selectDepart = depDao.find(selectDepartId);
        
        //очистка
        empList.clear();
        
        // все доступные подразделения
        if (selectDepart == null && !depList.isEmpty()) {
            if (showAll) {
                empList = empDao.findAllByDeparts(depList);
            } else {
                empList = empDao.findAllByEnabledByDeparts(depList);
            }
        }

        if (selectDepart != null) {
            if (showAll) {
                empList = empDao.findByDepart(selectDepart);
            } else {
                empList = empDao.findByEnabledByDepart(selectDepart);
            }
        }
        
        
//        if (userInfo.isAdmin()) {
//            if (showAll) {
//                empList = empDao.findAll();
//            } else {
//                empList = empDao.findByEnabled();
//            }
//        } else {
//            if (showAll) {
//                empList = empDao.findByDepart(selectDepart);
//            } else {
//                empList = empDao.findByEnabledByDepart(selectDepart);
//            }
//        }
    }
       
    public String addEmployee() {
        
        if (this.selectDepartId == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Выберите подразделение"));
            return null;
        }
        
        empSelect = null;
        return "employeeedit";
    }

    public String delEmployee() {
        for (Employees empSingle : empList ) {
            if (empSingle.getMarked()) {
                empSingle.setEnabled(false);
                empDao.edit(empSingle);
            }
        }
        return "employeelist";
    }
    
    public String editEmployee() {
        String emplIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("emplId");
        //параметр действия
        Integer emplIdInt = 0;
        
        try {
            emplIdInt = Integer.parseInt(emplIdStr);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
        
        empSelect = empDao.find(emplIdInt);
        
        return "employeeedit";
    }
    
    @PostConstruct
    private void init() {
        if (userInfo.getUsers().getAdm()) {
            depList = depDao.findAllDeparts();
        } else {
            depList = depDao.findByUser(userInfo.getUsers());
        }
        
        showAll = userInfo.getShowAll();
        
        //инициализируем пустым списком
        empList = new ArrayList<Employees>();
        
        
        refreshEmployees();
        
//        if (userInfo.isAdmin()) {
//            empList = empDao.findByEnabled();
//        } else {
//            empList = empDao.findByDepart(userInfo.getDepart());
//        }
    }
    
    @PreDestroy
    private void saveParams() {
        userInfo.setShowAll(showAll);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.entity.Depart;

/**
 *
 * @author Alex
 */
@Named(value = "departsBean")
@RequestScoped
public class DepartsBean implements Serializable {
    @EJB
    private DepartFacadeLocal depDao;
    
    
    private List<Depart> depList = null;
    private Collection<Object> depCollectSelect = null;
    private Depart depSelect = null;
    //create new flag
    private Boolean createNewDep;

    /**
     * Creates a new instance of DepartsBean
     */
    public DepartsBean() {
    }

    public List<Depart> getDepList() {
        return depList;
    }

    public void setDepList(List<Depart> depList) {
        this.depList = depList;
    }

    public Collection<Object> getDepCollectSelect() {
        return depCollectSelect;
    }

    public void setDepCollectSelect(Collection<Object> depCollectSelect) {
        this.depCollectSelect = depCollectSelect;
    }

    public Depart getDepSelect() {
        return depSelect;
    }

    public Boolean isCreateNewDep() {
        return createNewDep;
    }
    
    public String addDepart() {
        depSelect = null;
        return "departedit";
    }

    public String editDepart() {
        String depIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("depId");
        //параметр действия
        Integer depIdInt = 0;
        
        try {
            depIdInt = Integer.parseInt(depIdStr);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
        
        depSelect = depDao.find(depIdInt);
        
        return "departedit";
    }
 
     
    @PostConstruct
    private void init() {
        this.createNewDep = false;
        depList = depDao.findAllDeparts();
        
    }
}

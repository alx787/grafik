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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ru.vrsp.grafik.dao.GraftemplateFacadeLocal;
import ru.vrsp.grafik.entity.Graftemplate;

/**
 *
 * @author Alex
 */
@Named(value = "templatesBean")
@RequestScoped
public class TemplatesBean implements Serializable {
    @EJB
    private GraftemplateFacadeLocal templDao;

    private List<Graftemplate> templList;
    private Collection<Object> templCollectSelect;
    //selected grafik
    private Graftemplate templSelect;
    
    @Inject
    private UserBean userInfo;

    /**
     * Creates a new instance of TemplatesBean
     */
    public TemplatesBean() {
    }

    public List<Graftemplate> getTemplList() {
        return templList;
    }

    public void setTemplList(List<Graftemplate> templList) {
        this.templList = templList;
    }

    public Collection<Object> getTemplCollectSelect() {
        return templCollectSelect;
    }

    public void setTemplCollectSelect(Collection<Object> templCollectSelect) {
        this.templCollectSelect = templCollectSelect;
    }

    public Graftemplate getTemplSelect() {
        return templSelect;
    }

    public void setTemplSelect(Graftemplate templSelect) {
        this.templSelect = templSelect;
    }
    
    ////////////////////////////////////////////////////
    // methods
    
    public String addTemplate() {
        templSelect = null;
        return "templateedit";
    }

    public void delTemplate() {
        // удаление помеченных элементов
        // делаем лист
        List<Graftemplate> copyTemplList = new ArrayList<>();
        // собираем туда помеченные элементы
        for (Graftemplate templSingle : templList) {
            if (templSingle.isMarked()) {
                copyTemplList.add(templSingle);
            }
        }
        // удаляем
        for (Graftemplate templSingle : copyTemplList) {
            templList.remove(templSingle);
            templDao.delete(templSingle);
        }
        
    }
    
    public String editTemplate() {
        templSelect = null;
        String templIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("templId");
        //параметр действия
        Integer templIdInt = 0;
        
        try {
            templIdInt = Integer.parseInt(templIdStr);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
        
        templSelect = templDao.find(templIdInt);
        if (templSelect != null) {
            return "templateedit";
        }
        
        return null;
    }
    
    @PostConstruct
    private void init() {
        templSelect = null;
        templList = templDao.findAll();
    }
    
}

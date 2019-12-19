/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ru.vrsp.grafik.dao.GrafheadFacadeLocal;
import ru.vrsp.grafik.entity.Grafhead;

/**
 *
 * @author Alex
 */
@Named(value = "grafiksBean")
@RequestScoped
public class GrafiksBean implements Serializable {
    @EJB
    private GrafheadFacadeLocal grafheadDao;
    
    private List<Grafhead> grafikList;
    private Collection<Object> grafikCollectSelect;
    //selected grafik - выбранный график для редактирования
    private Grafhead grafikSelect;
    //period
    private Date periodBegin;
    private Date periodEnd;
    //create new flag
//    private Boolean createNewGrafik;
    
    @Inject
    private UserBean userInfo;

    /**
     * Creates a new instance of GrfiksBean
     */
    public GrafiksBean() {
    }

    public List<Grafhead> getGrafikList() {
        return grafikList;
    }

    public void setGrafikList(List<Grafhead> grafikList) {
        this.grafikList = grafikList;
    }

    public Collection<Object> getGrafikCollectSelect() {
        return grafikCollectSelect;
    }

    public void setGrafikCollectSelect(Collection<Object> grafikCollectSelect) {
        this.grafikCollectSelect = grafikCollectSelect;
    }

    public Date getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Date periodBegin) {
        this.periodBegin = periodBegin;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }
    
    public String refreshGrafik() {
        grafikList = grafheadDao.findRange(periodBegin, periodEnd, userInfo);
        return null;
    }

    public Grafhead getGrafikSelect() {
        return grafikSelect;
    }

    public void setGrafikSelect(Grafhead grafikSelect) {
        this.grafikSelect = grafikSelect;
    }

    ///////////////////////////////////////////////////////
    // methods
    
    public String addGrafik() {
        grafikSelect = null;
        return "grafikedit";
    }

    public String delGrafik() {
        return null;
    }
    
    public String editGrafik() {
        String grafikIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grafikId");
        //параметр действия
        Integer grafikIdInt = 0;
        try {
            grafikIdInt = Integer.parseInt(grafikIdStr);
            grafikSelect = grafheadDao.find(grafikIdInt);
            return "grafikedit";
        } catch (NumberFormatException numberFormatException) {
        }
        
        return null;
    }
    
    @PostConstruct
    private void init() {
        grafikSelect = null;
        
        
//        // ============== установка времени начало
//        Date tekDate = new Date();
//        GregorianCalendar gregCalendar = new GregorianCalendar();
//        gregCalendar.setTime(tekDate);
//        
//        if (periodBegin == null) {
//            //gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, gregCalendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
//            gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
//            periodBegin = gregCalendar.getTime();
//        }
//        
//        if (periodEnd == null) {
//            gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, gregCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
//            periodEnd = gregCalendar.getTime();
//        }
//        // ============== установка времени конец
        
        
//        // ============== удалить начало
//        gregCalendar.set(2014, 0, 1);
//        periodBegin = gregCalendar.getTime();
//        gregCalendar.set(2014, 6, 1);
//        periodEnd = gregCalendar.getTime();
//        // ============== удалить конец
        
        periodBegin = userInfo.getPeriodBegin();
        periodEnd = userInfo.getPeriodEnd();
        

        // ============== выборка для таблицы
        grafikList = grafheadDao.findRange(periodBegin, periodEnd, userInfo);
    }
    
    @PreDestroy
    private void saveParams() {
        userInfo.setPeriodBegin(periodBegin);
        userInfo.setPeriodEnd(periodEnd);
    }
}

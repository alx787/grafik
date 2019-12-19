/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vrsp.grafik.managed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.dao.EmployeesFacadeLocal;
import ru.vrsp.grafik.dao.GrafbodyFacadeLocal;
import ru.vrsp.grafik.dao.GrafheadFacadeLocal;
import ru.vrsp.grafik.dao.GraftemplateFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;
import ru.vrsp.grafik.entity.Grafbody;
import ru.vrsp.grafik.entity.Grafhead;
import ru.vrsp.grafik.entity.Graftemplate;
import ru.vrsp.grafik.util.GrafToXLS;
import ru.vrsp.grafik.util.SortEmplsByBoss;

/**
 *
 * @author Alex
 */
@Named(value = "grafikEditBean")
@ViewScoped
public class GrafikEditBean implements Serializable {

    @EJB
    private GrafheadFacadeLocal grafheadDao;
    @EJB
    private GrafbodyFacadeLocal grafbodyDao;
    @EJB
    private EmployeesFacadeLocal empDao;
    @EJB
    private GraftemplateFacadeLocal templDao;
    @EJB
    private DepartFacadeLocal depDao;

    private List<Grafbody> grafList;
    private String grafName;
    private Integer grafYear;
    private Integer grafMonth;
    // ектущий график ( =null - новый график)
    private Grafhead currentGrafik;

    // список сотрудников для подбора
    private List<Employees> employeesList;
    // список сотрудников для выбора бригадира
    private List<Employees> brigList;
    // флаг - отбор подбора по бригадиру
    private Boolean selectOnBrig;
    // бригадир для отбора
    private Integer selectBrig;
    // параметры автозаполнения
    // шаблоны графиков
    private List<Graftemplate> templList;
    // выбранный шаблон
    private Integer templIdSelect;

    // выбранное подразделение
    private Depart selectDepart;
    // 
    private Integer selectDepartId;
    //
    private List<Depart> depList;
    
    @Inject
    private GrafiksBean grafikBean;
    @Inject
    private UserBean userInfo;

    /**
     * Creates a new instance of GrafikEditBean
     */
    public GrafikEditBean() {
    }

    public List<Grafbody> getGrafList() {
        return grafList;
    }

    public void setGrafList(List<Grafbody> grafList) {
        this.grafList = grafList;
    }

    public String getGrafName() {
        return grafName;
    }

    public void setGrafName(String grafName) {
        this.grafName = grafName;
    }

    public Integer getGrafYear() {
        return grafYear;
    }

    public void setGrafYear(Integer grafYear) {
        this.grafYear = grafYear;
    }

    public Integer getGrafMonth() {
        return grafMonth;
    }

    public void setGrafMonth(Integer grafMonth) {
        this.grafMonth = grafMonth;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public List<Employees> getBrigList() {
        return brigList;
    }

    public void setBrigList(List<Employees> brigList) {
        this.brigList = brigList;
    }

    public Boolean getSelectOnBrig() {
        return selectOnBrig;
    }

    public void setSelectOnBrig(Boolean selectOnBrig) {
        this.selectOnBrig = selectOnBrig;
    }

    public Integer getSelectBrig() {
        return selectBrig;
    }

    public void setSelectBrig(Integer selectBrig) {
        this.selectBrig = selectBrig;
    }

    public List<Graftemplate> getTemplList() {
        return templList;
    }

    public void setTemplList(List<Graftemplate> templList) {
        this.templList = templList;
    }

    public Integer getTemplIdSelect() {
        return templIdSelect;
    }

    public void setTemplIdSelect(Integer templIdSelect) {
        this.templIdSelect = templIdSelect;
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

    public List<Depart> getDepList() {
        return depList;
    }

    public void setDepList(List<Depart> depList) {
        this.depList = depList;
    }

    ///////////////////////////////////////////////////
    // methods
    ///////////////////////////////////////////////////
    public String onActionOK() {
        saveGrafik();
        return "grafiklist";
    }
    
    public String onActionSave() {
        saveGrafik();
        return null;
    }
    
    private void saveGrafik() {

        GregorianCalendar gregCalendar = new GregorianCalendar();
        gregCalendar.set(this.grafYear, this.grafMonth - 1, 1);
        
        selectDepart = depDao.find(selectDepartId);

        // сохранение графика
        // сохранение нового
        if (currentGrafik == null) {
            // 1 - записать заголовок
            Grafhead grafheadNew = new Grafhead();
            grafheadNew.setPeriod(gregCalendar.getTime());
            grafheadNew.setComent(this.grafName);
            grafheadNew.setUserid(userInfo.getUsers());
            
            grafheadNew.setDepartid(selectDepart);

            grafheadDao.create(grafheadNew);

            // 2 - записать таблицу
            for (Grafbody grafbodySingle : grafList) {
                grafbodySingle.setHeadid(grafheadNew);
                grafbodyDao.create(grafbodySingle);
            }
            
            currentGrafik = grafheadNew;
        }

        // сохранение ранее созданных табл
        if (currentGrafik != null) {
            ////////////////////////////////////////////////////////
            // сохраняем заголовок

            currentGrafik.setPeriod(gregCalendar.getTime());
            currentGrafik.setComent(this.grafName);
            currentGrafik.setDepartid(selectDepart);
            
//            currentGrafik.setGrafbodyList(grafList);

//            System.out.println(" ==================== user info " + userInfo);
            grafheadDao.edit(currentGrafik);

            ////////////////////////////////////////////////////////
            // удалить удаленные строки
            List<Grafbody> currGrafbody = grafbodyDao.findByHead(currentGrafik);

            for (Grafbody grafbodySingle : currGrafbody) {
                if (!grafList.contains(grafbodySingle)) {
                    grafbodyDao.delete(grafbodySingle);
                }
            }
            ////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////
            // сохраняем строки
            for (Grafbody grafbodySingle : grafList) {
                if (grafbodySingle.getId() != null) {
                    // если существующий элемент то сохраняем
                    grafbodyDao.edit(grafbodySingle);
                } else {
                    // если новый элемент то создаем
                    grafbodyDao.create(grafbodySingle);
                }
            }
        }

        //return "grafiklist";
    }

    // добавить строку с сотрудником в график
    public void addEmployees() {
        if (employeesList != null) {
            for (Employees employeesSingle : employeesList) {
                if (employeesSingle.getMarked()) {
                    //grafList.add(new Grafbody(0, grafikBean.getGrafikSelect(), employeesSingle));
                    grafList.add(new Grafbody(null, currentGrafik, employeesSingle));
                }
            }
            employeesList.clear();
        }
    }

    public void delEmployees() {
        // удаление помеченных элементов
        // делаем лист
        List<Grafbody> copyGrafList = new ArrayList<>();
        // собираем туда помеченные элементы
        for (Grafbody grafbodyLine : grafList) {
            if (grafbodyLine.getMarked()) {
                copyGrafList.add(grafbodyLine);
            }
        }
        // удаляем
        for (Grafbody copyGrafbodyLine : copyGrafList) {
            grafList.remove(copyGrafbodyLine);
        }
    }

    // обновить список сотрудников при добавлении
    public void showAddEmplPanel() {
        if (employeesList == null) {
            employeesList = new ArrayList<>();
        }

        employeesList.removeAll(employeesList);

        if (selectBrig != null) {
            
//            if (userInfo.isAdmin()) {
                employeesList = empDao.findByBoss(selectBrig);
//            } else{
               // проверить на принадлежность
//                Employees selectBrigEmpl = empDao.find(selectBrig);
//                if (userInfo.getDeparts().contains(selectBrigEmpl.getDepartid())) {
//                    employeesList = empDao.findByBoss(selectBrig);
//                }
//                employeesList = empDao.findAllByDeparts(userInfo.getDeparts());
//            }
        } else {
            if (userInfo.isAdmin()) {
                employeesList = empDao.findAll();
            } else {
                employeesList = empDao.findAllByEnabledByDeparts(userInfo.getDeparts());
            }
            //employeesList = empDao.findAll();
            //employeesList = empDao.findAllByEnabledByDeparts(userInfo.getDeparts());
        }

        // удалим повторяющиеся
        for (Grafbody grafbodyLine : grafList) {
            if (employeesList.contains(grafbodyLine.getEmployeeid())) {
                employeesList.remove(grafbodyLine.getEmployeeid());
            }
        }
    }

    // заполняем по графику
    public String fillOnTemplate() {
        GregorianCalendar gregCalendar = new GregorianCalendar();
        gregCalendar.set(this.grafYear, this.grafMonth - 1, 1);

//        Integer startDay = gregCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        // максимальный день в месяце
        Integer endDay = gregCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        if (templIdSelect != null) {
            Graftemplate emplSelect = templDao.find(templIdSelect);
            if (emplSelect != null) {
                for (Grafbody singleGrafbody : grafList) {
                    if (singleGrafbody.getMarked()) {
                        singleGrafbody.setDay1(emplSelect.getDay1());
                        singleGrafbody.setDay2(emplSelect.getDay2());
                        singleGrafbody.setDay3(emplSelect.getDay3());
                        singleGrafbody.setDay4(emplSelect.getDay4());
                        singleGrafbody.setDay5(emplSelect.getDay5());
                        singleGrafbody.setDay6(emplSelect.getDay6());
                        singleGrafbody.setDay7(emplSelect.getDay7());
                        singleGrafbody.setDay8(emplSelect.getDay8());
                        singleGrafbody.setDay9(emplSelect.getDay9());
                        singleGrafbody.setDay10(emplSelect.getDay10());

                        singleGrafbody.setDay11(emplSelect.getDay11());
                        singleGrafbody.setDay12(emplSelect.getDay12());
                        singleGrafbody.setDay13(emplSelect.getDay13());
                        singleGrafbody.setDay14(emplSelect.getDay14());
                        singleGrafbody.setDay15(emplSelect.getDay15());
                        singleGrafbody.setDay16(emplSelect.getDay16());
                        singleGrafbody.setDay17(emplSelect.getDay17());
                        singleGrafbody.setDay18(emplSelect.getDay18());
                        singleGrafbody.setDay19(emplSelect.getDay19());
                        singleGrafbody.setDay20(emplSelect.getDay20());

                        singleGrafbody.setDay21(emplSelect.getDay21());
                        singleGrafbody.setDay22(emplSelect.getDay22());
                        singleGrafbody.setDay23(emplSelect.getDay23());
                        singleGrafbody.setDay24(emplSelect.getDay24());
                        singleGrafbody.setDay25(emplSelect.getDay25());
                        singleGrafbody.setDay26(emplSelect.getDay26());
                        singleGrafbody.setDay27(emplSelect.getDay27());
                        singleGrafbody.setDay28(emplSelect.getDay28());

                        if (endDay >= 29) {
                            singleGrafbody.setDay29(emplSelect.getDay29());
                        } else {
                            singleGrafbody.setDay29("");
                        }

                        if (endDay >= 30) {
                            singleGrafbody.setDay30(emplSelect.getDay30());
                        } else {
                            singleGrafbody.setDay30("");
                        }

                        if (endDay >= 31) {
                            singleGrafbody.setDay31(emplSelect.getDay31());
                        } else {
                            singleGrafbody.setDay31("");
                        }
                    }
                }

            }
        }
        return null;
    }

    // скачать график
    public String downloadGrafik() {

        if (currentGrafik == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Сохраните график ...", null));
            // новый график - выходим
            return null;
        }
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.reset();

        try {

            String filename = "output" + userInfo.getId().toString() + ".xls";
            String filepath = "с:\\";
            
            List<Grafbody> currentGrafbody = grafbodyDao.findByHead(currentGrafik);
            //Collections.sort(currentGrafbody);
            sortByBrig(currentGrafbody);
            
            GrafToXLS exportToExcel = new GrafToXLS(currentGrafik, currentGrafbody, userInfo, filename, filepath);
            if (!exportToExcel.createXLS()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ошибка сохранения графика ...", null));
                // новый график - выходим
                return null;
            }
            
            File xlsFile = new File(filepath + filename);
            PrintWriter out = response.getWriter();
            FileInputStream input;
            try {
                input = new FileInputStream(filepath + filename);
            } catch (FileNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Файл не найден ...", null));
                return null;
            }

            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=grafik.xls");
            response.setContentLength((int) xlsFile.length());

            try {
                int read = 0;
                while ((read = input.read()) != -1) {
                    out.write(read);
                }
            } finally {
                input.close();
                out.flush();
                out.close();
            }

            facesContext.responseComplete();

        } catch (IOException e) {

        }
        return null;
    }
    
    public void onSortByBrig () {
        sortByBrig(grafList);
    }

    // сортировка сотрудников по бригадирам
    private void sortByBrig(List<Grafbody> oldGrafList) {
        // создание списка сотрудников
        List<Employees> listEmpl = new ArrayList<>();
        for (Grafbody grafBodyRow : oldGrafList) {
            listEmpl.add(grafBodyRow.getEmployeeid());
        }
        
        // выполнение сортировки
        SortEmplsByBoss sortClass = new SortEmplsByBoss(listEmpl);
        sortClass.sortEmpls();
        listEmpl = sortClass.getEmplList();
        
        // переставим строки в соответствии с сортировкой
        List<Grafbody> newGrafList = new ArrayList<>();
        for (Employees emp : listEmpl) {
            for (Grafbody grafBodyRow : oldGrafList) {
                if (emp == grafBodyRow.getEmployeeid()) {
                    newGrafList.add(grafBodyRow);
                }
            }
        }
        
        oldGrafList.clear();
        for (Grafbody grafBodyRow : newGrafList) {
            oldGrafList.add(grafBodyRow);
        }
        
    }

    @PostConstruct
    private void init() {
        depList = userInfo.getDeparts();
        //////////////////////////////////////////////////////
        // по умолчанию - новый график
        currentGrafik = null;

        if (grafikBean != null && grafikBean.getGrafikSelect() != null) {
            currentGrafik = grafikBean.getGrafikSelect();
        }

        GregorianCalendar gregCalendar = new GregorianCalendar();

        //////////////////////////////////////////////////////
        // заполнение полей
        if (currentGrafik == null) {
            this.grafName = "Новый график";
            gregCalendar.setTime(new Date());
            //заполняем табл часть
            grafList = new ArrayList<Grafbody>();
        } else {
            this.grafName = currentGrafik.getComent();
            this.selectDepartId = currentGrafik.getDepartid().getId();
            this.selectDepart = currentGrafik.getDepartid();
            gregCalendar.setTime(currentGrafik.getPeriod());
            //заполняем табл часть
            grafList = grafbodyDao.findByHead(currentGrafik);

            //расчет итогового количества часов
//            System.out.println(" ================================ ");
//            for (Grafbody sGr: grafList) {
//                System.out.println(" ============== " + sGr.getTotalHours());
//            }
        }

        this.grafMonth = gregCalendar.get(GregorianCalendar.MONTH) + 1;
        this.grafYear = gregCalendar.get(GregorianCalendar.YEAR);

        //this.brigList = empDao.findAllBosses();
        this.brigList = empDao.findAllBossesByDepart(this.depList);
        
        this.templList = templDao.findAll();
        //////////////////////////////////////////////////////
        // параметры автозаполнения по умолчанию

        gregCalendar.set(this.grafYear, this.grafMonth - 1, 1);

    }
}

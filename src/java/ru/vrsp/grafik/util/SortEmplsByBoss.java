
package ru.vrsp.grafik.util;

import java.util.ArrayList;
import java.util.List;
import ru.vrsp.grafik.entity.Employees;

/**
 *
 * @author Alex
 */
public class SortEmplsByBoss {
    
    private List<Employees> emplList;
    private List<Employees> completeList = null;
    private List<Integer> brigListId = null;

    public SortEmplsByBoss() {
    }

    public SortEmplsByBoss(List<Employees> emplList) {
        this.emplList = emplList;
    }

    public List<Employees> getEmplList() {
        return emplList;
    }

    public void setEmplList(List<Employees> emplList) {
        this.emplList = emplList;
    }

    
    ///////////////////////////////////////////////////
    // methods
    public void sortEmpls() {
        
        if (emplList == null || emplList.isEmpty()) {
            return;
        }
        
        // список начальников
        List<Employees> bossesList = new ArrayList<>();
        // список бригадиров
        List<Employees> brigList = new ArrayList<>();
        // список всех остальных сотрудников
        List<Employees> sotrList = new ArrayList<>();
        
        for (Employees emp : emplList) {
            if (emp.getId() == emp.getBoss()) {
                bossesList.add(emp);
            } else {
                if (emp.getIamboss()) {
                    brigList.add(emp);
                } else {
                    sotrList.add(emp);
                }
            }
        }
        
        completeList = new ArrayList<>();
        for (Employees emp : bossesList) {
            completeList.add(emp);
        }
        
        for (Employees emp : brigList) {
            completeList.add(emp);
            for (Employees sotr : sotrList) {
                if (sotr.getBoss() == emp.getId() && !completeList.contains(sotr)) {
                    completeList.add(sotr);
                }
            }
        }
        
        //добавим в конец оставшихся
        for (Employees emp : emplList) {
            if (!completeList.contains(emp)) {
                completeList.add(emp);
            }
        }
        
        emplList = completeList;
    }
    
    public void sortRasstanovka() {
        if (emplList == null || emplList.isEmpty()) {
            return;
        }
        
        // список начальников
        List<Employees> bossesList = new ArrayList<>();
        // копия
        List<Employees> tList = emplList;
        
        
        completeList = new ArrayList<>();

        for (Employees emp : tList) {
            if (emp.getId() == emp.getBoss()) {
                bossesList.add(emp);
            }
        }
        
        for (Employees emp : bossesList) {
            completeList.add(emp);
            tList.remove(emp);
            recursFind(emp, tList);
        }

    }
    
    private void recursFind(Employees brig, List<Employees> tList) {
        List<Employees> ttList = tList;
        
        for (Employees emp : tList) {
            
            if (emp.getBoss() == brig.getId() && !tList.contains(emp)) {
                completeList.add(emp);
                ttList.remove(emp);
            }
            
            recursFind(emp, ttList);
        }
    }
        
}

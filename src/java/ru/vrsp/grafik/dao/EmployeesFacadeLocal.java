/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;

/**
 *
 * @author user
 */
@Local
public interface EmployeesFacadeLocal {

    
    void create(Employees employees);
//
    void edit(Employees employees);
//
//    void remove(Employees employees);

    Employees find(Integer id);
    List<Employees> findAll();
    List<Employees> findByEnabled();
    List<Employees> findByEnabledByDepart(Depart departId);
    List<Employees> findByDepart(Depart departId);
    List<Employees> findByBoss(Integer bossId);

    List<Employees> findAllByDeparts(List<Depart> departs);
    List<Employees> findAllByEnabledByDeparts(List<Depart> departs);
    
    
    List<Employees> findAllBosses();
    List<Employees> findAllBossesByDepart(List<Depart> departs);
    
}

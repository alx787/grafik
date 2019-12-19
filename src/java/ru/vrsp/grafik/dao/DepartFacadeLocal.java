/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author user
 */
@Local
public interface DepartFacadeLocal {

    void create(Depart depart);
    void edit(Depart depart);
//
//    void remove(Depart depart);
//
//    Depart find(Object id);

    List<Depart> findAllDeparts();
    List<Depart> findByUser(Users user);
    Depart find(Integer id);
//    List<Depart> findRange(int[] range);
//
//    int count();
    
}

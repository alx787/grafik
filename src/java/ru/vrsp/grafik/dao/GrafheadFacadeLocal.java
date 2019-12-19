/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Grafhead;
import ru.vrsp.grafik.managed.UserBean;

/**
 *
 * @author user
 */
@Local
public interface GrafheadFacadeLocal {

    void create(Grafhead grafhead);
//
    void edit(Grafhead grafhead);
//
//    void remove(Grafhead grafhead);
//
    Grafhead find(Integer id);

    List<Grafhead> findAll();

    List<Grafhead> findRange(Date dateBeg, Date dateEnd, UserBean user);
//
//    int count();
//    
}

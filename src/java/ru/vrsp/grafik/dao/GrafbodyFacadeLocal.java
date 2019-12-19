/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Grafbody;
import ru.vrsp.grafik.entity.Grafhead;

/**
 *
 * @author user
 */
@Local
public interface GrafbodyFacadeLocal {

    void create(Grafbody grafbody);
    void edit(Grafbody grafbody);
    void delete(Grafbody grafbody);
    List<Grafbody> findByHead(Grafhead headId);
    List<Grafbody> findAll();
//
//    List<Grafbody> findRange(int[] range);
//
//    int count();
    
}

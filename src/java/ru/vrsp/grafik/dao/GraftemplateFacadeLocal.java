/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Graftemplate;

/**
 *
 * @author Alex
 */
@Local
public interface GraftemplateFacadeLocal {
    List<Graftemplate> findAll();
    Graftemplate find(Integer id);
    void create(Graftemplate graftemplate);
    void edit(Graftemplate graftemplate);
    void delete(Graftemplate graftemplate);
}

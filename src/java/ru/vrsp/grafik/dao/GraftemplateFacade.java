/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.vrsp.grafik.entity.Graftemplate;

/**
 *
 * @author Alex
 */
@Stateless
public class GraftemplateFacade implements GraftemplateFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

    @Override
    public List<Graftemplate> findAll() {
        return em.createNamedQuery("Graftemplate.findAll").getResultList();
    }

    @Override
    public Graftemplate find(Integer id) {
        return (Graftemplate)em.createNamedQuery("Graftemplate.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public void create(Graftemplate graftemplate) {
        em.persist(graftemplate);
    }

    @Override
    public void edit(Graftemplate graftemplate) {
        em.merge(graftemplate);
    }

    @Override
    public void delete(Graftemplate graftemplate) {
        //em.getTransaction().begin();
        //Graftemplate templToBeRemove = (Graftemplate)em.find(Graftemplate.class, graftemplate.getId());
        //em.remove(templToBeRemove);
        //em.getTransaction().commit();
        
        Graftemplate templToBeRemove = (Graftemplate)em.merge(graftemplate);
        em.remove(templToBeRemove);
        
    }
    
}

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
import javax.persistence.Query;
import ru.vrsp.grafik.entity.Grafbody;
import ru.vrsp.grafik.entity.Grafhead;

/**
 *
 * @author user
 */
@Stateless
public class GrafbodyFacade implements GrafbodyFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;
    private Query query;

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
    public GrafbodyFacade() {
//        super(Grafbody.class);
    }

   @Override
    public void create(Grafbody grafbody) {
        em.persist(grafbody);
    }
    
    @Override
    public void edit(Grafbody grafbody) {
        em.merge(grafbody);
    }

    @Override
    public void delete(Grafbody grafbody) {
        Grafbody grafbodyToRemove = em.merge(grafbody);
        em.remove(grafbodyToRemove);
    }

    @Override
    public List<Grafbody> findByHead(Grafhead headId) {
        query = em.createNamedQuery("Grafbody.findByHeadId");
        query.setParameter("headid", headId);
        return query.getResultList();
    }
    
    @Override
    public List<Grafbody> findAll() {
        return em.createNamedQuery("Grafbody.findAll").getResultList();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Users;
//import ru.vrsp.grafik.entity.Depart;

/**
 *
 * @author user
 */
@Stateless
//public class DepartFacade extends AbstractFacade<Depart> implements DepartFacadeLocal {
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DepartFacade implements DepartFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }

    public DepartFacade() {
        //super(Depart.class);
    }

    @Override
    public void create(Depart depart) {
        em.persist(depart);
    }
    
    @Override
    public void edit(Depart depart) {
        em.merge(depart);
    }
    
    @Override
    public List<Depart> findAllDeparts() {
        return em.createNamedQuery("Depart.findAll").getResultList();
    }

    @Override
    public List<Depart> findByUser(Users user) {
        Query depQuery = em.createNamedQuery("Depart.findByUser");
        depQuery.setParameter("user", user);
        return depQuery.getResultList();
    }
    
    @Override
    public Depart find(Integer id) {
       Query depQuery = em.createNamedQuery("Depart.findById");
        depQuery.setParameter("id", id);
        List<Depart> resultDepQuery = depQuery.getResultList();
        if (!resultDepQuery.isEmpty()) {
            return resultDepQuery.get(0);
        }
        return null;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.vrsp.grafik.entity.Grafhead;
import ru.vrsp.grafik.managed.UserBean;

/**
 *
 * @author user
 */
@Stateless
public class GrafheadFacade implements GrafheadFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;
    
    public GrafheadFacade() {
//        super(Grafhead.class);
    }
    
   @Override
    public void create(Grafhead grafhead) {
        em.persist(grafhead);
        em.flush();
    }

    @Override
    public void edit(Grafhead grafhead) {
        em.merge(grafhead);
    }
    
    @Override
    public Grafhead find(Integer id) {
        Query grafheadQuery = em.createNamedQuery("Grafhead.findById");
        grafheadQuery.setParameter("id", id);
        List<Grafhead> resultGrafQuery = grafheadQuery.getResultList();
        if (!resultGrafQuery.isEmpty()) {
            return resultGrafQuery.get(0);
        }
        
        return null;
    }    
    
    @Override
    public List<Grafhead> findAll() {
        return em.createNamedQuery("Grafhead.findAll").getResultList();
    }

    @Override
    public List<Grafhead> findRange(Date dateBeg, Date dateEnd, UserBean user) {
        // переменная - запрос для создания запроса
        Query query = null;
        
        // запросы
        if (dateBeg == null && dateEnd == null) {
            if (user.isAdmin()) {
                query = em.createNamedQuery("Grafhead.findAll");
            } else {
                query = em.createNamedQuery("Grafhead.findAllUser");
//                query.setParameter("userid", user.getId());
                query.setParameter("userid", user.getUsers());
            }
        }
        
        if (dateBeg != null && dateEnd == null) {
            if (user.isAdmin()) {
                query = em.createNamedQuery("Grafhead.findByPeriodBeg");
                query.setParameter("periodbeg", dateBeg);
            } else {
                query = em.createNamedQuery("Grafhead.findByPeriodBegUser");
                query.setParameter("periodbeg", dateBeg);
//                query.setParameter("userid", user.getUsers());
                query.setParameter("userid", user.getUsers());
            }
        }
        
        if (dateBeg == null && dateEnd != null) {
            if (user.isAdmin()) {
                query = em.createNamedQuery("Grafhead.findByPeriodEnd");
                query.setParameter("periodend", dateEnd);
            } else {
                query = em.createNamedQuery("Grafhead.findByPeriodBegUser");
                query.setParameter("periodend", dateEnd);
//                query.setParameter("userid", user.getUsers());
                query.setParameter("userid", user.getUsers());
            }
        }
            
        if (dateBeg != null && dateEnd != null) {
            if (user.isAdmin()) {
                query = em.createNamedQuery("Grafhead.findByPeriodBegEnd");
                query.setParameter("periodbeg", dateBeg);
                query.setParameter("periodend", dateEnd);
            } else {
                query = em.createNamedQuery("Grafhead.findByPeriodBegEndUser");
                query.setParameter("periodbeg", dateBeg);
                query.setParameter("periodend", dateEnd);
//                query.setParameter("userid", user.getId());
                query.setParameter("userid", user.getUsers());
            }
        }
        
        if (query != null) {
            return query.getResultList();
        }
        
        return null;
    }

}

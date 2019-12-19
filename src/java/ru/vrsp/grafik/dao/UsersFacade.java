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
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author user
 */
@Stateless
public class UsersFacade implements UsersFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
    public UsersFacade() {
//        super(Users.class);
    }

    @Override
    public Users find(Object id) {
        return em.find(Users.class, id);
    }

    @Override
    public List<Users> findAll() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    
    @Override
    public Users userLogin(String userName, String userPass) {
        Query query = em.createNamedQuery("Users.findByNamePassword");
        query.setParameter("name", userName);
        query.setParameter("password", userPass);
        
        Users retVal = null;
                
        try {
            retVal = (Users) query.getSingleResult();
        } catch (Exception e) {
            
        }
        
        return retVal;
    }

    
}

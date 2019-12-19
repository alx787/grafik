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
import ru.vrsp.grafik.entity.Posts;

/**
 *
 * @author Alex
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PostsFacade implements PostsFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

    @Override
    public void create(Posts posts) {
        em.persist(posts);
    }
    
    @Override
    public void edit(Posts posts) {
        em.merge(posts);
    }

    @Override
    public Posts find(Integer id) {
        Query postQuery = em.createNamedQuery("Posts.findById");
        postQuery.setParameter("id", id);
        List<Posts> resultPostQuery = postQuery.getResultList();
        if (!resultPostQuery.isEmpty()) {
            return resultPostQuery.get(0);
        }
        
        return null;
    }

    @Override
    public List<Posts> findAll() {
        return em.createNamedQuery("Posts.findAll").getResultList();
    }
    
}

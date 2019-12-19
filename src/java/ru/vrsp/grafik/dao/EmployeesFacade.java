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
import ru.vrsp.grafik.entity.Employees;

/**
 *
 * @author user
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmployeesFacade implements EmployeesFacadeLocal {
    @PersistenceContext(unitName = "GrafikPU")
    private EntityManager em;

    public EmployeesFacade() {
//        super(Employees.class);
    }

    @Override
    public void create(Employees employees) {
        em.persist(employees);
    }
    
    @Override
    public void edit(Employees employees) {
        em.merge(employees);
    }

    @Override
    public List<Employees> findByDepart(Depart departId) {
        Query empQuery = em.createNamedQuery("Employees.findByDepart");
        empQuery.setParameter("depart", departId);
        return empQuery.getResultList();
    }
    
    @Override
    public Employees find(Integer id) {
        Query empQuery = em.createNamedQuery("Employees.findById");
        empQuery.setParameter("id", id);
        List<Employees> resultEmpQuery = empQuery.getResultList();
        if (!resultEmpQuery.isEmpty()) {
            return resultEmpQuery.get(0);
        }
        
        return null;
    }

    @Override
    public List<Employees> findAll() {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }
    
    @Override
    public List<Employees> findByEnabled() {
        return em.createNamedQuery("Employees.findByEnabled").getResultList();
    }

    @Override
    public List<Employees> findByEnabledByDepart(Depart departId) {
        Query empQuery = em.createNamedQuery("Employees.findByEnabledByDepart");
        empQuery.setParameter("depart", departId);
        return empQuery.getResultList();
    }
    
//    @Override
//    public List<Employees> findAllBosses() {
//        return em.createNamedQuery("SELECT e FROM Employees e WHERE e.boss > 0").getResultList();
//    }

    @Override
    public List<Employees> findAllBosses() {
        String queryText = "select emplext.id, emplext.name, emplext.departid, emplext.postid, emplext.enabled, emplext.boss, emplext.iamboss " +
                                "from employees emplext " +
                                "where exists (select 'x' from employees emplin where emplin.boss = emplext.id) " +
                                "and emplext.enabled = 1 " +
                                "order by emplext.name";
        return em.createNativeQuery(queryText, Employees.class).getResultList();
    }
    
    
    @Override
    public List<Employees> findByBoss(Integer bossId) {
        return em.createNamedQuery("Employees.findByBoss").setParameter("boss", bossId).getResultList();
    }

    @Override
    public List<Employees> findAllByDeparts(List<Depart> departs) {
        Query empQuery = em.createNamedQuery("Employees.findAllByDeparts");
        empQuery.setParameter("departs", departs);
        return empQuery.getResultList();
    }

    @Override
    public List<Employees> findAllByEnabledByDeparts(List<Depart> departs) {
        Query empQuery = em.createNamedQuery("Employees.findAllByEnabledByDeparts");
        empQuery.setParameter("departs", departs);
        return empQuery.getResultList();
    }

    @Override
    public List<Employees> findAllBossesByDepart(List<Depart> departs) {
        Query empQuery = em.createNamedQuery("Employees.findAllBossesByDepart");
        empQuery.setParameter("departs", departs);
        return empQuery.getResultList();
    }
    
}

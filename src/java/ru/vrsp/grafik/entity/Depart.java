/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "depart")
@NamedQueries({
    @NamedQuery(name = "Depart.findAll", query = "SELECT d FROM Depart d"),
    @NamedQuery(name = "Depart.findById", query = "SELECT d FROM Depart d WHERE d.id = :id"),
    @NamedQuery(name = "Depart.findByName", query = "SELECT d FROM Depart d WHERE d.name = :name"),
    @NamedQuery(name = "Depart.findByUser", query = "SELECT d FROM Depart d WHERE d.user = :user")
})
public class Depart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "departid", fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departid", fetch = FetchType.LAZY)
    private List<Grafhead> grafheadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departid", fetch = FetchType.LAZY)
    private List<Employees> employeesList;

    public Depart() {
    }

    public Depart(Integer id) {
        this.id = id;
    }

    public Depart(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Grafhead> getGrafheadList() {
        return grafheadList;
    }

    public void setGrafheadList(List<Grafhead> grafheadList) {
        this.grafheadList = grafheadList;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depart)) {
            return false;
        }
        Depart other = (Depart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.vrsp.grafik.entity.Depart[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e ORDER BY e.name"),
    @NamedQuery(name = "Employees.findByDepart", query = "SELECT e FROM Employees e WHERE e.departid = :depart ORDER BY e.name"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByName", query = "SELECT e FROM Employees e WHERE e.name = :name"),
    @NamedQuery(name = "Employees.findByEnabled", query = "SELECT e FROM Employees e WHERE e.enabled = 1 ORDER BY e.name"),
    @NamedQuery(name = "Employees.findByEnabledByDepart", query = "SELECT e FROM Employees e WHERE e.enabled = 1 AND e.departid = :depart ORDER BY e.name"),
    @NamedQuery(name = "Employees.findByBoss", query = "SELECT e FROM Employees e WHERE e.boss = :boss ORDER BY e.name"),
    @NamedQuery(name = "Employees.findAllByDeparts", query = "SELECT e FROM Employees e WHERE e.departid in :departs ORDER BY e.name"),
    @NamedQuery(name = "Employees.findAllByEnabledByDeparts", query = "SELECT e FROM Employees e WHERE e.enabled = 1 AND e.departid in :departs ORDER BY e.name"),
    @NamedQuery(name = "Employees.findAllBosses", query = "SELECT e FROM Employees e WHERE e.iamboss = 1 ORDER BY e.name"),
    @NamedQuery(name = "Employees.findAllBossesByDepart", query = "SELECT e FROM Employees e WHERE e.iamboss = 1 AND e.departid in :departs ORDER BY e.name")
})
public class Employees implements Serializable {
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
    
    @JoinColumn(name = "postid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Posts postid;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private boolean enabled;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "boss")
    private Integer boss;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeid", fetch = FetchType.LAZY)
    private List<Grafbody> grafbodyList;
    
    @JoinColumn(name = "departid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Depart departid;

    @Basic(optional = false)
    @NotNull
    @Column(name = "iamboss")
    private boolean iamboss;
    
    @Transient
    private Boolean marked;
    
    @Transient
    private String bossName;
    
    
    public Employees() {
    }

    public Employees(Integer id) {
        this.id = id;
    }

    public Employees(Integer id, String name, Depart departid, Posts postid, boolean enabled, Integer boss, List<Grafbody> grafbodyList, boolean iamboss) {
        this.id             = id;
        this.name           = name;
        this.departid       = departid;
        this.postid         = postid;
        this.enabled        = enabled;
        this.boss           = boss;
        this.grafbodyList   = grafbodyList;
        this.iamboss        = iamboss;
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

    public Posts getPostid() {
        return postid;
    }

    public void setPostid(Posts postid) {
        this.postid = postid;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public List<Grafbody> getGrafbodyList() {
        return grafbodyList;
    }

    public void setGrafbodyList(List<Grafbody> grafbodyList) {
        this.grafbodyList = grafbodyList;
    }

    public Depart getDepartid() {
        return departid;
    }

    public void setDepartid(Depart departid) {
        this.departid = departid;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public boolean getIamboss() {
        return iamboss;
    }

    public void setIamboss(boolean iamboss) {
        this.iamboss = iamboss;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.vrsp.grafik.entity.Employees[ id=" + id + " ]";
    }
    
}

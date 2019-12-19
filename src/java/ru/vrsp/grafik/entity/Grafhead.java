/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "grafhead")
@NamedQueries({
    @NamedQuery(name = "Grafhead.findAll", query = "SELECT g FROM Grafhead g"),
    @NamedQuery(name = "Grafhead.findById", query = "SELECT g FROM Grafhead g WHERE g.id = :id"),
    @NamedQuery(name = "Grafhead.findByPeriodBeg", query = "SELECT g FROM Grafhead g WHERE g.period >= :periodbeg"),
    @NamedQuery(name = "Grafhead.findByPeriodEnd", query = "SELECT g FROM Grafhead g WHERE g.period <= :periodend"),
    @NamedQuery(name = "Grafhead.findByPeriodBegEnd", query = "SELECT g FROM Grafhead g WHERE g.period >= :periodbeg AND g.period <= :periodend"),
    @NamedQuery(name = "Grafhead.findAllUser", query = "SELECT g FROM Grafhead g WHERE g.userid = :userid"),
    @NamedQuery(name = "Grafhead.findByPeriodBegUser", query = "SELECT g FROM Grafhead g WHERE g.period >= :periodbeg AND g.userid = :userid"),
    @NamedQuery(name = "Grafhead.findByPeriodEndUser", query = "SELECT g FROM Grafhead g WHERE g.period <= :periodend  AND g.userid = :userid"),
    @NamedQuery(name = "Grafhead.findByPeriodBegEndUser", query = "SELECT g FROM Grafhead g WHERE g.period >= :periodbeg AND g.period <= :periodend  AND g.userid = :userid")
})
public class Grafhead implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "period")
    @Temporal(TemporalType.DATE)
    private Date period;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "headid", fetch = FetchType.LAZY)
    private List<Grafbody> grafbodyList;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userid;
    @JoinColumn(name = "departid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Depart departid;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "coment")
    private String coment;
    
    @Transient
    private boolean marked;

    public Grafhead() {
    }

    public Grafhead(Integer id) {
        this.id = id;
    }

    public Grafhead(Integer id, Date period) {
        this.id = id;
        this.period = period;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public List<Grafbody> getGrafbodyList() {
        return grafbodyList;
    }

    public void setGrafbodyList(List<Grafbody> grafbodyList) {
        this.grafbodyList = grafbodyList;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Depart getDepartid() {
        return departid;
    }

    public void setDepartid(Depart departid) {
        this.departid = departid;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
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
        if (!(object instanceof Grafhead)) {
            return false;
        }
        Grafhead other = (Grafhead) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.vrsp.grafik.entity.Grafhead[ id=" + id + " ]";
    }
    
}

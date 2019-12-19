/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "graftemplate")
@NamedQueries({
        @NamedQuery(name = "Graftemplate.findAll", query = "SELECT g FROM Graftemplate g"),
        @NamedQuery(name = "Graftemplate.findById", query = "SELECT g FROM Graftemplate g WHERE g.id = :id")
})

public class Graftemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    
    @Transient
    private boolean marked;

    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_1")
    private String day1;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_2")
    private String day2;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_3")
    private String day3;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_4")
    private String day4;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_5")
    private String day5;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_6")
    private String day6;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_7")
    private String day7;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_8")
    private String day8;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_9")
    private String day9;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_10")
    private String day10;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_11")
    private String day11;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_12")
    private String day12;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_13")
    private String day13;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_14")
    private String day14;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_15")
    private String day15;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_16")
    private String day16;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_17")
    private String day17;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_18")
    private String day18;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_19")
    private String day19;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_20")
    private String day20;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_21")
    private String day21;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_22")
    private String day22;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_23")
    private String day23;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_24")
    private String day24;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_25")
    private String day25;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_26")
    private String day26;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_27")
    private String day27;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_28")
    private String day28;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_29")
    private String day29;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_30")
    private String day30;
    @NotNull
    @Size(min = 0, max = 3)
    @Column(name = "day_31")
    private String day31;

    public Graftemplate() {
    }

    public Graftemplate(Integer id) {
        this.id = id;
    }
    
    public Graftemplate(Integer id, String name, String author, String day1, String day2, String day3, String day4, String day5, String day6, String day7, String day8, String day9, String day10, String day11, String day12, String day13, String day14, String day15, String day16, String day17, String day18, String day19, String day20, String day21, String day22, String day23, String day24, String day25, String day26, String day27, String day28, String day29, String day30, String day31) {
        this.id = id;
        this.name = name;
        this.author = author;
        
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
        this.day8 = day8;
        this.day9 = day9;
        this.day10 = day10;
        this.day11 = day11;
        this.day12 = day12;
        this.day13 = day13;
        this.day14 = day14;
        this.day15 = day15;
        this.day16 = day16;
        this.day17 = day17;
        this.day18 = day18;
        this.day19 = day19;
        this.day20 = day20;
        this.day21 = day21;
        this.day22 = day22;
        this.day23 = day23;
        this.day24 = day24;
        this.day25 = day25;
        this.day26 = day26;
        this.day27 = day27;
        this.day28 = day28;
        this.day29 = day29;
        this.day30 = day30;
        this.day31 = day31;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    public String getDay8() {
        return day8;
    }

    public void setDay8(String day8) {
        this.day8 = day8;
    }

    public String getDay9() {
        return day9;
    }

    public void setDay9(String day9) {
        this.day9 = day9;
    }

    public String getDay10() {
        return day10;
    }

    public void setDay10(String day10) {
        this.day10 = day10;
    }

    public String getDay11() {
        return day11;
    }

    public void setDay11(String day11) {
        this.day11 = day11;
    }

    public String getDay12() {
        return day12;
    }

    public void setDay12(String day12) {
        this.day12 = day12;
    }

    public String getDay13() {
        return day13;
    }

    public void setDay13(String day13) {
        this.day13 = day13;
    }

    public String getDay14() {
        return day14;
    }

    public void setDay14(String day14) {
        this.day14 = day14;
    }

    public String getDay15() {
        return day15;
    }

    public void setDay15(String day15) {
        this.day15 = day15;
    }

    public String getDay16() {
        return day16;
    }

    public void setDay16(String day16) {
        this.day16 = day16;
    }

    public String getDay17() {
        return day17;
    }

    public void setDay17(String day17) {
        this.day17 = day17;
    }

    public String getDay18() {
        return day18;
    }

    public void setDay18(String day18) {
        this.day18 = day18;
    }

    public String getDay19() {
        return day19;
    }

    public void setDay19(String day19) {
        this.day19 = day19;
    }

    public String getDay20() {
        return day20;
    }

    public void setDay20(String day20) {
        this.day20 = day20;
    }

    public String getDay21() {
        return day21;
    }

    public void setDay21(String day21) {
        this.day21 = day21;
    }

    public String getDay22() {
        return day22;
    }

    public void setDay22(String day22) {
        this.day22 = day22;
    }

    public String getDay23() {
        return day23;
    }

    public void setDay23(String day23) {
        this.day23 = day23;
    }

    public String getDay24() {
        return day24;
    }

    public void setDay24(String day24) {
        this.day24 = day24;
    }

    public String getDay25() {
        return day25;
    }

    public void setDay25(String day25) {
        this.day25 = day25;
    }

    public String getDay26() {
        return day26;
    }

    public void setDay26(String day26) {
        this.day26 = day26;
    }

    public String getDay27() {
        return day27;
    }

    public void setDay27(String day27) {
        this.day27 = day27;
    }

    public String getDay28() {
        return day28;
    }

    public void setDay28(String day28) {
        this.day28 = day28;
    }

    public String getDay29() {
        return day29;
    }

    public void setDay29(String day29) {
        this.day29 = day29;
    }

    public String getDay30() {
        return day30;
    }

    public void setDay30(String day30) {
        this.day30 = day30;
    }

    public String getDay31() {
        return day31;
    }

    public void setDay31(String day31) {
        this.day31 = day31;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    
    //получить количество часов
    @SuppressWarnings("empty-statement")
    public Integer getTotalHours() {
        Integer i = 0;
        Integer iTot = 0;
        
        try { i = Integer.parseInt(this.day1); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day2); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day3); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day4); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day5); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day6); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day7); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day8); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day9); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day10); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        
        try { i = Integer.parseInt(this.day11); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day12); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day13); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day14); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day15); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day16); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day17); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day18); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day19); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day20); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        
        try { i = Integer.parseInt(this.day21); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day22); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day23); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day24); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day25); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day26); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day27); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day28); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day29); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        try { i = Integer.parseInt(this.day30); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;

        try { i = Integer.parseInt(this.day31); } catch (NumberFormatException e) { i = 0; }; iTot = iTot + i; i = 0;
        
        return iTot;
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
        if (!(object instanceof Graftemplate)) {
            return false;
        }
        Graftemplate other = (Graftemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.vrsp.grafik.entity.Graftemplate[ id=" + id + " ]";
    }
    
}

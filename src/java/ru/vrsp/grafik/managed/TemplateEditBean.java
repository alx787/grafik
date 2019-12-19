/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import ru.vrsp.grafik.dao.GraftemplateFacadeLocal;
import ru.vrsp.grafik.entity.Graftemplate;

/**
 *
 * @author Alex
 */
@Named(value = "templateEditBean")
@ViewScoped
public class TemplateEditBean implements Serializable {
    @EJB
    private GraftemplateFacadeLocal templDao;
    
    private Integer id;
    private String name;
    private String author;
    
    private List<String> inplaceVals;
    
    private Integer totalHours;
    
    // значения для заполнения
    private String val_1;
    private String val_2;
    private String val_3;
    private String val_4;
    private String val_5;
    private String val_6;
    private String val_7;
    private String val_8;
    private String val_9;
    private String val_10;

    private String val_11;
    private String val_12;
    private String val_13;
    private String val_14;
    private String val_15;
    private String val_16;
    private String val_17;
    private String val_18;
    private String val_19;
    private String val_20;
    
    private String val_21;
    private String val_22;
    private String val_23;
    private String val_24;
    private String val_25;
    private String val_26;
    private String val_27;
    private String val_28;
    private String val_29;
    private String val_30;
    
    private String val_31;
    ////////////////////////////////////////
    
    @Inject
    private TemplatesBean templBean;
    @Inject
    private UserBean userInfo;
    //
    private Graftemplate currentTempl;

    /**
     * Creates a new instance of TemplateEditBean
     */
    public TemplateEditBean() {
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

    public List<String> getInplaceVals() {
        return inplaceVals;
    }

    public void setInplaceVals(List<String> inplaceVals) {
        this.inplaceVals = inplaceVals;
    }

    public String getVal_1() {
        return val_1;
    }

    public void setVal_1(String val_1) {
        this.val_1 = val_1;
    }

    public String getVal_2() {
        return val_2;
    }

    public void setVal_2(String val_2) {
        this.val_2 = val_2;
    }

    public String getVal_3() {
        return val_3;
    }

    public void setVal_3(String val_3) {
        this.val_3 = val_3;
    }

    public String getVal_4() {
        return val_4;
    }

    public void setVal_4(String val_4) {
        this.val_4 = val_4;
    }

    public String getVal_5() {
        return val_5;
    }

    public void setVal_5(String val_5) {
        this.val_5 = val_5;
    }

    public String getVal_6() {
        return val_6;
    }

    public void setVal_6(String val_6) {
        this.val_6 = val_6;
    }

    public String getVal_7() {
        return val_7;
    }

    public void setVal_7(String val_7) {
        this.val_7 = val_7;
    }

    public String getVal_8() {
        return val_8;
    }

    public void setVal_8(String val_8) {
        this.val_8 = val_8;
    }

    public String getVal_9() {
        return val_9;
    }

    public void setVal_9(String val_9) {
        this.val_9 = val_9;
    }

    public String getVal_10() {
        return val_10;
    }

    public void setVal_10(String val_10) {
        this.val_10 = val_10;
    }

    public String getVal_11() {
        return val_11;
    }

    public void setVal_11(String val_11) {
        this.val_11 = val_11;
    }

    public String getVal_12() {
        return val_12;
    }

    public void setVal_12(String val_12) {
        this.val_12 = val_12;
    }

    public String getVal_13() {
        return val_13;
    }

    public void setVal_13(String val_13) {
        this.val_13 = val_13;
    }

    public String getVal_14() {
        return val_14;
    }

    public void setVal_14(String val_14) {
        this.val_14 = val_14;
    }

    public String getVal_15() {
        return val_15;
    }

    public void setVal_15(String val_15) {
        this.val_15 = val_15;
    }

    public String getVal_16() {
        return val_16;
    }

    public void setVal_16(String val_16) {
        this.val_16 = val_16;
    }

    public String getVal_17() {
        return val_17;
    }

    public void setVal_17(String val_17) {
        this.val_17 = val_17;
    }

    public String getVal_18() {
        return val_18;
    }

    public void setVal_18(String val_18) {
        this.val_18 = val_18;
    }

    public String getVal_19() {
        return val_19;
    }

    public void setVal_19(String val_19) {
        this.val_19 = val_19;
    }

    public String getVal_20() {
        return val_20;
    }

    public void setVal_20(String val_20) {
        this.val_20 = val_20;
    }

    public String getVal_21() {
        return val_21;
    }

    public void setVal_21(String val_21) {
        this.val_21 = val_21;
    }

    public String getVal_22() {
        return val_22;
    }

    public void setVal_22(String val_22) {
        this.val_22 = val_22;
    }

    public String getVal_23() {
        return val_23;
    }

    public void setVal_23(String val_23) {
        this.val_23 = val_23;
    }

    public String getVal_24() {
        return val_24;
    }

    public void setVal_24(String val_24) {
        this.val_24 = val_24;
    }

    public String getVal_25() {
        return val_25;
    }

    public void setVal_25(String val_25) {
        this.val_25 = val_25;
    }

    public String getVal_26() {
        return val_26;
    }

    public void setVal_26(String val_26) {
        this.val_26 = val_26;
    }

    public String getVal_27() {
        return val_27;
    }

    public void setVal_27(String val_27) {
        this.val_27 = val_27;
    }

    public String getVal_28() {
        return val_28;
    }

    public void setVal_28(String val_28) {
        this.val_28 = val_28;
    }

    public String getVal_29() {
        return val_29;
    }

    public void setVal_29(String val_29) {
        this.val_29 = val_29;
    }

    public String getVal_30() {
        return val_30;
    }

    public void setVal_30(String val_30) {
        this.val_30 = val_30;
    }

    public String getVal_31() {
        return val_31;
    }

    public void setVal_31(String val_31) {
        this.val_31 = val_31;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    /////////////////////////////////////////
    // methods
    
    // заполнение шаблона значениями
    private void fillTemplateBody(Graftemplate grTm) {

//        int i = 0;
//        try { i = Integer.parseInt(this.val_1); } catch (NumberFormatException e) { i = 0; };
//        if (i > 0) { grTm.setDay1(this.val_1); } else { grTm.setDay1(""); };
        
        grTm.setDay1(val_1); grTm.setDay2(val_2); grTm.setDay3(val_3); grTm.setDay4(val_4); grTm.setDay5(val_5);
        grTm.setDay6(val_6); grTm.setDay7(val_7); grTm.setDay8(val_8); grTm.setDay9(val_9); grTm.setDay10(val_10);

        grTm.setDay11(val_11); grTm.setDay12(val_12); grTm.setDay13(val_13); grTm.setDay14(val_14); grTm.setDay15(val_15);
        grTm.setDay16(val_16); grTm.setDay17(val_17); grTm.setDay18(val_18); grTm.setDay19(val_19); grTm.setDay20(val_20);

        grTm.setDay21(val_21); grTm.setDay22(val_22); grTm.setDay23(val_23); grTm.setDay24(val_24); grTm.setDay25(val_25);
        grTm.setDay26(val_26); grTm.setDay27(val_27); grTm.setDay28(val_28); grTm.setDay29(val_29); grTm.setDay30(val_30);

        grTm.setDay31(val_31);
        
    }
    
    public String onActionOK() {
        saveTempl();
        return "templatelist";
    }
    
    public String onActionSave() {
        saveTempl();
        return null;
    }
            
    private void saveTempl() {
//        if (templBean == null || templBean.getTemplSelect() == null) {
        if (this.currentTempl == null) {
            // новый шаблон
            Graftemplate templNew = new Graftemplate();
            
            templNew.setName(name);
            templNew.setAuthor(author);
            
            fillTemplateBody(templNew);
            
            templDao.create(templNew);
            
            currentTempl = templNew;
        } else {
            // редактируем существующий шаблон
            Graftemplate templNew = templDao.find(this.currentTempl.getId());
            
            if (templNew != null) {
                templNew.setName(name);
                templNew.setAuthor(author);
                
                fillTemplateBody(templNew);
                
                templDao.edit(templNew);
            }
        }
        
//        return "templatelist";
    }
    
    @PostConstruct
    private void init() {
        
        // инициализация тек шаблона
        this.currentTempl = null;
        if (this.templBean != null && this.templBean.getTemplSelect() != null) {
            this.currentTempl = this.templBean.getTemplSelect();
        }
        
        if (this.currentTempl == null) {
            // новый график
            this.id = null;
            this.totalHours = 0;
            this.name = "новый шаблон графика";
            this.author = userInfo.getName();
            
            this.val_1 = ""; this.val_2 = ""; this.val_3 = ""; this.val_4 = ""; this.val_5 = "";
            this.val_6 = ""; this.val_7 = ""; this.val_8 = ""; this.val_9 = ""; this.val_10 = "";
            
            this.val_11 = ""; this.val_12 = ""; this.val_13 = ""; this.val_14 = ""; this.val_15 = "";
            this.val_16 = ""; this.val_17 = ""; this.val_18 = ""; this.val_19 = ""; this.val_20 = "";

            this.val_21 = ""; this.val_22 = ""; this.val_23 = ""; this.val_24 = ""; this.val_25 = "";
            this.val_26 = ""; this.val_27 = ""; this.val_28 = ""; this.val_29 = ""; this.val_30 = "";
            
            this.val_31 = "";
        } else {
            // существующий график
            this.id = templBean.getTemplSelect().getId();
            this.totalHours = templBean.getTemplSelect().getTotalHours();
            this.name = templBean.getTemplSelect().getName();
            this.author = templBean.getTemplSelect().getAuthor();
            
            this.val_1 = templBean.getTemplSelect().getDay1();
            this.val_2 = templBean.getTemplSelect().getDay2();
            this.val_3 = templBean.getTemplSelect().getDay3();
            this.val_4 = templBean.getTemplSelect().getDay4();
            this.val_5 = templBean.getTemplSelect().getDay5();
            this.val_6 = templBean.getTemplSelect().getDay6();
            this.val_7 = templBean.getTemplSelect().getDay7();
            this.val_8 = templBean.getTemplSelect().getDay8();
            this.val_9 = templBean.getTemplSelect().getDay9();
            this.val_10 = templBean.getTemplSelect().getDay10();
            
            this.val_11 = templBean.getTemplSelect().getDay11();
            this.val_12 = templBean.getTemplSelect().getDay12();
            this.val_13 = templBean.getTemplSelect().getDay13();
            this.val_14 = templBean.getTemplSelect().getDay14();
            this.val_15 = templBean.getTemplSelect().getDay15();
            this.val_16 = templBean.getTemplSelect().getDay16();
            this.val_17 = templBean.getTemplSelect().getDay17();
            this.val_18 = templBean.getTemplSelect().getDay18();
            this.val_19 = templBean.getTemplSelect().getDay19();
            this.val_20 = templBean.getTemplSelect().getDay20();
            
            this.val_21 = templBean.getTemplSelect().getDay21();
            this.val_22 = templBean.getTemplSelect().getDay22();
            this.val_23 = templBean.getTemplSelect().getDay23();
            this.val_24 = templBean.getTemplSelect().getDay24();
            this.val_25 = templBean.getTemplSelect().getDay25();
            this.val_26 = templBean.getTemplSelect().getDay26();
            this.val_27 = templBean.getTemplSelect().getDay27();
            this.val_28 = templBean.getTemplSelect().getDay28();
            this.val_29 = templBean.getTemplSelect().getDay29();
            this.val_30 = templBean.getTemplSelect().getDay30();
            
            this.val_31 = templBean.getTemplSelect().getDay31();

        }
        
    }
}

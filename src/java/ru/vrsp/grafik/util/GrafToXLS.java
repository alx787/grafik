/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vrsp.grafik.util;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import ru.vrsp.grafik.entity.Grafbody;
import ru.vrsp.grafik.entity.Grafhead;
import ru.vrsp.grafik.managed.UserBean;

/**
 *
 * @author Alex
 */
public class GrafToXLS {
    // график для печати
    private Grafhead selectedGraf;
    // табличная часть
    private List<Grafbody> grafData;
    
    // имя и путь к файлу
    private String filename;
    private String filepath;
    // инфо о текущем пользователе
    private UserBean userInfo;

    public GrafToXLS() {
    }

    public GrafToXLS(Grafhead selectedGraf, List<Grafbody> grafData, UserBean userInfo, String filename, String filepath) {
        this.selectedGraf = selectedGraf;
        this.grafData = grafData;
        this.userInfo = userInfo;
        this.filename = filename;
        this.filepath = filepath;
    }

    public void setSelectedGraf(Grafhead selectedGraf) {
        this.selectedGraf = selectedGraf;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public UserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<Grafbody> getGrafData() {
        return grafData;
    }

    public void setGrafData(List<Grafbody> grafData) {
        this.grafData = grafData;
    }


    ////////////////////////////////////////////////
    // methods
    public boolean createXLS() {

        if (selectedGraf == null || grafData == null || userInfo == null) {
            return false;
        }

        if (filename == null || filepath == null) {
            return false;
        }
        
        if (grafData.isEmpty()) {
            return false;
        }
        
        try {
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(new File(filepath + filename));
            WritableSheet sheet = workbook.createSheet("Лист1", 0);

            /////////////////////////////////////////////////////////////////////
            // заголовок
            /////////////////////////////////////////////////////////////////////
            // ширина столбцов
            // фио
            sheet.setColumnView(0, 19);
            // должность
            sheet.setColumnView(1, 15);
            // дни
            for (int i = 2; i <= 32; i++) {
                sheet.setColumnView(i, 4);
            }
            // итого часов
            sheet.setColumnView(33, 9);

            /////////////////////////////////////////////////////////////////////
            // стили
            /////////////////////////////////////////////////////////////////////
            WritableFont cellFont10nobold = new WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD);
            WritableFont cellFont10bold = new WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD);

            WritableFont cellFont12nobold = new WritableFont(WritableFont.COURIER, 12, WritableFont.NO_BOLD);
            WritableFont cellFont12bold = new WritableFont(WritableFont.COURIER, 12, WritableFont.BOLD);

            // 10 nobold 
            WritableCellFormat cellFormat10noboldRight = new WritableCellFormat();
            cellFormat10noboldRight.setFont(cellFont10nobold);
            cellFormat10noboldRight.setAlignment(Alignment.RIGHT);

            WritableCellFormat cellFormat10noboldCenter = new WritableCellFormat();
            cellFormat10noboldCenter.setFont(cellFont10nobold);
            cellFormat10noboldCenter.setAlignment(Alignment.CENTRE);

            WritableCellFormat cellFormat10noboldLeft = new WritableCellFormat();
            cellFormat10noboldLeft.setFont(cellFont10nobold);
            cellFormat10noboldLeft.setAlignment(Alignment.LEFT);

            // 10 nobold left border
            WritableCellFormat cellFormat10noboldLeftBorder = new WritableCellFormat();
            cellFormat10noboldLeftBorder.setFont(cellFont10nobold);
            cellFormat10noboldLeftBorder.setAlignment(Alignment.LEFT);
            cellFormat10noboldLeftBorder.setBorder(Border.ALL, BorderLineStyle.THIN);

            // 10 nobold left border
            WritableCellFormat cellFormat10noboldCenterBorder = new WritableCellFormat();
            cellFormat10noboldCenterBorder.setFont(cellFont10nobold);
            cellFormat10noboldCenterBorder.setAlignment(Alignment.CENTRE);
            cellFormat10noboldCenterBorder.setBorder(Border.ALL, BorderLineStyle.THIN);

            // 10 bold
            WritableCellFormat cellFormat10boldRight = new WritableCellFormat();
            cellFormat10boldRight.setFont(cellFont10bold);
            cellFormat10boldRight.setAlignment(Alignment.RIGHT);

            WritableCellFormat cellFormat10boldCenter = new WritableCellFormat();
            cellFormat10boldCenter.setFont(cellFont10bold);
            cellFormat10boldCenter.setAlignment(Alignment.CENTRE);

            WritableCellFormat cellFormat10boldLeft = new WritableCellFormat();
            cellFormat10boldLeft.setFont(cellFont10bold);
            cellFormat10boldLeft.setAlignment(Alignment.LEFT);

            // 10 bold border center
            WritableCellFormat cellFormat10boldBorderCenter = new WritableCellFormat();
            cellFormat10boldBorderCenter.setFont(cellFont10bold);
            cellFormat10boldBorderCenter.setAlignment(Alignment.CENTRE);
            cellFormat10boldBorderCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            // 10 bold border center wrap
            WritableCellFormat cellFormat10boldBorderCenterWrap = new WritableCellFormat();
            cellFormat10boldBorderCenterWrap.setFont(cellFont10bold);
            cellFormat10boldBorderCenterWrap.setAlignment(Alignment.CENTRE);
            cellFormat10boldBorderCenterWrap.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat10boldBorderCenterWrap.setWrap(true);

            // 10 bold border center vertical_centre
            WritableCellFormat cellFormat10boldBorderCenterVertical = new WritableCellFormat();
            cellFormat10boldBorderCenterVertical.setFont(cellFont10bold);
            cellFormat10boldBorderCenterVertical.setAlignment(Alignment.CENTRE);
            cellFormat10boldBorderCenterVertical.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat10boldBorderCenterVertical.setVerticalAlignment(VerticalAlignment.CENTRE);

            // 12 nobold 
            WritableCellFormat cellFormat12noboldRight = new WritableCellFormat();
            cellFormat12noboldRight.setFont(cellFont12nobold);
            cellFormat12noboldRight.setAlignment(Alignment.RIGHT);

            WritableCellFormat cellFormat12noboldCenter = new WritableCellFormat();
            cellFormat12noboldCenter.setFont(cellFont12nobold);
            cellFormat12noboldCenter.setAlignment(Alignment.CENTRE);

            WritableCellFormat cellFormat12noboldLeft = new WritableCellFormat();
            cellFormat12noboldLeft.setFont(cellFont12nobold);
            cellFormat12noboldLeft.setAlignment(Alignment.LEFT);

            // 12 bold 
            WritableCellFormat cellFormat12boldRight = new WritableCellFormat();
            cellFormat12boldRight.setFont(cellFont12bold);
            cellFormat12boldRight.setAlignment(Alignment.RIGHT);

            WritableCellFormat cellFormat12boldCenter = new WritableCellFormat();
            cellFormat12boldCenter.setFont(cellFont12bold);
            cellFormat12boldCenter.setAlignment(Alignment.CENTRE);

            WritableCellFormat cellFormat12boldLeft = new WritableCellFormat();
            cellFormat12boldLeft.setFont(cellFont12bold);
            cellFormat12boldLeft.setAlignment(Alignment.LEFT);

            Label label = null;
            jxl.write.Number number = null;

            label = new Label(33, 0, "Приложение к приказу № _____ от \"____\" __________2014г.", cellFormat10noboldRight);
            sheet.addCell(label);

            label = new Label(33, 2, "Утверждаю:", cellFormat12boldRight);
            sheet.addCell(label);

            label = new Label(33, 3, "Директор ЗАО \"Вятка-Роспечать\"", cellFormat12boldRight);
            sheet.addCell(label);

            label = new Label(33, 4, "________________________ Е.Ю. Алборова", cellFormat12boldRight);
            sheet.addCell(label);

            label = new Label(33, 5, "\"_____\" ____________________ 201 ___ г.", cellFormat12boldRight);
            sheet.addCell(label);

            label = new Label(15, 7, "ГРАФИК", cellFormat12boldCenter);
            sheet.addCell(label);
            
            // получим месяц и год
            Date grafDate = selectedGraf.getPeriod();
            GregorianCalendar gCal = new GregorianCalendar();
            
            gCal.setTime(grafDate);
            Integer iMonth = gCal.get(GregorianCalendar.MONTH) + 1;
            Integer iYear = gCal.get(GregorianCalendar.YEAR);
            
            String sMonth;
            switch (iMonth) {
                case 1: sMonth = "ЯНВАРЬ"; break;
                case 2: sMonth = "ФЕВРАЛЬ"; break;
                case 3: sMonth = "МАРТ"; break;
                case 4: sMonth = "АПРЕЛЬ"; break;
                case 5: sMonth = "МАЙ"; break;
                case 6: sMonth = "ИЮНЬ"; break;
                case 7: sMonth = "ИЮЛЬ"; break;
                case 8: sMonth = "АВГУСТ"; break;
                case 9: sMonth = "СЕНТЯБРЬ"; break;
                case 10: sMonth = "ОКТЯБРЬ"; break;
                case 11: sMonth = "НОЯБРЬ"; break;
                case 12: sMonth = "ДЕКАБРЬ"; break;
                default: sMonth = "-----"; break;
                
            }

            label = new Label(15, 8, "Работы на       " + sMonth + "       " + iYear.toString() + " года", cellFormat12boldCenter);
            sheet.addCell(label);

            label = new Label(15, 9, "Структурное подразделение    " + selectedGraf.getDepartid().getName().toUpperCase() + "    ", cellFormat12boldCenter);
            sheet.addCell(label);

            label = new Label(0, 11, "ФИО работника", cellFormat10boldBorderCenterVertical);
            sheet.addCell(label);

            label = new Label(1, 11, "Должность", cellFormat10boldBorderCenterVertical);
            sheet.addCell(label);

            for (Integer i = 1; i <= 31; i++) {
                label = new Label(i + 1, 11, i.toString(), cellFormat10boldBorderCenterVertical);
                sheet.addCell(label);
            }

            label = new Label(33, 11, "Итого за месяц", cellFormat10boldBorderCenterWrap);
            sheet.addCell(label);

            /////////////////////////////////////////////////////////////////////
            // данные
            /////////////////////////////////////////////////////////////////////
            
            Grafbody grafbodySingle = null;
            // переменные пригодятся
            Integer rowNumInt = 0;
            String rowNumStr;
            Integer countBrig = 0; // количество бригадиров
            
//            for (Grafbody grafbodySingle : grafData) {
            for (int y = 0; y < grafData.size() ; y++) {

                grafbodySingle = grafData.get(y);
                Integer i;

                Integer x;
                
                // если бригадир то пропускаем одну строку
                if (grafbodySingle.getEmployeeid().getIamboss()) {
                    countBrig++;
                }
                
                x = y + countBrig;
                
                
                // ФИО
                label = new Label(0, x + 12, grafbodySingle.getEmployeeid().getName(), cellFormat10noboldLeftBorder);
                sheet.addCell(label);
                // должность
                label = new Label(1, x + 12, grafbodySingle.getEmployeeid().getPostid().getName(), cellFormat10noboldLeftBorder);
                sheet.addCell(label);
                
                // day 1
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay1()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(2, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else { 
                    label = new Label(2, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 2
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay2()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(3, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(3, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 3
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay3()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(4, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(4, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 4
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay4()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(5, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(5, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 5
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay5()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(6, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(6, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 6
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay6()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(7, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(7, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 7
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay7()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(8, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(8, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 8
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay8()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) { 
                    number = new jxl.write.Number(9, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(9, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 9
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay9()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(10, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(10, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 10
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay10()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(11, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else {
                    label = new Label(11, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                

                
                
                // day 11
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay11()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(12, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else { 
                    label = new Label(12, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 12
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay12()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(13, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(13, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 13
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay13()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(14, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(14, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 14
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay14()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(15, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(15, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 15
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay15()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(16, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(16, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 16
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay16()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(17, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(17, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 17
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay17()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(18, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(18, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 18
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay18()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) { 
                    number = new jxl.write.Number(19, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(19, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 19
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay19()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(20, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(20, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 20
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay20()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(21, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else {
                    label = new Label(21, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                
                

                // day 21
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay21()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(22, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else { 
                    label = new Label(22, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 22
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay22()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(23, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(23, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 23
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay23()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(24, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(24, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 24
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay24()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(25, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(25, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 25
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay25()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(26, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(26, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 26
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay26()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(27, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(27, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 27
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay27()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(28, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(28, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 28
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay28()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) { 
                    number = new jxl.write.Number(29, x + 12, i, cellFormat10noboldCenterBorder); 
                    sheet.addCell(number); 
                } else {
                    label = new Label(29, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 29
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay29()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(30, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number); 
                } else {
                    label = new Label(30, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                // day 30
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay30()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(31, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else {
                    label = new Label(31, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                
                
                // day 31
                i = 0;
                try { i = Integer.parseInt(grafbodySingle.getDay31()); } catch (NumberFormatException e) { i = 0; }
                if (i > 0) {
                    number = new jxl.write.Number(32, x + 12, i, cellFormat10noboldCenterBorder);
                    sheet.addCell(number);
                } else {
                    label = new Label(32, x + 12, "", cellFormat10noboldLeftBorder);
                    sheet.addCell(label);
                }
                
                
                
                rowNumInt = x + 12 + 1;
                rowNumStr = rowNumInt.toString();
                        
                //сумма - формула в конце строки
                Formula formula = new Formula(33, x + 12, "SUM(C" + rowNumStr + ":AG" + rowNumStr + ")", cellFormat10noboldCenterBorder);
                sheet.addCell(formula);
                
                
            }
            

            /////////////////////////////////////////////////////////////////////
            // подвал
            /////////////////////////////////////////////////////////////////////
            label = new Label(0, (rowNumInt + 2), "Руководитель структурного подразделения                       ___________________________ ( " + userInfo.getName() + " )", cellFormat10noboldLeft);
            sheet.addCell(label);

            /////////////////////////////////////////////////////////////////////
            // повторять при печати строки
            /////////////////////////////////////////////////////////////////////
            sheet.getSettings().setPrintTitlesRow(11, 11);
            
            workbook.write();
            workbook.close();

        } catch (Exception e) {
            System.out.println(" ======== error " + e);
            return false;
        }

        return true;
    }

}

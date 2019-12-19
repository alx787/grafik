
 // пометить все строки
function markAllGrafbody(setMark) {
    var elems = $("#mainform\\:grafDataTable input[type=checkbox]");
    for (var i = 0; i < elems.length; i++) {
        elems[i].checked = setMark;
    }
    return false;
}

function markAllAddEmpl(setMark) {
    var elems = $("#mainform\\:emplListTable input[type=checkbox]");
    for (var i = 0; i < elems.length; i++) {
        elems[i].checked = setMark;
    }
    return false;
}

// собрать итоги по строке
// dayBegin - день месяца даты начала (число)
// dayEnd - день месяца даты окончания (число)
// rowNum - номер строки (число) нумерация с нуля
function refreshSingleRow(dayBegin, dayEnd, rowNum) {
    var ss = 0;
    var strDayNum = "";
    var cellValue, cellValueParsed;
    
    for (var i = dayBegin; i <= dayEnd; i++) {
        strDayNum = i.toString();
        
//--        cellValue = $("input:text[id='mainform:grafDataTable:" + rowNum.toString() + ":day" + strDayNum + "']").val();
        cellValue = document.getElementById("mainform:grafDataTable:" + rowNum.toString() + ":day" + strDayNum).value;
        
        cellValueParsed = parseFloat(cellValue);
        if (!isNaN(cellValueParsed)) {
            ss = ss + cellValueParsed;
        }
    }
    //$("#mainform\\:grafDataTable\\:" + rowNum.toString() + "\\:totalHours").text(ss);    
//--    $("input:text[id='mainform:grafDataTable:" + rowNum.toString() + ":totalHours']").val(ss);
    document.getElementById("mainform:grafDataTable:" + rowNum.toString() + ":totalHours").value = ss;
    
    return false;
}

function refreshAllRow() {
    
    var grafikYear, grafikMonth;
    // год - период графика
    grafikYear = parseInt($("#mainform\\:grafikYear input:text").val());
    if (isNaN(grafikYear))
        return false;
    
    // месяц - период графика
    grafikMonth = parseInt($("#mainform\\:grafikMonth").val());
    if (isNaN(grafikMonth))
        return false;

    // последний день месяца
    var endDay = new Date(grafikYear, grafikMonth, 0).getDate();
    
    // количество строк
    var rowCount = $("input:checkbox[id$='_rowId']").length;
    
    for (var i = 0; i < rowCount; i++) {
//        if ($("input:checkbox[id='mainform:grafDataTable:" + i.toString() + ":_rowId']").is(":checked")) {
            refreshSingleRow(1, endDay, i);
//        }
    }
    return false;
}

function shiftLeft() {
    var grafikYear, grafikMonth;
    // год - период графика
    grafikYear = parseInt($("#mainform\\:grafikYear input:text").val());
    if (isNaN(grafikYear))
        return false;
    
    // месяц - период графика
    grafikMonth = parseInt($("#mainform\\:grafikMonth").val());
    if (isNaN(grafikMonth))
        return false;

    // последний день месяца
    // он же последний день даты заполнения графика
    var endDay = new Date(grafikYear, grafikMonth, 0).getDate();
    
    // количество строк
    var rowCount = $("input:checkbox[id$='_rowId']").length;
    var cellValue = "";
    var transVar = "";
    
    for (var i = 0; i < rowCount; i++) {
//--        if ($("input:checkbox[id='mainform:grafDataTable:" + i.toString() + ":_rowId']").is(":checked")) {
        if (document.getElementById("mainform:grafDataTable:" + i.toString() + ":_rowId").checked) {
            // i - номер строки
            // endDay - последний день месяца
            // transVar - данные первого дня месяца
            
//--            transVar = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day1']").val();
            transVar = document.getElementById("mainform:grafDataTable:" + i.toString() + ":day1").value;
            
            for (var dayNum = 1; dayNum <= endDay; dayNum++) {
                //cellValue = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val();
                if (dayNum < endDay) {
//--                    cellValue = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + (dayNum + 1).toString() + "']").val();
//--                    $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val(cellValue);
                    cellValue = document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + (dayNum + 1).toString()).value;
                    document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString()).value = cellValue;
                }
                if (dayNum == endDay) {
//--                    $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val(transVar);
                    document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString()).value = transVar;
                }
            }
            // пересчет суммы часов
            refreshSingleRow(1, endDay, i);            
        }
    }    
    return false;
}

function shiftRight() {
    
    var grafikYear, grafikMonth;
    // год - период графика
    grafikYear = parseInt($("#mainform\\:grafikYear input:text").val());
    if (isNaN(grafikYear))
        return false;
    
    // месяц - период графика
    grafikMonth = parseInt($("#mainform\\:grafikMonth").val());
    if (isNaN(grafikMonth))
        return false;

    // последний день месяца
    // он же последний день даты заполнения графика
    var endDay = new Date(grafikYear, grafikMonth, 0).getDate();
    
    // количество строк
    var rowCount = $("input:checkbox[id$='_rowId']").length;
    var cellValue = "";
    var transVar = "";
    
    for (var i = 0; i < rowCount; i++) {
        
        if (document.getElementById("mainform:grafDataTable:" + i.toString() + ":_rowId").checked) {
        
//--        if ($("input:checkbox[id='mainform:grafDataTable:" + i.toString() + ":_rowId']").is(":checked")) {
            // i - номер строки
            // endDay - последний день месяца
            // transVar - данные последнего дня месяца
            transVar = document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + endDay.toString()).value;
//--            transVar = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + endDay.toString() + "']").val();
            
            for (var dayNum = endDay; dayNum > 0; dayNum--) {
                //cellValue = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val();
                if (dayNum > 1) {
//--                    cellValue = $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + (dayNum - 1).toString() + "']").val();
//--                    $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val(cellValue);
                    
                    cellValue = document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + (dayNum - 1).toString()).value;
                    document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString()).value = cellValue;
                }
                if (dayNum == 1) {
//--                    $("input:text[id='mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString() + "']").val(transVar);
                    document.getElementById("mainform:grafDataTable:" + i.toString() + ":day" + dayNum.toString()).value = transVar;
                }
            }
            // пересчет суммы часов
            refreshSingleRow(1, endDay, i);            
        }
    }    
    return false;
}

function ignoreEnterBackspace() {
    if (event.keyCode == 13) {
        event.cancelBubble = true;
        event.returnValue = false;
    }
}

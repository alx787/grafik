
function getTotalHours() {
    var totHours = 0;
    var strDayNum = "";
    var cellValue, cellValueParsed;
    
    for (var i = 1; i <= 31; i++) {
        strDayNum = i.toString();
        cellValue = document.getElementById("mainform:day_" + strDayNum).value;
        cellValueParsed = parseFloat(cellValue);
        if (!isNaN(cellValueParsed)) {
            totHours = totHours + cellValueParsed;
        }
    }
    
    document.getElementById("mainform:totHours").value = totHours;
}



/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function checkDateFormatSetting(formid, componentid) {
    var regexp = /^(C|Y|y){1}(j|m|d|e){0,2}$/;
    var format = document.getElementById(formid + ":" + componentid);
    if (!regexp.exec(format.value)) {
        format.value = 'ym';
        alert('您设定的格式不符合要求,系统进行了预设!');
    }

}

//'C' 	Four-digit year divided by 100, formatted as two digits with leading zero as necessary, i.e. 00 - 99
//'Y' 	Year, formatted as at least four digits with leading zeros as necessary, e.g. 0092 equals 92 CE for the Gregorian calendar.
//'y' 	Last two digits of the year, formatted with leading zeros as necessary, i.e. 00 - 99.
//'j' 	Day of year, formatted as three digits with leading zeros as necessary, e.g. 001 - 366 for the Gregorian calendar.
//'m' 	Month, formatted as two digits with leading zeros as necessary, i.e. 01 - 13.
//'d' 	Day of month, formatted as two digits with leading zeros as necessary, i.e. 01 - 31
//'e' 	Day of month, formatted as two digits, i.e. 1 - 31.
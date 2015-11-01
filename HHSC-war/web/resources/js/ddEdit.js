/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var btnConfirmDetail = document.getElementById("formEdit:btnSaveDetail");
    btnConfirmDetail.disabled = true;
    $(btnConfirmDetail).addClass('ui-state-disabled');

    var btnDeleteDetail = document.getElementById("formEdit:btnDeleteDetail");
    btnDeleteDetail.disabled = true;
    $(btnDeleteDetail).addClass('ui-state-disabled');

});
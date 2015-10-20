/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var waiting = function () {
    var sendCount = $(document.getElementById('formRegister:count')).val();
    //alert(sendCount);
    if (sendCount !== "0" ) {
        var btn = $(document.getElementById('formRegister:btnSendVerifyCode'));
        waitingTime.waiting(btn);
    }
};

var waitingTime = {
    wait: 30,
    waiting: function (btn) {
        _this = this;
        if (_this.wait < 0) {
            $(btn).removeAttr("disabled");
            $(btn).find('span').text('获取验证码');
            _this.wait = 30;
        } else {
            var _this = this;
            $(btn).attr("disabled", true);
            $(btn).find('span').text('等待' + _this.wait + '秒');
            _this.wait--;
            setTimeout(function () {
                _this.waiting(btn);
            }, 1000);
        }
    }
};

var sendGraphicCode = function(){
    var btnSend = $(document.getElementById('formRegister:btnSendGraphicCode'));
    btnSend.click();
};
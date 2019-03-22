$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});

$(function () {
    $("#loginBtn").on("click",function(){
        var username = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type:"get",
            url:getRootPath_dc() + "/admin/login.do",
            dataType:"json",
            data:{
                "username":username,
                "password":password
            },
            success:function (data) {
                console.log(data);
                if (data.type == 0) {
                    alert(data.msg);
                }else if (data.type == 1){
                    window.location.href = getRootPath_dc() + "/provider/go2appointmentPage.do?shopId=" + data.shopId;
                }else if (data.type == 2) {
                    window.location.href = getRootPath_dc() + "/admin/power.do";
                }
            }

        });
    })
});

jQuery(document).ready(function(){
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        type:"get",
        url:getRootPath_dc() + "/admin/loginCookie.do",
        dataType:"json",
        data:{
            "username":username,
            "password":password
        },
        success:function (data) {
            console.log(data);
            if (data.type == 1){
                window.location.href = getRootPath_dc() + "/provider/go2appointmentPage.do?shopId=" + data.shopId;
            }else if (data.type == 2) {
                window.location.href = getRootPath_dc() + "/admin/power.do";
            }
        }

    });
});

function getRootPath_dc() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    }
    else {
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}
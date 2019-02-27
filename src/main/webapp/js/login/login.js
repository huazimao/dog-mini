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
            type:"post",
            url:getRootPath_dc() + "/login/login",
            dataType:"json",
            data:{
                "username":username,
                "password":password
            },
            success:function (data) {
                console.log(data);
                if (data.type == 1) {
                    alert("ok");
                }else {
                    alert("fail");
                }
            }

        });
    })
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
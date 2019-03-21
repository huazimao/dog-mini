function save() {
    alert("123");
    var t = $("#a1").serialize();
    // window.location.href = "127.0.0.1:8080/provider/saveOrUptSetting.do";
    $.ajax({
        type:"post",
        url:getRootPath_dc() + "/provider/saveOrUptSetting.do",
        contentType:"application/json;charset=UTF-8",
        dataType:"json",
        data:JSON.stringify(t),
        success:function (data) {
            console.info(data);
        }

    });
}


function getRootPath_dc() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        return window.location.protocol + '//' + window.location.host;

}
$("#o1").click(function() {

});
function o1() {
    alert(getRootPath_dc() + '/provider/showAppointmentByTimeAndShop.do?shopId=lj');
    window.location.href= getRootPath_dc() + '/provider/showAppointmentByTimeAndShop.do?shopId=lj';

}

function o2() {
    window.location.href= getRootPath_dc() + '/provider/showAppointmentByTimeAndShop.do?shopId=ls';
}

function o3() {
    window.location.href= getRootPath_dc() + '/provider/showAppointmentByTimeAndShop.do?shopId=rg';
}

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

function getRootPath_dc() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        return window.location.protocol + '//' + window.location.host;
}
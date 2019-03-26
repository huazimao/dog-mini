<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="/views/context/mytags.jsp"%>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>商家系统设置</title>
    <link rel="stylesheet" href="${context}/css/weui.css">
    <link rel="stylesheet" href="${context}/css/example.css">
    <script type="text/javascript" src="${context}/js/jquery-2.1.1.min.js"></script>
    <script>
        var path = '${context}';
    </script>

</head>
<body>
<div class="weui-tab">
    <div class="weui-navbar">
        <input type="hidden" id="time_hidden" name="workTime" value="今天">
        <input type="hidden" id="id" name="id" value="">

        <div class="weui-navbar__item weui-bar__item_on">
            <div class="item-t">今天</div>
            <div class="item-b">
                <span class="item-date" id="time1"></span>
                (<span class="item-day" id="week1"></span>)
            </div>
        </div>
        <div class="weui-navbar__item">
            <div class="item-t">明天</div>
            <div class="item-b">
                <span class="item-date" id="time2"></span>
                (<span class="item-day" id="week2"></span>)
            </div>
        </div>
        <div class="weui-navbar__item">
            <div class="item-t">后天</div>
            <div class="item-b">
                <span class="item-date" id="time3"></span>
                (<span class="item-day" id="week3"></span>)
            </div>
        </div>
    </div>
    <div class="weui-tab__panel">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label" id="shopId">龙江店</label></div>
            </div>
            <div class="lists">



            </div>




        </div>
    </div>
</div>
<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content">设置成功</p>
    </div>
</div>

<script src="${context}/js/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src="${context}/js/example.js"></script>
<script src="${context}/js/sysetting.js"></script>
<script>

    function showOK() {
        var $toast = $('#toast');
        if ($toast.css('display') != 'none') return;

        $toast.fadeIn(100);
        setTimeout(function () {
            $toast.fadeOut(100);
        }, 1000);
    }

    // 点击时间按钮
    $('.weui-navbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        var _date = $(".weui-tab .weui-navbar .weui-bar__item_on .item-t").text();
        $("#time_hidden").val(_date);
        getDefaultSetting();

    });

    //点击确定按钮
    $('#showTooltips').on('click', function(){
        var workTime = $("#time_hidden").val();
        var date = '';
        if (workTime == "今天"){
            date = GetDateStr(0);
        }else if (workTime == "明天"){
            date = GetDateStr(1);
        }else {
            date = GetDateStr(2);
        }

        save(date);
    });

    //保存设置
    function save(date) {
        var isAppTow = 0;
        var switchStatue = 0;
        var id = $("#id").val();
        var workTime = date;
        var serviceStartTime = renderTime($("#serviceStartTime").val());
        var serviceEndTime = renderTime($("#serviceEndTime").val());
        var shopName = document.getElementById("shopId").innerText.trim();
        var board = $("#board").text();
        //isAppTow = $("#isAppTow").val();

        //switchStatue = $("#switchStatue").val();
        //console.log("isAppTow=" + isAppTow + "switchStatue=" + switchStatue);

        if($('#isAppTow').is(':checked')) {
            isAppTow = 1;
        }
        if($('#switchStatue').is(':checked')) {
            switchStatue = 1;
        }

        var shopId = "";
        if (shopName == "龙江店"){
            shopId = 'lj';
        }else if (shopName == "龙山店"){
            shopId = "ls";
        }else if(shopName == "容桂店"){
            shopId = 'rg';
        }
        $.ajax({
            type:"post",
            url:path + "/provider/saveOrUptSetting.do",
            dataType:"json",
            data:{
                "workTime":workTime,
                "serviceStartTime":serviceStartTime,
                "serviceEndTime":serviceEndTime,
                "board":board,
                "shopId":shopId,
                "id":id,
                "isAppTow":isAppTow,
                "switchStatue":switchStatue
            },
            success:function (data) {
                console.info(data);
                if (data == 1){
                    showOK();
                }
            }

        });


    }


    // 带T时间的转换
    function renderTime(date) {
        var dateee = new Date(date).toJSON();
        return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    }



    //获取今天 明天 后天
    function GetDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
        var y = dd.getFullYear();//获取当前年份的日期
        var m = dd.getMonth()+1;//获取当前月份的日期
        var d = dd.getDate();//获取当前天数的日期
        var h = dd.getHours(); //获取当前小时数
        var mm = dd.getMinutes(); //获取当前分钟数
        var s = dd.getSeconds(); //获取当前描述
        if(d >= 10){
            //return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s;
            //if(AddDayCount == 0 ){
            //	return y + "-" + m + "-" + d + " " +  h + ":" + mm + ":" + s;
            //}
            return y + "-" + m + "-" + d + " " +  h + ":" + mm + ":" + s ;
        }else{
            //if(AddDayCount == 0 ){
            //	return y + "-" + m + "-" + d + " " +  h + ":" + mm + ":" + s;
            //}
            return y + "-" + m + "-0" + d + " " +  h + ":" + mm + ":" + s;
        }

    }

    // 获取周
    function getNowFormatDate() {
        var date1 = new Date();
        var month1 = date1.getMonth() + 1;
        var strDate1 = date1.getDate();
        var time1 =  month1 +"月"+ strDate1+"日";
        var day1 = date1.getDay();
        var weekday=new Array(7)
        weekday[0]="周日";
        weekday[1]="周一";
        weekday[2]="周二";
        weekday[3]="周三";
        weekday[4]="周四";
        weekday[5]="周五";
        weekday[6]="周六";
        var week1 = weekday[day1];

        var date2 = new Date(date1);
        date2.setDate(date1.getDate()+1);
        var month2 = date2.getMonth() + 1;
        var strDate2 = date2.getDate();
        var time2 =  month2 +"月"+ strDate2+"日";
        var day2 = date2.getDay();
        var date3 = new Date(date1);
        date3.setDate(date1.getDate()+2);
        var month3 = date3.getMonth() + 1;
        var strDate3 = date3.getDate();
        var time3 =  month3 +"月"+ strDate3+"日";
        var day3 = date3.getDay();
        var week2 = weekday[day2];
        var week3 = weekday[day3];
        $("#time1").text(time1);
        $("#time2").text(time2);
        $("#time3").text(time3);
        $("#week1").text(week1);
        $("#week2").text(week2);
        $("#week3").text(week3);
    }
    getNowFormatDate();

    jQuery(document).ready(function(){
        getDefaultSetting();
    });

    // 获取默认设置
    function getDefaultSetting() {
        var workTime = $("#time_hidden").val();
        var date = null;
        if (workTime == "今天"){
            date = GetDateStr(0);
        }else if (workTime == "明天"){
            date = GetDateStr(1);
        }else {
            date = GetDateStr(2);
        }
        var shopName = document.getElementById("shopId").innerText.trim();
        var shopId = "";
        if (shopName == "龙江店"){
            shopId = 'lj';
        }else if (shopName == "龙山店"){
            shopId = "ls";
        }else if(shopName == "容桂店"){
            shopId = 'rg';
        }
        $.ajax({
            type:"post",
            url:path + "/provider/showAppointmentByTimeAndShop.do",
            dataType:"json",
            data:{
                "shopId":shopId,
                "workTime":date
            },
            success:function (data) {
                console.log(data.list);
                if (data.list.type == 1){
                    for (var i = 0;i<data.list.length;i++){
                        var app = data.list[i];
                        var _html = '<div class="weui-cells"';
                            _html += '<div class="weui-cell__hd">';
                            _html += '<img src="app.wxImg">';
                            _html += '<span class="weui-badge">3</span>';
                            _html += ' </div>';
                            _html += ' <div class="weui-cell__bd">';
                            _html += ' <p class="customer-name">'+ app.nickName +'</p>';
                            _html += '<p class="customer-start-time">'+ app.oppointmentTime + '</p>';
                            _html += '<p class="customer-end-time">'+app.countFinishedTime +'</p>';
                            _html += '</div>';
                        $(".lists").append(_html);
                    }

                }
            /*







                <div class="weui-cell__ft">
                    <p class="customer-pet">小型犬洗澡</p>
                    <p class="customer-pet">大型犬洗澡</p>
                    </div>
                    <div class="weui-cell__ft">
                    <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default">已通知</a>
                 </div>
            </div>
            */


            }

        });
    }

    // 转换成带T的时间
    function dealDate(date) {
        return date.substring(1,11)+"T"+date.substring(12,17);
    }
</script>
</body>
</html>


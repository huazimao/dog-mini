<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="/views/context/mytags.jsp"%>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>预约列表</title>
    <link rel="stylesheet" href="${context}/css/weui.css">
    <link rel="stylesheet" href="${context}/css/example.css">
    <link rel="stylesheet" href="${context}/css/common.css">
    <link rel="stylesheet" href="${context}/css/lists.css">
    <link rel="stylesheet" href="${context}/css/info.css">
    <style>
        .hide {
            width: 100%;
            height: 100%;
            overflow: hidden;
            position: fixed;
            left:0;
            top:0;
            background-color: rgba(0,0,0,.4);
            z-index:99;
            display: none;
        }
        .hide .hide-div {
            display: none;
            width: 300px;
            overflow: hidden;
            background-color: #fff;
            border-radius: 10px;
            position: absolute;
            left:50%;
            top:50%;
            transform: translate(-50%,-50%);
            padding:15px;
        }
        .hide .hide-close {
            width: 20px;
            height: 20px;
            background-color: #e5e5e5;
            text-align: center;
            line-height: 20px;
            border-radius: 100%;
            position: absolute;
            right:10px;
            top:10px;
            color: #555;
        }
        .hide .div-box {
            width: 100%;
            overflow: hidden;
            margin-top: 30px;
        }
        .hide .div-box-item {
            width: 100%;
            overflow: hidden;
            border-bottom: 1px solid #eee;
            font-size: 16px;
            padding: 10px 0;
        }
        .hide .box-item-left {
            float: left;
        }
        .hide .box-item-right {
            float: left;
        }
        .hide .box-item-list {
            width: 100%;
            overflow: hidden;
        }
        .hide .box-item-list ul {
            width: 100%;
            overflow: hidden;
            padding-left:33px;
        }
        .weui-btn-blue {
            background-color: #87CEFA!important;
        }
    </style>
    <script type="text/javascript" src="${context}/js/jquery-2.1.1.min.js"></script>
    <script>
        var path = '${context}';
        var arry = new Array();
        var cancelId = '';
    </script>
</head>
<body>

    <div class="weui-tab">
        <div class="hide">
            <div class="hide-div">
                <div class="div-box">
                    <div class="div-box-item">
                        <p class="box-item-left">昵称：</p>
                        <p class="box-item-right nickName"></p>
                    </div>
                    <div class="div-box-item">
                        <p class="box-item-left">电话：</p>
                        <p class="box-item-right tel">
                            <a href = "tel:15823456789" >15823456789 </a>
                        </p>
                    </div>
                    <div class="div-box-item">
                        <p class="box-item-left">预约时间：</p>
                        <p class="box-item-right appTime"></p>
                    </div>
                    <div class="div-box-item">
                        <p class="box-item-left">服务时间：</p>
                        <p class="box-item-right quantum"></p>
                    </div>
                    <div class="div-box-item">
                        <p class="box-item-title">预约列表：</p>
                        <div class="box-item-list">
                            <ul class="item-list">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="hide-close">x</div>
            </div>
        </div>
        <div class="weui-navbar">
            <input type="hidden" id="time_hidden" name="workTime" value="今天">
            <input type="hidden" id="shopId" name="shopId" value="${shopId}">
            <input type="hidden" id="level" name="level" value="${level}">
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
        <div class="name-btn">
            <div class="address-name">
                <c:if test="${shopId == 'lj'}">
                    龙江店
                </c:if>
                <c:if test="${shopId == 'ls'}">
                    龙山店
                </c:if>
                <c:if test="${shopId == 'rg'}">
                    容桂店
                </c:if>
            </div>
            <%--<div class="button-sp-area" style="display: none">--%>
                <a href="${context}/provider/go2defaultSettingPage.do?shopId=${shopId}" class="weui-btn weui-btn_mini weui-btn_default setting-btn" style="display: none;">系统设置</a>
            <%--</div>--%>
        </div>

        <div class="weui-tab__bd">
            <div id="tab1" class="weui-tab__bd-item">
                <div class="lists">
                    <div class="weui-cells">



                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--BEGIN dialog1-->
    <div class="js_dialog" id="iosDialog1" style="display: none;">
        <div class="weui-mask"></div>
        <div class="weui-dialog">
            <div class="weui-dialog__hd"><strong class="weui-dialog__title">撤单操作</strong></div>
            <div class="weui-dialog__bd">撤单后将不再为该客户服务，您可以在详情中查看客户电话与客户沟通。</div>
            <div class="weui-dialog__ft">
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" onclick="closeMsg()">取消</a>
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" onclick="cancelApp()">撤单</a>
            </div>
        </div>
    </div>
    <!--END dialog1-->

    <!--BEGIN dialog2-->
    <div class="js_dialog" id="iosDialog2" style="display: none;">
        <div class="weui-mask"></div>
        <div class="weui-dialog">
            <div class="weui-dialog__bd"></div>
            <div class="weui-dialog__ft">
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" onclick="closeMsg()">知道了</a>
            </div>
        </div>
    </div>
    <!--END dialog2-->
<script src="${context}/js/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src="${context}/js/example.js"></script>
<script src="${context}/js/sysetting.js"></script>
<script type="text/javascript">

    $(function(){
        var $iosDialog1 = $('#iosDialog1'),
            $iosDialog2 = $('#iosDialog2'),
            $androidDialog1 = $('#androidDialog1'),
            $androidDialog2 = $('#androidDialog2');

        $('#dialogs').on('click', '.weui-dialog__btn', function(){
            $(this).parents('.js_dialog').fadeOut(200);
        });

        $('#showIOSDialog1').on('click', function(){
            $iosDialog1.fadeIn(200);
        });
        $('#showIOSDialog2').on('click', function(){
            $iosDialog2.fadeIn(200);
        });
        $('#showAndroidDialog1').on('click', function(){
            $androidDialog1.fadeIn(200);
        });
        $('#showAndroidDialog2').on('click', function(){
            $androidDialog2.fadeIn(200);
        });
    });


    $(".hide .hide-close").click(function () {
        $(".hide-div").hide();
        $(".hide").hide();
    });
    $(".hide").click(function () {
       $(".hide-div").hide();
       $(".hide").hide();
    });
    $(".hide .hide-div").click(function (e) {
       e.stopPropagation();
    });

    //显示客户详情
    function showInfo(i) {
        var app = arry[i];
        $(".hide").show();
        $(".hide-div").show();

        $(".hide .nickName").text(app.nickName);



        // $(".hide .tel").html('<a> href = ' + " tel : +'app.phone'+  > " + app.phone + '</a>');
        $(".hide .tel").html('<a href ="tel:'+app.phone+'">'+app.phone+'</a>');



        $(".hide .appTime").text(app.oppointmentTimeStr);
        // $(".hide .item-list").text(app.petLists);
        $(".item-list").html('');
        for(var i=0;i<app.petLists.length;i++){
            var likindPet = app.petLists[i].kindPet;
            var lisize = app.petLists[i].size;
            var likindService = app.petLists[i].kindService;
            if(likindPet!=undefined&&lisize!=undefined&&likindService!=undefined){
                var _likindPet = "";
                var _lisize = "";
                var _likindService ="";
                switch (likindPet) {
                    case "dog" :
                        _likindPet = "狗";
                        switch (lisize) {
                            case "mini" :
                                _lisize = "小型";
                                break;
                            case "normal" :
                                _lisize = "中型";
                                break;
                            case "large" :
                                _lisize = "大型";
                                break;
                        }
                        break;
                    case "cat" :
                        _likindPet = "猫";
                        switch (lisize) {
                            case "normal" :
                                _lisize = "幼年";
                                break;
                            case "large" :
                                _lisize = "成年";
                                break;
                        }
                        break;
                }
                switch (likindService) {
                    case "wash" :
                        _likindService = "洗澡";
                        break;
                    case "modeling" :
                        _likindService = "造型";
                        break;
                    case "SPA" :
                        _likindService = "SPA";
                        break;
                }
                var litext = ' <li>'+_lisize+' '+ _likindPet+' '+_likindService +'</li>';
                //console.log(litext);
                $(".item-list").append(litext);
            }

        }
        //转化服务区间
        var _quantum = '';
        switch (app.quantum){
            case 0:
                _quantum = '9:00 - 10:00';
                break;
            case 1:
                _quantum = '10:00 - 11:00';
                break;
            case 2:
                _quantum = '11:00 - 12:00';
                break;
            case 3:
                _quantum = '12:00 - 13:00';
                break;
            case 4:
                _quantum = '13:00 - 14:00';
                break;
            case 5:
                _quantum = '14:00 - 15:00';
                break;
            case 6:
                _quantum = '15:00 - 16:00';
                break;
            case 7:
                _quantum = '16:00 - 17:00';
                break;
            case 8:
                _quantum = '17:00 - 18:00';
                break;
        }
        $(".hide .quantum").text(_quantum);

    }

    //隐藏弹出框
    function closeMsg() {
        $(this).parents('.js_dialog').fadeOut(200);
        location.reload();
    }

    //显示弹出框
    function showMsg(msg) {
        var $iosDialog2 = $('#iosDialog2');
        $(".js_dialog .weui-dialog .weui-dialog__bd").html(msg);
        $iosDialog2.fadeIn(200);
    }
    
    //显示撤单弹框
    function showCancelApp(appointmentId) {
        var $iosDialog1 = $('#iosDialog1');
        $iosDialog1.fadeIn(200);
        cancelId = appointmentId;
    }

    //撤单
    function cancelApp() {
        $.ajax({
            type:"post",
            url:path + "/provider/cancelApponitment.do",
            dataType:"json",
            data:{
                "appointmentId":cancelId
            },
            success:function (data) {
                location.reload();
            }
        });
    }

    //完成服务
    function doneApp(openid,dtype,appointmentId) {
        var shopId = $("#shopId").val();
        $.ajax({
            type:"post",
            url:path + "/provider/doneApponitment.do",
            dataType:"json",
            data:{
                "shopId":shopId,
                "openid":openid,
                "dtype":dtype,
                "appointmentId":appointmentId
            },
            success:function (data) {
                console.log(data);
                showMsg(data.msg);
            }

        });
    }

    // 获取默认设置
    function getDefaultSetting() {
        var workTime = $("#time_hidden").val();
        var shopId = $("#shopId").val();
        var date = null;
        if (workTime == "今天"){
            date = GetDateStr(0);
        }else if (workTime == "明天"){
            date = GetDateStr(1);
        }else {
            date = GetDateStr(2);
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
                arry = (data.list);
                $(".lists").text("");
                if (data.type == 1){
                    for (var i = 0;i<data.list.length;i++){
                        var app = data.list[i];
                        var _html = '<div class="weui-cell">';
                            _html += '<div class="weui-cell__hd" onclick="showInfo('+i+')">';
                            _html += '<img src='+app.wxImg+'>';
                            // _html += '<span class="weui-badge">3</span>';
                            _html += '</div>';
                            _html += '<div class="weui-cell__bd">';
                            _html += '<p class="customer-name" onclick="showInfo('+i+')">'+app.nickName+'</p>';
                            _html += '<p class="customer-start-time" onclick="showInfo('+i+')">预约时间：'+app.appStr+'</p>';
                            _html += '<p class="customer-end-time" onclick="showInfo('+i+')">完成时间：'+app.accStr+'</p>';
                            _html += '</div>';
                            _html += '<div class="weui-cell__ft">';
                            for (var x = 0;x<app.petLists.length;x++){
                                var pets = app.petLists[x];
                                var _pets = '';
                                switch (pets.kindPet){
                                    case 'cat':
                                        _pets +="猫";
                                        switch (pets.size){
                                            case 'normal':
                                                _pets +="幼年"
                                                break;
                                            case 'large':
                                                _pets +="成年"
                                                break;
                                        }
                                        break;
                                    case 'dog':
                                        _pets +="犬";
                                        switch (pets.size){
                                            case 'mini':
                                                _pets +="小型"
                                                break;
                                            case 'normal':
                                                _pets +="中型"
                                                break;
                                            case 'large':
                                                _pets +="大型"
                                        }
                                        break;
                                }
                                switch (pets.kindService){
                                    case 'wash':
                                        _pets +="洗澡"
                                        break;
                                    case 'modeling':
                                        _pets +="造型"
                                        break;
                                    case 'SPA':
                                        _pets +="SPA"
                                        break;
                                }
                                _html += '<p class="customer-pet">'+_pets+'</p>';

                            }
                            _html += '</div>';
                            _html += '<div class="weui-cell__ft">';
                            switch (app.appointmentState){
                                case 1:
                                    _html += '<a href="javascript:doneApp(\''+app.openid+'\',\''+app.dtype+'\',\''+app.appointmentId+'\')" class="weui-btn weui-btn_mini weui-btn_primary">去完成</a>';
                                    _html += '<a href="javascript:showCancelApp('+app.appointmentId +')" class="weui-btn weui-btn_mini weui-btn_warn">撤单</a>';
                                    break;
                                case 2:
                                    _html += '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default weui-btn-blue">已完成</a>';
                                    break;
                                case 3:
                                    _html += '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default weui-btn-orange">已撤单</a>';
                                    break;
                                case 4:
                                    _html += '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default weui-btn-orange">已爽约</a>';
                                    break;
                                case 5:
                                    _html += '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default weui-btn-orange">通知失败</a>';
                                    break;
                            }
                            _html += '</div>';
                            _html += '</div>';
                        $(".lists").append(_html);
                    }
                }
            }
        });
    }

    // 转换成带T的时间
    function dealDate(date) {
        return date.substring(1,11)+"T"+date.substring(12,17);
    }

    // 点击时间按钮
    $('.weui-navbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        var _date = $(".weui-tab .weui-navbar .weui-bar__item_on .item-t").text();
        $("#time_hidden").val(_date);
        getDefaultSetting();
    });

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
        $(".setting-btn").show();
        getDefaultSetting();
        var level = $("#level").val();
        console.log(level);
        //alert(level);
        /*if (level == 2){
            //显示设置按钮
        }*/
    });

</script>
</body>
</html>


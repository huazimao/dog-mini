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
</head>

<body>
    <div class="weui-tab">
        <form id="a1">
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
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">学徒</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="traineeNum" name="traineeNum"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">学徒因子</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="traineeFactor" name="traineeFactor"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">熟手</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="skillerNum" name="skillerNum"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">熟手因子</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="skillerFactor" name="skillerFactor"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label for="" class="weui-label">开始时间</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="datetime-local" value="" placeholder="" id="serviceStartTime" name="serviceStartTime"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label for="" class="weui-label">结束时间</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="datetime-local" value="" placeholder="" id="serviceEndTime" name="serviceEndTime"/>
                        </div>
                    </div>
                </div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell weui-cell_switch">
                        <div class="weui-cell__bd">自动开启第三天预约</div>
                        <div class="weui-cell__ft">
                            <label for="isAppTow" class="weui-switch-cp">
                                <input id="isAppTow" class="weui-switch-cp__input" type="checkbox" name="isAppTow"/>
                                <div class="weui-switch-cp__box"></div>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell weui-cell_switch">
                        <div class="weui-cell__bd">预约总开关</div>
                        <div class="weui-cell__ft">
                            <label for="switchStatue" class="weui-switch-cp">
                                <input id="switchStatue" class="weui-switch-cp__input" type="checkbox" name="switchStatue"/>
                                <div class="weui-switch-cp__box"></div>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" >确定</a>
                </div>
            </div>
        </form>
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
<!--BEGIN toast-->
<!--end toast-->
<script type="text/javascript">
    // toast
    $(function(){
        var $toast = $('#toast');
        $('#showToast').on('click', function(){
            if ($toast.css('display') != 'none') return;

            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        });
    });
</script>
<script>

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
        var workTime = date;
        var traineeNum = $("#traineeNum").val();
        var traineeFactor = $("#traineeFactor").val();
        var skillerNum = $("#skillerNum").val();
        var skillerFactor = $("#skillerFactor").val();
        var serviceStartTime = renderTime($("#serviceStartTime").val());
        var serviceEndTime = renderTime($("#serviceEndTime").val());
        var id = $("#id").val();
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
            url:getRootPath_dc() + "/provider/saveOrUptSetting.do",
            dataType:"json",
            data:{
                "workTime":workTime,
                "traineeNum":traineeNum,
                "traineeFactor":traineeFactor,
                "skillerNum":skillerNum,
                "skillerFactor":skillerFactor,
                "serviceStartTime":serviceStartTime,
                "serviceEndTime":serviceEndTime,
                "shopId":shopId,
                "id":id
            },
            success:function (data) {
                console.info(data);
            }

        });

        
    }


    // 带T时间的转换
    function renderTime(date) {
        var dateee = new Date(date).toJSON();
        return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    }

    // 获取项目路径
    function getRootPath_dc() {
        var pathName = window.location.pathname.substring(1);
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
            return window.location.protocol + '//' + window.location.host;

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
    /*$.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                o[this.name]+= ',' + this.value;
            } else {
                o[this.name] = this.value || '';
            }
        });
        var $radio = $('input[type=radio],input[type=checkbox]',this);
        $.each($radio,function(){
            if(!o.hasOwnProperty(this.name)){
                o[this.name] = '';
            }
        });
        return o;
    };*/

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
            url:getRootPath_dc() + "/provider/getSySettingByShopIdAndTime.do",
            dataType:"json",
            data:{
                "shopId":shopId,
                "workTime":date
            },
            success:function (data) {
                console.log(data.systemSetting);
                var systemSetting = data.systemSetting;
                $("#traineeNum").val(systemSetting.traineeNum);
                $("#traineeFactor").val(systemSetting.traineeFactor);
                $("#skillerNum").val(systemSetting.skillerNum);
                $("#skillerFactor").val(systemSetting.skillerFactor);
                $("#id").val(systemSetting.id);
                var start = dealDate(systemSetting.startStr);
                var end = dealDate(systemSetting.endStr);
                $("#serviceStartTime").val(start);
                $("#serviceEndTime").val(end);
                var switchStatue = systemSetting.switchStatue;
                var isAppTow = systemSetting.isAppTow;
                if (switchStatue == 1){
                    $("#switchStatue").attr("checked", true);
                }
                if (isAppTow == 1){
                    $("#isAppTow").attr("checked", true);
                }
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


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
        <div class="weui-navbar">
            <div class="weui-navbar__item weui-bar__item_on">
                <div class="item-t">今天</div>
                <div class="item-b">
                    <span class="item-date" id="time1">2月24日</span>
                    (<span class="item-day" id="week1">周三</span>)
                </div>
            </div>
            <div class="weui-navbar__item">
                <div class="item-t">明天</div>
                <div class="item-b">
                    <span class="item-date" id="time2">2月25日</span>
                    (<span class="item-day" id="week2">周四</span>)
                </div>
            </div>
            <div class="weui-navbar__item">
                <div class="item-t">后天</div>
                <div class="item-b">
                    <span class="item-date" id="time3">2月26日</span>
                    (<span class="item-day" id="week3">周五</span>)
                </div>
            </div>
        </div>
        <div class="weui-tab__panel">
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">龙江店</label></div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">学徒</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="trainee_num"/>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">学徒因子</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="trainee_factor"/>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">熟手</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="skiller_num"/>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">熟手因子</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number" pattern="[0-9]*" placeholder="" id="skiller_factor"/>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label for="" class="weui-label">开始时间</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="datetime-local" value="" placeholder="" id="service_start_time"/>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label for="" class="weui-label">结束时间</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="datetime-local" value="" placeholder="" id="service_end_time"/>
                    </div>
                </div>
            </div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell weui-cell_switch">
                    <div class="weui-cell__bd">自动开启第三天预约</div>
                    <div class="weui-cell__ft">
                        <label for="is_app_tow" class="weui-switch-cp">
                            <input id="is_app_tow" class="weui-switch-cp__input" type="checkbox" checked="checked"/>
                            <div class="weui-switch-cp__box"></div>
                        </label>
                    </div>
                </div>
            </div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell weui-cell_switch">
                    <div class="weui-cell__bd">预约总开关</div>
                    <div class="weui-cell__ft">
                        <label for="switch_statue" class="weui-switch-cp">
                            <input id="switch_statue" class="weui-switch-cp__input" type="checkbox" checked="checked"/>
                            <div class="weui-switch-cp__box"></div>
                        </label>
                    </div>
                </div>
            </div>
            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
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

    $('.weui-navbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        var _date = $(".weui-tab weui-navbar weui-navbar__item weui-bar__item_on item-b").val();
        alert("=0000=" + _date);
    });

    //点击确定按钮
    $('#showTooltips').on('click', function(){
        alert("aaa");
    });



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
</script>
</body>
</html>


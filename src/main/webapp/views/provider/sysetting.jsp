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
</head>

<body>
    <div class="page__bd">
        <div class="weui-cells__title">系统设置</div>
        <div class="weui-cells weui-cells_form">
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

<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content">设置成功</p>
    </div>
</div>

<!--BEGIN toast-->
<!--end toast-->
<script type="text/javascript">
    /*// toast
    $(function(){
        var $toast = $('#toast');
        $('#showToast').on('click', function(){
            if ($toast.css('display') != 'none') return;

            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        });
    });*/
</script>
    <script type="text/javascript" src="${context}/js/jquery-2.1.1.min.js"></script>
<script src="${context}/js/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src=".${context}/js/example.js"></script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/context/mytags.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width"><meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>请选择店铺</title>
    <link rel="stylesheet" href="${context}/css/weui.css">
    <link rel="stylesheet" href="${context}/css/example.css">
    <script src="${context}/js/jquery-2.1.1.min.js"></script>
    <script>
        var path = '${context}';
    </script>
</head>

<body>
<div class="weui-cells">
    <a class="weui-cell weui-cell_access" href="javascript:;" onclick="o1()">
        <div class="weui-cell__bd" >
            <p>龙江店</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;"onclick="o2()">
        <div class="weui-cell__bd">
            <p>龙山店</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;"onclick="o3()">
        <div class="weui-cell__bd">
            <p>容桂店</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
</div>
<script src="${context}/js/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src="${context}/js/example.js"></script>
</body>
<script>
    function o1() {
        window.location.href= path + '/provider/go2appointmentPage.do?shopId=lj';

    }

    function o2() {
        window.location.href= path + '/provider/go2appointmentPage.do?shopId=ls';
    }

    function o3() {
        window.location.href= path + '/provider/go2appointmentPage.do?shopId=rg';
    }
</script>
</html>

<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page contentType="text/html;charset=utf-8"%>
<html lang="zh-cmn-Hans">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,viewport-fit=cover">
	<title>WeUI</title>
	<link rel="stylesheet" href="${context}/css/weui.css"/>
	<link rel="stylesheet" href="${context}/css/example.css"/>
</head>
<body ontouchstart>

<div class="container" id="container"></div>


<script type="text/html" id="tpl_navbar">
	<div class="page">
		<div class="page__bd" style="height: 100%;">
			<div class="weui-tab">
				<div class="weui-navbar">
					<div class="weui-navbar__item weui-bar__item_on">
						选项一
					</div>
					<div class="weui-navbar__item">
						选项二
					</div>
					<div class="weui-navbar__item">
						选项三
					</div>
				</div>
				<div class="weui-tab__panel">

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
        $(function(){
            $('.weui-navbar__item').on('click', function () {
                $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
            });
        });
</script>
<script src="${context}/js/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src="${context}/js/example.js"></script>
</body>
</html>

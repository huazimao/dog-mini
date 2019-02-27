<!DOCTYPE html >
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page contentType="text/html;charset=utf-8"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<link rel="stylesheet" href="${context}/css/weui.css"/>
	<link rel="stylesheet" href="${context}/css/example.css"/>
	<!-- iOS忽略将页面中的数字识别为电话号码-->
	<meta name="format-detection" content="telephone=no">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

	<%--<script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.8.1/less.min.js" ></script>--%>
	<%--<style>
		*{
			margin:0;
			padding:0;
			/*font-size: 26.6667vw;*/
			box-sizing: border-box;
		}
		.weui-navbar {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 100%;
			height: 52px;
			border-bottom: 1px solid #eee;
			margin-bottom: 20px;
		}

		.weui-navbar__item {
			flex:1;
			height:50px;
			line-height: 50px;
			text-align: center;
			border-right: 1px solid #eee;
			background-color: #fff;
		}
		.weui-navbar__item:last-child{
			border-right: none;
		}
		.weui-bar__item_on {
			background-color: #fafafa;
		}

	</style>--%>
	<%--<style>
		.weui-navbar {
			display: flex;
			position: absolute;
			z-index: 500;
			top: 0;
			width: 100%;
			background-color: #FAFAFA;

		&:after {
		 .setBottomLine(#CCCCCC);
		 }

		& + .weui-tab__panel {
			  padding-top: 50px;
			  padding-bottom: 0;
		  }
		}

		.weui-navbar__item {
			position: relative;
			display: block;
			flex: 1;
			padding: 13px 0;
			text-align: center;
			font-size: 15px;
		.setTapColor();

		&:active {
			 background-color: @weuiBgColorActive;
		 }

		&.weui-bar__item_on {
			 background-color: @weuiBgColorActive;
		 }

		&:after {
		 .setRightLine(#CCCCCC);
		 }

		&:last-child {
		&:after {
			 display: none;
		 }
		}
		}
	</style>


<div class="weui-navbar">
	<div class="weui-navbar__item weui-bar__item_on">
		今天
	</div>
	<div class="weui-navbar__item">
		明天（日期）
	</div>
	<div class="weui-navbar__item">
		后天（日期）
	</div>
</div>--%>
<%--<div class="weui-navbar">
	<div class="weui-navbar__item weui-bar__item_on">
		今天
	</div>

	<div class="weui-navbar__item">
		后天（日期）
	</div>
</div>--%>
</head>
<body>
			<div class="weui-tab">
				<div class="weui-navbar">
					<div class="weui-navbar__item weui-bar__item_on">
						选项一
					</div>
					<div class="weui-navbar__item">
						选项二
					</div>

				</div>
				<div class="weui-tab__panel">

				</div>
			</div>

<script src="${context}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<script src="${context}/js/example.js"></script>
<script type="text/javascript">
	$(function(){
		$('.weui-navbar__item').on('click', function () {
			alert(1);
            $('.weui-bar__item').removeClass('weui-bar__item_on');
			$(this).addClass('weui-bar__item_on');
		});
	});
</script>
</body>
	</html>



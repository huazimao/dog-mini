<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/views/context/mytags.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <script src="${context}/js/jquery-2.1.1.min.js"></script>
</head>

<body>
<span> 显示该用户openId:${openId}</span>
<img src="${context}/image/test/cat-01.jpg">
<input type="hidden" value="${context}">
</body>
<script>
    jQuery(document).ready(function () {
        $.ajax({
            type: "POST",
            url: getRootPath_dc() + "/test/test2.do",
            async:false,
            data: {

            },
            dataType: "json",
            success: function (data) {
                alert(data);
            }
        })


    });
</script>
</html>
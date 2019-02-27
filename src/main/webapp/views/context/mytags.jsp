<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page import="com.kingmao.dog.utils.SysCommon"%>

<%-- <% 
String path = request.getContextPath();
String basePath =  "https://" + request.getServerName()
		+ path + "/";
%>
<base href="<%=basePath%>"/>
<c:set var="context"
	value="https://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:set var="baseUrl"
	value="https://${pageContext.request.serverName}${pageContext.request.contextPath}" /> 
	
<% 
String basePath =  request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%=basePath%>"/>
<c:set var="context"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:set var="baseUrl"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
--%>
<%
	String basePath =  SysCommon.getSysUrl();
%>
<base href="<%=basePath%>"/>
<c:set var="context"
	   value="<%=basePath%>" />
<c:set var="baseUrl"
	   value="<%=basePath%>" />
<script>
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
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>待开发的页面</title>
 	<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" /> 
	<jsp:include page="left.jsp" flush="true" />
	
  </head>
  
  <body>
    <div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>尚未开发</h5>  
			</div> 
			<h1>施工中</h1>
	</div>
  </body>
</html>

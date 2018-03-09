<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html> 
	<head> 
		 <title>通知</title>
		 <link rel="stylesheet" href="<%=basePath %>css/ctdms.css" />
		 
		<jsp:include   page="left.jsp" flush="true"/> 
		 <link rel="stylesheet" href="<%=basePath %>css/ctdms_notices.css" />
	 	</head>

	<body>
		 
		<div class="module-frame" id="iframe"> 
			<div class="newsticker">
				<ul class="newsticker-list">
				<li class="newsticker-item">
					通知
				</li>
				<li class="newsticker-item">
					再通知:That creature his bring waters...
				</li>
				<li class="newsticker-item">
					再次通知:And also. Firmament and Give....
				</li>
			</ul>
		</div>
		
		<c:forEach var="nt" items="${noticesdto }">  
			<div class="msg-container fadeInUp animated" id="news">
				<dl class="msgtype"> ${nt.typeName }</dl> 
				<c:if test="${empty nt.notices }">
				    <dl>
						<dt>暂无任何通知</dt>
					</dl>
				</c:if>
				<c:forEach var="n" items="${nt.notices }">  
					<dl>
						<dt>${n.title }</dt>
						<dd>
							<p>${n.message }</p>
							<p>
								<span class="time">${n.time }</span>
								<span class="user">${n.author }</span>
							</p>
						</dd>
					</dl> 
				</c:forEach>
				<c:if test="${(notices)!= null && fn:length(notices) > 5}"> 
				   <div class="more">
						<a href="#">查看更多</a>
					</div>
				</c:if> 
			</div>
		</c:forEach>
	 
		</div>
	</body>

	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.newsticker.js"></script>
	<script>
		(function() {

			$('dd').filter(':nth-child(n+4)').addClass('hide');
			$('dl').on('click', 'dt', function() {
				$(this).next().slideToggle(200);
			});
		})(); 	
	</script>

</html>
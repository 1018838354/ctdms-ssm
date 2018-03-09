<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<title>发布通知</title>
<link rel="stylesheet" href="<%=basePath %>css/ctdms.css" />
<jsp:include page="left.jsp" flush="true" />
<style type="text/css">
input#title {
	width: 290px;
	height: 40px;
	color: #000;
	display: block;
	padding-left: 10px;
	line-height: normal;
	font-weight: bold;
	border-radius: 5px;
}

input#submit {
	display: block;
	margin: 10px auto;
	margin-bottom: 8px;
	width: 300px;
	height: 40px;
	line-height: 40px;
	background-color: #25c6ff;
	color: #fff;
	font-weight: 700;
	outline: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="container module-frame" id="iframe">
		<div class="frame-title">
			<h5>发布通知</h5>
		</div>
		<form action="notices" class="fadeInUp animated" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input id="title" type="text"
				name="title" placeholder="请输入标题" autocapitalize="off"
				autocomplete="off" />
			<div id="editor" class="text">
				<!--可使用 min-height 实现编辑区域自动增加高度-->
			</div>
			<input type="hidden" id="message" name="message" /> <input
				type="hidden" id="noticesType" name="noticesType" value="学院消息" /> <input
				type="submit" id="submit" onclick="msg()" value="提交" />
		</form>
	</div>
	<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
	<script type="text/javascript" src="<%=basePath %>js/wangEditor.min.js"></script>
	<script type="text/javascript">
			var E = window.wangEditor
			var editor = new E('#editor')
			// 或者 var editor = new E( document.getElementById('#editor') )
			editor.customConfig.uploadImgServer = '/upload' // 上传图片到服务器
			editor.create(); 
			function msg() {
				// 读取 html
				document.getElementById('message').value = editor.txt.html(); 
			}
		</script>
</body>

</html>
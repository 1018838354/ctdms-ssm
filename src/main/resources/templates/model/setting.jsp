<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>设置</title>
<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" /> 
<jsp:include page="left.jsp" flush="true" /> 
<link rel="stylesheet" href="<%=basePath%>css/ctdms_submit.css" /> 
<link rel="stylesheet" href="<%=basePath%>css/ctdms_setting.css" /> 
<style> 
a {
	color: #06C;
	cursor: pointer;
}
 
</style>
</head>
<body>  
		<div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>设置</h5> 
				
			</div> 
			
			<div class="module-toolbar">
		<div class="toolbar-left">
		
		<div class="upload-button-blue" onclick="getTermLayer()">
		<span class="button-contain">
		<i class="fa fa-retweet fa-lg"></i>
		<div class="text">
		 更新当前学期</div>
		 </span></div> 
		 
		<div class="ctdms-btn-small file">
		  <form class="uploadForm" action="<%=basePath %>cp?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" >
			<i class="fa fa-upload fa-lg"></i>
		 	上传排课计划
		  <input type="file"name="file"  /> 
		 </form>
		 </div> 
		  
			<div class="upload-button-blue" onclick="getDateLayer()">
				<span class="button-contain"> <i
					class="fa fa-plus-square-o fa-lg"></i>
					<div class="text">设定教学文档上传时段</div>
				</span>
			</div>
			<div class="upload-button-blue" onclick="getDocTypeLayer()">
				<span class="button-contain"> <i
					class="fa fa-cog fa-spin fa-lg"></i>
					<div class="text">修改可上传文档类型</div>
				</span>
			</div>
			<div class="upload-button-blue" onclick="getTextBookExcel()">
				<span class="button-contain"> <i
					class="fa fa-file-excel-o fa-lg"></i>
					<div class="text">导出申报教材Excel表格</div>
				</span>
			</div>
		</div>
	</div>
			<table class="table table-hover">
				<caption>操作历史记录</caption>
				<thead>
					<tr>
						<th>操作<th>
						<th>内容</th>
						<th>用户名<th>
						<th>时间<th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			
		</div>  
</body> 

<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>js/jquery-form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript"> 
layer.config({
	offset : 150,shadeClose : true
});

function getDateLayer() {
	layer.open({
		type : 2,
		shade : 0.1,
		title : false, 
		area : [ '500px', '500px' ],
		closeBtn : 0,
		shadeClose : true,
		skin : 'layui-layer-rim',
		content : 'limit'
	});
}

function getTermLayer() {
	layer.prompt({
		title : '修改当前学期为(尚未实现)'
	}, function(val, index) {
		layer.msg('得到了' + val);
		layer.close(index);
	});
}
function getDocTypeLayer() {
	layer.prompt({
		title : '可上传文档类型设置(尚未实现)'
	}, function(val, index) {
		layer.msg('得到了' + val);
		layer.close(index);
	});
}
function bindSubmit() {
	$('.uploadForm').bind("submit", function() {
		layer.load();
		var options = {
			dataType : "json", 
			type : "post",
			success : function(data) {
				layer.closeAll('loading');
				layer.msg('上传成功', {icon: 1},function(){ 
        			location.reload(true);   
        		});   
			},
			error : function(data){
				 layer.closeAll('loading');
				 layer.msg('上传失败', {icon: 5});  
			}
		};
		$(this).ajaxSubmit(options); 
		return false;
	});
}
function change() {
	$(".uploadForm").on("change", "input[type='file']", function() {
		var filePath = $(this).val();
		console.log(filePath);
		if (filePath.indexOf("xls") != -1) {
			$(this).parent().submit();
		}
		return false;
	});
}

window.onload = function() { 
	bindSubmit();
	change();
};
</script>
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
</html>
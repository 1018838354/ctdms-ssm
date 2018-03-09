<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title></title>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css"> 
<style>

.dateMessage_carry{
	width:400px;
	padding: 30px;
	height: 300px;
	margin:0px auto  ; 
}
.form-group .btn-primary{ 
	width: 30%;
}</style>
</head>
<body  style="padding-top: 50px">
	<div class="dateMessage_carry">
		<form class="form-horizontal" id="infoForm" >
			<div class="form-group"  style="margin-bottom: 100px">
				<label for="name">选择文档类型: <span class="label label-warning">必填</span></label>
				    <select class="form-control" id="type-select">
					      <option value="all">全部</option>
					      <option value="teach">教学进度表</option>
					      <option value="syllabus">教学大纲</option>
					      <option value="other">其他</option>
					</select> 
			</div> 

			<div class="form-group"  style="margin-bottom: 30px">
				<label for="name">提交文档日期范围: <span class="label label-danger">必填</span></label>
				<div class="input-daterange input-group" id="datepicker">
					<input type="text" class="form-control" name="start" id="qBeginTime" placeholder="开始日期"/> 
					<span class="input-group-addon">至</span>
					<input type="text" class="form-control" name="end" id="qEndTime"  placeholder="结束日期"/>
				</div>
			</div>

			<div class="form-group" >
				<button type="button" class="btn btn-primary" style="display: block;margin:15px auto;"
					onclick="javascript:Submit();" class="btn btn-default">确定</button>
			</div>
		</form>


	</div>
</body>

<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script> 

<script
	src="<%=basePath%>js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=basePath%>js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/bootstrap-datepicker.zh-CN.min.js"></script>

<script> 
		function dateReplace(e){
			e=e.replace("年","-");
			e=e.replace("月","-");
			e=e.replace("日","");
			return e;
		}
		function Submit(){
			var beginVal = $("#qBeginTime").val();
			var endVal = $("#qEndTime").val();
			if(beginVal.length<10||endVal.length<10){
				alert("请选择日期");
				return;
			} 
			var _start = dateReplace(beginVal);
			var _end = dateReplace(endVal); 
			
			var type = $('#type-select option:selected').val(); 
			console.log(type); 
		    var url="limit"; 
		    var data = {
		    		${_csrf.parameterName}:"${_csrf.token}",
		    		tab : type,
		    		start : _start,
		    		end : _end
		    };
		    console.log(url); 
		    ajaxSubmit(url,data);
		} 
		function ajaxSubmit(_url,_data){ 
		      	var options = {  
	         		url:_url,
	         		type : "post",
	         		data:_data,
	    			dataType : "json", 
	        	    success: function (data) {  
	        	    	parent.window.location.href="setting"; 
	                },
	                error: function (data){ 
	                  $(".btn-default").html('确定');
	                 	alert('服务器未响应');
	                }
	         };
	         $(".btn-default").html('修改中...');
			$.ajax(options);  
		} 
		
		$('#qBeginTime').datepicker({ 
			todayHighlight:true,
			language: 'zh-CN', 
			autoclose : true, 
		}).on('changeDate',function(e){
			var startTime = e.date;
			$('#qEndTime').datepicker('setStartDate',startTime);
		});
		//结束时间：
		$('#qEndTime').datepicker({
			todayHighlight:true, 
			language: 'zh-CN', 
			autoclose : true, 
		}).on('changeDate',function(e){
			var endTime = e.date;
			$('#qBeginTime').datepicker('setEndDate',endTime);
		}); 
		
		
	</script>
</html>

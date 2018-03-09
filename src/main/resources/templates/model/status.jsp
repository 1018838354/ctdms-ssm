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
<meta name="toTop" content="true">
<title>提交情况</title>
<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" />

<jsp:include page="left.jsp" flush="true" />
<link rel="stylesheet" href="<%=basePath%>css/ctdms_submit.css" />
<link rel="stylesheet" href="<%=basePath%>css/ctdms_status.css" />
<style>
.col-md-8 {
	width: 100%;
} 

a {
	color: #06C;
	cursor: pointer;
}

td,th {
	text-align: center;
}
table {
min-width:550px; 
} 
 

</style>
</head>
<body > 
	<div class="">

		<div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>文档提交进度</h5>
				<div class="nowrap pull-right" style="margin-top:10px">
				    <div class="ctdms-btn-small"><i class="glyphicon glyphicon-floppy-saved"></i> 导出为xls</div>
				    <div class="ctdms-btn-small"><i class="	glyphicon glyphicon-floppy-save"></i> 导出为txt</div>
				</div> 
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<div class="tab">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs">
							<li id="teach"><a href="status">按课程显示</a></li>
							<li id="syllabus"><a href="status?tab=syllabus">按教师显示</a>
							</li>
						</ul>
						<div class="tab-content tabs  fadeInUp animated">
							<div class="tab-pane fade in active" id="panel">
 
 <div style="border:1px solid #E5E5E5" class="fadeInUp animated">
		<table class="table table-bordered" id="table">
			<caption>
				<span>课程文档情况统计按</span> <select
					id="select" style="margin-top:6px">
					<option>是否提交</option>
					<option>是否通过全部审核</option>
					<option>是否通过第一次审核</option>
				</select>
				<span>排序按</span> <select
					id="select2" style="margin-top:6px">
					<option>名称</option>
					<option>完成数</option>
					<option>未成功数</option>
					<option>总数</option>
				</select>
				<button onclick="sortTurn()"><span class="glyphicon glyphicon-sort" style=""></span></button>
				<div class="bar-search">
					<div class="form-box ">
						<form class="search-form clearfix " action="javascript: void(0) "
							method="get">
							<input placeholder="搜索" autocomplete="off" 
								class="search-query " id="search-query" name="q"  type="text ">
							<span data-type='${tab }' class="search-button" onclick="search()"> <span
								class="glyphicon glyphicon-search" style="font-size:16px;"></span>
							</span>
						</form>
					</div>
				</div>
			</caption>

			<thead>
				<tr>
					<th>序号</th>
					
					<c:if test="${(tab)!= null && tab == 'teach'}">
						<th>课程</th> 
						<th>教材</th> 
						<th>教学大纲</th>
					</c:if>
					<c:if test="${(tab)!= null && tab == 'syllabus'}">
						<th>教师</th> 
					</c:if>
					<th id="teach-s" colspan="1" data-type='sign'><a href="javascript:;">教学进度表</a></th>
				</tr>
			</thead>
			<tbody id="tbody" style="overflow: hide;">

			</tbody>
		</table>
	</div>
 
 
 
 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="<%=basePath%>js/tab.js"></script> 
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/toTop.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.loadBar.js"></script>
<script type="text/javascript" src="<%=basePath%>js/schedule.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var tab = getUrlFrame('tab');
		document.getElementById(tab).className = "active";
		// main color
		loadBar.mainColor = 'red';  
		 
		// strip color
		loadBar.stripColor = '#444';  
		 
		// animation speed
		loadBar.barSpeed = 15; 
		 
		// bar height
		loadBar.barHeight = 5;
    	loadBar.trigger('show'); 
		ajax('status_all',tab,function(){
	    	loadBar.trigger('hide');
		});
	}; 
	
	
	
		 
		$("#teach-s").click(function(){
			var type = $(this).data('type'); 
			if(type=='sign'){
				$(this).data('type','name');
				showWithName();
			}else{
				$(this).data('type','sign');
				showWithSign();
			}
		});
		
		function showWithSign(){
			var s = document.getElementsByClassName('schedule'); 
			for(var i=0;i<s.length;i++){
				 var h; 
				if(s[i].childNodes[0].style==undefined){
					h ="<span class='glyphicon glyphicon-ok'></span>";
				}else if(s[i].childNodes[0].style.color=='red'){
					h ="<span class='glyphicon glyphicon-remove'></span>";
				}
				s[i].innerHTML= h ;
			} 
		}
		function showWithName(){
			var s = document.getElementsByClassName('schedule'); 
			for(var i=0;i<s.length;i++){
				 var h ;
				if(s[i].childNodes[0].className=='glyphicon glyphicon-remove'){
					h ='<span style="color:red;">'+s[i].getAttribute("title")+'</span>'
				}else{
					h = s[i].getAttribute('title');
				}
				s[i].innerHTML= h ;
			} 
		} 
</script>
</html>
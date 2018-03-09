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
<title>审核</title>
<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" />

<jsp:include page="left.jsp" flush="true" />
<link rel="stylesheet" href="<%=basePath%>css/ctdms_submit.css" />
<style>
.col-md-8 {
	width: 100%;
}
a {
	color: #06C; 
}
td,th {
	text-align: center;
}
.form-control{
	width:76px;
}
</style>
</head>
<body> 
	<div class="">

		<div class="container module-frame" id="iframe">
			<div class="frame-title">
				<h5>待审核的文档</h5>
				<div class="nowrap pull-right" style="margin-top:10px">
				    <div class="ctdms-btn-small" onclick="review_all('${tab }',1)"><i class="glyphicon glyphicon-ok"></i> 全部通过</div>
				    <div class="ctdms-btn-small btn-danger" onclick="review_all('${tab }',0)"><i class="glyphicon glyphicon-remove"></i> 全部打回</div>
				</div>
			</div> 
			<div class="row">
				<div class="col-md-8">
					<div class="tab">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs">
							<li id="teach"><a href="review">教学进度表</a></li>
							<li id="syllabus"><a href="review?tab=syllabus">课程教学大纲</a>
							</li>
							<li id="practice"><a href="review?tab=practice">实践教学大纲</a>
							</li>
							<li id="other"><a href="review?tab=other">其他</a></li>
						</ul>
						<div class="tab-content tabs  fadeInUp animated">
							<div class="tab-pane fade in active" id="panel">

								<table class="table table-hover">
									<thead>
										<tr>
											<th>状态</th>
											<th>课程</th>
											<c:if test="${(tab)!= null && tab=='teach' }">
												<th>班次</th>
											</c:if>
											<th>上传者</th>
											<th>最后一次修改时间</th>
											<th>审核</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="s" items="${docs }">
											<tr>
												<td>待审核</td>
												<td><a class="cin" href="javascript:openPDF('')">${s.name }</a>
												</td>
												<c:forEach var="i"  items="${s.docInfos }"> 
													<td>${i }</td> 
												</c:forEach>
												<td class="re-data" >${s.date }</td> 
												<td><a class="cback"
													href="<%=basePath%>download?docId=${s.docId }">下载</a> <span
													class="text-explode">|</span> <a class="cpass"
													href="javascript:review(${s.id },'${tab }',1)">通过</a> <span
													class="text-explode">|</span> <a class="cback"
													href="javascript:review(${s.id },'${tab }',0)">打回</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/timeago.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer/layer.js" ></script>
<script type="text/javascript">
	window.onload = function() {
		var tab = getUrlFrame('tab');
		document.getElementById(tab).className = "active";
		
		var list = document.getElementsByClassName('re-data');
		for (var i=0; i<list.length; i++){  
			list[i].innerHTML = timeago().format( list[i].innerHTML ,'zh_CN' ); 
		}  
	};
	
	function review(cid, ctab, pass) {
		$.ajax({
			url : "review",
			type:'post', 
			dataType : "json", 
			data : {
				${_csrf.parameterName}:"${_csrf.token}",
				id : cid,
				tab : ctab,
				isPass : pass
			},
			success : function(data) {
				if(data.success == true){
					layer.msg('审核成功', {icon: 1},function(){ 
        				location.reload(true);   
        			});
				}else{
					layer.msg(data.data, {icon: 5});
				}
			},
			error : function(err) {
				layer.msg('审核失败', {icon: 5});
			}
		});
	} 
	function review_all(ctab, pass) {
		layer.confirm('确定要对所有文档操作？', {
			  btn: ['确定','取消'] //按钮
			}, function(){  
				$.ajax({
					url : "review_all",
					type:'post', 
					dataType : "json", 
					data : {
						${_csrf.parameterName}:"${_csrf.token}",
						tab : ctab,
						isPass : pass
					},
					success : function(data) {
						layer.msg('审核成功', {icon: 1},function(){ 
		        				location.reload(true);   
		        		});
					},
					error : function(err) {
						layer.msg('审核失败', {icon: 5});
					}
				});
			}, function(){ 
			}); 
	} 
</script>
</html>
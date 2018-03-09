<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理</title>
<link rel="stylesheet" href="<%=basePath%>css/ctdms.css" />
<jsp:include page="left.jsp" flush="true" />
<style>
.btn-white {
	color: inherit;
	background: white;
	border: 1px solid #e7eaec
}

a {
	color: #06C;
}

td,th {
	text-align: center;
	font-size: 12px;
}

.list-table-check-td input {
	margin: 0;
	vertical-align: middle;
}

input[type=checkbox],input[type=radio] {
	-webkit-appearance: none;
	appearance: none;
	width: 18px;
	height: 18px;
	margin: 0;
	cursor: pointer;
	vertical-align: bottom;
	background: #fff;
	border: 1px solid #dcdcdc;
	-webkit-border-radius: 1px;
	-moz-border-radius: 1px;
	border-radius: 1px;
	-webkit-box-sizing: border-box !important;
	-moz-box-sizing: border-box !important;
	box-sizing: border-box !important;
	position: relative;
}

.list-table-check-td {
	width: 30px !important;
	text-align: center;
}

.form-group {
	margin-bottom: 0;
}
</style>
</head>

<body>

	<div class="container module-frame" id="iframe">
		<div class="frame-title">
			<h5>用户管理</h5>
			<div class="nowrap pull-right" style="margin-top:10px">
				<div class="ctdms-btn-small">
					<i class="glyphicon glyphicon-plus"></i> 添加用户
				</div>
				<div class="ctdms-btn-small btn-danger">
					<i class="glyphicon glyphicon-remove"></i> 删除用户
				</div>
			</div>
		</div>
		<div class="frame-content fadeInUp animated">
			<form class="animated fadeInUp form-search" method="get">

				<div class="row">
					<div class="col-xs-3">
						<div class="form-group" style="">
							<input type="text" name="username" value="" placeholder="用户名"
								class="input-sm form-control">
						</div>
					</div>

					<div class="col-xs-3">
						<div class="form-group">
							<input type="text" name="phone" value="" placeholder="手机号"
								class="input-sm form-control">
						</div>
					</div>

					<div class="col-xs-1">
						<div class="form-group">
							<button type="submit" class="btn btn-sm btn-white">
								<i class="glyphicon glyphicon-search"></i> 搜索
							</button>
						</div>
					</div>
				</div>
			</form>



			<div class="tab-pane fade in active" id="panel">

				<table class="table table-hover">
					<thead>
						<tr>
							<th class="list-table-check-td"><input data-none-auto=""
								data-check-target=".list-check-box" type="checkbox"></th>
							<th>用户账号</th>
							<th>姓名</th>
							<th>登录次数</th>
							<th>最后登录</th>
							<th>位置</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${users }">
							<tr>
								<td class="list-table-check-td"><input data-none-auto=""
									data-check-target=".list-check-box" type="checkbox"></td>
								<td>${s.userName }</td>
								<td>${s.realName }</td>
								<td>${s.loginTimes }</td>
								<td class="re-data">${s.loginDate }</td>

								<td
									<c:if test="${s.loginRealAddr == '未知'}"> style="color:#CCC"
		 												  </c:if>>${s.loginRealAddr }</td>

								 
								<c:choose>
									<c:when test="${s.state == 'Active'}">
										<td style="color:#090">使用中</td>
									</c:when>
									<c:otherwise>
										<td>已禁用</td>
									</c:otherwise>
								</c:choose>

								<td><a class="" href="">编辑</a> <span class="text-explode">|</span>
									<a class="" href="">授权</a> <span class="text-explode">|</span>
									<a class="" href="javascript:">密码</a><span class="text-explode">|</span>
									<c:choose>
										<c:when test="${s.state == 'Active'}">
											<a href="javascript:_forbid(${s.userId },1)">禁用</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:_forbid(${s.userId },0)">启用</a>
										</c:otherwise>
									</c:choose> <span class="text-explode">|</span> <a class=""
									href="javascript:_delete(${s.userId })">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/timeago.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript">
  
  function _forbid(uid,isforbid){ 
	  var msg ;
	  if(isforbid==1){
		  msg = '禁止用户';
	  }else{
		  msg = '启用用户';
	  } 
		ajax('user/forbid', {	
			${_csrf.parameterName}:"${_csrf.token}", 
			id : uid,
			forbid : isforbid},
			msg
		);  
	}
	
	function  _delete(uid){
		ajax('users', {	
			${_csrf.parameterName}:"${_csrf.token}",
			_method:'delete',
			id : uid },
			'删除用户'
		); 
	}
	function ajax(_url,_data,msg){ 
		layer.confirm('请确认是否'+msg, {
			  btn: ['确定','取消'] //按钮
			}, function(){  
				$.ajax({
					url : _url,
					type:'post',
					data : _data,
					success : function(data) {
						layer.msg(msg+'成功', {icon: 1},function(){ 
		        			location.reload(true);   
		        		});   
					},
					error : function(err) {
						layer.msg(msg+'失败', {icon: 5});
					}
				}); 
			}, function(){ 
			}); 
	}
	
	window.onload = function() {    
		/**
		*	设置时间
		*/
		var list = document.getElementsByClassName('re-data');
		for (var i=0; i<list.length; i++){ 
			if(list[i].innerHTML != '从未登录'){
				list[i].innerHTML = timeago().format( list[i].innerHTML ,'zh_CN' );
			}else{
				list[i].style.color= '#CCC';
			} 
		} 
		
	};
	
	
	</script>
</html>

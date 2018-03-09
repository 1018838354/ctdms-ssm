<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>学院教学文档管理系统</title> 
<link href="../img/favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/ctdms.css" /> 
</head>
<body>
	<div class="wallpaper">
		<div class="signup-form">
			<div class="signup-form__logo-box">
				<div class="signup-form__logo"></div>
				<div class="signup-form__catchphrase">让文档提交变得更有效率</div>
			</div>
			<div id="container-login">
				<div data-reactroot="" id="LoginComponent">
					<span>
						<form action="<c:url value='login' />" method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="input-field-group">
								<div class="input-field">
									<input type="text"  name="userName" placeholder="教师姓名/ctdms ID"
										autocomplete="false" value autocapitalize="off">
								</div>
								<div class="input-field">
									<input type="password"  name="passWord" placeholder="密码"
										autocomplete="false" autocapitalize="off">
								</div>
							</div>

							<ul class="error-msg-list fadeInUp animated">
								<c:if test="${!empty error }">
									<li class="error-msg-list__item">${error }</li>
								</c:if>
								<c:if test="${!empty logout }">
									<li class="error-msg-list__item">${logout }</li>
								</c:if>
							</ul>

							<input type="submit" onclick="return check(this)" class="signup-form__submit" value="登录">
							<div class="signup-form-nav">
								<div class="left"></div>
								<div class="right">
									<a href="" target="_blank">忘记了</a>
								</div>
							</div>
						</form>
					</span>
				</div>
			</div>
			<div class="signup-form__sns-btn-area">
				<div>江西财经大学-软通学院</div>
				<div class="sns-button-list"> 
				
				</div>
			</div>
		</div>
	</div>
</body>
 <script type="text/javascript" src="js/jquery.min.js"></script> 
 <script type="text/javascript" src="js/preload.min.js"></script> 
 <script type="text/javascript">
 $.pageLoad.PageLoading({
	 delayTime: 500
	}); 
 function check(btn){
	 var pw = $("input[type='password']").val();
	 var un = $("input[type='text']").val(); 
	 console.log(un);
	 var list = document.getElementsByClassName("error-msg-list")[0]; 
	 while(list.lastChild) //当div下还存在末尾节点时 循环继续
	 {list.removeChild(list.lastChild) }
	 var b1 = addErrorMsg(un,'您所输入的账号过短');
	 var b2 = addErrorMsg(pw,'密码为2～25文字以内');
	 console.log(b1 +" " +b2);
	 console.log(un.length); 
	 return b1&&b2; 
 }
 function addErrorMsg(check,msg){ 
	 if(check.length>=2){ return true;}
	 var list = document.getElementsByClassName("error-msg-list")[0]; 
	 var newNode = document.createElement("li");
	 newNode.className = 'error-msg-list__item fadeInUp animated';
	 newNode.innerHTML = msg; 
	 list.appendChild(newNode); 
	 return false;
 }
 </script>
<!-- 这骚操作 吃电脑配置
	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript" src="js/ios-parallax.js" ></script>
	<script> 
	    $(document).ready(function() {
	      $('.wallpaper').iosParallax({
	        movementFactor: 50
	      });
	    }); 
	</script>
	 -->
</html>
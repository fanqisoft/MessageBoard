<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	
	function checkForm(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		var rePassWord=$("#rePassWord").val();
		var mobile=$("#mobile").val();
		var address=$("#address").val();
		
		if(userName==""){
			$("#error").html("用户名不能为空！");
			return false;
		}
		if(password==""){
			$("#error").html("密码不能为空！");
			return false;
		}
		if(rePassWord==""){
			$("#error").html("确认密码不能为空！");
			return false;
		}
		if(password!=rePassWord){
			$("#error").html("确认密码和密码不一致，请重新输入！");
			return false;
		}
		if(mobile==""){
			$("#error").html("手机号码不能为空！");
			return false;
		}
		if(email==""){
			$("#error").html("邮箱不能为空！");
			return false;
		}
		return true;
	}

	function checkUserName(userName){
		if($("#userName").val()==""){
			$("#userErrorInfo").html("用户名不能为空！");
			$("#userName").focus();
			return;
		}
		$.post("${pageContext.request.contextPath}/panduan.action",{userName:userName},
				function(result){
					var result=eval('('+result+')');
					if(result.exist){
						$("#userErrorInfo").html("用户名已存在，请重新输入！");
						$("#userName").focus();
					}else{
						$("#userErrorInfo").html("用户名可以使用！");
					}
				}
		);
	}
</script>
</head>
<body>
<div class="container">
<jsp:include page="/common/head.jsp"/>

<div class="row-fluid">
	<form id="regForm" method="post" action="${pageContext.request.contextPath}/register.action" onsubmit="return checkForm()">
				<table>				
					
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input class="text" type="text" id="userName" name="userName" onblur="checkUserName(this.value)"/>&nbsp;<font id="userErrorInfo" color="red"></font></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input class="text"  type="password" id="password" name="password"   /></td>
					</tr>
					<tr>
						<td class="field">确认密码(*)：</td>
						<td><input class="text" type="password"  id="rePassWord"  name="rePassWord" /></td>
					</tr>
					
					<tr>
						<td class="field">真实姓名：</td>
						<td><input class="text" type="text" id="trueName" name="trueName"  /></td>
					</tr>
					<tr>
						<td class="field">手机号码(*)：</td>
						<td><input class="text" type="text" id="phone" name="phone" /></td>
					</tr>
					<tr>
						<td class="field">Email(*)：</td>
						<td><input class="text" type="text" id="email" name="email"  /></td>
					</tr>
					<tr>
						<td><input class="btn btn-primary" type="submit" name="submit" value="提交注册" /></td>
						<td><input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><font id="error" color="red"></font> </td>
					</tr>
				</table>
			</form>
</div>
<jsp:include page="/common/link.jsp"/>
<jsp:include page="/common/foot.jsp"/>
</div>
</body>
</html>
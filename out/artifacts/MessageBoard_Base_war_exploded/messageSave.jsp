<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我要留言</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function checkForm(){
		var title=document.getElementById("title").value;
		var userName=document.getElementById("userName").value;
		var content=CKEDITOR.instances.content.getData();
		if(title==null||title==""){
			document.getElementById("error").innerHTML="留言标题不能为空！";
			return false;
		}
		if(userName==null||userName==""){
			document.getElementById("error").innerHTML="留言作者不能为空！";
			return false;
		}
		if(content==null||content==""){
			document.getElementById("error").innerHTML="留言内容不能为空！";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="container">
<jsp:include page="/common/head.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<div class="data_list backMain">
	<div class="data_content">
		<form action="${pageContext.request.contextPath}/update.action" method="post"  onsubmit="return checkForm()">
			<table cellpadding="5" width="100%">
				<tr>
					<td width="80px">
						<label>留言标题：</label>
					</td>
					<td>
						<input type="text" id="title" name="title" class="input-xxlarge" value="${message.title }"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>留言作者：</label>
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty currentUser }">
								<input type="text" id="userName" name="userName" value="${currentUser.userName }" readonly="readonly"/>
							</c:when>
							<c:otherwise>
								<input type="text" id="userName" name="userName"  />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>留言内容：</label>
					</td>
					<td>
						<textarea class="ckeditor" id="content" name="content">${message.content }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="hidden" id="id" name="id"  value="${message.id }"/>
						<input type="submit" class="btn btn-primary" value="保存留言"/>&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>
</div>
</div>
<jsp:include page="/common/foot.jsp"/>
</div>


</body>
</html>

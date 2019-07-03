<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板首页</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<%
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="/default.jsp";
	}
%>
</head>
<body>
<div class="container">
<jsp:include page="/common/head.jsp"/>

<div class="row-fluid">
	<div class="span3">
		<div class="newsMenu">
			<ul class="nav nav-tabs nav-stacked">
				  <li><a href="#"><strong>主页</strong></a></li>
				  <li><a href="#"><strong>留言查看</strong></a></li>
				  <c:choose>
					<c:when test="${not empty currentUser }">
						<li><a href="${pageContext.request.contextPath}/main.action?userName=${currentUser.userName }">&nbsp;&nbsp;我的留言</a></li>
				  		<li><a href="${pageContext.request.contextPath}/main.action">&nbsp;&nbsp;所有留言</a></li>
				  		<li><a href="${pageContext.request.contextPath}/main.action">&nbsp;&nbsp;最新和最热留言</a></li>
				  		<li><a href="${pageContext.request.contextPath}/main.action?userName=${currentUser.userName }">&nbsp;&nbsp;管理我的留言</a></li>
					</c:when>
					<c:otherwise>
				  		<li><a href="${pageContext.request.contextPath}/main.action">&nbsp;&nbsp;所有留言</a></li>
				  		<li><a href="${pageContext.request.contextPath}/main.action">&nbsp;&nbsp;最新和最热留言</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div class="span9">
		<jsp:include page="<%=mainPage %>"></jsp:include>
	</div>
</div>
<jsp:include page="/common/link.jsp"/>
<jsp:include page="/common/foot.jsp"/>
</div>
</body>
</html>
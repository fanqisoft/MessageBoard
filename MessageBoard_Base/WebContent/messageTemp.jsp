<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fm留言板 Poweredby 小明</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
<jsp:include page="/common/head.jsp"/>
<div class="row-fluid">
	<div class="span8">
			<div class="data_list">
				<div class="dataHeader navi">
					${navCode}
				</div>
		<form action="preSave.action">
			<div>
			<div class="news_title"><h3>${message.title }</h3></div>
			<div class="news_info">
				发布时间：[${message.date }]&nbsp;&nbsp;作者：${message.userName }
			</div>
			<div class="news_content">
				${message.content }<br/><br/>
				<br/><br/>
				<c:choose>
					<c:when test="${currentUser.userName == message.userName }">
							<input type="hidden" id="id" name="id"  value="${message.id }"/>
							<button class="btn btn-primary" type="submit">修改留言</button>
							<a href="delete.action?id=${message.id }"><button class="btn btn-primary" type="button">删除留言</button></a>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary" >想修改~（您没有权限修改别人的留言~点我自己写一个）</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</form>
		<div class="upDownPage">
			${pageCode }
		</div>
	</div>
	
	<div class="data_list comment_list"> 
		<div class="dataHeader">用户评论</div>
		<div class="commentDatas">
			<c:forEach var="comment" items="${commentList }">
				<div class="comment">
					<font>${comment.userIP }：</font>${comment.content }&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;]
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="publish_list">
		<form action="comment.action" method="post">
			<div>
				<input type="hidden" value="${message.id }" id="id" name="id"/>
				<textarea style="width: 98%" rows="3" id="content" name="content"></textarea>
			</div>
			<div class="publishButton">
				<button class="btn btn-primary" type="submit">发表评论</button>
			</div>
		</form>
	</div>
	</div>
	<div class="span4">
		<div class="data_list right_news_list">
			<div class="dataHeader">最新留言</div>
			<div class="datas">
				<ul>
						<li><a href="pass.action?id=2" title="to小明">to小明</a></li>
				</ul>
			</div>
		</div>
		<div class="data_list right_news_list">
			<div class="dataHeader">热门留言</div>
			<div class="datas">
				<ul>
					<li><a href="pass.action?id=6" title="from 活鱼~">from 活鱼~</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/common/foot.jsp"/>
</div>
</body>
</html>
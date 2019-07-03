<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<%-- <div class="dataHeader navi">
		${navCode}
	</div> --%>
	<div class="span12">
		<div class="navbar">
		  <div class="navbar-inner">
		    <a class="brand" href="mainTemp.jsp">首页</a>
		   <%--  <ul class="nav">
		       <c:forEach var="newsType" items="${newsTypeList}">
		       		<li><a href="news?action=newsList&typeId=${newsType.newsTypeId }">${newsType.typeName }</a></li>
		       </c:forEach>
		    </ul> --%>
		  </div>
		</div>
	</div>
	<div class="datas news_type_list">
		<ul>
			<c:forEach var="messages" items="${messagesList }">
				<li><span>[${messages.date }]</span>&nbsp;&nbsp;<a href="pass.action?id=${messages.id }"  }" title="${messages.title }">${fn:substring(messages.title,0,42) }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="pagination pagination-centered">
  		<ul>
  			${pageCode }
  		</ul>
  	</div>
</div>
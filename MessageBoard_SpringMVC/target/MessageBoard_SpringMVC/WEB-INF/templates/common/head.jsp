<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function setDateTime(){
			var date=new Date();
			var day=date.getDay();
			var week;
			switch(day){
			case 0:week="星期日";break;
			case 1:week="星期一";break;
			case 2:week="星期二";break;
			case 3:week="星期三";break;
			case 4:week="星期四";break;
			case 5:week="星期五";break;
			case 6:week="星期六";break;
			}
			var today=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日  "+week+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			document.getElementById("today").innerHTML=today;
		}
		
		window.setInterval("setDateTime()", 1000);
		
		function logout(){
			if(confirm('您确定要退出系统吗？')){
				window.location.href='${pageContext.request.contextPath}/logout.action';
			}
		}
	</script>
<div class="row-fluid">
	<div class="span12">
		<div>
			<div class="headLeft">
				<img src="${pageContext.request.contextPath}/images/logo.jpg"/>
			</div>
			<div class="headRight">
				<c:choose>
					<c:when test="${not empty currentUser }">
						欢迎你：<font color="red">${currentUser.userName }</font>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:logout()">[&nbsp;安全退出&nbsp;]</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/messageSave.jsp">我要留言</a>
						<a href="${pageContext.request.contextPath}/main.action?userName=${currentUser.userName }">我的留言</a>
						<font id="today" class="currentDateTime"></font>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login.jsp" class="">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/register.jsp">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/messageSave.jsp">游客留言</a>
						<font id="today" class="currentDateTime"></font>
					</c:otherwise>
				</c:choose>
				<form action="main.action" method="post">
				<input type="text" id="s_title" name="s_title" placeholder="请输入你感兴趣的留言标题..." 
				onkeyup="" autocomplete="off" value="${s_title }" /> <input
				type="submit" id="cmdSearch"  value="搜索"  style="margin-top: -10px"/><br/>
				<div id="suggest" style="width: 200px"></div>
				</form>
	</div>
		</div>
	</div>
</div>

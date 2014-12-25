<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
  		function closeWin(){
  			window.location.href = '<%=request.getParameter("param")%>Action.action';
		}
</script>

	</head>

	<body>
		<center>
			<img src="<%= request.getContextPath()%>/images/message/fail.jpg"/>
			<br/>
			${request.msg}
			<a href="javascript:closeWin();">确定</a>
		</center>
		
	</body>
</html>

<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
  		function closeWin(){
  			//parent.refresh_tmUserTable();
  			eval('parent.refresh_' + '<%=request.getParameter("table")%>' + '()');
			parent.win.hide();
		}
</script>
	</head>

	<body>
		<center>
			<img src="<%= request.getContextPath()%>/images/message/success.jpg"/>
			<br/>
			<br/>
			<font size="3">
			${request.msg}
			<a href="javascript:closeWin();">确定</a>
			</font>
		</center>
	</body>
</html>

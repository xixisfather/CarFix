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
  			<%
				String e3Table = (String)request.getAttribute("e3Table");
				
				if(null!=e3Table){
			%>
				eval('parent.refresh_' + '<%=e3Table%>' + '()');
			<%
				}
				else{
			%>
  			var e3Table = '<%=request.getParameter("table")%>';
  			var e3TableArray = e3Table.split(",");
  			for(var i=0 ; i<e3TableArray.length; i++){
	  			eval('parent.refresh_' + e3TableArray[i] + '()');
  			}
  			<%
  				}
  			%>
			parent.win.hide();
		}
</script>
	</head>

	<body>
		<center>
		<table>
			<tr>
				<td><img src="<%= request.getContextPath()%>/images/message/success.jpg"/></td>
			</tr>
			<s:if test="#request.xlsMessage!=null">
				<tr>
					<td><%=request.getAttribute("xlsMessage")%></td>
				</tr>
			</s:if>
			
			<s:if test="#request.xlsMessageList!=null">
				<s:iterator id="repeatMessage" value="#request.xlsMessageList">
					<tr>
						<td>${repeatMessage}</td>
					</tr>
				</s:iterator>
			</s:if>
			<tr>
				<td><a href="javascript:closeWin();">确定</a></td>
			</tr>
		</table>
		</center>
	</body>
</html>

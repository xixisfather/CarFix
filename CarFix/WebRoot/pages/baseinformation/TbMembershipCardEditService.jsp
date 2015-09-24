<%@page import="com.selfsoft.baseinformation.model.TmMemberShipService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看服务</title>
</head>
<body>
<center>

<form action="updateTbMemberCardServiceAction.action" method="post">
<table width="800">


<%

List<TmMemberShipService> tmMemberShipServiceList = (List<TmMemberShipService>)request.getAttribute("tmMemberShipServiceList");

if(null != tmMemberShipServiceList && tmMemberShipServiceList.size() > 0) {
	
	int i = 0;
	
	for(TmMemberShipService t : tmMemberShipServiceList) {
		
%>

	<tr>
		<input type="hidden" name="memberShipId<%=i%>" value="<%=t.getMemberShipId()%>" />
		<td><input type="text"  name="serviceName<%=i%>" value="<%= t.getServiceName()%>"/></td>
		<td><input type="text"  name="serviceCount<%=i%>" value="<%= t.getServiceCount()%>"/></td>
	</tr>
	

<%		
		i++;
	}
	
	
}
else {
%>	
<br/>
<br/>
<br/>
<br/>
<font color="red" size="16">没有赠送的服务</font>

<%	
}

%>

<tr>
	<td colspan="2" align="center"><input type="submit" value="提交"/></td>
</tr>
</table>
</form>
</center>
</body>
</html>
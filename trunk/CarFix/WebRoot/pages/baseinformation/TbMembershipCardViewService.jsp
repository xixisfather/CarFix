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
<%

List<TmMemberShipService> tmMemberShipServiceList = (List<TmMemberShipService>)request.getAttribute("tmMemberShipServiceList");

if(null != tmMemberShipServiceList && tmMemberShipServiceList.size() > 0) {
	
	for(TmMemberShipService t : tmMemberShipServiceList) {
		
%>

	<%= t.getServiceName()%> &nbsp;&nbsp;&nbsp;&nbsp; <%= t.getServiceCount()%> 次<br/><br/>


<%		
		
	}
	
	
}
else {
%>	

没有赠送的服务

<%	
}

%>


</center>
</body>
</html>
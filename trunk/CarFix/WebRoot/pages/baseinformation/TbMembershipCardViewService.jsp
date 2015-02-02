<%@page import="com.selfsoft.baseinformation.model.TmMemberShipService"%>
<%@page import="java.util.List"%>
<%@page import="com.selfsoft.baseinformation.service.ITmMemberShipServiceService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
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

String id = request.getParameter("id");

ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"classpath:applicationContext*.xml");

ITmMemberShipServiceService tmMemberShipServiceService = (ITmMemberShipServiceService)appContext.getBean("tmMemberShipServiceService");

List<TmMemberShipService> tmMemberShipServiceList = tmMemberShipServiceService.findByMemberShipId(Long.valueOf(id));

if(null != tmMemberShipServiceList && tmMemberShipServiceList.size() > 0) {
	
	for(TmMemberShipService t : tmMemberShipServiceList) {
		
%>

	<%= t.getServiceName()%> &nbsp;&nbsp;&nbsp;&nbsp; <%= t.getServiceCount()%> 次<br/><br/>


<%		
		
	}
	
	
}

%>


</center>
</body>
</html>
<%@page import="com.selfsoft.business.service.ITbSmartBalanceService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.selfsoft.business.service.impl.TbSmartBalanceServiceImpl"%>
<%@page import="com.selfsoft.framework.common.CommonMethod"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.selfsoft.business.model.TbSmartBalance"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>快速结算打印</title>

<script language="javascript">
function printdiv(printpage)
{
var headstr = "<html><head><title></title></head><body>";
var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
document.body.innerHTML = headstr+newstr+footstr;
window.print(); 
document.body.innerHTML = oldstr;
return false;
}
</script>


</head>
<body>

<%
TbSmartBalance tbSmartBalance = (TbSmartBalance)request.getAttribute("tbSmartBalance");

ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"classpath:applicationContext*.xml");

ITbSmartBalanceService tbSmartBalanceService = (ITbSmartBalanceService)appContext.getBean("tbSmartBalanceService");

if(null == tbSmartBalance) {
	
	tbSmartBalance = tbSmartBalanceService.findById(Long.valueOf(request.getParameter("id")));
}

%>


<div id="div_print" align="center">
	
	<table cellpadding="30">
	
		<tr align="left">
			<td>项目</td>
			<td><%= tbSmartBalance.getServiceName()%></td>
		</tr>
		
		<tr align="left">
			<td>金额</td>
			<td><%= new BigDecimal(tbSmartBalance.getServiceMoney()).setScale(2)%></td>
		</tr>
	
		<tr align="left">
			<td>余额</td>
			<td><%= null == tbSmartBalance.getCardSaving()? "" : new BigDecimal(tbSmartBalance.getCardSaving()).setScale(2)%></td>
		</tr>
		
		<tr align="left">
			<td>施工人</td>
			<td><%= tbSmartBalance.getWorkerName()%></td>
		</tr>
		
		<tr align="left">
			<td>日期</td>
			<td><%= CommonMethod.parseDateToString(tbSmartBalance.getBalanceDate(), "yyyy-MM-dd HH:mm:ss") %></td>
		</tr>
		
		<tr align="left">
			<td>客户签字</td>
			<td>&nbsp;</td>
		</tr>	
	</table>


</div>

<div align="center">

<input name="b_print" type="button" onClick="printdiv('div_print');" value="打印">
&nbsp;&nbsp;&nbsp;&nbsp;
<input name="b_return" type="button" onClick="window.location.href='<%= request.getContextPath() %>/pages/business/tbSmartBalance/tbSmartBalanceCreate.jsp'" value="返回">
</div>


</body>
</html>
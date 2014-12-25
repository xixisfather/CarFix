<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.business.model.TbBusinessBalance"%>
<%@page import="com.selfsoft.baseinformation.service.ITbCarInfoService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.selfsoft.baseinformation.model.TbCarInfo"%>
<%@page import="com.selfsoft.framework.common.CommonMethod"%>
<%@page import="com.selfsoft.secrity.service.ITmCompanyService"%>
<%@page import="com.selfsoft.secrity.model.TmCompany"%>
<%@page import="com.selfsoft.business.model.TbBusinessSpecialBalance"%><html>
<head>
<title>结算财务汇总报表</title>

<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 575px;
	height: 30px;
	z-index: 1;
	left: 75px;
	top: -1px;
	font-size: 16px;
	font-weight: bold;
	text-align: left;
}

#apDiv1 center {
	font-weight: bold;
	font-size: 24px;
}

#apDiv2 {
	position: absolute;
	width: 646px;
	height: 145px;
	z-index: 2;
	left: 5px;
	top: 65px;
	font-size: 12px;
}

#apDiv3 {
	position: absolute;
	width: 652px;
	height: 31px;
	z-index: 3;
	top: 30px;
	left: 4px;
	font-size: 12px;
}

@media print {
	.btn {
		display: none;
	}
}

@CHARSET "UTF-8";

td {
	font-family: "宋体";
	font-size: 12px;
}

th {
	font-family: "宋体";
	font-size: 12px;
}

body {
	font-family: "宋体";
	font-size: 12px;
}
-->
</style>
</head>

<body>
<%
	List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = (List<TbBusinessSpecialBalance>) session
			.getAttribute("tbBusinessSpecialBalanceHandleList");

	

	TbBusinessSpecialBalance _tbBusinessSpecialBalance = (TbBusinessSpecialBalance)session.getAttribute("tbBusinessSpecialHandleBalance");

%>

<div id="apDiv1">公司财务录入汇总报表&nbsp;&nbsp;<span class="btn"><font
	color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<div id="apDiv2">
<table width="800" height="76" border="0">
	<tr>
		<td></td>
		<td>结算单号</td>
		<td>单据号</td>
		<td>客户名</td>
		<td>车牌号</td>
		<td>车型</td>
		<td>底盘号</td>
		<td>联系方式</td>
		<td>金额</td>
		<td>结算日期</td>
		<td>接待员</td>
	</tr>

	<%
		if (null != tbBusinessSpecialBalanceList
				&& tbBusinessSpecialBalanceList.size() > 0) {
			
			for(int i = 0 ; i < tbBusinessSpecialBalanceList.size() ; i++){
				
				TbBusinessSpecialBalance tbBusinessSpecialBalance = tbBusinessSpecialBalanceList.get(i);
	%>

	<tr>
		<td><%= i + 1 %></td>
		<td><%= tbBusinessSpecialBalance.getEditCode() %></td>
		<td><%= tbBusinessSpecialBalance.getEntrustCodeDB() == null ? "" : tbBusinessSpecialBalance.getEntrustCodeDB() %></td>
		<td><%= tbBusinessSpecialBalance.getTbCustomer() == null ? "" : (tbBusinessSpecialBalance.getTbCustomer().getCustomerName() == null ? "" : tbBusinessSpecialBalance.getTbCustomer().getCustomerName()) %></td>
		<td><%= tbBusinessSpecialBalance.getTbCarInfo() == null ? "" : (tbBusinessSpecialBalance.getTbCarInfo().getLicenseCode() == null ? "" : tbBusinessSpecialBalance.getTbCarInfo().getLicenseCode())%></td>
		<td><%= tbBusinessSpecialBalance.getModelCode()%></td>
		<td><%= tbBusinessSpecialBalance.getTbCarInfo() == null ? "" : (tbBusinessSpecialBalance.getTbCarInfo().getChassisCode() == null ? "" : tbBusinessSpecialBalance.getTbCarInfo().getChassisCode())%></td>
		<td><%= tbBusinessSpecialBalance.getTbCustomer() == null ? "" : (tbBusinessSpecialBalance.getTbCustomer().getTelephone() == null ? "" : tbBusinessSpecialBalance.getTbCustomer().getTelephone()) %></td>
		<td><%= tbBusinessSpecialBalance.getBalanceTotalAll() %></td>
		<td><%= tbBusinessSpecialBalance.getBananceDate() %></td>
		<td><%= tbBusinessSpecialBalance.getUserRealName() %></td>
	</tr>

	<%
			}
		}
	%>


	
</table>
<p>&nbsp;</p>
<table width="312" border="0">
	<tr>
		<td width="73" align="right">业务笔数：</td>
		<td width="73"><%=session.getAttribute("totalHandleSize") %></td>
		<td width="73" align="right">总金额：</td>
		<td width="75"><%=session.getAttribute("total") %></td>
	</tr>
</table>
<p>&nbsp;</p>
</div>
<div id="apDiv3">结算日期 

 &nbsp;&nbsp;    
	
	<%
		if(null != _tbBusinessSpecialBalance){
			
	%>
	<%= CommonMethod.parseDateToString(_tbBusinessSpecialBalance.getBananceDateStart(),"yyyy-MM-dd")%>&nbsp;&nbsp;至&nbsp;&nbsp;  <%= CommonMethod.parseDateToString(_tbBusinessSpecialBalance.getBananceDateEnd(),"yyyy-MM-dd")%>
	
	<%		
		}
	%>
		
</div>
</body>
</html>



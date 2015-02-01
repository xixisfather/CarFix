<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.framework.common.Constants"%><html>
	<head>
		<title>会员卡历史信息查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCardHis.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body onload="document.getElementById('cardNo').focus();">
		<% 
			Map<String,String> operationCardMap = Constants.getOperationCardMap();
		
			request.setAttribute("operationCardMap",operationCardMap);
		
		%>
		<s:form action="tbCardHisFindAction.action">
			<table>
				<tr>
					<td>会员卡号</td>
					<td>
						<s:textfield id="cardNo" name="tbCardHis.cardNo"/><font color="red">(请先刷会员卡)</font>
					</td>
					<td>客户号</td>
					<td><s:textfield id="customerCode" name="tbCardHis.customerCode" onfocus="openWin();"/>
					<img src="<%= request.getContextPath() %>/images/icons/find.gif"
				style="cursor: pointer;" onclick="openWin();" />
					</td>
					<td>客户姓名</td>
					<td><s:textfield id="customerName" name="tbCardHis.customerName"/></td>
					<td>
						操作日期从
						<s:textfield id="operationDateFrom" name="tbCardHis.operationDateFrom">
						
							<s:param name="value"><s:date name="tbCardHis.operationDate" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
						<e3c:calendar for="operationDateFrom" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>	
						<s:textfield id="operationDateTo" name="tbCardHis.operationDateTo">
						
							<s:param name="value"><s:date name="tbCardHis.operationDateTo" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
						<e3c:calendar for="operationDateTo" dataFmt="yyyy-MM-dd"/>
					</td>
					
					<td>操作类型</td>
					<td>
						<s:select id="operationCardMap" name="tbCardHis.operationType" list="#request.operationCardMap" listKey="key" listValue="value" emptyOption="true"></s:select>
					</td>
				</tr>	
				<tr>	
					<td>
						结算单号
					</td>
					<td>
						<s:textfield id="balanceCode" name="tbCardHis.balanceCode"/>
					</td>
				</tr>
				<tr>
					<td colspan="9" align="center">
						<input type="button" value="查询"
							onclick="tbCardHisTableQuery();" />
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tbCardHisTable" uri="tbCardHisFindAction.action" var="tbCardHis"
			scope="request" items="tbCardHisList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="200" width="1800"
			height="400" caption="会员卡历史记录">
			
			<e3t:column property="customerCode" title="客户号"/>
			
			<e3t:column property="customerName" title="客户姓名"/>
			
			<e3t:column property="operationType" title="操作类型"/>
			
			<e3t:column property="operationDate" width="120" title="操作日期">
				<fmt:formatDate value="${tbCardHis.operationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			
			<e3t:column property="cardNo" title="卡号"></e3t:column>
			
			<e3t:column property="userRealName" title="操作人"></e3t:column>
			
			<e3t:column property="operationDesc" title="明细"></e3t:column>
		</e3t:table>
	</body>
</html>

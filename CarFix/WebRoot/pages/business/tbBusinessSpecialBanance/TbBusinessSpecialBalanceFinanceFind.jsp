<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>财务录入查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessSpecialBanance/TbBusinessSpecialBalanceFinance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessSpecialBalanceFinanceFindAction.action">
			<table>
				<tr>
					<td><s:hidden name="tbBusinessSpecialBalance.specialType" value="2"></s:hidden> </td>
				</tr>
				<tr>
					
					<td>
						流水号
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.editCode"></s:textfield>
					</td>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.balanceCodeDB"></s:textfield>
					</td>
					<td>
						委托书号
					</td>
					<td>	
						<s:textfield name="tbBusinessSpecialBalance.entrustCodeDB"></s:textfield>
					</td>
				</tr>	
				<tr>	
					<td>
						结算日期
					</td>
					<td>
						<s:textfield id="bananceDateStart" name="tbBusinessSpecialBalance.bananceDateStart"></s:textfield>
						<e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>
						<s:textfield id="bananceDateEnd" name="tbBusinessSpecialBalance.bananceDateEnd"></s:textfield>
						<e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
					
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbBusinessSpecialBalanceTableQuery();"/>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessSpecialBalanceTable" uri="tbBusinessSpecialBalanceFinanceFindAction.action" var="tbBusinessSpecialBalance"
			scope="request" items="tbBusinessSpecialBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="业务接待">
			<e3t:column property="no" title="操作" sortable="false" width="200">
			
				<a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceFinanceForwardPageAction.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				
				&nbsp;&nbsp;
				
				<a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceFinancePrintAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a> 
				
			</e3t:column>
			<e3t:column property="editCode" title="流水号" />
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="balanceCodeDB" title="结算单号" />
			<e3t:column property="entrustCodeDB" title="委托书号" />
			<e3t:column property="stockOutCodeDB" title="销售单号" />
			<e3t:column property="bananceDate" title="结算日期" width="120">
				<fmt:formatDate value="${tbBusinessSpecialBalance.bananceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="balanceTotalAll" title="结算金额" />
			<e3t:column property="userRealName" title="结算员" />
			
		</e3t:table>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>再结算处理</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessReBalance/TbBusinessReBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessReBalanceFindAction.action">
			<table>
				<tr>
					<s:hidden name="tbBusinessBalance.balanceStatus" value="8004"></s:hidden>
				</tr>
				<tr>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield name="tbBusinessBalance.balanceCode"></s:textfield>
					</td>
					<td>
						委托书号
					</td>
					<td>	
						<s:textfield name="tbBusinessBalance.tbFixEntrust.entrustCode"></s:textfield>
					</td>
					<td>
						结算日期
					</td>
					<td>
						<s:textfield id="bananceDateStart" name="tbBusinessBalance.bananceDateStart"></s:textfield>
						<e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>
						<s:textfield id="bananceDateEnd" name="tbBusinessBalance.bananceDateEnd"></s:textfield>
						<e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbBusinessBalanceTableQuery();"/>
						
						&nbsp;&nbsp;
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessBalanceTable" uri="tbBusinessReBalanceFindAction.action" var="tbBusinessBalance"
			scope="request" items="tbBusinessBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="结算单">
			<e3t:column property="no" title="操作"
				sortable="false" width="200">
				<a href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceViewAction.action',600,300);">
					<font color="blue">
						结算明细
					</font>
				</a>
				<a href="javascript:reTbBusinessBalance('${tbBusinessBalance.id}');">
					<font color="blue">
						再结算
					</font>
				</a>
				
				<a href="javascript:reBackTbBusinessBalance('${tbBusinessBalance.balanceCode}');">
					<font color="blue">
						重新结算
					</font>
				</a>
				
			</e3t:column>
			<e3t:column property="balanceCode" title="结算单号" />
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="stockOutCode" title="销售单号" />
			<e3t:column property="bananceDate" title="结算日期" width="120">
				<fmt:formatDate value="${tbBusinessBalance.bananceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="balanceTotalAll" title="结算金额" />
			<e3t:column property="shouldPayAmount" title="收款金额" />
			<e3t:column property="userRealName" title="结算员" />
			<e3t:column property="remark" title="备注" />
		</e3t:table>
	</body>
</html>

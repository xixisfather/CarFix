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
		<title>结算单实际到款汇总查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessBalanceGroupSumAmountFindAction.action">
			<table>
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
						<s:textfield name="tbBusinessBalance.entrustCode"></s:textfield>
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
					
					<td>结算方式</td>
					<td>
						<s:select id="payPattern" name="tbBusinessBalance.payPattern" list="#request.payMap" listKey="key" listValue="value" emptyOption="true"></s:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="8" align="center">
						<input type="submit" value="查询"/>
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessBalanceTable" uri="tbBusinessBalanceGroupFindAction.action" var="tbBusinessBalance"
			scope="request" items="tbBusinessBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320">
			<e3t:column property="balanceCode" title="结算单号" />
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="stockOutCode" title="销售单号" />
			<e3t:column property="bananceDate" title="结算日期" width="120">
				<fmt:formatDate value="${tbBusinessBalance.bananceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			
			<e3t:column property="shouldPayAmount" title="收款金额" />
			
			<e3t:column property="userRealName" title="结算员" />
			<e3t:column property="remark" title="备注" />
		</e3t:table>
		<table>
			<tr>
			
				<td>实际到款总金额：</td>
				<td>${request.totalSJ}</td>
			</tr>
		</table>
	</body>
</html>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		
		<script language="javascript">
function tbBusinessBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessBalanceGroupSumAmountFindAction";
	pConfig.showLoadingMsg = true;
}
			
		</script>

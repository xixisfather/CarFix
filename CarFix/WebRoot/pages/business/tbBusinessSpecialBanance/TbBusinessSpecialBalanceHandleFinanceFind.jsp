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
		<title></title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	
	<script language="javascript">
function tbBusinessSpecialBalanceHandleFinanceTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbBusinessSpecialBalanceHandleFinanceForwardPageAction.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessSpecialBalanceHandleFinanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessSpecialBalanceHandleFinanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessSpecialBalanceHandleFinanceFindAction";
	pConfig.showLoadingMsg = true;
}
</script>
	</head>

	<body>
		<s:form action="tbBusinessSpecialBalanceHandleFinanceFindAction.action">
			<table>
				<tr>
					<td>
						统计月份从
					</td>
					<td>
						<s:textfield id="staticsDateFrom" name="tbBusinessSpecialBalanceHandleFinance.staticsDateFrom" readonly="true" size="15"/> 
						
						<e3c:calendar for="staticsDateFrom" dataFmt="yyyy-MM"/>
					</td>
					<td>
						至
						<s:textfield id="staticsDateEnd" name="tbBusinessSpecialBalanceHandleFinance.staticsDateEnd" readonly="true" size="15"/> 
						
						<e3c:calendar for="staticsDateEnd" dataFmt="yyyy-MM"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbBusinessSpecialBalanceHandleFinanceTableQuery();"/>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessSpecialBalanceHandleFinanceTable" uri="tbBusinessSpecialBalanceHandleFinanceFindAction.action" var="tbBusinessSpecialBalanceHandleFinance"
			scope="request" items="tbBusinessSpecialBalanceHandleFinanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320">
			<e3t:column property="no" title="操作" sortable="false" width="200">
			
				<a href="javascript:editObject('${tbBusinessSpecialBalanceHandleFinance.id}','tbBusinessSpecialBalanceHandleFinanceForwardPageAction.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				
				&nbsp;&nbsp;
				<a
					href="javascript:deleteObject('${tbBusinessSpecialBalanceHandleFinance.id}','tbBusinessSpecialBalanceHandleFinanceDeleteAction.action');"><font
					color="blue">删除
				</font>
				</a>
				
			</e3t:column>
			<e3t:column property="staticsDate" title="统计月份"/>
			<e3t:column property="userRealName" title="录入人"/>
		</e3t:table>
	</body>
</html>

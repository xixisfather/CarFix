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
		<title>车辆修理情况排行榜</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessBalanceCarOrderFindAction.action">
			<table>
				
				<tr>
					<td>
					
						<input type="radio" name="orderCode" value="fixCount" checked="checked"/>
						
					</td>
					<td>	
						按结算单数排名
					</td>
					<td>
					
						<input type="radio" name="orderCode" value="balanceTotal"/>
						
					</td>
					<td>	
						按修理总费用排名
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbBusinessBalanceTableQuery();"/>
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessBalanceTable" uri="tbBusinessBalanceCarOrderFindAction.action" var="tbBusinessBalance"
			scope="request" items="tbBusinessBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" statusVar="status">
			<e3t:column property="no_1" title="排名" sortable="false">
        		${status.globalCount}
      		</e3t:column>
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="modelTypeName" title="车型" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="fixCount" title="结算单数" />
			<e3t:column property="workingHourTotalAll" title="工时费合计" />
			<e3t:column property="fixPartTotalAll" title="材料费合计" />
			<e3t:column property="solePartTotalAll" title="销售额合计" />
			<e3t:column property="balanceTotalAll" title="结算金额合计" />
			<e3t:column property="shouldPayAmount" title="收款金额合计" />
			<e3t:column property="oweAmount" title="欠款金额合计" />
		</e3t:table>
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
	pConfig.form = "tbBusinessBalanceCarOrderFindAction";
	pConfig.showLoadingMsg = true;
}
			
		</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>已结算修理销售成本</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getIsBalanceFixSellCostDetailStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		
	</script>
	<body>
		<s:form action="getIsBalanceFixSellCostDetailStatAction.action">
			<table>
				<tr>	
					<td>结算日期</td>
					<td><s:textfield id="beginFixDate" name="balanceFixSellVo.beginFixDate" />
						<e3c:calendar for="beginFixDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield id="endFixDate" name="balanceFixSellVo.endFixDate" />
						<e3c:calendar for="endFixDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>车型</td>
					<td><s:select name="balanceFixSellVo.modelTypeId" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/></td>
					<td>牌照号</td>
					<td><s:textfield name="balanceFixSellVo.lianceseCode" ></s:textfield> </td>
					<td>修理类型</td>
					<td><s:select name="balanceFixSellVo.fixTypeId" list="#request.tmFixTypeList" emptyOption="true" listKey="id" listValue="fixType"/></td>
					<td>服务顾问</td>
					<td><s:select name="balanceFixSellVo.userId" list="#request.tmUserList" emptyOption="true" listKey="id" listValue="userRealName"/></td>
					
				</tr>
			
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="getIsBalanceFixSellCostDetailStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" >
			
			<e3t:column width="100" property="bananceDate" title="结算日期" >
				<fmt:formatDate value="${tbPartInfo.bananceDate}" pattern="yyyy-MM-dd" />
			</e3t:column>
			<e3t:column width="100" property="entrustCode" title="委托书" />
			<e3t:column width="100" property="maintainCode" title="维修发料单" />
			<e3t:column width="100" property="stockOutCode" title="销售单" />
			<e3t:column width="50" property="lianceseCode" title="牌照号" />
			<e3t:column width="70" property="costXj" title="总成本" />
			<e3t:column width="70" property="fixCostPrice" title="修理成本" />
			<e3t:column width="70" property="spPrice" title="索赔成本" />
			<e3t:column width="70" property="swPrice" title="首保成本" />
			<e3t:column width="70" property="fgPrice" title="返工成本" />
			<e3t:column width="70" property="fwPrice" title="服务成本" />
			<e3t:column width="70" property="sellCostPrice" title="销售成本" />
			<e3t:column width="100" property="fixType" title="修理类型" />
			<e3t:column width="100" property="customerName" title="客户名称" />
			<e3t:column width="100" property="balanceCode" title="结算单" />
			<e3t:column width="100" property="userRealName" title="服务顾问" />
		</e3t:table>
	</body> 
</html>

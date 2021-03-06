<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件每日出库查询</title>
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
			pConfig.form = "findStStockoutAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		
	</script>
	<body>
		
			
		<s:form action="findStStockoutAction.action">
			<table>
				<tr>	
					<td>仓库：</td>
					<td><s:textfield  name="stStockout.houseName" id="houseName" /></td>
					
					<td>查询日期：</td>
					<td  ><s:textfield cssStyle="width:100px" id="beginDate" name="stStockout.beginStockOutDate" />
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="endDate" name="stStockout.endStockOutDate" />
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
					
					<td>出库类型：</td>
					<td><s:select name="stStockout.stockoutType" list="#request.elementsMap" headerKey="" headerValue="所有" listKey="key" listValue="value"/></td>
					
					
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="查询" />
	
					</td>
				</tr>
			</table>
		</s:form>
			
		
		
		<e3t:table id="tbPartInfoTable" uri="findStStockoutAction.action" var="tbPartInfo"
			scope="request" items="stStockoutList" mode="ajax" caption="配件每日出库清单" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="920"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="stockoutCode" title="单据号码" />
			<e3t:column property="stockoutType" title="出库类型" />
			<e3t:column property="quantity" title="数量" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="sellPrice" title="销售价" />
			<e3t:column  width="60" property="stockOutDate" title="出库日期" >
				<fmt:formatDate value="${tbPartInfo.stockOutDate}" />
			</e3t:column>
		</e3t:table>
		<table width="600" >
			<tr>
				<td align="right" >出库成本：</td>
				<td>${countVo.totalCostPrice }</td>
				<td align="right" >出库销售：</td>
				<td>${countVo.totalSellPrice }</td>
				<td align="right" >出库种类：</td>
				<td>${countVo.partCategory}</td>
				<td align="right" >出库数量：</td>
				<td>${countVo.totalQuantity }</td>
			</tr>
		</table>			
	</body> 
</html>

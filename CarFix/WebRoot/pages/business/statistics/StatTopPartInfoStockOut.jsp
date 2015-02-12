<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件出库排行榜</title>
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
			pConfig.form = "getToppartInfoStockOutStatAction";
			pConfig.showLoadingMsg = true;
		}


		function exportXls(){
			
			 document.getElementById("exp_storeHouseId").value = document.getElementById("storeHouseId").value;
			 document.getElementById("exp_beginDate").value = document.getElementById("beginDate").value;
			 document.getElementById("exp_endDate").value = document.getElementById("endDate").value;
			 document.getElementById("exp_stockOutType").value = document.getElementById("stockOutType").value;
			 document.forms["getTopPartStockoutExportXlsAction"].submit();
			//window.open(url,'_blank');
		}
		
	</script>
	<body>
	
		<form id="getTopPartStockoutExportXlsAction" onsubmit="return true;" action="getTopPartStockoutExportXlsAction.action" method="post">
			<input type="hidden" name="tbPartInfoStockOutVo.storeHouseId" value="" id="exp_storeHouseId"/>
			<input type="hidden" name="tbPartInfoStockOutVo.stockOutType" value="" id="exp_stockOutType"/>
			<input type="hidden" name="tbPartInfoStockOutVo.beginDate" value="" id="exp_beginDate"/>
			<input type="hidden" name="tbPartInfoStockOutVo.endDate" value="" id="exp_endDate"/>
		</form>
		
		<s:form action="getToppartInfoStockOutStatAction.action">
			<s:hidden id="storeQuantity" name="tbPartInfo.storeQuantity" ></s:hidden>
			<table>
				<tr>	
					<td>仓库</td>
					<td><s:select id="storeHouseId" name="tbPartInfoStockOutVo.storeHouseId" list="#request.stroeHouseList" headerValue="所有" headerKey="" listKey="id" listValue="houseName"/></td>
					<td>出库类型：</td>
					<td><s:select id="stockOutType" name="tbPartInfoStockOutVo.stockOutType" list="#request.elementsMap" headerValue="所有" headerKey="" listKey="key" listValue="value" ></s:select></td>
					<td>日期从</td>
					<td  ><s:textfield id="beginDate" name="tbPartInfoStockOutVo.beginDate" />
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						到
						<s:textfield id="endDate" name="tbPartInfoStockOutVo.endDate" />
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>	
					<td>排序条件</td>
					<td colspan="5" ><s:radio list="%{#{'1':'销售量','2':'利润','3':'销售金额','4':'成本金额'}}" name="tbPartInfoStockOutVo.orderByType" /></td>
				</tr>
				<tr>
					<td>
						<s:submit value="查询" ></s:submit>
					</td>
					<td>
						<input type="button" onclick="exportXls()" value="xls导出" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="getToppartInfoStockOutStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="20" width="900"
			height="320" >
			<e3t:column property="partName" title="配件" />
			<e3t:column property="modelName" title="车型" />
			<e3t:column property="storeLocation" title="仓位" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column property="sellQuantity" title="销售量" />
			<e3t:column property="sellPrice" title="销售金额" />
			<e3t:column property="costXj" title="成本金额	" />
			<e3t:column property="gain" title="利润" />
		</e3t:table>
		<table width="600" >
			<tr>
				<td align="right" >销售总金额：</td>
				<td>${countVo.totalSellPrice }</td>
				<td align="right" >利润：</td>
				<td>${countVo.totalGain }</td>
				<td align="right" >库存总量：</td>
				<td>${countVo.totalStoreQuantity}</td>
				<td align="right" >库存成本：</td>
				<td>${countVo.totalCostPrice }</td>
			</tr>
		</table>
		
	</body> 
</html>

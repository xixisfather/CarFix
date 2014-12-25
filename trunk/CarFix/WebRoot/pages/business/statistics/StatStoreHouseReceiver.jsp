<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库期间收发统计</title>
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
			pConfig.form = "getStoreHouseReceiverStatAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="getStoreHouseReceiverStatAction.action">
			<table>
				<tr>	
					<td>仓库</td>
					<td><s:select name="tmStoreHouseReceiverStatVo.storeHouseId" list="#request.stroeHouseList" headerValue="所有" headerKey="" listKey="id" listValue="houseName"/></td>
					<td>日期从</td>
					<td  ><s:textfield id="beginDate" name="tmStoreHouseReceiverStatVo.beginDate" />
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						到
						<s:textfield id="endDate" name="tmStoreHouseReceiverStatVo.endDate" />
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="getStoreHouseReceiverStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="仓库期间收发统计"
			toolbarPosition="bottom" skin="E3002" pageSize="20" width="600"
			height="320" >
			<e3t:column property="storeHouseName" title="仓库" />
			<e3t:column property="inOutTypeName" title="出入库类型" />
			<e3t:column property="stockInCostPrice" title="入库成本" />
			<e3t:column property="stockOutCostPrice" title="出库成本" />
			<e3t:column property="sellCostPrice" title="出库销售总金额" />
		</e3t:table>
	</body> 
</html>

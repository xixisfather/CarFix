<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库月度期间收发统计</title>
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
			pConfig.form = "getStoreHouseMounthReceiverStatAction";
			pConfig.showLoadingMsg = true;
		}
		function receiverPrint(){
			document.getElementById("qryyearHidden").value = document.getElementById("qryyearSelect").value;
			document.getElementById("qrymounth").value = document.getElementById("qrymounthSelect").value; 
			document.forms["printStoreHouseMounthReceiverAction"].submit();
		}
	</script>
	<body>
		<s:form action="printStoreHouseMounthReceiverAction.action" target="blank_">
			<s:hidden name="qryyear" id="qryyearHidden" ></s:hidden>
			<s:hidden name="qrymounth" id="qrymounthHidden" ></s:hidden>
		</s:form>
		<s:form action="getStoreHouseMounthReceiverStatAction.action">
			<table>
				<tr>	
					<td>会计年度</td>
					<td><s:select id="qryyearSelect" name="qryyear" list="#request.yearMap" listKey="key" listValue="value"/></td>
					<td>月度</td>
					<td><s:select id="qrymounthSelect" name="qrymounth" list="#request.mounthMap" listKey="key" listValue="value"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
					</td>
					<td>
						<input type="button" onclick="receiverPrint();" value="打印" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="getStoreHouseMounthReceiverStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="仓库期间收发统计"
			toolbarPosition="bottom" skin="E3002" pageSize="20" width="900"
			height="320" >
			<e3t:column property="storeHouseName" title="仓库" />
			<e3t:column property="inOutTypeName" title="出入库类型" />
			<e3t:column property="stockInCostPrice" title="入库成本" />
			<e3t:column property="stockOutCostPrice" title="出库成本" />
			<e3t:column property="sellCostPrice" title="出库销售总金额" />
			<e3t:column property="qcPrice" title="期初" />
			<e3t:column property="qmPrice" title="期末" />
		</e3t:table>
	</body> 
</html>

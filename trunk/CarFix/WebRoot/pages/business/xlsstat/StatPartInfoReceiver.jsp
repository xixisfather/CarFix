<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件期间收发存统计</title>
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
			pConfig.form = "findStPartReceiverAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findStPartReceiverAction.action">
			<table>
				<tr>
					<td valign="top" >
						<table>
							<tr>
								<td>配件代码</td>
								<td><s:textfield  name="stPartReceiver.partCode" id="partCode" /> </td>
								<td>配件名称</td>
								<td><s:textfield  name="stPartReceiver.partName" id="partName" /> </td>
								<td>仓库</td>
								<td  ><s:textfield  name="stPartReceiver.houseName" id="houseName" /> </td>
							</tr>	
							<tr>
								<td >
									<s:submit value="查询" ></s:submit>
								</td>
							</tr>
						</table>
					</td>
					
					
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="findStPartReceiverAction.action" var="tbPartInfo"
			scope="request" items="stPartReceiverList" mode="ajax" caption="配件期间收发存统计"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" >
			
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="100" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="stockinQuantity" title="入库数量" />
			<e3t:column property="stockinPrice" title="入库金额" />
			<e3t:column property="stockoutQuantity" title="出库数量" />
			<e3t:column property="stockoutPrice" title="出库成本" />
			<e3t:column property="storeQuantity" title="当前库存" />
			<e3t:column property="storePrice" title="当前库存成本" />
			
		</e3t:table>
		
		<table width="600" >
			<tr>
				<td align="right" >入库数量：</td>
				<td>${statTotal.totalStockinQuantity}</td>
				<td align="right" >入库金额：</td>
				<td>${statTotal.totalStockinPrice}</td>
				<td align="right" >出库数量：</td>
				<td>${statTotal.totalStockoutQuantity}</td>
				<td align="right" >出库成本：</td>
				<td>${statTotal.totalStockoutPrice}</td>
			</tr>
		</table>
	</body> 
</html>

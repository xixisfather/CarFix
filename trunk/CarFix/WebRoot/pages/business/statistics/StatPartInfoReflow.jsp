<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件流向及流量查询</title>
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
			pConfig.form = "findTbPartInfoReFlowStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbPartInfoTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
				var e3PartInfoId = record.get("id");
				document.getElementById("partInfoId").value = e3PartInfoId;
				document.getElementById("detailPartInfoId").value = e3PartInfoId;
				elementTypeTableQuery();
				document.getElementById("typeValue").value = "";
				reFlowTableQuery();
			});
	
		}
		
		
		function elementTypeTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function elementTypeTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getAllTypeSumReFlowStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		// 表格显示前,通常在这注册单击，双击事件
		function elementTypeTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
				var e3typeValue = record.get("typeValue");
				document.getElementById("typeValue").value = e3typeValue;
				reFlowTableQuery();
			});
	
		}
		
		function reFlowTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function reFlowTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getPartInfoReFlowDetailStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function reFlowQry(){
			var uphouseid = document.getElementById("uphouseid");
			var storehouseid = document.getElementById("storehouseid");
			
			if(uphouseid.value != ""){
				storehouseid.value = uphouseid.value;
			}
			
			reFlowTableQuery();
		}
	</script>
	<body>
		<s:form action="findTbPartInfoReFlowStatAction.action">
			
			<table>
				<tr>
					<td>配件代码</td>
					<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
					<td>配件名称</td>
					<td><s:textfield  name="tbPartInfo.partName" ></s:textfield></td>
					<td>拼音名称</td>
					<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
					<td>仓库</td>
					<td>
						<s:select id="uphouseid" name="tbPartInfo.tmStoreHouse.id" list="#request.tmStoreHouseList" listKey="id" listValue="houseName" emptyOption="true" ></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="查询" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
	
		
		<e3t:table id="tbPartInfoTable" uri="findTbPartInfoReFlowStatAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" caption="配件列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" >
			<e3t:column property="id" hidden="true" title="配件ID" />
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="70" property="unitName" title="计量单位" />
			<e3t:column width="70" property="partBroadType" title="大类" />
			<e3t:column property="modelName" title="车辆类型" />
			<e3t:column width="50" property="storeQuantity" title="库存" />
			<e3t:column width="50" property="lianceQuantity" title="借进量" />
			<e3t:column width="50" property="loanQuantity" title="借出量" />
			<e3t:column width="70" property="lastModifyDate" title="最后发生日期" >
				<fmt:formatDate value="${tbPartInfo.lastModifyDate}" />
			</e3t:column>	
		</e3t:table>
		<s:form action="getAllTypeSumReFlowStatAction.action">
			<s:hidden id="partInfoId" name="tbPartInfo.id" ></s:hidden>
		</s:form>
		
		<s:form action="getPartInfoReFlowDetailStatAction.action">
			<s:hidden id="detailPartInfoId" name="tbPartInfo.id" ></s:hidden>
			<s:hidden id="storehouseid"  name="tbPartInfo.tmStoreHouse.id" ></s:hidden>
			<s:hidden id="typeValue" name="tbPartInfoReFlowStatVo.typeValue" ></s:hidden>
			<table>
				<tr>
					<td>日期从</td>
					<td  ><s:textfield id="beginDate" name="tbPartInfoReFlowStatVo.beginDate" />
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						到
						<s:textfield id="endDate" name="tbPartInfoReFlowStatVo.endDate" />
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>车牌号</td>
					<td>
						<s:textfield name="tbPartInfoReFlowStatVo.licenseCode" />
					</td>
					<td>
						<input type="button" value="查询" onclick="reFlowTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		<table >
			<tbody>
				<tr>
					<td  id="countTd" >
					</td>
		
					<td id="listTd" >
					</td>
				</tr>
			</tbody>
		</table>
					
				<e3t:table id="elementTypeTable" uri="getAllTypeSumReFlowStatAction.action" var="elementType" renderTo="countTd" toolbarPosition="false" toolbarShowPolicy="false"
					scope="request" items="typeResults" mode="ajax" skin="E3002" width="350" pageSize="10"
					height="300" >
					<e3t:column property="typeName" title="进出类型" />
					<e3t:column property="totalQuantity" title="数量" />
					<e3t:column property="typeValue" hidden="true" title="进出类型VALUE" />
				</e3t:table>
				
				<e3t:table id="reFlowTable" uri="getPartInfoReFlowDetailStatAction.action" var="reflow" renderTo="listTd"
					scope="request" items="results" mode="ajax" skin="E3002" width="700" pageSize="10"
					height="300" >
					<e3t:column width="70" property="createDate" title="发生日期" >
						<fmt:formatDate value="${reflow.createDate}" pattern="yyyy-MM-dd" />
					</e3t:column>
					<e3t:column width="70" property="elementType" title="进出类型" />
					<e3t:column width="50" property="inQuantity" title="进数量" />
					<e3t:column width="50" property="outQuantity" title="出数量" />
					<e3t:column width="60" property="ratePrice" title="进出单价" />
					<e3t:column width="60" property="subTotalPrice" title="金额" />
					<e3t:column property="customerName" title="客户" />
					<e3t:column width="80" property="licenseCode" title="牌照号" />
					<e3t:column property="chassisCode" title="底盘号" />
				</e3t:table>
		
		
	</body> 
</html>

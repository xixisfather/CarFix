<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>配件积压查询</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/global.css" />

<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			pConfig.tbar = [
				{
				text : '打印库存汇总单',
				iconCls : 'editIcon',
				handler : function() {
					window.open('<%=request.getContextPath() %>/pages/business/statistics/StatoverStockTbPartInfoPrint.jsp');
				}
} 

			];
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "overStockTbPartInfoStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function showZeroPartInfo(chk){
			var storeQuantity = document.getElementById("storeQuantity");
			if(chk.checked){
				storeQuantity.value = 0;
			}else{
				storeQuantity.value = "";
			}
		}
	</script>
<body>
<s:form action="overStockTbPartInfoStatAction.action">
	<s:hidden id="storeQuantity" name="tbPartInfo.storeQuantity"></s:hidden>
	<table>
		<tr>
			<td>仓库</td>
			<td><s:select name="tbPartInfo.tmStoreHouse.id"
				list="#request.stroeHouseList" emptyOption="true" listKey="id"
				listValue="houseName" /></td>
			<td>车型</td>
			<td><s:select name="tbPartInfo.tmCarModelType.id"
				list="#request.carModelTypeList" emptyOption="true" listKey="id"
				listValue="modelName" /></td>
			<td>最后发生日期</td>
			<td><s:textfield id="lastModifyDate"
				name="tbPartInfo.lastModifyDate" /> <e3c:calendar
				for="lastModifyDate" dataFmt="yyyy-MM-dd" /></td>
			<td><input type="checkbox" onclick="showZeroPartInfo(this);" />不显示库存为零的配件</td>
		</tr>
		<tr>
			<td>配件代码</td>
			<td><s:textfield name="tbPartInfo.partCode"  /></td>
			<td>配件名称</td>
			<td><s:textfield name="tbPartInfo.partName"  /></td>
		</tr>
		<tr>
			<td><input type="button" value="查询"
				onclick="tbPartInfoTableQuery();" /></td>
		</tr>
	</table>
</s:form>


<e3t:table id="tbPartInfoTable"
	uri="overStockTbPartInfoStatAction.action" var="tbPartInfo"
	scope="request" items="tbPartInfoList" mode="ajax"
	varStatus="tbPartInfoStatus" toolbarPosition="bottom" skin="E3002"
	pageSize="10" width="900" height="320">

	<e3t:column property="houseName" title="仓库" />
	<e3t:column property="partCode" title="配件代码" />
	<e3t:column property="partName" title="配件名称" />
	<e3t:column property="modelName" title="车辆类型" />
	<e3t:column width="50" property="storeQuantity" title="库存" />
	<e3t:column width="50" property="lianceQuantity" title="借进量" />
	<e3t:column width="50" property="loanQuantity" title="借出量" />
	<e3t:column width="70" property="costPrice" title="成本价" />
	<e3t:column property="stockMoney" title="库存金额" />
	<e3t:column property="lastModifyDate" title="最后发生日期">
		<fmt:formatDate value="${tbPartInfo.lastModifyDate}" />
	</e3t:column>
	<c:if test="${tbPartInfoStatus.last}">
		<e3t:appendRow>
			<e3t:attribute name="style" value="background-color:#CCCCFF" />
			<e3t:addCell>
				        库存金额合计:
				      </e3t:addCell>
			<e3t:addCell>
				      ${totalStockMoney }
				      </e3t:addCell>
		</e3t:appendRow>
	</c:if>
</e3t:table>
</body>
</html>

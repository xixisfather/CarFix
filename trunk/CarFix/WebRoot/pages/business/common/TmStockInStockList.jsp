<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tmDetTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllStockInByStockAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var stockInId = parent.document.getElementById("${stockInId}");
					var stockInCode = parent.document.getElementById("${stockInCode}");
					var arriveDate = parent.document.getElementById("${arriveDate}");
					var supplierName = parent.document.getElementById("${supplierName}");
					
					//e3列值
					var e3stockInId = record.get("stockInId");		
					var e3stockInCode = record.get("stockInCode");
					var e3arriveDate = record.get("arriveDate");
					var e3supplierName = record.get("supplierName");	
					
					//赋值
					if(stockInId != null)
						stockInId.value = e3stockInId;
					if(stockInCode != null)
						stockInCode.value = e3stockInCode;
					if(arriveDate != null)
						arriveDate.value = e3arriveDate.trim();
					if(supplierName != null)
						supplierName.value = e3supplierName;
						
					parent.extWindow.hide();
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval(e3table+ '()');
				});
		
		}
		
		
	</script>
	<body>
		<s:form action="findAllStockInByStockAction.action" >
			<table>
				<tr>
					<td>入库单号：</td>
					<td><s:textfield  name="tmStockIn.stockInCode" ></s:textfield></td>
					<td>入库凭证：</td>
					<td><s:textfield  name="tmStockIn.oucherCode" ></s:textfield></td>
				</tr>
				<tr>	
					<td>供应商</td>
					<td ><s:select id="supplierId" name="tmStockIn.supplierId" list="#request.tbCustomers" headerKey="" headerValue="所有" listKey="id" listValue="customerName"/></td>
					<td>到货日期：</td>
					<td ><s:textfield cssStyle="width:100px" id="beginDate" name="tmStockIn.beginDate" />
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="endDate" name="tmStockIn.endDate" />
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" 
							onclick="tmDetTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tmDetTable" uri="findAllStockInByStockAction.action" var="remVo"
			scope="request" items="stockList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
			height="320" >
			<e3t:column property="stockInCode" title="采购单号" />
			<e3t:column property="indent" title="订货单号" />
			<e3t:column property="oucherCode"  title="入库凭证" />
			<e3t:column property="arriveDate"  title="到货日期" >
				<fmt:formatDate value="${remVo.arriveDate}" pattern="yyyy-MM-dd" />
			</e3t:column>
			<e3t:column property="supplierName" title="供货商" />
			
			<e3t:column property="stockInId" hidden="true" title="采购入库ID" />
		</e3t:table>
		
	</body> 
</html>

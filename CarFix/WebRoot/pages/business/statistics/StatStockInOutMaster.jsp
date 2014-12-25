<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>收发单据查询</title>
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
		function stockTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function stockTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "stockInOutBillStatActon";
			pConfig.showLoadingMsg = true;
		}
		
		function openWin(){
			var props = "customerId,customerCode,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','客户列表',650,350,props,null);
		}
		
		function printObj(id){
			var actionUrl = "";
			
			var billType = document.getElementById("billType");
			switch(parseInt(billType.value)){
		   		case 1:
		   			//采购入库
			   		actionUrl = "printCGRKAction.action";
			    	break;
			   	case 15:
			   		//采购退货
			    	actionUrl = "printCGTHAction.action";
			    	break;
			    case 16:
			   		//借出登记
			    	actionUrl = "printJCDJAction.action";
			    	break;	
			    case 17:
			   		//借出归还
			    	actionUrl = "printJCGHAction.action";
			   		break;
			   	case 6:
			   		//借进登记
			    	actionUrl = "printJJDJAction.action";
			   		break;
			   	case 7:
			   		//借进归还
			    	actionUrl = "printJJGHAction.action";
			   		break;
			   	case 18:
			   		//移库出仓
			    	actionUrl = "printYKCCAction.action";
			   		break;
			   	case 19:
			   		//移库进仓
			    	actionUrl = "printYKJCAction.action";
			   		break;
			   	case 20:
			   		//维修发料
			    	actionUrl = "printWXFLAction.action";
			   		break;
			   	case 14:
			   		//调拨出库
			    	actionUrl = "printDBCKAction.action";
			   		break;
			   	case 2:
			   		//调拨入库
			    	actionUrl = "printDBJCAction.action";
			   		break;	
		   	 	case 13:
			   		//配件报损
			    	actionUrl = "printPJBSAction.action";
			   		break;	
		   	 	case 3:
			   		//配件报溢
			    	actionUrl = "printPJBYAction.action";
			   		break;
			   	case 11:
			   		//配件销售
			    	actionUrl = "printPJXSAction.action";
			   		break;
			   	case 12:
			   		//领用出库
			    	actionUrl = "printLYCKAction.action";
			   		break;
			    default:
		    }
			editObject( id,actionUrl,600,300);
		}
		
		function maintainStat(type){
			if(type == "1")
				window.open('<%=request.getContextPath() %>/printStatMaintainAction.action');
			else
				window.open('<%=request.getContextPath() %>/printStatStockOutAction.action');
		}
		
		function exportXls(url){
			window.open(url,'_blank');
		}
	</script>
	<body>
		<s:form action="stockInOutBillStatActon.action" >
			
			<table>
				<tr>
					<td>
						单据类型：
					</td>
					<td>
						<s:select id="billType" name="elementValue" list="#request.elementsMap" listKey="key" listValue="value" ></s:select>
					</td>
					<td>
						单据号：
					</td>
					
					<td>
						<s:if test="#request.stockType == 1">
							<s:textfield id="stockInCode" name="tmStockIn.stockInCode"  />
						</s:if>
						<s:if test="#request.stockType == 2">
							<s:textfield id="stockOutCode" name="tmStockOut.stockOutCode"  />
						</s:if>
						<s:if test="#request.stockType == 7">
							<s:textfield id="maintainCode" name="tbMaintainPartContent.maintainCode"  />
						</s:if>
					</td>
				</tr>
				<tr>
					<td>日期从</td>
					<td>
						<s:if test="#request.stockType == 1">
							<s:hidden id="customerId" name="tmStockIn.supplierId" ></s:hidden>
							<s:textfield id="beginDate" name="tmStockIn.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmStockIn.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 2">
							<s:hidden id="customerId" name="tmStockOut.customerBill" ></s:hidden>
							<s:textfield id="beginDate" name="tmStockOut.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmStockOut.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 3">
							<s:hidden id="customerId" name="tmLianceRegister.supplierId" ></s:hidden>
							<s:textfield id="beginDate" name="tmLianceRegister.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmLianceRegister.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 4">
							<s:textfield id="beginDate" name="tmLianceReturn.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmLianceReturn.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 5">
							<s:hidden id="customerId" name="tmLoanRegister.customerId" ></s:hidden>
							<s:textfield id="beginDate" name="tmLoanRegister.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmLoanRegister.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 6">
							<s:textfield id="beginDate" name="tmLoanReturn.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tmLoanReturn.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						<s:if test="#request.stockType == 7">
							<s:textfield id="beginDate" name="tbMaintainPartContent.beginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
							到
							<s:textfield id="endDate" name="tbMaintainPartContent.endDate" />
							<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
						</s:if>
						
					</td>
					
					<td>客户号：</td>
					<td><s:textfield id="customerCode" name="tbCustomer.customerCode" onfocus="openWin();"  /></td>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="tbCustomer.customerName" /></td>
					
					<s:if test="#request.stockType == 7">
						<td>领料人</td>
						<td><s:textfield id="userRealName" name="tbMaintainPartContent.userRealName"  /></td>
					</s:if>
				</tr>
				<tr>
					<s:if test="#request.stockType == 1">
						<td >配件代码</td>
						<td><s:textfield id="partCode" name="tmStockIn.partCode"  /></td>
						<td>配件名称</td>
						<td><s:textfield id="partName" name="tmStockIn.partName"  /></td>
					</s:if>
					<s:if test="#request.stockType == 2">
						<td>配件代码</td>
						<td><s:textfield id="partCode" name="tmStockOut.partCode"  /></td>
						<td>配件名称</td>
						<td><s:textfield id="partName" name="tmStockOut.partName"  /></td>
					</s:if>
					<s:if test="#request.stockType == 7">
						<td>配件代码</td>
						<td><s:textfield id="partCode" name="tbMaintainPartContent.partCode"  /></td>
						<td>配件名称</td>
						<td><s:textfield id="partName" name="tbMaintainPartContent.partName"  /></td>
					</s:if>
					
				</tr>
				<tr>
				
					<td>
						<s:submit value="查询" ></s:submit>
					</td>
					<td>
						
						<s:if test="#request.stockType == 7">
							<input type="button" onclick="maintainStat(1);" value="维修发料统计汇总" />
							<input type="button" onclick="exportXls('wxflStatExportXlsAction.action')" value="xls导出" />
						</s:if>
						<s:if test="#request.stockType == 2">
							<input type="button" onclick="maintainStat(2);" value="配件出库统计汇总" />
						</s:if>
						<s:if test="#request.stockType == 1">
							<input type="button" onclick="exportXls('cgrkStatExportXlsAction.action')" value="xls导出" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="stockTable" uri="stockInOutBillStatActon.action" var="stock"
			scope="request" items="results" mode="ajax"   varStatus="stockInStatus"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000" caption="${e3TableCaption}列表"
			height="320" >
			<s:if test="#request.stockType == 1">
				<e3t:column property="arriveDate" title="入库日期" >
					<fmt:formatDate  value="${stock.arriveDate}" />
				</e3t:column>
				<e3t:column property="stockInCode" title="入库单号" />
				<e3t:column property="customerCode" title="供应商号" />
				<e3t:column property="customerName" title="供应商名" />
				<e3t:column property="userName" title="操作人" />
				<e3t:column property="partCode" title="配件代码" />
				<e3t:column property="partName" title="配件名称" />
				<e3t:column property="quantity" title="入库数量" />
				<e3t:column property="costPrice" title="价格" />
				<e3t:column property="storeCostPrice" title="成本价" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.stockInId}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			<s:if test="#request.stockType == 2">
				<e3t:column property="stockOutDate" beanProperty="tmStockOutVo.stockOutDate" title="出库日期" >
					<fmt:formatDate  value="${stock.tmStockOutVo.stockOutDate}" />
				</e3t:column>
				<e3t:column property="stockOutCode" beanProperty="tmStockOutVo.stockOutCode"  title="出库单号" />
				<e3t:column property="customerCode" beanProperty="tmStockOutVo.customerCode" title="客户号" />
				<e3t:column property="customerName" beanProperty="tmStockOutVo.customerName" title="客户名称" />
				<e3t:column property="userName" beanProperty="tmStockOutVo.userName" title="发料人" />
				<e3t:column property="userRealName" beanProperty="tmStockOutVo.userRealName" title="领料人" />
				<e3t:column property="partCode" title="配件代码" />
				<e3t:column property="partName" title="配件名称" />
				<e3t:column property="quantity" title="出库数量" />
				<e3t:column property="price" title="价格" />
				<e3t:column property="freeName" title="免费标志" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.stockOutId}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			<s:if test="#request.stockType == 3">
				<e3t:column property="lianceDate" title="借进日期" >
					<fmt:formatDate  value="${stock.lianceDate}" />
				</e3t:column>
				<e3t:column property="lianceBill" title="借进单号" />
				<e3t:column property="customerCode" title="供应商号" />
				<e3t:column property="customerName" title="供应商名" />
				<e3t:column property="userName" title="经办人" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.lianceId}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			
			<s:if test="#request.stockType == 4">
				<e3t:column property="returnDate" title="借进归还日期" >
					<fmt:formatDate  value="${stock.returnDate}" />
				</e3t:column>
				<e3t:column property="lianceReturnBill" title="借进归还单号" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.id}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			
			
			<s:if test="#request.stockType == 5">
				<e3t:column property="loanDate" title="借出日期" >
					<fmt:formatDate  value="${stock.loanDate}" />
				</e3t:column>
				<e3t:column property="loanBill" title="借出单号" />
				<e3t:column property="customerCode" title="客户号" />
				<e3t:column property="customerName" title="客户名称" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.loanId}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			<s:if test="#request.stockType == 6">
				<e3t:column property="returnDate" title="借出归还日期" >
					<fmt:formatDate  value="${stock.returnDate}" />
				</e3t:column>
				<e3t:column property="loanReturnBill" title="借出归还单号" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.id}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
			
			<s:if test="#request.stockType == 7">
				<e3t:column property="maintainCode" title="维修发料单号" />
				<e3t:column property="entrustCode" title="委托书号" />
				<e3t:column property="partCode" title="配件代码" />
				<e3t:column property="partName" title="配件名称" />
				<e3t:column property="partQuantity" title="出库数量" />
				<e3t:column property="price" title="价格" />
				<e3t:column property="userRealName" title="领料人" />
				<e3t:column property="freeName" title="免费标志" />
				<e3t:column property="no" title="操作" >
					&nbsp;&nbsp;
					<a href="#" onclick="printObj('${stock.maintainCode}');">
						<font color="blue">
							打印
						</font>
					</a>
				</e3t:column>
			</s:if>
		</e3t:table>
		<s:if test="#request.stockType == 7">
			<script language="javascript">
				function stockTableConfigHandler(pConfig) {
				
					pConfig.tbar = [
				
					{	
						text : '打印汇总报表',
						iconCls : 'editIcon',
						handler : function() {
							window.open('<%=request.getContextPath() %>/printStatMaintainAction.action');
						}
					} ];
				
				}
			</script>
		</s:if>
	</body> 
</html>

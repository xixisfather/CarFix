<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库查询</title>
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
		function stockInDetailTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function stockInDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "stockInTbPartInfoByStatActon";
			pConfig.showLoadingMsg = true;
		}
		
		function openWin(){
			var props = "customerId,customerName,customerName&types=2,3";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
		
		function query(){
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_partCode").value = document.getElementById("partCode").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_partName").value = document.getElementById("partName").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_beginDate").value = document.getElementById("beginDate").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_endDate").value = document.getElementById("endDate").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_supplierId").value = document.getElementById("customerId").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_busType").value = document.getElementById("busType").value;
			 document.frames["tmStockinDetailTab"].document.getElementById("iframe_payMent").value = document.getElementById("payMent").value;

			 document.frames["tmStockinDetailTab"].detailSubmit();
			 
			 if(document.frames["tbCustomerTab"] != null){
				 document.frames["tbCustomerTab"].document.getElementById("iframe_partCode").value = document.getElementById("partCode").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_beginDate").value = document.getElementById("beginDate").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_endDate").value = document.getElementById("endDate").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_supplierId").value = document.getElementById("customerId").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_partName").value = document.getElementById("partName").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_busType").value = document.getElementById("busType").value;
				 document.frames["tbCustomerTab"].document.getElementById("iframe_payMent").value = document.getElementById("payMent").value;
					
				 document.frames["tbCustomerTab"].detailSubmit();
			 }
			 
			 if(document.frames["masterStockTab"] != null){
				 document.frames["masterStockTab"].document.getElementById("iframe_partCode").value = document.getElementById("partCode").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_beginDate").value = document.getElementById("beginDate").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_endDate").value = document.getElementById("endDate").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_supplierId").value = document.getElementById("customerId").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_partName").value = document.getElementById("partName").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_busType").value = document.getElementById("busType").value;
				 document.frames["masterStockTab"].document.getElementById("iframe_payMent").value = document.getElementById("payMent").value;
					
				 document.frames["masterStockTab"].detailSubmit();
			 }
			 
			 
		}
		
		function exportXls(){
			
			 document.getElementById("exp_partCode").value = document.getElementById("partCode").value;
			 document.getElementById("exp_beginDate").value = document.getElementById("beginDate").value;
			 document.getElementById("exp_endDate").value = document.getElementById("endDate").value;
			 document.getElementById("exp_supplierId").value = document.getElementById("customerId").value;
			 document.getElementById("exp_partName").value = document.getElementById("partName").value;
			 document.getElementById("exp_busType").value = document.getElementById("busType").value;
			 document.getElementById("exp_payMent").value = document.getElementById("payMent").value;
			 document.forms["stockInDetailsExportXlsAction"].submit();
			//window.open(url,'_blank');
		}
	</script>
	<body>
		<s:form action="stockInDetailsExportXlsAction.action" >
			<s:hidden name="tmStockIn.partCode" id="exp_partCode" ></s:hidden>
			<s:hidden name="tmStockIn.beginDate" id="exp_beginDate" ></s:hidden>
			<s:hidden name="tmStockIn.endDate" id="exp_endDate" ></s:hidden>
			<s:hidden name="tmStockIn.supplierId" id="exp_supplierId" ></s:hidden>
			<s:hidden name="tmStockIn.partName" id="exp_partName" ></s:hidden>
			<s:hidden name="tmStockIn.busType" id="exp_busType" ></s:hidden>
			<s:hidden name="tmStockIn.payMent" id="exp_payMent" ></s:hidden>
			<s:hidden  id="customerId" ></s:hidden>
		</s:form>
	
		<s:form action="stockInTbPartInfoByStatActon.action">
			<table>
				<tr>
					<td valign="top" >
						<table>
							<tr>
								<td>日期从</td>
								<td  ><s:textfield id="beginDate" name="tmStockIn.beginDate" />
									<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
									到
									<s:textfield id="endDate" name="tmStockIn.endDate" />
									<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td>供应商</td>
								<!-- <td ><s:select id="supplierId" name="tmStockIn.supplierId" list="#request.tbCustomers" headerKey="" headerValue="所有" listKey="id" listValue="customerName"/></td>
								 -->
								<td><s:textfield id="customerName" onfocus="openWin();" /></td>
							</tr>	
							<tr>
								<td>配件代码</td>
								<td  ><s:textfield  name="tmStockIn.partCode" id="partCode" /> </td>
							</tr>
							<tr>
								<td>配件名称</td>
								<td  ><s:textfield  name="tmStockIn.partName" id="partName" /> </td>
							</tr>
							<tr>
								<td>业务类型</td>
								<td >
									<s:select id="busType" name="tmStockIn.busType" list="#request.busTypes" listKey="key" listValue="value"/>
								</td>
							</tr>
							<tr>
								<td>支付方式</td>
								<td >
									<s:select emptyOption="true" id="payMent" name="tmStockIn.payMent" list="#request.panMentMap" listKey="key" listValue="value"/>
								</td>
							</tr>
							<tr>
								<td >
									<input type="button" value="查询" onclick="query();" />
								</td>
								
								<td>
									<input type="button" onclick="exportXls()" value="xls导出" />
								</td>
							</tr>
						</table>
					</td>
					
					
				</tr>
			</table>
		</s:form>
		<div id='tabPlaceHolder'></div>
		
	<script type="text/javascript">
		
		
		
		Ext.onReady(function(){
			
		TabPanel.create('tabPlaceHolder',350,
		[
			
			{
				id:'tmStockinDetailTab' , title:'采购单明细',disabled:false,url:'stockInTbPartInfoByStatActon.action'
				
			},
			{
				id:'tbCustomerTab' , title:'供应商列表',disabled:false,url:'stockInGroupCustomerByStatAction.action'
				
			},
			{
				id:'masterStockTab' , title:'供货信息',disabled:false,url:'stockInMasterBillByStatAction.action'
			}
		]);
	});
	
	</script>	
		
	</body> 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>销售退货</title>
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
		var ctx = "<%= request.getContextPath() %>";
		var partInfoArray = new Array() 			//dataGrid 数据容器
		var outGrid = null;							//dataGrid对象
		var store = null;							//数据仓库
		var loanRetId = "";							//配件归还ID 
	
	
	
		function tmSellReturnTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				id : 'ain',
				text : '保存',
				iconCls : 'addIcon',
				handler : function() {
					addPartCollection('8000');
				}
			}, '', '-', '', {
				id : 'allin',
				text : '确认入库',
				iconCls : 'addIcon',
				handler : function() {
					addPartCollection('8002');
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmSellReturnTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findSellReturnAction";
			pConfig.showLoadingMsg = true;
		}
		
		function deleteRow(){
			var selectedRow = tmSellReturnTableE3ExtGrid.getSelectionModel().getSelected();
			tmSellReturnTableE3ExtStore.remove(selectedRow);
		}	
		
		function vaildeQuantity(retQuan,sourceQuanId){
			var sq = document.getElementById(sourceQuanId);
 			if(sq.value == 0 || parseFloat(retQuan.value) > parseFloat(sq.value)){
				Ext.MessageBox.alert('温馨提示：', '输入数量有误！', null);
				this.focus();
			} 	
		}
		
		
		
		//添加入库单
		function addPartCollection(confirm){
			var partCol = "";
			var totalQu = 0 ;
			var totalPr = 0 ;
			for(var j=0 ; j<tmSellReturnTableE3ExtStore.getCount(); j++){
				//每行的record对象
				var record = tmSellReturnTableE3ExtStore.getAt(j);
				var partinfoId = record.get("partinfoId");														// 配件ID
				var stockOutId = record.get("stockOutId");														// 借出登记ID
				var stockOutDetailId = record.get("stockOutDetailId");											// 借出登记详细ID
				var returnquantity = document.getElementById("returnquantity"+stockOutDetailId).value;			// 销售退货数量
				var price = record.get("price");																// 销售价
				var str = partinfoId+":"+price+":"+returnquantity+":"+stockOutDetailId;
				partCol += str + ",";
				
				totalQu = parseFloat(returnquantity)+ parseFloat(totalQu);
				totalPr += parseFloat(returnquantity)* parseFloat(price);
			}
			
			
			document.getElementById("totalQuantity").value = totalQu;
			document.getElementById("totalPrice").value = totalPr;
			document.getElementById("partCol").value = partCol;
			document.getElementById("soi").value = document.getElementById("qsoi").value;
			
			
			if(tmSellReturnTableE3ExtStore.getCount() < 1){
				Ext.MessageBox.alert('温馨提示：', '内容不能为空.', null);
				return;
			}
			
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
			/*
			if(confirm ==  "8000"){
				//保存单据
				Ext.getCmp('ain').disable();
				saveSellReturn(confirm);
			}
			if(confirm ==  "8002"){
				//单据入库 -- 如果之前保存过了单据则进行更新库存操作
				if(loanRetId == ""){
					saveSellReturn(confirm);
				}else{
					updateSellReturn();
				}
			}
			*/
		}
		
		
		
		/*保存入库*/
		function saveSellReturn(confirm){
			var url = ctx+"/insertSellReturnAction.action?";
			var pars = "isConfirm="+confirm+"&";
			pars+=Form.serialize($(insertSellReturnAction));
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:savecallback}
			);
			
		}
		
		function savecallback(originalRequest){
			var tminfo = originalRequest.responseText.split(',');
			loanRetId  = tminfo[0];
			var suc = tminfo[1];
			
			if(suc == parseInt("8002")){
				window.location.href = ctx+"/findSellReturnAction.action";
			}
		}
		/*更新入库*/
		function updateSellReturn(){
			var url = ctx+"/updateSellReturnStateAction.action?";
			var pars = "id="+loanRetId;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:updatecallback}
			);
		}
		
		function updatecallback(originalRequest){
			loanRetId = "";
			window.location.href = ctx+"/findSellReturnAction.action";
		}
		
		
		function openWin(){
			var props = "qsoi,stockOutCode,sellDate,customerName";
			showCommonWin('findAllTmStockOutBySellAction.action','销售单列表',575,355,props,'tmSellReturnTable');
		}
	</script>
	<body>
		<s:form action="insertSellReturnAction.action">
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden  id="soi" name="tmStockIn.stockOutId" ></s:hidden>
			<s:hidden name="totalQuantity" ></s:hidden>
			<s:hidden name="totalPrice" ></s:hidden>
			<table>
				<tr>
					
					<td>退货日期：</td>
					<td><s:textfield id="arrdate" name="tmStockIn.arriveDate" 
							readonly="true" />
						<e3c:calendar for="arrdate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>操作员：</td>
					<td>${tmUseruserRealName }</td>
				</tr>
			</table>
		</s:form>
		
		<s:form action="findSellReturnAction.action" >
			<s:hidden id="qsoi" name="stockOutId" ></s:hidden>
			<table>
				<tr>
					<td>原销售单：</td>
					<td><s:textfield id="stockOutCode" onfocus="openWin();"  ></s:textfield>  </td>
					<td>销售日期：</td>
					<td><e3c:calendar id="sellDate" dataFmt="yyyy-MM-dd" /></td>
					<td>客户：</td>
					<td><s:textfield id="customerName" /></td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tmSellReturnTable" uri="findSellReturnAction.action" var="vo" 
			scope="request" items="detVos" mode="ajax"  caption="销售退货"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" width="60" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="unitName" width="60" title="单位" />
			<e3t:column property="returnquantity" width="60" title="数量" >
				<input type="text" onchange="vaildeQuantity(this,'quantity${vo.stockOutDetailId}')" id="returnquantity${vo.stockOutDetailId}" value="${vo.quantity}"  />
			</e3t:column>
			<e3t:column property="price" title="销售价" />
			<e3t:column property="sellPrice" title="销售金额" />
			<e3t:column property="no" title="相关操作" 
				sortable="false">
				<a href="#" onclick="deleteRow();" >
					<font color="blue">
						删除${i }
				    </font>
				</a>
			</e3t:column>
			<!-- 隐藏域 -->
			<e3t:column property="quantity"  hidden="true" title="数量" >
				<input type="hidden" id="quantity${vo.stockOutDetailId}" value="${vo.quantity}" />
			</e3t:column>
			<e3t:column property="partinfoId" hidden="true"  title="配件ID" />
			<e3t:column property="stockOutId" hidden="true"  title="出库ID" />
			<e3t:column property="stockOutDetailId" hidden="true" title="出库详细ID" />
			
			<!-- 隐藏域 -->
		</e3t:table>
		
		
	
		
		
		<br>
		<br>		
		<div id="partinfocollectionDiv" ></div>
		
	
	</body> 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改采购退货</title>
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
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		
		
		function openCustomerWin(){
			document.getElementById("loan").value = "";
			document.getElementById("loanBill").value = "";
			var props = "cust,null,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,'tmStockDetailTable');
		}
		
		function openLoanRegWin(){
			document.getElementById("cust").value = "";
			document.getElementById("customerName").value = "";
			var props = "loan,loanBill,null";
			showCommonWin('findAllTmLoanRegisterAction.action','借出列表',575,355,props,'tmStockDetailTable');
		}
				
		function tmStockReturnTableConfigHandler(pConfig) {
			pConfig.tbar = [
			{	
				id : 'savdj',
				text : '保存',
				iconCls : 'addIcon',
				handler : function() {
					updateEntity('8000');
				}
			}, '', '-', '', {
				text : '确认入库',
				iconCls : 'addIcon',
				handler : function() {
					updateEntity('8002');
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockReturnTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "initEditStockInPageAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		/****  借出明细 e3Table ****/
		function tmStockDetailTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findLoanRegDetailAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function tmStockDetailTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//处理数据
					rowHandler(record);
				});
		
		}
		
		function rowHandler(record){
			/*采购退货值*/
			var e3houseName = record.get("houseName");
			var e3partCode = record.get("partCode");
			var e3partName = record.get("partName");
			var e3unitName = record.get("unitName");
			var e3quantity = record.get("quantity");						//采购数量
			var e3stockInDetailId = record.get("stockInDetailId");			//借出详细ID
			var e3partinfoId = record.get("partinfoId");					//配件ID
			var e3costPrice = record.get("storeCostPrice");					//成本价
			//update by baijx
			var e3hasQuantity = record.get("hasQuantity");					// 已退数量
			/*采购退货值*/
			
			
			
			/*验证是否能添加*/
			for(var i=0; i<tmStockReturnTableE3ExtStore.getCount(); i++){
				var record = tmStockReturnTableE3ExtStore.getAt(i);
				var partinfoId = record.get("partinfoId");
				if(e3partinfoId == partinfoId){
					alert("不能重复添加!")
					return ;
				}
			}
			/*验证是否能添加*/
		
			var idx = tmStockReturnTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
				
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partCode', mapping: 'partCode'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'unitName', mapping: 'unitName'},
			    {name: 'currQuantity', mapping: 'currQuantity'},
			   	{name: 'costPrice', mapping: 'costPrice'},
			    {name: 'stockInDetailId', mapping: 'stockInDetailId'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			     {name: 'hasQuantity', type: 'float'},
			    {name: 'no', mapping: 'no'}
			    
			    
			]);
			
			var myNewRecord = new TopicRecord({	
			    houseName: e3houseName,
			    partCode: e3partCode,
			    partName: e3partName,
			    unitName: e3unitName,
			   	currQuantity: "<input onchange=valiQuantity(this,\""+e3quantity+"\",\""+e3hasQuantity+"\") id=currQuantity"+e3partinfoId+" value="+e3quantity+" />",
			   	costPrice:e3costPrice,	
			   	stockInDetailId: e3stockInDetailId,
			   	partinfoId: e3partinfoId,
			    no: '&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>'
			});
			
			tmStockReturnTableE3ExtStore.insert(idx,myNewRecord);
			
		}
		/****  借出明细 e3Table ****/
		
		
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmStockReturnTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmStockReturnTableE3ExtStore.remove(selectedRow);
		}
		
		/*验证数量，归还数量不能大于借出数量*/
		function valiQuantity(currentQuantity,quantity,hasQuantityA){
			/*
			if(parseFloat(currentQuantity.value)<= 0 || parseFloat(quantity)< parseFloat(currentQuantity.value)){
				currentQuantity.value = quantity;
				currentQuantity.focus();
				Ext.MessageBox.alert('温馨提示：', '数量输了有误！', null);
			}
			*/
			if(null==hasQuantityA || "" == hasQuantityA) hasQuantityA = 0;
			var hasquantity = parseFloat(hasQuantityA);					 //已还数量
			var newquantity = parseFloat(quantity);	 				 //现有数量
			var returnquantity = currentQuantity.value;	 			 //本次归还数量
			
			if(returnquantity > (newquantity - hasquantity) || returnquantity <=0){
				currentQuantity.focus();
				currentQuantity.value = "";
				Ext.MessageBox.alert('温馨提示：', '数量输了有误！', null);
			}
		}
		
		function updateEntity(confirm){
			var param = "";
			var totalNum = 0;
			var totalprice = 0;
			
			for(var i=0; i<tmStockReturnTableE3ExtStore.getCount(); i++){
				var record = tmStockReturnTableE3ExtStore.getAt(i);
					
				var e3partInfoId = record.get("partinfoId");										//配件ID
				
				var e3stockInDetailId = record.get("stockInDetailId");								//借出登记明细ID
				var currQuantity = document.getElementById("currQuantity"+e3partInfoId);			//本次归还		
				var e3costPrice = record.get("costPrice");											//归还时的成本价
				
				param += e3partInfoId+":"+currQuantity.value+":"+e3costPrice+":"+e3stockInDetailId+",";
				
				totalNum += parseFloat(currQuantity.value);
				totalprice += Math.round( parseFloat(currQuantity.value) * parseFloat(e3costPrice) *100)/100 ;
			}
			
			
			document.getElementById("totalQuantity").value = totalNum;
			document.getElementById("totalPrice").value = totalprice;
			var partCol = document.getElementById("partCol");
			partCol.value = param;

			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
			/*
			if(confirm ==  "8000"){
				//保存单据
				Ext.getCmp('savdj').disable();
				savestock(confirm);
			}
			if(confirm ==  "8002"){
				//单据入库 -- 如果之前保存过了单据则进行更新库存操作
				if(stockInId == "")
					savestock(confirm);
				else
					updatestock();
				
			}
			*/
		
		}	
		
		 /*保存入库*/
		function savestock(confirm){
			var url = ctx+"/"+insertAction+".action?";
			var pars = "isConfirm="+confirm+"&";
			pars+=Form.serialize($(insertAction));
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:savecallback}
			);
			
		}
		
		function savecallback(originalRequest){
			var tminfo = originalRequest.responseText.split(',');
			stockInId  = tminfo[0];
			var suc = tminfo[1];
			if(suc == parseInt("8002")){
				window.location.href = ctx+"/"+initPageAction+".action";
			}
		}
		/*更新入库*/
		function updatestock(){
			var url = ctx+"/"+updateAction+".action?";
			var pars = "id="+stockInId;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:updatecallback}
			);
		}
		
		function updatecallback(originalRequest){
			stockInId = "";
			window.location.href = ctx+"/"+initPageAction+".action";
		}
		
		var stockInId = "";
	</script>
	<body >
		<s:form action="updateStockReturnAction.action">
				<s:hidden name="partCol" id="partCol" ></s:hidden>
				<s:hidden name="tmStockOut.stockInId" ></s:hidden>
				<s:hidden name="tmStockOut.id" ></s:hidden>
				<s:hidden name="tmStockOut.totalQuantity" id="totalQuantity" ></s:hidden>
				<s:hidden name="tmStockOut.totalPrice" id="totalPrice" ></s:hidden>
				<s:hidden name="tmStockOut.userId" ></s:hidden>
				<s:hidden name="tmStockOut.createDate" ></s:hidden>
				<s:hidden name="tmStockOut.outType" ></s:hidden>
				<table>
					<tr>
						<td>退货单号：</td>
						<td><s:textfield  cssStyle="color:#FF0000"  readonly="true" name="tmStockOut.stockOutCode" /></td>
						<td>退货日期：</td>
						<td><s:textfield id="stockOutDate" name="tmStockOut.stockOutDate" readonly="true" >
								<s:param name="value"><s:date name="tmStockOut.stockOutDate" format="yyyy-MM-dd"/></s:param>
							</s:textfield>
							<e3c:calendar for="stockOutDate" dataFmt="yyyy-MM-dd" />
						</td>
						<td>操作员：</td>
						<td>${tmUser.userRealName }</td>
					</tr>
				</table>
		</s:form>
		
		<table>
			<tr>
				<td>采购单号：</td>
				<td><s:textfield name="#request.tmStockIn.stockInCode" readonly="true"  ></s:textfield></td>
				<td>到货日期：</td>
				<td><s:textfield name="#request.tmStockIn.arriveDate" readonly="true" >
						<s:param name="value"><s:date name="#request.tmStockIn.arriveDate" format="yyyy-MM-dd"/></s:param>
					</s:textfield>
				</td>
			</tr> 
	
		</table>
			
		
		<e3t:table id="tmStockDetailTable" uri="initEditStockReturnPageAction.action" var="collectionVo"
			scope="request" items="detInVos" mode="ajax"  caption="采购入库明细"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="60" property="unitName" title="单位" />
			<e3t:column width="60" property="quantity" title="采购数量" />
			<e3t:column width="60" property="storeCostPrice" title="成本价" />
			<e3t:column property="hasQuantity" title="已退数量" />
			<!-- 隐藏域 -->
			<e3t:column property="partinfoId" hidden="true"  title="配件ID" />
			<e3t:column property="stockInDetailId" hidden="true"  title="采购明细ID" />
			
			<!-- 隐藏域 -->
		</e3t:table>
	
	
	
		 
		 <e3t:table id="tmStockReturnTable" uri="initEditStockReturnPageAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="采购退货明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="60" property="unitName" title="单位" />
			<e3t:column width="70" property="currQuantity" title="退货数量" >
				<input style="width:60px" onchange="valiQuantity(this,'${detVo.quantity}','${det.hasQuantity}')" id="currQuantity${detVo.partinfoId}" value="${detVo.quantity}" />
			</e3t:column>
			<e3t:column width="60" property="costPrice" title="成本价" />

			<e3t:column property="stockInDetailId" hidden="true" title="采购明细ID" />
			<e3t:column property="partinfoId" hidden="true" title="配件ID" />
			<e3t:column property="no" title="操作" sortable="false">
				&nbsp;
  				<a href="#" onclick="delRecord();" >
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		
	</body> 
	

</html>

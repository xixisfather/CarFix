<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改销售退货</title>
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
		var gtitle = "${gtitle}";						//grid标题
		
		
	
		function openWin(){
			var props = "customerId,customerCode,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
		
				
		function tmStockInDetailTableConfigHandler(pConfig) {
			pConfig.tbar = [
			{	
				id : 'savdj',
				text : '保存',
				iconCls : 'addIcon',
				handler : function() {
					updateTmStockInDet('8000');
				}
			}, '', '-', '', {
				text : '确认入库',
				iconCls : 'addIcon',
				handler : function() {
					updateTmStockInDet('8002');
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockInDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "initEditSellReturnPageAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function handlerE3Table(){
			var totalnumspan = document.getElementById("totalnumspan");
			var totalpricespan = document.getElementById("totalpricespan");
			
			var totalNum = 0;
			var totalprice = 0;
			
			for(var j=0 ; j<tmStockInDetailTableE3ExtStore.getCount(); j++){
				//每行的record对象
				var record = tmStockInDetailTableE3ExtStore.getAt(j);
				var partinfoId = record.get("partinfoId");											//配件ID
				var userQuantity = document.getElementById("userQuantity"+partinfoId);				//数量
				var spanXsxj = document.getElementById("spanXsxj"+partinfoId);						//销售小计
				//数量合计
				totalNum = parseFloat(userQuantity.value) + totalNum;
				//销售合计
				totalprice = parseFloat(spanXsxj.innerHTML) + totalprice;
			
			}
			
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			
			
		}
		
		 var tbPartInfoObj = {};
		 var stockInId = "";							//入库单ID
		 
		/**计算单价，小计**/
		function jsRecord(partinfoId){
			var userQuantity = document.getElementById("userQuantity"+partinfoId);					//数量
			var spanSellPrice = document.getElementById("spanSellPrice"+partinfoId);				//销售价
			var spanXsxj = document.getElementById("spanXsxj"+partinfoId);							//销售小计
			
			spanXsxj.innerHTML = Math.round(parseFloat(userQuantity.value)* parseFloat(spanSellPrice.innerHTML)*100)/100;
			
			handlerE3Table();
		}
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmStockInDetailTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmStockInDetailTableE3ExtStore.remove(selectedRow);
			
			handlerE3Table();
		}	
		
		 
		 /** 修改配件入库**/
		 function updateTmStockInDet(confirm){
		 
		 	var str = "";
	 		for(var i=0; i<tmStockInDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockInDetailTableE3ExtStore.getAt(i);
				
				var partinfoId = record.get("partinfoId");
				var userQuantity = document.getElementById("userQuantity"+partinfoId);					//数量
				var spanSellPrice = document.getElementById("spanSellPrice"+partinfoId);				//销售价
				var stockOutDetailId = record.get("stockOutDetailId");									// 销售出库明细ID
				
				str += partinfoId +":"+ userQuantity.value  +":"+ parseFloat(spanSellPrice.innerHTML) +":"+ stockOutDetailId +",";
			}
			var partCol = document.getElementById("partCol");
			var totalQuantity = document.getElementById("totalQuantity");
			var totalPrice = document.getElementById("totalPrice");
			partCol.value = str;
			totalQuantity.value = document.getElementById("totalnumspan").innerHTML;
			totalPrice.value = document.getElementById("totalpricespan").innerHTML;
			
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
	</script>
	<body onload="handlerE3Table();" >
		<s:form action="updateSellReturnAction.action">
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmStockIn.stockOutId" ></s:hidden>
			<s:hidden id="totalQuantity" name="tmStockIn.totalQuantity" ></s:hidden>
			<s:hidden id="totalPrice" name="tmStockIn.totalPrice" ></s:hidden>
			<s:hidden name="tmStockIn.id" ></s:hidden>
			<s:hidden name="tmStockIn.userId" ></s:hidden>
			<s:hidden name="tmStockIn.createDate" ></s:hidden>
			<s:hidden name="tmStockIn.inType" ></s:hidden>
			<table>
				<tr>
					<td>退货单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" name="tmStockIn.stockInCode" readonly="true" /></td>
					<td>退货日期：</td>
					<td><s:textfield id="arrdate" name="tmStockIn.arriveDate" readonly="true" >
							<s:param name="value"><s:date name="tmStockIn.arriveDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="arrdate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>操作员：</td>
					<td>${tmUseruserRealName }</td>
				</tr>
			</table>
		</s:form>
		 <br>
		
		 
		 <e3t:table id="tmStockInDetailTable" uri="initEditSellReturnPageAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="销售退货明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="userQuantity"  title="出库数量" >
				<input onchange="jsRecord('${detVo.partinfoId}')" id="userQuantity${detVo.partinfoId}" type="text" value="${detVo.quantity}"  style="width:80px" />
			</e3t:column>
			<e3t:column property="sellPrice"  title="销售价" >
				<span id="spanSellPrice${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2"  value="${detVo.costPrice}"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="xsxj"  title="销售小计" >
				<span id="spanXsxj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2"  value="${detVo.costPrice * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="no" title="操作" sortable="false">
				&nbsp;
  				<a href="#" onclick="delRecord();" >
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="stockOutDetailId"  title="销售出库明细ID" />
			<e3t:column property="costPrice"  hidden="true"  title="销售价" />
			<e3t:column property="partinfoId"  hidden="true"  title="配件ID" />
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		 合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;销售合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;
		
	</body> 
	

</html>

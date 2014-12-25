<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>移进入库</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/stockout/raak.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var shiftId = "";
		
		
		function tmDetTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	id:'savdj',
				text : '保存单据',
				iconCls : 'addIcon',
				handler : function() {
					addItem('8000');
				}
			}, '', '-', '',{	
				text : '确认单据',
				iconCls : 'addIcon',
				handler : function() {
					addItem('8002');
				}
			}];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findRemoveStockAction";
			pConfig.showLoadingMsg = true;
		}
		
		function addItem(confirm){
			/*验证*/
			var shiftinStoreHouseId = document.getElementById("shiftinStoreHouseId").value;
			var removeStoreHouseId = document.getElementById("removeStoreHouseId").value;
			if(shiftinStoreHouseId == removeStoreHouseId){
				Ext.MessageBox.alert('温馨提示：', '源仓库不能与目标仓库相同!', null);
				return;
			}
			document.getElementById("rsId").value =  document.getElementById("removeStockId").value;
			
			
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
			/*
			if(confirm ==  "8000"){
				//保存单据
				Ext.getCmp('savdj').disable();
				saveShinftin(confirm);
			}
			if(confirm ==  "8002"){
				//单据入库 -- 如果之前保存过了单据则进行更新库存操作
				if(shiftId == "")
					saveShinftin(confirm);
				else
					updatestock();
				
			}
			*/
		}
		
		function saveShinftin(confirm){
		
			var url = ctx+"/updateShiftAction.action?";
			var pars=Form.serialize($(updateShiftAction));
			pars += "&isConfirm="+confirm;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:savecallback}
			);
			
		}
		
		function savecallback(originalRequest){
			var tminfo = originalRequest.responseText.split(',');
			shiftId  = tminfo[0];
			var suc = tminfo[1];
	
			if(suc == parseInt("8002")){
				window.location.href = ctx+"/findShiftinStockAction.action";
			}
		}
		
		
		/*更新入库*/
		function updatestock(){
			var url = ctx+"/updateTmShiftStockAction.action?";
			var pars = "id="+shiftId;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:updatecallback}
			);
		}
		
		function updatecallback(originalRequest){
			shiftId = "";
			window.location.href = ctx+"/findShiftinStockAction.action";
		}
		
		
		function openWin(){
			var props = "removeStockId,removeStockBill,removeDate,removeStoreHouseId";
			showCommonWin('findAllRemoveStockAction.action','移出列表',575,355,props,'tmDetTable');
		}
		
		function bodyOnLoad(){
			eval('refresh_tmDetTable()')
		}
		
	</script>
	<body onload="bodyOnLoad();" >
		<s:form action="updateShiftAction.action" >
			<s:hidden name="tmShiftinStock.id"  />
			<s:hidden name="tmShiftinStock.userId"  />
			<s:hidden name="tmShiftinStock.createDate"  />
			<s:hidden name="tmShiftinStock.removeStockId" id="rsId"  />
			<table>
				<tr>
					<td>移进单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" readonly="true" name="tmShiftinStock.shiftinBill" ></s:textfield> </td>
					<td>移进日期：</td>
					<td><s:textfield readonly="true" id="shiftinDate" name="tmShiftinStock.shiftinDate" >
							<s:param name="value"><s:date name="tmShiftinStock.shiftinDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="shiftinDate"  dataFmt="yyyy-MM-dd" />
					</td>
					<td>移进配件仓库：</td>
					<td><s:select id="shiftinStoreHouseId" name="tmShiftinStock.storeHoseId" list="#request.storeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
				</tr>
			</table>
		</s:form>
		<s:form action="findRemoveStockAction.action" >
			<s:hidden id="removeStockId" name="removeStockId"   ></s:hidden>
			<table>
				<tr>
					<td>移出单号：</td>
					<td><s:textfield id="removeStockBill" name="#request.removeStock.removeStockBill" onfocus="openWin();" ></s:textfield> </td>
					<td>移出日期：</td>
					<td><s:textfield id="removeDate" name="#request.removeStock.removeDate"  >
							<s:param name="value"><s:date name="#request.removeStock.removeDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar  for="removeDate" dataFmt="yyyy-MM-dd" ></e3c:calendar>
					</td>
					<td>移出仓库：</td>
					<td><s:select id="removeStoreHouseId" name="#request.removeStock.storeHoseId" list="#request.storeHouseList" emptyOption="true" listKey="id" listValue="houseName"/> </td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tmDetTable" uri="findRemoveStockAction.action" var="detVo"
			scope="request" items="detVoList" mode="ajax" caption="移进入库"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="40" property="unitName" title="单位" />
			<e3t:column property="quantity" title="移进数量" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="price" title="成本价小计" />
		</e3t:table>
		
	</body> 
</html>

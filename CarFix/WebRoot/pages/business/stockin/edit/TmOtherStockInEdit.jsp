<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改其它入库</title>
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
			var props = "customerId,customerCode,customerName&types=2,3";
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
			pConfig.form = "initEditStockInPageAction";
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
				var spanSqxj = document.getElementById("spanSqxj"+partinfoId);						//税前小计
				
				
				//数量合计
				totalNum = parseFloat(userQuantity.value) + totalNum;
				//税前合计
				totalprice = parseFloat(spanSqxj.innerHTML) + totalprice;
				
				
			
			}
			
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			
			
		}
		
		 var tbPartInfoObj = {};
		 var stockInId = "";							//入库单ID
		 
		/**计算单价，小计**/
		function jsRecord(stockInDetailId){
			var userQuantity = document.getElementById("userQuantity"+stockInDetailId);				//数量
			var userCostPrice = document.getElementById("userCostPrice"+stockInDetailId);			//税前单价
			var spanSqxj = document.getElementById("spanSqxj"+stockInDetailId);					//税前小计
			spanSqxj.innerHTML = Math.round(parseFloat(userQuantity.value)* parseFloat(userCostPrice.value)*100)/100;
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
		
		/**e3Table 增加行事件 **/
		function addRecord(){
			/**验证合法性**/
			if(tbPartInfoObj.partQuantity == undefined) return;
			if(parseFloat(tbPartInfoObj.partQuantity) <= 0  || parseFloat(tbPartInfoObj.price) <= 0){
				alert("数量，金额输入有误！");
				return;
			}
			for(var i=0; i<tmStockInDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockInDetailTableE3ExtStore.getAt(i);
				var e3partInfoId = record.get("partinfoId");
				if(e3partInfoId == tbPartInfoObj.tbPartInfoId){
					alert("已有配件，不能重复添加");
					return;
				}
			}
			/**验证合法性**/
			
			var idx = tmStockInDetailTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
				
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'userQuantity', mapping: 'userQuantity'},
			    {name: 'userCostPrice', mapping: 'userCostPrice'},
			    {name: 'sqxj', mapping: 'sqxj'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			    {name: 'no', mapping: 'no'}
			    
			    
			]);
			
			var myNewRecord = new TopicRecord({	
			    houseName: tbPartInfoObj.houseName,
			    partName: tbPartInfoObj.partName,
			    userQuantity: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"userQuantity"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.partQuantity+"\" style=width:80px  />",
			    userCostPrice: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"userCostPrice"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.price+"\" style=width:80px />",
			    sqxj: "<span id=\"spanSqxj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.sqxj+"</span>",
			    no : '&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>',
			    partinfoId: tbPartInfoObj.tbPartInfoId
			});
			
			tmStockInDetailTableE3ExtStore.insert(idx,myNewRecord);
			
			handlerE3Table();
		}
		
		/**选择配件**/
		function openPartWin(){
			var props = "";
			showCommonWin('findAllTbPartInfoAction.action','配件列表',730,450,props,'addCallBack');
		}
		
		
		 /**选择配件之后的回调处理**/
		 function addCallBack(){
		    
		    var houseNameTD = document.getElementById("houseNameTD");
		    var partNameTD = document.getElementById("partNameTD");
		    var unitNameTD = document.getElementById("unitNameTD");
		    var quantityTD = document.getElementById("quantityTD");
		    var costPriceTD = document.getElementById("costPriceTD");
		    var sqxjTD = document.getElementById("sqxjTD");
		    
		    
		    
		    houseNameTD.innerHTML = tbPartInfoObj.houseName;
		    partNameTD.innerHTML = tbPartInfoObj.partName;
		    unitNameTD.innerHTML = tbPartInfoObj.unitName;
		    quantityTD.innerHTML = "<input id=quanInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.partQuantity+" />";
		    costPriceTD.innerHTML = "<input id=pceInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.price+" />";
		    sqxjTD.innerHTML  =  "0.00";
		      
		 }
		 
		 /**计算全局配件对象中单价，小计**/
		 function objHanlderVal(){
		 	var sqxjTD = document.getElementById("sqxjTD");
		 	var quanInp =  document.getElementById("quanInp");
		 	var pceInp =  document.getElementById("pceInp");
		 	//税前小计
		 	var sqxj = Math.round((parseFloat(quanInp.value) * parseFloat(pceInp.value))*100)/100;
		 
		 	
		 	tbPartInfoObj.partQuantity = quanInp.value;
		 	tbPartInfoObj.price = pceInp.value;
			tbPartInfoObj.sqxj = sqxj;	 
		 	
		 	sqxjTD.innerHTML = sqxj;
		 }
		 
		 /** 修改配件入库**/
		 function updateTmStockInDet(confirm){
		 	if(tmStockInDetailTableE3ExtStore.getCount() == 0){
		 		Ext.MessageBox.alert('温馨提示：', '配件内容不能为空.', null);
		 		return;
		 	}
		 
		 	var str = "";
	 		for(var i=0; i<tmStockInDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockInDetailTableE3ExtStore.getAt(i);
				
				var partinfoId = record.get("partinfoId");
				var stockInDetailId = record.get("partinfoId");
				var userQuantity = document.getElementById("userQuantity"+stockInDetailId);					//数量
				var userCostPrice = document.getElementById("userCostPrice"+stockInDetailId);				//税前单价
				
				str += partinfoId + ":" + userQuantity.value + ":" + userCostPrice.value + ",";
			}
			var partCol = document.getElementById("partCol");
			var totalQuantity = document.getElementById("totalQuantity");
			var totalPrice = document.getElementById("totalPrice");
			partCol.value = str;
			totalQuantity.value = document.getElementById("totalnumspan").innerHTML;
			totalPrice.value = document.getElementById("totalpricespan").innerHTML;
			//alert(str);
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
		<s:form action="updateOtherStockInAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmStockIn.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmStockIn.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockIn.supplierId" ></s:hidden>
			
			
			<s:hidden name="tmStockIn.id" ></s:hidden>
			<s:hidden name="tmStockIn.userId" ></s:hidden>
			<s:hidden name="tmStockIn.createDate" ></s:hidden>
			<s:hidden name="tmStockIn.inType" ></s:hidden>
			<s:hidden name="tmStockIn.profitLossBill" ></s:hidden>
			<table>
				<tr>
					<td>入库单号：</td>
					<td><s:textfield cssStyle="color:#FF0000"  readonly="true" name="tmStockIn.stockInCode" /></td>
					<td>业务单据：</td>
					<td><s:textfield name="tmStockIn.businessCode" /></td>
					<td>到货日期：</td>
					<td><s:textfield id="arriveDate" name="tmStockIn.arriveDate" readonly="true" >
							<s:param name="value"><s:date name="tmStockIn.arriveDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="arriveDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
				<tr>
					<td>供应商号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" onfocus="openWin();" /><font color="red">*</font></td>
					<td>供应商名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName" /></td>
				</tr>
			</table>
		</s:form>
		<c:set var="rate" value="1.17" ></c:set>		 
		 <br>
		 <table id="mainTable"  >
			<tr>
				<th width="100" >仓库</th>
				<th width="120">配件名称</th>
				<th width="70">单位</th>
				<th width="80">数量</th>
				<th width="80">税前单价</th>
				<th width="80">税前小计</th>
				<th colspan="2" width="80">操作</th>
			</tr>
			<tr>
				<td id="houseNameTD"  ></td>
				<td id="partNameTD" ></td>
				<td id="unitNameTD"  ></td>
				<td id="quantityTD" ></td>
				<td id="costPriceTD"></td>
				<td id="sqxjTD"></td>
				
				<td><input type="button" value="选择配件" onclick="openPartWin();"  /></td>
				<td><input type="button" value="增加" onclick="addRecord();"  /></td>
			</tr>
		</table>
		 
		 <e3t:table id="tmStockInDetailTable" uri="initEditStockInPageAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="采购入库明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="userQuantity"  title="入库数量" >
				<input onchange="jsRecord('${detVo.partinfoId}')" id="userQuantity${detVo.partinfoId}" type="text" value="${detVo.quantity}" style="width:80px"  />
			</e3t:column>
			<e3t:column property="userCostPrice"  title="税前单价" >
				<input onchange="jsRecord('${detVo.partinfoId}')"  id="userCostPrice${detVo.partinfoId}" type="text" value="${detVo.costPrice}" style="width:80px"  />
			</e3t:column>
			<e3t:column property="sqxj"   title="税前小计" >
				<span id="spanSqxj${detVo.partinfoId}" >
					<fmt:formatNumber maxFractionDigits="2" pattern="#######"  value="${detVo.quantity * detVo.costPrice }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			
			
			<e3t:column property="no" title="操作" sortable="false">
				&nbsp;
  				<a href="#" onclick="delRecord();" >
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="houseId" hidden="true"  title="仓库ID" />
			<e3t:column property="partinfoId" hidden="true"  title="配件ID" />
			<e3t:column property="stockInDetailId" hidden="true" title="入库明细ID" />
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		 合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;税前合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;
		
	</body> 
	

</html>

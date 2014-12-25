<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改销售出库</title>
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
		var partIdArr = new Array();	
		
		var e3Index = 0; 
		
		
		function tmStockOutDetailTableConfigHandler(pConfig) {
			pConfig.tbar = [
			{	
				id : 'savdj',
				text : '保存',
				iconCls : 'addIcon',
				handler : function() {
					updateTmStockInDet();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockOutDetailTableE3ConfigHandler(pConfig) {
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
			for(var j=0 ; j<e3Index; j++){
				//每行的record对象
				//var record = tmStockOutDetailTableE3ExtStore.getAt(j);
				//var partinfoId = record.get("partinfoId");											//配件ID
				var partinfoId = document.getElementById("partinfoId"+j);					//数量
				if(partinfoId == null) continue;
				var userQuantity = document.getElementById("userQuantity"+j);				//数量
				var spanXsxj = document.getElementById("spanXsxj"+j);						//出库小计
				
				//alert( "**********"+userQuantity.value);
				//数量合计
				totalNum = parseFloat(userQuantity.value) + totalNum;
				var isfree = document.getElementById("isFree"+j);
				if(isfree.value == 1){
					//出库合计
					totalprice = parseFloat(spanXsxj.innerHTML) + totalprice;
				}
				
				partIdArr.push(partinfoId.value);
			
			}
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			
			
		}
		
		 var tbPartInfoObj = {};

		/**计算单价，小计**/
		function jsRecord(partinfoId){
			var userQuantity = document.getElementById("userQuantity"+partinfoId);					//数量
			var sellPrice = document.getElementById("sellPrice"+partinfoId);						//出库价
			var spanXsxj = document.getElementById("spanXsxj"+partinfoId);							//出库小计
			var spanCostPrice = document.getElementById("spanCostPrice"+partinfoId);				//成本价
			var spanCbxj = document.getElementById("spanCbxj"+partinfoId);							//成本小计
			
			spanXsxj.innerHTML =  Math.round(parseFloat(userQuantity.value)* parseFloat(sellPrice.value)*100)/100;
			spanCbxj.innerHTML =  Math.round(parseFloat(userQuantity.value)* parseFloat(spanCostPrice.innerHTML)*100)/100;
			
			handlerE3Table();
		}
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmStockOutDetailTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmStockOutDetailTableE3ExtStore.remove(selectedRow);
			
			handlerE3Table();
		}	
		
		/**e3Table 增加行事件 **/
		function addRecord(){
			
			var idx = tmStockOutDetailTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'userQuantity', mapping: 'userQuantity'},
			    {name: 'costPrice', mapping: 'costPrice'},
			    {name: 'cbxj', mapping: 'cbxj'},
			    {name: 'sellPrice', mapping: 'sellPrice'},
			    {name: 'xsxj', mapping: 'xsxj'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			    {name: 'freeName', mapping: 'freeName'},
			    {name: 'no', mapping: 'no'}
			    
			]);
			
			var disable = tbPartInfoObj.addType == "newAdd" ?false:true;
			var freeN = buildFreeFlagSelect("isFree"+e3Index,"handlerE3Table()",tbPartInfoObj.isFree,disable,"freeCollection");
			/*
			if(tbPartInfoObj.isFree == 1)
				freeN = "<input disabled=true type=checkbox name=freeCollection onclick=handlerE3Table() id=\"isFree"+e3Index+"\" checked=true />";
			else
				freeN = "<input disabled=true type=checkbox name=freeCollection onclick=handlerE3Table() id=\"isFree"+e3Index+"\"  />";
			*/
			var myNewRecord = new TopicRecord({	
			    houseName: tbPartInfoObj.houseName,
			    partName: tbPartInfoObj.partName,
			    userQuantity: "<input name=quantityCollection onchange=\"jsRecord("+e3Index+");\" id=\"userQuantity"+e3Index+"\" type=text value=\""+tbPartInfoObj.partQuantity+"\" style=width:80  />",
			    costPrice: "<span id=\"spanCostPrice"+e3Index+"\" >"+tbPartInfoObj.costPrice+"</span>",
			    cbxj: "<span id=\"spanCbxj"+e3Index+"\" >"+tbPartInfoObj.cbxj+"</span>",
			    sellPrice: "<input name=sellPriceCollection id=\"sellPrice"+e3Index+"\" type=text value=\""+tbPartInfoObj.price+"\" readonly=true style=width:80 />",
			    xsxj: "<span id=\"spanXsxj"+e3Index+"\" >"+tbPartInfoObj.xsxj+"</span><input type=hidden id=partinfoId"+e3Index+" name=partCollection value="+tbPartInfoObj.tbPartInfoId+" >",
			    freeName:freeN,
			    no :'&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>',
			    partinfoId: tbPartInfoObj.tbPartInfoId
			});
			
			tmStockOutDetailTableE3ExtStore.insert(idx,myNewRecord);
			
			e3Index++ ;
			handlerE3Table();
		}
		
		/**选择配件**/
		function openPartWin(){
			var props = "";
			showCommonWin('findAllTbPartInfoAction.action','配件列表',730,400,props,'addCallBack');
		}
		
		
		 /**选择配件之后的回调处理**/
		 function addCallBack(){
		    
		    var houseNameTD = document.getElementById("houseNameTD");
		    var partNameTD = document.getElementById("partNameTD");
		    var unitNameTD = document.getElementById("unitNameTD");
		    var quantityTD = document.getElementById("quantityTD");
		    var costPriceTD = document.getElementById("costPriceTD");
		    var cbxjTD = document.getElementById("cbxjTD");
		    var sellPriceTD = document.getElementById("sellPriceTD");
		    var xsxjTD = document.getElementById("xsxjTD");
		   
		   
		    
		    houseNameTD.innerHTML = tbPartInfoObj.houseName;
		    partNameTD.innerHTML = tbPartInfoObj.partName;
		    unitNameTD.innerHTML = tbPartInfoObj.unitName;
		    quantityTD.innerHTML = "<input id=quanInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.partQuantity+" />";
		    costPriceTD.innerHTML = tbPartInfoObj.price;
		    cbxjTD.innerHTML  =  "0.00";
		    sellPriceTD.innerHTML  =  "<input id=pceInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.sellPrice+" />";;
		    xsxjTD.innerHTML  = "0.00";
		      
		 }
		 
		 /**计算全局配件对象中单价，小计**/
		 function objHanlderVal(){
		 
		 	var costPriceTD = document.getElementById("costPriceTD");
		    var cbxjTD = document.getElementById("cbxjTD");
		    var sellPriceTD = document.getElementById("sellPriceTD");
		    var xsxjTD = document.getElementById("xsxjTD");
		 	var quanInp =  document.getElementById("quanInp");
		 	var pceInp =  document.getElementById("pceInp");
		 	//出库小计
		 	var  xsxj = Math.round((parseFloat(quanInp.value) * parseFloat(pceInp.value))*100)/100;
		 	//成本小计
		 	var cbxj = Math.round((parseFloat(quanInp.value) * parseFloat(costPriceTD.innerHTML))*100)/100;
		 	
		 	tbPartInfoObj.partQuantity = quanInp.value;
		 	tbPartInfoObj.price = pceInp.value;
		 	tbPartInfoObj.costPrice = parseFloat(costPriceTD.innerHTML);
			tbPartInfoObj.cbxj = cbxj;	 
			tbPartInfoObj.xsxj = xsxj;
		 	
		 	cbxjTD.innerHTML = cbxj;
		 	xsxjTD.innerHTML = xsxj;
		 }
		 
		 /** 修改配件入库**/
		 function updateTmStockInDet(){
		 	
		 	var flag = updateVaildate();
		 	if(!flag)return;
		 	
		 	var str = "";
	 		var quantityCollection = document.getElementsByName("quantityCollection");
			var sellPriceCollection = document.getElementsByName("sellPriceCollection");
			var partCollection = document.getElementsByName("partCollection");
			var freeCollection = document.getElementsByName("freeCollection");
			for(var i=0 ; i<quantityCollection.length; i++){
				var quantity = quantityCollection[i];
				var sellprice = sellPriceCollection[i];
				var partinfo = partCollection[i];
				var isFree = freeCollection[i];
				/*
				var freeVal;
				if(isFree.checked)
					freeVal = true;
				else
					freeVal = false;
					*/
				str += partinfo.value + ":" + quantity.value + ":" + sellprice.value + ":true:"+isFree.value+ ",";
			}
			
			var partCol = document.getElementById("partCol");
			var totalQuantity = document.getElementById("totalQuantity");
			var totalPrice = document.getElementById("totalPrice");
			partCol.value = str;
			totalQuantity.value = document.getElementById("totalnumspan").innerHTML;
			totalPrice.value = document.getElementById("totalpricespan").innerHTML;
			
			document.forms[0].submit();
			
		 }
		 
		 function partReturn(index,tbPartInfoId,houseName,partName,partQuantity,costPrice,price,isFree){
		 
		 	var newPartQuantity = parseFloat("-"+partQuantity);
		 	tbPartInfoObj.tbPartInfoId = tbPartInfoId;
		 	tbPartInfoObj.houseName = houseName;
		 	tbPartInfoObj.partName = partName;
		 	tbPartInfoObj.partQuantity = newPartQuantity;
		 	tbPartInfoObj.costPrice = costPrice;
		 	tbPartInfoObj.price = price;
		 	tbPartInfoObj.cbxj = formatFloat(costPrice*newPartQuantity,2)
		 	tbPartInfoObj.xsxj = formatFloat(price*newPartQuantity,2)
		 	tbPartInfoObj.isFree = isFree;
		 	tbPartInfoObj.addType = "return";
		 	var flag = validateIsReturn(tbPartInfoId);
		 	if(flag)
		 		addRecord();
		 }
		 
		 
		 function newPartAdd(){
		 	/**验证合法性**/
			if(tbPartInfoObj.partQuantity == undefined) return;
			if(parseFloat(tbPartInfoObj.partQuantity) <= 0  || parseFloat(tbPartInfoObj.price) <= 0){
				alert("数量，金额输入有误！");
				return;
			}
		 	/**验证合法性**/
		 	/*
		 	for(var i=0; i<tmStockOutDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockOutDetailTableE3ExtStore.getAt(i);
				var e3partInfoId = record.get("partinfoId");
				if(e3partInfoId == tbPartInfoObj.tbPartInfoId){
					alert("已有配件，不能重复添加");
					return;
				}
			}
			for(var k=0;k<partIdArr.length;k++){
				if(partIdArr[k] == tbPartInfoObj.tbPartInfoId ){
					Ext.MessageBox.alert('温馨提示：', '不能重复添加已有配件！', null);
					return;
				}
			}
			*/
			
			
			tbPartInfoObj.addType = "newAdd";
			addRecord();
		 }
		 
		 function validateIsReturn(partInfoId){
		 	var returnQuantity = document.getElementsByName("returnQuantity"+partInfoId);
		 	var result = 0;
		 	for(var i=0; i<returnQuantity.length; i++){
		 		result += parseFloat(returnQuantity[i].value);
		 	}
		 	if(parseFloat(result) <= 0 )
		 		return false;
		 		
		 	return true;
		 }
		 
		 function updateVaildate(){
		 	var old_partObjArray = new Array();
		 	
		 	
		 	var oldPartInfo = document.getElementsByName("oldPartInfo");
		 	for(var i=0; i<oldPartInfo.length; i++){
		 		var oldPartId = oldPartInfo[i].value.split(",")[0];
		 		var oldQuantity = oldPartInfo[i].value.split(",")[1];
		 		var isAdd = true;
		 		for(var j=0; j<old_partObjArray.length; j++){
		 			if(oldPartId == old_partObjArray[j].tbPartInfoId){
						old_partObjArray[j].partQuantity = parseFloat(oldQuantity) + parseFloat(old_partObjArray[j].partQuantity);
						isAdd = false;
						break;
					}
		 		}
					
				if(isAdd){
					var partObj = {}
					partObj.tbPartInfoId = oldPartId;
					partObj.partQuantity = oldQuantity;
					old_partObjArray.push(partObj);
				}
		 	}
		 	
		 	
		 	var quantityCollection = document.getElementsByName("quantityCollection");
			var partCollection = document.getElementsByName("partCollection");
			
			for(var i=0 ; i<quantityCollection.length; i++){
				var quantity = quantityCollection[i];
				var partinfo = partCollection[i];
				var isAdd = true;
				for(var j=0; j<old_partObjArray.length; j++){
		 			if(partinfo.value == old_partObjArray[j].tbPartInfoId){
						old_partObjArray[j].partQuantity = parseFloat(quantity.value) + parseFloat(old_partObjArray[j].partQuantity);
						isAdd = false;
						break;
					}
		 		}
					
				if(isAdd){
					var partObj = {}
					partObj.tbPartInfoId = partinfo.value;
					partObj.partQuantity = quantity.value;
					old_partObjArray.push(partObj);
				}
				
			}
			
			for(var i=0;i<old_partObjArray.length;i++){
				var obj = old_partObjArray[i]
				//alert(obj.tbPartInfoId + "**************" + obj.partQuantity);
				
				if(parseFloat(obj.partQuantity) < 0 ){
					Ext.MessageBox.alert('温馨提示：', '	您退的料超出已发数量请核对！', null);
					return false;
				}
				
			}
			
			return true;
		 
		 }
		 
		 function init(){
		 	e3Index = tmStockOutDetailTableE3ExtStore.getCount();
		 }
	</script>
	<body onload="init();handlerE3Table();" >
		<s:form action="updateConfirmSellAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmStockOut.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmStockOut.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockOut.customerBill" ></s:hidden>
			
			
			<s:hidden name="tmStockOut.id" ></s:hidden>
			<s:hidden name="tmStockOut.userId" ></s:hidden>
			<s:hidden name="tmStockOut.createDate" ></s:hidden>
			<s:hidden name="tmStockOut.outType" ></s:hidden>
			<table>
				<tr>
					<td>销售单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" name="tmStockOut.stockOutCode" disabled="true" /></td>
					<td>委托书号：</td>
					<td><s:textfield name="tmStockOut.trustBill" /></td>
					<td>销售日期：</td>
					<td><s:textfield id="stockOutDate" name="tmStockOut.stockOutDate" disabled="true" >
							<s:param name="value"><s:date name="tmStockOut.stockOutDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<font color="red">*</font>
						<e3c:calendar for="stockOutDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>销售类型：</td>
					<td><s:select disabled="true" name="tmStockOut.soleTypeId" list="#request.tsyList" emptyOption="false" listKey="id" listValue="soleName"/></td>
				</tr>
				<tr>
					<td>客户号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" disabled="true" /></td>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName" disabled="true" /></td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
		</s:form>
		 <br>
		 <table id="mainTable"  >
			<tr>
				<th width="100" >仓库</th>
				<th width="120">配件名称</th>
				<th width="70">单位</th>
				<th width="80">数量</th>
				<th width="80">成本价</th>
				<th width="80">成本小计</th>
				<th width="80">销售价</th>
				<th width="80">销售小计</th>
				<th colspan="2" width="80">操作</th>
			</tr>
			<tr>
				<td id="houseNameTD"  ></td>
				<td id="partNameTD" ></td>
				<td id="unitNameTD"  ></td>
				<td id="quantityTD" ></td>
				<td id="costPriceTD"></td>
				<td id="cbxjTD"></td>
				<td id="sellPriceTD"></td>
				<td id="xsxjTD"></td>
				
				<td><input type="button" value="选择配件" onclick="openPartWin();"  /></td>
				<td><input type="button" value="增加" onclick="newPartAdd();"  /></td>
			</tr>
		</table>
		 
		 <e3t:table id="tmStockOutDetailTable" uri="initEditSellPageAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="采购出库明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" >
			<e3t:column property="no" title="操作" sortable="false">
				&nbsp;&nbsp;
				<c:if test="${detVo.quantity > 0}">
					<a  href="#" onclick="partReturn('${vv.index}','${detVo.partinfoId}','${detVo.houseName}','${detVo.partName}','${detVo.quantity}','${detVo.costPrice}','${detVo.price}','${detVo.isFree}')" >
						<font color="blue">退货
					    </font>
					</a>
				</c:if>
			</e3t:column>
			<e3t:column property="freeName" title="免费标志" sortable="false">
			<!-- 
				<c:if test="${detVo.isFree == 1}"> 
					<input disabled="disabled" type="checkbox" checked="true" onclick="handlerE3Table()" id="isFree${vv.index}" value="${detVo.isFree }" />
				</c:if>
				<c:if test="${detVo.isFree == 0}"> 
					<input disabled="disabled"  type="checkbox" onclick="handlerE3Table()" id="isFree${vv.index}" value="${detVo.isFree }" />
				</c:if>
				 -->
				<select disabled="disabled" id="isFree${vv.index}"  >
					<s:iterator id="free" value="#request.freeFlagTypes">
						<c:if test="${ detVo.isFree == free.key }">
							<option selected="selected" value="${free.key}">
								${free.value }
							</option>
						</c:if>
						<c:if test="${ detVo.isFree != free.key }">
							<option value="${free.key}">
								${free.value }
							</option>
						</c:if>
					</s:iterator>
				</select>
			</e3t:column>
			
			
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="userQuantity"  title="出库数量" >
				<input type="hidden" name="oldPartInfo" value="${detVo.partinfoId},${detVo.quantity}" />
				<input readonly="true" name="returnQuantity${detVo.partinfoId}" onchange="jsRecord('${vv.index}')" id="userQuantity${vv.index}" type="text" value="${detVo.quantity}"  style="width:80px" />
			</e3t:column>
			<e3t:column property="costPrice"  title="成本价" >
				<span id="spanCostPrice${vv.index}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="########" value="${detVo.costPrice}"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="cbxj"  title="成本小计" >
				<span id="spanCbxj${vv.index}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="########"  value="${detVo.costPrice * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="sellPrice" title="销售价" >
				<input readonly="true" onchange="jsRecord('${vv.index}')" id="sellPrice${vv.index}" type="text" value="${detVo.price}" style="width:80px"  />
			</e3t:column>
			<e3t:column property="xsxj"  title="销售小计" >
				<span id="spanXsxj${vv.index}" >	
					<fmt:formatNumber  maxFractionDigits="2" pattern="########"  value="${detVo.price * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
		
			<e3t:column property="partinfoId"  hidden="true"  title="配件ID" >
				<input id="partinfoId${vv.index}" type="hidden" value="${detVo.partinfoId}" />
			</e3t:column>
			<e3t:column property="stockOutDetailId" hidden="true" title="入库明细ID" />
			
		
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		 合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;销售合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;
		
	</body> 
	

</html>

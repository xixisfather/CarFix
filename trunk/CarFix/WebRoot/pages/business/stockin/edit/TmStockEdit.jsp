<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改采购入库</title>
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
			var totalratepricespan = document.getElementById("totalratepricespan");
			
			var totalNum = 0;
			var totalprice = 0;
			var totalrate = 0;
			
			for(var j=0 ; j<tmStockInDetailTableE3ExtStore.getCount(); j++){
				//每行的record对象
				var record = tmStockInDetailTableE3ExtStore.getAt(j);
				var partinfoId = record.get("partinfoId");											//配件ID
				var userQuantity = document.getElementById("userQuantity"+partinfoId);				//数量
				var spanCostXj = document.getElementById("spanCostXj"+partinfoId);					//税前小计
				var spanRateXj = document.getElementById("spanRateXj"+partinfoId);					//税后小计
				
				
				//数量合计
				totalNum = parseFloat(userQuantity.value) + totalNum;
				//税前合计
				totalprice = parseFloat(spanCostXj.innerHTML) + totalprice;
				//税后合计
				totalrate =  parseFloat(spanRateXj.innerHTML) + totalrate
				
			
			}
			
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			totalratepricespan.innerHTML = Math.round( totalrate*100)/100;
			
			
		}
		
		 var tbPartInfoObj = {};
		 var stockInId = "";							//入库单ID
		 
		/**计算单价，小计**/
		function jsRecord(tbPartInfoId,type){
			var rate = document.getElementById("sl").value;											//税率
			var userQuantity = document.getElementById("userQuantity"+tbPartInfoId);				//数量
			var userCostPrice = document.getElementById("userCostPrice"+tbPartInfoId);				//税前单价
			var spanCostXj = document.getElementById("spanCostXj"+tbPartInfoId);					//税前小计
			var rateprice = document.getElementById("rateprice"+tbPartInfoId);						//税后单价
			var spanRateXj = document.getElementById("spanRateXj"+tbPartInfoId);					//税后小计
		
		
			
			if(type == 1){
				//税前录入，自动计算税后单价
				rateprice.value = formatFloat(parseFloat(userCostPrice.value)*parseFloat(rate),2);
			}
			if(type == 2){
				//税后录入，自动计算税前单价
				userCostPrice.value = formatFloat(parseFloat(rateprice.value)/parseFloat(rate),2);
			}
			
			spanCostXj.innerHTML = formatFloat(parseFloat(userQuantity.value)* parseFloat(userCostPrice.value),2);
			spanRateXj.innerHTML = formatFloat(parseFloat(userQuantity.value) * rateprice.value ,2);
			
			
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
			    {name: 'costXj', mapping: 'costXj'},
			    {name: 'ratePrice', mapping: 'ratePrice'},
			    {name: 'rateXj', mapping: 'rateXj'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			    {name: 'no', mapping: 'no'}
			    
			    
			]);
			
			var myNewRecord = new TopicRecord({	
			    houseName: tbPartInfoObj.houseName,
			    partName: tbPartInfoObj.partName,
			    userQuantity: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"userQuantity"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.partQuantity+"\"  />",
			    userCostPrice: "<input onchange=\"jsRecord(\'"+tbPartInfoObj.tbPartInfoId+"\',1)\" id=\"userCostPrice"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.price+"\"  />",
			    costXj: "<span id=\"spanCostXj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.sqxj+"</span>",
			    //ratePrice: "<span id=\"spanRatePrice"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.ratePrice+"</span>",
			    ratePrice:"<input type=text style=width:80 onchange=\"jsRecord(\'"+tbPartInfoObj.tbPartInfoId+"\',2)\" id=rateprice"+tbPartInfoObj.tbPartInfoId+"  value="+tbPartInfoObj.ratePrice+" />",
			    
			    rateXj: "<span id=\"spanRateXj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.shxj+"</span>",
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
		    var ratePriceTD = document.getElementById("ratePriceTD");
		    var shxjTD = document.getElementById("shxjTD");
		    
		    var rate = document.getElementById("sl");
		    var ratePrice = formatFloat(parseFloat(tbPartInfoObj.price) * rate.value ,2);
		    
		    houseNameTD.innerHTML = tbPartInfoObj.houseName;
		    partNameTD.innerHTML = tbPartInfoObj.partName;
		    unitNameTD.innerHTML = tbPartInfoObj.unitName;
		    quantityTD.innerHTML = "<input id=quanInp style=width:80 onchange=objHanlderVal(3); value="+tbPartInfoObj.partQuantity+" />";
		    costPriceTD.innerHTML = "<input id=pceInp style=width:80 onchange=objHanlderVal(1); value="+tbPartInfoObj.price+" />";
		    sqxjTD.innerHTML  =  "0.00";
		    ratePriceTD.innerHTML  =  "<input id=shInp type=text  onchange=objHanlderVal(2); style=width:80 value="+ratePrice+" />";
		    shxjTD.innerHTML  = "0.00";
		      
		 }
		 
		 /**计算全局配件对象中单价，小计**/
		 function objHanlderVal(type){
		 	 //税率
			var rate = document.getElementById("sl").value;
		 	var sqxjTD = document.getElementById("sqxjTD");
		    var ratePriceTD = document.getElementById("ratePriceTD");
		    var shxjTD = document.getElementById("shxjTD");
		 	var quanInp =  document.getElementById("quanInp");			//数量
		 	var pceInp =  document.getElementById("pceInp");			//税前单价
		 	var shInp = document.getElementById("shInp");				//税后单价
		 	
		 	if(type == 1){
				//税前录入，自动计算税后单价
				shInp.value = formatFloat(parseFloat(pceInp.value)*parseFloat(rate),2);
			}
			if(type == 2){
				//税后录入，自动计算税前单价
				pceInp.value = formatFloat(parseFloat(shInp.value)/parseFloat(rate),2);
			}
		 	
		 	
		 	//税前小计
		 	var sqxj = Math.round((parseFloat(quanInp.value) * parseFloat(pceInp.value))*100)/100;
		 	//税后小计
		 	var shxj = Math.round(parseFloat(shInp.value) * parseFloat(quanInp.value) * 100)/100;
		 	
		 	tbPartInfoObj.partQuantity = quanInp.value;
		 	tbPartInfoObj.price = pceInp.value;
			tbPartInfoObj.sqxj = sqxj;	 
			tbPartInfoObj.ratePrice = shInp.value;	
			tbPartInfoObj.shxj = shxj;
		 	
		 	sqxjTD.innerHTML = sqxj;
		 	shxjTD.innerHTML = shxj;
		 	
		 	
		 	
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
			
			
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
		
		 }
		 
		 function handlerPrice(tbPartInfoId,type){
		 	var rate = document.getElementById("sl").value;
			//税前单价
			var costprice = document.getElementById("userCostPrice"+tbPartInfoId);
			//税后单价
			var rateprice = document.getElementById("rateprice"+tbPartInfoId);
			
			if(type == 1){
				//税前录入，自动计算税后单价
				rateprice.value = formatFloat(parseFloat(costprice.value)*parseFloat(rate),2);
			}
			if(type == 2){
				//税后录入，自动计算税前单价
				costprice.value = formatFloat(parseFloat(rateprice.value)/parseFloat(rate),2);
			}
		}
	</script>
	<body onload="handlerE3Table();" >
		<s:form action="updateStockInAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmStockIn.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmStockIn.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockIn.supplierId" ></s:hidden>
			
			
			<s:hidden name="tmStockIn.id" ></s:hidden>
			<s:hidden name="tmStockIn.userId" ></s:hidden>
			<s:hidden name="tmStockIn.createDate" ></s:hidden>
			<s:hidden name="tmStockIn.inType" ></s:hidden>
			<s:hidden name="tmStockIn.oucherNo" ></s:hidden>
			<s:hidden name="tmStockIn.businessCode" ></s:hidden>
			<s:hidden name="tmStockIn.profitLossBill" ></s:hidden>
			<table>
				<tr>
					<td>采购单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" readonly="true" name="tmStockIn.stockInCode" /></td>
					<td>订货单号：</td>
					<td><s:textfield name="tmStockIn.indent" /></td>
					<td>到货日期：</td>
					<td><s:textfield id="arriveDate" name="tmStockIn.arriveDate" >
							<s:param name="value"><s:date name="tmStockIn.arriveDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="arriveDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>入库凭证：</td>
					<td><s:textfield name="tmStockIn.oucherCode" /></td>
					
				</tr>
				<tr>
					<td>供应商号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" onfocus="openWin();" /><font color="red">*</font></td>
					<td>供应商名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName"  /></td>
					<td>税率：</td>
					<td>
						<select id="sl" >
						 	<s:iterator value="#request.rates" id="r" >
							 	<s:if test="#r.paramvalue==0.17"><option selected="selected" value="${r.paramvalue+1 }"  >${r.paramvalue }</option></s:if>
						 		<s:if test="#r.paramvalue!=0.17"><option value="${r.paramvalue+1 }" >${r.paramvalue }</option></s:if>
						 	</s:iterator>
					 	</select>
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
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
				<th width="80">税后单价</th>
				<th width="80">税后小计</th>
				<th colspan="2" width="80">操作</th>
			</tr>
			<tr>
				<td id="houseNameTD"  ></td>
				<td id="partNameTD" ></td>
				<td id="unitNameTD"  ></td>
				<td id="quantityTD" ></td>
				<td id="costPriceTD"></td>
				<td id="sqxjTD"></td>
				<td id="ratePriceTD"></td>
				<td id="shxjTD"></td>
				
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
				<input onchange="jsRecord('${detVo.partinfoId}')" id="userQuantity${detVo.partinfoId}" type="text" value="${detVo.quantity}" style="width:80" />
			</e3t:column>
			<e3t:column property="userCostPrice"  title="税前单价" >
				<input onchange="jsRecord('${detVo.partinfoId}',1)" id="userCostPrice${detVo.partinfoId}" type="text" value="${detVo.costPrice}" style="width:80" />
			</e3t:column>
			<e3t:column property="costXj"   title="税前小计" >
				<span id="spanCostXj${detVo.partinfoId}" >
					<fmt:formatNumber maxFractionDigits="2" pattern="#######"  value="${detVo.quantity * detVo.costPrice }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="ratePrice"  title="税后单价" >
				<fmt:formatNumber maxFractionDigits="2"  var="ratePrice" pattern="#######" value="${1.17 * detVo.costPrice }"  />	
				<input type="text" style="width:80" onchange="jsRecord('${detVo.partinfoId}',2)" id="rateprice${detVo.partinfoId}"  value="${ratePrice}" />
			</e3t:column>
			<e3t:column property="rateXj"  title="税后小计" >
				<span id="spanRateXj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="#######" value="${detVo.quantity * (1.17 * detVo.costPrice) }"  ></fmt:formatNumber>
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
		 合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;税前合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;税后合计： <SPAN id="totalratepricespan" ></SPAN>
		
	</body> 
	

</html>

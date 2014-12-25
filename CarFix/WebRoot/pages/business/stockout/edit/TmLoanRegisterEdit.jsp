<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改借出登记</title>
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
		var initPageAction = "${initPageAction}";		//出入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		
		
	
		function openWin(){
			var props = "customerId,customerCode,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
		
				
		function tmLoanRegDetailTableConfigHandler(pConfig) {
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
		
		function tmLoanRegDetailTableE3ConfigHandler(pConfig) {
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
			
			for(var j=0 ; j<tmLoanRegDetailTableE3ExtStore.getCount(); j++){
				//每行的record对象
				var record = tmLoanRegDetailTableE3ExtStore.getAt(j);
				var partinfoId = record.get("partinfoId");											//配件ID
				var loanQuantity = document.getElementById("loanQuantity"+partinfoId);				//数量
				var spanpriceXj = document.getElementById("spanpriceXj"+partinfoId);					//出货小计
				
				
				//数量合计
				totalNum = parseFloat(loanQuantity.value) + totalNum;
				//税前合计
				totalprice = parseFloat(spanpriceXj.innerHTML) + totalprice;
				
				
			
			}
			
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			
		}
		
		 var tbPartInfoObj = {};
		 var stockInId = "";							//入库单ID
		 
		/**计算单价，小计**/
		function jsRecord(stockInDetailId){
			var loanQuantity = document.getElementById("loanQuantity"+stockInDetailId);				//数量
			var loanPrice = document.getElementById("loanPrice"+stockInDetailId);			//出货单价
			var spanpriceXj = document.getElementById("spanpriceXj"+stockInDetailId);			//出货小计
		
			spanpriceXj.innerHTML = Math.round(parseFloat(loanQuantity.value)* parseFloat(loanPrice.value)*100)/100;
			
			handlerE3Table();
		}
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmLoanRegDetailTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmLoanRegDetailTableE3ExtStore.remove(selectedRow);
			
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
			for(var i=0; i<tmLoanRegDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmLoanRegDetailTableE3ExtStore.getAt(i);
				var e3partInfoId = record.get("partinfoId");
				if(e3partInfoId == tbPartInfoObj.tbPartInfoId){
					alert("已有配件，不能重复添加");
					return;
				}
			}
			/**验证合法性**/
			
			var idx = tmLoanRegDetailTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
				
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'loanQuantity', mapping: 'loanQuantity'},
			    {name: 'oldCostPrice', mapping: 'oldCostPrice'},
			    {name: 'loanPrice', mapping: 'loanPrice'},
			    {name: 'priceXj', mapping: 'priceXj'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			    {name: 'no', mapping: 'no'}
			    
			    
			]);
			
			var myNewRecord = new TopicRecord({	
			    houseName: tbPartInfoObj.houseName,
			    partName: tbPartInfoObj.partName,
			    loanQuantity: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"loanQuantity"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.partQuantity+"\" style=width:80px />",
			    oldCostPrice : tbPartInfoObj.costPrice,
			    loanPrice: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"loanPrice"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.price+"\" style=width:80px />",
			    priceXj: "<span id=\"spanpriceXj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.xj+"</span>",
			    no : '&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>',
			    partinfoId: tbPartInfoObj.tbPartInfoId
			});
			
			tmLoanRegDetailTableE3ExtStore.insert(idx,myNewRecord);
			
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
		    var ofPriceTD = document.getElementById("ofPriceTD");
		    var xjTD = document.getElementById("xjTD");
		    
		    
		    houseNameTD.innerHTML = tbPartInfoObj.houseName;
		    partNameTD.innerHTML = tbPartInfoObj.partName;
		    unitNameTD.innerHTML = tbPartInfoObj.unitName;
		    quantityTD.innerHTML = "<input id=quanInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.partQuantity+" />";
		    costPriceTD.innerHTML = tbPartInfoObj.price;
		    ofPriceTD.innerHTML  =  "<input id=pceInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.price+" />"
		    xjTD.innerHTML  = "0.00";
		    
		    //新增成本价属性
		    tbPartInfoObj.costPrice = tbPartInfoObj.price;
		      
		 }
		 
		 /**计算全局配件对象中单价，小计**/
		 function objHanlderVal(){
		    var xjTD = document.getElementById("xjTD");
		    
		 	var quanInp =  document.getElementById("quanInp");
		 	var pceInp =  document.getElementById("pceInp");
		 	//出货小计
		 	var xj = Math.round((parseFloat(quanInp.value) * parseFloat(pceInp.value))*100)/100;
		 	
		 	
		 	tbPartInfoObj.partQuantity = quanInp.value;
		 	tbPartInfoObj.price = pceInp.value;
			tbPartInfoObj.xj = xj;	 
	
		 	
		 	xjTD.innerHTML = xj;
		 }
		 
		 /** 修改配件入库**/
		 function updateTmStockInDet(confirm){
		 	if(tmLoanRegDetailTableE3ExtStore.getCount() == 0){
		 		Ext.MessageBox.alert('温馨提示：', '配件内容不能为空.', null);
		 		return;
		 	}
		 	var str = "";
	 		for(var i=0; i<tmLoanRegDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmLoanRegDetailTableE3ExtStore.getAt(i);
				
				var partinfoId = record.get("partinfoId");
				var oldCostPrice = record.get("oldCostPrice");											//当时成本价
				var loanQuantity = document.getElementById("loanQuantity"+partinfoId);			//数量
				var loanPrice = document.getElementById("loanPrice"+partinfoId);				//税前单价
				
				str += partinfoId + ":" + loanQuantity.value + ":" + oldCostPrice +":" + loanPrice.value + ",";
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
				//单据入库 -- 如果之前保存过了单据则出行更新库存操作
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
		<s:form action="updateLoanRegisterAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmLoanRegister.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmLoanRegister.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmLoanRegister.customerId" ></s:hidden>
			
			<s:hidden name="tmLoanRegister.id" ></s:hidden>
			<s:hidden name="tmLoanRegister.userId" ></s:hidden>
			<s:hidden name="tmLoanRegister.createDate" ></s:hidden>
			<s:hidden name="tmLoanRegister.isReturn" ></s:hidden>
			<table>
				<tr>
					<td>借出单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" readonly="true" name="tmLoanRegister.loanBill" />
					<td>借出日期：</td>
					<td><s:textfield readonly="true" id="loanDate" name="tmLoanRegister.loanDate" >
							<s:param name="value"><s:date name="tmLoanRegister.loanDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="loanDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
				<tr>
					<td>客户号：</td>
					<td><s:textfield id="customerCode" onfocus="openWin();" name="#request.customer.customerCode" /><font color="red">*</font></td>
					<td>客户名称：</td>
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
				<th width="80">成本价</th>
				<th width="80">借出价</th>
				<th width="80">借出小计</th>
				<th colspan="2" width="80">操作</th>
			</tr>
			<tr>
				<td id="houseNameTD"  ></td>
				<td id="partNameTD" ></td>
				<td id="unitNameTD"  ></td>
				<td id="quantityTD" ></td>
				<td id="costPriceTD"></td>
				<td id="ofPriceTD"></td>
				<td id="xjTD"></td>
				
				<td><input type="button" value="选择配件" onclick="openPartWin();"  /></td>
				<td><input type="button" value="增加" onclick="addRecord();"  /></td>
			</tr>
		</table>
		 
		 <e3t:table id="tmLoanRegDetailTable" uri="initEditLoanRegisterPageAction.action" var="detVo"
			scope="request" items="registerDetailVos" mode="ajax" caption="借出登记明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="loanQuantity"  title="借出数量" >
				<input onchange="jsRecord('${detVo.partinfoId}')" id="loanQuantity${detVo.partinfoId}" type="text" value="${detVo.loanQuantity}" style="width:80px" />
			</e3t:column>
			<e3t:column property="loanPrice"  title="借出单价" >
				<input onchange="jsRecord('${detVo.partinfoId}')"  id="loanPrice${detVo.partinfoId}" type="text" value="${detVo.loanPrice}" style="width:80px"  />
			</e3t:column>
			<e3t:column property="priceXj"   title="借出小计" >
				<span id="spanpriceXj${detVo.partinfoId}" >
					<fmt:formatNumber maxFractionDigits="2"  pattern="#######" value="${detVo.loanQuantity * detVo.loanPrice }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			
			<e3t:column property="no" title="操作" sortable="false">
				&nbsp;
  				<a href="#" onclick="delRecord();" >
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			
			<e3t:column property="oldCostPrice" hidden="true"  title="当时成本价" />
			<e3t:column property="partinfoId" hidden="true"  title="配件ID" />
			<e3t:column property="lianceRegDtlId" hidden="true" title="入库明细ID" />
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		 &nbsp;合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;成本合计： <SPAN id="totalpricespan" ></SPAN>
		
	</body> 
	

</html>

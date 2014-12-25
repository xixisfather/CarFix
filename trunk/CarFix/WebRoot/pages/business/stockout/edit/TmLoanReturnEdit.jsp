<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改借出归还登记</title>
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
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,'tmLoanRegDetailTable');
		}
		
		function openLoanRegWin(){
			document.getElementById("cust").value = "";
			document.getElementById("customerName").value = "";
			var props = "loan,loanBill,null";
			showCommonWin('findAllTmLoanRegisterAction.action','借出列表',575,355,props,'tmLoanRegDetailTable');
		}
				
		function tmLoanReturnDetTableConfigHandler(pConfig) {
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
		
		function tmLoanReturnDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "initEditStockInPageAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		/****  借出明细 e3Table ****/
		function tmLoanRegDetailTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmLoanRegDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findLoanRegDetailAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function tmLoanRegDetailTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//处理数据
					rowHandler(record);
				});
		
		}
		
		function rowHandler(record){
			/*借出值*/
			var e3loanBill = record.get("loanBill");
			var e3loanDate = record.get("loanDate");
			var e3houseName = record.get("houseName");
			var e3partCode = record.get("partCode");
			var e3partName = record.get("partName");
			var e3unitName = record.get("unitName");
			var e3loanQuantity = record.get("loanQuantity");				//借出数量
			var e3returnQuantity = record.get("returnQuantity");			//以还数量
			var e3nowCosePrice = record.get("nowCosePrice");				//目前成本价
			var e3loanRegDtlId = record.get("loanRegDtlId");				//借出详细ID
			var e3partinfoId = record.get("partinfoId");					//配件ID
			/*借出值*/
			
			/*验证是否能添加*/
			for(var i=0; i<tmLoanReturnDetTableE3ExtStore.getCount(); i++){
				var record = tmLoanReturnDetTableE3ExtStore.getAt(i);
				var registerDetId = record.get("registerDetId");
				if(e3loanRegDtlId == registerDetId){
					alert("不能重复添加!")
					return ;
				}
			}
			/*验证是否能添加*/
		
			var idx = tmLoanReturnDetTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
				
			    {name: 'loanRegBill', mapping: 'loanRegBill'},
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partCode', mapping: 'partCode'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'unitName', mapping: 'unitName'},
			    {name: 'returnQuantity', mapping: 'returnQuantity'},			//以还数量
			    {name: 'lackReturnQuantity', mapping: 'lackReturnQuantity'},	//未还数量
			    {name: 'currQuantity', mapping: 'currQuantity'},				//本次归还
			    {name: 'costPrice', mapping: 'costPrice'},						//成本价
			    {name: 'registerDetId', mapping: 'registerDetId'},
			    {name: 'partInfoId', mapping: 'partInfoId'},
			    {name: 'no', mapping: 'no'}
			    
			    
			]);
			
			var currQuantity = Math.round((parseFloat(e3loanQuantity) - parseFloat(e3returnQuantity))*100)/100;	//本次归还
			
			var myNewRecord = new TopicRecord({	
				loanRegBill: e3loanBill,
			    houseName: e3houseName,
			    partCode: e3partCode,
			    partName: e3partName,
			    unitName: e3unitName,
			    returnQuantity: e3returnQuantity,
			    lackReturnQuantity: currQuantity,
			    currQuantity: "<input style=width:60px onchange=\"isQan("+currQuantity+",this)\" id=currQuantity"+e3partinfoId+" value="+currQuantity+" />",
			    costPrice: e3nowCosePrice,
			    registerDetId: e3loanRegDtlId,
			    partInfoId:	e3partinfoId,
			    no: '&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>'
			});
			
			tmLoanReturnDetTableE3ExtStore.insert(idx,myNewRecord);
			
		}
		/****  借出明细 e3Table ****/
		
		
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmLoanReturnDetTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmLoanReturnDetTableE3ExtStore.remove(selectedRow);
		}
		
		/*验证数量，归还数量不能大于借出数量*/
		function isQan(crQan,currQan){
			if(parseFloat(currQan.value)<= 0 || parseFloat(crQan)< parseFloat(currQan.value)){
				currQan.value = crQan;
				currQan.focus();
				Ext.MessageBox.alert('温馨提示：', '数量输了有误！', null);
			}
		}
		
		function updateEntity(confirm){
			var param = "";
		
			for(var i=0; i<tmLoanReturnDetTableE3ExtStore.getCount(); i++){
				var record = tmLoanReturnDetTableE3ExtStore.getAt(i);
					
				var e3partInfoId = record.get("partInfoId");										//配件ID
				
				var e3registerDetId = record.get("registerDetId");									//借出登记明细ID
				var e3loanBill	= record.get("loanRegBill");										//借出登记单号
				var e3lackReturnQuantity = record.get("lackReturnQuantity");						//未还数量
				var currQuantity = document.getElementById("currQuantity"+e3partInfoId);			//本次归还		
				var e3costPrice = record.get("costPrice");											//归还时的成本价
				
				
				param += e3registerDetId+":"+e3loanBill+":"+e3lackReturnQuantity+":"+currQuantity.value+":"+e3costPrice+",";
			}
			
			var partCol = document.getElementById("partCol");
			partCol.value = param;
			
			document.forms["updateLoanReturnDetailAction"].action += "?isConfirm="+confirm;
			document.forms["updateLoanReturnDetailAction"].submit();

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
		<s:form action="findLoanRegDetailAction.action">
			<s:hidden name="customerId" id="cust" ></s:hidden>
			<s:hidden name="loanRegId" id="loan" ></s:hidden>
			<table>
				<tr>
					<td>供应商号：</td>
					<td><s:textfield id="customerName" onfocus="openCustomerWin();" ></s:textfield></td>
					<td>借出单号：</td>
					<td><s:textfield id="loanBill" onfocus="openLoanRegWin();"  ></s:textfield>	</td>
				</tr>
				
			</table>
		</s:form>
		
		<s:form action="updateLoanReturnDetailAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmLoanReturn.userId" ></s:hidden>
			<s:hidden name="tmLoanReturn.createDate" ></s:hidden>
			<s:hidden name="tmLoanReturn.id" ></s:hidden>
			<table>
				<tr>
					<td>借出归还单号：<br></td>
					<td><s:textfield cssStyle="color:#FF0000" readonly="true" name="tmLoanReturn.loanReturnBill" ></s:textfield></td>
					<td>归还日期：<br></td>
					<td><s:textfield readonly="true" id="returnDate" name="tmLoanReturn.returnDate"  >
							<s:param name="value"><s:date name="tmLoanReturn.returnDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="returnDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
			
			
		</s:form>
		
		<e3t:table id="tmLoanRegDetailTable" uri="findLoanRegDetailAction.action" var="collectionVo"
			scope="request" items="collctionList" mode="ajax"  caption="借出明细"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="loanBill" title="借出单号" />
			<e3t:column property="loanDate"  title="借出日期" >
				<fmt:formatDate value="${collectionVo.loanDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="60" property="unitName" title="单位" />
			<e3t:column width="60" property="loanQuantity" title="借出数量" />
			<e3t:column width="60" property="returnQuantity" title="以还数量" />
			<e3t:column width="60" property="loanPrice" title="借出价格" />
			<e3t:column property="oldCostPrice" title="当时成本价" />
			
			<!-- 隐藏域 -->
			<e3t:column property="loanRegId" hidden="true"  title="借出登记ID" />
			<e3t:column property="loanRegDtlId" hidden="true" title="借出登记详细ID" />
			<e3t:column property="nowCosePrice" hidden="true"  title="现成本价" />
			
			<!-- 隐藏域 -->
		</e3t:table>
	
	
	
		 
		 <e3t:table id="tmLoanReturnDetTable" uri="initEditLoanReturnPageAction.action" var="detVo"
			scope="request" items="returnDetails" mode="ajax" caption="借出归还明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="810"
			height="320" >
			<e3t:column property="loanRegBill" title="借出单号" />
			<e3t:column property="houseName" beanProperty="tmLoanRegisterDetail.tbPartInfo.tmStoreHouse.houseName" title="仓库" />
			<e3t:column width="60" property="partCode" beanProperty="tmLoanRegisterDetail.tbPartInfo.partCode" title="配件代码" />
			<e3t:column property="partName" beanProperty="tmLoanRegisterDetail.tbPartInfo.partName" title="配件名称" />
			<e3t:column width="60" property="unitName" beanProperty="tmLoanRegisterDetail.tbPartInfo.tmUnit.unitName" title="单位" />
			<e3t:column width="60" property="returnQuantity" beanProperty="tmLoanRegisterDetail.returnQuantity" title="以还数量" />
			<e3t:column width="60" property="lackReturnQuantity" title="未还数量" />
			<e3t:column width="70" property="currQuantity" title="本次归还" >
				<input style="width:60px" onchange="isQan('${detVo.lackReturnQuantity}',this)" id="currQuantity${detVo.tmLoanRegisterDetail.tbPartInfo.id}" value="${detVo.returnQuantity}" />
			</e3t:column>
			<e3t:column width="60" property="costPrice" title="成本价" />
			
			<e3t:column property="partInfoId" beanProperty="tmLoanRegisterDetail.tbPartInfo.id" hidden="true" title="配件ID" />
			<e3t:column property="registerDetId" hidden="true" beanProperty="tmLoanRegisterDetail.id" title="借出明细ID" />
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

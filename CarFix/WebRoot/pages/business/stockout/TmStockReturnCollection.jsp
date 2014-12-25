<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购退货</title>
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
	
		Ext.onReady(createGrid);					//初始化操作
	
		function dataHandler(record){
			/*验证*/
			var flag = hasAdd(record.get("partinfoId"));
			
			if(!flag) return ;
			/*验证*/
			
			/* 获取单元格值 */ 
			var e3stockInId = record.get("stockInId");					// 采购入库ID
			var e3houseName = record.get("houseName");					// 仓库
			var e3partCode = record.get("partCode");					// 配件代码
			var e3partName = record.get("partName");					// 借配件名称
			var e3unitName = record.get("unitName");					// 单位
			var e3quantity = record.get("quantity");					// 入库数量
			var e3costPrice = record.get("costPrice");					// 成本价
			var e3stockInDetailId = record.get("stockInDetailId");		// 采购入库明细ID
			var e3partinfoId = record.get("partinfoId");				// 配件ID
			//update by baijx
			var e3hasQuantity = record.get("hasQuantity");				// 已退数量
			
			var outArray = new Array();
			outArray.push(e3stockInId);				//采购入库ID
			outArray.push(e3houseName);				//仓库
			outArray.push(e3partCode);				//配件代码
			outArray.push(e3partName);				//配件名称
			outArray.push(e3unitName);				//单位
			outArray.push(e3quantity);				//入库数量
			outArray.push(e3costPrice);				//成本价
			outArray.push(e3stockInDetailId);		//采购入库明细ID
			outArray.push(e3partinfoId);			//配件ID
			outArray.push(e3hasQuantity);			//已退数量
			partInfoArray.push(outArray);
			
			
			
			
			store.loadData(partInfoArray);
			
		}
	
	
		function tmStockInDetailTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				id : 'ain',
				text : '单项移入',
				iconCls : 'addIcon',
				handler : function() {
					//得到当前选择行
					var selectedRow = tmStockInDetailTableE3ExtGrid.getSelectionModel().getSelected();
					//得到出库明细	单号的值
					var stockInDetailId = selectedRow.get("stockInDetailId");
					//找到该行的索引
					var num = tmStockInDetailTableE3ExtStore.find('stockInDetailId',stockInDetailId);
					//找到record对象
					var record = tmStockInDetailTableE3ExtStore.getAt(num);
					//处理grid数据
					dataHandler(record);
				}
			}, '', '-', '', {
				id : 'allin',
				text : '全部移入',
				iconCls : 'addIcon',
				handler : function() {
					
					for(var j=0 ; j<tmStockInDetailTableE3ExtStore.getCount(); j++){
						//每行的record对象
						var record = tmStockInDetailTableE3ExtStore.getAt(j);
						//处理grid数据
						dataHandler(record);
					}
					
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockInDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findStockAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmStockInDetailTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//处理数据
					dataHandler(record);
				
				});
		
		}
		
		/*判断某行借出单是否能移入*/
		function hasAdd(partinfoId){
			for(var i=0; i<partInfoArray.length; i++){
				var currPartInfoId = partInfoArray[i][partInfoArray[i].length-1];
				if(currPartInfoId == partinfoId)
					return false;
			}
			
			return true;
		}
		
		
		
		//创建dataGrid组件
		function createGrid(){
			document.getElementById("partinfocollectionDiv").innerHTML = "";
			// 创建dataGrid存储箱
			store = new Ext.data.SimpleStore({
		        fields: [
		           {name: 'stockInId', type: 'string'},
		           {name: 'houseName', type: 'string'},
		           {name: 'partCode', type: 'string'},
		           {name: 'partName', type: 'string'},
		           {name: 'unitName', type: 'string'},
		           {name: 'quantity', type: 'float'},
		           {name: 'costPrice', type: 'float'},
		           {name: 'stockInDetailId', type: 'string'},
		           {name: 'partinfoId', type: 'string'},
		            {name: 'hasQuantity', type: 'float'}
		        ]
		    });
		    //装载数据
		    store.loadData(partInfoArray);
		
		   	// 创建dataGrid
		    outGrid = new Ext.grid.GridPanel({
		        store: store,
		        columns: [
		        	{id:'id',header: "id", sortable: true, hidden:true,dataIndex: 'id'},
		            {header: "仓库", width: 100, sortable: true, dataIndex:'houseName'},
		            {header: "配件代码", width: 100, sortable: true, dataIndex:'partCode'},
		            {header: "配件名称", width: 100, sortable: true, dataIndex:'partName'},
		            {header: "单位", width: 50, sortable: true, dataIndex:'unitName'},
		            {
						header : "退货数量",
						width : 100,
						dataIndex : 'quantity',
						align : 'center',
						renderer : function(value, cellmeta, record, rowIndex, columnIndex,st) {
									var crQuantity = record.get("hasQuantity");			// 以还数量
									var partinfoId = record.get("partinfoId");			// 采购数量
									var quantity =  record.get("quantity");			// 采购数量
									if(crQuantity == null || crQuantity == ""){
										crQuantity = 0;
									}
							
								return "<input style=width:50; onchange=\"isQan("+crQuantity+", "+quantity+",this)\" id=quantity"+partinfoId+" type=text value='"+value+"' />" 
								}
					},
		            {header: "现成本价", width: 100, sortable: true, dataIndex:'costPrice'},
		            {header: "采购入库明细ID", hidden:true,sortable: true, dataIndex:'stockInDetailId'},
		            {header: "配件ID", hidden:true,sortable: true, dataIndex:'partinfoId'}
		            
		        ],
		        stripeRows: true,
		        autoExpandColumn: 'id',
		        height:350,
		        width:700,
		        title:'退货列表',
		        tbar : [
					{	
						id :'aRem',
						text : "单项移出",
						iconCls : 'deleteIcon',
						handler : function() {
							/*当前选择行，没有选择则不处理*/
							var selectedRow = outGrid.getSelectionModel().getSelected();
							if (selectedRow == undefined )	return ;
							/*移除行*/
							store.remove(selectedRow);
							/*重载数据*/
							var stockInDetailId = selectedRow.get("stockInDetailId"); 
							var ttArr = new Array();
							for(var i=0; i<partInfoArray.length; i++){
								if(stockInDetailId == partInfoArray[i][0]) continue;
								ttArr.push(partInfoArray[i])
							}
							
							partInfoArray = ttArr;
							
						}
					},'', '-', '',{	
						id :'allRem',
						text : "全部移出",
						iconCls : 'deleteIcon',
						handler : function() {
							/*移出全部行*/
							store.removeAll();
							/*重载数据*/
							partInfoArray = new Array();
						}
					},'', '-', '',{	
						id:'savdj',
						text : "保存单据",
						iconCls : 'addIcon',
						handler : function() {
							addPartCollection("8000");
						}
					},'', '-', '',{	
						text : "确认出库",
						iconCls : 'addIcon',
						handler : function() {
							addPartCollection("8002");
						}
					}]
		    });
		    //显示dataGrid
		    outGrid.render('partinfocollectionDiv');
		}
		
		/*验证数量，退货数量不能大于采购数量*/
		function isQan(crQan,stockquantity,currQan){
			var hasquantity = parseFloat(crQan);			 //已还数量
			var newquantity = parseFloat(stockquantity);	 //现有数量
			var returnquantity = currQan.value;	 			//本次归还数量
			
			if(returnquantity > (newquantity - hasquantity)){
				currQan.focus();
				Ext.MessageBox.alert('温馨提示：', '数量输了有误！', null);
			}
		}
		
		//添加入库单
		function addPartCollection(confirm){
			
			var partCol = "";
			var totalNum = 0;
			var totalprice = 0;
			
			for(var j=0 ; j<store.getCount(); j++){
				//每行的record对象
				var record = store.getAt(j);
				var partinfoId = record.get("partinfoId");										//配件ID
				var stockInId = record.get("stockInId");										// 采购入库ID
				var quantity =  document.getElementById("quantity"+partinfoId).value;			// 退货数量
				var costPrice = record.get("costPrice");										// 成本价
				var stockInDetailId = record.get("stockInDetailId");							//采购入库详细ID
				var str = partinfoId+":"+quantity+":"+costPrice+":"+stockInDetailId;
				partCol += str + ",";
				
				totalNum += parseFloat(quantity);
				totalprice += Math.round( parseFloat(quantity) * parseFloat(costPrice) *100)/100 ;
			}
			
			document.getElementById("totalQuantity").value = totalNum;
			document.getElementById("totalPrice").value = totalprice;
			document.getElementById("partCol").value = partCol;
			document.getElementById("tsi").value = document.getElementById("siId").value
			
			if(partInfoArray.length < 1){
				Ext.MessageBox.alert('温馨提示：', '内容不能为空.', null);
				return;
			}
			if( document.getElementById("stockOutDate").value == ""){
				Ext.MessageBox.alert('温馨提示：', '退货日期不能为空.', null);
				return;
			}
			
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
			/*
			if(confirm ==  "8000"){
				//保存单据
				Ext.getCmp('savdj').disable();
				Ext.getCmp('aRem').disable();
				Ext.getCmp('allRem').disable();
				Ext.getCmp('ain').disable();
				Ext.getCmp('allin').disable();
				
				saveLianceReturn(confirm);
			}
			if(confirm ==  "8002"){
				//单据入库 -- 如果之前保存过了单据则进行更新库存操作
				if(loanRetId == ""){
					saveLianceReturn(confirm);
				}else{
					updateLianceReturn();
				}
			}
			*/
		}
		
		
		
		/*保存入库*/
		function saveLianceReturn(confirm){
			var url = ctx+"/insertStockReturnAction.action?";
			var pars = "isConfirm="+confirm+"&";
			pars+=Form.serialize($(insertStockReturnAction));
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
				window.location.href = ctx+"/findStockAction.action";
			}
		}
		/*更新入库*/
		function updateLianceReturn(){
			var url = ctx+"/updateStockReturnStateAction.action?";
			var pars = "id="+loanRetId;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:updatecallback}
			);
		}
		
		function updatecallback(originalRequest){
			loanRetId = "";
			window.location.href = ctx+"/findStockAction.action";
		}
		
		
		function queryTable(){
			var siId = document.getElementById("siId");
			siId.value = document.getElementById("tsi").value;
			tmStockInDetailTableQuery();
		}
		
		
		function openWin(){
			var props = "tsi,stockInCode,arriveDate,supplierName";
			showCommonWin('findAllStockInByStockAction.action','采购单列表',550,355,props,'queryTable');
			
		}
	</script>
	<body>
		
			
			<s:form action="insertStockReturnAction.action" >
			<table>
					<s:hidden name="partCol" id="partCol" ></s:hidden>
					<s:hidden id="tsi" name="tmStockOut.stockInId" ></s:hidden>
					<s:hidden name="tmStockOut.totalQuantity" id="totalQuantity" ></s:hidden>
					<s:hidden name="tmStockOut.totalPrice" id="totalPrice" ></s:hidden>
				<tr>
					<td>退货日期：</td>
					<td><s:textfield id="stockOutDate" name="tmStockOut.stockOutDate" />
						<e3c:calendar for="stockOutDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
			</s:form>
				
			<s:form action="findStockAction.action" >
				<s:hidden id="siId" name="tmStockInId" ></s:hidden>
			<table>
				<tr>
					<td>入库单号：</td>
					<td><s:textfield id="stockInCode" onfocus="openWin();" ></s:textfield></td>
					<td>入库日期：</td>
					<td><s:textfield id="arriveDate" ></s:textfield></td>
					<td>供应商：</td>
					<td><s:textfield id="supplierName" ></s:textfield></td>
				</tr> 
		
			</table>
			</s:form>
			
			
		
		
		
		<e3t:table id="tmStockInDetailTable" uri="findStockAction.action" var="vo"
			scope="request" items="detVos" mode="ajax"  caption="采购入库明细"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="unitName" title="单位" />
			<e3t:column property="quantity" title="入库数量" />
			<e3t:column property="hasQuantity" title="已退数量" />
			<e3t:column property="costPrice" title="成本价" />
			
			<!-- 隐藏域 -->
			<e3t:column property="stockInId" hidden="true"  title="入库单ID" />
			<e3t:column property="stockInDetailId" hidden="true" title="明细入库ID" />
			<e3t:column property="partinfoId"  hidden="true" title="配件ID" />
			
			<!-- 隐藏域 -->
		</e3t:table>
		
		
	
		
		
		<br>
		<br>		
		<div id="partinfocollectionDiv" ></div>
	</body> 
</html>

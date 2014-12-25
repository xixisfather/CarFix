<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>借进归还</title>
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
		var linanceRetId = "";						//配件归还ID 
	
		Ext.onReady(createGrid);					//初始化操作
	
		function dataHandler(record){
			/*验证*/
			var flag = hasAdd(record.get("lianceRegDtlId"));
			if(!flag) return ;
			/*验证*/
			
			/* 获取单元格值 */ 
			var e3lianceBill = record.get("lianceBill");				// 借进单号
			var e3lianceDate = record.get("lianceDate");				// 借进日期
			var e3houseName = record.get("houseName");					// 仓库
			var e3partCode = record.get("partCode");					// 配件代码
			var e3partName = record.get("partName");					// 借配件名称
			var e3unitName = record.get("unitName");					// 单位
			var e3lianceQuantity = record.get("lianceQuantity");		// 借进数量
			var e3returnQuantity = record.get("returnQuantity");		// 以还数量
			var e3lianceRegDtlId = record.get("lianceRegDtlId");		// 借进登记详细ID
			
			//未还数量
			var crQuantity = parseFloat(e3lianceQuantity) - parseFloat(e3returnQuantity);		
			if(parseFloat(crQuantity) == 0 ){
				Ext.MessageBox.alert('温馨提示：', e3partName+'配件已归还！', null);
				return;
			}
			
			
			// 现成本价
			var e3nowCosePrice = record.get("nowCosePrice");		
			
			var outArray = new Array();
			outArray.push(e3lianceBill);		//借进单号
			outArray.push(e3lianceDate);		//借进日期
			outArray.push(e3houseName);			//仓库
			outArray.push(e3partCode);			//配件代码
			outArray.push(e3partName);			//配件名称
			outArray.push(e3unitName);			//单位
			outArray.push(crQuantity);			//未还数量
			outArray.push(crQuantity);			//本次归还
			outArray.push(e3nowCosePrice);		//现成本价
			outArray.push(e3lianceRegDtlId);	//借进登记详细ID
			
			partInfoArray.push(outArray);
			
			
			store.loadData(partInfoArray);
			
		}
	
	
		function tmLianceRegDetailTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				id : 'ain',
				text : '单项移入',
				iconCls : 'addIcon',
				handler : function() {
					//得到当前选择行
					var selectedRow = tmLianceRegDetailTableE3ExtGrid.getSelectionModel().getSelected();
					//得到借进单号的值
					var lianceBill = selectedRow.get("lianceBill");
					//找到该行的索引
					var num = tmLianceRegDetailTableE3ExtStore.find('lianceBill',lianceBill);
					//找到record对象
					var record = tmLianceRegDetailTableE3ExtStore.getAt(num);
					//处理grid数据
					dataHandler(record);
				}
			}, '', '-', '', {
				id : 'allin',
				text : '全部移入',
				iconCls : 'addIcon',
				handler : function() {
					
					for(var j=0 ; j<tmLianceRegDetailTableE3ExtStore.getCount(); j++){
						//每行的record对象
						var record = tmLianceRegDetailTableE3ExtStore.getAt(j);
						//处理grid数据
						dataHandler(record);
					}
					
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmLianceRegDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findLinaceRegDetailAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmLianceRegDetailTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//处理数据
					dataHandler(record);
				
				});
		
		}
		
		/*判断某行借进单是否能移入*/
		function hasAdd(currBill){
			for(var i=0; i<partInfoArray.length; i++){
				var lianceBill = partInfoArray[i][partInfoArray[i].length-1];
				if(currBill == lianceBill)
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
		          
		           {name: 'lianceBill', type: 'string'},
		           {name:'lianceDate',type:'string'},
		           {name: 'houseName', type: 'string'},
		           {name: 'partCode', type: 'string'},
		           {name: 'partName', type: 'string'},
		           {name: 'unitName', type: 'string'},
		           {name: 'crQuantity', type: 'float'},
		           {name: 'quantity', type: 'float'},
		           {name: 'nowCosePrice', type: 'float'},
		           {name: 'lianceRegDtlId', type: 'float'},
		           
		        ]
		    });
		    //装载数据
		    store.loadData(partInfoArray);
		
		   	// 创建dataGrid
		    outGrid = new Ext.grid.GridPanel({
		        store: store,
		        columns: [
		        	 {id:'id',header: "id", sortable: true, hidden:true,dataIndex: 'id'},
		            {header: "借进单号",  sortable: true, dataIndex:'lianceBill'},
		            {header: "借进日期", width: 100, sortable: true, dataIndex:'lianceDate'},
		            {header: "仓库", width: 100, sortable: true, dataIndex:'houseName'},
		            {header: "配件代码", width: 50, sortable: true, dataIndex:'partCode'},
		            {header: "配件名称", width: 50, sortable: true, dataIndex:'partName'},
		            {header: "单位", width: 50, sortable: true, dataIndex:'unitName'},
		            {header: "未还数量", width: 80, sortable: true, dataIndex:'crQuantity'},
		            {
						header : "本次归还",
						width : 80,
						dataIndex : 'quantity',
						align : 'center',
						renderer : function(value, cellmeta, record, rowIndex, columnIndex,st) {
								var crQuantity = record.get("crQuantity");			// 未还数量
									return "<input style=width:50; onchange=\"isQan("+crQuantity+",this)\" id=quantity"+rowIndex+" type=text value='"+value+"' />" 
								}
					},
		            {header: "现成本价", width: 100, sortable: true, dataIndex:'nowCosePrice'},
		            {header: "借进登记详细ID", width: 100, hidden:true,sortable: true, dataIndex:'lianceRegDtlId'}
		        ],
		        stripeRows: true,
		        autoExpandColumn: 'id',
		        height:350,
		        width:700,
		        title:'借出列表',
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
							var lianceBill = selectedRow.get("lianceBill"); 
							var ttArr = new Array();
							for(var i=0; i<partInfoArray.length; i++){
								if(lianceBill == partInfoArray[i][0]) continue;
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
						text : "确认入账",
						iconCls : 'addIcon',
						handler : function() {
							addPartCollection("8002");
						}
					}]
		    });
		    //显示dataGrid
		    outGrid.render('partinfocollectionDiv');
		}
		
		/*验证数量，归还数量不能大于借进数量*/
		function isQan(crQan,currQan){
			if(parseFloat(currQan.value)<= 0 || parseFloat(crQan)< parseFloat(currQan.value)){
				currQan.value = crQan;
				currQan.focus();
				Ext.MessageBox.alert('温馨提示：', '数量输了有误！', null);
			}
		}
		
		//添加入库单
		function addPartCollection(confirm){
			
			var partCol = "";
			for(var i=0; i<partInfoArray.length; i++){}
			
			for(var j=0 ; j<store.getCount(); j++){
				//每行的record对象
				var record = store.getAt(j);
				var lianceBill = record.get("lianceBill");							// 借进单号
				var returnQ = record.get("crQuantity");								// 未还数量
				var lackReturnQ = document.getElementById("quantity"+j).value;		// 本次归还数量
				var nowCosePrice = record.get("nowCosePrice");						// 归还时的成本价
				var lianceRegDtlId = record.get("lianceRegDtlId");					//借进登记详细ID
				var str = lianceRegDtlId+":"+lianceBill+":"+returnQ+":"+lackReturnQ+":"+nowCosePrice;
				
				partCol += str + ",";
			}
			
			
			document.getElementById("partCol").value = partCol;
			if(partInfoArray.length < 1){
				Ext.MessageBox.alert('温馨提示：', '内容不能为空.', null);
				return;
			}
			if( document.getElementById("returnDate").value == ""){
				Ext.MessageBox.alert('温馨提示：', '归还日期不能为空.', null);
				return;
			}
			
			document.forms["insertLinaceReturnDetailAction"].action += "?isConfirm="+confirm;
			document.forms["insertLinaceReturnDetailAction"].submit();
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
				if(linanceRetId == ""){
					saveLianceReturn(confirm);
				}else{
					updateLianceReturn();
				}
			}
			*/
		}
		
		
		
		/*保存入库*/
		function saveLianceReturn(confirm){
			var url = ctx+"/insertLinaceReturnDetailAction.action?";
			var pars = "isConfirm="+confirm+"&";
			pars+=Form.serialize($(insertLinaceReturnDetailAction));
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:savecallback}
			);
			
		}
		
		function savecallback(originalRequest){
			var tminfo = originalRequest.responseText.split(',');
			linanceRetId  = tminfo[0];
			var suc = tminfo[1];
			
			if(suc == parseInt("8002")){
				window.location.href = ctx+"/findLinaceRegDetailAction.action";
			}
		}
		/*更新入库*/
		function updateLianceReturn(){
			var url = ctx+"/updateLianceReturnAction.action?";
			var pars = "id="+linanceRetId;
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:updatecallback}
			);
		}
		
		function updatecallback(originalRequest){
			linanceRetId = "";
			window.location.href = ctx+"/findLinaceRegDetailAction.action";
		}
		
		
		
		function openSupplierWin(){
			/*移出全部行*/
			store.removeAll();
			/*重载数据*/
			partInfoArray = new Array();
			document.getElementById("lian").value = "";
			document.getElementById("lianceBill").value = "";
			var props = "supp,null,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,'tmLianceRegDetailTable');
		}
		
		function openLianceRegWin(){
			/*移出全部行*/
			store.removeAll();
			/*重载数据*/
			partInfoArray = new Array();
			document.getElementById("supp").value = "";
			document.getElementById("customerName").value = "";
			var props = "lian,lianceBill,null";
			showCommonWin('findAllTmLianceRegisterAction.action','借进列表',575,355,props,'tmLianceRegDetailTable');
		}
		
		
		
	</script>
	<body>
		<s:form action="findLinaceRegDetailAction.action">
			<s:hidden name="supplierId" id="supp" ></s:hidden>
			<s:hidden name="lianceRegId" id="lian" ></s:hidden>
			<table>
				<tr>
					<td>供应商：</td>
					<td><s:textfield id="customerName" onfocus="openSupplierWin();" ></s:textfield> </td>
					<td>借进单号：</td>
					<td><s:textfield id="lianceBill" onfocus="openLianceRegWin();"  ></s:textfield></td>
				</tr>
				
			
		</s:form>
		
		<s:form action="insertLinaceReturnDetailAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			
			<table>
				<tr>
					<td>归还日期：</td>
					<td><s:textfield id="returnDate" name="tmLianceReturn.returnDate" />
						<e3c:calendar for="returnDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
			
			
		</s:form>
		
		
		<e3t:table id="tmLianceRegDetailTable" uri="findLinaceRegDetailAction.action" var="collectionVo"
			scope="request" items="collctionList" mode="ajax"  caption="借进明细"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="lianceBill" title="借进单号" />
			<e3t:column property="lianceDate" title="借进日期" >
				<fmt:formatDate value="${collectionVo.lianceDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="unitName" title="单位" />
			<e3t:column property="lianceQuantity" title="借进数量" />
			<e3t:column property="returnQuantity" title="以还数量" />
			<e3t:column property="liancePrice" title="借进价格" />
			<e3t:column property="oldCostPrice" title="当时成本价" />
			
			<!-- 隐藏域 -->
			<e3t:column property="lianceRegId" hidden="true"  title="借进登记ID" />
			<e3t:column property="lianceRegDtlId" hidden="true" title="借进登记详细ID" />
			<e3t:column property="nowCosePrice" hidden="true"  title="现成本价" />
			
			<!-- 隐藏域 -->
		</e3t:table>
		
		
	
		
		
		<br>
		<br>		
		<div id="partinfocollectionDiv" ></div>
	</body> 
</html>

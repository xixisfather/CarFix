<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title></title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
				pConfig.tbar = [
				{	
					text : '新增',
					iconCls : 'addIcon',
					handler : function() {
						addObject('tbPartInfoForwardAction.action',600,300);
					}
				}, '', '-', '', {
					text : '刷新',
					iconCls : 'refreshIcon',
					handler : function() {
						refresh_tbPartInfoTable();
					}
				} ];
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tabFindPartInfoAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbPartInfoTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record

				addPartInfo(record);
			});
	
		}
		function addPartInfo(record){
			
				
		
		
			//处理数据
			var e3partId = record.get("id");
			var e3houseName = record.get("houseName");
			var e3partName= record.get("partName");
			var e3storeLocation = record.get("storeLocation");
			var e3unitName = record.get("unitName"); 
			var e3storeQuantity = record.get("storeQuantity");
			var e3sellPrice = record.get("sellPrice");
			var e3costPrice = record.get("costPrice");
			var e3partCode = record.get("partCode");
			var e3ModelName = record.get("modelName");
			/*防止重复添加*/ 
			/* 
			for(var i=0; i<parent.tbmainArray.length; i++){
				var obj = parent.tbmainArray[i];
				if(obj.tbPartInfoId == e3partId) return;
			}
			*/
			 
			tbmainObj = {}
		    tbmainObj.tbPartInfoId = e3partId;
		    tbmainObj.partQuantity = 0;
		    tbmainObj.price = e3sellPrice;
		    tbmainObj.cliamPartPersonId = "";
		    tbmainObj.totalPrice = "";
		    tbmainObj.houseName = e3houseName;
		    tbmainObj.partName = e3partName;
		    tbmainObj.unitName = e3unitName;
		    tbmainObj.isFree = "false";
		    tbmainObj.addType = "newAdd";
		    tbmainObj.costPrice = e3costPrice;
		    tbmainObj.partCode = e3partCode;
		    tbmainObj.modelName = e3ModelName;
		    parent.tbmainArray.push(tbmainObj);
			 
			 /*
			var mainTable = parent.document.getElementById("mainTable");
			
		    var mainRow = mainTable.insertRow(); 
		    var houseNameCell = mainRow.insertCell();
		    var partNameCell = mainRow.insertCell();
		    var unitNameCell = mainRow.insertCell();
		    var costPriceCell = mainRow.insertCell();
		    var quantityCell = mainRow.insertCell();
		    var peopleCell = mainRow.insertCell();
		    var xjCell = mainRow.insertCell();
		    xjCell.id = "xj"+e3partId;
		    
		   
		    
		    houseNameCell.innerHTML = e3houseName;
		    partNameCell.innerHTML = e3partName;
		    unitNameCell.innerHTML = e3unitName;
		    costPriceCell.innerHTML = "<input id='price"+e3partId+"' name=pirces value="+e3costPrice+" onchange=\"jsprice("+e3partId+");\" />";
		    quantityCell.innerHTML = "<input id='partQuantity"+e3partId+"' name=quantities onchange=\"jsprice("+e3partId+");\"  />";
		    peopleCell.innerHTML = "<input id='pep"+e3partId+"' name=peoples onfocus=\"openPeoWin('phi"+e3partId+"' , this.id)\" /><input id='phi"+e3partId+"' type=hidden  />";
		    xjCell.innerHTML = "0.00";
		    */
		    
		    var entrustId  =  parent.document.getElementById("entrustId");
		    
		    if(entrustId != null && entrustId.value != null  && entrustId.value != ""){
		    	requestSellPrice(e3partId,entrustId.value);
		    }else{
		    	parent.addPartInfo(tbmainObj,false);
		    }
		    
		    
		}
		
		
		function requestSellPrice(partId,entrustId){
			
			var url = "getCustomerSellpriceAction.action?";
			
			var param = "partId="+partId+"&entrustId="+entrustId;
			
			var ajax = new Ajax.Request(url, {
				method : 'get',
	
				parameters:param,
				
				onComplete : getSellpriceInfo,
	
				asynchronous : true
	
			});
		}
		
		function getSellpriceInfo(originalRequest){
		
			var reponseInfo = originalRequest.responseText;

			tbmainObj.price = reponseInfo;
			
			parent.addPartInfo(tbmainObj,false);
		}
		
		var tbmainObj = {};
	</script>
	<body  >
	<s:form action="tabFindPartInfoAction.action">
		<table>
			<tr>	
				<td>仓库</td>
				<td><s:select name="tbPartInfo.tmStoreHouse.id" list="#request.stroeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
				<td>车辆类型</td>
				<td><s:select name="tbPartInfo.tmCarModelType.id" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/></td>
				<td>配件名称</td>
				<td><s:textfield name="tbPartInfo.partName" /></td>
			</tr>
			
			<tr>
				<td>配件代码</td>
				<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
				<td>拼音名称</td>
				<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
				<td>配件类型</td>
				<td><s:select name="tbPartInfo.tmPartType.id" list="#request.partTypeList" emptyOption="true" listKey="id" listValue="typeName"/></td>
			</tr>
			<tr>
				<td>
					<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
				</td>
			</tr>
		</table>
	</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="tabFindPartInfoAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" caption="配件"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="950"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="modelName" beanProperty="tmCarModelType.modelName" title="车型" />
			<e3t:column width="80" property="storeLocation" title="仓位" />
			<e3t:column property="unitName" title="计量单位" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column property="sellPrice" title="销售价" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="id" hidden="true" title="配件id" />
		</e3t:table>
	</body> 
		
		
</html>

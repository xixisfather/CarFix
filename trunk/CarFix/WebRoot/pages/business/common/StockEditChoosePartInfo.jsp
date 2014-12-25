<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件列表</title>
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
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllTbPartInfoAction";
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
			var e3unitName = record.get("unitName"); 
			var e3storeQuantity = record.get("storeQuantity");
			var e3costPrice = record.get("costPrice");
			var e3houseId = record.get("houseId");
			var e3sellPrice = record.get("sellPrice");
			var e3factPrice = record.get("factPrice");
			var e3storeLocation = record.get("storeLocation");
			var e3partCode = record.get("partCode");

			parent.tbPartInfoObj = {};
			parent.tbPartInfoObj.tbPartInfoId = e3partId;
			parent.tbPartInfoObj.houseName = e3houseName;
			parent.tbPartInfoObj.partName = e3partName;
		    parent.tbPartInfoObj.partQuantity = 0;
		    parent.tbPartInfoObj.unitName =  e3unitName;
		    parent.tbPartInfoObj.price = e3costPrice;
			parent.tbPartInfoObj.storeQuantity = e3storeQuantity;
			parent.tbPartInfoObj.houseId = e3houseId;
			parent.tbPartInfoObj.sellPrice = e3sellPrice;
			parent.tbPartInfoObj.factPrice = e3factPrice;
			parent.tbPartInfoObj.storeLocation = e3storeLocation;
			parent.tbPartInfoObj.partCode = e3partCode;
			parent.extWindow.hide();
					
			var e3table = "${e3Table}";
			if(e3table != 'null' && e3table != ""  )
				parent.eval(e3table+ '()');
			
		}
		
		
	</script>
	<body  >
		<s:form action="findAllTbPartInfoAction.action">
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
		
		<e3t:table id="tbPartInfoTable" uri="findAllTbPartInfoAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="650"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="typeName" title="配件类型" />
			<e3t:column property="storeLocation" title="仓位" />
			<e3t:column width="40" property="unitName" title="单位" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column property="costPrice" title="成本价" />
			
			<e3t:column property="sellPrice" hidden="true" title="销售价" />
			<e3t:column property="houseId" beanProperty="tmStoreHouse.id"  hidden="true" title="仓库ID" />
			<e3t:column property="id" hidden="true" title="配件ID" />
			<e3t:column property="factPrice" hidden="true" title="库存金额" />
			<e3t:column property="partCode" hidden="true" title="配件代码" />
		</e3t:table>
		
		
	</body>  
		
		
</html>

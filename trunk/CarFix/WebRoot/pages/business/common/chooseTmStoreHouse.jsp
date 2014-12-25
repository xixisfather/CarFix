<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库列表</title>
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
		function tmStoreHouseTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStoreHouseTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllTmStoreHouseAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmStoreHouseTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record

				addPartInfo(record);
			});
	
		}
		function addPartInfo(record){
			
			//处理数据
			var e3houseId = record.get("id");
			var e3houseCode = record.get("houseCode");
			var e3houseName = record.get("houseName");
			var e3isMixed = record.get("isMixed");
			var e3isMixedShow = record.get("isMixedShow");

			parent.tmStoreHouse = {};
			parent.tmStoreHouse.id = e3houseId;
			parent.tmStoreHouse.houseCode = e3houseCode;
			parent.tmStoreHouse.houseName = e3houseName;
			parent.tmStoreHouse.isMixed = e3isMixed;
			parent.tmStoreHouse.isMixedShow = e3isMixedShow;
			if(parent.extWindow != null)
				parent.extWindow.hide();
					
			var e3table = "${e3Table}";
			if(e3table != 'null' && e3table != ""  )
				parent.eval(e3table+ '()');
				
			parent.addStoreHouse();
			
		}
		
		
	</script>
	<body  >
		<s:form action="findAllTmStoreHouseAction.action">
			<table>
				<tr>	
					<td>仓库代码</td>
					<td><s:textfield name="tmStoreHouse.houseCode" /></td>
					<td>仓库名称</td>
					<td><s:textfield name="tmStoreHouse.houseName" /></td>					
				</tr>
				
				<tr>
					<td>
						<input type="button" value="查询" onclick="tmStoreHouseTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tmStoreHouseTable" uri="findAllTmStoreHouseAction.action" var="tmStoreHouse"
			scope="request" items="tmStoreHouseList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="650"
			height="320" >
			<e3t:column property="houseCode" title="仓库代码" />
			<e3t:column property="houseName" title="仓库名称" />
			<e3t:column property="isMixedShow" title="是否为杂项" />
			<e3t:column property="isMixed" hidden="true" title="是否为杂项" />
			<e3t:column property="id" hidden="true" title="仓库id" />
		</e3t:table>
		
		
	</body>  
		
		
</html>

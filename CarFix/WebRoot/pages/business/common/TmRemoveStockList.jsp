<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>移库出仓列表</title>
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
		
		
		function tmDetTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllRemoveStockAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var removeStockId = parent.document.getElementById("${removeStockId}");
					var removeStockBill = parent.document.getElementById("${removeStockBill}");
					var removeDate = parent.document.getElementById("${removeDate}");
					var removeStoreHouseId = parent.document.getElementById("${removeStoreHouseId}");
					
					//e3列值
					var e3tmstockId = record.get("tmstockId");		
					var e3houseId = record.get("houseId");
					var e3removeStockBill = record.get("removeStockBill");
					var e3removeDate = record.get("removeDate");		
					
				
					//赋值
					if(removeStockId != null)
						removeStockId.value = e3tmstockId;
					if(removeStoreHouseId != null)
						removeStoreHouseId.value = e3houseId;
					if(removeStockBill != null)
						removeStockBill.value = e3removeStockBill;
					if(removeDate != null)
						removeDate.value = e3removeDate;
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
				});
		
		}
		
		
	</script>
	<body>
		
		<e3t:table id="tmDetTable" uri="findAllRemoveStockAction.action" var="remVo"
			scope="request" items="remStockVoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
			height="320" >
			<e3t:column property="removeStockBill" title="移出单据号" />
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="houseCode" title="仓库代码" />
			<e3t:column property="dat" title="移出日期" >
				<fmt:formatDate value="${remVo.removeDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			
			<e3t:column property="tmstockId" hidden="true" title="移库出仓单ID" />
			<e3t:column property="houseId" hidden="true" title="仓库ID" />
			<e3t:column property="removeDate" hidden="true" title="移库出仓单ID" />
		</e3t:table>
		
	</body> 
</html>

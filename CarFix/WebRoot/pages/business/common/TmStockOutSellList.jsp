<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>销售出库列表</title>
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
			pConfig.form = "findAllTmStockOutBySellAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var qsoi = parent.document.getElementById("${qsoi}");
					var stockOutCode = parent.document.getElementById("${stockOutCode}");
					var sellDate = parent.document.getElementById("${sellDate}");
					var customerName = parent.document.getElementById("${customerName}");
					
					//e3列值
					var e3stockOutId = record.get("stockOutId");		
					var e3stockOutCode = record.get("stockOutCode");
					var e3stockOutDate = record.get("stockOutDate");
					var e3customerName = record.get("customerName");		
					
					//赋值
					if(qsoi != null)
						qsoi.value = e3stockOutId;
					if(stockOutCode != null)
						stockOutCode.value = e3stockOutCode;
					if(sellDate != null)
						sellDate.value = e3stockOutDate;
					if(customerName != null)
						customerName.value = e3customerName;
					parent.extWindow.hide();
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
				});
		
		}
		
		
	</script>
	<body>
		
		<e3t:table id="tmDetTable" uri="findAllTmStockOutBySellAction.action" var="remVo"
			scope="request" items="sellVoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
			height="320" >
			<e3t:column property="stockOutCode" title="销售单号" />
			<e3t:column property="stockOutDate" title="销售日期" />
			<e3t:column property="soleName"  title="销售类型" />
			<e3t:column property="customerName"  title="客户" />
			<e3t:column property="totalPrice" title="合计总金额" />
			
			<e3t:column property="stockOutId" hidden="true" title="销售出库ID" />
		</e3t:table>
		
	</body> 
</html>

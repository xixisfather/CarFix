<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>借进列表</title>
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
			pConfig.form = "findAllTmLianceRegisterAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var lianceRegId = parent.document.getElementById("${lianceRegId}");
					var lianceBill = parent.document.getElementById("${lianceBill}");
					var lianceDate = parent.document.getElementById("${lianceDate}");
					
					//e3列值
					var e3lianceRegId = record.get("lianceId");		
					var e3lianceBill = record.get("lianceBill");
					var e3lianceDate = record.get("lianceDate");		
					
					//赋值
					if(lianceRegId != null)
						lianceRegId.value = e3lianceRegId;
					if(lianceBill != null)
						lianceBill.value = e3lianceBill;
					if(lianceDate != null)
						lianceDate.value = e3lianceDate;
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
				});
		
		}
		
		
	</script>
	<body>
		
		<e3t:table id="tmDetTable" uri="findAllTmLianceRegisterAction.action" var="remVo"
			scope="request" items="lianceRegVos" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
			height="320" >
			<e3t:column property="lianceBill" title="借进单据" />
			<e3t:column property="lianceDate" title="借进日期" />
			<e3t:column property="customerName" title="供应商号" />
			<e3t:column property="customerCode"  title="供应商" />
			
			<e3t:column property="lianceId" hidden="true" title="借进ID" />
		</e3t:table>
		
	</body> 
</html>

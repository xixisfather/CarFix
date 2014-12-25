<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>借出列表</title>
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
			pConfig.form = "findAllTmLoanRegisterAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var loanRegId = parent.document.getElementById("${loanRegId}");
					var loanBill = parent.document.getElementById("${loanBill}");
					var loanDate = parent.document.getElementById("${loanDate}");
					
					//e3列值
					var e3loanId = record.get("loanId");		
					var e3loanBill = record.get("loanBill");
					var e3loanDate = record.get("loanDate");		
					
					//赋值
					if(loanRegId != null)
						loanRegId.value = e3loanId;
					if(loanBill != null)
						loanBill.value = e3loanBill;
					if(loanDate != null)
						loanDate.value = e3loanDate;
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
				});
		
		}
		
		
	</script>
	<body>
		
		<e3t:table id="tmDetTable" uri="findAllTmLoanRegisterAction.action" var="remVo"
			scope="request" items="loanRegVos" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
			height="320" >
			<e3t:column property="loanBill" title="借出单据" />
			<e3t:column property="loanDate" title="借出日期" />
			<e3t:column property="customerName" title="供应商号" />
			<e3t:column property="customerCode"  title="供应商" />
			
			<e3t:column property="loanId" hidden="true" title="借出ID" />
		</e3t:table>
		
	</body> 
</html>

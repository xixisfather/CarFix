<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>数据导入</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form id="importXlsForm" action="tmCarModelTypeImportXlsAction.action" enctype="multipart/form-data">
			<table>
				<tr>
					<td>选择数据文件</td>
					<td>
						
						<s:file name="fileXls"></s:file>
					
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="确定"/>
						
						<input type="button" value="关闭" onclick="closeWin();"/>
					</td>
					
				</tr>
			</table>
		</s:form>
		
	</body>
</html>
<script language="javascript">
	var form = document.getElementById('importXlsForm');
	
	form.onsubmit=function(){
		Ext.MessageBox.wait("处理数据中,请等待......", "信息");   
	}
	
	function closeWin(){
  		
		parent.win.hide();
	}
</script>
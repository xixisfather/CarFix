<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>销售类型增加</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmSoleType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	</head>

	<body>
		<s:form action="tmSoleTypeInsertAction.action" onsubmit="return formValidate()">
			<s:hidden id="isDefault" name="tmSoleType.isDefault" value="0" ></s:hidden>
			<table>
				<tr>
					<td>售价名称</td>
					<td>
						
						<s:textfield id="soleName" name="tmSoleType.soleName" />
						<font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>是否为默认</td>
					<td>
						<input type="checkbox" value="0" onclick="var isd = document.getElementById('isDefault');if(this.checked){isd.value=1;}else{isd.value=0}" >
						<font color="red">*</font>
					</td>
					
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="增加" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>	
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>单位修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmUnit.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	</head>

	<body>
		<s:form action="tmUnitUpdateAction.action" onsubmit="return formValidate()">
			<table>
				<tr>
					<td><s:hidden name="tmUnit.id"/></td>
				</tr>
				<tr>
					<td>单位名称</td>
					<td>
						
						<s:textfield id="unitName" name="tmUnit.unitName" />
						<font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>单位说明</td>
					<td>
						
						<s:textarea name="tmUnit.unitRemark" />

					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="修改" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

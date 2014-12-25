<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>部门增加</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmDepartmentInsertAction.action">
			<table>
				<tr>
					<td>部门编号</td>
					<td>
						
						<s:textfield name="tmDepartment.deptCode" />
					</td>
					
				</tr>
				<tr>
					<td>部门名称</td>
					<td>
						
						<s:textarea name="tmDepartment.deptName" />

					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="增加" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

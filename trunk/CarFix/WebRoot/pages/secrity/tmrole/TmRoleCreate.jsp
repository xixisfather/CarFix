<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增角色
		</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmRoleInsertAction.action">
			<table>
				<tr>
					<td>
						角色代码：
						<s:textfield name="tmRole.roleCode" />
					</td>
				
				</tr>
				<tr>
					<td>
						角色名称：
						<s:textfield name="tmRole.roleName" />

					</td>
				</tr>
				<tr>
					<td>
						角色说明：
						<s:textarea name="tmRole.roleNote" />

					</td>
				</tr>
				<tr>

					<td align="center">
						<input type="submit"
							value="<s:text name="TM_FLAG_CAR_BUTTON_SUBMIT"/>" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

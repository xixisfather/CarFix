<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmFlagCarUpdateAction.action">
			<table>
				<tr>
					<td><s:hidden name="tmFlagCar.id"/></td>
				</tr>
				<tr>
					<td>
						<s:text name="TM_FLAG_CAR_CODE" />
						<s:textfield name="tmFlagCar.flagCode" />
					</td>
				</tr>
				<tr>
					<td>
						<s:text name="TM_FLAG_CAR_REMARK" />
						<s:textarea name="tmFlagCar.flagRemark" />

					</td>
				</tr>
				<tr>

					<td align="center">
						<input type="submit"
							value="<s:text name="TM_USER_BUTTON_SUBMIT"/>" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理类型修改</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmSoleTypeUpdateAction.action">
			<table>
				<tr>
					<td><s:hidden name="tmSoleType.id"/></td>
				</tr>
				<tr>
					<td>售价名称</td>
					<td>
						
						<s:textfield name="tmSoleType.soleName" />
					</td>
					
				</tr>
				
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="修改" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

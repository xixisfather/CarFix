<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>项目类型修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmProjectType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	</head>

	<body>
		<s:form action="tmProjectTypeUpdateAction.action">
			<table>
				<tr>
					<td><s:hidden name="tmProjectType.id"/></td>
				</tr>
				<tr>
					<td>维修项目类型</td>
					<td>
						
						<s:textfield id="projectType" name="tmProjectType.projectType" />
						
					</td>
					
				</tr>
				<tr>
					<td>帐类</td>
					<td>
						
						<s:textfield id="zl" name="tmProjectType.zl" />

					</td>
				</tr>
				<tr>
					<td>维修工单类型</td>
					<td>
						
						<s:textfield id="xmlx" name="tmProjectType.xmlx" />

					</td>
				</tr>
				<tr>

					<td align="center" colspan="8">
						<input type="submit" value="修改" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

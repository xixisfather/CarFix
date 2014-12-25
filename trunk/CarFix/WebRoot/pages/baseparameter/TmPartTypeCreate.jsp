<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆类型增加
		</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmPartType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmPartTypeInsertAction.action" onsubmit="return formValidate()">
			<table>
				<tr>
					<td>
						类型代码
					</td>
					<td>	
						<s:textfield id="typeCode" name="tmPartType.typeCode" />
						<font color="red">*</font>
					</td>
				
				</tr>
				<tr>
					<td>
						类型名称
					</td>
					<td>	
						<s:textarea id="typeName" name="tmPartType.typeName" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						类型说明
					</td>
					<td>	
						<s:textarea name="tmPartType.typeRemark" />
					</td>
				</tr>
				
				<tr>

					<td align="center">
						<input type="submit"
							value="<s:text name="TM_FLAG_CAR_BUTTON_SUBMIT"/>" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

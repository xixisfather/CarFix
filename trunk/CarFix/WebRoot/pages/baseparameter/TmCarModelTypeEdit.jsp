<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆类型修改
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
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmCarModelType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmCarModelTypeUpdateAction.action" onsubmit="return formValidate()">
			<table>
				<tr>
					<td><s:hidden name="tmCarModelType.id"/></td>
				</tr>
				<tr>
					<td>
						车型代码
					</td>
					<td>	
						<s:textfield id="modelCode" name="tmCarModelType.modelCode" />
						<font color="red">*</font>
					</td>
				
				</tr>
				<tr>
					<td>
						车型名称
					</td>
					<td>	
						<s:textarea id="modelName" name="tmCarModelType.modelName" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						车型工位标志
					</td>
					<td>	
						<s:select id="tmCarStationTypeId" name="tmCarModelType.tmCarStationType.id" list="#request.tmCarStationTypeMap" emptyOption="true" listKey="key" listValue="value"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						工时单价
					</td>
					<td>	
						<s:select id="tmWorkingHourPriceId" name="tmCarModelType.tmWorkingHourPrice.id" list="#request.tmWorkingHourPriceMap" emptyOption="true" listKey="key" listValue="value"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>仓库修改</title>
	<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmStoreHouse.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmStoreHouseUpdateAction.action" onsubmit="return formValidate()">
			<table>
				<tr>
					<td><s:hidden name="tmStoreHouse.id"/></td>
				</tr>
				<tr>
					<td>
						<s:text name="TM_STORE_HOUSE_CODE" />
						<s:textfield id="houseCode" name="tmStoreHouse.houseCode" />
						<font color="red">*</font>
					</td>
					<td>
						<s:text name="TM_STORE_HOUSE_NAME" />
						<s:textfield id="houseName" name="tmStoreHouse.houseName" />
						<font color="red">*</font>
					</td>
					<td>
						<s:text name="TM_STORE_HOUSE_ISMIXED" />
						<s:select name="tmStoreHouse.isMixed" list="#request.isNoMap"
							emptyOption="true" listKey="key" listValue="value" />

					</td>
				</tr>
				<tr>
					<td>
						<s:text name="TM_STORE_HOUSE_REMARK" />
						<s:textarea name="tmStoreHouse.houseRemark" />

					</td>
				</tr>
				<tr>

					<td align="center">
						<input type="submit"
							value="<s:text name="TM_USER_BUTTON_SUBMIT"/>" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

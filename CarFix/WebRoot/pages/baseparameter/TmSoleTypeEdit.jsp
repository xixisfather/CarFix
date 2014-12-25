<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理类型修改</title>
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
		<s:form action="tmSoleTypeUpdateAction.action" onsubmit="return formValidate()">
			<s:hidden id="isDefault" name="tmSoleType.isDefault" ></s:hidden>
			<table>
				<tr>
					<td><s:hidden name="tmSoleType.id"/></td>
				</tr>
				<tr>
					<td>售价名称</td>
					<td>
						
						<s:textfield id="soleName" name="tmSoleType.soleName" />
					</td>
					
				</tr>
				<tr>
					<td>是否为默认</td>
					<td>
						<c:if test="${tmSoleType.isDefault == 1}">
							<input type="checkbox"  checked="checked" onclick="var isd = document.getElementById('isDefault');if(this.checked){isd.value=1;}else{isd.value=0}" >
						</c:if>
						<c:if test="${tmSoleType.isDefault == 0}">
							<input type="checkbox"  onclick="var isd = document.getElementById('isDefault');if(this.checked){isd.value=1;}else{isd.value=0}" >
						</c:if>
						<font color="red">*</font>
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

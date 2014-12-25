<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="TM_USER_TITLE" /></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/secrity/TmUser.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmUserFindAction.action">
			<table>
				<tr>
					<td>
						<s:text name="TM_USER_USERNAME" />
						<s:textfield name="tmUser.userName" />
					</td>
					<td>
						<s:text name="TM_USER_PASSWORD" />
						<s:textfield name="tmUser.password" />
					</td>
					
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmUserTableQuery();" />
					</td>
				</tr>


			</table>


		</s:form>
		<!--使用JSTL使E3Table国际化-->
		<c:set var="TM_USER_TABLE_CAPTION">
			<s:text name="TM_USER_TABLE_CAPTION" />
		</c:set>
		<c:set var="TM_USER_USERNAME">
			<s:text name="TM_USER_USERNAME" />
		</c:set>
		<c:set var="TM_USER_PASSWORD">
			<s:text name="TM_USER_PASSWORD" />
		</c:set>
		<c:set var="TM_USER_TABLE_OPERATE">
			<s:text name="TM_USER_TABLE_OPERATE" />
		</c:set>
		<!-- end -->
		<e3t:table id="tmUserTable" uri="tmUserFindAction.action" var="tmUser"
			scope="request" items="tmUserList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" caption="${TM_USER_TABLE_CAPTION}">
			<e3t:column property="userName" title="${TM_USER_USERNAME}" />
			<e3t:column property="userRealName" title="员工姓名" />
			<e3t:column property="password" title="${TM_USER_PASSWORD}" />
			<e3t:column property="userStatus" title="账号状态" />
			
			
			<e3t:column property="no" title="${TM_USER_TABLE_OPERATE}"
				sortable="false">
				<a href="javascript:editObject('${tmUser.id}','tmUserForwardPageAction!forwardPage.action',800,450);"><font color="blue"><s:text
							name="TM_USER_HREF_UPDATE" />
				</font>
				</a>
				  &nbsp;&nbsp;
				  <!--
  				<a
					href="javascript:deleteObject('${tmUser.id}','tmUserDeleteAction.action');"><font
					color="blue"><s:text name="TM_USER_HREF_DELETE" />
				</font>
				</a>
				  -->
				<a href="javascript:operateObject('${tmUser.id}&flag=qy','tmUserValidAction.action','确定启用该账号?');">
					<font color="blue">启用</font>
				</a>
				
				<a href="javascript:operateObject('${tmUser.id}&flag=ty','tmUserValidAction.action','确定停用该账号?');">
					<font color="blue">停用</font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>

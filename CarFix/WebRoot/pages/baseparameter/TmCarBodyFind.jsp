<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车身部位查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmCarBody.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	</head>

	<body>
		<s:form action="tmCarBodyFindAction.action">
		
		</s:form>
		<e3t:table id="tmCarBodyTable" uri="tmCarBodyFindAction.action" var="tmCarBody"
			scope="request" items="tmCarBodyList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="360" caption="车身部位">
			<e3t:column property="bodyName" title="部位名称" />
			<e3t:column property="bodyRemark" title="说明" width="300"/>
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmCarBody.id}','tmCarBodyForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmCarBody.id}','tmCarBodyDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>

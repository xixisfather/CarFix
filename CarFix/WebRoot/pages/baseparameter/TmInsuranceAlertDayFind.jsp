<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>维修回访天数定义</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmInsuranceAlertDay.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmInsuranceAlertDayFindAction.action">
		
		</s:form>
		<e3t:table id="tmInsuranceAlertDayTable" uri="tmInsuranceAlertDayFindAction.action" var="tmInsuranceAlertDay"
			scope="request" items="tmInsuranceAlertDayList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="360" caption="维修回访天数">
			<e3t:column property="id" hidden="true"/>
			<e3t:column property="alertDay" title="维修回访天数" width="300"/>
			<e3t:column property="continueDay" title="持续提醒天数" width="300"/>
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmInsuranceAlertDay.id}','tmInsuranceAlertDayForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>

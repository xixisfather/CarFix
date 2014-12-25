<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>维修预约查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbBook.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBookQueryAction.action">
			<s:hidden id="licenseCode" name="tbBook.licenseCode"></s:hidden>
		</s:form>
		<e3t:table id="tbBookTable" uri="tbBookQueryAction.action" var="tbBook"
			scope="request" items="tbBookList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" caption="">
			<e3t:column property="bookCode" title="预约单号"/>
			<e3t:column property="registerDate" title="登记日期"/>
			<e3t:column property="modelName" title="车型"/>
			<e3t:column property="customerName" title="车主"/>
			<e3t:column property="fixType" title="修理类型"/>
			<e3t:column property="planFixTime" title="约定修理时间"/>
			<e3t:column property="isComeShow" title="是否履约"/>
			<e3t:column property="userName" title="服务顾问"/>
			<e3t:column property="fixContent" title="维修内容"/>
			<e3t:column property="bookRemark" title="备注说明"/>
		</e3t:table>
	</body>
</html>
<script>
	var licenseCode = document.getElementById('licenseCode');
	
	licenseCode.value = parent.document.getElementById('licenseCode').value;
	
	setTimeout('refresh_tbBookTable()',2000);
	
</script>
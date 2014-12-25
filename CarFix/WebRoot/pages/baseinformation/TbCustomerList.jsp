<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>客户列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		
		
	</script>
	<body>
		
		<s:form action="tbCustomerSelectForCardAction.action">
			<table>
				<tr>
					<td>客户号</td>
					<td>
						<s:textfield id="customerCode" name="tbCarInfo.tbCustomer.customerCode"/>
					</td>
					<td>客户名称</td>
					<td><s:textfield id="customerName" name="tbCarInfo.tbCustomer.customerName"/></td>
					<td>车牌号</td>
					<td><s:textfield name="tbCarInfo.licenseCode"/></td>
				</tr>
				<tr>
					<td>底盘号</td>
					<td><s:textfield name="tbCarInfo.chassisCode"/></td>
					<td>车型</td>
					<td><s:select name="tbCarInfo.tmCarModelType.id" list="#request.tmCarModelTypeMap" emptyOption="true" listKey="key" listValue="value"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询"
							onclick="tbCustomerTableQuery();" />
							
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		
			
		
		</s:form>
		
		<e3t:table id="tbCustomerTable" uri="tbCustomerSelectForCardAction.action" var="tbCarInfo"
			scope="request" items="tbCarInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" >
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户名称" />
			<e3t:column property="licenseCode" title="牌照号" />
		</e3t:table>
		
	</body> 
</html>

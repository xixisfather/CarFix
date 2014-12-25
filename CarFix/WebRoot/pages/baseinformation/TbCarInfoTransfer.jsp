<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆过户</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCustomer.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCarInfo.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbCarInfoUpdateAction.action">
			<table>
				<tr>
					<td>原客户号</td>
					<td>
						
						<s:textfield name="tbCustomer.customerCode" />
					</td>
					<td>
						客户姓名
					</td>
					<td>
						
						<s:textfield name="tbCustomer.customerName" />
					</td>
				</tr>
				<tr>
					<td>新客户号</td>
					<td>
						<s:hidden id="tbCustomerId"/>
						<s:textfield id="tbCustomerCode" onblur="pickTbCustomer();"/>
					</td>
					<td>新客户名称</td>
					<td>
						<s:textfield id="tbCustomerName" />
					</td>
				</tr>
				
				<tr>

					<td align="center" colspan="2">
						<input type="button" value="确定" onclick="transferCar();"/>
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

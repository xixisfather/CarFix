<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>客户修改</title>
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
		<s:form action="tbCustomerUpdateAction.action" onsubmit="return customerFormValidate()">
			<table>
				<tr>
					<td><s:hidden name="tbCustomer.id"/></td>
				</tr>
				<tr>
					<td>客户号</td>
					<td>
						<s:textfield name="tbCustomer.customerCode" readonly="true"/>
						<font color="blue">#</font>
					</td>
					<td>
						客户姓名
					</td>
					<td>
						<!-- 
						commom.js中方法sendChn(中文输入框ID,拼音输入框ID);
						 -->
						<s:textfield id="customerName" name="tbCustomer.customerName" onblur="sendChn('customerName','pinyinCode');" maxlength="50"/>
					
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>固定电话</td>
					<td>
						<s:textfield name="tbCustomer.phone" maxlength="8"/>
					</td>
					<td>拼音代码</td>
					<td>
						<s:textfield id="pinyinCode" name="tbCustomer.pinyinCode" readonly="true"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td>
						<s:textfield id="telephone" name="tbCustomer.telephone" maxlength="11"/>
						<font color="red">*</font>
					</td>
					<td>联系地址</td>
					<td>
						<s:textfield name="tbCustomer.address" maxlength="50"/>
						
					</td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td>
						<s:textfield name="tbCustomer.zipCode" maxlength="6"/>
					</td>
					<td>单位帐号</td>
					<td>
						<s:textfield name="tbCustomer.companyAccountCode" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>单位税号</td>
					<td>
						<s:textfield name="tbCustomer.companyTaxCode" maxlength="50"/>
					</td>
					<td>法人代表</td>
					<td>
						<s:textfield name="tbCustomer.lawPresent" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>联系人</td>
					<td>
						<s:textfield id="contractPerson" name="tbCustomer.contractPerson" maxlength="10"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>客户类别</td>
					<td>
						<s:select name="tbCustomer.customerProperty" list="#request.customerPropertyMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>客户类型</td>
					<td>
						<s:select name="tbCustomer.tmCustomerType.id" list="#request.customerTypeMap" emptyOption="true" listKey="key" listValue="value" />
					</td>
				</tr>
				<tr>
					<td>销售类型</td>
					<td>
						<s:select name="tbCustomer.tmSoleType.id" list="#request.tmSoleTypeMap" emptyOption="true" listKey="key" listValue="value" />
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

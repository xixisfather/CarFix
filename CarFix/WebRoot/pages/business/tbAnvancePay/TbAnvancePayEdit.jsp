<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理类型修改</title>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/css/tableIcon.css" />
		<script type="text/javascript" src="../../../js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="../../../ext/js/ext-base.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-all.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="TbAnvancePay.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../../../js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbAnvancePayUpdateAction.action">
			<table>
				<tr>
					<td><s:hidden name="tbAnvancePay.id"/></td>
				</tr>
				<tr>
					<td>单据号</td>
					<td>
						<s:textfield id="anvanceCode" name="tbAnvancePay.anvanceCode" readonly="true"/><font color="blue">#</font>
					</td>
					<td>牌照号</td>
					<td>
						<s:textfield id="licenseCode" name="tbAnvancePay.tbCarInfo.licenseCode" onblur="sendCarLicense();"/>
					
						<s:hidden id="carInfoId" name="tbAnvancePay.tbCarInfo.id"></s:hidden>
						<img src="../../../images/icons/find.gif" style="cursor: pointer;" onclick="openWin();"/>
					</td>
					<td>
						客户名称
					</td>
					<td>
						<s:textfield id="customerName" name="tbAnvancePay.tbCarInfo.tbCustomer.customerName" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						到款日期
					</td>
					<td>
						<s:textfield id="payDay" name="tbAnvancePay.payDay" readonly="true"></s:textfield><font color="blue">#</font>
					</td>
					<td>到款金额</td>
					<td><s:textfield id="payAmount" name="tbAnvancePay.payAmount"></s:textfield></td>
					<td>收款人</td>
					<td>
						<s:textfield id="customerName" name="tbAnvancePay.tmUser.customerName" readonly="true" value="陈春荣"></s:textfield>
						<s:hidden id="tmUserId" name="tbAnvancePay.tmUser.id" value="1"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>支付方式</td>
					<td>
						<s:select id="payPattern" name="tbAnvancePay.payPattern" list="#request.payMap" onchange="setChequeCodeDisable();"></s:select>
					</td>
					<td>
						支票号码
					</td>
					<td>
						<s:textfield id="chequeCode" name="tbAnvancePay.chequeCode" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>备注说明</td>
					<td>
						<s:textarea id="remark" name="tbAnvancePay.remark">
						
						</s:textarea>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="修改" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
<script>
	setChequeCodeDisable();
</script>
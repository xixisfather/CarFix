<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>销售类型查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessBalanceTotalShowAction.action">
			<table>
				<tr>
					<td>
						结算总金额
					</td>
					<td>
						<s:textfield id="balanceTotalAll" name="tbBusinessBalance.balanceTotalAll" disabled="true"></s:textfield>
						<font color="blue">#</font>
					</td>
				</tr>
				<tr>
					<td>
						已付金额
					</td>
					<td>
						<s:textfield id="payedAmount" name="tbBusinessBalance.payedAmount" disabled="true"></s:textfield>
						<font color="blue">#</font>
					</td>
				</tr>
				<!--预收款先不开放-->
				<!--<tr>
					<td>
						以往存结余款
					</td>
					<td>
						<s:textfield id="saveAmount" name="tbBusinessBalance.saveAmount" disabled="true"></s:textfield>
						<font color="blue">#</font>
					</td>
				</tr>
				<tr>
					<td>
						本次用结余款
					</td>
					<td>
						<s:textfield id="usedSaveAmount" name="tbBusinessBalance.usedSaveAmount" value="0.00" onblur="calcPay();"></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				-->
				<tr>
					<td>
						本次收款金额
					</td>
					<td>
						<s:textfield id="shouldPayAmount" name="tbBusinessBalance.shouldPayAmount" disabled="true"></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						付款方式
					</td>
					<td>
						<s:select id="payPattern" name="tbBusinessBalance.payPattern" list="#request.payMap" listKey="key" listValue="value" disabled="true"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						支票号码
					</td>
					<td>
						<s:textfield id="chequeCode" name="tbBusinessBalance.chequeCode" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						结清期限
					</td>
					<td>
						<s:textfield id="balanceDeadTime" name="tbBusinessBalance.balanceDeadTime" disabled="true">
							<s:param name="value"><s:date name="tbBusinessBalance.balanceDeadTime" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td>
						<s:textarea id="remark" name="tbBusinessBalance.remark" disabled="true">
						
						</s:textarea>
					</td>
				</tr>
			</table>
		</s:form>
		
	</body>
</html>
<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalanceTotal.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>



<script>
	
</script>


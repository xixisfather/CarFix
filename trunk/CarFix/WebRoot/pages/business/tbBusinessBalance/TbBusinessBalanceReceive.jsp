<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算单--委托书</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbReceiveFreeInsertAction.action" onsubmit="return validate();">
			<table>
				<tr>
					<td>
						<s:hidden id="tbFixEntrustId" name="tbBusinessBalance.tbFixEntrust.id"></s:hidden>
						<s:hidden id="tbBusinessBalanceId" name="tbBusinessBalance.id"></s:hidden>
					</td>
				</tr>
				
				<tr>
					<td>
						客户号
					</td>
					<td>
						<input type="text" id="customerCode" name="customerCode" value="<s:property value='#request.tbCustomer.customerCode'/>" readonly="true"/>
					</td>
					<td>
						客户名称
					</td>
					<td>
						<input type="text" id="customerName" name="customerName" value="<s:property value='#request.tbCustomer.customerName'/>" readonly="true"/>
						<input type="hidden" id="customerId" name="customerId" value="<s:property value='#request.tbCustomer.id'/>"/>
					</td>
				</tr>
				<tr>
					<td>
						结算总金额
					</td>
					<td>
						<s:textfield id="balanceTotalAll" name="tbBusinessBalance.balanceTotalAll" disabled="true"></s:textfield>
					</td>
					<td>
						已收款金额
					</td>
					<td>
						<s:textfield id="shouldPayAmount" name="tbBusinessBalance.shouldPayAmount" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					
					<td>
						<c:choose>
							<c:when test="${tbReceiveFree.amountType==1}">
								本次付款金额
							</c:when>
							
							<c:otherwise>
								本次减免金额
							</c:otherwise>
						</c:choose>
						
						
					</td>
					<td>
						<s:textfield id="feeAmount" name="tbReceiveFree.feeAmount" value="0.00"/>
						<s:hidden id="amountType" name="tbReceiveFree.amountType"></s:hidden>
					</td>
					<td>备注</td>
					<td>
						<s:textarea id="remark" name="tbReceiveFree.remark"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="确认"/>
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<div id='tabPlaceHolder'></div>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
	var balanceTotalAll = document.getElementById('balanceTotalAll');
		
	var shouldPayAmount = document.getElementById('shouldPayAmount');
		
	var feeAmount =document.getElementById('feeAmount');
	
	function init()
	{
		
		var amount = parseFloat(balanceTotalAll.value) - parseFloat(shouldPayAmount.value); 
	
		feeAmount.value = amount;
		
	}
	
	init();
	
	function validate(){
		
		var amount = parseFloat(balanceTotalAll.value) - parseFloat(shouldPayAmount.value); 
	
		return clearNoNum22(feeAmount,0.00,amount);
	}
	
</script>





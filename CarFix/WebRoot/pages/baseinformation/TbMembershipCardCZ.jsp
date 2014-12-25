<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会员卡充值</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/baseinformation/TbMembershipCard.js"
	charset="UTF-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
</head>

<body>
<s:form action="tbMembershipCardCZAction.action"
	onsubmit="return validateData();">

	<table>
		<tr>
			<td>会员卡号</td>

			<td><s:hidden name="tbMembershipCard.id" id="tbMembershipCardId"></s:hidden> <s:textfield
				id="cardNo" name="tbMembershipCard.cardNo" maxlength="50"
				readonly="true"></s:textfield></td>

			<td>卡种</td>
			<td><s:textfield name="tbMembershipCard.cardDesc"
				readonly="true"></s:textfield> <!--<s:select id="tmCardTypeId"
				name="tbMembershipCard.tmCardType.id" list="#request.tmCardTypeMap"
				emptyOption="false" listKey="key" listValue="value" />--></td>
		</tr>

		<tr>
			<td>客户</td>

			<td><!--<s:hidden id="customerId" name="tbMembershipCard.tbCustomer.id"></s:hidden>-->
			<s:textfield id="customerName" name="tbMembershipCard.customerName"
				readonly="true" /></td>

			<td>有效期</td>

			<td><s:textfield id="validDate"
				name="tbMembershipCard.validDate" readonly="true" size="15">
				<s:param name="value">
					<s:date name="tbMembershipCard.validDate" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield></td>

		</tr>

		<tr>
			<td>现有积分</td>
			<td><s:textfield id="cardPoint"
				name="tbMembershipCard.cardPoint" readonly="true"></s:textfield></td>
			<td>现有卡内金额</td>
			<td><s:textfield id="cardSaving"
				name="tbMembershipCard.cardSaving" readonly="true"></s:textfield></td>
		</tr>
		<tr>
			<td colspan="4">
			<hr width="100%">
			</td>
		</tr>
		<tr>
			<td>请填写充值金额</td>
			<td><s:textfield id="czje" name="tbMembershipCard.czje"
				maxlength="10" value="0" onkeyup="sendCZJE();"></s:textfield></td>
		</tr>
		
		<tr>
			<td>赠送金额:</td>
			
			<td><s:textfield id="zsje" disabled="true" value="0"/>元</td>
		</tr>
		
		<tr>
			<td>赠送积分:</td>
			
			<td><s:textfield id="zsjf" disabled="true" value="0"/>积分</td>
		</tr>
		
		<tr>
			<td>充值后金额:</td>
			<td><s:textfield id="aftJe" disabled="true"/>元</td>
		</tr>
		
		<tr>
			<td>充值后积分:</td>
			<td><s:textfield id="aftJf" disabled="true"/>积分</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">充&nbsp;值&nbsp;奖&nbsp;励&nbsp;规&nbsp;则:</td>
		</tr>

		<tr>
			<td colspan="2">
			&nbsp;&nbsp;&nbsp;&nbsp;1.一&nbsp;次&nbsp;性&nbsp;充&nbsp;值&nbsp;<font color="red">${tbMembershipCard.tmCardType.cFullMoney}</font>&nbsp;元&nbsp;送&nbsp;
			<font color="red">${tbMembershipCard.tmCardType.cGiveMoney}</font>&nbsp;元&nbsp;
			<font color="red">${tbMembershipCard.tmCardType.cGivePoint}</font>&nbsp;积&nbsp;分</td>
		</tr>

		<tr>

			<td align="center" colspan="2"><input type="submit" value="确定"/> &nbsp;&nbsp; <input type="reset"
				value="重置" /></td>

		</tr>
	</table>
</s:form>
<script language="javascript">
	function validateData() {

		var czje = document.getElementById('czje');

		if (!isNumber(czje.value)) {

			alert('请填写正确的金额');

			return false;
		}

		return true;
	}
</script>
</body>
</html>

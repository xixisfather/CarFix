<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会员卡增加</title>
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
<!--
<script type="text/javascript"
	src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js"
	charset="UTF-8"></script>  -->	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
</head>

<body onload="document.getElementById('cardNo').focus();">
<s:form action="tbMembershipCardInsertAction.action"
	onsubmit="return tbMembershipCardFormValidate();">

	<table>
		<tr>
			<td>会员卡号</td>

			<td><s:textfield id="cardNo" name="tbMembershipCard.cardNo"
				maxlength="50" onkeydown="EnterTab();"></s:textfield></td>
		</tr>
		
		<tr>
			<td>密码(<font color="red">最多6位数字</font>)</td>
			<td><s:password id="cardPassword" name="tbMembershipCard.cardPassword" maxlength="6" onkeydown="EnterTab();"></s:password>
				<font color="blue">提示客户输入完后按【确定键】</font>
			</td>
		</tr>
			
		<tr>
			<td>确认密码</td>
			<td><s:password id="cardPasswordConfirm" maxlength="6" onkeydown="EnterTab();"></s:password></td>
		</tr>
		<tr>
			<td>卡种</td>
			<td><s:select id="tmCardTypeId"
				name="tbMembershipCard.tmCardType.id" list="#request.tmCardTypeMap"
				emptyOption="false" listKey="key" listValue="value" /></td>
		</tr>

 
		<tr>
		<!--
			<td>车牌号</td>
			<td><s:hidden id="tbCarInfoId"
				name="tbMembershipCard.tbCarInfo.id"></s:hidden> <s:textfield
				id="licenseCode" name="tbMembershipCard.tbCarInfo.licenseCode"
				onblur="getInfoByCarLicense();" size="10" /> <font color="red">*</font>
			<img src="<%= request.getContextPath() %>/images/icons/find.gif"
				style="cursor: pointer;" onclick="openWin();" /></td>
 -->				
				<td>客户号：</td>
					<td>
						<s:hidden id="customerId" name="tbMembershipCard.tbCustomer.id"  />
						<s:textfield id="customerCode" onfocus="openWin();"  name="tbMembershipCard.tbCustomer.customerCode"/>
						<font color="red">*</font>
						<img src="<%= request.getContextPath() %>/images/icons/find.gif"
				style="cursor: pointer;" onclick="openWin();" />
					</td>
		</tr>
		<tr>			
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="tbMembershipCard.tbCustomer.customerName"/></td>
				
				
				
			<!-- 
			<td>客户</td>

			<td><s:hidden id="customerId"
				name="tbMembershipCard.tbCustomer.id"></s:hidden> <s:textfield
				id="customerName" onfocus="openWin();" readonly="true" /><font
				color="red">*</font></td>
			 -->
		</tr>

		<tr>
			 
			<td>有效期</td>

			<td><s:textfield id="validDate"
				name="tbMembershipCard.validDate" size="15"
				value="9999-12-31" /> <e3c:calendar for="validDate" minDay="0"
				dataFmt="yyyy-MM-dd" /></td>

		</tr>

		<tr>
			<td>初始积分</td>
			<td><s:textfield id="cardPoint"
				name="tbMembershipCard.cardPoint" maxlength="10" value="0"></s:textfield>
			</td>
		</tr>

		<tr>
			
			<td>初始卡内金额</td>
			<td><s:textfield id="cardSaving"
				name="tbMembershipCard.cardSaving" maxlength="10" value="0.00"></s:textfield>
			</td>
		</tr>
				
		<tr>
			<td colspan="2"><font color="red">注意:初始金额不参加充值优惠活动</font></td>
		</tr>

		<tr>

			<td align="center" colspan="2"><input type="button" value="确定" onclick="formSubmit();"/>
			&nbsp;&nbsp; <input type="reset" value="重置" /></td>

		</tr>
	</table>
</s:form>
</body>
</html>

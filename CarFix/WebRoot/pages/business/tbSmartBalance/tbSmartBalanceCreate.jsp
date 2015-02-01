<%@page import="com.selfsoft.baseinformation.model.TmMemberShipService"%>
<%@page import="com.selfsoft.baseinformation.model.TbMembershipCard"%>
<%@page import="com.selfsoft.framework.common.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>创建快速结算</title>

		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbSmartBalance/tbSmartBalance.js" charset="UTF-8"></script>
</head>
<body onload="document.getElementById('cardNo').focus();">

<%
	Map<Long,String> payPattenMap = Constants.getPayMap();

	request.setAttribute("payPattenMap", payPattenMap);
%>

<br/>
<center>

<%if(null != request.getAttribute("errorMsg")){%>

<font color="red"><%=request.getAttribute("errorMsg")%></font>


<%} %>

<% 

String cardNo = "";

String cardTypeDesc = "";

String cardSaving = "";

if(null != request.getAttribute("tbMembershipCard")) {
	
	TbMembershipCard tbMembershipCard = (TbMembershipCard)request.getAttribute("tbMembershipCard");
	
	cardNo = tbMembershipCard.getCardNo();
	
	cardTypeDesc = tbMembershipCard.getCardDesc();
	
	cardSaving = tbMembershipCard.getCardSaving().toString();
}

%>

<s:form id="formId" action="tbSmartBalanceCreateAction.action">

<table border="0">


<tr align="left">
	
	<td>支付方式</td>
	
	<td>
		
		<s:select id="payPatten" name="tbSmartBalance.payPatten" list="#request.payPattenMap" listKey="key" listValue="value" emptyOption="false" value="5"></s:select>
					
	
	</td>
	
</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">
					<td>
						会员卡号
					</td>
					<td>
						
						<input type="text" id="cardNo" name="cardNo" onkeydown="EnterTab();" value="<%=cardNo%>"/>
					</td>
					
					<td>	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入密码
						
						<input type="password" id="cardPassword" name="cardPassword" onblur="getMemberShipCardInfo();" maxlength="6" onkeydown="EnterTab();"/>
						<font color="blue">提示客户输入完后按【确定键】</font>
						&nbsp;&nbsp;
						<font color="red">*
						
						</font>
						
						
					</td>
</tr>

<tr>
<tr><td>&nbsp;</td></tr>
</tr>

<tr align="left">
	<td>会员卡种类</td><td><input type="text" id="cardType" name="cardType" readonly="readonly" value="<%=cardTypeDesc%>"></td>
	
</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">
	<td>当前卡内金额</td><td><input type="text" id="cardSaving" name="cardSaving" readonly="readonly" value="<%=cardSaving%>"></td>
</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">

	<td colspan="3">当前卡内赠送服务(如果使用赠送服务，项目名称将取赠送服务的名称，结算金额取0.00元)</td>
	
	<tr><td>&nbsp;</td></tr>
	
	<%
		List<TmMemberShipService> tmMemberShipServiceList = (List<TmMemberShipService>)request.getAttribute("tmMemberShipServiceList");
	
		if(null != tmMemberShipServiceList && tmMemberShipServiceList.size() > 0) {
			
			for(int i = 0 ; i < tmMemberShipServiceList.size() ; i++) {
				
				
	%>
	<tr align="left">
	
		<td><%= tmMemberShipServiceList.get(i).getServiceName()%></td><td><%=tmMemberShipServiceList.get(i).getServiceCount()%>次&nbsp;&nbsp;<input type="checkbox" id="serviceName<%=i%>" name="serviceName<%=i%>" value="<%=tmMemberShipServiceList.get(i).getServiceName() + "_" +tmMemberShipServiceList.get(i).getServiceCount()%>"/>是否使用</td>
	
		<tr><td>&nbsp;</td></tr>
	</tr>
	<%			
			}
			
		}
	
	%>
	
	

</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">
	
	<td>
		结算项目
	</td>
	
	<td>
		<input type="text" id="serviceName" name="serviceName" value="单独洗车"/>
	</td>

</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">
	
	<td>
		结算金额
	</td>
	
	<td>
		<input type="text" id="serviceMoney" name="serviceMoney" value="0.00"/>元
	</td>

</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">

	<td>
	
		车牌号
	</td>
	
	<td>
		<input type="text" id="licenseCode" name="licenseCode"/>
		<img src="<%= request.getContextPath() %>/images/icons/find.gif" style="cursor: pointer;" onclick="openWin();"/>
	</td>

</tr>

<tr><td>&nbsp;</td></tr>

<tr align="left">

	<td>施工人</td>
	
	<td><input type="text" id="workerName" name="workerName"/></td>

</tr>


<tr><td>&nbsp;</td></tr>

<tr>
	<td><input type="submit" value="确定"></td>
	
	<td><input type="reset" value="重置"></td>

</tr>



</table>



</s:form>


</center>



</body>
</html>
<script>
function getMemberShipCardInfo() {
	
	var cardNo = document.getElementById('cardNo');
	
	var cardPassword = document.getElementById('cardPassword');
	
	if(null!=cardNo.value&&''!=cardNo.value&&null!=cardPassword.value&&''!=cardPassword.value) {
		
		window.location.href = "<%= request.getContextPath() %>" + '/getMemberShipCardInfoAction.action?cardNo='+cardNo.value+'&cardPassword='+cardPassword.value;
		
	}
	else {
		
		alert('请输入卡号密码');
	}
}


</script>




<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body >
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
						<s:textfield id="shouldPayAmount" name="tbBusinessBalance.shouldPayAmount" value="0.00" onblur="calPayValidate();parentValueSet();"></s:textfield>
						<font color="red">*</font>
					</td>
					
					<td>	
						
						其中会员卡支付金额
						
						<s:if test="null==tbMembershipCard">
							<s:textfield id="cardZFJE" value="0" disabled="true"></s:textfield>
						</s:if>
						
						<s:else>
							<s:textfield id="cardZFJE" value="0"></s:textfield>
						</s:else>
						<font color="red">(请先刷会员卡)</font>
					</td>
					
				</tr>
				
				<tr>
					<td>
						会员卡号
					</td>
					<td>
						
						<s:textfield id="cardNo" name="tbMembershipCard.cardNo" onkeydown="EnterTab();"></s:textfield>
					</td>
					
					<td>	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入密码
						
						<s:password id="cardPassword" name="tbMembershipCard.cardPassword" onblur="sendCardPassToEncrypt();" maxlength="6" onkeydown="EnterTab();"></s:password>
						<font color="blue">提示客户输入完后按【确定键】</font>
						&nbsp;&nbsp;
						<font color="red">*<s:if test="#request.pass == 'false'">
							(密码错误请重新输入)
						</s:if>
						<s:elseif test="#request.pass == 'notValid'">
							(卡已失效或被挂失)
						</s:elseif>
						
						</font>
						
						
					</td>
				</tr>
				<tr>
					<td>
						会员卡种类
					</td>
					<td>
						<s:textfield id="cardType" name="tbMembershipCard.tmCardType.cardDesc" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>当前卡内金额</td>
					<td>
						<s:textfield id="cardSaving" name="tbMembershipCard.cardSaving" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>支付后卡内金额</td>
					<td>
						<s:textfield id="aftCardSaving" name="tbMembershipCard.aftCardSaving" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>当前卡内积分</td>
					<td>
						<s:textfield id="cardPoint" name="tbMembershipCard.cardPoint" disabled="true"></s:textfield>
					</td>
				</tr>
				
				<tr>
					<td>积分可抵扣金额</td>
					<td>
						<s:textfield id="dhMoneyShow" name="tbMembershipCard.dhMoney" disabled="true"></s:textfield>
						<input type="checkbox" name= "dhMoney" id="dhMoney" onclick="dhMoneyClick();"/>
					</td>
				</tr>
				
				<tr>
					<td>
						付款方式
					</td>
					<%request.setAttribute("payPattern",request.getParameter("payPattern"));%>
					<td>
						<s:select id="payPattern" name="tbBusinessBalance.payPattern" list="#request.payMap" listKey="key" listValue="value" onchange="payMethodChange();parentValueSet();" value="#request.payPattern"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						支票号码
					</td>
					<td>
						<s:textfield id="chequeCode" name="tbBusinessBalance.chequeCode" disabled="true" onblur="parentValueSet();"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						结清期限
					</td>
					<td>
						<s:textfield id="balanceDeadTime" name="tbBusinessBalance.balanceDeadTime">
						
						</s:textfield>
						<e3c:calendar for="balanceDeadTime" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td>
						<s:textarea id="remark" name="tbBusinessBalance.remark" onblur="parentValueSet();">
						
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalanceTotalXsd.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>




<script>
	setZJE();
	
	initShouldPay();
	
	new Form.Element.Observer("balanceDeadTime", 0.5, function(){
		parentValueSet();
	});
	
	new Form.Element.Observer("balanceTotalAll", 0.5, function(){
		
		initShouldPay();
		
		parentValueSet();
	});

	new Form.Element.Observer("cardZFJE", 0.5, function(){

		cardZFJEValidate();
		
	});

	if(null == document.getElementById('cardNo').value || ''==document.getElementById('cardNo').value){

		document.getElementById('cardNo').focus();
	}
	
	parentValueSet();
	
	var dhMoneyShow = document.getElementById('dhMoneyShow').value;
	
	document.getElementById('dhMoney').value = dhMoneyShow;
	
</script>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会员卡修改</title>
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
<script type="text/javascript"
	src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js"
	charset="UTF-8"></script>	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
</head>

<body onload="document.getElementById('cardPassword').focus();">
<s:form action="tbMembershipCardResetPasswordAction.action" onsubmit="return tbMembershipCardFormPass();">

	<table>
		<tr>
			<td>会员卡号</td>

			<td>
			<s:hidden name="tbMembershipCard.id"></s:hidden>
			
			<s:textfield id="cardNo" name="tbMembershipCard.cardNo"
				maxlength="50" disabled="true"></s:textfield></td>

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

			<td align="center" colspan="2"><input type="submit" value="确定"/>
			&nbsp;&nbsp; <input type="reset" value="重置" /></td>

		</tr>
	</table>
</s:form>
</body>
</html>
<script>
	function tbMembershipCardFormPass()
	{

		var cardPassword = document.getElementById('cardPassword');
		
		var cardPasswordConfirm = document.getElementById('cardPasswordConfirm');

		var errorMsg = '';
		
		if('' == cardPassword.value){
			
			errorMsg += '请客户输入密码\n';
			
		}
		else
		{
			
			if(cardPassword.value != cardPasswordConfirm.value){
				
				errorMsg += '两次密码输入不一致\n';
			}
			
		}
		
		if ('' != errorMsg) {

			alert(errorMsg);

			return false;

		}

		return true;
	}
</script>
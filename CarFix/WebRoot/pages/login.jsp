<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="java.net.URLDecoder"%><html>
<head>
<title>思易汽修</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="description" content="思易汽修软件、思易软件 QQ:1031556362" />
<meta name="keywords" content="思易汽修软件,思易汽修,思易软件,汽修厂管理软件,汽修汽配软件,汽车维修软件,汽车4S店管理软件,汽车4S管理系统,汽车美容管理软件,汽车修理厂管理系统,汽车美容软件,汽配汽修管理软件,汽修软件,汽配软件,汽修汽配管理软件下载" />

<script type="text/javascript">
		function submitFm(){
			document.forms[0].submit();
		}
		
		function resetFm(){
			document.forms[0].reset();
		}
		function ondowncheck(){
		  if (event.keyCode ==13){
		   	 submitFm();
		  }
		}
		
	</script>



</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">

<%
	String userName = "";

	Cookie[] cookies = request.getCookies();

	if (null != cookies) {

		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];

			if (c.getName().equalsIgnoreCase(request.getRemoteAddr() + "usernameCookie")) {
				userName = URLDecoder.decode(c.getValue(), "UTF-8");
			}

		}
	}
	
	request.setAttribute("userName",userName);
%>
<!-- Save for Web Slices (login03.psd) -->
<s:form action="tmUserLoginAction.action">
	<table width="100%" height="681" border="0" cellpadding="0"
		cellspacing="0" align="center"
		background="<%=request.getContextPath()%>/images/login/bg.jpg">
		<tr>
			<td align="center">
			<table id="Table_01" width="1003" height="681" border="0"
				cellpadding="0" cellspacing="0" background="#000">
				<tr>
					<td rowspan="9"><img
						src="<%=request.getContextPath()%>/images/login/login03_01.gif"
						width="221" height="680" alt=""></td>
					<td colspan="10"><img
						src="<%=request.getContextPath()%>/images/login/login03_02.gif"
						width="782" height="202" alt=""></td>
				</tr>
				<tr>
					<td colspan="9"><img
						src="<%=request.getContextPath()%>/images/login/login03_03.gif"
						width="560" height="84" alt=""></td>
					<td rowspan="8"><img
						src="<%=request.getContextPath()%>/images/login/login03_04.gif"
						width="222" height="478" alt=""></td>
				</tr>
				<tr>
					<td colspan="3" rowspan="2"><img
						src="<%=request.getContextPath()%>/images/login/login03_05.gif"
						width="278" height="34" alt=""></td>
					<td colspan="5">
					<table border="0" cellpadding="0" cellspacing="0" width="152"
						height="25">
						<tr>
							<td>
							<s:hidden name="tmUser.userStatus" value="正常"></s:hidden>
							<s:textfield name="tmUser.userName"
								cssStyle="width:100%;height:95%" value="%{#request.userName}"></s:textfield></td>
						</tr>
					</table>
					</td>
					<td rowspan="6"><img
						src="<%=request.getContextPath()%>/images/login/login03_07.gif"
						width="130" height="168" alt=""></td>
				</tr>
				<tr>
					<td colspan="5"><img
						src="<%=request.getContextPath()%>/images/login/login03_08.gif"
						width="152" height="9" alt=""></td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2"><img
						src="<%=request.getContextPath()%>/images/login/login03_09.gif"
						width="277" height="61" alt=""></td>
					<td colspan="5">
					<table border="0" cellpadding="0" cellspacing="0" width="152"
						height="25">
						<tr>
							<td><s:password name="tmUser.password"
								onkeydown="ondowncheck();" cssStyle="width:100%;height:95%"></s:password>
							</td>
						</tr>
					</table>
					</td>
					<td rowspan="4"><img
						src="<%=request.getContextPath()%>/images/login/login03_11.gif"
						width="1" height="134" alt=""></td>
				</tr>
				<tr>
					<td colspan="5"><img
						src="<%=request.getContextPath()%>/images/login/login03_12.gif"
						width="152" height="36" alt=""></td>
				</tr>
				<tr>
					<td rowspan="2"><img
						src="<%=request.getContextPath()%>/images/login/login03_13.gif"
						width="251" height="73" alt=""></td>
					<td colspan="3"><img
						src="<%=request.getContextPath()%>/images/login/login03_14.gif"
						width="60" height="21" alt="" style="cursor: pointer"
						onclick="submitFm()"></td>
					<td rowspan="2"><img
						src="<%=request.getContextPath()%>/images/login/login03_15.gif"
						width="34" height="73" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/login03_16.gif"
						width="60" height="21" alt="" style="cursor: pointer"
						onclick="resetFm()"></td>
					<td rowspan="2"><img
						src="<%=request.getContextPath()%>/images/login/login03_17.gif"
						width="24" height="73" alt=""></td>
				</tr>
				<tr>
					<td colspan="3"><img
						src="<%=request.getContextPath()%>/images/login/login03_18.gif"
						width="60" height="52" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/login03_19.gif"
						width="60" height="52" alt=""></td>
				</tr>
				<tr>
					<td colspan="9"><img
						src="<%=request.getContextPath()%>/images/login/login03_20.gif"
						width="560" height="226" alt=""></td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="221" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="251" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="26" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="1" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="33" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="34" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="60" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="24" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="1" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="130" height="1" alt=""></td>
					<td><img
						src="<%=request.getContextPath()%>/images/login/spacer.gif"
						width="222" height="1" alt=""></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<!-- End Save for Web Slices -->
</body>
</html>

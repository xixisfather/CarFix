<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.business.model.TbBusinessBalance"%>
<%@page import="com.selfsoft.baseinformation.service.ITbCarInfoService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.selfsoft.baseinformation.model.TbCarInfo"%>
<%@page import="com.selfsoft.framework.common.CommonMethod"%>
<%@page import="com.selfsoft.secrity.service.ITmCompanyService"%>
<%@page import="com.selfsoft.secrity.model.TmCompany"%>
<%@page import="com.selfsoft.business.model.TbFixShare"%><html>
<head>
<title>修理工工时打印</title>

<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 575px;
	height: 30px;
	z-index: 1;
	left: 75px;
	top: -1px;
	font-size: 16px;
	font-weight: bold;
	text-align: left;
}

#apDiv1 center {
	font-weight: bold;
	font-size: 24px;
}

#apDiv2 {
	position: absolute;
	width: 646px;
	height: 145px;
	z-index: 2;
	left: 5px;
	top: 65px;
	font-size: 12px;
}

#apDiv3 {
	position: absolute;
	width: 652px;
	height: 31px;
	z-index: 3;
	top: 30px;
	left: 4px;
	font-size: 12px;
}

@media print {
	.btn {
		display: none;
	}
}

@CHARSET "UTF-8";

td {
	font-family: "宋体";
	font-size: 12px;
}

th {
	font-family: "宋体";
	font-size: 12px;
}

body {
	font-family: "宋体";
	font-size: 12px;
}
-->
</style>

</head>

<body>
<%
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"classpath:applicationContext*.xml");

	ITmCompanyService tmCompanyService = (ITmCompanyService) appContext
			.getBean("tmCompanyService");

	TmCompany t = tmCompanyService.findAll().get(0);

	
%>

<div id="apDiv1"><%=t.getCompanyName()%>修理工工时统计报表&nbsp;&nbsp;<span
	class="btn"><font color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<div id="apDiv2">
<table width="800" border="0">
	<tr>
		<td>序号</td>
		<td>派工时间</td>
		<td>修理员</td>
		<td>工位代码</td>
		<td>工位名称</td>
		<td>修理工时</td>
		<td>派工费</td>
		<td>修理工时费</td>
		<td>修理日期</td>
		<td>车牌号</td>
	</tr>
	<%
		List<TbFixShare> resultsInSessionList = (List<TbFixShare>) session
				.getAttribute("resultsInSession");

		if (null != resultsInSessionList && resultsInSessionList.size() > 0) {

			int i = 1;

			for (TbFixShare tbFixShare : resultsInSessionList) {
	%>
	<tr height="30">
		<td><%=i%></td>
		<td><%=tbFixShare.getSendHour()%></td>
		<td><%=tbFixShare.getTmUser().getUserRealName()%></td>
		<td><%=tbFixShare.getTbFixEntrustContent()
							.getTbWorkingInfo().getStationCode()%></td>
		<td><%=tbFixShare.getTbFixEntrustContent()
							.getStationName()%></td>
		<td><%=tbFixShare.getTbFixEntrustContent().getFixHour()%></td>
		<td><%=tbFixShare.getWorkPrice()%></td>
		<td><%=tbFixShare.getTbFixEntrustContent()
							.getWorkingHourPrice()%></td>
		<td><%=CommonMethod.parseDateToString(tbFixShare
							.getTbFixEntrustContent().getTbFixEntrust()
							.getFixDate(), "yyyy-MM-dd HH:ss:mm")%></td>
		<td><%=tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getTbCarInfo().getLicenseCode()%></td>
	</tr>
	<%
		i++;
			}

		}
	%>


</table>
<p>&nbsp;</p>
<table width="641" border="0">
	<tr>
		<td>修理工</td>
		<td>派工时间合计</td>
		
		<td>派工费合计</td>
		<td>修理工时费合计</td>
	</tr>
	<%
		List<TbFixShare> groupResults = (List<TbFixShare>) session
				.getAttribute("groupResultsInSession");

		if (null != groupResults && groupResults.size() > 0) {

			for (TbFixShare tbFixShare : groupResults) {
	%>
	<tr>
		<td><%=tbFixShare.getFixPersonName()%></td>
		<td><%=tbFixShare.getSumSendHour()%></td>
		
		<td><%=tbFixShare.getSumWorkPriceD()%></td>
		<td><%=tbFixShare.getSumFixPriceD()%></td>
	</tr>
	<%
		}

		}
	%>

</table>

<table>
	<s:iterator value="#session.workTypeHourPriceVosInSession" id="wt">
		<tr>
			<td>${wt.workTypeName }</td>
		</tr>
		<tr>
			<td>派工总工时：&nbsp;${wt.totalSendHourD }&nbsp;&nbsp;</td>
			<td>派工总额：&nbsp;${wt.totalSendPriceD }&nbsp;&nbsp;</td>
			<td>修理总工时：&nbsp;${wt.totalFixHourD }&nbsp;&nbsp;</td>
			<td>修理总额：&nbsp;${wt.totalFixPriceD }&nbsp;&nbsp;</td>
		</tr>
	</s:iterator>

</table>
<p>&nbsp;</p>
</div>
<%
TbFixShare _tbFixShare = (TbFixShare) session
.getAttribute("tbFixShareInSession");

%>
<div id="apDiv3">查询日期 &nbsp;&nbsp; 

	<%=
		CommonMethod.parseDateToString(null ==_tbFixShare ? null : _tbFixShare
						.getTbFixEntrustContent().getTbFixEntrust()
						.getFixDateStart(), "yyyy-MM-dd")%>&nbsp;&nbsp;至&nbsp;&nbsp;  <%=CommonMethod.parseDateToString(null ==_tbFixShare ? null :_tbFixShare
						.getTbFixEntrustContent().getTbFixEntrust()
						.getFixDateEnd(), "yyyy-MM-dd")%>
						
</div>
</body>
</html>



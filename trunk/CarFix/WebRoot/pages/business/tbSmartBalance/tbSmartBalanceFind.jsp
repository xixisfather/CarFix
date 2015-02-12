<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/pages/business/tbSmartBalance/TbSmartBalance.js"
	charset="UTF-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/global.css" />

</head>
<body>

<s:form action="tbSmartBalanceFindAction.action">
		
		<table>
		
			<tr>
				<td>支付方式</td>
				<td>
					<s:select id="payPatten" name="tbSmartBalance.payPatten" list="#request.payPattenMap" listKey="key" listValue="value" emptyOption="false"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>施工人</td>
				<td>
					
					<s:textfield id="workerName" name="tbSmartBalance.workerName"/>
				
				</td>
			
			</tr>	
			
			<tr>
				<td>
					结算日期
				</td>
				
				<td><s:textfield id="balanceDateStart"
						name="tbSmartBalance.balanceDateStart">

						<s:param name="value">
							<s:date name="tbSmartBalance.balanceDateStart" format="yyyy-MM-dd" />
						</s:param>

					</s:textfield> <e3c:calendar for="balanceDateStart" dataFmt="yyyy-MM-dd" /></td>
				<td>至</td>
				<td><s:textfield id="balanceDateEnd" name="tbSmartBalance.balanceDateEnd">

						<s:param name="value">
							<s:date name="tbSmartBalance.balanceDateEnd" format="yyyy-MM-dd" />
						</s:param>


					</s:textfield> <e3c:calendar for="balanceDateEnd" dataFmt="yyyy-MM-dd" /></td>
			
			</tr>

			<tr>
				<td colspan="6" align="center"><input type="submit" value="查询"
					/> &nbsp;&nbsp; <input
					type="reset" value="重置" /></td>
			</tr>
		</table>
		
	</s:form>


	<e3t:table id="tbSmartBalanceTable" uri="tbSmartBalanceFindAction.action"
		var="tbSmartBalance" scope="request" items="tbSmartBalanceList"
		mode="ajax" toolbarPosition="bottom" skin="E3002" pageSize="10"
		width="1000" height="340" caption="洗车单">

	<e3t:column property="no" title="操作" sortable="false" width="100">
		
		<a href="#" onclick="javascript:editObject('${tbSmartBalance.id}','<%= request.getContextPath() %>/pages/business/tbSmartBalance/tbSmartBalancePrintAction.action',600,300);">打印</a>
	
	</e3t:column>
	
	<e3t:column property="serviceName" title="结算项目" />
	
	<e3t:column property="serviceMoney" title="结算金额" />
	
	<e3t:column property="payPattenShow" title="支付方式" />
	
	<e3t:column property="cardNo" title="会员卡号" />
	
	<e3t:column property="cardSaving" title="余额" />
	
	<e3t:column property="workerName" title="施工人" />
		
	<e3t:column property="balanceDate" title="结算日期" width="120">
		<fmt:formatDate value="${tbSmartBalance.balanceDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	</e3t:column>

	<e3t:column property="licenseCode" title="车牌号" />

	<e3t:column property="useCardServiceShow" title="是否使用赠送服务" />


	</e3t:table>

</body>
</html>
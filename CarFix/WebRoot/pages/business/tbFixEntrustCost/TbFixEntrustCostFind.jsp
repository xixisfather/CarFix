<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>单据成本查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrustCost/TbFixEntrustCost.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbFixEntrustCostFindAction.action">
			<table>
				
				<tr>
					<td>委托书号</td>
					<td><s:textfield id="entrustCode" name="tbFixEntrustCost.tbFixEntrust.entrustCode"/></td>
					
				
					<td>车牌号</td>
				
					<td><s:textfield id="licenseCode" name="tbFixEntrustCost.tbFixEntrust.tbCarInfo.licenseCode"/></td>
				</tr>
				<tr>
					<td>修理日期</td>
					<td>
						<s:textfield id="fixDateStart" name="tbFixEntrustCost.tbFixEntrust.fixDateStart">
						
							<s:param name="value"><s:date name="tbFixEntrustCost.tbFixEntrust.fixDateStart" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
						<e3c:calendar for="fixDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>	
						<s:textfield id="fixDateEnd" name="tbFixEntrustCost.tbFixEntrust.fixDateEnd">
						
							<s:param name="value"><s:date name="tbFixEntrustCost.tbFixEntrust.fixDateEnd" format="yyyy-MM-dd"/></s:param>
						
						
						</s:textfield>
						
						
						
						<e3c:calendar for="fixDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbFixEntrustCostTableQuery();"/>
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbFixEntrustCostTable" uri="tbFixEntrustCostFindAction.action" var="tbFixEntrustCost"
			scope="request" items="tbFixEntrustCostList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="单据成本">
			<e3t:column property="no" title="操作" sortable="false" width="120">
				<a href="javascript:editObject('${tbFixEntrustCost.id}','tbFixEntrustCostForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbFixEntrustCost.id}','tbFixEntrustCostDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="entrustCode" title="委托书号">
				${tbFixEntrustCost.tbFixEntrust.entrustCode}
			</e3t:column>
			<e3t:column property="fixDate" title="修理日期" width="120">
				<fmt:formatDate value="${tbFixEntrustCost.tbFixEntrust.fixDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="customerName" title="客户姓名">
				${tbFixEntrustCost.tbFixEntrust.tbCustomer.customerName}
			</e3t:column>	
			<e3t:column property="licenseCode" title="车牌号">
				${tbFixEntrustCost.tbFixEntrust.tbCarInfo.licenseCode}
			</e3t:column>
			<e3t:column property="costType" title="成本类型">
				${tbFixEntrustCost.costType}
			</e3t:column>
			<e3t:column property="costPrice" title="成本金额">
				${tbFixEntrustCost.costPrice}
			</e3t:column>

		</e3t:table>
		
		总计:${request.d1}
	</body>
</html>

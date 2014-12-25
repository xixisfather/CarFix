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
		<title>客户满意信息查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/visit/TbReturnVisit.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbReturnVisitFindAction.action">
			<table>
				<s:hidden id="returnType" name="tbReturnVisit.returnType" value="2"></s:hidden>
				<tr>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield id="licenseCode" name="tbReturnVisit.tbCarInfo.licenseCode"/>
					</td>
					
					<td>
						登记日期从
						<s:textfield id="datefrom" name="tbReturnVisit.visitDateFrom"/>
						<e3c:calendar for="datefrom" dataFmt="yyyy-MM-dd"/>
						至
						<s:textfield id="dateto" name="tbReturnVisit.visitDateTo"/>
						<e3c:calendar for="dateto" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>	
				
				<tr>
					
					<td colspan="4" align="center">
					
						<input type="button" value="查询"
							onclick="tbReturnVisitTableQuery();" />
						
						<input type="reset" value="重置"/>
						
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tbReturnVisitTable" uri="tbReturnVisitFindAction.action" var="tbReturnVisit"
			scope="request" items="tbReturnVisitList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="400" caption="满意信息">
			<e3t:column property="licenseCode" beanProperty="tbCarInfo.licenseCode" title="牌照号" />
			<e3t:column property="customerCode" beanProperty="tbCarInfo.tbCustomer.customerCode" title="客户号" />
			<e3t:column property="customerName" beanProperty="tbCarInfo.tbCustomer.customerName" title="客户姓名" />
			<e3t:column property="phone" beanProperty="tbCarInfo.tbCustomer.phone" title="固定电话"/>
			<e3t:column property="telephone" beanProperty="tbCarInfo.tbCustomer.telephone" title="手机号码"/>
			<e3t:column property="address" beanProperty="tbCarInfo.tbCustomer.address" title="联系地址"/>
			<e3t:column property="zipCode" beanProperty="tbCarInfo.tbCustomer.zipCode" title="邮政编码"/>
			<e3t:column property="visitRemark" title="回访记录"/>
			<e3t:column property="visitDate" title="回访日期">
				<fmt:formatDate value="${tbReturnVisit.visitDate}" pattern="yyyy-MM-dd HH:mm:dd"/>
			</e3t:column>
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:editObject('${tbReturnVisit.id}','tbReturnVisitForwardPageAction!forwardPage.action',800,500);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbReturnVisit.id}','tbReturnVisitDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body>
</html>

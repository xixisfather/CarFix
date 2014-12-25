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
		<title>维修回访信息查询</title>
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
		<s:form action="tbCarInfoWXHFAction.action">
			<table>
				<tr>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield id="licenseCode1" name="tbCarInfo.licenseCode"/>
					</td>
					
					<td>
						最后修理日期
						<s:textfield id="last" name="tbCarInfo.lastFixDayFrom"/>
						<e3c:calendar for="last" dataFmt="yyyy-MM-dd"/>
						至
						<s:textfield id="to" name="tbCarInfo.lastFixDayTo"/>
						<e3c:calendar for="to" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td  colspan="4" align="center">
						<input type="button" value="查询"
							onclick="tbCarInfoWXHFTableQuery();" />
						
						<input type="reset" value="重置"/>
							
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbCarInfoWXHFTable" uri="tbCarInfoWXHFAction.action" var="tbCarInfo"
			scope="request" items="tbCarInfoWXHF" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1200"
			height="200" caption="维修回访车辆信息">
			
			<e3t:column property="no1" title="操作">
				<a href="javascript:forwardPage('${tbCarInfo.entrustId}','tbFixEntrustViewAction.action','wxhf&carId=${tbCarInfo.id}&licenseCode=${tbCarInfo.licenseCode}',600,300);"><font color="blue">进行回访</font></a>
			</e3t:column>
			
			<e3t:column property="lastFixDay" title="上次修理时间" width="120">
				<fmt:formatDate value="${tbCarInfo.lastFixDay}" pattern="yyyy-MM-dd HH:mm:dd"/>
			</e3t:column>
			
			<e3t:column property="entrustCode" title="委托书号"/>
			<e3t:column property="licenseCode" title="牌照号" />
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="phone" title="固定电话"/>
			<e3t:column property="telephone" title="手机号码"/>
			<e3t:column property="address" title="联系地址"/>
			<e3t:column property="zipCode" title="邮政编码"/>
		</e3t:table>
		
		<s:form action="tbReturnVisitFindAction.action">
			<table>
				<s:hidden id="returnType" name="tbReturnVisit.returnType" value="4"></s:hidden>
				<tr>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield id="licenseCode2" name="tbReturnVisit.tbCarInfo.licenseCode"/>
					</td>
					
					<td>
						回访日期从
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
							onclick="tbReturnVisit2TableQuery();" />
						
						<input type="reset" value="重置"/>
						
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tbReturnVisit2Table" uri="tbReturnVisitFindAction.action" var="tbReturnVisit"
			scope="request" items="tbReturnVisitList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1200"
			height="200" caption="回访信息">
			<e3t:column property="no1"title="委托书号">
				${tbReturnVisit.tbFixEntrust.entrustCode}
			</e3t:column>
			<e3t:column property="licenseCode" beanProperty="tbCarInfo.licenseCode" title="牌照号" />
			<e3t:column property="customerCode" beanProperty="tbCarInfo.tbCustomer.customerCode" title="客户号" />
			<e3t:column property="customerName" beanProperty="tbCarInfo.tbCustomer.customerName" title="客户姓名" />
			<e3t:column property="phone" beanProperty="tbCarInfo.tbCustomer.phone" title="固定电话"/>
			<e3t:column property="telephone" beanProperty="tbCarInfo.tbCustomer.telephone" title="手机号码"/>
			<e3t:column property="address" beanProperty="tbCarInfo.tbCustomer.address" title="联系地址"/>
			<e3t:column property="zipCode" beanProperty="tbCarInfo.tbCustomer.zipCode" title="邮政编码"/>
			<e3t:column property="visitRemark" title="回访记录"/>
			<e3t:column property="visitDate" title="回访日期" width="120">
				<fmt:formatDate value="${tbReturnVisit.visitDate}" pattern="yyyy-MM-dd HH:mm:dd"/>
			</e3t:column>
			<e3t:column property="no" title="操作" sortable="false">
				
				<a href="javascript:forwardPage('${tbReturnVisit.entrustId}','tbFixEntrustViewAction.action','wxhf&carId=${tbReturnVisit.tbCarInfo.id}&licenseCode=${tbReturnVisit.tbCarInfo.licenseCode}&returnVisitId=${tbReturnVisit.id}',600,300);"><font color="blue">修改</font></a>
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

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
		<title>维修预约查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbBook/TbBook.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBookFindAction.action">
			<table>
				<tr>
					<td>
						预约单号
					</td>
					<td>
						<s:textfield name="tbBook.bookCode"></s:textfield>
					</td>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield name="tbBook.licenseCode"></s:textfield>
					</td>
					<td>
						登记日期
					</td>
					<td>
						<s:textfield id="registerDateStart" name="tbBook.registerDateStart"
							/>
						<e3c:calendar for="registerDateStart" dataFmt="yyyy-MM-dd 00:00:00" />
						至
						<s:textfield id="registerDateEnd" name="tbBook.registerDateEnd"
							/>
						<e3c:calendar for="registerDateEnd" dataFmt="yyyy-MM-dd 23:59:59" />
					</td>
				</tr>
				<tr>
					<td>
						是否履约
					</td>
					<td>
						<s:select name="tbBook.isCome" list="#request.isNoMap" emptyOption="true" listKey="key" listValue="value"></s:select>
					</td>
					<td>
						服务顾问
					</td>
					<td>
						<s:select id="tmUserId" name="tbBook.tmUser.id"
							list="#request.tmUserMap" listKey="key" listValue="value"
							emptyOption="true" />
						
					</td>
					<td>
						约定修理时间
					</td>
					<td>
						<s:textfield id="planFixTimeStart" name="tbBook.planFixTimeStart"
							readonly="true" />
						<e3c:calendar for="planFixTimeStart" dataFmt="yyyy-MM-dd 00:00:00" />
						至
						<s:textfield id="planFixTimeEnd" name="tbBook.planFixTimeEnd"
							readonly="true" />
						<e3c:calendar for="planFixTimeEnd" dataFmt="yyyy-MM-dd 23:59:59" />
					</td>
					
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询"
							onclick="tbBookTableQuery();" />
						<input type="reset" value="重置">	
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBookTable" uri="tbBookFindAction.action" var="tbBook"
			scope="request" items="tbBookList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
			height="360" caption="维修预约">
			<e3t:column property="no" title="操作"
				sortable="false">
				<!-- 
				<a href="javascript:tbBookeepAppoint('${tbBook.id}','tbBookeepAppointAction.action')">
					<font color="blue"> 
						履约 
					</font>
				</a>
				-->
				<a href="javascript:editObject('${tbBook.id}','tbBookForwardPageAction!forwardPage.action',600,300);">
					<font color="blue"> 
						修改 
					</font>
				</a>
				  &nbsp;&nbsp;
				<c:if test="${tbBook.isCome==null}"> 
  				<a
					href="javascript:deleteObject('${tbBook.id}','tbBookDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
				</c:if>
			</e3t:column>
			
			<e3t:column property="bookCode" title="预约单号"/>
			<e3t:column property="registerDate" title="登记日期" width="120">
				<fmt:formatDate value="${tbBook.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="modelName" title="车型"/>
			<e3t:column property="customerName" title="车主"/>
			<e3t:column property="fixType" title="修理类型"/>
			<e3t:column property="planFixTime" title="约定修理时间">
				<fmt:formatDate value="${tbBook.planFixTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="isComeShow" title="是否履约"/>
			<e3t:column property="userName" title="服务顾问"/>
			<e3t:column property="fixContent" title="维修内容"/>
			<e3t:column property="bookRemark" title="备注说明"/>
			
			
		</e3t:table>
	</body>
</html>

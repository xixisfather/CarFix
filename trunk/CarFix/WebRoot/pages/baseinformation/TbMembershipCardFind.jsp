<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>会员卡信息查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbMembershipCard.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body onload="document.getElementById('cardNo').focus();">
		<s:form action="tbMembershipCardFindAction.action">
			<table>
				<tr>
					<td>卡号</td>
					<td><s:textfield id="cardNo" name="tbMembershipCard.cardNo"/><font color="red">(请先刷卡)</font></td>
					<td>卡种</td>
					<td><s:select id="tmCardTypeId"
				name="tbMembershipCard.tmCardType.id" list="#request.tmCardTypeMap"
				emptyOption="true" listKey="key" listValue="value" /></td>
					<td>客户姓名</td>
					<td><s:textfield id="customerName" name="tbMembershipCard.customerName"/></td>
					<td>客户手机</td>
					<td><s:textfield id="telephone" name="tbMembershipCard.telephone"/></td>
					<td>是否有效</td>
					<td><s:select id="cardStatus"
				name="tbMembershipCard.cardStatus" list="#request.cardStatusMap"
				emptyOption="true" listKey="key" listValue="value" /></td>
				</tr>
<!--				
				<tr>
					<td>车牌号</td>
					<td>
						<s:textfield id="licenseCode" name="tbMembershipCard.licenseCode"></s:textfield>
					</td>
				</tr>
 -->				
				<tr>
					<td>
						<input type="button" value="查询"
							onclick="tbMembershipCardTableQuery();" />
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tbMembershipCardTable" uri="tbMembershipCardFindAction.action" var="tbMembershipCard"
			scope="request" items="tbMembershipCardList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
			height="360" caption="会员卡信息">
			
			<e3t:column property="no" title="操作" sortable="false" width="340">
				<a href="javascript:editObject('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action',800,500);">
					<font color="blue">修改</font>
				</a>
				<a href="javascript:forwardPage('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action','cz',800,500);">
					<font color="blue">充值</font>
				</a>
				<a href="javascript:forwardPage('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action','cjf',800,500);">
					<font color="blue">送积分</font>
				</a>
				<a href="javascript:forwardPage('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action','jfxf',800,500);">
					<font color="blue">积分消费</font>
				</a>
				<a href="javascript:operateObject('${tbMembershipCard.id}','tbMembershipCardValidAction.action','确定生效操作?');"><font color="blue">生效</font></a>
				
				<a href="javascript:operateObject('${tbMembershipCard.id}&flag=sx','tbMembershipCardDeleteAction.action','确定失效操作?');"><font color="blue">失效</font></a>
				
				<a href="javascript:operateObject('${tbMembershipCard.id}&flag=gs','tbMembershipCardDeleteAction.action','确定挂失操作?');"><font color="blue">挂失</font></a>
			
				<a href="javascript:forwardPage('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action','hk',800,500);">
					<font color="blue">换卡</font>
				</a>
				
				<a href="javascript:forwardPage('${tbMembershipCard.id}','tbMembershipCardForwardPageAction!forwardPage.action','pass',800,500);">
					<font color="blue">重置密码</font>
				</a>
				
				<a href="javascript:editObject('${tbMembershipCard.id}','<%= request.getContextPath() %>/pages/baseinformation/TbMembershipCardViewService.jsp',600,300);">
					<font color="blue">查看服务</font>
				</a>
				
			</e3t:column>
			
			<e3t:column property="cardStatusShow" title="当前状态" />
			
			<e3t:column property="cardNo" title="会员卡号" />
		
			<e3t:column property="customerCode" title="客户号" />
			
			<e3t:column property="customerName" title="客户姓名" />
			
			<e3t:column property="telephone" title="手机号码" />
			
			<e3t:column property="cardDesc" title="卡种" />
			
			<e3t:column property="cardPoint" title="卡内积分" />
			
			<e3t:column property="cardSaving" title="卡内金额" />
			
			<e3t:column property="createDate" title="创建日期" width="120">
				<fmt:formatDate value="${tbMembershipCard.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			
			<e3t:column property="validDate" title="有效期" />
		</e3t:table>
	</body>
</html>

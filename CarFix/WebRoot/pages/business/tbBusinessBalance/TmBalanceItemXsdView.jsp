<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.math.BigDecimal"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>结算代码明细</title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	</head>
  
  	<body>
    	<s:form action="tmBalanceItemCalcXsdAction.action">
			<s:hidden id="tmBalanceId" name="tmBalanceItem.tmBalance.id" value="%{#request.tmBalanceId}"/>
		</s:form>
		<e3t:table id="tmBalanceItemTable" uri="tmBalanceItemCalcXsdAction.action" var="tmBalanceItem"
			scope="request" items="tmBalanceItemList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="850"
			height="400" caption="结算代码明细信息">
			<e3t:column property="id" hidden="true"/>
			<e3t:column property="balanceName" title="结算项目" />
			<e3t:column property="itemName" title="明细名称" />
			<e3t:column property="itemCode" title="明细代码" />
			<e3t:column property="formula" title="公式" />
			<e3t:column property="itemRemark" title="备注说明" />
			<e3t:column property="allowHandShow" title="是否手工输入" />
			<e3t:column property="no" title="项目金额">
				<input type="text" id="${tmBalanceItem.itemCode}" name="${tmBalanceItem.itemCode}" disabled="true"/> 
			</e3t:column>
		</e3t:table>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TmBalanceItemXsd.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
	
	function init()
	{
	<%
		Map calcMapReturn = (LinkedHashMap)request.getAttribute("calcMapReturn");
		
		for(Object object : calcMapReturn.keySet())
		{
			String itemCode = (String)object;
			
			BigDecimal itemValue = new BigDecimal(String.valueOf(calcMapReturn.get(object)));
	%>	
			setValues('<%=itemCode%>','<%=itemValue%>');
	<%	
		}
	%>
	}
	
	setTimeout('init()',1500);
</script>
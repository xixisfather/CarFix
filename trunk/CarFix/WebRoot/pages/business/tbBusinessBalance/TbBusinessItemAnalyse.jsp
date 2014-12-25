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
		<title>统计及付款情况</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessItemAnalyseAction.action">
			
		</s:form>
		
		<table>
			<tr valign="top">
    			<td rowspan="2">
    			
    			<e3t:table id="tbBusinessItemAnalyseTable" uri="tbBusinessItemAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_balanceItem" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="250" caption="结算情况" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="balanceItemName" title="结算项目"/>
			<e3t:column property="balanceItemAmount" title="金额"/>
		</e3t:table>
    			
    			
    			</td>
    			
    			
    			<td>
    			
    			<e3t:table id="tbBusinessNoOweTable" uri="tbBusinessItemAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_noOwe" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="125" caption="已付清" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="balanceItemName" title="销售类型"/>
			<e3t:column property="countNum" title="结算单数"/>
			<e3t:column property="balanceItemAmount" title="收款金额"/>
		</e3t:table>
    			
    			</td>
  			</tr>
 	 		
 	 		
 	 		<tr valign="top">
    			<td>
    			
    			<e3t:table id="tbBusinessOweTable" uri="tbBusinessItemAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_owe" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="125" caption="未付清" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="balanceItemName" title="销售类型"/>
			<e3t:column property="countNum" title="结算单数"/>
			<e3t:column property="balanceItemAmount" title="收款金额"/>
			<e3t:column property="balanceItemOweAmount" title="欠款金额"/>
				</e3t:table>
    			
    			</td>
  			</tr>
  			
  			<tr>
  				<td>
  					<e3t:table id="tbBusinessAllTable" uri="tbBusinessItemAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_all" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="220" caption="结算统计"
			toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="staticsShow"/>
			<e3t:column property="staticsVal"/>
				</e3t:table>
  				</td>
  			</tr>
  		</table>
  		<!--  
  		<table border="1">
  			<tr>
  				<td width="590" height="50" valign="middle">
  				
  					<table>
  						<tr>
  							<td>税后毛利润</td>
  							<td>8888</td>
  							<td>=</td>
  							<td>产值</td>
  							<td>${request.totalAll}</td>
  							<td>/1.17</td>
  							<td>-</td>
  							<td>材料成本</td>
  							<td>7777</td>
  						</tr>
  					</table>
  				
  				</td>
  			</tr>
  		</table>
  		-->
  		
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		
		<script language="javascript">
	
		</script>

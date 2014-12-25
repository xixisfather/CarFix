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
		<title>委托书统计查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbFixEntrustAnalyseAction.action">
			
		</s:form>
		<table>
			<tr>
				<td>按修理类型统计</td>
				<td>按车型统计</td>
			</tr>
			<tr>
				<td>
				<e3t:table id="statisticsFixTable" uri="tbFixEntrustAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_FixType" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="400" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="fixType" title="修理类型"/>
			<e3t:column property="countNum" title="次数"/>
		</e3t:table>
				</td>
				<td>
				
				<e3t:table id="statisticsTable" uri="tbFixEntrustAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="400" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="modelTypeCode" title="车型代码" />
			<e3t:column property="modelTypeName" title="车型名称" />
			<e3t:column property="countNum" title="台次" />
		</e3t:table>
		
				</td>
			</tr>
			<tr>
				<td>工时完成情况</td>
				<td>
				
				</td>
			</tr>
			<tr>
				<td>
				<e3t:table id="statisticsContentTable" uri="tbFixEntrustAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_Content" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="200" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="contentShow" title=""/>
			<e3t:column property="contentQuantity" title="次数" width="200"/>
		</e3t:table>
				</td>
				<td>
				<e3t:table id="statisticsSoleTypeTable" uri="tbFixEntrustAnalyseAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList_soleType" mode="ajax"
			 skin="E3002" pageSize="10" width="600"
			height="200" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="soleType" title=""/>
			<e3t:column property="hasBalance" title="已结算(笔)"/>
			<e3t:column property="noBalance" title="未结算(笔)"/>
			<e3t:column property="allBalance" title="总计"/>
			
		</e3t:table>
				</td>
			</tr>
		</table>
		
	</body>
</html>
<script>

function statisticsTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustAnalyseAction";
	pConfig.showLoadingMsg = true;
}	
	
</script>
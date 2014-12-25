<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件积压查询</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getStoreHouseSurveyStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function query(){
			var selType = document.getElementById("selType");
			document.forms["getStoreHouseSurveyStatAction"].action += "?storeStatType="+selType.value;
			document.forms["getStoreHouseSurveyStatAction"].submit();
		}
		
		
		function setParam(){
			var selType = document.getElementById("selType");
			if("${storeStatType}" != "")
				selType.value = "${storeStatType}";
		}
	</script>
	<body onload="setParam()" >
		<s:form action="getStoreHouseSurveyStatAction.action">
			<table>
				<tr>	
					<td>
						查看类型：
					</td>
					<td>
						<select id="selType" >
							<option value="1" >仓库</option>
							<option value="2" >车型</option>
						</select>
					</td>
				</tr>
			
				<tr>
					<td>
						<input type="button" onclick="query()" value="查询" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="getStoreHouseSurveyStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="仓库概貌"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" >
			<s:if test="#request.storeStatType == 1">
				<e3t:column property="houseName" title="仓库" />
			</s:if>	
			<s:if test="#request.storeStatType == 2">
				<e3t:column property="modelTypeName" title="车型" />
			</s:if>	
				<e3t:column property="partInfoCategory" title="配件种类" />
				<e3t:column property="zeroCategory" title="数量为零种类" />
				<e3t:column property="costPrice" title="成本金额" />
				<e3t:column property="sellPrice" title="销售金额" />
				<e3t:column property="liancePrice" title="借进成本" />
				<e3t:column property="loanPrice" title="借出成本" />
		</e3t:table>
	</body> 
</html>


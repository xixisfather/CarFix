<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库概貌</title>
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
			pConfig.form = "findStStorehouseSurveyAction";
			pConfig.showLoadingMsg = true;
		}
		
		
	</script>
	<body >
		<s:form action="findStStorehouseSurveyAction.action">
			<table>
				<tr>
					<td>仓库</td>
					<td><s:textfield  name="stStorehouseSurvey.houseName" id="houseName" /> </td>
				</tr>
			
				<tr>
					<td>
						<input type="button" onclick="tbPartInfoTableQuery()" value="查询" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="findStStorehouseSurveyAction.action" var="tbPartInfo"
			scope="request" items="stStorehouseSurveyList" mode="ajax" caption="仓库概貌"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" >
				<e3t:column property="houseName" title="仓库" />
				<e3t:column property="partCategory" title="配件种类" />
				<e3t:column property="costPrice" title="成本金额" />
				<e3t:column property="sellPrice" title="销售金额" />
				<e3t:column property="linacePrice" title="借进成本" />
				<e3t:column property="loanPrice" title="借出成本" />
		</e3t:table>
	</body> 
</html>


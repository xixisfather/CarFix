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
		<title>经营状况统计</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="businessAnalyseAction.action">
			<table>
				<tr>
					<td>统计日期</td>
					<td>
						<s:textfield id="fixDateStart" name="tbFixEntrust.fixDateStart">
							<s:param name="value"><s:date name="tbFixEntrust.fixDateStart" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						
						
						<e3c:calendar for="fixDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>	
						<s:textfield id="fixDateEnd" name="tbFixEntrust.fixDateEnd">
							<s:param name="value"><s:date name="tbFixEntrust.fixDateEnd" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						
						
						<e3c:calendar for="fixDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
					
					<td>车型</td>
					<td>
						<s:select id="tmCarModelTypeId" name="tbFixEntrust.tbCarInfo.tmCarModelType.id" list="#request.tmCarModelTypeMap" listKey="key" listValue="value" emptyOption="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="查询"/>
						&nbsp;&nbsp;
						<input type="button" value="重置" onclick="clearValue();"/>
					</td>
				</tr>
			</table>
		</s:form>
	
		<div id='tabPlaceHolder'></div>
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
	Ext.onReady(function(){
		
		var fixDateStart = document.getElementById('fixDateStart');
		
		var fixDateEnd = document.getElementById('fixDateEnd'); 
		
		var tmCarModelTypeId = document.getElementById('tmCarModelTypeId'); 
		
		TabPanel.create('tabPlaceHolder',600,
		[
			{
				id:'tbFixEntrustTab' , title:'修理及工时完成情况',disabled:false,url:'tbFixEntrustAnalyseAction.action?fixDateStart='+fixDateStart.value+'&fixDateEnd='+fixDateEnd.value+'&tmCarModelTypeId='+tmCarModelTypeId.value
			},
			{
				id:'tbBusinessBalanceTab' , title:'结算及付款情况',disabled:false,url:'tbBusinessItemAnalyseAction.action?fixDateStart='+fixDateStart.value+'&fixDateEnd='+fixDateEnd.value+'&tmCarModelTypeId='+tmCarModelTypeId.value
			}
			//,
			//{
			//	id:'tbBusinessBalanceNoFinishTab' , title:'未结算销售额',disabled:false,url:''
			//},
			//{
			//	id:'freeSymbolTab' , title:'索赔免保及返工',disabled:false,url:''
			//}
			
		]);
	});
	
	function clearValue(){
		
		var fixDateStart = document.getElementById('fixDateStart');
		
		var fixDateEnd = document.getElementById('fixDateEnd'); 
		
		var tmCarModelTypeId = document.getElementById('tmCarModelTypeId'); 
		
		fixDateStart.value = '';
		
		fixDateEnd.value = '';
		
		tmCarModelTypeId.value = '';
	
	}
		</script>

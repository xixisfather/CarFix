<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.selfsoft.business.model.TbFixEntrustContent"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理委托书查看</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:include value="TbFixEntrustViewTemplate.jsp"></s:include>
		<div id='tabPlaceHolder'></div>
	</body>
</html>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfo.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
	
	Ext.onReady(function(){
		 
		var tbFixEntrustId = document.getElementById('tbFixEntrustId');
		
		var licenseCode = document.getElementById('licenseCode');
		
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbFixEntrustContent' , title:'委托修理工位',disabled:false,url:'tbFixEntrustContentFindAction.action?tbFixEntrustId='+tbFixEntrustId.value + '&flag=wts'
				
			},
			{
				id:'partContent' , title:'预发材料',disabled:false,url:'findBespokePartContentAction.action?entrustId='+tbFixEntrustId.value
				
			}
			,
			{
				id:'currentPartContent' , title:'实发材料',disabled:false,url:'findEnstrustTbMaintainDetailAction.action?entrustId='+tbFixEntrustId.value
				
			}
			,
			{
				id:'tbBookInfo' , title:'预约信息',disabled:false,url:'tbBookQueryAction.action?licenseCode='+licenseCode.value
				
			},
			{
				id:'tbReturnVisit' , title:'回访信息',disabled:false,url:'tbReturnVisitForwardPageAction!forwardPage.action?flag=4&entrustId='+tbFixEntrustId.value+'&carId=<%=String.valueOf(request.getAttribute("carId"))%>'+'&licenseCode=<%=String.valueOf(request.getAttribute("licenseCode"))%>'+'&id=<%=String.valueOf(request.getAttribute("returnVisitId") == null ? "":request.getAttribute("returnVisitId"))%>'
				
			}
			
		]);
		
	});
	
	
	
</script>

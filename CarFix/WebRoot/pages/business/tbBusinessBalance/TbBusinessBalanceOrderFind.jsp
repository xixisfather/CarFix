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
		<title>车辆修理情况排行榜</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
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
		
		TabPanel.create('tabPlaceHolder',400,
		[
			{
				id:'customerOrder' , title:'按客户分类查询',disabled:false,url:'tbBusinessBalanceOrderFindAction.action'
			},
			{
				id:'carOrder' , title:'按车辆分类查询',disabled:false,url:'tbBusinessBalanceCarOrderFindAction.action'
			}
			
		]);
	});
		</script>

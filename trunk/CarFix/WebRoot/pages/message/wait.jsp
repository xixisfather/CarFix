<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>
	</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalance.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	
	<body  >
	页面加载中请稍候...........长时间没有响应点击标签页
		
		<br>
		<script language="javascript">
	function request(paras){ 
var url = location.href; 
var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
var paraObj = {} 
for (i=0; j=paraString[i]; i++){ 
paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
} 
var returnValue = paraObj[paras.toLowerCase()]; 
if(typeof(returnValue)=="undefined"){ 
return ""; 
}else{ 
return returnValue; 
} 
}

	var type=request('type');
	var direct=request('direct')
	if('xsd'==direct)
	{
		parent.initTabXsd(type);
	}
	else
	{
		parent.initTab(type);
	}
	
</script>
	</body>
</html>

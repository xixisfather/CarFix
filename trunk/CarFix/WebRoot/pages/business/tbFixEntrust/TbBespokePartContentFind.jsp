<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>预约配件列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tbBespokeTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbBespokeTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findDrawAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		
		<e3t:table id="tbBespokeTable" uri="findDrawAction.action" var="stockOut"
			scope="request" items="bespokePartContents" mode="ajax" caption="领用单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="partQuantity" title="预约数量" />
			<e3t:column property="houseName" beanProperty="tbPartInfo.tmStoreHouse.houseName"  title="仓库" />
			<e3t:column property="partCode" beanProperty="tbPartInfo.partCode"  title="配件代码" />
			<e3t:column property="partName" beanProperty="tbPartInfo.partName"  title="配件名称" />
			<e3t:column property="unitName" beanProperty="tbPartInfo.tmUnit.unitName"  title="计量单位" />
			<e3t:column property="storeQuantity" beanProperty="tbPartInfo.storeQuantity" title="库存" />
			<e3t:column property="sellPrice" beanProperty="tbPartInfo.sellPrice" title="销售价" />
		</e3t:table>
		
	</body> 
</html>

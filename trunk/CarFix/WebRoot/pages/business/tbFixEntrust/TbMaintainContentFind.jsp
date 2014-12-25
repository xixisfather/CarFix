<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>维修发料列表</title>
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
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tbMaintainTableConfigHandler(pConfig) {
			pConfig.tbar = [
			// pConfig.autoExpandColumn='no';
			]
		}
		
		function tbMaintainTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findEnstrustTbMaintainDetailAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		
		
	</script>
	<body>
		<s:form action="findEnstrustTbMaintainDetailAction.action" >
			<s:hidden  name="entrustId" ></s:hidden>
		</s:form>
		
		实发材料费总计:${maintianTotalPrice}
		<e3t:table id="tbMaintainTable" uri="findEnstrustTbMaintainDetailAction.action" var="maintain"
			scope="request" items="maintianvos" mode="ajax" caption="维修发料列表"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="1100"
			height="320" >
			<e3t:column property="maintainCode" title="维修发料单号" />
			<e3t:column property="houseName"  title="仓库" />
			<e3t:column property="partCode"	 title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="70" property="unitName" title="计量单位" />
			<e3t:column width="70" property="partQuantity" title="发料数量" />
			<e3t:column width="70" property="price" title="发料价" />
			<e3t:column width="70" property="jxPrice" title="发料金额" />
			<e3t:column width="70" property="userRealName" title="领用人" />
			<e3t:column width="100" property="zl" title="账类" />
			<e3t:column width="100" property="xmlx" title="项目类型" />
			<e3t:column width="100" property="projectType" title="维修项目类型" />
		</e3t:table>
		
	</body> 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>车辆保险提醒</title>
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
		
		
		function tmCarInsuranceAlertTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmCarInsuranceAlertTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmCarInsuranceAlertTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findTmCarInsuranceAlertAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findTmCarInsuranceAlertAction.action" >
			<table>
				
			</table>
		
		</s:form>
		<e3t:table id="tmCarInsuranceAlertTable" uri="findTmCarInsuranceAlertAction.action" var="carAlert"
			scope="request" items="insuranceCarList" mode="ajax"  caption="车辆保险提醒列表"
			toolbarPosition="bottom" skin="E3002" pageSize="100" width="700"
			height="320" >
			<e3t:column property="remindInsuranceDate"  title="提醒保险日期" >
				<fmt:formatDate value="${carAlert.remindInsuranceDate}" />
			</e3t:column>
			<e3t:column property="licenseCode" title="牌照号码" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="phone" title="固定电话"/>
			<e3t:column property="telephone" title="手机号码"/>
			<e3t:column property="address" title="联系地址"/>
			<e3t:column property="zipCode" title="邮政编码"/>
		</e3t:table>
		
	</body> 
</html>

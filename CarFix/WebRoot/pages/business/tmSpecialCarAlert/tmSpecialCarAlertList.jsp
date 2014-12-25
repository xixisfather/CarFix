<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>特殊车辆提醒</title>
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
		
		
		function tmSpecialCarAlertTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增',
				iconCls : 'addIcon',
				handler : function() {
					addObject('tmSpecialCarAlertPageAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmSpecialCarAlertTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmSpecialCarAlertTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findTmSpecialCarAlertAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findTmSpecialCarAlertAction.action" >
			<table>
				<tr>
					<td>牌照号：</td>
					<td><s:textfield  name="tmSpecialCarAlert.tbCarInfo.licenseCode" ></s:textfield></td>
					<td>提醒次数：</td>
					<td>
						<s:select name="tmSpecialCarAlert.alertCount" emptyOption="true" list="#request.alertCountMap" key="key" value="value" ></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmSpecialCarAlertTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tmSpecialCarAlertTable" uri="findTmSpecialCarAlertAction.action" var="carAlert"
			scope="request" items="tmSpecialCarAlertList" mode="ajax"  caption="特殊车辆提醒列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="licenseCode" beanProperty="tbCarInfo.licenseCode"  title="牌照号码" />
			<e3t:column property="beginDate"  title="开始日期" >
				<fmt:formatDate value="${carAlert.beginDate}" />
			</e3t:column>
			<e3t:column property="endDate"  title="结束日期" >
				<fmt:formatDate value="${carAlert.endDate}" />
			</e3t:column>
			<e3t:column property="alertCountName"  title="提醒次数" />
			<e3t:column property="alertRangeName" width="150"  title="提醒范围" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${carAlert.id}','tmSpecialCarAlertPageAction.action',800,450)">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${carAlert.id}','deleteTmSpecialCarAlertAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>

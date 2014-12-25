<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>内部领用出库列表</title>
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
		
		
		function tmStockOutTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增',
				iconCls : 'addIcon',
				handler : function() {
					//window.location.href = ctx+"/initDrawPageAction.action";
					addObject('forwardInnerStockOutPageAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmStockOutTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockOutTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findInnerStockOutAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findInnerStockOutAction.action" >
			<table>
				<tr>
					<td>出库单号：</td>
					<td><s:textfield  name="tmStockOut.stockOutCode" ></s:textfield></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmStockOutTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tmStockOutTable" uri="findInnerStockOutAction.action" var="stockOut"
			scope="request" items="stockOutList" mode="ajax" caption="内部领用单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="stockOutCode" title="出库单号" />
			<e3t:column property="outTypeName" title="出库类型" />
			<e3t:column property="stockOutDate"  title="出库日期" >
				<fmt:formatDate  value="${stockOut.stockOutDate}" pattern="yyyy-MM-dd" />
			</e3t:column>
			<e3t:column property="userRealName" title="领用人" />
			<e3t:column property="totalPrice"  title="合计总价" />
			<e3t:column property="stockOutId" hidden="true" title="出库ID" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${stockOut.stockOutId}','forwardInnerStockOutPageAction.action',800,450);">
					<font color="blue">修改</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${stockOut.stockOutId}','deleteStockOutAction.action');">
					<font color="blue">删除</font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>

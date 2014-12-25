<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件移库出仓列表</title>
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
		
		
		function tmRemoveStockTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增移库单',
				iconCls : 'addIcon',
				handler : function() {
					//window.location.href = ctx+"/initRemoveStockPageAction.action";
					addObject('initRemoveStockPageAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmRemoveStockTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmRemoveStockTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findRemStockAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findRemStockAction.action" >
			<table>
				<tr>
					<td>移库出仓单号：</td>
					<td><s:textfield  name="tmRemoveStock.removeStockBill" ></s:textfield></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmRemoveStockTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tmRemoveStockTable" uri="findRemStockAction.action" var="remStock"
			scope="request" items="removeStockVos" mode="ajax" caption="移库出仓列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" >
			<e3t:column property="removeStockBill" title="移库出仓单号" />
			<e3t:column property="removeDate"  title="移出日期" >
				<fmt:formatDate  value="${remStock.removeDate}" pattern="yyyy-MM-dd" />
			</e3t:column>
			<e3t:column property="houseName" title="移出仓库" />
			
			<e3t:column property="tmstockId" hidden="true" title="移库ID" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${remStock.tmstockId}','initEditRemoveStockPageAction.action',800,450)">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${remStock.tmstockId}','deleteTmRemStockAction.action');">
					<font color="blue">删除
				    </font>
				</a>
				&nbsp;&nbsp;
				<a href="javascript:editObject('${remStock.tmstockId}','printYKCCAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a>
				
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>

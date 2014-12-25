<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<s:head theme="ajax" /> 
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	

	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tmStockInTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增采购单',
				iconCls : 'addIcon',
				handler : function() {
					//window.location.href = ctx+"/initStockInPageAction.action";
					addObject('initStockInPageAction.action',800,450);
				}
			}, '', '-', '', 
			{	
				text : '导入',
				iconCls : 'editIcon',
				handler : function() {
				
					var date = new Date();
				
					var time = date.getTime();
					
					addObject('initcgrk2ImportXlsPageAction.action?timeId='+time,600,300);
				
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmStockInTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockInTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findStockInAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form>
			<table>
				<tr>
					<td>入库单号：</td>
					<td><s:textfield  name="tmStockIn.stockInCode" ></s:textfield></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" 
							onclick="tmStockInTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tmStockInTable" uri="findStockInAction.action" var="stockIn"
			scope="request" items="stockInList" mode="ajax" caption="采购单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" >
			<e3t:column property="stockInCode" title="入库单号" />
			<e3t:column property="inTypeName" title="入库类型" />
			<e3t:column property="arriveDate"  title="到货日期" >
				<fmt:formatDate value="${stockIn.arriveDate}" />
			</e3t:column>
			<e3t:column property="indent"  title="订货单号" />
			<e3t:column property="oucherCode"  title="入库凭证" />
			<e3t:column property="totalPrice"  title="合计总价" />
			<e3t:column property="stockInId" hidden="true" title="入库ID" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${stockIn.id}','initEditStockInPageAction.action',800,450)">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${stockIn.id}','deleteStockInAction.action');">
					<font color="blue">删除
				    </font>
				</a>
				&nbsp;&nbsp;
				<a href="javascript:editObject('${stockIn.id}','printCGRKAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>

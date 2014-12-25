<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件管理</title>
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
		function tbPartInfoTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				text : '添加配件',
				iconCls : 'addIcon',
				handler : function() {
					addObject('tbPartInfoForwardAction.action',600,300);
				}
			}, '', '-', '', 
			{	
				text : '导入',
				iconCls : 'editIcon',
				handler : function() {
				
					var date = new Date();
				
					var time = date.getTime();
					
					addObject('initPjxxImportXlsPageAction.action?timeId='+time,600,300);
				
				}
			}, '', '-', '',
			{	
				text : '导出',
				iconCls : 'viewIcon',
				handler : function() {
					
					var date = new Date();
					
					var time = date.getTime();
					
					window.open('pjxxExportXlsAction.action?timeId='+time,'_blank');
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbPartInfoTable();
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tbPartInfoFindAction";
			pConfig.showLoadingMsg = true;
		}
		
		
	</script>
	<body>
		<s:form action="tbPartInfoFindAction.action">
			<table>
				<tr>	
					<td>仓库</td>
					<td><s:select name="tbPartInfo.tmStoreHouse.id" list="#request.stroeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
					<td>车辆类型</td>
					<td><s:select name="tbPartInfo.tmCarModelType.id" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/></td>
					<td>配件名称</td>
					<td><s:textfield name="tbPartInfo.partName" /></td>
				</tr>
				
				<tr>
					<td>配件代码</td>
					<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
					<td>拼音名称</td>
					<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
					<td>配件类型</td>
					<td><s:select name="tbPartInfo.tmPartType.id" list="#request.partTypeList" emptyOption="true" listKey="id" listValue="typeName"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tbPartInfoTable" uri="tbPartInfoFindAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1200"
			height="320" >
			<e3t:column width="180" property="no" title="相关操作" sortable="false">
				<a href="javascript:editObject('${tbPartInfo.id}','initUpdateCostPircePageAction.action',300,200);">
					<font color="blue">
						修改成本价
					</font>
				</a>
				  &nbsp;&nbsp;
				<a href="javascript:editObject('${tbPartInfo.id}','tbPartInfoForwardAction.action',800,500);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbPartInfo.id}','tbPartInfoDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
			
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="modelName" title="车辆类型" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="50" property="storeQuantity" title="库存" />
			<e3t:column width="70" property="pageCostPrice" title="成本价(含税)" />
			<e3t:column width="70" property="costPrice" title="成本价(不含税)" />
			<e3t:column width="70" property="sellPrice" title="销售价" />
			<e3t:column width="50" property="lianceQuantity" title="借进量" />
			<e3t:column width="50" property="loanQuantity" title="借出量" />
			<e3t:column width="70" property="pinyinCode" title="拼音代码" />
			<e3t:column width="70" property="unitName" title="计量单位" />
			<e3t:column property="typeName" title="配件类型" />
			<e3t:column property="partBroadType" title="配件大类" />
			
		</e3t:table>
	</body> 
</html>

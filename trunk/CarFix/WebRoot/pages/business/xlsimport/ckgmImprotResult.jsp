<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库概貌查询</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<style>
		.count{
			font-size:12px;
			font-weight:bold;
			color:#15428b;
		}
		
	</style>
	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
			pConfig.tbar = [
			{	
					text : '导入',
					iconCls : 'editIcon',
					handler : function() {
					
						var date = new Date();
					
						addObject('initCkgmImportXlsPageAction.action',600,300);
					
					}
				}, '', '-', '',
				{
					text : '刷新',
					iconCls : 'refreshIcon',
					handler : function() {
						refresh_tbPartInfoTable();
					}
				} ];
			}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "ckgmImportXlsResultAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		
	</script>
	<body>
	
			
		<s:form action="ckgmImportXlsResultAction.action">
			
		</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="ckgmImportXlsResultAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="仓库概貌" varStatus="stockInStatus" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="920"
			height="350" >
				<e3t:column property="houseName" title="仓库" />
				<e3t:column property="xlsPjZl" title="配件种类" />
				<e3t:column property="costPrice" title="成本金额" />
				<e3t:column property="sellPrice" title="销售金额" />
				<e3t:column property="liancePrice" title="借进成本" />
				<e3t:column property="loanPrice" title="借出成本" />
				<e3t:column property="no" title="操作" sortable="false">
					<a href="javascript:deleteObject('${tbPartInfo.xlsPK}','ckgmDeleteAction.action')">
						<font color="blue">
							删除
						</font>
					</a>
				</e3t:column>
		</e3t:table>
	</body> 
</html>

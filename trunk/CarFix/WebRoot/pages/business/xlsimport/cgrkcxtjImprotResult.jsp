<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库查询统计</title>
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
					
						addObject('initCgrkImportXlsPageAction.action',600,300);
					
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
			pConfig.form = "cgrkImportXlsResultAction";
			pConfig.showLoadingMsg = true;
		}
		function customerTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "cgrkImportXlsResultAction";
			pConfig.showLoadingMsg = true;
		}
		
		
	</script>
	<body>
	
			
		<s:form action="cgrkImportXlsResultAction.action">
			
		</s:form>
		
		<table>
			<tr>
				<td id="cgmxtd" ></td>
				<td id="gystd" ></td>
			</tr>
		</table>
		
		<e3t:table id="tbPartInfoTable" uri="cgrkImportXlsResultAction.action" var="tbPartInfo" renderTo="cgmxtd"
			scope="request" items="results" mode="ajax" caption="采购单明细" varStatus="stockInStatus" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="350" >
			<e3t:column property="stockInCode" title="入库单号" />
			<e3t:column property="xlsArriveDate" title="入库日期" />
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="50" property="quantity" title="数量" />
			<e3t:column width="70" property="costPrice" title="入库价" />
			<e3t:column property="customerCode" title="供应商号" />
			<e3t:column property="customerName" title="供应商名" />
			<e3t:column property="indent" title="入库凭证" />
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:deleteObject('${tbPartInfo.xlsPK}','cgrkDeleteAction.action')">
					<font color="blue">
						删除
					</font>
				</a>
			</e3t:column>
			<c:if test="${stockInStatus.last}">
				<e3t:appendRow>
				      <e3t:attribute name="style" value="background-color:#CCCCFF"/>
				      <e3t:addCell>
				        小计:
				      </e3t:addCell>
				      <e3t:addCell>
				      ${countVo.stockTotalPrice }
				      </e3t:addCell>
				</e3t:appendRow>
			</c:if>
		</e3t:table>
		
		
		<e3t:table id="customerTable" uri="cgrkImportXlsResultAction.action" var="customer" renderTo="gystd"
			scope="request" items="customerList" mode="ajax" caption="供应商列表" toolbarPosition="false" toolbarShowPolicy="false"
			skin="E3002" pageSize="10" width="320"
			height="350" >
			<e3t:column property="customerCode" title="供应商号" />
			<e3t:column property="customerName" title="供应商名" />
			<e3t:column property="total" title="供货金额" />
		</e3t:table>
	</body> 
</html>

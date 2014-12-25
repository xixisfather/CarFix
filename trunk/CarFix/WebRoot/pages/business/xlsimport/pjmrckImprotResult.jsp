<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件每日出库查询</title>
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
					
						addObject('initPjmrckImportXlsPageAction.action',600,300);
					
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
			pConfig.form = "pjmrckImportXlsResultAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function deleteObj(){
		
		}
	</script>
	<body>
	
			
		<s:form action="pjmrckImportXlsResultAction.action">
			
		</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="pjmrckImportXlsResultAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="配件每日出库清单" varStatus="stockInStatus" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="920"
			height="350" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="stockOutCode" title="单据号码" />
			<e3t:column property="xlsStockOutTypeName" title="出库类型" />
			<e3t:column property="quantity" title="数量" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="sellPrice" title="销售价" />
			<e3t:column  width="60" property="xlsStockOutDate" title="出库日期" />
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:deleteObject('${tbPartInfo.xlsPK}','pjmrckDeleteAction.action')">
					<font color="blue">
						删除
					</font>
				</a>
			</e3t:column>
			
			 <c:if test="${stockInStatus.last}">
				<e3t:appendRow>
				      <e3t:attribute name="style" value="background-color:#CCCCFF"/>
				      <e3t:addCell>
				     	 <span class="count" >	出库成本：</span> 
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalCostPrice }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count"> 出库销售：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalSellPrice }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	 <span class="count">出库种类：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.partCategory }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">出库数量：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalQuantity }</span>
				      </e3t:addCell>
				</e3t:appendRow>
			</c:if>	
		</e3t:table>
	</body> 
</html>

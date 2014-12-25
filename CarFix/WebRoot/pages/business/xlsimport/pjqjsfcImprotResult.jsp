<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件期间收发存查询</title>
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
					
						addObject('initPjqjsfcmportXlsPageAction.action',600,300);
					
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
			pConfig.form = "pjqjsfcImportXlsResultAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		
	</script>
	<body>
	
			
		<s:form action="pjqjsfcImportXlsResultAction.action">
			
		</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="pjqjsfcImportXlsResultAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="配件期间收发存统计" varStatus="stockInStatus" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="350" >
			<e3t:column property="storeHouseName" title="仓库" />
			<e3t:column width="100" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="stockInQuantity" title="入库数量" />
			<e3t:column property="stockInPrice" title="入库金额" />
			<e3t:column property="stockOutQuantity" title="出库数量" />
			<e3t:column property="stockOutPrice" title="出库成本" />
			<e3t:column property="storeQuantity" title="当前库存" />
			<e3t:column property="storePrice" title="当前库存成本" />
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:deleteObject('${tbPartInfo.xlsPK}','pjqjsfcDeleteAction.action')">
					<font color="blue">
						删除
					</font>
				</a>
			</e3t:column>
			 <c:if test="${stockInStatus.last}">
				<e3t:appendRow>
				      <e3t:attribute name="style" value="background-color:#CCCCFF"/>
				      <e3t:addCell>
				     	 <span class="count" >入库数量：</span> 
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalStockInQuantity }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count"> 入库金额：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalStockInPrice }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	 <span class="count">出库数量：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalStockOutQuantity }</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">出库成本：</span>
				      </e3t:addCell>
				      <e3t:addCell>
				      	<span class="count">${countVo.totalStockOutPrice }</span>
				      </e3t:addCell>
				</e3t:appendRow>
			</c:if>	
		</e3t:table>
	</body> 
</html>

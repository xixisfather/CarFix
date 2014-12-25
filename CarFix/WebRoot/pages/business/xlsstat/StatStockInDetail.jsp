<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库查询</title>
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
		function stockInDetailTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function stockInDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findStStockInAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="findStStockInAction.action">
			<table>
				<tr>
					<td valign="top" >
						<table>
							<tr>
								<td>日期从</td>
								<td  ><s:textfield id="beginDate" name="stStockin.beginStockInDate" />
									<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
									到
									<s:textfield id="endDate" name="stStockin.endStockInDate" />
									<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td>配件代码</td>
								<td  ><s:textfield  name="stStockin.partCode" id="partCode" /> </td>
								
							</tr>
							<tr>
								<td >
									<input type="button" value="查询" onclick="stockInDetailTableQuery();" />
								</td>
							</tr>
						</table>
					</td>
					
					
				</tr>
			</table>
		</s:form>
	
		
		<e3t:table id="stockInDetailTable" uri="findStStockInAction.action" var="stockInDetail"
			scope="request" items="stStockinList" mode="ajax"   varStatus="stockInStatus" caption="采购入库明细"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" >
			<e3t:column property="stockInCode" title="入库单号" />
			<e3t:column property="stockInDate" title="入库日期" >
				<fmt:formatDate  value="${stockInDetail.stockInDate}" />
			</e3t:column>
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="50" property="quantity" title="数量" />
			<e3t:column width="70" property="price" title="入库价" />
			<e3t:column property="customerCode" title="供应商号" />
			<e3t:column property="customerName" title="供应商名" />
			<e3t:column property="oucherCode" title="入库凭证" />
			
		</e3t:table>
	</body> 
</html>

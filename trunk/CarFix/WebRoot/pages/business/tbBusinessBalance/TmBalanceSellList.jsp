<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>未结算销售单列表</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<body>
		<s:form action="findBalanceSellAction.action" >
			<s:hidden id="customerId" name="tmStockOut.customerBill" ></s:hidden>
			<table>
				<tr>
					<td>销售单号：</td>
					<td><s:textfield  name="tmStockOut.stockOutCode" ></s:textfield></td>
					
					<td>客户：</td>
					<td><s:textfield  id="customerName" onfocus="openWin()" ></s:textfield></td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmStockOutTableQuery();" />
							
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tmStockOutTable" uri="findBalanceSellAction.action" var="stockOut"
			scope="request" items="stockOutList" mode="ajax" caption="采购单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="700"
			height="250" statusVar="status">
			<e3t:column property="checktmStockOutId" style="width:25px"  title="" sortable="false" >
      			<input type="radio" id="stockOut${status.globalCount}" value="${stockOut.stockOutId}" name="checktmStockOutId" />
    		</e3t:column>
			<e3t:column property="stockOutCode" title="销售单号" width="150"/>
			<e3t:column property="stockOutDate"  title="销售日期" width="150">
				<fmt:formatDate  value="${stockOut.stockOutDate}" pattern="yyyy-MM-dd" />
			</e3t:column>
			<e3t:column property="customerName"  title="客户" width="150"/>
			<e3t:column property="totalPrice"  title="合计总价" width="150"/>
			<e3t:column property="stockOutId" hidden="true" title="出库ID" />
		</e3t:table>
		
	</body> 
</html>
	
	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tmStockOutTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{
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
			pConfig.form = "findBalanceSellAction";
			pConfig.showLoadingMsg = true;
		}
		
		function openWin(){
			var props = "customerId,null,customerName&types=1,3";
			showCommonWin('findAllTmTbCustomerAction.action','客户列表',575,355,props,null)
		}
		
		
    	<%
		
		List stockOutList = (List)request.getAttribute("stockOutList");
		
		int stockOutListCount = 0;
		
		if(null!=stockOutList){
			
			stockOutListCount = stockOutList.size();
		
		}
		
	%>
    
    var stockOutListCount = <%=stockOutListCount%>;
	</script>
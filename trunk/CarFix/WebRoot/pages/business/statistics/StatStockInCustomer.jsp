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
		function customerTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function customerTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "stockInGroupCustomerByStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function detailSubmit(){
			document.forms["stockInGroupCustomerByStatAction"].submit();
		}
	</script>
	<body>
		<s:form action="stockInGroupCustomerByStatAction.action" >
			<s:hidden name="tmStockIn.partCode" id="iframe_partCode" ></s:hidden>
			<s:hidden name="tmStockIn.beginDate" id="iframe_beginDate" ></s:hidden>
			<s:hidden name="tmStockIn.endDate" id="iframe_endDate" ></s:hidden>
			<s:hidden name="tmStockIn.supplierId" id="iframe_supplierId" ></s:hidden>
			<s:hidden name="tmStockIn.partName" id="iframe_partName" ></s:hidden>
			<s:hidden name="tmStockIn.busType" id="iframe_busType" ></s:hidden>
			<s:hidden name="tmStockIn.payMent" id="iframe_payMent" ></s:hidden>
		</s:form>
		<e3t:table id="customerTable" uri="stockInGroupCustomerByStatAction.action" var="customer"
			scope="request" items="customerVos" mode="ajax"  varStatus="userStatus"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" >
			<e3t:column property="customerCode" title="供应商号" />
			<e3t:column property="customerName" title="供应商名" />
			<e3t:column property="totalPrice" title="供货金额" />
			<e3t:column property="thPrice" title="退货金额" />
			<e3t:column property="cjPrice" title="实付金额" />
			<c:if test="${userStatus.last}">
				<e3t:appendRow>
		      	<e3t:attribute name="style" value="background-color:#CCCCFF"/>
		      		<e3t:addCell>
		        		<font color="blue" >供货金额合计:</font>
		     	 	</e3t:addCell>
		   			<e3t:addCell>
		   				${supplierTotalPrice }
				    </e3t:addCell>
			    </e3t:appendRow>
		  	 </c:if>
		</e3t:table>
		
	
		
	</body> 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>其它入库</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/stockin/stockin.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		var gtitle = "${gtitle}";						//grid标题
		
		function openWin(){
			var props = "customerId,customerCode,customerName&types=2,3";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
	</script>
	<body>
		<s:form action="insertOtherStockInAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="code" id="code" ></s:hidden>
			<s:hidden name="totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockIn.supplierId" ></s:hidden>
			<table>
				<tr>
					<td>业务单据：</td>
					<td><s:textfield name="tmStockIn.businessCode" /></td>
					<td>到货日期：</td>
					<td><s:textfield id="arriveDate" name="tmStockIn.arriveDate" />
						<c:if test="${tq == false}">
							<e3c:calendar minDay="0" for="arriveDate" dataFmt="yyyy-MM-dd" />
						</c:if>
						<c:if test="${tq == true}">
							<e3c:calendar for="arriveDate" dataFmt="yyyy-MM-dd" />
						</c:if>
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
				<tr>
					<td>供应商号：</td>
					<td><s:textfield id="customerCode" onfocus="openWin();" /><font color="red">*</font></td>
					<td>供应商名称：</td>
					<td><s:textfield id="customerName" /></td>
					<td>税率：</td>
					<td>
						<select id="sl" >
						 	<s:iterator value="#request.rates" id="r" >
							 	<s:if test="#r.paramvalue==0.17"><option selected="selected" value="${r.paramvalue+1 }"  >${r.paramvalue }</option></s:if>
						 		<s:if test="#r.paramvalue!=0.17"><option value="${r.paramvalue+1 }" >${r.paramvalue }</option></s:if>
						 	</s:iterator>
					 	</select>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="partinfocollectionDiv" ></div>
		 &nbsp;合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;税前合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;税后合计： <SPAN id="totalratepricespan" ></SPAN>
		
	</body> 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>借出登记	</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/stockout/loan.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		var gtitle = "${gtitle}";						//grid标题
		var loanRegTypeName = "${loanRegTypeName}";
		
		function openWin(){
			var props = "customerId,customerCode,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
	</script>
	<body>
		<s:form action="insertLoanRegisterAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmLoanRegister.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmLoanRegister.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmLoanRegister.customerId" />
			<table>
				<tr>
					<td>借出日期：</td>
					<td><s:textfield readonly="true" id="loanDate" name="tmLoanRegister.loanDate" />
						<e3c:calendar for="loanDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
				<tr>
					<td>客户号：</td>
					<td><s:textfield id="customerCode" onfocus="openWin();"  /><font color="red">*</font></td>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" /></td>
				</tr>
			</table>
		</s:form>
		<div id="partinfocollectionDiv" ></div>
		 &nbsp;合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;成本金额合计： <SPAN id="totalpricespan" ></SPAN>
		
	</body> 
</html>
 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>配件领用</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/stockout/stockout.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		var gtitle = "${gtitle}";						//grid标题
		
		
		var stockOutTypeName = "${stockOutTypeName}";
		
		
		function openWin(){
			var props = "userId,userRealName";
			showCommonWin('findAllTmUserAction.action','职工列表',575,355,props,null);
		}
		
		function vaildate(){
			if(document.getElementById("userId").value == "" ){
		 		Ext.MessageBox.alert('温馨提示：', '请选择领用人.', null);
		 		return false;
		 	}
		 	
		 	return true;
		}
	</script>
	<body>
		<s:form action="insertDrawAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden name="tmStockOut.drawPeople" id="userId" ></s:hidden>
			<table>
				<tr>
					<td>领用日期：</td>
					<td><s:textfield name="tmStockOut.stockOutDate" id="stockOutDate" readonly="true" />
						<e3c:calendar for="stockOutDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>领用人：</td>
					<td><s:textfield id="userRealName" onfocus="openWin()" /><font color="red">*</font></td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
		</s:form>
		<div id="partinfocollectionDiv" ></div>
		 &nbsp;合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;税前合计： <SPAN id="totalpricespan" ></SPAN>
		
	</body> 
</html>

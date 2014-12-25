<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>内部领用出库</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/extend/PagingMemoryProxy.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/stockout/innerstockout.js" charset="UTF-8"></script>
	
	<s:head theme="ajax" /> 
	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		var gtitle = "${gtitle}";						//grid标题
		
		function openWin(){
			var props = "userId,userRealName";
			showCommonWin('findAllTmUserAction.action','职工列表',575,355,props,null);
		}

		document.onkeydown = check;
		function check(e) {
		    var code;
		    if (!e) var e = window.event;
		    if (e.keyCode) code = e.keyCode;
		    else if (e.which) code = e.which;
		    if (((event.keyCode == 8) &&                                                    //BackSpace 
		         ((event.srcElement.type != "text" && 
		         event.srcElement.type != "textarea" && 
		         event.srcElement.type != "password") || 
		         event.srcElement.readOnly == true)) || 
		        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||    //CtrlN,CtrlR 
		        (event.keyCode == 116) ) {                                                   //F5 
		        event.keyCode = 0; 
		        event.returnValue = false; 
		    }
		    return true;
		}
				
	</script>
	<body>
	
		<s:form action="insertInnerStockOutAction.action" >
			<s:hidden name="totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden name="tmStockOut.drawPeople" id="userId" ></s:hidden>
			<div id="fatherId" ></div>
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
		&nbsp; 合计数量： <span id="totalnumspan" > </span>&nbsp;&nbsp; 金额合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;
		
		
		
	<br>
	<br>
	<br>
	<div id='tabPlaceHolder'></div>	
	<script language="javascript">
		Ext.onReady(function(){
			TabPanel.create('tabPlaceHolder',430,
			[
				{
					id:'tbPartInfoTab' , title:'配件列表',disabled:false,url:'tabFindPartInfoAction.action'
					
				}
				
			]);
		});
		
	</script>
	</body> 
</html>

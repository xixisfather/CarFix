<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>新增盘点单</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/extend/PagingMemoryProxy.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/check/stockinv2.js" charset="UTF-8"></script>
	
	<s:head theme="ajax" /> 
	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径

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
	
		<s:form action="insertStockInAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="code" id="code" ></s:hidden>
			<s:hidden name="totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockIn.supplierId" ></s:hidden>
			<div id="fatherId" ></div>
			<table>
				<tr>
					<td>盘点单号：</td>
					<td><s:textfield name="tbStoreHouseCheck.checkCode" /></td>
					<td>盘点日期：</td>
					<td><s:textfield id="checkDate" name="tbStoreHouseCheck.checkDate" /><font color="red">*</font>
						<e3c:calendar for="checkDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>经办人：</td>
					<td><s:textfield name="tbStoreHouseCheck.tmUser.userRealName" /></td>
					<td>备注：</td>
					<td><s:textfield name="tbStoreHouseCheck.memo"/></td>
					<td>仓库：</td>
					<td><s:textfield name="tbStoreHouseCheck.storeHouseIds" /></td>
					<td>扎帐日期：</td>
					<td><s:textfield id="zzDate" name="tbStoreHouseCheck.zzDate" /><font color="red">*</font>
						<e3c:calendar for="zzDate" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
			</table>
		</s:form>
		<div id="partinfocollectionDiv" ></div>
		
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

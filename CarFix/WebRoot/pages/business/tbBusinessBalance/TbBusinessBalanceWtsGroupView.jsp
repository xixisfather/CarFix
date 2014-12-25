<%@page import="com.selfsoft.framework.common.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算单--委托书</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
	    <% 
	   
	      Map<String,String> companyMap = Constants.getCompanyMap();
	    
	      if(companyMap.get("xtlName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	
	    	  request.setAttribute("comName", "xtl");
	    	  
	      }
	      
	      else if(companyMap.get("dfbzName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	  
	    	  request.setAttribute("comName", "dfbz");
	    	  
	      }
	      
	      else{
	    	  
	    	  request.setAttribute("comName", "other");
	      }
	    
	    %>
		<s:form action="tbBusinessBalanceWtsInsertAction.action">
			<table>
				<tr>
					<td>
						<s:hidden id="tbFixEntrustId" name="tbBusinessBalance.tbFixEntrust.id"></s:hidden>
						<s:hidden id="tbBusinessBalanceId" name="tbBusinessBalance.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield id="balanceCode" name="tbBusinessBalance.balanceCode" readonly="true"/>
						<font color="blue">#</font>
					</td>
					<td>
						委托书号
					</td>
					<td>
						<s:textfield id="entrustCode" name="tbBusinessBalance.tbFixEntrust.entrustCode" readonly="true"/>
					</td>
					<td>
						车牌号
					</td>	
					<td>
						<s:textfield id="licenseCode" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.licenseCode" readonly="true"/>
						<s:hidden id="carInfoId" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.id"></s:hidden>
					</td>
					
					
				</tr>
				<tr>
					<td>
						客户名称
					</td>
					<td>
						<s:textfield id="customerName" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.tbCustomer.customerName" readonly="true"></s:textfield>
						<s:hidden id="customerId" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.tbCustomer.id"></s:hidden>
					</td>
					<td>
						结算日期
					</td>
					<td>
						<s:bean name="java.util.Date" id="date"></s:bean>
						<s:textfield id="bananceDate" name="tbBusinessBalance.bananceDate" readonly="true">
							<s:param name="value"><s:date name="date" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<font color="blue">#</font>
					</td>
					<td>
						修理类型
					</td>
					<td>
						<s:select id="fixTypeId" name="tbBusinessBalance.tbFixEntrust.tmFixType.id" list="#request.tmFixTypeMap" emptyOption="true" listKey="key" listValue="value" disabled="true"></s:select>
					</td>
					
				</tr>
				<tr>
					<td>
						服务顾问
					</td>
					<td>
						<s:textfield name="tbBusinessBalance.tbFixEntrust.tmUser.userRealName" readonly="true"></s:textfield>
						<s:hidden id="tmUserId_wts" name="tbBusinessBalance.tbFixEntrust.tmUser.id"></s:hidden>
					</td>
					<td>
						旧件处理
					</td>
					<td>
						<s:select id="oldPartDeal" name="tbBusinessBalance.oldPartDeal" list="#request.oldPartDealMap" listKey="key" listValue="value" emptyOption="true" disabled="true"/>
					</td>
					<td>
						结算代码
					</td>
					<td>
						<s:select id="tmBalanceId" name="tbBusinessBalance.tbFixEntrust.tmFixType.tmBalance.id" list="#request.tmBalanceMap" listKey="key" listValue="value" emptyOption="true" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td>
						结算员
					</td>
					<td>
						<s:textfield name="tbBusinessBalance.tmUser.userRealName" readonly="true"></s:textfield>
						<s:hidden id="tmUserId" name="tbBusinessBalance.tmUser.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td style="display: none;">
						
						工时优惠率<s:textfield id="workingHourFavourRate" name="tbBusinessBalance.workingHourFavourRate"/>
						修理材料优惠率<s:textfield id="fixPartFavourRate" name="tbBusinessBalance.fixPartFavourRate"/>
						销售材料优惠率<s:textfield id="solePartFavourRate" name="tbBusinessBalance.solePartFavourRate"/>
						<!--本次用结余款<s:textfield id="usedSaveAmount" name="tbBusinessBalance.usedSaveAmount"/>-->
						本次收款金额<s:textfield id="shouldPayAmount" name="tbBusinessBalance.shouldPayAmount"/>
						付款方式<s:textfield id="payPattern" name="tbBusinessBalance.payPattern"/>
						支票号码<s:textfield id="chequeCode" name="tbBusinessBalance.chequeCode"></s:textfield> 
						结清期限<s:textfield id="balanceDeadTime" name="tbBusinessBalance.balanceDeadTime"/>
						备注<s:textfield id="remark" name="tbBusinessBalance.remark"/>
						
						
						<s:if test="#session.calcMapReturn!=null">
							
							<s:iterator id="itemCode" value="#session.calcMapReturn.keySet()">
								<input type="text" name="params" value="${itemCode}"/>
								<s:iterator id="itemValue" value="#session.calcMapReturn.get(#itemCode)">
									<input type="text" id="${itemCode}" name="${itemCode}" value="${itemValue}"/>
								</s:iterator>
							</s:iterator>
						</s:if>
						
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						
						<c:choose>
							<c:when test="${request.comName=='dfbz'}">
							<input type="button" value="打印东风标致" onclick="javascript:window.open('tbBusinessBalanceTemplatePrintAction.action?id=${tbBusinessBalance.id}&companyName=dfbz')">
							</c:when>
							<c:when test="${request.comName=='xtl'}">
							<input type="button" value="打印雪条龙" onclick="javascript:window.open('tbBusinessBalanceTemplatePrintAction.action?id=${tbBusinessBalance.id}&companyName=xtl')">
							</c:when>
							<c:otherwise>
							<input type="button" value="打印" onclick="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceWtsPrintAction.action',600,300);"/>
						
							</c:otherwise>
						</c:choose>
						
						&nbsp;&nbsp;
						<input type="button" value="退出" onclick="redirectPage();"/>
						
					</td>
				</tr>
			</table>
		</s:form>
		<div id='tabPlaceHolder'></div>
	</body>
</html>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
	function redirectPage(){
		
		if(typeof(parent.win)!='undefined'){
			closeWindow();
		}
		else{
			window.location.href = 'pages/business/tbBusinessBalance/tbBusinessBalanceForwardPageAction!forwardPage.action';
		}
		
	}

	Ext.onReady(function(){
			
		var tbFixEntrustId = document.getElementById('tbFixEntrustId');
		
		var tmBalanceId = document.getElementById('tmBalanceId');
			
		var workingHourFavourRate = document.getElementById('workingHourFavourRate');	
			
		var carInfoId = document.getElementById('carInfoId');
		
		var entrustCode = document.getElementById('entrustCode');
		
		var customerId = document.getElementById('customerId');
		
		var tbBusinessBalanceId = document.getElementById('tbBusinessBalanceId');
		
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbBusinessBalanceTab' , title:'结算清单',disabled:false,url:'tbBusinessBalanceTotalGroupViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustContent' , title:'修理工时',disabled:false,url:'tbFixEntrustContentGroupViewAction.action?tbFixEntrustId='+tbFixEntrustId.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartContent' , title:'修理材料',disabled:false,url:'viewEntrustSellStockGroupAction.action?entrustId='+tbFixEntrustId.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartSoleContent' , title:'销售材料',disabled:false,url:'viewEntrustSellDetailGroupAction.action?trustBill='+entrustCode.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tmBalanceItemTab' , title:'结算项目明细',disabled:false,url:'tmBalanceItemGroupCalcViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value+'&tmBalanceId='+tmBalanceId.value
			}
			
		]);
	});
	
</script>





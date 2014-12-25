<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
				
	</script>
	<body>
		<s:form action="tbBusinessBalanceXsdInsertAction.action" >
			<s:hidden id="tmStockOutId" name="tbBusinessBalance.tmStockOut.id" ></s:hidden>
			<s:hidden id="tmBalanceId" name="tmBalanceId" value="%{#request.tmBalanceId}"></s:hidden>
			<s:hidden id="tbBusinessBalanceId" name="tbBusinessBalance.id"></s:hidden>
			<table>
				<tr>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield id="balanceCode" name="tbBusinessBalance.balanceCode" readonly="true"/>
						<font color="blue">#</font>
					</td>
					<td>销售单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" name="tbBusinessBalance.tmStockOut.stockOutCode" readonly="true" /></td>
					<td>销售日期：</td>
					<td>
						<s:textfield id="stockOutDate" name="tbBusinessBalance.tmStockOut.stockOutDate" readonly="true">
							<s:param name="value"><s:date name="tbBusinessBalance.tmStockOut.stockOutDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
					</td>
						
					<td>销售类型：</td>
					<td><s:select name="tbBusinessBalance.tmStockOut.soleTypeId" list="#request.tsyList" emptyOption="false" listKey="id" listValue="soleName" disabled="true"/></td>
				</tr>
				<tr>	
					<td>客户号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" readonly="true"/></td>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName" readonly="true"/></td>
					<td>
						结算日期
					</td>
					<td>
						
						<s:textfield id="bananceDate" name="tbBusinessBalance.bananceDate" readonly="true">
							<s:param name="value"><s:date name="tbBusinessBalance.bananceDate" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<font color="blue">#</font>
					</td>
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
						已付款金额<s:textfield id="payedAmount" name="tbBusinessBalance.payedAmount"/>
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
	Ext.onReady(function(){
			
		var tmStockOutId = document.getElementById('tmStockOutId');
		
		var tmBalanceId = document.getElementById('tmBalanceId'); 
		
		var tbBusinessBalanceId = document.getElementById('tbBusinessBalanceId');
		
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbBusinessBalanceTab' , title:'结算清单',disabled:false,url:'tbBusinessBalanceTotalViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartSoleContent' , title:'销售材料',disabled:false,url:'tmStockOutDetailViewAction.action?tmStockOutId='+tmStockOutId.value+'&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tmBalanceItemTab' , title:'结算项目明细',disabled:false,url:'tmBalanceItemCalcXsdViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value+'&tmBalanceId='+tmBalanceId.value
			}
			
		]);
	});
	
</script>

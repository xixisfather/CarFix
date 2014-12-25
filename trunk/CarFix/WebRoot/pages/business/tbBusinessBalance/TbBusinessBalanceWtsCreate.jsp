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
	
		<s:form action="tbBusinessBalanceWtsInsertAction.action" onsubmit="return formValidate();">
			<table>
				<tr>
					<td>
						<s:hidden id="tbFixEntrustId" name="tbBusinessBalance.tbFixEntrust.id"></s:hidden>
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
						<s:select id="oldPartDeal" name="tbBusinessBalance.oldPartDeal" list="#request.oldPartDealMap" listKey="key" listValue="value" emptyOption="true"/>
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
						<s:textfield name="tbBusinessBalance.tmUser.userRealName" value="%{#session.tmUser.userRealName}" readonly="true"></s:textfield>
						<s:hidden id="tmUserId" name="tbBusinessBalance.tmUser.id" value="%{#session.tmUser.id}"></s:hidden>
						
					</td>
				</tr>
				<tr>
					<td style="display: none;">
						
						工时优惠率<s:textfield id="workingHourFavourRate" name="tbBusinessBalance.workingHourFavourRate"/>
						修理材料优惠率<s:textfield id="fixPartFavourRate" name="tbBusinessBalance.fixPartFavourRate"/>
						销售材料优惠率<s:textfield id="solePartFavourRate" name="tbBusinessBalance.solePartFavourRate"/>
						<!--本次用结余款<s:textfield id="usedSaveAmount" name="tbBusinessBalance.usedSaveAmount"/>-->
						已付款金额<s:textfield id="payedAmount" name="tbBusinessBalance.payedAmount"/>
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
						
						<s:textfield id="cardZFJE" name="tbBusinessBalance.cardZFJE" value="0">
						
						</s:textfield>
						
						<s:textfield id="cardNo" name="tbMembershipCard.cardNo">
						
						</s:textfield>
						
						<input type="text" id="passFlag" value="${request.pass}"/>
					</td>
				</tr>
				
				<tr>

						
						
					<td align="center" colspan="6">
						<input type="button" value="结算预览" onclick="prePrint();"/>
						&nbsp;&nbsp;
						<input type="submit" value="结算"/>
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
						&nbsp;&nbsp;
						<input type="button" value="退出" onclick="window.location.href='<%= request.getContextPath()%>/business/tbBusinessBalance/tbBusinessBalanceForwardPageAction!forwardPage.action'"/>
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
			
		var tbFixEntrustId = document.getElementById('tbFixEntrustId');
		
		var tmBalanceId = document.getElementById('tmBalanceId');
			
		var workingHourFavourRate = document.getElementById('workingHourFavourRate');	
			
		var carInfoId = document.getElementById('carInfoId');
		
		var entrustCode = document.getElementById('entrustCode');
		
		var customerId = document.getElementById('customerId');

		var cardNo = document.getElementById('cardNo');

		var passFlag = document.getElementById('passFlag');
		
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbBusinessBalanceTab' , title:'结算清单',disabled:false,url:'tbBusinessBalanceTotalFindAction.action?carInfoId='+carInfoId.value+'&cardNo='+cardNo.value+'&passFlag='+passFlag.value
			},
			{
				id:'tbFixEntrustContent' , title:'修理工时',disabled:false,url:'tbFixEntrustContentFindAction.action?tbFixEntrustId='+tbFixEntrustId.value + '&flag=jsd&cardNo='+cardNo.value
			},
			{
				id:'tbFixEntrustPartContent' , title:'修理材料',disabled:false,url:'findEntrustSellStockAction.action?entrustId='+tbFixEntrustId.value+'&cardNo='+cardNo.value
			},
			{
				id:'tbFixEntrustPartSoleContent' , title:'销售材料',disabled:false,url:'findEntrustSellDetailAction.action?trustBill='+entrustCode.value+'&cardNo='+cardNo.value
			},
			{
				id:'tmBalanceItemTab' , title:'结算项目明细',disabled:false,url:'<%= request.getContextPath() %>/pages/message/wait.jsp?type=tmBalanceItemTab'
			}
			
		]);
	});
	
	setTimeout('initTab()',2000);
	
	new Form.Element.Observer("ZJE", 0.5, function(){
		
		var ZJE = document.getElementById('ZJE');
	
		var balanceTotalAll = document.getElementById('tbBusinessBalanceTab').contentWindow.document.getElementById("balanceTotalAll");

		balanceTotalAll.value = ZJE.value;
		
	});
	
	new Form.Element.Observer("XLGSF", 0.5, function(){
		calcAjax('XLGSF');
	});
	
	new Form.Element.Observer("XSJE", 0.5, function(){
		calcAjax('XSJE');
	});
	
	new Form.Element.Observer("XLCLF", 0.5, function(){
		calcAjax('XLCLF');
	});
	
	
	function alertCar(){
	
		var carInfoId = document.getElementById('carInfoId');
	
		hasCarAlertContent(carInfoId.value,2);
	
	}
	
	setTimeout('alertCar()',1000);
	
</script>





<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>特殊结算单--委托书</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessSpecialBalanceUpdateAction.action">
			<table>
				<tr>
					<td>
						<s:hidden id="tbBusinessSpecialBalanceId" name="tbBusinessSpecialBalance.id"></s:hidden>
						<s:hidden id="tbFixEntrustId" name="tbBusinessBalance.tbFixEntrust.id"></s:hidden>
						<s:hidden id="tbBusinessBalanceId" name="tbBusinessBalance.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>
						流水号
					</td>
					<td>
						<s:textfield id="balanceCode" name="tbBusinessSpecialBalance.editCode" readonly="true"/>
						<font color="blue">#</font>
					</td>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield id="balanceCode" name="tbBusinessBalance.balanceCode" readonly="true"/>
					</td>
					<td>
						委托书号
					</td>
					
					<td>
						<s:textfield id="entrustCode" name="tbBusinessBalance.tbFixEntrust.entrustCode" readonly="true"/>
					</td>
				</tr>
				<tr>	
					<td>
						车牌号
					</td>	
					<td>
						<s:textfield id="licenseCode" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.licenseCode" readonly="true"/>
						<s:hidden id="carInfoId" name="tbBusinessBalance.tbFixEntrust.tbCarInfo.id"></s:hidden>
					</td>
				
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
						
						<s:textfield id="bananceDate" name="tbBusinessSpecialBalance.bananceDate" readonly="true">
							<s:param name="value"><s:date name="tbBusinessSpecialBalance.bananceDate" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<e3c:calendar for="bananceDate" dataFmt="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>	
					<td>
						修理类型
					</td>
					<td>
						<s:select id="fixTypeId" name="tbBusinessBalance.tbFixEntrust.tmFixType.id" list="#request.tmFixTypeMap" emptyOption="true" listKey="key" listValue="value" disabled="true"></s:select>
					</td>
					
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
				</tr>
				<tr>	
					<td>
						结算代码
					</td>
					<td>
						<s:select id="tmBalanceId" name="tbBusinessBalance.tbFixEntrust.tmFixType.tmBalance.id" list="#request.tmBalanceMap" listKey="key" listValue="value" emptyOption="true" disabled="true"/>
					</td>
				
					<td>
						结算员
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.tmUser.userRealName" readonly="true"  value="%{#session.tmUser.userRealName}"></s:textfield>
						<s:hidden id="tmUserId" name="tbBusinessSpecialBalance.tmUser.id" value="%{#session.tmUser.id}"></s:hidden>
					</td>
					<td>
						备注
					</td>
					<td>
						<s:textfield size="50" name="tbBusinessSpecialBalance.remark"></s:textfield>
					</td>
				</tr>
				<tr>
					<td style="display: none;">
						
					
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="content">
							<div id="fixWorkingContent" class="x-hide-display">
						<table id="tbWorkingInfoTable" border="0">
							<tr>
								
								<td colspan="2">
									工时优惠率<input type="text" id="workingHourFavourRate" name="workingHourFavourRate" value="${tbBusinessSpecialBalance.workingHourFavourRate}" onblur="initValue();"/>
								
									<input type="hidden" id="w_count" name="w_count" value="0"/>
								</td>
								
							</tr>
							<tr>
								<td width="100">
									操作
								</td>
								<td width="70">
									免费标志
								</td>
								<td width="100">
									修理工位
								</td>
								<td width="150">
									修理内容
								</td>
								<td width="70">
									修理工时
								</td>
								<td width="70">
									工时单价
								</td>
								<td width="80">
									修理工时费
								</td>
							</tr>
							
							<s:if test="#request.tbSpecialWorkingContentList!=null">
								
								<s:iterator id="tbFixEntrustContent" value="#request.tbSpecialWorkingContentList" status="status">
									<s:if test="#status.index==0">
									<tr style="display: none">
										<td><input type="text" id="workingHourPrice" name="workingHourPrice" value="${tbFixEntrustContent.workingHourPrice}"></td>
									</tr>
									</s:if>
									<tr>
										<td>
											<a onclick="deleteTd(this);"><font color="blue">删除</font></a>
											
											<input type="hidden" id="tbWorkingInfoId${status.index}" name="tbWorkingInfoId${status.index}" value="${tbFixEntrustContent.tbWorkingInfo.id}"/>
										</td>
										<td>
											<s:if test="#tbFixEntrustContent.freesymbol==1">
												<input type="checkbox" id="freesymbol${status.index}" name="freesymbol${status.index}" checked="checked" value="1" onclick="initValue();"/>
											</s:if>
											<s:else>
												<input type="checkbox" id="freesymbol${status.index}" name="freesymbol${status.index}" onclick="initValue();"/>
											</s:else>
										</td>
										<td>${tbFixEntrustContent.tbWorkingInfo.stationCode}</td>
										<td>
											<s:if test="#tbFixEntrustContent.stationNameDB==null">
												<input type="text" id="stationName${status.index}" name="stationName${status.index}" value="${tbFixEntrustContent.tbWorkingInfo.stationName}"/>
											</s:if>
											<s:else>
												<input type="text" id="stationName${status.index}" name="stationName${status.index}" value="${tbFixEntrustContent.stationNameDB}"/>
											</s:else>
										</td>
										
										
										<td><input type="text" id="fixHour${status.index}" name="fixHour${status.index}" value="${tbFixEntrustContent.fixHour}" onblur="initValue();"></td>
										<td>
											${tbFixEntrustContent.workingHourPrice}
											
										</td>
										<td><input type="text" id="fixHourAll${status.index}" name="fixHourAll${status.index}" value="${tbFixEntrustContent.fixHourAll}" readonly="true"></td>
									</tr>
									
								</s:iterator>
								
							</s:if>
						</table>
					</div>
					<div id="partContent" class="x-hide-display">
						<table id="partInfoTable">
							<tr>
								
								<td colspan="2">
									材料优惠率<input type="text" id="fixPartFavourRate" name="fixPartFavourRate" value="${tbBusinessSpecialBalance.fixPartFavourRate}" onblur="initValue();"/>
									<input type="hidden" id="f_count" name="f_count" value="0"/>
								</td>
							</tr>
							<tr>
								<td width="100">
									操作
								</td>
								<td width="70">
									是否免费
								</td>
								<td width="100" >
									仓库
								</td>
								<td width="120">
									配件代码
								</td>
								<td width="120">
									配件名称
								</td>
								<td width="70">
									单位
								</td>
								<td width="100">
									发料单价
								</td>
								<td width="100">
									数量
								</td>
								<td width="100">
									总价
								</td>
							</tr>
							
							<s:if test="#request.tbSpecialPartContentList!=null">
								<s:iterator id="sellStock" value="#request.tbSpecialPartContentList" status="status">
									<tr style="display: none">
										
										
									</tr>
									<tr>
										<td><input type="hidden" id="tbPartInfoId${status.index}" name="tbPartInfoId${status.index}" value="${sellStock.tbPartInfo.id}"/><a onclick="deleteTd(this);"><font color="blue">删除</font></a></td>
										
										<td>
											
											<s:if test="#sellStock.isFree==1">
												<input type="checkbox" id="free${status.index}" name="free${status.index}" checked="checked" value="1" onclick="initValue();"/>
											</s:if>
											<s:else>
												<input type="checkbox" id="free${status.index}" name="free${status.index}" onclick="initValue();"/>
											</s:else>
										</td>
										<td>${sellStock.tbPartInfo.houseName}</td>
										<td>${sellStock.tbPartInfo.partCode}</td>
										<td>
											<s:if test="#sellStock.partNameDB==null">
												
												<input type="text" id="partName${status.index}" name="partName${status.index}" value="${sellStock.tbPartInfo.partName}"/>
											</s:if>
											<s:else>
												<input type="text" id="partName${status.index}" name="partName${status.index}" value="${sellStock.partNameDB}"/>
											</s:else>
										</td>
										<td>${sellStock.tbPartInfo.unitName}</td>
										<td><input type="text" id="price${status.index}" name="price${status.index}" value="${sellStock.partPrice}" onblur="initValue();"/></td>
										<td><input type="text" id="partQuantity${status.index}" name="partQuantity${status.index}" value="${sellStock.partQuantity}" onblur="initValue();"/></td>
										<td><input type="text" id="total${status.index}" name="total${status.index}" value="${sellStock.partTotal}" readonly="true"/></td>
									</tr>
								</s:iterator>
								
							</s:if>
						</table>
						</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<table>
							<tr>
								<td colspan="2">
									<b>结算清单:</b>
								</td>
							</tr>
							<s:if test="#session.calcMapReturn!=null">
							
							<s:iterator id="itemCode" value="#session.calcMapReturn.keySet()">
							<tr>	
								<input type="hidden" name="params" value="${itemCode}"/>
								<s:iterator id="itemValue" value="#session.calcMapReturn.get(#itemCode)">
									
									<s:iterator id="tmBalanceItem" value="#request.tmBalanceItemList">
										<s:if test="#tmBalanceItem.itemCode==#itemCode">
											<td>
												${tmBalanceItem.itemName}
											</td>
											<td>
												<input type="text" id="${itemCode}" name="${itemCode}" value="${itemValue}" onblur="calcAjax('${itemCode}')"/>
											</td>
										</s:if>
									</s:iterator>
									
								</s:iterator>
							</tr>	
							</s:iterator>
							</s:if>
							<tr>
								<td></td>
							</tr>
						</table>
					</td>
				
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="确定"/>
						
						&nbsp;&nbsp;
						
						<input type="reset" value="重置"/>
						
						&nbsp;&nbsp;
						<!--<input type="button" value="退出" onclick="javascript:window.location.href='<%=request.getContextPath()%>/business/tbBusinessSpecialBalance/tbBusinessBalanceSelectAction.action'"/>  -->
						
						<input type="button" value="退出" onclick="closeWindow();"/>
					</td>
				
				</tr>
			</table>
		</s:form>
		
		<div id='tabPlaceHolder'></div>
	</body>
</html>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessSpecialBanance/TbBusinessBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
<%
	List workingInfoList = (List)request.getAttribute("tbSpecialWorkingContentList");

	int wCount = 0 ; 
	
	if(null!=workingInfoList){
		wCount = workingInfoList.size();
	}

	List partAll = (List)request.getAttribute("tbSpecialPartContentList");
	
	int fCount = 0 ; 
	
	if(null!=partAll){
		fCount = partAll.size();
	}
%>
function setCount(compId,val){

	var countObject = document.getElementById(compId);

	countObject.value = val;
}

setCount('w_count','<%=wCount%>');

setCount('f_count','<%=fCount%>');

function deleteTd(obj)
{
	if(confirm('确定删除该条工位？')){
		
		obj.parentNode.parentNode.removeNode(true);

		initValue();
	}
}

function calcAjax(itemCode)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tbFixEntrustId = document.getElementById('tbFixEntrustId').value;
	
	var tmBalanceId = document.getElementById('tmBalanceId').value;

	var itemVal = document.getElementById(itemCode).value;
	
	if(itemVal.indexOf(".") == -1){
		itemVal = itemVal + '.00';
	}
	
	if(!validateNonPositiveNum(itemVal))
	{
		
		alert('填写金额不正确');

		return;
	}
	
	var url = 'tbBusinessBalanceCalcAction.action?tbFixEntrustId=' + tbFixEntrustId + '&tmBalanceId='+ tmBalanceId + '&itemCode=' + itemCode + '&itemVal=' + itemVal + '&time=' + time;
	
	var calcAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : getCalcInfo,

		asynchronous : true

	});
}

function getCalcInfo(originalRequest)
{
	var calcInfo = originalRequest.responseText.split(',');
	
	for(var i = 0 ; calcInfo.length-1; i++)
	{
		var itemInfo = calcInfo[i].split(':');
		
		var itemCode = document.getElementById(itemInfo[0]);
		
		var itemValue = itemInfo[1];
		
		itemCode.value = itemValue;
		
	}
	
}

new Form.Element.Observer("XLGSF", 0.5, function(){
	calcAjax('XLGSF');
});

new Form.Element.Observer("XLCLF", 0.5, function(){
	calcAjax('XLCLF');
});

function initValue()
{

	var fixHourAllCount = 0.00;

	var totalCount = 0.00;

	var w_count = document.getElementById('w_count');

	var f_count = document.getElementById('f_count');

	var XLGSF = document.getElementById('XLGSF');

	var XLCLF = document.getElementById('XLCLF');

	var workingHourFavourRate = document.getElementById('workingHourFavourRate');

	var fixPartFavourRate = document.getElementById('fixPartFavourRate');

	if(!clearNoNum22(workingHourFavourRate,0,1)) return;

	if(!clearNoNum22(fixPartFavourRate,0,1)) return;
	
	for(var i =  0 ;i <= w_count.value ; i++){
		
		var fixHourAll = document.getElementById('fixHourAll'+i); 

		var freesymbol = document.getElementById('freesymbol'+i);

		var fixHour = document.getElementById('fixHour'+i);

		var workingHourPrice = document.getElementById('workingHourPrice');
		
		if(null==fixHourAll){
			continue;
		}

		fixHourAll.value = formatFloat(parseFloat(fixHour.value)*parseFloat(workingHourPrice.value),2);
		
		if(freesymbol.checked){
			continue;
		}

		fixHourAllCount += formatFloat(parseFloat(fixHourAll.value)*parseFloat(1-workingHourFavourRate.value),2);
		
	}

	for(var i = 0 ; i <= f_count.value ; i++)
	{
		var total = document.getElementById('total'+i); 

		var free = document.getElementById('free'+i);

		var price = document.getElementById('price'+i);

		var partQuantity = document.getElementById('partQuantity'+i);
		
		if(null==total){

			continue;

		}

		total.value = formatFloat(parseFloat(price.value)*parseFloat(partQuantity.value),2);
		
		if(free.checked){
			continue;
		}

		totalCount += formatFloat(parseFloat(total.value)*parseFloat(1-fixPartFavourRate.value),2);
			
	}

	XLGSF.value = fixHourAllCount;
	
	XLCLF.value = totalCount; 
	
}

//initValue();

Ext.onReady( function() {

	var tabs = new Ext.TabPanel( {
		renderTo : 'content',
		tabWidth : 500,
		width : 1000,
		activeTab : 0,
		frame : true,
		defaults : {
			autoHeight : true
		},
		items : [ {
			contentEl : 'fixWorkingContent',
			title : '修理工位'
		}, {
			contentEl : 'partContent',
			title : '修理材料'
		}
		 ]
	});
});



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
				id:'tbWorkingTab' , title:'修理工位',disabled:false,url:'tbWorkingInfoShowAction.action?carInfoId='+carInfoId.value
				
			},
			{
				id:'tbPartInfoTab' , title:'当前库存',disabled:false,url:'tabShowInfoAction.action'
				
			},
			{
				id:'tbBusinessBalanceTab' , title:'原结算清单',disabled:false,url:'tbBusinessBalanceTotalViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustContent' , title:'原修理工时',disabled:false,url:'tbFixEntrustContentViewAction.action?tbFixEntrustId='+tbFixEntrustId.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartContent' , title:'原修理材料',disabled:false,url:'viewEntrustSellStockAction.action?entrustId='+tbFixEntrustId.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartSoleContent' , title:'原销售材料',disabled:false,url:'viewEntrustSellDetailAction.action?trustBill='+entrustCode.value + '&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tmBalanceItemTab' , title:'原结算项目明细',disabled:false,url:'tmBalanceItemCalcViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value+'&tmBalanceId='+tmBalanceId.value
			}
	]);
});

</script>





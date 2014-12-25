<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>销售出库</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	
	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
				
	</script>
	<body>
		<s:form action="tbBusinessSpecialBalanceXsdInsertAction.action" >
			<s:hidden id="tmStockOutId" name="tbBusinessBalance.tmStockOut.id" ></s:hidden>
			<s:hidden id="tmBalanceId" name="tmBalanceId" value="%{#request.tmBalanceId}"></s:hidden>
			<s:hidden id="tbBusinessBalanceId" name="tbBusinessBalance.id"></s:hidden>
			<table>
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
						<font color="blue">#</font>
					</td>
					<td>销售单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" name="tbBusinessBalance.tmStockOut.stockOutCode" readonly="true" /></td>
				</tr>
				<tr>
					<td>销售日期：</td>
					<td>
						
						<s:textfield id="stockOutDate" name="tbBusinessBalance.tmStockOut.stockOutDate">
							<s:param name="value"><s:date name="tbBusinessBalance.tmStockOut.stockOutDate" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
					</td>
						
					<td>销售类型：</td>
					<td><s:select name="tbBusinessBalance.tmStockOut.soleTypeId" list="#request.tsyList" emptyOption="false" listKey="id" listValue="soleName" disabled="true"/></td>
					
					<td>客户号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" readonly="true"/></td>
				</tr>
				<tr>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName" readonly="true"/></td>
					<td>
						结算日期
					</td>
					<td>
						<s:bean name="java.util.Date" id="date"></s:bean>
						<s:textfield id="bananceDate" name="tbBusinessSpecialBalance.bananceDate">
							<s:param name="value"><s:date name="date" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<e3c:calendar for="bananceDate" dataFmt="yyyy-MM-dd HH:mm:ss"/>
						<font color="blue">#</font>
					</td>
					<td>
						结算员
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.tmUser.userRealName" readonly="true" value="%{#session.tmUser.userRealName}"></s:textfield>
						<s:hidden id="tmUserId" name="tbBusinessSpecialBalance.tmUser.id" value="%{#session.tmUser.id}"></s:hidden>
						
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<table id="partInfoTable">
							<tr>
								
								<td colspan="2">
									材料优惠率<input type="text" id="fixPartFavourRate" name="fixPartFavourRate" value="0.00" onblur="initValue();"/>
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
									销售单价
								</td>
								<td width="100">
									数量
								</td>
								<td width="100">
									总价
								</td>
							</tr>
							
							<s:if test="#request.detVos!=null">
								<s:iterator id="sellStock" value="#request.detVos" status="status">
									<tr style="display: none">
										
										
									</tr>
									<tr>
										<td><input type="hidden" id="tbPartInfoId${status.index}" name="tbPartInfoId${status.index}" value="${sellStock.partinfoId}"/><a onclick="deleteTd(this);"><font color="blue">删除</font></a></td>
										
										<td>
											
											<s:if test="#sellStock.isFree!=1">
												<input type="checkbox" id="free${status.index}" name="free${status.index}" checked="checked" value="1" onclick="initValue();"/>
											</s:if>
											<s:else>
												<input type="checkbox" id="free${status.index}" name="free${status.index}" onclick="initValue();"/>
											</s:else>
										</td>
										<td>${sellStock.houseName}</td>
										<td>${sellStock.partCode}</td>
										<td>
												
												<input type="text" id="partName${status.index}" name="partName${status.index}" value="${sellStock.partName}"/>
											
										</td>
										<td>${sellStock.unitName}</td>
										<td><input type="text" id="price${status.index}" name="price${status.index}" value="${sellStock.price}" onblur="initValue();"/></td>
										<td><input type="text" id="partQuantity${status.index}" name="partQuantity${status.index}" value="${sellStock.quantity}" onblur="initValue();"/></td>
										<td><input type="text" id="total${status.index}" name="total${status.index}" value="${sellStock.total}" readonly="true"/></td>
									</tr>
								</s:iterator>
								
							</s:if>
						</table>
					
					
					</td>
				</tr>	
					
				
				<tr>
					<td colspan="6">
						<table>
							<tr>
								<td colspan="2">
									<b>结算清单:<input type="button" value="计算" onclick="initValue();"><font color="red">(税额不计入总金额)</font></b>
								</td>
							</tr>
							<s:if test="#session.calcMapReturn!=null">
							
							<s:iterator id="itemCode" value="#session.calcMapReturn.keySet()">
							<tr>	
								<input type="hidden" name="params" value="${itemCode}"/>
								<s:iterator id="itemValue" value="#session.calcMapReturn.get(#itemCode)">
									
									<s:iterator id="tmBalanceItem" value="#request.tmBalanceItemList">
										<s:if test="#tmBalanceItem.itemCode==#itemCode">
											<td>${tmBalanceItem.itemName}</td>
											<td>
												<input type="text" id="${itemCode}" name="${itemCode}" value="${itemValue}" onblur="calcAjaxXsd('${itemCode}')"/>
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
						<!--<input type="button" value="退出" onclick="javascript:window.location.href='<%=request.getContextPath()%>/business/tbBusinessSpecialBalance/tbBusinessBalanceSelectAction.action'"/>-->
						<input type="button" value="退出" onclick="closeWindow();"/>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessSpecialBanance/TbBusinessBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
	<%
	List detVos = (List)request.getAttribute("detVos");
	
	int fCount = 0 ; 
	
	if(null!=detVos){
		fCount = detVos.size();
	}
%>
function deleteTd(obj)
{
	if(confirm('确定删除该配件？')){
		
		obj.parentNode.parentNode.removeNode(true);

		initValue();
	}
}

function setCount(compId,val){

	var countObject = document.getElementById(compId);

	countObject.value = val;
}

function calcAjaxXsd(itemCode)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tmStockOutId = document.getElementById('tmStockOutId').value;
	
	var tmBalanceId = document.getElementById('tmBalanceId').value;

	var itemVal = document.getElementById(itemCode).value;
	
	if(''==itemVal)
	{
		
		return;
	}
	
	var url = 'tbBusinessBalanceCalcXsdAction.action?tmStockOutId=' + tmStockOutId + '&tmBalanceId='+ tmBalanceId + '&itemCode=' + itemCode + '&itemVal=' + itemVal + '&time=' + time;
	
	var calcAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : getCalcInfoXsd,

		asynchronous : true

	});
}

function getCalcInfoXsd(originalRequest)
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

new Form.Element.Observer("XSJE", 0.5, function(){
	calcAjaxXsd('XSJE');
});



setCount('f_count','<%=fCount%>');


function initValue()
{

	var totalCount = 0.00;

	var f_count = document.getElementById('f_count');

	var XSJE = document.getElementById('XSJE');

	var fixPartFavourRate = document.getElementById('fixPartFavourRate');

	if(!clearNoNum22(fixPartFavourRate,0,1)) return;

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

	XSJE.value = totalCount; 
	
}

	Ext.onReady(function(){
			
		var tmStockOutId = document.getElementById('tmStockOutId');
		
		var tmBalanceId = document.getElementById('tmBalanceId'); 
		
		var tbBusinessBalanceId = document.getElementById('tbBusinessBalanceId');
		
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbPartInfoTab' , title:'当前库存',disabled:false,url:'partSellShowAction.action'
				
			},
			{
				id:'tbBusinessBalanceTab' , title:'原结算清单',disabled:false,url:'tbBusinessBalanceTotalViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tbFixEntrustPartSoleContent' , title:'原销售材料',disabled:false,url:'tmStockOutDetailViewAction.action?tmStockOutId='+tmStockOutId.value+'&tbBusinessBalanceId='+tbBusinessBalanceId.value
			},
			{
				id:'tmBalanceItemTab' , title:'原结算项目明细',disabled:false,url:'tmBalanceItemCalcXsdViewAction.action?tbBusinessBalanceId='+tbBusinessBalanceId.value+'&tmBalanceId='+tmBalanceId.value
			}
			
		]);
	});
	
</script>
